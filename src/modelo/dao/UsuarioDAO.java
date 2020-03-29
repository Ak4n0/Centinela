package modelo.dao;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mapper.UsuarioMapper;
import modelo.pojo.Usuario;

public class UsuarioDAO {

	private UsuarioDAO() {}
	
	public static Usuario getUsuario(Usuario usuario) {
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
}
