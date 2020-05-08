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
 * Servlet implementation class EditarUsuario
 */
@WebServlet("/EditarUsuario")
public class EditarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	UsuariosEJB usuariosEJB;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idArg = request.getParameter("id");
		int id;
		if(idArg == null) {
			response.sendRedirect("ObtenerUsuarios");
			return;
		}
		try {
			id = Integer.parseInt(idArg);
		} catch (NumberFormatException e) {
			response.sendRedirect("ObtenerUsuarios");
			return;
		}
		
		UsuarioFullInfo usuario = usuariosEJB.getDatabaseUser(id);
		RequestDispatcher rs = getServletContext().getRequestDispatcher("/editarUsuario.jsp");
		request.setAttribute("usuario", usuario);
		rs.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rs = null;
		String idArg = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		String email = request.getParameter("email");
		String passwd = request.getParameter("passwd");
		int id;
		
		// Falta algún dato
		if(idArg == null || nombre == null || email == null || passwd == null) {
			response.sendRedirect("ObtenerUsuarios");
			return;
		}
		
		// Comprueba que idCliente es un número válido
		try {
			id = Integer.parseInt(idArg);
		} catch(NumberFormatException e) {
			// TODO: usar el logger para mostrar el error
			e.printStackTrace();
			response.sendRedirect("ObtenerUsuarios");
			return;
		}
		
		UsuarioFullInfo usuarioSesion = usuariosEJB.getSessionUser(request);
		UsuarioFullInfo usuario = new UsuarioFullInfo();
		usuario.setId(id);
		usuario.setNombre(nombre);
		usuario.setEmail(email);
		usuario.setPasswd(passwd);
		usuario.setAdministrador(false);
	
		if(usuarioSesion == null || !usuarioSesion.isAdministrador() || usuarioSesion.getId() != usuario.getId()) {
			rs = getServletContext().getRequestDispatcher("/aviso.jsp");
			request.setAttribute("titulo", "No tiene permiso");
			request.setAttribute("mensaje", "<p>No dispones permiso para acceder a esta sección.</p>" +
											"<p>Si tienes una cuenta de administrador <a href='Login'>inicia sesión</a> con ella.</a>. " +
											"Si no eres administrador regresa a la página principal haciendo <a href='Principal'>click aquí</a>.");
			rs.forward(request, response);
		} else {
			usuariosEJB.updateDatabaseUser(usuario);
		}
	}

}
