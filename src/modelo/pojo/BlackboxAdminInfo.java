package modelo.pojo;

/**
 * Clase que extiende de Blackbox con información de especial interés para el Administrador
 * @author mique
 *
 */
public class BlackboxAdminInfo extends Blackbox {

	// Nombre del propietario de la blackbox
	private String nombreUsuario;

	/**
	 * @return Nombre dle usuario
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	/**
	 * @param nombreUsuario Nombre de usuario a establecer
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	
	
}
