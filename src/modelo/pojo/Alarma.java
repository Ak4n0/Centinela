package modelo.pojo;

import java.util.Date;

public class Alarma {

	private Integer idBlackbox;
	private Date fechaHora;
	private String puerto;
	private Integer nivel;
	private Integer valorUmbral;
	
	public Alarma() {}

	/**
	 * @return the idBlackbox
	 */
	public Integer getIdBlackbox() {
		return idBlackbox;
	}

	/**
	 * @param idBlackbox the idBlackbox to set
	 */
	public void setIdBlackbox(Integer idBlackbox) {
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
	
	/**
	 * @return the valorUmbral
	 */
	public Integer getValorUmbral() {
		return valorUmbral;
	}

	/**
	 * @param valorUmbral the valorUmbral to set
	 */
	public void setValorUmbral(Integer valorUmbral) {
		this.valorUmbral = valorUmbral;
	}
	
}
