package controlador;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.ejb.ControlUsuariosEJB;
import modelo.pojo.Usuario;

/**
 * Servlet implementation class Principal
 */
@WebServlet("/Principal")
public class Principal extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	ControlUsuariosEJB controlUsuarioEJB;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = controlUsuarioEJB.getSessionUser(request);
		
		// Si el usuario no está logeado mandarlo al servicio de login
		if(usuario == null) {
			response.sendRedirect("Login");
			return;
		}
		
		// TODO: continuar con la página índice
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/**
	 * Método que devuelve el nombre de usuario si está logeado
	 * 
	 * @param request request del servlet
	 * @return El nombre de usuario si se ha logeado o null si no.
	 */
	private String getLoggedUser(HttpServletRequest request) {
		String user = null;

		// Comprobamos si tenemos una sesión y obtenemos su nombre de usuario.
		HttpSession session = request.getSession(false);

		if (session != null) {
			user = (String) session.getAttribute("usuario");
		}

		return user;
	}

}
