package rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import modelo.ejb.JwtEJB;
import modelo.ejb.BlackboxEJB;

@Path("/blackbox")
/**
 * Maneja la comunicación con las blackboxes
 * @author mique
 *
 */
public class BlackboxRest {
	
	@EJB
	JwtEJB jwtEJB;
	
	@EJB
	BlackboxEJB blackboxEJB;
	
	/**
	 * Recibe mensajes de las blackboxes, los procesa y regresa la respuesta
	 * @param token JWT recibido desde una blackbox
	 * @return Devuelve un JWT con la respuesta a la blackbox que envió una petición 
	 */
	@POST
	@Path("/mensajes")
	@Consumes("text/plain")
	@Produces("text/plain")
	public String recepcion(String token) {
		return jwtEJB.interpretar(token);
	}
}
