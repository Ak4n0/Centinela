package modelo.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import modelo.dao.UsuarioDAO;
import modelo.pojo.PeticionNuevoUsuario;
import modelo.pojo.UsuarioAdminInfo;
import modelo.pojo.UsuarioFullInfo;

@LocalBean
@Stateless
/**
 * Clase maneja los usuarios en sistemas de persistencia
 * @author mique
 *
 */
public class UsuariosEJB {

	/**
	 * Obtiene el usuario de la sesión
	 * @param request Objeto request
	 * @return Retorna un UsuarioFullInfo rellenado, si existe usuario en la sesión o null si no existe
	 */
	public UsuarioFullInfo getSessionUser(HttpServletRequest request) {
		UsuarioFullInfo user = null;
		
		HttpSession session = request.getSession(false);
		if(session != null) {
			user = (UsuarioFullInfo) session.getAttribute("usuario");
		}
		
		return user;
	}
	
	/**
	 * Obtiene un usuario de la base de datos a partir del e-mail y del password
	 * @param email Email del usuario
	 * @param passwd Contraseña del usuario
	 * @return Retorna un UsuarioFullInfo rellenado, si existe usuario en la sesión o null si no existe
	 */
	public UsuarioFullInfo getDatabaseUser(String email, String passwd) {
		UsuarioFullInfo usuarioResult = null;
		
		UsuarioFullInfo usuarioArgumento = new UsuarioFullInfo();
		usuarioArgumento.setEmail(email);
		usuarioArgumento.setPasswd(passwd);
		
		usuarioResult = UsuarioDAO.getUsuario(usuarioArgumento);
		
		return usuarioResult;
	}

	/**
	 * Obtiene un usuario del sistema de persistencia a partir de su id interno
	 * @param id Id interno del sistema de persistencia del usuario
	 * @return Retorna un UsuarioFullInfo rellenado, si existe usuario en la sesión o null si no existe
	 */
	public UsuarioFullInfo getDatabaseUser(int id) {
		return UsuarioDAO.getUsuario(id);
	}
	
	/**
	 * Indica si existe un usuario con un email dado
	 * @param email Email del usuario
	 * @return Devuelve true si el usuario existe, false en caso contrario
	 */
	public boolean existeUsuario(String email) {
		return UsuarioDAO.existeUsuario(email);
	}

	/**
	 * Establece un usuario en la sesión del web
	 * @param request Objeto HttpServletRequest del servlet
	 * @param usuario Objeto UsuarioFullInfo relleno con la información del usuario
	 */
	public void setSessionUser(HttpServletRequest request, UsuarioFullInfo usuario) {
		request.getSession().setAttribute("usuario", usuario);
	}

	/**
	 * Obtiene el nombre de un usuario según su e-mail
	 * @param email Email del usuario
	 * @return Retorna el nombre de usuario
	 */
	public String getUserName(String email) {
		return UsuarioDAO.getNombreUsuario(email);
	}

	/**
	 * Obtiene la id interna del sistema de persistencia de un usuario según su e-mail
	 * @param email Email del usuario
	 * @return Id del usuario
	 */
	public Integer getIdFromEmail(String email) {
		return UsuarioDAO.getIdDesdeEmail(email);
	}

	/**
	 * Inserta un usuario en el sistema de persistencia
	 * @param usuario Objeto UsuarioFullInfo relleno con toda la información del usuario
	 */
	public void setDatabaseUser(UsuarioFullInfo usuario) {
		UsuarioDAO.setUsuario(usuario);
	}

	/**
	 * Devuelve una lista con los datos de interés del administrador de los usuarios
	 * @return Lista de objetos UsuarioAdminInfo
	 */
	public List<UsuarioAdminInfo> getDatabaseUsersForAdmin() {
		return UsuarioDAO.getListaUsuariosParaAdministracion();
	}

	/**
	 * Genera un temporizador para que un nuevo usuario pueda validarse
	 * @param usuario UsuarioFullInfo con la información del usuario
	 * @param clave Clave token que deberá usar el usuario para validarse
	 */
	public void setNewUserTimer(UsuarioFullInfo usuario, String clave) {
		UsuarioDAO.setTemporizadorNuevoUsuario(usuario, clave);
	}

	/**
	 * Indica la existecia de un token de nuevo usuario
	 * @param clave Token de nuevo usuario a consultar
	 * @return Devuelve true si existe, en caso contrario devuelve false
	 */
	public boolean keyNewUserExists(String clave) {
		return UsuarioDAO.existeClaveUsuarioNuevo(clave);
	}

	/**
	 * Elimina un token de nuevo usuario
	 * @param clave Token de nuevo usuario a eliminar
	 */
	public void removeKeyNewUser(String clave) {
		UsuarioDAO.eliminarClaveUsuarioNuevo(clave);
	}

	/**
	 * Obtiene una lista de objetos PeticionNuevoUsuario
	 * @return Lista de objetosPeticionNuevoUsuario
	 */
	public List<PeticionNuevoUsuario> getNewUserPetitions() {
		return UsuarioDAO.getPeticionesNuevoUsuario();
	}

	/**
	 * Elimina un usuario del sistema de persistencia
	 * @param idUsuario Id del usuario
	 */
	public void removeDatabaseUser(Integer idUsuario) {
		UsuarioDAO.eliminarUsuario(idUsuario);
	}

	/**
	 * Devuelve una lista de usuarios validados
	 * @return Lista de UsuarioAdminInfo
	 */
	public List<UsuarioAdminInfo> getDatabaseValidatedUsers() {
		return UsuarioDAO.getUsuariosValidados();
	}

	/**
	 * Edita la información de un usuario en el sistema de permanencia
	 * @param usuario Objeto UsuarioFullInfo con toda la información a cambiar excepto el id, que servirá para identificar el usuario
	 */
	public void updateDatabaseUser(UsuarioFullInfo usuario) {
		UsuarioDAO.modificarUsuario(usuario);
	}
	
}
