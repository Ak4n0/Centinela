package modelo.dao;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mapper.AlarmaMapper;
import modelo.pojo.Alarma;

public class AlarmaDAO {

	public static void addAlarma(Alarma alarma) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			AlarmaMapper mapper = sqlSession.getMapper(AlarmaMapper.class);
			mapper.addAlarma(alarma);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}
	
}
