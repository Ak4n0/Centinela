package modelo.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import modelo.dao.UsuarioDAO;
import modelo.pojo.Usuario;

@LocalBean
@Stateless
public class OperacionesUsuariosEJB {

	public Usuario getSessionUser(HttpServletRequest request) {
		Usuario user = null;
		
		HttpSession session = request.getSession(false);
		if(session != null) {
			user = (Usuario) session.getAttribute("usuario");
		}
		
		return user;
	}
	
	public Usuario getDatabaseUser(String email, String passwd) {
		Usuario usuarioResult = null;
		
		Usuario usuarioArgumento = new Usuario();
		usuarioArgumento.setEmail(email);
		usuarioArgumento.setPasswd(passwd);
		
		usuarioResult = UsuarioDAO.getUsuario(usuarioArgumento);
		
		return usuarioResult;
	}
	
	public boolean existeUsuario(String email) {
		return UsuarioDAO.existeUsuario(email);
	}

	public void setSessionUser(HttpServletRequest request, Usuario usuarioRegistrado) {
		request.getSession().setAttribute("usuario", usuarioRegistrado);
	}

	public String getUserName(String email) {
		return UsuarioDAO.getNombreUsuario(email);
	}

	public Integer getIdFromEmail(String email) {
		return UsuarioDAO.getIdDesdeEmail(email);
	}
	
}
