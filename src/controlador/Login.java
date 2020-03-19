package controlador;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.ejb.ControlUsuariosEJB;
import modelo.pojo.Usuario;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	ControlUsuariosEJB controlUsuariosEJB;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rs = getServletContext().getRequestDispatcher("/login.jsp");
		rs.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String passwd = request.getParameter("passwd");
		
		// Control de parámetros
		RequestDispatcher rs = getServletContext().getRequestDispatcher("/login.jsp");
		if(email == null) {
			request.setAttribute("error", "email");
			rs.forward(request, response);
			return;
		}
		
		if(passwd == null) {
			request.setAttribute("error", "passwd");
			rs.forward(request, response);
			return;
		}
		
		// No hay errores de parámetros
		// Intenta conseguir el usuario desde la BBDD
		Usuario usuarioRegistrado = controlUsuariosEJB.getDatabaseUser(email, passwd);
		
		// El usuario no existe
		if(usuarioRegistrado == null) {
			request.setAttribute("error", "credenciales");
			rs.forward(request, response);
			return;
		}
		
		// Si existe guardarlo en la sesión con el objeto Usuario
		controlUsuariosEJB.setSessionUser(request, usuarioRegistrado);
		
		// Regresar a la página principal
		response.sendRedirect("Principal");
	}

}
