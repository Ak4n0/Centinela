package modelo.pojo;

/**
 * Extiende Blackbox. Guarda toda la información perteneciente a una blackbox
 * @author mique
 *
 */
public class BlackboxFullInfo extends Blackbox {
	
	private String nombre_O0;
	private String nombre_O1;
	private String nombre_O2;
	private String nombre_O3;
	
	private String nombre_I0;
	private String nombre_I1;
	private String nombre_I2;
	private String nombre_I3;
	
	private String unidades_I0;
	private String unidades_I1;
	private String unidades_I2;
	private String unidades_I3;
	
	private String funcionTransferencia_I0;
	private String funcionTransferencia_I1;
	private String funcionTransferencia_I2;
	private String funcionTransferencia_I3;
	
	private String funcionTransferenciaInversa_I0;
	private String funcionTransferenciaInversa_I1;
	private String funcionTransferenciaInversa_I2;
	private String funcionTransferenciaInversa_I3;
	
	private Integer umbralInferiorI0;
	private Integer umbralSuperiorI0;
	private Integer umbralInferiorI1;
	private Integer umbralSuperiorI1;
	private Integer umbralInferiorI2;
	private Integer umbralSuperiorI2;
	private Integer umbralInferiorI3;
	private Integer umbralSuperiorI3;
	
	/**
	 * @return Devuelve el nombre de la salida O0
	 */
	public String getNombre_O0() {
		return nombre_O0;
	}
	/**
	 * @param nombre_O0 El nombre para la salida O0 a establecer
	 */
	public void setNombre_O0(String nombre_O0) {
		this.nombre_O0 = nombre_O0;
	}
	/**
	 * @return Devuelve el nombre de la salida O1
	 */
	public String getNombre_O1() {
		return nombre_O1;
	}
	/**
	 * @param nombre_O1 El nombre para la salida O1 a establecer
	 */
	public void setNombre_O1(String nombre_O1) {
		this.nombre_O1 = nombre_O1;
	}
	/**
	 * @return Devuelve el nombre de la salida O2
	 */
	public String getNombre_O2() {
		return nombre_O2;
	}
	/**
	 * @param nombre_O2 El nombre para la salida O2 a establecer
	 */
	public void setNombre_O2(String nombre_O2) {
		this.nombre_O2 = nombre_O2;
	}
	/**
	 * @return Devuelve el nombre de la salida O3
	 */
	public String getNombre_O3() {
		return nombre_O3;
	}
	/**
	 * @param nombre_O3 El nombre para la salida O3 a establecer
	 */
	public void setNombre_O3(String nombre_O3) {
		this.nombre_O3 = nombre_O3;
	}
	/**
	 * @return Devuelve el nombre de la entrada I0
	 */
	public String getNombre_I0() {
		return nombre_I0;
	}
	/**
	 * @param nombre_I0 El nombre para la entrada I0 a establecer
	 */
	public void setNombre_I0(String nombre_I0) {
		this.nombre_I0 = nombre_I0;
	}
	/**
	 * @return Devuelve el nombre de la entrada I1
	 */
	public String getNombre_I1() {
		return nombre_I1;
	}
	/**
	 * @param nombre_I1 El nombre para la entrada I1 a establecer
	 */
	public void setNombre_I1(String nombre_I1) {
		this.nombre_I1 = nombre_I1;
	}
	/**
	 * @return Devuelve el nombre de la entrada I2
	 */
	public String getNombre_I2() {
		return nombre_I2;
	}
	/**
	 * @param nombre_I2 El nombre para la entrada I2 a establecer
	 */
	public void setNombre_I2(String nombre_I2) {
		this.nombre_I2 = nombre_I2;
	}
	/**
	 * @return Devuelve el nombre de la entrada I3
	 */
	public String getNombre_I3() {
		return nombre_I3;
	}
	/**
	 * @param nombre_I3 El nombre para la entrada I3 a establecer
	 */
	public void setNombre_I3(String nombre_I3) {
		this.nombre_I3 = nombre_I3;
	}
	/**
	 * @return Devuelve las unidades de la entrada I0
	 */
	public String getUnidades_I0() {
		return unidades_I0;
	}
	/**
	 * @param unidades_I0 Las unidades para la entrada I0 a establecer
	 */
	public void setUnidades_I0(String unidades_I0) {
		this.unidades_I0 = unidades_I0;
	}
	/**
	 * @return Devuelve las unidades de la entrada I1
	 */
	public String getUnidades_I1() {
		return unidades_I1;
	}
	/**
	 * @param unidades_I1 Las unidades para la entrada I1 a establecer
	 */
	public void setUnidades_I1(String unidades_I1) {
		this.unidades_I1 = unidades_I1;
	}
	/**
	 * @return Establece las unidades para la entrada I2
	 */
	public String getUnidades_I2() {
		return unidades_I2;
	}
	/**
	 * @param unidades_I2 Las unidades para la entrada I2 a establecer
	 */
	public void setUnidades_I2(String unidades_I2) {
		this.unidades_I2 = unidades_I2;
	}
	/**
	 * @return Establece las unidades para la entrada I3
	 */
	public String getUnidades_I3() {
		return unidades_I3;
	}
	/**
	 * @param unidades_I3 Las unidades para la entrada I3 a establecer
	 */
	public void setUnidades_I3(String unidades_I3) {
		this.unidades_I3 = unidades_I3;
	}
	/**
	 * @return Devuelve la función de transferencia de la entrada I0
	 */
	public String getFuncionTransferencia_I0() {
		return funcionTransferencia_I0;
	}
	/**
	 * @param funcionTransferencia_I0 La función de transeferencia para la entrada I0 a establecer
	 */
	public void setFuncionTransferencia_I0(String funcionTransferencia_I0) {
		this.funcionTransferencia_I0 = funcionTransferencia_I0;
	}
	/**
	 * @return Devuelve la función de transferencia de la entrada I1
	 */
	public String getFuncionTransferencia_I1() {
		return funcionTransferencia_I1;
	}
	/**
	 * @param funcionTransferencia_I1 La función de transeferencia para la entrada I0 a establecer
	 */
	public void setFuncionTransferencia_I1(String funcionTransferencia_I1) {
		this.funcionTransferencia_I1 = funcionTransferencia_I1;
	}
	/**
	 * @return Devuelve la función de transferencia de la entrada I2
	 */
	public String getFuncionTransferencia_I2() {
		return funcionTransferencia_I2;
	}
	/**
	 * @param funcionTransferencia_I2 La función de transeferencia para la entrada I0 a establecer
	 */
	public void setFuncionTransferencia_I2(String funcionTransferencia_I2) {
		this.funcionTransferencia_I2 = funcionTransferencia_I2;
	}
	/**
	 * @return Devuelve la función de transferencia de la entrada I3
	 */
	public String getFuncionTransferencia_I3() {
		return funcionTransferencia_I3;
	}
	/**
	 * @param funcionTransferencia_I3 La función de transeferencia para la entrada I3 a establecer
	 */
	public void setFuncionTransferencia_I3(String funcionTransferencia_I3) {
		this.funcionTransferencia_I3 = funcionTransferencia_I3;
	}
	/**
	 * @return Devuelve la función de transferencia inversa para la entrada I0
	 */
	public String getFuncionTransferenciaInversa_I0() {
		return funcionTransferenciaInversa_I0;
	}
	/**
	 * @param funcionTransferenciaInversa_I0 Función de transferencia inversa para la entrada I0 a establecer
	 */
	public void setFuncionTransferenciaInversa_I0(String funcionTransferenciaInversa_I0) {
		this.funcionTransferenciaInversa_I0 = funcionTransferenciaInversa_I0;
	}
	/**
	 * @return Devuelve la función de transferencia inversa para la entrada I1
	 */
	public String getFuncionTransferenciaInversa_I1() {
		return funcionTransferenciaInversa_I1;
	}
	/**
	 * @param funcionTransferenciaInversa_I1 Función de transferencia inversa para la entrada I1 a establecer
	 */
	public void setFuncionTransferenciaInversa_I1(String funcionTransferenciaInversa_I1) {
		this.funcionTransferenciaInversa_I1 = funcionTransferenciaInversa_I1;
	}
	/**
	 * @return Devuelve la función de transferencia inversa para la entrada I2
	 */
	public String getFuncionTransferenciaInversa_I2() {
		return funcionTransferenciaInversa_I2;
	}
	/**
	 * @param funcionTransferenciaInversa_I2 Función de transferencia inversa para la entrada I2 a establecer
	 */
	public void setFuncionTransferenciaInversa_I2(String funcionTransferenciaInversa_I2) {
		this.funcionTransferenciaInversa_I2 = funcionTransferenciaInversa_I2;
	}
	/**
	 * @return Devuelve la función de transferencia inversa para la entrada I3
	 */
	public String getFuncionTransferenciaInversa_I3() {
		return funcionTransferenciaInversa_I3;
	}
	/**
	 * @param funcionTransferenciaInversa_I3 Función de transferencia inversa para la entrada I3 a establecer
	 */
	public void setFuncionTransferenciaInversa_I3(String funcionTransferenciaInversa_I3) {
		this.funcionTransferenciaInversa_I3 = funcionTransferenciaInversa_I3;
	}
	/**
	 * @return Devuelve el valor umbral inferior para I0
	 */
	public Integer getUmbralInferiorI0() {
		return umbralInferiorI0;
	}
	/**
	 * @param umbralInferiorI0 Valor el umbral inferior I0 a establecer
	 */
	public void setUmbralInferiorI0(Integer umbralInferiorI0) {
		this.umbralInferiorI0 = umbralInferiorI0;
	}
	/**
	 * @return Devuelve el valor umbral superior para I0
	 */
	public Integer getUmbralSuperiorI0() {
		return umbralSuperiorI0;
	}
	/**
	 * @param umbralSuperiorI0 Valor el umbral superior I0 a establecer
	 */
	public void setUmbralSuperiorI0(Integer umbralSuperiorI0) {
		this.umbralSuperiorI0 = umbralSuperiorI0;
	}
	/**
	 * @return Devuelve el valor umbral inferior para I1
	 */
	public Integer getUmbralInferiorI1() {
		return umbralInferiorI1;
	}
	/**
	 * @param umbralInferiorI1 Valor el umbral inferior I1 a establecer
	 */
	public void setUmbralInferiorI1(Integer umbralInferiorI1) {
		this.umbralInferiorI1 = umbralInferiorI1;
	}
	/**
	 * @return Devuelve el valor umbral superior para I1
	 */
	public Integer getUmbralSuperiorI1() {
		return umbralSuperiorI1;
	}
	/**
	 * @param umbralSuperiorI1 Valor el umbral superior I1 a establecer
	 */
	public void setUmbralSuperiorI1(Integer umbralSuperiorI1) {
		this.umbralSuperiorI1 = umbralSuperiorI1;
	}
	/**
	 * @return Devuelve el valor umbral inferior para I2
	 */
	public Integer getUmbralInferiorI2() {
		return umbralInferiorI2;
	}
	/**
	 * @param umbralInferiorI2 Valor el umbral inferior I2 a establecer
	 */
	public void setUmbralInferiorI2(Integer umbralInferiorI2) {
		this.umbralInferiorI2 = umbralInferiorI2;
	}
	/**
	 * @return Devuelve el valor umbral superior para I2
	 */
	public Integer getUmbralSuperiorI2() {
		return umbralSuperiorI2;
	}
	/**
	 * @param umbralSuperiorI2 Valor el umbral superior I2 a establecer
	 */
	public void setUmbralSuperiorI2(Integer umbralSuperiorI2) {
		this.umbralSuperiorI2 = umbralSuperiorI2;
	}
	/**
	 * @return Devuelve el valor umbral inferior para I3
	 */
	public Integer getUmbralInferiorI3() {
		return umbralInferiorI3;
	}
	/**
	 * @param umbralInferiorI3 Valor el umbral inferior I3 a establecer
	 */
	public void setUmbralInferiorI3(Integer umbralInferiorI3) {
		this.umbralInferiorI3 = umbralInferiorI3;
	}
	/**
	 * @return Devuelve el valor umbral superior para I3
	 */
	public Integer getUmbralSuperiorI3() {
		return umbralSuperiorI3;
	}
	/**
	 * @param umbralSuperiorI3 Valor el umbral superior I3 a establecer
	 */
	public void setUmbralSuperiorI3(Integer umbralSuperiorI3) {
		this.umbralSuperiorI3 = umbralSuperiorI3;
	}

}
