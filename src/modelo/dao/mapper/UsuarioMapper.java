package modelo.dao.mapper;

import modelo.pojo.Usuario;

/**
 * Interfaz usada por MyBatis para acceder a la tabla Usuarios
 * @author mique
 *
 */
public interface UsuarioMapper {
	
	/**
	 * Obtiene un objeto de tipo usuario con todos los datos a partir de un usuario con solo el nombre y el pass.
	 * @param usuario Objeto Usuario con, al menos, los atributos nombre y pass rellenados.
	 * @return Retorna un Objeto Usuario que se encuentre en la base de datos con la misma información rellenado con todos los datos.
	 */
	public Usuario getUsuario(Usuario usuario);

	public boolean existeUsuario(String email);

	public String getNombreUsuario(String email);
	
}
