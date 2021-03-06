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

import modelo.ejb.UsuariosEJB;
import modelo.pojo.UsuarioAdminInfo;
import modelo.pojo.UsuarioFullInfo;

/**
 * Obtiene una lista de usuarios para el administrador
 */
@WebServlet("/ObtenerUsuarios")
public class ObtenerUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private UsuariosEJB usuariosEJB;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// A esta págian solo puede entrar un administrador
		UsuarioFullInfo usuario = usuariosEJB.getSessionUser(request);
		RequestDispatcher rs = null;
		// Sólo el administrador puede acceder a esta sección
		if(usuario == null || !usuario.isAdministrador()) {
			rs = getServletContext().getRequestDispatcher("/aviso.jsp");
			request.setAttribute("titulo", "No tiene permiso");
			request.setAttribute("mensaje", "<p>No dispone permiso para acceder a esta sección.</p>" +
											"<p>Si tienes una cuenta de administrador <a href='Login'>inicie sesión</a> con ella.</a>. " +
											"Si no es administrador regrese a la página principal haciendo <a href='Principal'>click aquí</a>.");
		} else {
			rs = getServletContext().getRequestDispatcher("/vistaUsuarios.jsp");
			List<UsuarioAdminInfo> listaUsuarios = usuariosEJB.getDatabaseUsersForAdmin();
			request.setAttribute("listaUsuarios", listaUsuarios);
		}
		rs.forward(request, response);
	}

}
