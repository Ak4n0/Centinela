package modelo.ejb;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.Timer;

import modelo.pojo.PeticionNuevoUsuario;

@Stateless
@LocalBean
/**
 * Temporizador interno para trabajos automáticos
 * @author mique
 *
 */
public class TimerEJB {

    @EJB
    UsuariosEJB operacionesUsuario;
	
    /**
     * Borra usuarios que no han validado su cuenta
     */
	@Schedule(second="*", minute="*/10", hour="*")
    private void eliminarUsuariosNoValidados() {
        List<PeticionNuevoUsuario> lista = operacionesUsuario.getNewUserPetitions();
		for(PeticionNuevoUsuario peticion: lista) {
			Date ahora = new Date();
			Calendar fechaPeticion = Calendar.getInstance();
			fechaPeticion.setTime(peticion.getFechaHora());
			fechaPeticion.add(Calendar.DATE, 1);
			if(ahora.after(fechaPeticion.getTime())) {
				operacionesUsuario.removeDatabaseUser(peticion.getIdUsuario());
			}
		}
    }
}