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
import modelo.ejb.EmailEJB;
import modelo.ejb.OperacionesTokensNuevoPasswordEJB;
import modelo.enumeracion.TipoError;
import modelo.pojo.TokenNuevoPassword;

/**
 * Servlet implementation class PasswordOlvidado
 */
@WebServlet("/PasswordOlvidado")
public class PasswordOlvidado extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	OperacionesUsuariosEJB operacionesUsuariosEJB;
	
	@EJB
	EmailEJB emailEJB;
	
	@EJB
	OperacionesTokensNuevoPasswordEJB operacionesTokensNuevoPasswordEJB;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rs = getServletContext().getRequestDispatcher("/password-olvidado.jsp");
		rs.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		
		// Control de parámetros
		RequestDispatcher rs = getServletContext().getRequestDispatcher("/password-olvidado.jsp");
		if(email == null) {
			request.setAttribute("error", TipoError.DATOS_INCOMPLETOS);
			rs.forward(request, response);
			return;
		}
		
		if(!operacionesUsuariosEJB.existeUsuario(email)) {
			request.setAttribute("error", TipoError.CREDENCIALES);
			rs.forward(request, response);
			return;
		}
		
		// El usuario ha introducido correctamente su email
		// Ahora hay que enviar un mensaje para que pueda introducir una nueva contraseña
		// Obtener el nombre del usuario
		String nombre = operacionesUsuariosEJB.getUserName(email);
		
		// Obtener el identificador para cambiar la clave
		// Comprobar si el usuario ya tiene un identificador
		TokenNuevoPassword tokenNuevoPassowrd = operacionesTokensNuevoPasswordEJB.getTokenFromEmail(email);
		
		
	}

}
