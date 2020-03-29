package modelo.ejb;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@LocalBean
@Stateless
public class UtilidadesEJB {

	public String convertirSHA256(String password) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} 
		catch (NoSuchAlgorithmException e) {		
			e.printStackTrace();
			// TODO: Insertar el mensaje en el logger
			return null;
		}
		    
		byte[] hash = md.digest(password.getBytes());
		StringBuffer sb = new StringBuffer();
		    
		for(byte b : hash) {        
			sb.append(String.format("%02x", b));
		}
		    
		return sb.toString();
	}
	
	public String getServerPublicIp() {
		InetAddress inetAddres;
		try {
			inetAddres = InetAddress.getLocalHost();
			return inetAddres.getHostName();
		} catch (UnknownHostException e) {
			// TODO: informar del error por el logger
			e.printStackTrace();
			return null;
		}
	}
	
}
