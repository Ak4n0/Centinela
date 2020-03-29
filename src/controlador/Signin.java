package controlador;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.ejb.OperacionesUsuariosEJB;
import modelo.enumeracion.TipoError;
import modelo.pojo.Usuario;

/**
 * Servlet implementation class Signin
 */
@WebServlet("/Signin")
public class Signin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	OperacionesUsuariosEJB operacionesUsuariosEJB;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rs = getServletContext().getRequestDispatcher("/signin.jsp");
		rs.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
		String email = request.getParameter("email");
		String passwd = request.getParameter("passwd");
		
		// Control de parámetros
		RequestDispatcher rs = getServletContext().getRequestDispatcher("/signin.jsp");
		if(nombre == null || email == null || passwd == null) {
			request.setAttribute("error", TipoError.DATOS_INCOMPLETOS);
			rs.forward(request, response);
			return;
		}
		
		// No hay errores de parámetros
		// Comprobar si existe un usuario con el mismo email
		if(operacionesUsuariosEJB.existeUsuario(email)) {
			request.setAttribute("error", TipoError.CREDENCIALES);
			rs.forward(request, response);
			return;
		}
		
		// No existe un usuario con el mismo email, introducirlo en la BBDD
		Usuario usuario = new Usuario();
		usuario.setNombre(nombre);
		usuario.setEmail(email);
		usuario.setPasswd(passwd);
		usuario.setAdministrador(false);
		operacionesUsuariosEJB.setDatabaseUser(usuario);
		
		// Una vez que el usuario existe iniciar la sesión
		operacionesUsuariosEJB.setSessionUser(request, usuario);
		
		// Ir a la página principal
		response.sendRedirect("Principal");
	}

}
