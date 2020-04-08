package modelo.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import modelo.pojo.Blackbox;
import modelo.pojo.BlackboxAdminInfo;

public interface BlackboxMapper {

	List<BlackboxAdminInfo> getBlackboxes();

	void addBlackbox(@Param("idUnico") String id, @Param("passwd") String passwd, @Param("idUsuario") int idUsuario);

	void borrarBlackbox(int id);
	
	void editarBlackbox(@Param("id") int id, @Param("idUnico") String idUnico, @Param("passwd") String passwd, @Param("idUsuario") int idUsuario);

	BlackboxAdminInfo getBlackbox(int id);

}
