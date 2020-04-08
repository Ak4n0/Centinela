package modelo.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.dao.BlackboxDAO;
import modelo.pojo.Blackbox;
import modelo.pojo.BlackboxAdminInfo;

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

	public void editBlackbox(int id, String idUnico, String passwd, int idUsuario) {
		BlackboxDAO.editarBlackbox(id, idUnico, passwd, idUsuario);
	}
	
}
