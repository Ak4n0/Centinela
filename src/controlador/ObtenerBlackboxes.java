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

import modelo.ejb.OperacionesBlackboxesEJB;
import modelo.ejb.OperacionesUsuariosEJB;
import modelo.pojo.BlackboxAdminInfo;
import modelo.pojo.UsuarioFullInfo;

/**
 * Servlet implementation class ObtenerBlackboxes
 */
@WebServlet("/ObtenerBlackboxes")
public class ObtenerBlackboxes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	OperacionesUsuariosEJB operacionesUsuariosEJB;
	
	@EJB
	OperacionesBlackboxesEJB operacionesBlackboxesEJB;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsuarioFullInfo usuarioFullInfo = operacionesUsuariosEJB.getSessionUser(request);
		RequestDispatcher rs = null;
		if(usuarioFullInfo == null || !usuarioFullInfo.isAdministrador()) {
			rs = getServletContext().getRequestDispatcher("/aviso.jsp");
			request.setAttribute("titulo", "No tiene permiso");
			request.setAttribute("mensaje", "<p>No dispones permiso para acceder a esta sección.</p>" +
											"<p>Si tienes una cuenta de administrador <a href='Login'>inicia sesión</a> con ella.</a>. " +
											"Si no eres administrador regresa a la página principal haciendo <a href='Principal'>click aquí</a>.");
		} else {
			rs = getServletContext().getRequestDispatcher("/vistaBlackboxes.jsp");
			List<BlackboxAdminInfo> listaBlackboxes = operacionesBlackboxesEJB.getDatabaseBlackboxes();
			request.setAttribute("listaBlackboxes", listaBlackboxes);
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
