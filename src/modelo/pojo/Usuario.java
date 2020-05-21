package modelo.pojo;

public abstract class Usuario {

	protected Integer id;
	protected String nombre;
	protected String email;
	protected Boolean administrador;
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
