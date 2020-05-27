package modelo.pojo;

import java.util.Date;

/**
 * Clase que sirve para anotar el momento en que un nuevo usuario se registra
 * @author mique
 *
 */
public class PeticionNuevoUsuario {
	private String id;
	private Integer idUsuario;
	private Date fechaHora;
	
	/**
	 * @return Devuelve el id de la petición
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @param id Id de la petición a establecer
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return Devuelve el id del usuario
	 */
	public Integer getIdUsuario() {
		return idUsuario;
	}
	
	/**
	 * @param idUsuario id del usuario a establecer
	 */
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	/**
	 * @return Devuelve el momento en que el usuario se registra
	 */
	public Date getFechaHora() {
		return fechaHora;
	}
	
	/**
	 * @param fechaHora Momento en que el usuario se registra a establecer
	 */
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}
	
}
