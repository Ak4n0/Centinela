package modelo.pojo;

public abstract class Blackbox {

	protected int id;
	protected String identificador;
	protected String nombre;
	protected String passwd;
	protected String ip_v4;
	protected String informacionExtra;
	protected int idUsuario;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return the identificador
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
	 * @return the informacionExtra
	 */
	public String getInformacionExtra() {
		return informacionExtra;
	}
	
	/**
	 * @param informacionExtra the informacionExtra to set
	 */
	public void setInformacionExtra(String informacionExtra) {
		this.informacionExtra = informacionExtra;
	}
	
	/**
	 * @return the idUsuario
	 */
	public int getIdUsuario() {
		return idUsuario;
	}
	
	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
}
