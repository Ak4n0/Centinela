package modelo.ejb;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import modelo.pojo.Alarma;
import modelo.pojo.BlackboxAdminInfo;
import modelo.pojo.BlackboxBuffer;
import modelo.pojo.IOPort;
import modelo.pojo.UsuarioFullInfo;

@LocalBean
@Stateless
public class JwtEJB {

	@EJB
	BlackboxEJB blackboxEJB;
	
	@EJB
	BlackboxBufferEJB blackboxBufferEJB;
	
	@EJB
	EmailEJB emailEJB;
	
	@EJB
	UsuariosEJB usuarioEJB;
	
	public String interpretar(String token) {
		DecodedJWT jwt = autentificar(token);
		String respuesta = null;
		if(jwt != null) {
			// Decodificar el mensaje
			switch(jwt.getSubject()) {
			case "regular":
				guardarIO(jwt);
				break;
			case "alarma":
				guardarAlarma(jwt);
				break;
			case "pwd":
				cambiarPassword(jwt);
				break;
			case "SYN":
				// no hacer nada. Se enviarán datos si los hay
			}
			
			// Preparar respuesta (información que debe enviarse a la blackbox)
			respuesta = obtenerRespuesta(jwt.getIssuer());
		}
		
		return respuesta;
	}
	
	private DecodedJWT autentificar(String token) {
		if(token == null) {
			return null;
		}
		
		DecodedJWT jwt = JWT.decode(token);
		
		if(jwt == null) {
			return null;
		}
		
		String issuer = jwt.getIssuer();
		if(issuer == null) {
			return null; 
		}
		
		BlackboxAdminInfo blackbox = blackboxEJB.getBlackbox(issuer);
		if(blackbox == null) {
			return null;
		}
		
		String clave = blackbox.getPasswd();
		try {
		    Algorithm algorithm = Algorithm.HMAC256(clave);
		    JWTVerifier verifier = JWT.require(algorithm)
		        .withAudience("Centinela")
		        .build();
		    jwt = verifier.verify(jwt);
		} catch (JWTVerificationException exception){
		    return null;
		}
		
		return jwt;
	}

	private void guardarIO(DecodedJWT jwt) {
		IOPort ioport = new IOPort();
		Claim claim;
		
		Date fecha = jwt.getIssuedAt();
		if(fecha == null) {
			fecha = new Date();
		}
		ioport.setFechaHora(fecha);
		
		claim = jwt.getClaim("I0");
		if(claim != null) {
			ioport.setI0(claim.asInt());
		}
		
		claim = jwt.getClaim("I1");
		if(claim != null) {
			ioport.setI1(claim.asInt());
		}
		
		claim = jwt.getClaim("I2");
		if(claim != null) {
			ioport.setI2(claim.asInt());
		}
		
		claim = jwt.getClaim("I3");
		if(claim != null) {
			ioport.setI3(claim.asInt());
		}
		
		claim = jwt.getClaim("O0");
		if(claim != null) {
			ioport.setO0(claim.asInt());
		}
		
		claim = jwt.getClaim("O1");
		if(claim != null) {
			ioport.setO1(claim.asInt());
		}
		
		claim = jwt.getClaim("O2");
		if(claim != null) {
			ioport.setO2(claim.asInt());
		}
		
		claim = jwt.getClaim("O3");
		if(claim != null) {
			ioport.setO3(claim.asInt());
		}
		
		BlackboxAdminInfo blackbox = blackboxEJB.getBlackbox(jwt.getIssuer());
		if(blackbox == null)
			return;
		
		ioport.setIdBlackbox(blackbox.getId());
		
		blackboxEJB.addIOPortEntry(ioport);
	}

	private void guardarAlarma(DecodedJWT jwt) {
		Alarma alarma = new Alarma();
		Claim claim;
		
		Date fecha = jwt.getIssuedAt();
		if(fecha == null) {
			fecha = new Date();
		}
		alarma.setFechaHora(fecha);
		
		claim = jwt.getClaim("valor");
		if(claim != null) {
			alarma.setValor(claim.asInt());
		} else {
			return;
		}
		
		claim = jwt.getClaim("puerto");
		if(claim != null) {
			alarma.setPuerto(claim.asString());
		} else {
			return;
		}
		
		claim = jwt.getClaim("umbral");
		if(claim != null) {
			alarma.setValorUmbral(claim.asInt());
		} else {
			return;
		}
		
		BlackboxAdminInfo blackbox = blackboxEJB.getBlackbox(jwt.getIssuer());
		if(blackbox == null)
			return;
		
		alarma.setIdBlackbox(blackbox.getId());
		
		blackboxEJB.addAlarma(alarma);
		
		// mandar un e-mail con la alarma
		// - obtener el nombre de la blackbox
		String nombreBlackbox = blackbox.getNombre();
		if(nombreBlackbox == null || nombreBlackbox.isEmpty()) {
			nombreBlackbox = jwt.getIssuer();
		}
		// - obtener el nombre del puerto
		String nombrePuerto = blackboxEJB.getPortName(blackbox.getId(), jwt.getClaim("puerto").asString());
		if(nombrePuerto == null || nombrePuerto.isEmpty()) {
			nombrePuerto = jwt.getClaim("puerto").asString();
		}
		// - obtener el e-mail del usuario
		UsuarioFullInfo usuario = usuarioEJB.getDatabaseUser(blackbox.getIdUsuario());
		// - obtener el cuerpo del mensaje
		String cuerpoEmail = emailEJB.cuerpoMensajeAlarma(blackbox.getNombreUsuario(), nombreBlackbox, nombrePuerto);
		// - enviarl el e-mail
		emailEJB.sendMail(usuario.getEmail(), "Centinela - Aviso de alarma", cuerpoEmail);
	}
	
	private String obtenerRespuesta(String blackboxUID) {
		String token = null;
		
		// Obtener datos que se deben enviar a la blackbox
		BlackboxBuffer blackbox = BlackboxBufferEJB.extraer(blackboxUID);
		Builder jwt = newCommonToken(blackboxUID);
		jwt.withIssuedAt(new Date());
		// No hay modificaciones. Devolver EOT (fin de transmisión)
		if(blackbox == null) {
			jwt.withSubject("EOT");
		} else {
		// Sí hay datos que devolver. Incluirlos en el jwt
			// SUB marca que habrá modificaciones
			jwt.withSubject("SUB");
			if(blackbox.getNuevoPasswd() != null) {
				jwt.withClaim("pwd", blackbox.getNuevoPasswd());
			}
			if(blackbox.getSalidaO0() != null) {
				jwt.withClaim("O0", blackbox.getSalidaO0());
			}
			if(blackbox.getSalidaO1() != null) {
				jwt.withClaim("O1", blackbox.getSalidaO0());
			}
			if(blackbox.getSalidaO2() != null) {
				jwt.withClaim("O2", blackbox.getSalidaO0());
			}
			if(blackbox.getSalidaO3() != null) {
				jwt.withClaim("O3", blackbox.getSalidaO0());
			}
			if(blackbox.getLimiteInferiorI0() != null || blackbox.getLimiteSuperiorI0() != null) {
				Map<String, Integer> salidas = new HashMap<>();
				if(blackbox.getLimiteInferiorI0() != null) {
					salidas.put("inf", blackbox.getLimiteInferiorI0());
				}
				if(blackbox.getLimiteSuperiorI0() != null) {
					salidas.put("sup", blackbox.getLimiteSuperiorI0());
				}
				jwt.withClaim("I0", salidas);
			}
			if(blackbox.getLimiteInferiorI1() != null || blackbox.getLimiteSuperiorI1() != null) {
				Map<String, Integer> salidas = new HashMap<>();
				if(blackbox.getLimiteInferiorI1() != null) {
					salidas.put("inf", blackbox.getLimiteInferiorI1());
				}
				if(blackbox.getLimiteSuperiorI1() != null) {
					salidas.put("sup", blackbox.getLimiteSuperiorI1());
				}
				jwt.withClaim("I1", salidas);
			}
			if(blackbox.getLimiteInferiorI2() != null || blackbox.getLimiteSuperiorI2() != null) {
				Map<String, Integer> salidas = new HashMap<>();
				if(blackbox.getLimiteInferiorI2() != null) {
					salidas.put("inf", blackbox.getLimiteInferiorI2());
				}
				if(blackbox.getLimiteSuperiorI2() != null) {
					salidas.put("sup", blackbox.getLimiteSuperiorI2());
				}
				jwt.withClaim("I2", salidas);
			}
			if(blackbox.getLimiteInferiorI3() != null || blackbox.getLimiteSuperiorI3() != null) {
				Map<String, Integer> salidas = new HashMap<>();
				if(blackbox.getLimiteInferiorI3() != null) {
					salidas.put("inf", blackbox.getLimiteInferiorI3());
				}
				if(blackbox.getLimiteSuperiorI3() != null) {
					salidas.put("sup", blackbox.getLimiteSuperiorI3());
				}
				jwt.withClaim("I3", salidas);
			}
			token = sign(jwt, blackbox.getPasswd());
		}
		
		return token;
	}
	
	private void cambiarPassword(DecodedJWT jwt) {
		// Obtener la blackbox para cambiarle la contraseña
		BlackboxAdminInfo blackbox = blackboxEJB.getBlackbox(jwt.getIssuer());
		blackboxEJB.cambiarPasswd(blackbox.getId(), jwt.getClaim("valor").asString());
	}
	
	private Builder newCommonToken(String blackboxUID) {
		Builder token = JWT.create();
		token.withIssuer("Centinela");
		token.withAudience(blackboxUID);
		token.withIssuedAt(new Date());
		
		return token;
	}
	
	private String sign(Builder token, String passwd) {
		Algorithm algorithm = Algorithm.HMAC256(passwd);
		return token.sign(algorithm);
	}
}