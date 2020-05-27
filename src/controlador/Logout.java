package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Cierra la sesión de un usuario
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Método get
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Elimina la sesión del usuario
		HttpSession session = request.getSession(false);
		
		// Sólo se cierra una sesión si existe
		if(session != null) {
			session.invalidate();
		}
		
		// Redirigimos a la página principal
		response.sendRedirect("Principal");
	}

}
