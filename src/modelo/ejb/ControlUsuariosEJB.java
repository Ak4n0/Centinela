package modelo.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import modelo.pojo.Usuario;

@LocalBean
@Stateless
public class ControlUsuariosEJB {

	public Usuario getSessionUser(HttpServletRequest request) {
		Usuario user = null;
		
		HttpSession session = request.getSession(false);
		if(session != null) {
			user = (Usuario) session.getAttribute("usuario");
		}
		
		return user;
	}
}
