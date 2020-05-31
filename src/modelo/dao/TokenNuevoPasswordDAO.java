package modelo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mapper.TokenNuevoPasswordMapper;
import modelo.pojo.TokenNuevoPassword;

/**
 * Maneja la tabla de tokens de petición de nuevo password
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

	/**
	 * Comprueba si un token de petición de nuevo password existe en la base de datos.
	 * @param token Token a buscar
	 * @return Devuelve true si existe, false en caso contrario
	 */
	public static boolean existeToken(String token) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			TokenNuevoPasswordMapper mapper = sqlSession.getMapper(TokenNuevoPasswordMapper.class);
			return mapper.existeToken(token);
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Obtiene una lista de todos los tokens activos
	 * @return Lista de objetos TokenNuevoPassword
	 */
	public static List<TokenNuevoPassword> getListaTokens() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			TokenNuevoPasswordMapper mapper = sqlSession.getMapper(TokenNuevoPasswordMapper.class);
			return mapper.getListaTokens();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Borra un token en la base de datos
	 * @param id Id del token a borrar
	 */
	public static void borrarToken(String id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			TokenNuevoPasswordMapper mapper = sqlSession.getMapper(TokenNuevoPasswordMapper.class);
			mapper.borrarToken(id);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Obtiene toda la información de un token a partir de su Id
	 * @param id Id del token a obtener
	 * @return Devuelve un TokenNuevoPassword con toda la información rellenada
	 */
	public static TokenNuevoPassword obtenerTokenDesdeId(String id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			TokenNuevoPasswordMapper mapper = sqlSession.getMapper(TokenNuevoPasswordMapper.class);
			return mapper.obtenerTokenDesdeId(id);
		} finally {
			sqlSession.close();
		}
	}
}
