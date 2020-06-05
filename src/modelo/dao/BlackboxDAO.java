package modelo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mapper.BlackboxMapper;
import modelo.pojo.BlackboxFullInfo;
import modelo.pojo.BlackboxAdminInfo;

/**
 * Maneja la tabla Blackbox de la BBDD
 * @author mique
 *
 */
public class BlackboxDAO {

	private BlackboxDAO() {}
	
	/**
	 * Obtiene una lista de Blackboxes con información del interés de administrador
	 * @return lista de BlackboxAdminInfo
	 */
	public static List<BlackboxAdminInfo> getBlackboxes() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			BlackboxMapper mapper = sqlSession.getMapper(BlackboxMapper.class);
			return mapper.getBlackboxes();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Añade una blackbox a la base de datos
	 * @param blackbox BlackboxFullInfo con toda la información de la blackbox
	 */
	public static void addBlackbox(BlackboxFullInfo blackbox) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			BlackboxMapper mapper = sqlSession.getMapper(BlackboxMapper.class);
			mapper.addBlackbox(blackbox);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Borra una blackbox de la base de datos
	 * @param id Id interna de la base de datos
	 */
	public static void borrarBlackbox(int id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			BlackboxMapper mapper = sqlSession.getMapper(BlackboxMapper.class);
			mapper.borrarBlackbox(id);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Extrae de la base de datos una blackbox con datos de interés para el administrador
	 * @param id Id de la blackbox a extraer
	 * @return Objeto BlackboxAdminInfo de la blackbox pedida
	 */
	public static BlackboxAdminInfo getBlackbox(int id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			BlackboxMapper mapper = sqlSession.getMapper(BlackboxMapper.class);
			return mapper.getBlackboxPorId(id);
		} finally {
			sqlSession.close();
		}
	}
	
	/**
	 * Extrae de la base de datos una blackbox con datos de interés para el administrador
	 * @param idUnico Identificador único de la blackbox a extraer
	 * @return Objeto BlackboxAdminInfo de la blackbox pedida
	 */
	public static BlackboxAdminInfo getBlackbox(String idUnico) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			BlackboxMapper mapper = sqlSession.getMapper(BlackboxMapper.class);
			return mapper.getBlackboxPorIdUnico(idUnico);
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Edita una blackbox para cambiarle los datos
	 * @param id Identificador en la base de datos de la blackbox
	 * @param idUnico Identificador único de la blackbox que usa para identificarse
	 * @param passwd Contraseña de cifrado de la blackbox
	 * @param idUsuario Id del usuario en la base de datos propietario de la blackbox
	 */
	public static void editarBlackbox(int id, String idUnico, String passwd, int idUsuario) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			BlackboxMapper mapper = sqlSession.getMapper(BlackboxMapper.class);
			mapper.editarBlackbox(id, idUnico, passwd, idUsuario);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Modifica únicamente la contraseña de la blackbox
	 * @param id Identifiador en la base de datos a la blackbox
	 * @param passwd Nueva contraseña de la blackbox
	 */
	public static void cambiarPasswd(int id, String passwd) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			BlackboxMapper mapper = sqlSession.getMapper(BlackboxMapper.class);
			mapper.cambiarPasswd(id, passwd);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Devuelve el nombre que le ha dado el usuario al puerto
	 * @param id Id de la blackbox a la que extraer el nombre
	 * @param puerto Nombre del puerto: I0, I1, O0, O1...
	 * @return
	 */
	public static String getNombrePuerto(int id, String puerto) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			BlackboxMapper mapper = sqlSession.getMapper(BlackboxMapper.class);
			return mapper.getNombrePuerto(id, "nombre_" + puerto);
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Obtiene datos de una blackbox según su identificador único
	 * @param uid Identificador único de la blackbox
	 * @return Objeto BlackboxFullInfo con toda la información de la blackbox
	 */
	public static BlackboxFullInfo getBlackboxFullInfo(String uid) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			BlackboxMapper mapper = sqlSession.getMapper(BlackboxMapper.class);
			return mapper.getBlackboxFullInfo(uid);
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Edita una blackbox mediante un objeto BlackboxFullInfo con todas las modificaciones. El único dato que debe
	 * invariable es BlackboxFullInfo.id que identificará la blackbox del en la base de datos
	 * @param blackbox BlackboxFullInfo con todas las modificaciones
	 */
	public static void editarBlackbox(BlackboxFullInfo blackbox) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			BlackboxMapper mapper = sqlSession.getMapper(BlackboxMapper.class);
			mapper.editarBlackboxFullInfo(blackbox);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Obtiene las blackbox de un usuario
	 * @param id Id en la base de datos del usuario
	 * @return Retorna una lista de BlackboxAdminInfo con todas la blackbox que le pertenecen
	 */
	public static List<BlackboxAdminInfo> getDatabaseBlackboxesPorUsuario(Integer id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			BlackboxMapper mapper = sqlSession.getMapper(BlackboxMapper.class);
			return mapper.getDatabaseBlackboxesPorUsuario(id);
		} finally {
			sqlSession.close();
		}
	}
	
}
