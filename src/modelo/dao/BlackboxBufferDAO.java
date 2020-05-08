package modelo.dao;

import java.util.Hashtable;
import java.util.Map;

import modelo.pojo.BlackboxBuffer;

public class BlackboxBufferDAO {

private static Map<String, BlackboxBuffer> pool = new Hashtable<String, BlackboxBuffer>();
	
	static public void insertar(BlackboxBuffer blackbox) {
		pool.put(blackbox.getIdentificador(), blackbox);
	}
	
	static public BlackboxBuffer extraer(String identificadorUnico) {
		return pool.remove(identificadorUnico);
	}
}
