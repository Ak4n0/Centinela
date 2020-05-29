package modelo.pojo;

/**
 * Extiende Blackbox. Guarda los cambios que establece un usuario para indicarlos a una blackbox
 * @author mique
 *
 */
public class BlackboxBuffer extends BlackboxFullInfo {

	private String nuevoPasswd;

	private Boolean salidaO0;
	private Boolean salidaO1;
	private Boolean salidaO2;
	private Boolean salidaO3;
	
	private Boolean cambioUmbral;
	
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
	/**
	 * @return Devuelve true si hay cambio en los umbrales, false o null en caso contrario
	 */
	public Boolean getCambioUmbral() {
		return cambioUmbral;
	}
	/**
	 * @param cambioUmbral Indica si hay cambios en los umbrales
	 */
	public void setCambioUmbral(Boolean cambioUmbral) {
		this.cambioUmbral = cambioUmbral;
	}
	
}
