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
}
