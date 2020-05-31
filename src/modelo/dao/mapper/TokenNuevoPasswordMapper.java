package modelo.dao.mapper;

import java.util.List;

import modelo.pojo.TokenNuevoPassword;

/**
 * Interface del mapper
 * @author mique
 *
 */
public interface TokenNuevoPasswordMapper {

	TokenNuevoPassword obtenerTokenDesdeEmail(String email);

	void insertarToken(TokenNuevoPassword tokenNuevoPassword);

	boolean existeToken(String token);

	List<TokenNuevoPassword> getListaTokens();

	void borrarToken(String id);

	TokenNuevoPassword obtenerTokenDesdeId(String id);

}
