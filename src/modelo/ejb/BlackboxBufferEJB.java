package modelo.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.BlackboxBufferDAO;
import modelo.pojo.BlackboxBuffer;

@LocalBean
@Stateless
/**
 * Guarda y extrae información de las modificaciones de la blackbox
 * @author mique
 *
 */
public class BlackboxBufferEJB {

	/**
	 * Añade una nueva blackbox al buffer
	 * @param blackbox BlackboxBuffer con los cambios que debe enviar a una blackbox
	 */
	public void insertar(BlackboxBuffer blackbox) {
		BlackboxBufferDAO.insertar(blackbox);
	}
	
	/**
	 * Extrae y borra una blackbox del buffer
	 * @param identificadorUnico Identificador único de la blackbox
	 * @return Objecto BlackboxBuffer con los cambios necesarios
	 */
	public BlackboxBuffer extraer(String identificadorUnico) {
		return BlackboxBufferDAO.extraer(identificadorUnico);
	}
}
