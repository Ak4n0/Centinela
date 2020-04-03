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

	
}
