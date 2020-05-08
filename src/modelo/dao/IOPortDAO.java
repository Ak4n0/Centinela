package modelo.dao;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mapper.IOPortMapper;
import modelo.pojo.IOPort;

public class IOPortDAO {

	private IOPortDAO() {}

	public static void addIOPortEntry(IOPort ioport) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			System.out.println("Valores IO: I0 (" + ioport.getI0() + "), I1 (" + ioport.getI1() + "), I2 (" + ioport.getI2() + "), I3 (" + ioport.getI0() + ")");
			IOPortMapper mapper = sqlSession.getMapper(IOPortMapper.class);
			mapper.addIOPortEntry(ioport);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}
	
	
}
