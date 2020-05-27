package modelo.pojo;

/**
 * Clase que deriva de Usuario. Tiene valores añadidos de interés para el administrador
 * @author mique
 *
 */
public class UsuarioAdminInfo extends Usuario{

	private int numBlackboxes;

	/**
	 * @return Devuelve el número de blackboxes que pertenece al usuario
	 */
	public int getNumBlackboxes() {
		return numBlackboxes;
	}

	/**
	 * @param numBlackboxes Número de blackboxes que pertenecen al usuario a establecer
	 */
	public void setNumBlackboxes(int numBlackboxes) {
		this.numBlackboxes = numBlackboxes;
	}
	
}
