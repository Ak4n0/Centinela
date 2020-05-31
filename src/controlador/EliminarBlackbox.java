package controlador;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.ejb.BlackboxEJB;
import modelo.ejb.UsuariosEJB;
import modelo.pojo.UsuarioFullInfo;

/**
 * Borra una blackbox de la DDBB
 */
@WebServlet("/EliminarBlackbox")
public class EliminarBlackbox extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private BlackboxEJB blackboxEJB;
	
	@EJB
	private UsuariosEJB usuariosEJB;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// A esta págian solo puede entrar un administrador
		UsuarioFullInfo usuario = usuariosEJB.getSessionUser(request);
		RequestDispatcher rs = null;
		// Sólo tiene permiso para borrar un administrador
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
			
			// Falta la id, por lo tanto no hacer nada
			if(idArg == null) {
				return;
			}
			
			// Si la id no es un número regresar sin hacer nada.
			try {
				id = Integer.parseInt(idArg);
			} catch(NumberFormatException e) {
				return;
			}
			
			// Borra la blackbox
			blackboxEJB.deleteBlackbox(id);
		}	
	}

}
