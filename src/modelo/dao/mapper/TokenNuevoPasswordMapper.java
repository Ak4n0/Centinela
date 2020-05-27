package modelo.dao.mapper;

import modelo.pojo.TokenNuevoPassword;

/**
 * Interface del mapper
 * @author mique
 *
 */
public interface TokenNuevoPasswordMapper {

	TokenNuevoPassword obtenerTokenDesdeEmail(String email);

	void insertarToken(TokenNuevoPassword tokenNuevoPassword);

}
