package modelo.pojo;

/**
 * Clase abstracta para representar a un usuario
 * @author mique
 *
 */
public abstract class Usuario {

	protected Integer id;
	protected String nombre;
	protected String email;
	protected Boolean administrador;
	
	/**
	 * @return Devuelve el id del usuario
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * @param id Id del usuario a establecer
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * @return Devuelve el nombre del usuario
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * @param nombre Nombre del usuario a establecer
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * @return Devuelve el email del usuario
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param email email del usuario a establecer
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return Devuelve si el usuario es administrador
	 */
	public Boolean getAdministrador() {
		return administrador;
	}
	
	/**
	 * @return Devuelve si el usuario es administrador
	 */
	public Boolean isAdministrador() {
		return getAdministrador();
	}
	
	/**
	 * @param administrador Booleano que establece si el usuario es administrador
	 */
	public void setAdministrador(Boolean administrador) {
		this.administrador = administrador;
	}
	
}
