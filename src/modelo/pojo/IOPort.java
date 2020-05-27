package modelo.pojo;

import java.util.Date;

/**
 * Clase que guarda los datos E/S y el momento en que se producen
 * @author mique
 *
 */
public class IOPort {

	private Integer idBlackbox;
	private Date fechaHora;
	
	private Boolean O0;
	private Boolean O1;
	private Boolean O2;
	private Boolean O3;
	
	private Integer I0;
	private Integer I1;
	private Integer I2;
	private Integer I3;
	
	/**
	 * @return Devuelve la id de la blackbox
	 */
	public Integer getIdBlackbox() {
		return idBlackbox;
	}
	
	/**
	 * @param idBlackbox id de la blackbox a establecer
	 */
	public void setIdBlackbox(Integer idBlackbox) {
		this.idBlackbox = idBlackbox;
	}
	
	/**
	 * @return Devuelve el momento en que se produce la alarma
	 */
	public Date getFechaHora() {
		return fechaHora;
	}
	
	/**
	 * @param fechaHora Momento en que se produce la alarma a establecer
	 */
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	/**
	 * @return Devuelve el valor del puerto O0
	 */
	public Boolean getO0() {
		return O0;
	}

	/**
	 * @param o0 Valor de O0 a establecer
	 */
	public void setO0(Boolean o0) {
		O0 = o0;
	}

	/**
	 * @return Devuelve el valor del puerto O1
	 */
	public Boolean getO1() {
		return O1;
	}

	/**
	 * @param o1 Valor de O1 a establecer
	 */
	public void setO1(Boolean o1) {
		O1 = o1;
	}

	/**
	 * @return Devuelve el valor del puerto O2
	 */
	public Boolean getO2() {
		return O2;
	}

	/**
	 * @param o2 Valor de O2 a establecer
	 */
	public void setO2(Boolean o2) {
		O2 = o2;
	}

	/**
	 * @return Devuelve el valor del puerto O3
	 */
	public Boolean getO3() {
		return O3;
	}

	/**
	 * @param o3 Valor de O3 a establecer
	 */
	public void setO3(Boolean o3) {
		O3 = o3;
	}

	/**
	 * @return Devuelve el valor del puerto I0
	 */
	public Integer getI0() {
		return I0;
	}

	/**
	 * @param i0 Valor de I0 a establecer
	 */
	public void setI0(Integer i0) {
		I0 = i0;
	}

	/**
	 * @return Devuelve el valor del puerto I1
	 */
	public Integer getI1() {
		return I1;
	}

	/**
	 * @param i1 Valor de I1 a establecer
	 */
	public void setI1(Integer i1) {
		I1 = i1;
	}

	/**
	 * @return Devuelve el valor del puerto I2
	 */
	public Integer getI2() {
		return I2;
	}

	/**
	 * @param i2 Valor de I2 a establecer
	 */
	public void setI2(Integer i2) {
		I2 = i2;
	}

	/**
	 * @return Devuelve el valor del puerto I3
	 */
	public Integer getI3() {
		return I3;
	}

	/**
	 * @param i3 Valor de I3 a establecer
	 */
	public void setI3(Integer i3) {
		I3 = i3;
	}
	
}
