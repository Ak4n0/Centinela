package modelo.dao;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mapper.TokenNuevoPasswordMapper;
import modelo.pojo.TokenNuevoPassword;

/**
 * Maneja la tabla de tokens para los nuevos usuarios
 * @author mique
 *
 */
public class TokenNuevoPasswordDAO {

	private TokenNuevoPasswordDAO() {}

	/**
	 * Obtiene el token de la base de datos según el e-mail del usuario
	 * @param email Dirección e-mail del usuario
	 * @return TokenNuevoPassword con la información del token
	 */
	public static TokenNuevoPassword obtenerTokenDesdeEmail(String email) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			TokenNuevoPasswordMapper mapper = sqlSession.getMapper(TokenNuevoPasswordMapper.class);
			return mapper.obtenerTokenDesdeEmail(email);
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Inserta un nuevo token en la base de datos. Informa del éxito de la operación
	 * @param tokenNuevoPassword Objeto TokenNuevoPassword
	 * @return Devuelve true si tuvo éxito, false en caso contrario
	 */
	public static boolean insertarToken(TokenNuevoPassword tokenNuevoPassword) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		boolean retValue = false;
		try {
			TokenNuevoPasswordMapper mapper = sqlSession.getMapper(TokenNuevoPasswordMapper.class);
			mapper.insertarToken(tokenNuevoPassword);
			sqlSession.commit();
			retValue = true;
		} finally {
			sqlSession.close();
		}
		return retValue;
	}
}
