package modelo.dao;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mapper.TokenNuevoPasswordMapper;
import modelo.pojo.TokenNuevoPassword;

public class TokenNuevoPasswordDAO {

	private TokenNuevoPasswordDAO() {}

	public static TokenNuevoPassword obtenerTokenDesdeEmail(String email) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			TokenNuevoPasswordMapper mapper = sqlSession.getMapper(TokenNuevoPasswordMapper.class);
			return mapper.obtenerTokenDesdeEmail(email);
		} finally {
			sqlSession.close();
		}
	}
}