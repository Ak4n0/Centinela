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
 * Obtiene los datos de todas las entradas de una blackbox para mostrarselas a su usuario
 */
@WebServlet("/ObtenerDatosBlackbox")
public class ObtenerDatosBlackbox extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	UsuariosEJB usuariosEJB;

	@EJB
	BlackboxEJB blackboxEJB;
	
	/**
	 * Método get
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsuarioFullInfo usuarioFullInfo = usuariosEJB.getSessionUser(request);
		RequestDispatcher rs = null;
		// A esta zona sólo puede accederse con sesión activa
		if(usuarioFullInfo == null) {
			rs = getServletContext().getRequestDispatcher("/aviso.jsp");
			request.setAttribute("titulo", "No tiene permiso");
			request.setAttribute("mensaje", "<p>No dispone permiso para acceder a esta sección.</p>" +
											"<p>Si tiene <a href='Login'>inicie sesión</a> con ella.</a>. " +
											"Si no tiene cuenta puede crearse una haciendo <a href='Signin'>click aquí</a>.");
		} else {
			// Para tomar los datos debe haberse seleccionado una blackbox
			String uid = request.getParameter("id");
			if(uid == null) {
				rs = getServletContext().getRequestDispatcher("/aviso.jsp");
				request.setAttribute("titulo", "Falta el identificador único");
				request.setAttribute("mensaje", "<p>No ha proporcionado el identificador de la blackbox, por tanto no pueden mostrarse sus datos.</p>" +
												"<p>Realice de nuevo la petición asegurándose de aportar el identificador único en ella.</p>");
				rs.forward(request, response);
				return;
			}
			
			rs = getServletContext().getRequestDispatcher("/vistaDatosBlackbox.jsp");
			BlackboxFullInfo blackbox = blackboxEJB.getBlackboxFullInfo(uid);
			// El uid de la blackbox pasado no existe
			if(blackbox == null) {
				rs = getServletContext().getRequestDispatcher("/aviso.jsp");
				request.setAttribute("titulo", "Fallo de identificación");
				request.setAttribute("mensaje", "<p>La Blackbox especificada no se encuentra en nuestra base de datos.</p>" +
												"<p>Vuelva a realizar la petición poniendo atención en que el identificador es correcto."
												+ "Si el identificador es correcto póngase en contacto con la administración de Centinela.</p>");
				rs.forward(request, response);
				return;
			}
			List<IOPort> io = blackboxEJB.getIOPorts(blackbox.getId());
			IOPort ultimaIO = blackboxEJB.getLastIO(blackbox.getId());
			request.setAttribute("blackbox", blackbox);
			request.setAttribute("io", io);
			request.setAttribute("ultimaIO", ultimaIO);
			
		}
		rs.forward(request, response);
	}

}
