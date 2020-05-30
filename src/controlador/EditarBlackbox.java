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
import modelo.pojo.BlackboxFullInfo;
import modelo.pojo.UsuarioAdminInfo;
import modelo.pojo.UsuarioFullInfo;

/**
 * Servlet que actualiza una blackbox en la BBDD
 */
@WebServlet("/EditarBlackbox")
public class EditarBlackbox extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = (Logger) LoggerFactory.getLogger(EditarBlackbox.class);
	
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
			String uid = request.getParameter("uid");
			if(uid == null) {
				response.sendRedirect("ObtenerBlackboxes");
				return;
			}
			
			rs = getServletContext().getRequestDispatcher("/editarBlackbox.jsp");
			// Obtiene una lista de todos los usuarios validados para a los cuales se les puede dar una blackbox
			List<UsuarioAdminInfo> listaUsuarios = usuariosEJB.getDatabaseValidatedUsers();
			// Obtiene los datos de la blackbox con ese id
			BlackboxFullInfo blackbox = blackboxEJB.getBlackboxFullInfo(uid);
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
											"<p>Si tienes una cuenta de administrador <a href='Login'>inicia sesión</a> con ella.</p>" +
											"<p>Si no eres administrador regresa a la página principal haciendo <a href='Principal'>click aquí</a>.</p>");
			logger.debug("Usuario no válido");
			rs.forward(request, response);
			return;
		} else {
			String idArg = request.getParameter("id");
			String idUnico = request.getParameter("idUnico");
			String passwd = request.getParameter("passwd");
			String idUsuarioArg = request.getParameter("usuario");
			String unidadesI0 = request.getParameter("unidades_i0");
			String funcTransI0 = request.getParameter("func_trans_i0");
			String funcTransInvI0 = request.getParameter("func_trans_inv_i0");
			String unidadesI1 = request.getParameter("unidades_i1");
			String funcTransI1 = request.getParameter("func_trans_i1");
			String funcTransInvI1 = request.getParameter("func_trans_inv_i1");
			String unidadesI2 = request.getParameter("unidades_i2");
			String funcTransI2 = request.getParameter("func_trans_i2");
			String funcTransInvI2 = request.getParameter("func_trans_inv_i2");
			String unidadesI3 = request.getParameter("unidades_i3");
			String funcTransI3 = request.getParameter("func_trans_i3");
			String funcTransInvI3 = request.getParameter("func_trans_inv_i3");
			int id;
			int idUsuario;
			
			// Falta algún dato
			if(idArg == null || idUnico == null || passwd == null || idUsuarioArg == null ||
					unidadesI0 == null || unidadesI1 == null || unidadesI2 == null || unidadesI3 == null ||
					funcTransI0 == null || funcTransI1 == null || funcTransI2 == null || funcTransI3 == null ||
					funcTransInvI0 == null || funcTransInvI1 == null || funcTransInvI2 == null || funcTransInvI3 == null) {
				logger.debug("Faltan argumentos. Parámetros desde la web:");
				for(String param: request.getParameterMap().keySet()) {
					logger.debug(param + " : " + request.getParameterMap().get(param)[0]);
				}
				response.sendRedirect("ObtenerBlackboxes");
				return;
			}
			
			// Comprueba que el id blackbox y el id de usuario són números
			try {
				id = Integer.parseInt(idArg);
				idUsuario = Integer.parseInt(idUsuarioArg);
			} catch(NumberFormatException e) {
				logger.error("No se pudieron transformar cadenas a números: id o idUsuario");
				System.out.println("Editar Blackbox: no se pudieron transformar id o idUsuario");
				response.sendRedirect("ObtenerBlackboxes");
				return;
			}
			
			// Inserta una nueva blackbox
			BlackboxFullInfo blackbox = blackboxEJB.getBlackboxFullInfo(idUnico);
			blackbox.setId(id);
			blackbox.setIdentificador(idUnico);
			blackbox.setPasswd(passwd);
			blackbox.setIdUsuario(idUsuario);
			blackbox.setUnidades_I0(unidadesI0);
			blackbox.setUnidades_I1(unidadesI1);
			blackbox.setUnidades_I2(unidadesI2);
			blackbox.setUnidades_I3(unidadesI3);
			blackbox.setFuncionTransferencia_I0(funcTransI0);
			blackbox.setFuncionTransferencia_I1(funcTransI1);
			blackbox.setFuncionTransferencia_I2(funcTransI2);
			blackbox.setFuncionTransferencia_I3(funcTransI3);
			blackbox.setFuncionTransferenciaInversa_I0(funcTransInvI0);
			blackbox.setFuncionTransferenciaInversa_I1(funcTransInvI1);
			blackbox.setFuncionTransferenciaInversa_I2(funcTransInvI2);
			blackbox.setFuncionTransferenciaInversa_I3(funcTransInvI3);
			// Inserta una nueva blackbox
			blackboxEJB.editBlackbox(blackbox);
		}
	}

}
