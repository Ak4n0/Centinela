package modelo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mapper.BlackboxMapper;
import modelo.pojo.BlackboxFullInfo;
import modelo.pojo.BlackboxAdminInfo;

public class BlackboxDAO {

	private BlackboxDAO() {}
	
	public static List<BlackboxAdminInfo> getBlackboxes() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			BlackboxMapper mapper = sqlSession.getMapper(BlackboxMapper.class);
			return mapper.getBlackboxes();
		} finally {
			sqlSession.close();
		}
	}

	public static void addBlackbox(String id, String passwd, int idUsuario) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			BlackboxMapper mapper = sqlSession.getMapper(BlackboxMapper.class);
			mapper.addBlackbox(id, passwd, idUsuario);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

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

	public static BlackboxAdminInfo getBlackbox(int id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			BlackboxMapper mapper = sqlSession.getMapper(BlackboxMapper.class);
			return mapper.getBlackboxPorId(id);
		} finally {
			sqlSession.close();
		}
	}
	
	public static BlackboxAdminInfo getBlackbox(String idUnico) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			BlackboxMapper mapper = sqlSession.getMapper(BlackboxMapper.class);
			return mapper.getBlackboxPorIdUnico(idUnico);
		} finally {
			sqlSession.close();
		}
	}

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

	public static String getNombrePuerto(int id, String puerto) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			BlackboxMapper mapper = sqlSession.getMapper(BlackboxMapper.class);
			return mapper.getNombrePuerto(id, "nombre_" + puerto);
		} finally {
			sqlSession.close();
		}
	}

	public static BlackboxFullInfo getBlackboxFullInfo(String uid) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			BlackboxMapper mapper = sqlSession.getMapper(BlackboxMapper.class);
			return mapper.getBlackboxFullInfo(uid);
		} finally {
			sqlSession.close();
		}
	}
	
}
