package modelo.dao;

import java.util.Hashtable;
import java.util.Map;

import modelo.pojo.BlackboxBuffer;

/**
 * Manja el buffer de cambios de la blackbox
 * @author mique
 *
 */
public class BlackboxBufferDAO {

	private BlackboxBufferDAO() {}
	
	private static Map<String, BlackboxBuffer> pool = new Hashtable<String, BlackboxBuffer>();
	
	/**
	 * Añade una nueva blackbox al buffer
	 * @param blackbox BlackboxBuffer con los cambios que debe enviar a una blackbox
	 */
	static public void insertar(BlackboxBuffer blackbox) {
		pool.put(blackbox.getIdentificador(), blackbox);
	}
	
	/**
	 * Extrae y borra información de modificacion de una blackbox del buffer
	 * @param identificadorUnico Identificador único de la blackbox
	 * @return Objecto BlackboxBuffer con los cambios necesarios
	 */
	static public BlackboxBuffer extraer(String identificadorUnico) {
		return pool.remove(identificadorUnico);
	}
}
