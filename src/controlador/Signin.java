package controlador;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.ejb.EmailEJB;
import modelo.ejb.OperacionesUsuariosEJB;
import modelo.ejb.UtilidadesEJB;
import modelo.enumeracion.TipoError;
import modelo.pojo.UsuarioFullInfo;

/**
 * Servlet implementation class Signin
 */
@WebServlet("/Signin")
public class Signin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	OperacionesUsuariosEJB operacionesUsuariosEJB;
	
	@EJB
	EmailEJB emailEJB;
	
	@EJB
	UtilidadesEJB utilidadesEJB;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rs = null;
		HttpSession session = request.getSession(false);
		if(session != null) {
			UsuarioFullInfo usuarioFullInfo = (UsuarioFullInfo)session.getAttribute("usuario");
			if(usuarioFullInfo != null) {
				rs = getServletContext().getRequestDispatcher("/aviso.jsp");
				request.setAttribute("titulo", "Ya existe sesión activa");
				request.setAttribute("mensaje", "<p>Actualmente " +
						((UsuarioFullInfo) session.getAttribute("usuario")).getNombre() +
						" ya tiene una sesión activa.</p>" +
						"<p>Antes de iniciar una nueva sesión debe <a href='Logout'>cerrar la actual</a></p>.");
				rs.forward(request, response);
				return;
			}
		}
		rs = getServletContext().getRequestDispatcher("/signin.jsp");
		rs.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
		String email = request.getParameter("email");
		String passwd = request.getParameter("passwd");
		
		// Control de parámetros
		RequestDispatcher rs = getServletContext().getRequestDispatcher("/signin.jsp");
		if(nombre == null || email == null || passwd == null) {
			request.setAttribute("error", TipoError.DATOS_INCOMPLETOS);
			rs.forward(request, response);
			return;
		}
		
		// No hay errores de parámetros
		// Comprobar si existe un usuario con el mismo email
		if(operacionesUsuariosEJB.existeUsuario(email)) {
			request.setAttribute("error", TipoError.CREDENCIALES);
			rs.forward(request, response);
			return;
		}
		
		// No existe un usuario con el mismo email, introducirlo en la BBDD
		UsuarioFullInfo usuario = new UsuarioFullInfo();
		usuario.setNombre(nombre);
		usuario.setEmail(email);
		usuario.setPasswd(passwd);
		usuario.setAdministrador(false);
		operacionesUsuariosEJB.setDatabaseUser(usuario);
		
		// Obtener la id que nos ha dado la base de datos
		usuario = operacionesUsuariosEJB.getDatabaseUser(usuario.getEmail(), usuario.getPasswd());
		
		// Agregar una entrada al temporizador para que el usuario pueda validar su cuenta
		String clave = utilidadesEJB.convertirSHA256(usuario.getEmail() + usuario.getId());
		operacionesUsuariosEJB.setNewUserTimer(usuario, clave);
		
		// Enviar un email al cliente para que valide su dirección
		String enlace = utilidadesEJB.getServerPublicIp() + "/ValidarNuevoUsuario?v=" + clave;
		String mensaje = emailEJB.cuerpoMensajeNuevoUsuario(usuario.getNombre(), enlace);
		if(!emailEJB.sendMail(usuario.getEmail(), "CENTINELA - Validar email", mensaje)) {
			// Falló el envío del email. Borrar el usuario que se había creado y el temporizador.
			operacionesUsuariosEJB.removeDatabaseUser(usuario.getId());
			operacionesUsuariosEJB.removeKeyNewUser(clave);
			
			// Informar del error al usuario para que se ponga en contacto con su email informando dle fallo.
			rs = getServletContext().getRequestDispatcher("/aviso.jsp");
			request.setAttribute("titulo", "Error en el proceso");
			request.setAttribute("mensaje", "<p>No se pudo completar el proceso satisfactoriamente.</p>" + 
											"<p>Inténtalo más tarde. Si los problemas persisten envía un email a " +
											"<a href='mailto:centinela.soluciones@gmail.com?subject=Aviso%20de%20error%20web'>" + 
											"centinela.soluciones@gmail.com</a> explicando lo sucedido.</p>");
			rs.forward(request, response);
			return;
		}
		
		// Una vez que el usuario existe iniciar la sesión
		operacionesUsuariosEJB.setSessionUser(request, usuario);
		
		// Informar al usuario que le han mandado un email para que valide su cuenta
		// en caso contrario esta será borrada en un venticuatro horas.
		rs = getServletContext().getRequestDispatcher("/aviso.jsp");
		request.setAttribute("Validar cuenta", "<p>Acabamos de enviarte un email con un link para que puedas validar " +
							"tu dirección de correo.</p>" + 
							"<p>Si esta validación no se realiza en las próximas 24 horas tu cuenta eliminada.</p>");
		
	}

}
