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
import modelo.pojo.UsuarioAdminInfo;
import modelo.pojo.UsuarioFullInfo;

/**
 * Servlet implementation class AddBlackbox
 */
@WebServlet("/AddBlackbox")
public class AddBlackbox extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	OperacionesUsuariosEJB operacionesUsuariosEJB;
	
	@EJB
	OperacionesBlackboxesEJB operacionesBlackboxesEJB;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// A esta págian solo puede entrar un administrador
		UsuarioFullInfo usuario = operacionesUsuariosEJB.getSessionUser(request);
		RequestDispatcher rs = null;
		if(usuario == null || !usuario.isAdministrador()) {
			rs = getServletContext().getRequestDispatcher("/aviso.jsp");
			request.setAttribute("titulo", "No tiene permiso");
			request.setAttribute("mensaje", "<p>No dispones permiso para acceder a esta sección.</p>" +
											"<p>Si tienes una cuenta de administrador <a href='Login'>inicia sesión</a> con ella.</a>. " +
											"Si no eres administrador regresa a la página principal haciendo <a href='Principal'>click aquí</a>.");
		} else {
			rs = getServletContext().getRequestDispatcher("/addBlackbox.jsp");
			// Obtiene una lista de todos los usuarios validados para a los cuales se les puede dar una blackbox
			List<UsuarioAdminInfo> listaUsuarios = operacionesUsuariosEJB.getDatabaseValidatedUsers();
			request.setAttribute("listaUsuarios", listaUsuarios);
		}
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
			return;
		} else {
			String id = request.getParameter("id");
			String passwd = request.getParameter("passwd");
			String idUsuarioArg = request.getParameter("usuario");
			int idUsuario;
			
			// Falta algún dato
			if(id == null || passwd == null || idUsuarioArg == null) {
				return;
			}
			
			// Comprueba que idCliente es un número válido
			try {
				idUsuario = Integer.parseInt(idUsuarioArg);
			} catch(NumberFormatException e) {
				return;
			}
			
			// Inserta una nueva blackbox
			operacionesBlackboxesEJB.addBlackbox(id, passwd, idUsuario);
		}	
	}

}
