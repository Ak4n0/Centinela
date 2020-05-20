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
public class BlackboxEJB {

	public List<BlackboxAdminInfo> getDatabaseBlackboxes() {
		return BlackboxDAO.getBlackboxes();
	}

	public void addBlackbox(String id, String passwd, int idUsuario) {
		BlackboxDAO.addBlackbox(id, passwd, idUsuario);
	}

	public void deleteBlackbox(int id) {
		BlackboxDAO.borrarBlackbox(id);
	}

	public BlackboxAdminInfo getBlackbox(int id) {
		return BlackboxDAO.getBlackbox(id);
	}
	
	public BlackboxAdminInfo getBlackbox(String idUnico) {
		return BlackboxDAO.getBlackbox(idUnico);
	}

	public void editBlackbox(int id, String idUnico, String passwd, int idUsuario) {
		BlackboxDAO.editarBlackbox(id, idUnico, passwd, idUsuario);
	}

	public void addIOPortEntry(IOPort ioport) {
		IOPortDAO.addIOPortEntry(ioport);
	}

	public void addAlarma(Alarma alarma) {
		AlarmaDAO.addAlarma(alarma);
	}

	public void cambiarPasswd(int id, String passwd) {
		BlackboxDAO.cambiarPasswd(id, passwd);
	}

	public String getPortName(int id, String puerto) {
		return BlackboxDAO.getNombrePuerto(id, puerto);
	}

	public BlackboxFullInfo getBlackboxFullInfo(String uid) {
		return BlackboxDAO.getBlackboxFullInfo(uid);
	}

	public List<IOPort> getIOPorts(int id) {
		return IOPortDAO.getIOPorts(id);
	}

	public void editBlackbox(BlackboxFullInfo blackbox) {
		BlackboxDAO.editarBlackbox(blackbox);
		
	}
	
}
