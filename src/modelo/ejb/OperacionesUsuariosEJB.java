package modelo.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import modelo.dao.UsuarioDAO;
import modelo.pojo.UsuarioAdminInfo;
import modelo.pojo.UsuarioFullInfo;

@LocalBean
@Stateless
public class OperacionesUsuariosEJB {

	public UsuarioFullInfo getSessionUser(HttpServletRequest request) {
		UsuarioFullInfo user = null;
		
		HttpSession session = request.getSession(false);
		if(session != null) {
			user = (UsuarioFullInfo) session.getAttribute("usuario");
		}
		
		return user;
	}
	
	public UsuarioFullInfo getDatabaseUser(String email, String passwd) {
		UsuarioFullInfo usuarioResult = null;
		
		UsuarioFullInfo usuarioArgumento = new UsuarioFullInfo();
		usuarioArgumento.setEmail(email);
		usuarioArgumento.setPasswd(passwd);
		
		usuarioResult = UsuarioDAO.getUsuario(usuarioArgumento);
		
		return usuarioResult;
	}
	
	public boolean existeUsuario(String email) {
		return UsuarioDAO.existeUsuario(email);
	}

	public void setSessionUser(HttpServletRequest request, UsuarioFullInfo usuarioFullInfo) {
		request.getSession().setAttribute("usuario", usuarioFullInfo);
	}

	public String getUserName(String email) {
		return UsuarioDAO.getNombreUsuario(email);
	}

	public Integer getIdFromEmail(String email) {
		return UsuarioDAO.getIdDesdeEmail(email);
	}

	public void setDatabaseUser(UsuarioFullInfo usuarioFullInfo) {
		UsuarioDAO.setUsuario(usuarioFullInfo);
	}

	public List<UsuarioAdminInfo> getDatabaseUsersForAdmin() {
		return UsuarioDAO.getListaUsuariosParaAdministracion();
	}
	
}
