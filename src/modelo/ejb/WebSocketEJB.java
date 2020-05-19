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
public class WebSocketEJB {

	@EJB
	BlackboxEJB blackboxEJB;
	
	@EJB
	BlackboxBufferEJB blackboxBufferEJB;
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(WebSocketEJB.class);

	
	// private static Set<Session> sessions = new HashSet<>();
	// En el map está:
	//	Session: guarda información conocer el cliente
	//  String: guarda la uid de la blackbox
	private static Map<Session, String> sessions = new HashMap<>();
	
	@OnOpen
	public void onOpen(Session session) {
		sessions.put(session, null);
	}
	
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
							blackbox.setLimiteSuperiorI0((Integer) obj.get("valor"));
						} else if(umbral.equals("inf")) {
							blackbox.setLimiteInferiorI0((Integer) obj.get("valor"));
						}
						break;
					case "I1":
						if(umbral.equals("sup")) {
							blackbox.setLimiteSuperiorI1((Integer) obj.get("valor"));
						} else if(umbral.equals("inf")) {
							blackbox.setLimiteInferiorI1((Integer) obj.get("valor"));
						}
						break;
					case "I2":
						if(umbral.equals("sup")) {
							blackbox.setLimiteSuperiorI2((Integer) obj.get("valor"));
						} else if(umbral.equals("inf")) {
							blackbox.setLimiteInferiorI2((Integer) obj.get("valor"));
						}
						break;
					case "I3":
						if(umbral.equals("sup")) {
							blackbox.setLimiteSuperiorI3((Integer) obj.get("valor"));
						} else if(umbral.equals("inf")) {
							blackbox.setLimiteInferiorI3((Integer) obj.get("valor"));
						}
						break;
					}
					blackboxBufferEJB.insertar(blackbox);
					break;
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@OnClose
	public void onClose(Session session) {
		sessions.remove(session);
	}
	
	@OnError
	public void onError(Throwable e) {
		
	}
	
	public void enviarData(IOPort infoPuertos) {
		// Obtener blackbox
		Session session = null;
		BlackboxAdminInfo blackbox = blackboxEJB.getBlackbox(infoPuertos.getIdBlackbox());
		if(blackbox != null) {
			String uid = blackbox.getIdentificador();
			if(sessions.containsValue(blackbox.getIdentificador())) {
				session = sessions.entrySet()
									.stream()
									.filter(entry -> uid.equals(entry.getValue()))
									.findFirst().get().getKey();
			}
		}
		if(session != null) {
			String mensaje = getJson(infoPuertos);
			try {
				session.getBasicRemote().sendText(mensaje, true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
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
