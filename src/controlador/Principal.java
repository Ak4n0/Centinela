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
import modelo.pojo.UsuarioFullInfo;

/**
 * Servlet implementation class Principal
 */
@WebServlet("/Principal")
public class Principal extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	OperacionesUsuariosEJB controlUsuarioEJB;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsuarioFullInfo usuarioFullInfo = controlUsuarioEJB.getSessionUser(request);
		RequestDispatcher rs;
		
		// Comprobar si el usuario tiene una cuenta
		if(usuarioFullInfo != null) {
			// Si el usuario es administrador mandarlo su apartado
			if(usuarioFullInfo.isAdministrador()) {
				rs = getServletContext().getRequestDispatcher("/administracion.jsp");
			} else { // el usuario no es administrador, enviarlo a su dashboard
				rs = getServletContext().getRequestDispatcher("/dashboard.jsp");	
			}
		} else { // El usuario no ha iniciado sesión. Mostrar la página genérica.
			rs = getServletContext().getRequestDispatcher("/index.jsp");
		}
		rs.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
