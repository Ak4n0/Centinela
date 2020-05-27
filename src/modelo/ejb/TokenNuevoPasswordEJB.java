package modelo.ejb;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.TokenNuevoPasswordDAO;
import modelo.pojo.TokenNuevoPassword;

@LocalBean
@Stateless
/**
 * Clase que maneja los tokens relacionados a que los usuarios quieran cambiar de clave
 * @author mique
 *
 */
public class TokenNuevoPasswordEJB {

	@EJB
	UsuariosEJB usuariosEJB;
	
	@EJB
	UtilidadesEJB utilidadesEJB;
	
	/**
	 * Obtiene el token del usuario a partir de su e-mail
	 * @param email Email del usuario
	 * @return TokenNuevoPassword con información del token del usuario
	 */
	public TokenNuevoPassword getTokenFromEmail(String email) {
		if(email == null) return null;
		return TokenNuevoPasswordDAO.obtenerTokenDesdeEmail(email);
	}

	/**
	 * Genera un nuevo token para un usuario
	 * @param email Email del usuario
	 * @return Objeto TokenNuevoPassword con la información necesaria
	 */
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

	/**
	 * Añade un nuevo token al sistema de persistencia
	 * @param tokenNuevoPassword Token a guardar
	 * @return Devuelve true si se consiguió insertar el token, false en caso contrario
	 */
	private boolean setToken(TokenNuevoPassword tokenNuevoPassword) {
		if(tokenNuevoPassword == null) return false;
		return TokenNuevoPasswordDAO.insertarToken(tokenNuevoPassword);
	}
	
}
