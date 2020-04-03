package modelo.dao.mapper;

import java.util.List;

import modelo.pojo.Blackbox;
import modelo.pojo.BlackboxAdminInfo;

public interface BlackboxMapper {

	List<BlackboxAdminInfo> getBlackboxes();

}
