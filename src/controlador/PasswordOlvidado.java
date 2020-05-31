package controlador;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.ejb.UsuariosEJB;
import modelo.ejb.UtilidadesEJB;
import modelo.ejb.EmailEJB;
import modelo.ejb.TokenNuevoPasswordEJB;
import modelo.enumeracion.TipoError;
import modelo.pojo.TokenNuevoPassword;

/**
 * Recuperación de contraseña perdida para un usuario
 */
@WebServlet("/PasswordOlvidado")
public class PasswordOlvidado extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private UsuariosEJB usuariosEJB;
	
	@EJB
	private EmailEJB emailEJB;
	
	@EJB
	private TokenNuevoPasswordEJB tokenNuevoPasswordEJB;
	
	@EJB
	private UtilidadesEJB utilidadesEJB;
	
	/**
	 * Método get
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Muestra la página para recuperar el password
		RequestDispatcher rs = getServletContext().getRequestDispatcher("/password-olvidado.jsp");
		rs.forward(request, response);
	}

	/**
	 * Método post
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		
		// Control de parámetros
		RequestDispatcher rs = getServletContext().getRequestDispatcher("/password-olvidado.jsp");
		
		// No ha dado el e-mail, informar del error
		if(email == null) {
			request.setAttribute("error", TipoError.DATOS_INCOMPLETOS);
			rs.forward(request, response);
			return;
		}
		
		
		// El usuario no ha escrito bien su e-mail, informar del error
		if(!usuariosEJB.existeUsuario(email)) {
			request.setAttribute("error", TipoError.CREDENCIALES);
			rs.forward(request, response);
			return;
		}
		
		// El usuario ha introducido correctamente su email
		// Ahora hay que enviar un mensaje para que pueda introducir una nueva contraseña
		// Obtener el nombre del usuario
		String nombre = usuariosEJB.getUserName(email);
		
		// Obtener el identificador para cambiar la clave
		// Comprobar si el usuario ya tiene un identificador
		TokenNuevoPassword tokenNuevoPassword = tokenNuevoPasswordEJB.getTokenFromEmail(email);
		
		// Si no tiene se genera uno
		if(tokenNuevoPassword == null) {
			tokenNuevoPassword = tokenNuevoPasswordEJB.createToken(email);
		}
		
		// Si no se pudo generar informar del error
		if(tokenNuevoPassword == null) {
			rs = getServletContext().getRequestDispatcher("/aviso.jsp");	
			request.setAttribute("titulo", "Ocurrió un error");
			request.setAttribute("mensaje", "<p>Ha ocurrido un error al acceder a la base de datos.</p>" +
					"<p>Por favor inténtelo más tarde. Si los problemas persisten envíe " +
						"un email a esta direccion <a href='mailto:centinela.soluciones@gmail.com?subject=Aviso%20de%20error%20web'>" + 
						"centinela.soluciones@gmail.com</a> explicando el problema.</p>");
			rs.forward(request, response);
			return;
		}
		
		// Se pudieron conseguir todos los datos, por lo que se manda por email
		// Primero conseguir la ip del servidor
		String enlace = utilidadesEJB.getServerPublicIp(request) + ":8080" + request.getContextPath() + "/CambiarPassword?solicitud=" + tokenNuevoPassword.getId();
		
		// No ocurrió ningún error en la toma de datos
		String cuerpoMensajeEmail = emailEJB.cuerpoMensajeNuevaClave(nombre, enlace);
		emailEJB.sendMail(email, "Centinela Servicios. Solicitud de cambio de contraseña", cuerpoMensajeEmail);
		
		
		// Notificar el envio del e-mail
		rs = getServletContext().getRequestDispatcher("/aviso.jsp");	
		request.setAttribute("titulo", "Compruebe e-mail");
		request.setAttribute("mensaje", "<p>En breve recibirá un e-mail con las instrucciones a proceder para el cambio de su contraseña.</p>");
		rs.forward(request, response);
	}

}
