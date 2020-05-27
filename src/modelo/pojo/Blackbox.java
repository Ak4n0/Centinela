package modelo.pojo;

/**
 * Clase abstracta con información básica de una blackbox
 * @author mique
 *
 */
public abstract class Blackbox {

	// id de la blackbox
	protected int id;
	// identificador único de la blackbox
	protected String identificador;
	// nombre de la blackbox
	protected String nombre;
	// contraseña para el cifrado
	protected String passwd;
	// información extra usada por el usuario
	protected String informacionExtra;
	// identificador del usuario propietario
	protected int idUsuario;
	
	/**
	 * @return Devuelve el id la blackbox
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @param id Id de la blackbox a establecer
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return Devuelve el identificador único de la blackbox
	 */
	public String getIdentificador() {
		return identificador;
	}
	
	/**
	 * @param identificador the identificador to set
	 */
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	
	/**
	 * @return Devuelve el nombre de la blackbox
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * @param nombre Nombre de la blackbox a establecer
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * @return Devuelve la contraseña de la blackbox
	 */
	public String getPasswd() {
		return passwd;
	}
	
	/**
	 * @param passwd Contraseña a establecer
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	/**
	 * @return Devuelve la información extra de la blackbox
	 */
	public String getInformacionExtra() {
		return informacionExtra;
	}
	
	/**
	 * @param informacionExtra Información extra a establecer
	 */
	public void setInformacionExtra(String informacionExtra) {
		this.informacionExtra = informacionExtra;
	}
	
	/**
	 * @return Devuelve el id del usuario propietario
	 */
	public int getIdUsuario() {
		return idUsuario;
	}
	
	/**
	 * @param idUsuario Id del propietar a establecer
	 */
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
}
