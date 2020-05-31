package modelo.ejb;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

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
	 * Regresa la dirección del servidor. Primeramente buscará si existe un archivo properties de nombre servidor.properties.
	 * Si existe, dentro de él buscará la propiedad 'direccion'. Si existe y no tiene el valor 'auto' devolverá este valor,
	 * en caso contrario se devolverá la dirección local del servidor tomada del objeto 'request' que se le ha pasado
	 * @param request Objeto HttpServletRequest llamante
	 * @return Devuelve la dirección IP del servidor
	 */
	public String getServerPublicIp(HttpServletRequest request) {
		Properties prop = new Properties();
		String localAddress = null;
		// Busca en el archivo 'servidor.properties' la propiedad 'direccion'
		// Si lo consigue y esa propiedad no se llama auto toma el valor de esa propiedad
		try (FileInputStream in = new FileInputStream("servidor.properties")){
			prop.load(in);
			if(prop.containsKey("direccion")) {
				if(!prop.get("direccion").equals("auto")) {
					localAddress = prop.getProperty("direccion");
				}
			}
		} catch (IOException e) {
			// No hacer nada
		}
		
		// En caso de que las condiciones anteriores no se cumplan devolverá la ip en la red local del servidor.
		if(localAddress == null) {
			localAddress = request.getLocalAddr();
		}
		return request.getLocalAddr();
	}
	
}
