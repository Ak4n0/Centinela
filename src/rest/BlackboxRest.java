package rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import modelo.ejb.JwtEJB;
import modelo.ejb.BlackboxEJB;

@Path("/blackbox")
public class BlackboxRest {
	
	@EJB
	JwtEJB jwtEJB;
	
	@EJB
	BlackboxEJB blackboxEJB;
	
	@POST
	@Path("/mensajes")
	@Consumes("text/plain")
	@Produces("text/plain")
	public String recepcion(String token) {
		return jwtEJB.interpretar(token);
	}
}
