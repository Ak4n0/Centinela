package controlador;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.ejb.UsuariosEJB;

/**
 * Servlet implementation class ValidarNuevoUsuario
 */
@WebServlet("/ValidarNuevoUsuario")
public class ValidarNuevoUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	UsuariosEJB usuariosEJB;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// obtien la clave desde el e-mail
		String clave = request.getParameter("v");
		
		RequestDispatcher rs;
		// si no existe la clave se informa al usuario de la situación
		if(clave == null) {
			rs = getServletContext().getRequestDispatcher("/aviso.jsp");
			request.setAttribute("titulo", "Error de validación");
			request.setAttribute("mensaje", "<p>No hay ninguna petición de validación asociada para esta petición.</p>");
			rs.forward(request, response);
			return;
		}
		
		// comprobar que existe dicha clave en la base de datos
		if(!usuariosEJB.keyNewUserExists(clave)) {
			rs = getServletContext().getRequestDispatcher("/aviso.jsp");
			request.setAttribute("titulo", "Petición no reconocida");
			request.setAttribute("mensaje", "<p>No existe ninguna petición de nuevo usuario correspondiente a dicha clave.</p>");
			rs.forward(request, response);
			return;
		}
		
		// existe, luego se borra
		usuariosEJB.removeKeyNewUser(clave);
		
		// informar al usuario que está correctamente registrado
		rs = getServletContext().getRequestDispatcher("/aviso.jsp");
		request.setAttribute("titulo", "Registro completo");
		request.setAttribute("mensaje", "<p>Has validado completamente tu cuenta. A partir de este momento puedes ponerte en contacto con el servicio de ventas para adquirir el hardware Blackbox.</p>");
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
