package modelo.dao.mapper;

import modelo.pojo.TokenNuevoPassword;

public interface TokenNuevoPasswordMapper {

	TokenNuevoPassword obtenerTokenDesdeEmail(String email);

}
