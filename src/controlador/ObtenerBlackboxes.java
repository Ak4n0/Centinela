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
import modelo.pojo.UsuarioFullInfo;

/**
 * Obtiene las blackboxes existentes para mostrarselas al administrador
 */
@WebServlet("/ObtenerBlackboxes")
public class ObtenerBlackboxes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private UsuariosEJB usuariosEJB;
	
	@EJB
	private BlackboxEJB blackboxEJB;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsuarioFullInfo usuarioFullInfo = usuariosEJB.getSessionUser(request);
		RequestDispatcher rs = null;
		// El usuario debe ser el único que pueda acceder a esta página
		if(usuarioFullInfo == null || !usuarioFullInfo.isAdministrador()) {
			rs = getServletContext().getRequestDispatcher("/aviso.jsp");
			request.setAttribute("titulo", "No tiene permiso");
			request.setAttribute("mensaje", "<p>No dispones permiso para acceder a esta sección.</p>" +
											"<p>Si tienes una cuenta de administrador <a href='Login'>inicia sesión</a> con ella.</a>. " +
											"Si no eres administrador regresa a la página principal haciendo <a href='Principal'>click aquí</a>.");
		} else {
			// Muestra la lista al administrador
			rs = getServletContext().getRequestDispatcher("/vistaBlackboxes.jsp");
			List<BlackboxAdminInfo> listaBlackboxes = blackboxEJB.getDatabaseBlackboxes();
			request.setAttribute("listaBlackboxes", listaBlackboxes);
		}
		rs.forward(request, response);
	}

}
