package modelo.dao;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mapper.UsuarioMapper;
import modelo.pojo.Usuario;

public class UsuarioDAO {

	private UsuarioDAO() {}
	
	public static Usuario getUsuario(Usuario usuario) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuarioMapper mapper = sqlSession.getMapper(UsuarioMapper.class);
			return mapper.getUsuario(usuario);
		} finally {
			sqlSession.close();
		}
	}
}
