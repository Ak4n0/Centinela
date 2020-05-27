package modelo.dao;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mapper.AlarmaMapper;
import modelo.pojo.Alarma;

/**
 * Maneja las la entidad Alarma en la base de datos
 * @author mique
 *
 */
public class AlarmaDAO {

	/**
	 * Añade una alarma a la bbdd
	 * @param alarma Objeto Alarma con datos que hicieron disparar la alarma
	 */
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
