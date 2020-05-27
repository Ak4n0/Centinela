package modelo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mapper.IOPortMapper;
import modelo.pojo.IOPort;

/**
 * Maneja la tabla IOPort de la base de datos
 * @author mique
 *
 */
public class IOPortDAO {

	private IOPortDAO() {}

	/**
	 * Añade una nueva entrada con los datos de los puertos de una blackbox
	 * @param ioport Objeto IOPort rellenado con datos E/S
	 */
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

	/**
	 * Saca una lista sobre los datos E/S de una blackbox
	 * @param id Identificador en la base de datos de una blackbox
	 * @return Lista de objetos IOPort
	 */
	public static List<IOPort> getIOPorts(int id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			IOPortMapper mapper = sqlSession.getMapper(IOPortMapper.class);
			return mapper.getIOPorts(id);
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Devuelve la última información E/S introducida de una blackbox
	 * @param id Identificador en la base de datos 
	 * @return Un objeto IOPort con información E/S
	 */
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
