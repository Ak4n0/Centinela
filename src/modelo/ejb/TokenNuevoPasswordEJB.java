package modelo.ejb;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.TokenNuevoPasswordDAO;
import modelo.pojo.TokenNuevoPassword;

@LocalBean
@Stateless
public class TokenNuevoPasswordEJB {

	@EJB
	UsuariosEJB usuariosEJB;
	
	@EJB
	UtilidadesEJB utilidadesEJB;
	
	public TokenNuevoPassword getTokenFromEmail(String email) {
		if(email == null) return null;
		return TokenNuevoPasswordDAO.obtenerTokenDesdeEmail(email);
	}

	public TokenNuevoPassword createToken(String email) {
		if(email == null) return null;
		
		// Obtener id del usuario con ese email
		Integer idUsuario =  usuariosEJB.getIdFromEmail(email);
		
		// Obtener la nueva hora de creación del token
		Date momentoAhora = new Date();
		
		// Obtener el token
		String clave = email + momentoAhora.getTime();
		String token = utilidadesEJB.convertirSHA256(clave);
		TokenNuevoPassword tokenNuevoPassword = new TokenNuevoPassword();
		
		tokenNuevoPassword.setId(token);
		tokenNuevoPassword.setIdUsuario(idUsuario);
		tokenNuevoPassword.setFechaHora(momentoAhora);
		
		// Introducir el nuevo token en la BBDD
		boolean exito = setToken(tokenNuevoPassword);
		
		// Si la inserción en la base de datos tuvo éxito retornar el token, null en caso contrario
		return exito? tokenNuevoPassword : null;
	}

	private boolean setToken(TokenNuevoPassword tokenNuevoPassword) {
		if(tokenNuevoPassword == null) return false;
		return TokenNuevoPasswordDAO.insertarToken(tokenNuevoPassword);
	}
	
}
