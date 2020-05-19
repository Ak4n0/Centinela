package modelo.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.BlackboxBufferDAO;
import modelo.pojo.BlackboxBuffer;

@LocalBean
@Stateless
public class BlackboxBufferEJB {

	public void insertar(BlackboxBuffer blackbox) {
		BlackboxBufferDAO.insertar(blackbox);
	}
	
	public BlackboxBuffer extraer(String identificadorUnico) {
		return BlackboxBufferDAO.extraer(identificadorUnico);
	}
}
