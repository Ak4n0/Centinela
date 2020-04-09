package modelo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mapper.UsuarioMapper;
import modelo.pojo.PeticionNuevoUsuario;
import modelo.pojo.UsuarioAdminInfo;
import modelo.pojo.UsuarioFullInfo;

public class UsuarioDAO {

	private UsuarioDAO() {}
	
	public static UsuarioFullInfo getUsuario(UsuarioFullInfo usuario) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper mapper = sqlSession.getMapper(UsuarioMapper.class);
			return mapper.getUsuario(usuario);
		} finally {
			sqlSession.close();
		}
	}
	
	public static boolean existeUsuario(String email) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper mapper = sqlSession.getMapper(UsuarioMapper.class);
			return mapper.existeUsuario(email);
		} finally {
			sqlSession.close();
		}
	}

	public static String getNombreUsuario(String email) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper mapper = sqlSession.getMapper(UsuarioMapper.class);
			return mapper.getNombreUsuario(email);
		} finally {
			sqlSession.close();
		}
	}

	public static Integer getIdDesdeEmail(String email) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper mapper = sqlSession.getMapper(UsuarioMapper.class);
			return mapper.getIdDesdeEmail(email);
		} finally {
			sqlSession.close();
		}
	}

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

	public static List<UsuarioAdminInfo> getListaUsuariosParaAdministracion() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper mapper = sqlSession.getMapper(UsuarioMapper.class);
			return mapper.getListaUsuariosParaAdministracion();
		} finally {
			sqlSession.close();
		}
	}

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

	public static boolean existeClaveUsuarioNuevo(String clave) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper mapper = sqlSession.getMapper(UsuarioMapper.class);
			return mapper.existeClaveUsuarioNuevo(clave);
		} finally {
			sqlSession.close();
		}
	}

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

	public static List<PeticionNuevoUsuario> getPeticionesNuevoUsuario() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper mapper = sqlSession.getMapper(UsuarioMapper.class);
			return mapper.getPeticionesNuevoUsuario();
		} finally {
			sqlSession.close();
		}
	}

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

	public static List<UsuarioAdminInfo> getUsuariosValidados() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper mapper = sqlSession.getMapper(UsuarioMapper.class);
			return mapper.getUsuariosValidados();
		} finally {
			sqlSession.close();
		}
	}

	public static UsuarioFullInfo getUsuario(int id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper mapper = sqlSession.getMapper(UsuarioMapper.class);
			return mapper.getUsuarioPorId(id);
		} finally {
			sqlSession.close();
		}
	}
}
