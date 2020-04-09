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

import modelo.ejb.OperacionesUsuariosEJB;
import modelo.pojo.UsuarioFullInfo;

/**
 * Servlet implementation class EditarUsuario
 */
@WebServlet("/EditarUsuario")
public class EditarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	OperacionesUsuariosEJB operacionesUsuariosEJB;
	
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
		
		UsuarioFullInfo usuario = operacionesUsuariosEJB.getDatabaseUser(id);
		RequestDispatcher rs = getServletContext().getRequestDispatcher("/editarUsuario.jsp");
		request.setAttribute("usuario", usuario);
		rs.forward(request, response);
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
			
			//operacionesBlackboxesEJB.editBlackbox(id, idUnico, passwd, idUsuario);
		}
	}

}
