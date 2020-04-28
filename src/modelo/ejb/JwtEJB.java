package modelo.ejb;

import java.util.Date;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import modelo.enumeracion.TipoError;
import modelo.pojo.Alarma;
import modelo.pojo.BlackboxAdminInfo;
import modelo.pojo.IOPort;

@LocalBean
@Stateless
public class JwtEJB {

	@EJB
	OperacionesBlackboxesEJB operacionesBlackboxesEJB;
	
	public DecodedJWT decodificar(String jwt) {
		JWT token = new JWT();
		return token.decodeJwt(jwt);
	}
	
	public TipoError auntentificar(DecodedJWT jwt) {
		if(jwt == null) {
			return TipoError.OBJETO_ERRONEO;
		}
		
		String issuer = jwt.getIssuer();
		if(issuer == null) {
			return TipoError.DATOS_INCOMPLETOS; 
		}
		
		BlackboxAdminInfo blackbox = operacionesBlackboxesEJB.getBlackbox(issuer);
		if(blackbox == null) {
			return TipoError.CREDENCIALES;
		}
		
		String clave = blackbox.getPasswd();
		try {
		    Algorithm algorithm = Algorithm.HMAC256(clave);
		    JWTVerifier verifier = JWT.require(algorithm)
		        .withIssuer(issuer)
		        .build();
		    jwt = verifier.verify(jwt);
		} catch (JWTVerificationException exception){
		    return TipoError.CREDENCIALES;
		}
		
		return TipoError.SIN_ERROR;
	}

	public IOPort obtenerIOPort(DecodedJWT jwt) {
		IOPort ioport = new IOPort();
		Claim claim;
		ioport.setFechaHora(new Date());
		
		claim = jwt.getClaim("I0");
		if(claim == null)
			return null;
		ioport.setI0(claim.asInt());
		
		claim = jwt.getClaim("I1");
		if(claim == null)
			return null;
		ioport.setI1(claim.asInt());
		
		claim = jwt.getClaim("I2");
		if(claim == null)
			return null;
		ioport.setI2(claim.asInt());
		
		claim = jwt.getClaim("I3");
		if(claim == null)
			return null;
		ioport.setI3(claim.asInt());
		
		claim = jwt.getClaim("O0");
		if(claim == null)
			return null;
		ioport.setO0(claim.asInt());
		
		claim = jwt.getClaim("O1");
		if(claim == null)
			return null;
		ioport.setO1(claim.asInt());
		
		claim = jwt.getClaim("O2");
		if(claim == null)
			return null;
		ioport.setO2(claim.asInt());
		
		claim = jwt.getClaim("O3");
		if(claim == null)
			return null;
		ioport.setO3(claim.asInt());
		
		BlackboxAdminInfo blackbox = operacionesBlackboxesEJB.getBlackbox(jwt.getIssuer());
		if(blackbox == null)
			return null;
		
		ioport.setIdBlackbox(blackbox.getId());
		
		return ioport;
	}

	public Alarma obtenerAlarma(DecodedJWT jwt) {
		Alarma alarma = new Alarma();
		Claim claim;
		alarma.setFechaHora(new Date());
		
		claim = jwt.getClaim("nivel");
		if(claim == null)
			return null;
		alarma.setNivel(claim.asInt());
		
		claim = jwt.getClaim("puerto");
		if(claim == null)
			return null;
		alarma.setPuerto(claim.asString());
		
		claim = jwt.getClaim("umbral");
		if(claim == null)
			return null;
		alarma.setValorUmbral(claim.asInt());
		
		BlackboxAdminInfo blackbox = operacionesBlackboxesEJB.getBlackbox(jwt.getIssuer());
		if(blackbox == null)
			return null;
		
		alarma.setIdBlackbox(blackbox.getId());
		
		return alarma;
	}
	
	public String obtenerIP(DecodedJWT jwt) {
		Claim claim = jwt.getClaim("ip");
		if(claim == null)
			return null;
		
		String ip = claim.asString();
		
		String zeroTo255 = "([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])";

		String IP_REGEXP = zeroTo255 + "\\." + zeroTo255 + "\\." + zeroTo255 + "\\." + zeroTo255;
		
		Pattern IP_PATTERN = Pattern.compile(IP_REGEXP);
		
		return IP_PATTERN.matcher(ip).matches()? ip : null;
	}
	
}
