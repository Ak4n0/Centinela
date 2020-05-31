package modelo.ejb;

import java.util.Base64;
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
import modelo.pojo.BlackboxFullInfo;
import modelo.pojo.IOPort;
import modelo.pojo.UsuarioFullInfo;

@LocalBean
@Stateless
/**
 * Trata toda comunicación con el protocolo JWT
 * @author mique
 *
 */
public class JwtEJB {

	@EJB
	private BlackboxEJB blackboxEJB;
	
	@EJB
	private BlackboxBufferEJB blackboxBufferEJB;
	
	@EJB
	private EmailEJB emailEJB;
	
	@EJB
	private UsuariosEJB usuarioEJB;
	
	@EJB
	private WebSocketEJB webSocketEJB;
	
	/**
	 * Valida e interpreta un JWT
	 * @param token JWT codificador
	 * @return Retorna una respuesta para ser entregada a la blackbox que ha enviado el token
	 */
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
			case "init":
				// Caso especial pues debe devolver información por sí mismo
				return inicializarBlackbox(jwt);
			case "SYN":
				// no hacer nada. Se enviarán datos si los hay
			}
			
			// Preparar respuesta (información que debe enviarse a la blackbox)
			respuesta = obtenerRespuesta(jwt.getIssuer());
		}
		
		return respuesta;
	}

	/**
	 * Comprueba que el JWT provenga de una blackbox válida
	 * @param token JWT cifrado
	 * @return Devuelve el JWT descifrado para que pueda ser leído
	 */
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
	
	/**
	 * Envia a una blackbox información para inicializarla
	 * @param jwt JWT recibido desde la blackbox
	 * @return Devuelve un token jwt para ser enviado a la blackbox
	 */
	private String inicializarBlackbox(DecodedJWT jwt) {
		String uid = jwt.getIssuer();
		String token = null;
		String passwd = null;
		
		// Obtener el password para cifrar
		BlackboxFullInfo blackbox = blackboxEJB.getBlackboxFullInfo(uid);
		if(blackbox != null) {
			passwd = blackbox.getPasswd();
		
			// Obtener datos que se deben enviar a la blackbox
			Builder jwtResponse = newCommonToken(uid);
			jwtResponse.withIssuedAt(new Date());
			
			// Obtener los últimos datos de los puertos de salida
			IOPort io = blackboxEJB.getLastIO(blackbox.getId());
			
			// SUB marca que habrá modificaciones
			// Sólo se enviarán los datos que no sean null en sus respectivos campos
			jwtResponse.withSubject("SUB");
			// Añadir las salidas digitales que haya
			jwtResponse.withClaim("O0", io == null || io.getO0() == null? false: io.getO0());
			jwtResponse.withClaim("O1", io == null || io.getO1() == null? false: io.getO1());
			jwtResponse.withClaim("O2", io == null || io.getO2() == null? false: io.getO2());
			jwtResponse.withClaim("O3", io == null || io.getO3() == null? false: io.getO3());
			
			// Añadir nuevos límites si los hay
			/*
			 * El siguiente algoritmo, extensible a los demas puertos de entrada genera la siguiente
			 * entrada en el json del JWT
			 * "I0": [
			 * 		"inf": x,
			 * 		"sup": y
			 * ]
			 * Incluyendo x y/o y sólo en caso de que existan. Si ninguno de los dos existe el atributo I0
			 * no se incluye
			 */
			Map<String, String> I0umbr = new HashMap<>();
			I0umbr.put("inf", blackbox.getUmbralInferiorI0() == null? "" : String.valueOf(blackbox.getUmbralInferiorI0()));
			I0umbr.put("sup", blackbox.getUmbralSuperiorI0() == null? "" : String.valueOf(blackbox.getUmbralInferiorI0()));
			jwtResponse.withClaim("I0", I0umbr);
			
			Map<String, String> I1umbr = new HashMap<>();
			I1umbr.put("inf", blackbox.getUmbralInferiorI1() == null? "" : String.valueOf(blackbox.getUmbralInferiorI1()));
			I1umbr.put("sup", blackbox.getUmbralSuperiorI1() == null? "" : String.valueOf(blackbox.getUmbralInferiorI1()));
			jwtResponse.withClaim("I1", I1umbr);
			
			Map<String, String> I2umbr = new HashMap<>();
			I2umbr.put("inf", blackbox.getUmbralInferiorI2() == null? "" : String.valueOf(blackbox.getUmbralInferiorI2()));
			I2umbr.put("sup", blackbox.getUmbralSuperiorI2() == null? "" : String.valueOf(blackbox.getUmbralInferiorI2()));
			jwtResponse.withClaim("I2", I2umbr);
			
			Map<String, String> I3umbr = new HashMap<>();
			I3umbr.put("inf", blackbox.getUmbralInferiorI3() == null? "" : String.valueOf(blackbox.getUmbralInferiorI3()));
			I3umbr.put("sup", blackbox.getUmbralSuperiorI3() == null? "" : String.valueOf(blackbox.getUmbralInferiorI3()));
			jwtResponse.withClaim("I3", I3umbr);
			
			token = sign(jwtResponse, passwd);

			System.out.println("Mandando la información de inicialización");
			System.out.println(verPayload(token));
		}
		return token;
	}

	/**
	 * Guarda el estado de E/S que ha indicado la blackbox
	 * @param jwt JWT descifrado
	 */
	private void guardarIO(DecodedJWT jwt) {
		IOPort ioport = new IOPort();
		Claim claim;
		
		Date fecha = jwt.getIssuedAt();
		if(fecha == null) {
			fecha = new Date();
		}
		ioport.setFechaHora(fecha);
		
		claim = jwt.getClaim("I0");
		if(!claim.isNull()) {
			ioport.setI0(claim.asInt());
		}
		
		claim = jwt.getClaim("I1");
		if(!claim.isNull()) {
			ioport.setI1(claim.asInt());
		}
		
		claim = jwt.getClaim("I2");
		if(!claim.isNull()) {
			ioport.setI2(claim.asInt());
		}
		
		claim = jwt.getClaim("I3");
		if(!claim.isNull()) {
			ioport.setI3(claim.asInt());
		}
		
		claim = jwt.getClaim("O0");
		if(!claim.isNull()) {
			ioport.setO0(claim.asBoolean());
		}
		
		claim = jwt.getClaim("O1");
		if(!claim.isNull()) {
			ioport.setO1(claim.asBoolean());
		}
		
		claim = jwt.getClaim("O2");
		if(!claim.isNull()) {
			ioport.setO2(claim.asBoolean());
		}
		
		claim = jwt.getClaim("O3");
		if(!claim.isNull()) {
			ioport.setO3(claim.asBoolean());
		}
		
		BlackboxAdminInfo blackbox = blackboxEJB.getBlackbox(jwt.getIssuer());
		if(blackbox == null)
			return;
		
		ioport.setIdBlackbox(blackbox.getId());
		
		blackboxEJB.addIOPortEntry(ioport);
		webSocketEJB.enviarData(ioport);
	}

	/**
	 * Guarda una alarma indicada por una blackbox
	 * @param jwt JWT descifrado
	 */
	private void guardarAlarma(DecodedJWT jwt) {
		Alarma alarma = new Alarma();
		Claim claim;
		
		Date fecha = jwt.getIssuedAt();
		if(fecha == null) {
			fecha = new Date();
		}
		alarma.setFechaHora(fecha);
		
		claim = jwt.getClaim("valor");
		if(!claim.isNull()) {
			alarma.setValor(claim.asInt());
		} else {
			return;
		}
		
		claim = jwt.getClaim("puerto");
		if(!claim.isNull()) {
			alarma.setPuerto(claim.asString());
		} else {
			return;
		}
		
		claim = jwt.getClaim("umbral");
		if(!claim.isNull()) {
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
	
	/**
	 * Obtiene la cadena de un jwt cifrado con los datos a modificar para una blackbox dada
	 * @param blackboxUID Identificador único de la blackbox a responder
	 * @return JWT cifrado, listo para mandar
	 */
	private String obtenerRespuesta(String blackboxUID) {
		String token = null;
		String passwd = null;
		
		// Obtener el password para cifrar
		BlackboxAdminInfo b = blackboxEJB.getBlackbox(blackboxUID);
		if(b != null) {
			passwd = b.getPasswd();
		
			// Obtener datos que se deben enviar a la blackbox
			BlackboxBuffer blackbox = blackboxBufferEJB.extraer(blackboxUID);
			Builder jwt = newCommonToken(blackboxUID);
			jwt.withIssuedAt(new Date());
			
			// No hay modificaciones. Devolver EOT (fin de transmisión)
			if(blackbox == null) {
				jwt.withSubject("EOT");
			} else {
			// Sí hay datos que devolver. Incluirlos en el jwt
				// SUB marca que habrá modificaciones
				// Sólo se enviarán los datos que no sean null en sus respectivos campos
				jwt.withSubject("SUB");
				
				// Añadir las salidas digitales que haya
				if(blackbox.getSalidaO0() != null) {
					jwt.withClaim("O0", blackbox.getSalidaO0());
				}
				if(blackbox.getSalidaO1() != null) {
					jwt.withClaim("O1", blackbox.getSalidaO1());
				}
				if(blackbox.getSalidaO2() != null) {
					jwt.withClaim("O2", blackbox.getSalidaO2());
				}
				if(blackbox.getSalidaO3() != null) {
					jwt.withClaim("O3", blackbox.getSalidaO3());
				}
				// Añadir nuevos límites si los hay
				/*
				 * El siguiente algoritmo, extensible a los demas puertos de entrada genera la siguiente
				 * entrada en el json del JWT
				 * "I0": [
				 * 		"inf": x,
				 * 		"sup": y
				 * ]
				 * Incluyendo x y/o y sólo en caso de que existan. Si ninguno de los dos existe el atributo I0
				 * no se incluye
				 */
				if(blackbox.getCambioUmbral() != null && blackbox.getCambioUmbral() == true) {
					Map<String, String> I0umbr = new HashMap<>();
					I0umbr.put("inf", blackbox.getUmbralInferiorI0() == null? "" : String.valueOf(blackbox.getUmbralInferiorI0()));
					I0umbr.put("sup", blackbox.getUmbralSuperiorI0() == null? "" : String.valueOf(blackbox.getUmbralSuperiorI0()));
					jwt.withClaim("I0", I0umbr);
					
					Map<String, String> I1umbr = new HashMap<>();
					I1umbr.put("inf", blackbox.getUmbralInferiorI1() == null? "" : String.valueOf(blackbox.getUmbralInferiorI1()));
					I1umbr.put("sup", blackbox.getUmbralSuperiorI1() == null? "" : String.valueOf(blackbox.getUmbralSuperiorI1()));
					jwt.withClaim("I1", I1umbr);
					
					Map<String, String> I2umbr = new HashMap<>();
					I2umbr.put("inf", blackbox.getUmbralInferiorI2() == null? "" : String.valueOf(blackbox.getUmbralInferiorI2()));
					I2umbr.put("sup", blackbox.getUmbralSuperiorI2() == null? "" : String.valueOf(blackbox.getUmbralSuperiorI2()));
					jwt.withClaim("I2", I2umbr);
					
					Map<String, String> I3umbr = new HashMap<>();
					I3umbr.put("inf", blackbox.getUmbralInferiorI3() == null? "" : String.valueOf(blackbox.getUmbralInferiorI3()));
					I3umbr.put("sup", blackbox.getUmbralSuperiorI3() == null? "" : String.valueOf(blackbox.getUmbralSuperiorI3()));
					jwt.withClaim("I3", I3umbr);
				}
			}
			token = sign(jwt, passwd);
			
		}
		return token;
	}
	
	/**
	 * Cambia el password a una blackbox en la base de datos
	 * @param jwt
	 */
	private void cambiarPassword(DecodedJWT jwt) {
		// Obtener la blackbox para cambiarle la contraseña
		BlackboxAdminInfo blackbox = blackboxEJB.getBlackbox(jwt.getIssuer());
		blackboxEJB.cambiarPasswd(blackbox.getId(), jwt.getClaim("valor").asString());
	}
	
	/**
	 * Rellena y regresa la parte común de un JWT de respuesta
	 * @param blackboxUID Identificador único de una blackbox
	 * @return Objeto Builder con los campos comunes de un JWT rellenados
	 */
	private Builder newCommonToken(String blackboxUID) {
		Builder token = JWT.create();
		token.withIssuer("Centinela");
		token.withAudience(blackboxUID);
		token.withIssuedAt(new Date());
		
		return token;
	}
	
	/**
	 * Firma un objeto Builder y lo transforma en un token JWT
	 * @param token Objeto Builder que contiene toda la información del JWT
	 * @param passwd Contraseña con la que debe ser firmado el JWT
	 * @return JWT cifrado listo para enviar
	 */
	private String sign(Builder token, String passwd) {
		Algorithm algorithm = Algorithm.HMAC256(passwd);
		return token.sign(algorithm);
	}
	
	/**
	 * Muestra el payload de un jwt
	 * @param jwt jwt del que se desea ver el payload
	 * @return Cadena con la información del payload
	 */
	private String verPayload(String jwt) {
		String payload = JWT.decode(jwt).getPayload();
		byte[] decodedBytes = Base64.getDecoder().decode(payload);
		String retValue = new String(decodedBytes);
		return retValue;
	}
}
