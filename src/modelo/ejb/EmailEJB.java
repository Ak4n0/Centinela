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
}
