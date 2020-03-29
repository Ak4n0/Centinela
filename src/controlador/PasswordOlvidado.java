package controlador;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.ejb.OperacionesUsuariosEJB;
import modelo.ejb.UtilidadesEJB;
import modelo.ejb.EmailEJB;
import modelo.ejb.OperacionesTokensNuevoPasswordEJB;
import modelo.enumeracion.TipoError;
import modelo.pojo.TokenNuevoPassword;

/**
 * Servlet implementation class PasswordOlvidado
 */
@WebServlet("/PasswordOlvidado")
public class PasswordOlvidado extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	OperacionesUsuariosEJB operacionesUsuariosEJB;
	
	@EJB
	EmailEJB emailEJB;
	
	@EJB
	OperacionesTokensNuevoPasswordEJB operacionesTokensNuevoPasswordEJB;
	
	@EJB
	UtilidadesEJB utilidadesEJB;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rs = getServletContext().getRequestDispatcher("/password-olvidado.jsp");
		rs.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		
		// Control de parámetros
		RequestDispatcher rs = getServletContext().getRequestDispatcher("/password-olvidado.jsp");
		if(email == null) {
			request.setAttribute("error", TipoError.DATOS_INCOMPLETOS);
			rs.forward(request, response);
			return;
		}
		
		if(!operacionesUsuariosEJB.existeUsuario(email)) {
			request.setAttribute("error", TipoError.CREDENCIALES);
			rs.forward(request, response);
			return;
		}
		
		// El usuario ha introducido correctamente su email
		// Ahora hay que enviar un mensaje para que pueda introducir una nueva contraseña
		// Obtener el nombre del usuario
		String nombre = operacionesUsuariosEJB.getUserName(email);
		
		// Obtener el identificador para cambiar la clave
		// Comprobar si el usuario ya tiene un identificador
		TokenNuevoPassword tokenNuevoPassword = operacionesTokensNuevoPasswordEJB.getTokenFromEmail(email);
		
		// Si no tiene se genera uno
		if(tokenNuevoPassword == null) {
			tokenNuevoPassword = operacionesTokensNuevoPasswordEJB.createToken(email);
		}
		
		// Si no se pudo generar informar del error
		if(tokenNuevoPassword == null) {
			rs = getServletContext().getRequestDispatcher("/aviso.jsp");	
			request.setAttribute("titulo", "Ocurrió un error");
			request.setAttribute("mensaje", "<p>Ha ocurrido un error al acceder a la base de datos.</p>" +
					"<p>Por favor inténtalo más tarde. Si los problemas persisten envia " +
						"un email a esta direccion <a href='mailto:centinela.soluciones@gmail.com?subject=Aviso%20de%20error%20web'>" + 
						"centinela.soluciones@gmail.com</a> explicando el problema.</p>");
			rs.forward(request, response);
			return;
		}
		
		// Se pudieron conseguir todos los datos, por lo que se manda por email
		// Primero conseguir la ip del servidor
		String enlace = utilidadesEJB.getServerPublicIp();
		// Si no se obtuvo la ip del servidor avisar al usuario
		if(enlace == null) {
			rs = getServletContext().getRequestDispatcher("/aviso.jsp");
			rs = getServletContext().getRequestDispatcher("/aviso.jsp");	
			request.setAttribute("titulo", "Ocurrió un error");
			request.setAttribute("mensaje", "<p>Ha ocurrido un error servidor.</p>" +
					"<p>Por favor inténtalo más tarde. Si los problemas persisten envia " +
						"un email a esta direccion <a href='mailto:centinela.soluciones@gmail.com?subject=Aviso%20de%20error%20web'>" + 
						"centinela.soluciones@gmail.com</a> explicando el problema.</p>");
			rs.forward(request, response);
			return;
		}
		
		// No ocurrió ningún error en la toma de datos
		String cuerpoMensajeEmail = emailEJB.cuerpoMensajeNuevaClave(nombre, enlace);
		emailEJB.sendMail(email, "Centinela Servicios. Solicitud de cambio de contraseña", cuerpoMensajeEmail);
		
		// Regresar Principal
		response.sendRedirect("Principal");
	}

}
