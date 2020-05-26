package modelo.dao;

import java.util.List;

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

	public static List<IOPort> getIOPorts(int id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			IOPortMapper mapper = sqlSession.getMapper(IOPortMapper.class);
			return mapper.getIOPorts(id);
		} finally {
			sqlSession.close();
		}
	}

	public static IOPort getLastIO(int id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			IOPortMapper mapper = sqlSession.getMapper(IOPortMapper.class);
			return mapper.getLastIO(id);
		} finally {
			sqlSession.close();
		}
		
	}
	
	
}
