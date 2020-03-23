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
import modelo.ejb.EmailEJB;
import modelo.enumeracion.TipoError;

/**
 * Servlet implementation class PasswordOlvidado
 */
@WebServlet("/PasswordOlvidado")
public class PasswordOlvidado extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	ControlUsuariosEJB controlusUsuariosEJB;
	
	@EJB
	EmailEJB emailEJB;
	
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
		
	}

}
