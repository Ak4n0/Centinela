package modelo.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import modelo.pojo.BlackboxFullInfo;
import modelo.pojo.BlackboxAdminInfo;

public interface BlackboxMapper {

	List<BlackboxAdminInfo> getBlackboxes();

	void addBlackbox(@Param("idUnico") String id, @Param("passwd") String passwd, @Param("idUsuario") int idUsuario);

	void borrarBlackbox(int id);
	
	void editarBlackbox(@Param("id") int id, @Param("idUnico") String idUnico, @Param("passwd") String passwd, @Param("idUsuario") int idUsuario);

	BlackboxAdminInfo getBlackboxPorId(int id);

	BlackboxAdminInfo getBlackboxPorIdUnico(String idUnico);

	void cambiarPasswd(@Param("id") int id, @Param("passwd") String passwd);

	String getNombrePuerto(@Param("id") int id, @Param("puerto") String puerto);

	BlackboxFullInfo getBlackboxFullInfo(String uid); 

}
