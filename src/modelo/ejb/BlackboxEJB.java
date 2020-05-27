package modelo.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.AlarmaDAO;
import modelo.dao.BlackboxDAO;
import modelo.dao.IOPortDAO;
import modelo.pojo.Alarma;
import modelo.pojo.BlackboxFullInfo;
import modelo.pojo.BlackboxAdminInfo;
import modelo.pojo.IOPort;

@Stateless
@LocalBean
/**
 * Maneja la persistencia relacionada con las blackbox
 * @author mique
 *
 */
public class BlackboxEJB {

	/**
	 * Devuelve una lista de blackboxes con información interesante para el administrador
	 * @return Lista de objetos BlackboxAdminInfo
	 */
	public List<BlackboxAdminInfo> getDatabaseBlackboxes() {
		return BlackboxDAO.getBlackboxes();
	}

	/**
	 * Añade una blackbox
	 * @param blackbox Objeto BlackboxFullInfo con toda la información del equipo
	 */
	public void addBlackbox(BlackboxFullInfo blackbox) {
		BlackboxDAO.addBlackbox(blackbox);
	}

	/**
	 * Borra una blackbox
	 * @param id Id interno en el sistema de permanencia de la blackbox
	 */
	public void deleteBlackbox(int id) {
		BlackboxDAO.borrarBlackbox(id);
	}

	/**
	 * Ombtiene los datos de una blackbox de interés del administrador a partir de un id interno de la blackbox
	 * @param id Id interno en el sistema de permanencia de la blackbox
	 * @return Devuelve un objeto BlackboxAdminInfo
	 */
	public BlackboxAdminInfo getBlackbox(int id) {
		return BlackboxDAO.getBlackbox(id);
	}
	
	/**
	 * Ombtiene los datos de una blackbox de interés del administrador a partir del identificador único de la blackbox
	 * @param idUnico
	 * @return
	 */
	public BlackboxAdminInfo getBlackbox(String idUnico) {
		return BlackboxDAO.getBlackbox(idUnico);
	}

	/**
	 * Edita una blackbox para cambiarle los datos
	 * @param id Identificador interno del sistema de persistencia de la blackbox
	 * @param idUnico Identificador único de la blackbox
	 * @param passwd Nuevo password de la blackbox
	 * @param idUsuario Identificador del usuario propietario de la blackbox
	 */
	public void editBlackbox(int id, String idUnico, String passwd, int idUsuario) {
		BlackboxDAO.editarBlackbox(id, idUnico, passwd, idUsuario);
	}

	/**
	 * Añade informació E/S de la blackbox
	 * @param ioport Objeto IOPort del con la información de los puertos
	 */
	public void addIOPortEntry(IOPort ioport) {
		IOPortDAO.addIOPortEntry(ioport);
	}

	/**
	 * Añade una alarma que haya hecho saltar el sistema blackbox
	 * @param alarma Objeto Alarma con la informaciónuna alarma
	 */
	public void addAlarma(Alarma alarma) {
		AlarmaDAO.addAlarma(alarma);
	}

	/**
	 * Realiza un simple de contraseña de una blackbox
	 * @param id Identificador interno del sistema de persistencia
	 * @param passwd Nuevo password a guardar
	 */
	public void cambiarPasswd(int id, String passwd) {
		BlackboxDAO.cambiarPasswd(id, passwd);
	}

	/**
	 * Obtiene el nombre un puerto de la blackbox
	 * @param id Identificador interno del sistema de persistencia
	 * @param puerto Nombre del puerto: I0, I1, I2, O0, O1, O2...
	 * @return Cadena con el nombre del puerto de la blackbox
	 */
	public String getPortName(int id, String puerto) {
		return BlackboxDAO.getNombrePuerto(id, puerto);
	}

	/**
	 * Obtiene la información completa de una blackbox a partir del identificador único
	 * @param uid Cadena con el identificador único
	 * @return Objeto BlackboxFullInfo con toda la información de una blackbox
	 */
	public BlackboxFullInfo getBlackboxFullInfo(String uid) {
		return BlackboxDAO.getBlackboxFullInfo(uid);
	}

	/**
	 * Obtiene una lista con todos los datos E/S de una blackbox
	 * @param id Identificador interno del sistema del sistema de persistencia
	 * @return Lista de objetos IOPort
	 */
	public List<IOPort> getIOPorts(int id) {
		return IOPortDAO.getIOPorts(id);
	}

	/**
	 * Edita los datos de una blackbox. Éstos son entregados a través de un objeto BlakcboxFullInfo con todos los cambios.
	 * Debe permanecer intacto el id para referenciar a la blackbox que se debe cambiar
	 * @param blackbox Objeto BlakcboxFullInfo con todos los datos que se van a modificar
	 */
	public void editBlackbox(BlackboxFullInfo blackbox) {
		BlackboxDAO.editarBlackbox(blackbox);
	}

	/**
	 * Obtiene la última E/S de una blackbox
	 * @param id Identificador interno del sistema de persistencia de la blackbox
	 * @return Objeto IOPort con inofrmación E/S
	 */
	public IOPort getLastIO(int id) {
		return IOPortDAO.getLastIO(id);
	}
	
}
