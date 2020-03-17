package modelo.pojo;

import java.util.Date;

public class Alarma {

	private Integer id;
	private String idBlackbox;
	private Date fechaHora;
	private String puerto;
	private Integer nivel;
	
	public Alarma() {}

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
	 * @return the idBlackbox
	 */
	public String getIdBlackbox() {
		return idBlackbox;
	}

	/**
	 * @param idBlackbox the idBlackbox to set
	 */
	public void setIdBlackbox(String idBlackbox) {
		this.idBlackbox = idBlackbox;
	}

	/**
	 * @return the fechaHora
	 */
	public Date getFechaHora() {
		return fechaHora;
	}

	/**
	 * @param fechaHora the fechaHora to set
	 */
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	/**
	 * @return the puerto
	 */
	public String getPuerto() {
		return puerto;
	}

	/**
	 * @param puerto the puerto to set
	 */
	public void setPuerto(String puerto) {
		this.puerto = puerto;
	}

	/**
	 * @return the nivel
	 */
	public Integer getNivel() {
		return nivel;
	}

	/**
	 * @param nivel the nivel to set
	 */
	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}
	
}
