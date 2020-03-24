package modelo.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.TokenNuevoPasswordDAO;
import modelo.pojo.TokenNuevoPassword;

@LocalBean
@Stateless
public class OperacionesTokensNuevoPasswordEJB {

	public TokenNuevoPassword getTokenFromEmail(String email) {
		return TokenNuevoPasswordDAO.obtenerTokenDesdeEmail(email);
	}
	
}
