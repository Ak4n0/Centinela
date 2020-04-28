package modelo.dao;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mapper.IOPortMapper;
import modelo.pojo.IOPort;

public class IOPortDAO {

	private IOPortDAO() {}

	public static void addIOPortEntry(IOPort ioport) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			IOPortMapper mapper = sqlSession.getMapper(IOPortMapper.class);
			mapper.addIOPortEntry(ioport);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}
	
	
}
