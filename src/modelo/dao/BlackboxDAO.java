package modelo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mapper.BlackboxMapper;
import modelo.pojo.Blackbox;
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
			return mapper.getBlackbox(id);
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
}
