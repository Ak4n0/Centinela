package modelo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mapper.UsuarioMapper;
import modelo.pojo.PeticionNuevoUsuario;
import modelo.pojo.UsuarioAdminInfo;
import modelo.pojo.UsuarioFullInfo;

/**
 * Maneja la tabla Usuario de la base de datos
 * @author mique
 *
 */
public class UsuarioDAO {

	private UsuarioDAO() {}
	
	/**
	 * Extrae toda la información de la base de datos a partir de una información mínima
	 * @param usuario UsuarioFullInfo con los campos email y passwd rellenados
	 * @return Objeto UsuarioFullInfo con todos los datos del usuario
	 */
	public static UsuarioFullInfo getUsuario(UsuarioFullInfo usuario) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper mapper = sqlSession.getMapper(UsuarioMapper.class);
			return mapper.getUsuario(usuario);
		} finally {
			sqlSession.close();
		}
	}
	
	/**
	 * Comprueba si existe un usuario en la base de datos
	 * @param email Email del usuario a buscar
	 * @return Devuelve true si existe el usuario, false en caso contrario
	 */
	public static boolean existeUsuario(String email) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper mapper = sqlSession.getMapper(UsuarioMapper.class);
			return mapper.existeUsuario(email);
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Obtiene únicamente el nombre de un usuario
	 * @param email Email de usuario a buscar
	 * @return Una cadena con el nombre del usuario
	 */
	public static String getNombreUsuario(String email) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper mapper = sqlSession.getMapper(UsuarioMapper.class);
			return mapper.getNombreUsuario(email);
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Obtiene únicamente el id de un usuario desde el e-mail
	 * @param email Email del usuario a buscar
	 * @return El entero identificador interno de la base de datos del usuario
	 */
	public static Integer getIdDesdeEmail(String email) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper mapper = sqlSession.getMapper(UsuarioMapper.class);
			return mapper.getIdDesdeEmail(email);
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Inserta un usuario en la base de datos
	 * @param usuarioFullInfo Objeto usuarioFullInfo con toda la información del usuario
	 */
	public static void setUsuario(UsuarioFullInfo usuarioFullInfo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper mapper = sqlSession.getMapper(UsuarioMapper.class);
			mapper.setUsuario(usuarioFullInfo);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Obtiene una lista de usuarios con información de interés para el Administrador
	 * @return Una lista de objetos UsuarioAdminInfo
	 */
	public static List<UsuarioAdminInfo> getListaUsuariosParaAdministracion() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper mapper = sqlSession.getMapper(UsuarioMapper.class);
			return mapper.getListaUsuariosParaAdministracion();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Inserta en la base de datos información sobre un nuevo usuario: el token que debe usar para validarse, su id y la fecha/hora actual
	 * @param usuario Objeto UsuarioFullInfo que debe tener, almenos el id rellenado
	 * @param clave Cadena con el token que debe usar el usuario para validar su cuenta
	 */
	public static void setTemporizadorNuevoUsuario(UsuarioFullInfo usuario, String clave) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper mapper = sqlSession.getMapper(UsuarioMapper.class);
			mapper.setTemporizadorNuevoUsuario(usuario, clave);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Indica si el token que debe usar un usuario para su identificación existe
	 * @param clave Cadena con el token de identificación
	 * @return Devuelve true si existe dicho token, false en caso contrario
	 */
	public static boolean existeClaveUsuarioNuevo(String clave) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper mapper = sqlSession.getMapper(UsuarioMapper.class);
			return mapper.existeClaveUsuarioNuevo(clave);
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Elimina de la base de datos el token de validación
	 * @param clave Cadena con el token de validación del usuario
	 */
	public static void eliminarClaveUsuarioNuevo(String clave) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper mapper = sqlSession.getMapper(UsuarioMapper.class);
			mapper.eliminarClaveUsuarioNuevo(clave);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Devuelve una lista de usuarios con todos los usuarios que aún no se han validado
	 * @return Lista de PeticionNuevoUsuario con todas las peticiones de usuarios nuevos
	 */
	public static List<PeticionNuevoUsuario> getPeticionesNuevoUsuario() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper mapper = sqlSession.getMapper(UsuarioMapper.class);
			return mapper.getPeticionesNuevoUsuario();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Elimina un usuario de la base de datos
	 * @param idUsuario Id interno de la base de datos del usuario
	 */
	public static void eliminarUsuario(Integer idUsuario) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper mapper = sqlSession.getMapper(UsuarioMapper.class);
			mapper.eliminarUsuario(idUsuario);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Devuelve una lista con todos los usuarios que han sido validados
	 * @return Lista de objetos UsuarioAdminInfo con sólo los usuarios validados
	 */
	public static List<UsuarioAdminInfo> getUsuariosValidados() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper mapper = sqlSession.getMapper(UsuarioMapper.class);
			return mapper.getUsuariosValidados();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Obtiene una información con toda la información de los usuarios
	 * @param id Identificador en la base de datos del usuario
	 * @return Devuelve un objeto UsuarioFullInfo con toda la información del usuario
	 */
	public static UsuarioFullInfo getUsuario(int id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper mapper = sqlSession.getMapper(UsuarioMapper.class);
			return mapper.getUsuarioPorId(id);
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Modifica un usuario de la base de datos
	 * @param usuario UsuarioFullInfo con los con todos los datos a modificar salvo el id, que sirve para identificar al usuario en la bbdd
	 */
	public static void modificarUsuario(UsuarioFullInfo usuario) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper mapper = sqlSession.getMapper(UsuarioMapper.class);
			mapper.modificarUsuario(usuario);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}
}
