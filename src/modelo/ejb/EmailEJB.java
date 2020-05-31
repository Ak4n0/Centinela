package modelo.ejb;

import java.io.File;
import java.util.Properties;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@Stateless
@LocalBean
/**
 * Maneja el envio de e-mails
 * @author mique
 *
 */
public class EmailEJB {
	
	/**
	 * Envia un e-mail
	 * @param para Dirección e-mail destinatario
	 * @param asunto Asunto del e-mail
	 * @param mensaje Cuerpo del mensaje
	 * @return retorna true si se pudo mandar el e-mail, false en caso contrario
	 */
	public boolean sendMail(String para, String asunto, String mensaje) {
		return this.sendMail(para, asunto, mensaje, null);
	}
	
	/**
	 * Envia un e-mail con archivos adjuntos
	 * @param para Dirección e-mail destinatario
	 * @param asunto Asunto del e-mail
	 * @param mensaje Cuerpo del mensaje
	 * @param archivos Dirección de los archivos a adjuntar
	 * @return retorna true si se pudo mandar el e-mail, false en caso contrario
	 */
	public boolean sendMail(String para, String asunto, String mensaje, String[] archivos) {
		boolean exito = true;
		
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
        prop.put("mail.smtp.user", "centinela.soluciones");
        prop.put("mail.smtp.clave", "Canberra0");    //La clave de la cuenta
        prop.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
        prop.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
        prop.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("centinela.soluciones@gmail.com", "Canberra0");
            }
        });
        Message message = new MimeMessage(session);

        try {
//                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("centinela.soluciones@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(para));
                message.setSubject(asunto);

                MimeBodyPart mimeBodyPart = new MimeBodyPart();
                mimeBodyPart.setContent(mensaje, "text/html");
                
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(mimeBodyPart);    	                
                
                // Agrega archivos si hay
                if(archivos != null) {
                	for(String archivo: archivos) {
						MimeBodyPart attachmentBodyPart = new MimeBodyPart();
						attachmentBodyPart.attachFile(new File(archivo));
						multipart.addBodyPart(attachmentBodyPart);
                	}
                }
                message.setContent(multipart);

                Transport.send(message);

        } catch (Exception e) {
        	exito = false;
        }
        
        return exito;
    }
	
	/**
	 * Devuelve una cadena con un cuerpo de mensaje predefinido para que el usuario pueda crear una nueva contraseña
	 * @param nombre Nombre del usuario
	 * @param enlace Enlace que debe seguir el usuario para crear una nueva contraseña
	 * @return Cadena con el texto del cuerpo del mensaje
	 */
	public String cuerpoMensajeNuevaClave(String nombre, String enlace) {
		return 	"<!DOCTYPE html>" +
				"<html>" +
			    	"<head>" +
			    	"<title>Generar nueva contraseña</title>" +
				    "<meta charset='UTF-8'>" +
			        "<meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
				    "</head>" +
				    "<body>" +
				        "<h1>Centinela Soluciones</h1>" +
				        "<p>Hola " + nombre + ",</p>" +
				        "<p>Recibe este e-mail porque ha solicitado un cambio de contraseña. " +
				            "Para ellos debe hacer click en el siguiente <a href='" + enlace + "'>enlace</a>.</p>" +
				        "<p>En la página del <a href='" + enlace + "'>enlace</a> debe introducir su " +
				            "nueva contraseña. Hecho esto podrá entrar en nuestros servicios con la nueva credencial.</p>" +
				        "<p>La petición de cambio de contraseña caduca a los sesenta minutos desde la petición en la web. " +
				            "Si antes de que pase ese tiempo no ha completado el proceso la petición quedará anulada.</p>" +
				        "<p>Si el enlace no funciona correctamente copie y pegue la siguiente dirección el en omnibox de su explorador:<br>" + 
				            enlace + "</p>" +
				        "<hr>" +
				        "<p>Si no quiere realizar el cambio de contraseña simplemente ignore o borre este e-mail.</p>" +
				    "</body>" +
				"</html>";
	}
	
	/**
	 * Devuelve una cadena de un cuerpo predefinido para que un usuario pueda validar su cuenta
	 * @param nombre Nombre del usuario
	 * @param enlace Enlace que debe seguir el usuario para validar
	 * @return Retorna una cadena con el cuerpo del mensaje
	 */
	public String cuerpoMensajeNuevoUsuario(String nombre, String enlace) {
		return 	"<!DOCTYPE html>" +
				"<html>" +
			    	"<head>" +
			    	"<title>Validar contraseña</title>" +
				    "<meta charset='UTF-8'>" +
			        "<meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
				    "</head>" +
				    "<body>" +
				        "<h1>Centinela Soluciones</h1>" +
				        "<p>Hola " + nombre + ",</p>" +
				        "<p>Desde Centinela Soluciones le damos las gracias por haber confiado en nosotros. " +
				            "Para completar su registro debe hacer click en este <a href='" + enlace + "'>enlace</a>.</p>" +
				        "<p>Si el enlace no funciona copie la siguiente dirección y pégala en el omnibox de su navegador: <a href='" + enlace + "'>" + enlace + "</a></p>" +
				            "<p>Tiene 24 horas desde que inició el registro para validar el email, en caso contrario su cuenta será borrada.</p>" +
				        "<hr>" +
				        "<p>En caso de que no haya iniciado un registro y este mensaje es un error ignórelo o bórrelo.</p>" +
				    "</body>" +
				"</html>";
	}
	
	/**
	 * Devuelve una cadena predefinida que informa a un usuario de que ha saltado una alarma
	 * @param nombre Nombre del usuario
	 * @param nombreBlackbox Nombre de la blackbox
	 * @param nombrePuerto Puerto de la blackbox que hizo saltar la alarma
	 * @return Devuelve una cadena con el cuerpo del mensaje
	 */
	public String cuerpoMensajeAlarma(String nombre, String nombreBlackbox, String nombrePuerto) {
		return 	"<!DOCTYPE html>" +
				"<html>" +
			    	"<head>" +
			    	"<title>Aviso de alarma</title>" +
				    "<meta charset='UTF-8'>" +
			        "<meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
				    "</head>" +
				    "<body>" +
				        "<h1>Centinela Soluciones</h1>" +
				        "<p>Hola " + nombre + ",</p>" +
				        "<p>La blackbox " + nombreBlackbox + " ha informado de una alarma en la entrada " + nombrePuerto + ".</p>" +
				        "<p>Esto es debido a que el valor capturado por el sensor ha traspasado el umbral.</p>" +
				        "<p>Debería atender a la blackbox " + nombreBlackbox + " lo antes posible y revisar que pudo causar que se sobrepasara el límite establecido.</p>" +
				    "</body>" +
				"</html>";
	}
}
