package modelo.pojo;

import java.util.Date;

/**
 * Clase que representa una alarma lanzada desde una blackbox
 * @author mique
 *
 */
public class Alarma {

	// Id interna de la blackbox
	private Integer idBlackbox;
	// Momento en que se produce la alarma
	private Date fechaHora;
	// Puerto dónde la alarma ha saltado
	private String puerto;
	// Valor conseguido por ese puerto
	private Integer valor;
	// Valor umbral sobrepasado
	private Integer valorUmbral;
	
	public Alarma() {}

	/**
	 * @return Devuelve la id de la blackbox
	 */
	public Integer getIdBlackbox() {
		return idBlackbox;
	}

	/**
	 * @param idBlackbox Id de la blackbox a establecer
	 */
	public void setIdBlackbox(Integer idBlackbox) {
		this.idBlackbox = idBlackbox;
	}

	/**
	 * @return Devuelve el momento fechaHora
	 */
	public Date getFechaHora() {
		return fechaHora;
	}

	/**
	 * @param fechaHora Momento fecha/hora que se establece
	 */
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	/**
	 * @return Devuelve el puerto que ha hecho saltar la alarma
	 */
	public String getPuerto() {
		return puerto;
	}

	/**
	 * @param puerto Establece el puerto que ha hecho saltar la alarma
	 */
	public void setPuerto(String puerto) {
		this.puerto = puerto;
	}

	/**
	 * @return Devuelve el valor leído en el puerto
	 */
	public Integer getValor() {
		return valor;
	}

	/**
	 * @param valor Establece el valor del puerto
	 */
	public void setValor(Integer valor) {
		this.valor = valor;
	}
	
	/**
	 * @return Devuelve umbral que se ha sobrepasado
	 */
	public Integer getValorUmbral() {
		return valorUmbral;
	}

	/**
	 * @param valorUmbral Establece el valor umbral que se ha sobrepasado
	 */
	public void setValorUmbral(Integer valorUmbral) {
		this.valorUmbral = valorUmbral;
	}
	
}
