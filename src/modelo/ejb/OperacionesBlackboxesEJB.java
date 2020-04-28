package modelo.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.AlarmaDAO;
import modelo.dao.BlackboxDAO;
import modelo.dao.IOPortDAO;
import modelo.pojo.Alarma;
import modelo.pojo.BlackboxAdminInfo;
import modelo.pojo.IOPort;

@Stateless
@LocalBean
public class OperacionesBlackboxesEJB {

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

	public void actualizarIP(Integer idBlackbox, String ip) {
		BlackboxDAO.actualizarIP(idBlackbox, ip);
	}
	
}
