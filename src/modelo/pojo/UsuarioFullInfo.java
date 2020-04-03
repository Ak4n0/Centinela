package modelo.pojo;

public class UsuarioFullInfo extends Usuario {

	private String passwd;
	private Boolean administrador;
	
	/**
	 * @return the passwd
	 */
	public String getPasswd() {
		return passwd;
	}
	
	/**
	 * @param passwd the passwd to set
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	/**
	 * @return the administrador
	 */
	public Boolean getAdministrador() {
		return administrador;
	}
	
	public Boolean isAdministrador() {
		return getAdministrador();
	}
	
	/**
	 * @param administrador the administrador to set
	 */
	public void setAdministrador(Boolean administrador) {
		this.administrador = administrador;
	}
	
}
