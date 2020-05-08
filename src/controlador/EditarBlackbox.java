package controlador;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.ejb.BlackboxEJB;
import modelo.ejb.UsuariosEJB;
import modelo.pojo.BlackboxAdminInfo;
import modelo.pojo.UsuarioAdminInfo;
import modelo.pojo.UsuarioFullInfo;

/**
 * Servlet implementation class EditarBlackbox
 */
@WebServlet("/EditarBlackbox")
public class EditarBlackbox extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	UsuariosEJB usuariosEJB;
	
	@EJB
	BlackboxEJB blackboxEJB;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// A esta págian solo puede entrar un administrador
		UsuarioFullInfo usuario = usuariosEJB.getSessionUser(request);
		RequestDispatcher rs = null;
		if(usuario == null || !usuario.isAdministrador()) {
			rs = getServletContext().getRequestDispatcher("/aviso.jsp");
			request.setAttribute("titulo", "No tiene permiso");
			request.setAttribute("mensaje", "<p>No dispones permiso para acceder a esta sección.</p>" +
											"<p>Si tienes una cuenta de administrador <a href='Login'>inicia sesión</a> con ella.</a>. " +
											"Si no eres administrador regresa a la página principal haciendo <a href='Principal'>click aquí</a>.");
		} else {
			String idArg = request.getParameter("id");
			int id;
			if(idArg == null) {
				response.sendRedirect("ObtenerBlackboxes");
				return;
			}
			try {
				id = Integer.parseInt(idArg);
			} catch (NumberFormatException e) {
				response.sendRedirect("ObtenerBlackboxes");
				return;
			}
			
			rs = getServletContext().getRequestDispatcher("/editarBlackbox.jsp");
			// Obtiene una lista de todos los usuarios validados para a los cuales se les puede dar una blackbox
			List<UsuarioAdminInfo> listaUsuarios = usuariosEJB.getDatabaseValidatedUsers();
			// Obtiene los datos de la blackbox con ese id
			BlackboxAdminInfo blackbox = blackboxEJB.getBlackbox(id);
			request.setAttribute("listaUsuarios", listaUsuarios);
			request.setAttribute("blackbox", blackbox);
		}
		rs.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
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
			String idUnico = request.getParameter("idUnico");
			String passwd = request.getParameter("passwd");
			String idUsuarioArg = request.getParameter("usuario");
			int id;
			int idUsuario;
			
			// Falta algún dato
			if(idArg == null || idUnico == null || passwd == null || idUsuarioArg == null) {
				response.sendRedirect("ObtenerBlackboxes");
				return;
			}
			
			// Comprueba que idCliente es un número válido
			try {
				id = Integer.parseInt(idArg);
				idUsuario = Integer.parseInt(idUsuarioArg);
			} catch(NumberFormatException e) {
				e.printStackTrace();
				response.sendRedirect("ObtenerBlackboxes");
				return;
			}
			
			// Inserta una nueva blackbox
			blackboxEJB.editBlackbox(id, idUnico, passwd, idUsuario);
		}
	}

}
