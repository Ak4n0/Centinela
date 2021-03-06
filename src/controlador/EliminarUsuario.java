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
import modelo.pojo.UsuarioFullInfo;

/**
 * Elimina un usuario
 */
@WebServlet("/EliminarUsuario")
public class EliminarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private UsuariosEJB usuariosEJB;

	/**
	 * Método post
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// A esta págian solo puede entrar un administrador
		UsuarioFullInfo usuario = usuariosEJB.getSessionUser(request);
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
			
			// Si no está el dato de identificador del usuario, regresar
			if(idArg == null) {
				return;
			}
			
			// Si el id del usuario no es un número regresar
			try {
				id = Integer.parseInt(idArg);
			} catch(NumberFormatException e) {
				return;
			}
			
			// borra el usuario
			usuariosEJB.removeDatabaseUser(id);
		}
	}

}
