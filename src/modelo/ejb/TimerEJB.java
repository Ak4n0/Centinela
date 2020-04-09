package modelo.ejb;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.Timer;

import modelo.pojo.PeticionNuevoUsuario;

@Stateless
public class TimerEJB {

    @EJB
    OperacionesUsuariosEJB operacionesUsuario;
	
	@SuppressWarnings("unused")
	@Schedule(second="0", minute="*/10", hour="*")
    private void eliminarUsuariosNoValidados(final Timer t) {
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