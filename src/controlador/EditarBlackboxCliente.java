package controlador;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import modelo.ejb.BlackboxBufferEJB;
import modelo.ejb.BlackboxEJB;
import modelo.ejb.UsuariosEJB;
import modelo.pojo.BlackboxBuffer;
import modelo.pojo.BlackboxFullInfo;
import modelo.pojo.UsuarioFullInfo;

/**
 * Servlet implementation class EditarBlackbox
 */
@WebServlet("/EditarBlackboxCliente")
public class EditarBlackboxCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = (Logger) LoggerFactory.getLogger(EditarBlackboxCliente.class);
	
	@EJB
	UsuariosEJB usuariosEJB;
	
	@EJB
	BlackboxEJB blackboxEJB;
	
	@EJB
	BlackboxBufferEJB bufferEJB;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// A esta págian solo puede entrar un administrador
		UsuarioFullInfo usuario = usuariosEJB.getSessionUser(request);
		RequestDispatcher rs = null;
		if(usuario == null) {
			rs = getServletContext().getRequestDispatcher("/aviso.jsp");
			request.setAttribute("titulo", "No tiene permiso");
			request.setAttribute("mensaje", "<p>No dispones permiso para acceder a esta sección.</p>" +
					"<p>Debes ser propietario de esta blackbox para poder editarla. <a href='Login'>Inicia sesión</a> con su cuenta de usuario.</p>. " +
					"<p>Si no el propietario de esta blackbox regresa a la página haciendo <a href='Principal'>click aquí</a>.</p>");
			logger.warn("No hay sesión de usuario");;
		} else {
			String uid = request.getParameter("uid");
			if(uid == null) {
				response.sendRedirect("Principal");
				logger.warn("No se ha pasado el uid de la blackbox en los argumentos.");
			}
			
			rs = getServletContext().getRequestDispatcher("/editarBlackboxCliente.jsp");
			// Obtiene los datos de la blackbox con ese id
			BlackboxFullInfo blackbox = blackboxEJB.getBlackboxFullInfo(uid);
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
		if(usuario == null) {
			rs = getServletContext().getRequestDispatcher("/aviso.jsp");
			request.setAttribute("titulo", "No tiene permiso");
			request.setAttribute("mensaje", "<p>No dispones permiso para acceder a esta sección.</p>" +
					"<p>Debes ser propietario de esta blackbox para poder editarla. <a href='Login'>Inicia sesión</a> con su cuenta de usuario.</p>. " +
					"<p>Si no el propietario de esta blackbox regresa a la página haciendo <a href='Principal'>click aquí</a>.</p>");
			rs.forward(request, response);
			return;
		} else {
			String uid = request.getParameter("uid");
			String nombre = request.getParameter("nombre");
			String descr = request.getParameter("descr");
			String I0 = request.getParameter("I0");
			String I1 = request.getParameter("I1");
			String I2 = request.getParameter("I2");
			String I3 = request.getParameter("I3");
			String O0 = request.getParameter("O0");
			String O1 = request.getParameter("O1");
			String O2 = request.getParameter("O2");
			String O3 = request.getParameter("O3");
			String I0supArg = request.getParameter("I0sup");
			String I0infArg = request.getParameter("I0inf");
			String I1supArg = request.getParameter("I1sup");
			String I1infArg = request.getParameter("I1inf");
			String I2supArg = request.getParameter("I2sup");
			String I2infArg = request.getParameter("I2inf");
			String I3supArg = request.getParameter("I3sup");
			String I3infArg = request.getParameter("I3inf");
			
			// No se ha pasado un uid válido
			if(uid == null) {
				rs = getServletContext().getRequestDispatcher("/aviso.jsp");
				request.setAttribute("titulo", "Blackbox desconocida");
				request.setAttribute("mensaje", "<p>No se reconoce la blackbox a la que se ha referido.</p>" +
						"<p>Debe revisar que el identificador de la blackbox que proporciona es realmente válido e identifica a una blackbox de su propiedad</p>" +
						"<p>Haga <a href='Principal'>click aquí</a> para regresar a la págian principal.</p>");
				rs.forward(request, response);
				return;
			}

			BlackboxFullInfo blackbox = blackboxEJB.getBlackboxFullInfo(uid);
			
			// La blackbox no pertenece al usuario
			if(blackbox.getIdUsuario() != usuario.getId()) {
				rs = getServletContext().getRequestDispatcher("/aviso.jsp");
				request.setAttribute("titulo", "No tiene permiso para editar esta blackbox");
				request.setAttribute("mensaje", "<p>La blackbox que ha seleccionado no le pertenece.</p>" +
						"<p>Debe revisar que el identificador de la blackbox que proporciona es realmente válido e identifica a una blackbox de su propiedad</p>" +
						"<p>Haga <a href='Principal'>click aquí</a> para regresar a la págian principal.</p>");
				rs.forward(request, response);
				return;
			}
			
			Integer I0sup = null;
			Integer I0inf = null;
			Integer I1sup = null;
			Integer I1inf = null;
			Integer I2sup = null;
			Integer I2inf = null;
			Integer I3sup = null;
			Integer I3inf = null;
			
			try {
				I0sup = Integer.parseInt(I0supArg);
			} catch (NumberFormatException e) {
				// No hacer nada, queda como null
			}
			
			try {
				I0inf = Integer.parseInt(I0infArg);
			} catch (NumberFormatException e) {
				// No hacer nada, queda como null
			}
			
			try {
				I1sup = Integer.parseInt(I1supArg);
			}catch (NumberFormatException e) {
				// No hacer nada, queda como null
			}
			
			try {
				I1inf = Integer.parseInt(I1infArg);
			} catch (NumberFormatException e) {
				// No hacer nada, queda como null
			}
				
			try {
				I2sup = Integer.parseInt(I2supArg);
			}
			catch (NumberFormatException e) {
				// No hacer nada, queda como null
			}
			
			try {
				I2inf = Integer.parseInt(I2infArg);
			}
			catch (NumberFormatException e) {
				// No hacer nada, queda como null
			}
			
			try {
				I3sup = Integer.parseInt(I3supArg);
			}
			catch (NumberFormatException e) {
				// No hacer nada, queda como null
			}
			
			try {
				I3inf = Integer.parseInt(I3infArg);
			}
			catch (NumberFormatException e) {
				// No hacer nada, queda como null
			}
			
			blackbox.setNombre(nombre);
			blackbox.setInformacionExtra(descr);
			blackbox.setNombre_I0(I0);
			blackbox.setNombre_I1(I1);
			blackbox.setNombre_I2(I2);
			blackbox.setNombre_I3(I3);
			blackbox.setNombre_O0(O0);
			blackbox.setNombre_O1(O1);
			blackbox.setNombre_O2(O2);
			blackbox.setNombre_O3(O3);
			blackbox.setUmbralSuperiorI0(I0sup);
			blackbox.setUmbralInferiorI0(I0inf);
			blackbox.setUmbralSuperiorI1(I1sup);
			blackbox.setUmbralInferiorI1(I1inf);
			blackbox.setUmbralSuperiorI2(I2sup);
			blackbox.setUmbralInferiorI2(I2inf);
			blackbox.setUmbralSuperiorI3(I3sup);
			blackbox.setUmbralInferiorI3(I3inf);
			
			blackboxEJB.editBlackbox(blackbox);
			
			// Preparar la información para enviar a la blackbox
			BlackboxBuffer blackboxBuffer = bufferEJB.extraer(blackbox.getIdentificador());
			if(blackboxBuffer == null) {
				blackboxBuffer = new BlackboxBuffer();
				blackboxBuffer.setIdentificador(blackbox.getIdentificador());
			}
			blackboxBuffer.setLimiteSuperiorI0(I0sup);
			blackboxBuffer.setLimiteInferiorI0(I0inf);
			blackboxBuffer.setLimiteSuperiorI1(I1sup);
			blackboxBuffer.setLimiteInferiorI1(I1inf);
			blackboxBuffer.setLimiteSuperiorI2(I2sup);
			blackboxBuffer.setLimiteInferiorI2(I2inf);
			blackboxBuffer.setLimiteSuperiorI3(I3sup);
			blackboxBuffer.setLimiteInferiorI3(I3inf);
			bufferEJB.insertar(blackboxBuffer);
		}
	}

}
