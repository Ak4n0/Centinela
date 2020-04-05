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
public class EmailEJB {
	
	public void sendMail(String para, String asunto, String mensaje) {
		this.sendMail(para, asunto, mensaje, null);
	}
	
	public void sendMail(String para, String asunto, String mensaje, String[] archivos) {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", 587);
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("centinela.soluciones@gmail.com", "centinelapp0");
            }
        });

        try {
                Message message = new MimeMessage(session);
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
        	// TODO: añadir el logger y quitar e.printStackTrace();
        	e.printStackTrace();
        }
    }
	
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
				        "<p>Recibes este e-mail porque has solicitado un cambio de contraseña." +
				            "Para ellos debes hacer click en el siguiente <a href='" + enlace + "'>enlace</a>.</p>" +
				        "<p>En la página del <a href='" + enlace + "'>enlace</a> debes introducir tu" +
				            "nueva contraseña. Hecho esto podrás entrar en nuestros servicios con la nueva credencial.</p>" +
				        "<p>La petición de cambio de contraseña caduca a los sesenta minutos desde la petición en la web." +
				            "Si antes de que pase ese tiempo no has completado el proceso la petición quedará anulada.</p>" +
				        "<hr>" +
				        "<p>Si no quieres realizar el cambio de contraseña simplemente ignora o borra este e-mail.</p>" +
				    "</body>" +
				"</html>";
	}
	
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
				        "<p>Desde Centinela Soluciones te damos las gracias por haber confiado en nosotros." +
				            "Para completar el registro debes hacer click en este <a href='" + enlace + "'>enlace</a>.</p>" +
				        "<p>Si el enlace no funciona copia la siguiente dirección y pégala en el omnibox de tu navegador: " + enlace + "</p>" +
				            "<p>Tienes 24 horas desde que iniciaste el registro para validar el email, en caso contrario tu cuenta será borrada.</p>" +
				        "<hr>" +
				        "<p>En caso de que no hayas iniciado un registro y este mensaje es un error óbvialo y bórralo.</p>" +
				    "</body>" +
				"</html>";
	}
}
