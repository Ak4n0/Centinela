package modelo.ejb;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

import modelo.pojo.PeticionNuevoUsuario;
import modelo.pojo.TokenNuevoPassword;

@Stateless
@LocalBean
/**
 * Temporizador interno para trabajos automáticos
 * @author mique
 *
 */
public class TimerEJB {

    @EJB
    private UsuariosEJB operacionesUsuario;
    
    @EJB
    private TokenNuevoPasswordEJB tokenNuevoPassword;
	
    /**
     * Borra entradas caducadas de la de la base de datos
     */
	@Schedule(second="*", minute="*/10", hour="*")
    private void eliminarUsuariosNoValidados() {
		// Borra usuarios que no se han validad en un periodo de 24 horas
        List<PeticionNuevoUsuario> listaUsuarios = operacionesUsuario.getNewUserPetitions();
		for(PeticionNuevoUsuario peticion: listaUsuarios) {
			Date ahora = new Date();
			Calendar fechaPeticion = Calendar.getInstance();
			fechaPeticion.setTime(peticion.getFechaHora());
			fechaPeticion.add(Calendar.DATE, 1);
			if(ahora.after(fechaPeticion.getTime())) {
				operacionesUsuario.removeDatabaseUser(peticion.getIdUsuario());
			}
		}
		
		// Borra peticiones de cambio de password en un periodo de 1 hora
		List<TokenNuevoPassword> listaPeticiones = tokenNuevoPassword.getListaTokens();
		for(TokenNuevoPassword peticion: listaPeticiones) {
			Date ahora = new Date();
			Calendar fechaPeticion = Calendar.getInstance();
			fechaPeticion.setTime(peticion.getFechaHora());
			fechaPeticion.add(Calendar.HOUR, 1);
			if(ahora.after(fechaPeticion.getTime())) {
				tokenNuevoPassword.deleteToken(peticion.getId());
			}
		}
    }
}