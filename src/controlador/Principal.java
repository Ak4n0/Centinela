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

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import modelo.ejb.BlackboxEJB;
import modelo.ejb.UsuariosEJB;
import modelo.pojo.BlackboxAdminInfo;
import modelo.pojo.UsuarioFullInfo;

/**
 * Clase Principal, la que debe llamarse cuándo se conecta un usuario
 */
@WebServlet("/Principal")
public class Principal extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = (Logger) LoggerFactory.getLogger(Principal.class);
	
	@EJB
	private UsuariosEJB controlUsuarioEJB;
	
	@EJB
	private BlackboxEJB blackboxEJB;
	
	/**
	 * Método get
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsuarioFullInfo usuarioFullInfo = controlUsuarioEJB.getSessionUser(request);
		RequestDispatcher rs;
		
		logger.warn("Existe usuario = " + (usuarioFullInfo != null));
		
		// Comprobar si el usuario tiene una cuenta
		if(usuarioFullInfo != null) {
			// Si el usuario es administrador mandarlo su apartado
			if(usuarioFullInfo.isAdministrador()) {
				rs = getServletContext().getRequestDispatcher("/administracion.jsp");
			} else { // el usuario no es administrador, enviarlo a su dashboard
				rs = getServletContext().getRequestDispatcher("/dashboard.jsp");
				List<BlackboxAdminInfo> listaBlackboxes = blackboxEJB.getDatabaseBlackboxes();
				request.setAttribute("listaBlackboxes", listaBlackboxes);
			}
		} else { // El usuario no ha iniciado sesión. Mostrar la página genérica.
			rs = getServletContext().getRequestDispatcher("/index.jsp");
		}
		rs.forward(request, response);
	}

}
