package modelo.ejb;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

@LocalBean
@Stateless
/**
 * Utilidades varias
 * @author mique
 *
 */
public class UtilidadesEJB {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(UtilidadesEJB.class);
	
	/**
	 * Codifica una cadena usando el algoritmo SHA256
	 * @param cadena Cadena a codificar
	 * @return Cadena codificada
	 */
	public String convertirSHA256(String cadena) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} 
		catch (NoSuchAlgorithmException e) {		
			logger.error("No existe el algoritmo de codificación: " + e.getMessage());
			return null;
		}
		    
		byte[] hash = md.digest(cadena.getBytes());
		StringBuffer sb = new StringBuffer();
		    
		for(byte b : hash) {        
			sb.append(String.format("%02x", b));
		}
		    
		return sb.toString();
	}
	
	/**
	 * Regresa la dirección local del servidor
	 * @param request Objeto HttpServletRequest llamante
	 * @return ip local del servidor
	 */
	public String getServerPublicIp(HttpServletRequest request) {
		return request.getLocalAddr();
	}
	
}
