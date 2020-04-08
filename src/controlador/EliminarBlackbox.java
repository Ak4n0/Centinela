package controlador;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.ejb.OperacionesBlackboxesEJB;
import modelo.ejb.OperacionesUsuariosEJB;
import modelo.pojo.UsuarioFullInfo;

/**
 * Servlet implementation class EliminarBlackbox
 */
@WebServlet("/EliminarBlackbox")
public class EliminarBlackbox extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	OperacionesBlackboxesEJB operacionesBlackboxesEJB;
	
	@EJB
	OperacionesUsuariosEJB operacionesUsuariosEJB;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// A esta págian solo puede entrar un administrador
		UsuarioFullInfo usuario = operacionesUsuariosEJB.getSessionUser(request);
		RequestDispatcher rs = null;
		if(usuario == null || !usuario.isAdministrador()) {
			rs = getServletContext().getRequestDispatcher("/aviso.jsp");
			request.setAttribute("titulo", "No tiene permiso");
			request.setAttribute("mensaje", "<p>No dispones permiso para acceder a esta sección.</p>" +
											"<p>Si tienes una cuenta de administrador <a href='Login'>inicia sesión</a> con ella.</a>. " +
											"Si no eres administrador regresa a la página principal haciendo <a href='Principal'>click aquí</a>.");
			rs.forward(request, response);
			return;
		} else {
			String idArg = request.getParameter("id");
			int id;
			
			// Falta algún dato
			if(idArg == null) {
				return;
			}
			
			// Comprueba que idCliente es un número válido
			try {
				id = Integer.parseInt(idArg);
			} catch(NumberFormatException e) {
				return;
			}
			
			// Inserta una nueva blackbox
			operacionesBlackboxesEJB.deleteBlackbox(id);
		}	
	}

}
