package modelo.pojo;

/**
 * Clase que deriva de Usuario. Tiene toda la información requerida de un usuario
 * @author mique
 *
 */
public class UsuarioFullInfo extends Usuario {

	private String passwd;
	
	/**
	 * @return Devuelve la contraseña del usuario
	 */
	public String getPasswd() {
		return passwd;
	}
	
	/**
	 * @param passwd Contraseña del usuario a establecer
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
}
