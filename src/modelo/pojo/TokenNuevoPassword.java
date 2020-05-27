package modelo.pojo;

import java.util.Date;

/**
 * Representa los datos para una petición de una nueva contraseña
 * @author mique
 *
 */
public class TokenNuevoPassword {
	private String id;
	private Integer idUsuario;
	private Date fechaHora;
	
	public TokenNuevoPassword() {}

	/**
	 * @return Devuelve el id del token
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id Id del token a establecer
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
	 * @return Devuelve el momento en que se produce la petición
	 */
	public Date getFechaHora() {
		return fechaHora;
	}

	/**
	 * @param fechaHora Momento en que se establece la petición a establecer
	 */
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}
	
}
