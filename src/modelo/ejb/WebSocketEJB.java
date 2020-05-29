package modelo.ejb;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.jose4j.json.internal.json_simple.JSONObject;
import org.jose4j.json.internal.json_simple.parser.JSONParser;
import org.jose4j.json.internal.json_simple.parser.ParseException;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import modelo.pojo.BlackboxAdminInfo;
import modelo.pojo.BlackboxBuffer;
import modelo.pojo.IOPort;

@Singleton
@ApplicationScoped
@ServerEndpoint("/ws")
/**
 * Manejo de las funcionalidades de websocket
 * @author mique
 *
 */
public class WebSocketEJB {

	@EJB
	BlackboxEJB blackboxEJB;
	
	@EJB
	BlackboxBufferEJB blackboxBufferEJB;
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(WebSocketEJB.class);

	/**
	 * private static Set<Session> sessions = new HashSet<>();
	 * En el map está:
	 *	Session: guarda información conocer el cliente
	 *  String: guarda la uid de la blackbox
	 */
	private static Map<Session, String> sessions = new HashMap<>();
	
	/**
	 * Guarda una sesión ws
	 * @param session Sesión ws
	 */
	@OnOpen
	public void onOpen(Session session) {
		sessions.put(session, null);
	}
	
	/**
	 * Trata los mensajes que llegan a través del websocket
	 * @param message Mensaje que ha llegado
	 * @param session Sesión del cliente
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		JSONParser parser = new JSONParser();
		if(sessions.containsKey(session)) {
			try {
				JSONObject obj = (JSONObject) parser.parse(message);
				String operacion = (String) obj.get("op");
				String uid;
				String port;
				BlackboxBuffer blackbox;
				switch(operacion) {
				case "conn":
					uid = (String) obj.get("uid");
					sessions.replace(session, uid);
					break;
				case "outp":
					port = (String) obj.get("port");
					uid = (String) sessions.get(session);
					blackbox = blackboxBufferEJB.extraer(uid);
					if(blackbox == null) {
						blackbox = new BlackboxBuffer();
						blackbox.setIdentificador(uid);
					}
					switch(port) {
					case "O0":
						blackbox.setSalidaO0((boolean) obj.get("valor"));
						break;
					case "O1":
						blackbox.setSalidaO1((boolean) obj.get("valor"));
						break;
					case "O2":
						blackbox.setSalidaO2((boolean) obj.get("valor"));
						break;
					case "O3":
						blackbox.setSalidaO3((boolean) obj.get("valor"));
						break;
					}
					blackboxBufferEJB.insertar(blackbox);
					break;
				case "limit":
					port = (String) obj.get("port");
					uid = (String) sessions.get(session);
					blackbox = blackboxBufferEJB.extraer(uid);
					String umbral = (String) obj.get("umbral");
					if(blackbox == null) {
						blackbox = new BlackboxBuffer();
						blackbox.setIdentificador(uid);
					}
					switch(port) {
					case "I0":
						if(umbral.equals("sup")) {
							blackbox.setUmbralSuperiorI0((Integer) obj.get("valor"));
						} else if(umbral.equals("inf")) {
							blackbox.setUmbralInferiorI0((Integer) obj.get("valor"));
						}
						break;
					case "I1":
						if(umbral.equals("sup")) {
							blackbox.setUmbralSuperiorI1((Integer) obj.get("valor"));
						} else if(umbral.equals("inf")) {
							blackbox.setUmbralInferiorI1((Integer) obj.get("valor"));
						}
						break;
					case "I2":
						if(umbral.equals("sup")) {
							blackbox.setUmbralSuperiorI2((Integer) obj.get("valor"));
						} else if(umbral.equals("inf")) {
							blackbox.setUmbralInferiorI2((Integer) obj.get("valor"));
						}
						break;
					case "I3":
						if(umbral.equals("sup")) {
							blackbox.setUmbralSuperiorI3((Integer) obj.get("valor"));
						} else if(umbral.equals("inf")) {
							blackbox.setUmbralInferiorI3((Integer) obj.get("valor"));
						}
						break;
					}
					blackboxBufferEJB.insertar(blackbox);
					break;
				}
			} catch (ParseException e) {
				logger.error("No se pudo parsear: " + e.getMessage());
			}
		}
		
	}

	/**
	 * Al cerrar la sesión se elimina el cliente
	 * @param session
	 */
	@OnClose
	public void onClose(Session session) {
		sessions.remove(session);
	}
	
	@OnError
	public void onError(Throwable e) {
		logger.error("Ocurrió un error durante una transmisión websocket: " + e.getMessage());
	}
	
	/**
	 * Envia información de los puertos de la blackbox a su cliente
	 * @param infoPuertos Objeto IOPort con la información de puertos que se quiere transmitir
	 */
	public void enviarData(IOPort infoPuertos) {
		Session session = null;
		// Obtener blackbox a la que pertenecen los puertos
		BlackboxAdminInfo blackbox = blackboxEJB.getBlackbox(infoPuertos.getIdBlackbox());
		// Si la blackbox existe obtener la sesión del usuario a la que pertenece 
		if(blackbox != null) {
			String uid = blackbox.getIdentificador();
			if(sessions.containsValue(blackbox.getIdentificador())) {
				session = sessions.entrySet()
									.stream()
									.filter(entry -> uid.equals(entry.getValue()))
									.findFirst().get().getKey();
			}
		}
		// Si la sesión existe enviar el información
		if(session != null) {
			String mensaje = getJson(infoPuertos);
			try {
				session.getBasicRemote().sendText(mensaje, true);
			} catch (IOException e) {
				logger.error("No se pudo enviar la información por websocket: " + e.getMessage());
			}
		}
	}

	@SuppressWarnings("unchecked")
	/**
	 * Crea un Json a partir de un objeto IOPort
	 * @param io Objeto IOPort con información de los puertos
	 * @return Objeto Json convertido a cadena con información de de E/S
	 */
	private String getJson(IOPort io) {
		JSONObject obj = new JSONObject();
		obj.put("I0", io.getI0());
		obj.put("I1", io.getI1());
		obj.put("I2", io.getI2());
		obj.put("I3", io.getI3());
		obj.put("O0", io.getI0());
		obj.put("O1", io.getO1());
		obj.put("O2", io.getO2());
		obj.put("O3", io.getO3());
		obj.put("fecha", io.getFechaHora().getTime());
		return obj.toString();
	}
}
