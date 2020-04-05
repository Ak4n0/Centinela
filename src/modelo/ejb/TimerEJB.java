package modelo.ejb;

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
	@Schedule(second="0", minute="*/5", hour="*", info="Operacion de limpieza")
    private void eliminarUsuariosNoValidados(final Timer t) {
        List<PeticionNuevoUsuario> lista = operacionesUsuario.getNewUserPetitions();
		for(PeticionNuevoUsuario peticion: lista) {
			operacionesUsuario.removeDatabaseUser(peticion.getIdUsuario());
		}
    }
}