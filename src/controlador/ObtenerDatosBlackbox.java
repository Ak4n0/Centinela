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
import modelo.pojo.BlackboxFullInfo;
import modelo.pojo.IOPort;
import modelo.pojo.UsuarioFullInfo;

/**
 * Servlet implementation class ObtenerDatosBlackbox
 */
@WebServlet("/ObtenerDatosBlackbox")
public class ObtenerDatosBlackbox extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	UsuariosEJB usuariosEJB;

	@EJB
	BlackboxEJB blackboxEJB;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsuarioFullInfo usuarioFullInfo = usuariosEJB.getSessionUser(request);
		RequestDispatcher rs = null;
		if(usuarioFullInfo == null) {
			rs = getServletContext().getRequestDispatcher("/aviso.jsp");
			request.setAttribute("titulo", "No tiene permiso");
			request.setAttribute("mensaje", "<p>No dispones permiso para acceder a esta sección.</p>" +
											"<p>Si tienes una cuenta de administrador <a href='Login'>inicia sesión</a> con ella.</a>. " +
											"Si no eres administrador regresa a la página principal haciendo <a href='Principal'>click aquí</a>.");
		} else {
			String uid = request.getParameter("id");
			if(uid == null) {
				rs = getServletContext().getRequestDispatcher("/aviso.jsp");
				request.setAttribute("titulo", "Falta el identificador único");
				request.setAttribute("mensaje", "<p>No has proporcionado el identificador de la blackbox, por tanto no pueden mostrarse sus datos.</p>" +
												"<p>Realiza de nuevo la petición asegurándote de aportar el identificador único en ella.</p>");
				rs.forward(request, response);
				return;
			}
			
			rs = getServletContext().getRequestDispatcher("/vistaDatosBlackbox.jsp");
			BlackboxFullInfo blackbox = blackboxEJB.getBlackboxFullInfo(uid);
			if(blackbox == null) {
				rs = getServletContext().getRequestDispatcher("/aviso.jsp");
				request.setAttribute("titulo", "Fallo de identificación");
				request.setAttribute("mensaje", "<p>La Blackbox expecificada no se encuentra en nuestra base de datos.</p>" +
												"<p>Vuelve a realizar la petición poniendo atención en que el identificador es correcto."
												+ "Si el identificador es correcto ponte en contacto con la administración de Centinela.</p>");
				rs.forward(request, response);
				return;
			}
			List<IOPort> io = blackboxEJB.getIOPorts(blackbox.getId());
			request.setAttribute("blackbox", blackbox);
			request.setAttribute("io", io);
			
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
