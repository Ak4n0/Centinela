package controlador;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.ejb.TokenNuevoPasswordEJB;
import modelo.ejb.UsuariosEJB;
import modelo.enumeracion.TipoError;
import modelo.pojo.TokenNuevoPassword;
import modelo.pojo.UsuarioFullInfo;

/**
 * Atiende a la solicitud de cambio de password de un usuario
 */
@WebServlet("/CambiarPassword")
public class CambiarPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private TokenNuevoPasswordEJB tokenNuevoPasswordEJB;
	
	@EJB
	private UsuariosEJB usuariosEJB;
	
	/**
	 * Método get
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String token = request.getParameter("solicitud");
		RequestDispatcher rs = null;
		// comprobar que la solicitud no esté vacía
		if(token == null) {
			rs = getServletContext().getRequestDispatcher("/aviso.jsp");
			request.setAttribute("titulo", "Sin identificador");
			request.setAttribute("mensaje", "<p>No ha proporcionado un identificador de " +
					"solicitud de cambio de contraseña válido.</p>" +
					"<p>Vuelva a realizar la petición con un identificador válido.</p>");
			rs.forward(request, response);
			return;
		}
		
		// comprobar que el identificador de solicitud es válido
		TokenNuevoPassword tnp =  tokenNuevoPasswordEJB.getTokenFromId(token);
		if(tnp == null) {
			rs = getServletContext().getRequestDispatcher("/aviso.jsp");
			request.setAttribute("titulo", "Identificador no válido");
			request.setAttribute("mensaje", "<p>No ha proporcionado un identificador de " +
					"solicitud de cambio de contraseña válido.</p>" +
					"<p>Vuelva a realizar la petición con un identificador válido.</p>");
			rs.forward(request, response);
			return;
		}
		
		rs = getServletContext().getRequestDispatcher("/cambiarPassword.jsp");
		request.setAttribute("usuario", tnp.getIdUsuario());
		rs.forward(request, response);
	}

	/**
	 * Método post
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idUsuarioArg = request.getParameter("usuario");
		String passwd = request.getParameter("passwd");
		int idUsuario;
		RequestDispatcher rs = null;
		
		// Error, no se ha enviado el id del usuario
		if(idUsuarioArg == null) {
			rs = getServletContext().getRequestDispatcher("/aviso.jsp");
			request.setAttribute("titulo", "Usuario no reconocido");
			request.setAttribute("mensaje", "<p>El usuario de la " +
					"solicitud de cambio de contraseña no es válido.</p>" +
					"<p>Vuelva a realizar la petición con un usuario válido.</p>");
			rs.forward(request, response);
			return;
		}
		
		// Error al intentar convertir el parámetro idUsuario a un número
		try {
			idUsuario = Integer.parseInt(idUsuarioArg);
		} catch(NumberFormatException e) {
			rs = getServletContext().getRequestDispatcher("/aviso.jsp");
			request.setAttribute("titulo", "Usuario no reconocido");
			request.setAttribute("mensaje", "<p>El usuario de la " +
					"solicitud de cambio de contraseña no es válido.</p>" +
					"<p>Vuelva a realizar la petición con un usuario válido.</p>");
			rs.forward(request, response);
			return;
		}
		
		// Error, no se ha mandado la nueva password de usuario
		if(passwd == null) {
			rs = getServletContext().getRequestDispatcher("/cambiarPassword.jsp");
			request.setAttribute("usuario", idUsuario);
			request.setAttribute("error", TipoError.DATOS_INCOMPLETOS);
			rs.forward(request, response);
			return;
		}
		
		// Obtener el usuario de la base de datos
		UsuarioFullInfo usuario = usuariosEJB.getDatabaseUser(idUsuario);
		
		// Error, dicho usuario no existe en la base de datos
		if(usuario == null) {
			rs = getServletContext().getRequestDispatcher("/aviso.jsp");
			request.setAttribute("titulo", "Usuario no reconocido");
			request.setAttribute("mensaje", "<p>El usuario de la " +
					"solicitud de cambio de contraseña no es válido.</p>" +
					"<p>Vuelva a realizar la petición con un usuario válido.</p>");
			rs.forward(request, response);
			return;
		}
		
		// Comprobar que exite una solicitud de cambio de contraseña de dicho usuario
		TokenNuevoPassword tnp = tokenNuevoPasswordEJB.getTokenFromEmail(usuario.getEmail());
		
		// Si la solicitud no existe avisar al usuario
		if(tnp == null) {
			rs = getServletContext().getRequestDispatcher("/aviso.jsp");
			request.setAttribute("titulo", "No existe solicitud");
			request.setAttribute("mensaje", "<p>Este usuario no " +
					"tiene ninguna solicitud para cambiar su contraseña.</p>" +
					"<p>Vuelva a realizar la petición.</p>");
			rs.forward(request, response);
			return;
		}
		
		// Todos los datos son correctos. Realizar el cambio.
		usuario.setPasswd(passwd);
		usuariosEJB.updateDatabaseUser(usuario);
		
		// Borrar la solicitud de la BBDD
		tokenNuevoPasswordEJB.deleteToken(tnp.getId());
		
		// Avisar al usuario de que su contraseña ha sido cambiada
		rs = getServletContext().getRequestDispatcher("/aviso.jsp");
		request.setAttribute("titulo", "Contraseña cambiada");
		request.setAttribute("mensaje", "<p>El cambio de contraseña " +
				"se ha realizado correctamente.</p>" +
				"<p>Regrese a la página principal y vuelva a logearse.</p>");
		rs.forward(request, response);
	}

}
