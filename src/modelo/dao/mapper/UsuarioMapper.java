package modelo.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import modelo.pojo.PeticionNuevoUsuario;
import modelo.pojo.UsuarioAdminInfo;
import modelo.pojo.UsuarioFullInfo;

/**
 * Interfaz usada por MyBatis para acceder a la tabla Usuarios
 * @author mique
 *
 */
public interface UsuarioMapper {
	
	/**
	 * Obtiene un objeto de tipo usuario con todos los datos a partir de un usuario con solo el nombre y el pass.
	 * @param usuarioFullInfo Objeto UsuarioFullInfo con, al menos, los atributos nombre y pass rellenados.
	 * @return Retorna un Objeto UsuarioFullInfo que se encuentre en la base de datos con la misma información rellenado con todos los datos.
	 */
	public UsuarioFullInfo getUsuario(UsuarioFullInfo usuarioFullInfo);

	public boolean existeUsuario(String email);

	public String getNombreUsuario(String email);

	public Integer getIdDesdeEmail(String email);

	public void setUsuario(UsuarioFullInfo usuarioFullInfo);

	public List<UsuarioAdminInfo> getListaUsuariosParaAdministracion();

	public void setTemporizadorNuevoUsuario(@Param("usuario") UsuarioFullInfo usuario, @Param("clave") String clave);

	public boolean existeClaveUsuarioNuevo(String clave);

	public void eliminarClaveUsuarioNuevo(String clave);

	public List<PeticionNuevoUsuario> getPeticionesNuevoUsuario();

	public void eliminarUsuario(Integer idUsuario);

	public List<UsuarioAdminInfo> getUsuariosValidados();

	public UsuarioFullInfo getUsuarioPorId(int id);
	
}
