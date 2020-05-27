package modelo.pojo;

/**
 * Extiende Blackbox. Guarda los cambios que establece un usuario para indicarlos a una blackbox
 * @author mique
 *
 */
public class BlackboxBuffer extends Blackbox {

	private String nuevoPasswd;
	
	private Integer limiteSuperiorI0;
	private Integer limiteInferiorI0;
	private Integer limiteSuperiorI1;
	private Integer limiteInferiorI1;
	private Integer limiteSuperiorI2;
	private Integer limiteInferiorI2;
	private Integer limiteSuperiorI3;
	private Integer limiteInferiorI3;

	private Boolean salidaO0;
	private Boolean salidaO1;
	private Boolean salidaO2;
	private Boolean salidaO3;
	
	/**
	 * @return Devuelve la nueva contraseña
	 */
	public String getNuevoPasswd() {
		return nuevoPasswd;
	}
	/**
	 * @param nuevoPasswd Establece una nueva contraseña
	 */
	public void setNuevoPasswd(String nuevoPasswd) {
		this.nuevoPasswd = nuevoPasswd;
	}
	/**
	 * @return Devuelve el umbral superior para I0
	 */
	public Integer getLimiteSuperiorI0() {
		return limiteSuperiorI0;
	}
	/**
	 * @param limiteSuperiorI0 Umbral superior I0 a establecer
	 */
	public void setLimiteSuperiorI0(Integer limiteSuperiorI0) {
		this.limiteSuperiorI0 = limiteSuperiorI0;
	}
	/**
	 * @return Devuelve el umbral inferior para I0
	 */
	public Integer getLimiteInferiorI0() {
		return limiteInferiorI0;
	}
	/**
	 * @param limiteInferiorI0 Umbral inferior I0 a establecer
	 */
	public void setLimiteInferiorI0(Integer limiteInferiorI0) {
		this.limiteInferiorI0 = limiteInferiorI0;
	}
	/**
	 * @return Devuelve el umbral superior para I1
	 */
	public Integer getLimiteSuperiorI1() {
		return limiteSuperiorI1;
	}
	/**
	 * @param limiteSuperiorI1 Umbral superior I1 a establecer
	 */
	public void setLimiteSuperiorI1(Integer limiteSuperiorI1) {
		this.limiteSuperiorI1 = limiteSuperiorI1;
	}
	/**
	 * @return Devuelve el umbral inferior para I1
	 */
	public Integer getLimiteInferiorI1() {
		return limiteInferiorI1;
	}
	/**
	 * @param limiteInferiorI1 Umbral inferior I1 a establecer
	 */
	public void setLimiteInferiorI1(Integer limiteInferiorI1) {
		this.limiteInferiorI1 = limiteInferiorI1;
	}
	/**
	 * @return Devuelve el umbral superior para I2
	 */
	public Integer getLimiteSuperiorI2() {
		return limiteSuperiorI2;
	}
	/**
	 * @param limiteSuperiorI2 Umbral superior I2 a establecer
	 */
	public void setLimiteSuperiorI2(Integer limiteSuperiorI2) {
		this.limiteSuperiorI2 = limiteSuperiorI2;
	}
	/**
	 * @return Devuelve el umbral inferior para I2
	 */
	public Integer getLimiteInferiorI2() {
		return limiteInferiorI2;
	}
	/**
	 * @param limiteInferiorI2 Umbral inferior I2 a establecer
	 */
	public void setLimiteInferiorI2(Integer limiteInferiorI2) {
		this.limiteInferiorI2 = limiteInferiorI2;
	}
	/**
	 * @return Devuelve el umbral superior para I3
	 */
	public Integer getLimiteSuperiorI3() {
		return limiteSuperiorI3;
	}
	/**
	 * @param limiteSuperiorI3 Umbral superior I3 a establecer
	 */
	public void setLimiteSuperiorI3(Integer limiteSuperiorI3) {
		this.limiteSuperiorI3 = limiteSuperiorI3;
	}
	/**
	 * @return Devuelve el umbral superior para I3
	 */
	public Integer getLimiteInferiorI3() {
		return limiteInferiorI3;
	}
	/**
	 * @param limiteInferiorI3 Umbral inferior I3 a establecer
	 */
	public void setLimiteInferiorI3(Integer limiteInferiorI3) {
		this.limiteInferiorI3 = limiteInferiorI3;
	}
	/**
	 * @return Devuelve la salida O0
	 */
	public Boolean getSalidaO0() {
		return salidaO0;
	}
	/**
	 * @param salidaO0 Valor salida O0 a establecer
	 */
	public void setSalidaO0(Boolean salidaO0) {
		this.salidaO0 = salidaO0;
	}
	/**
	 * @return Devuelve la salida O1
	 */
	public Boolean getSalidaO1() {
		return salidaO1;
	}
	/**
	 * @param salidaO1 Valor salida O1 a establecer
	 */
	public void setSalidaO1(Boolean salidaO1) {
		this.salidaO1 = salidaO1;
	}
	/**
	 * @return Devuelve la salida O2
	 */
	public Boolean getSalidaO2() {
		return salidaO2;
	}
	/**
	 * @param salidaO2 Valor salida O2 a establecer
	 */
	public void setSalidaO2(Boolean salidaO2) {
		this.salidaO2 = salidaO2;
	}
	/**
	 * @return Devuelve la salida O3
	 */
	public Boolean getSalidaO3() {
		return salidaO3;
	}
	/**
	 * @param salidaO3 Valor salida O3 a establecer
	 */
	public void setSalidaO3(Boolean salidaO3) {
		this.salidaO3 = salidaO3;
	}
	
}
