package rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.auth0.jwt.interfaces.DecodedJWT;

import modelo.ejb.JwtEJB;
import modelo.ejb.OperacionesBlackboxesEJB;
import modelo.pojo.Alarma;
import modelo.pojo.IOPort;

@Path("/blackbox")
public class BlackboxRest {
	
	@EJB
	JwtEJB jwtEJB;
	
	@EJB
	OperacionesBlackboxesEJB operacionesBlackboxesEJB;
	
	@POST
	@Path("/regular")
	@Consumes("text/plain")
	@Produces("text/plain")
	public String nuevoDatoRegular(String encodedJwt) {
		DecodedJWT jwt = jwtEJB.decodificar(encodedJwt);
		switch(jwtEJB.auntentificar(jwt)) {
		case CREDENCIALES:
			return "NAK";
		case DATOS_INCOMPLETOS:
			return "NAK";
		case OBJETO_ERRONEO:
			return "NAK";
		case SIN_ERROR:
			break;
		}
		
		IOPort ioport = jwtEJB.obtenerIOPort(jwt);
		if(ioport == null) {
			return "NAK";
		}
		
		String ip = jwtEJB.obtenerIP(jwt);
		if(ip == null) {
			return "NAK";
		}
		
		operacionesBlackboxesEJB.actualizarIP(ioport.getIdBlackbox(), ip);
		
		operacionesBlackboxesEJB.addIOPortEntry(ioport);
		
		return "ACK";
	}
	
	@POST
	@Path("/alarm")
	@Consumes("text/plain")
	@Produces("text/plain")
	public String nuevaAlarma(String encodedJwt) {
		DecodedJWT jwt = jwtEJB.decodificar(encodedJwt);
		switch(jwtEJB.auntentificar(jwt)) {
		case CREDENCIALES:
			return "NAK";
		case DATOS_INCOMPLETOS:
			return "NAK";
		case OBJETO_ERRONEO:
			return "NAK";
		case SIN_ERROR:
			break;
		}
		
		Alarma alarma = jwtEJB.obtenerAlarma(jwt);
		
		if(alarma == null) {
			return "NAK";
		}
		
		String ip = jwtEJB.obtenerIP(jwt);
		if(ip == null) {
			return "NAK";
		}
		
		operacionesBlackboxesEJB.actualizarIP(alarma.getIdBlackbox(), ip);
		
		operacionesBlackboxesEJB.addAlarma(alarma);
		
		return "ACK";
	}
}
