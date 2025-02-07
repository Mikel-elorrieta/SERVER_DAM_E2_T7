package Server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import modelo.Horarios;
import modelo.Ikastetxe;
import modelo.Matriculaciones;
import modelo.Reuniones;
import modelo.Users;
import modelo.VistaHorariosUsuarios;

public class ServerGeneral {

	private static final String REGEX = "/";
	private static final String SRC_ELORSON_JSON = "src/elorson.json";
	private static final String GET_ALL_IKASTETXEAK = "getAllIkastetxeak";
	private static final String UPDATE_STATUS = "updateStatus";
	private static final String GET_MATRICULACION_BY_USER_ID = "getMatriculacionByUserId";
	private static final String GET_ALL_TEACHERS = "getAllTeachers";
	private static final String GET_BILERAK_BY_ALUMNO_ID = "getBilerakByAlumnoId";
	private static final String GET_BILERAK_BY_PROFESOR_ID = "getBilerakByProfesorId";
	private static final String FORGOT_PASSWORD = "forgotPassword";
	private static final String IS_LOGIN_OK = "isLoginOk";
	private static final String GET_HORARIOS_BY_ALUMNO_ID = "getHorariosByAlumnoId";
	private static final String GET_HORARIOS_BY_PROFE_ID = "getHorariosByProfeId";
	private static final String GET_USER_BY_NAME = "getUserByName";
	private static final String GET_ALL_ALUMNOS = "getAllAlumnos";
	private static final String GET_ALL_USERS = "getAllUsers";

	
	
	/**
	 * Metodo principal que inicia el servidor
	 * 
	 * 
	 * Se crea un socket en el puerto 20000 y se queda escuchando conexiones entrantes
	 * 
	 * Cuando se recibe una conexion se crea un hilo que se encarga de atender al cliente
	 * Eso se hace para que el servidor pueda atender a varios clientes a la vez
	 * 
	 * 
	 * while(true), se queda escuchando conexiones entrantes en un bucle infinito
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			ServerSocket serverSocket = new ServerSocket(20000);
			Socket socket = null;
			while (true) {

				socket = serverSocket.accept();

				ServerThread st = new ServerThread(socket);
				st.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static List<Ikastetxe> getIkastetxeListFromJson(String json) {
		ObjectMapper objectMapper = new ObjectMapper();
		List<Ikastetxe> ikastetxeList = null;

		try {
			// Deserializamos el JSON directamente a una lista de Ikastetxe
			ikastetxeList = objectMapper.readValue(json,
					objectMapper.getTypeFactory().constructCollectionType(List.class, Ikastetxe.class));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return ikastetxeList;
	}

	public static String readJsonFromFile(String filePath) {
		StringBuilder jsonContent = new StringBuilder();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				jsonContent.append(line); // No agregar "\n"
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return jsonContent.toString();
	}

	/**
	 * Metodo que recibe un string con una key y devuelve un objeto
	 *
	 * getAllUsers -> devuelve un arraylist de usuarios getUserByName/<name> ->
	 * devuelve un usuario getHorariosByUserId/<id> -> devuelve un arraylist de
	 * horarios isLoginOk/<user>/<pass> -> devuelve true o false
	 * forgotPassword/<email> -> devuelve true o false getBilerakByUserId/<id> ->
	 * getBilerakByProfesorId/<id> -> devuelve un arraylist de reuniones del profesor
	 * getAllTeachers -> devuelve un arraylist de profesores
	 * getMatriculacionByUserId/<id> -> devuelve una matriculacion del usuario
	 * updateStatus/<id>/<status> -> devuelve true o false , actualiza el Estado de las Reuniones
	 * getAllIkastetxeak -> devuelve una lista de Ikastetxe, recupera los datos de un JSON
	 * 
	 *
	 * @param key
	 * @return
	 */
	public static Object consultar(String key) {

		ArrayList<Users> listU = new ArrayList();
		ArrayList<Horarios> listH = new ArrayList();
		ArrayList<Reuniones> listB = new ArrayList();
		ArrayList<VistaHorariosUsuarios> listV = new ArrayList();

		String[] params = key.split(REGEX);

		System.out.println(params[0]);

		switch (params[0]) {

		case GET_ALL_USERS:
			listU = ((ArrayList<Users>) Kontsultak.conectar(Kontsultak.getAllUsers()));
			return listU;

		case GET_ALL_ALUMNOS:
			listU = ((ArrayList<Users>) Kontsultak.conectar(Kontsultak.getAllAlumnos()));
			return listU;

		case GET_USER_BY_NAME:
			listU = ((ArrayList<Users>) Kontsultak.conectar(Kontsultak.getUserByName(params[1])));
			return listU.get(0);

		case GET_HORARIOS_BY_PROFE_ID:
			listH = ((ArrayList<Horarios>) Kontsultak.conectar(Kontsultak.getHorariosByProfeId(params[1])));
			return listH;

		case GET_HORARIOS_BY_ALUMNO_ID:
			listV = ((ArrayList<VistaHorariosUsuarios>) Kontsultak
					.conectar(Kontsultak.getHorariosByAlumnoId(params[1])));
			return listV;

		case IS_LOGIN_OK:
			listU = ((ArrayList<Users>) Kontsultak.conectar(Kontsultak.isLoginOk(params[1], params[2])));
			if (listU.size() > 0) {
				return true;
			} else {
				return false;
			}
		case FORGOT_PASSWORD:
			listU = ((ArrayList<Users>) Kontsultak.conectar(Kontsultak.getUserByEmail(params[1])));
			if (listU.size() > 0) {
				Kontsultak.conectarCrud(Kontsultak.updatePassword(listU.get(0).getId() + "", Utils.randomPass()));
				listU = ((ArrayList<Users>) Kontsultak.conectar(Kontsultak.getUserByEmail(params[1])));
				Utils.sendEmail(listU.get(0));
				return true;
			} else {
				return false;
			}

		case GET_BILERAK_BY_PROFESOR_ID:
			listB = ((ArrayList<Reuniones>) Kontsultak.conectar(Kontsultak.getBilerakByProfesorId(params[1])));
			return listB;

		case GET_BILERAK_BY_ALUMNO_ID:
			listB = ((ArrayList<Reuniones>) Kontsultak.conectar(Kontsultak.getBilerakByAlumnoId(params[1])));
			return listB;

		case GET_ALL_TEACHERS:
			listU = ((ArrayList<Users>) Kontsultak.conectar(Kontsultak.getAllTeachers()));
			return listU;

		case GET_MATRICULACION_BY_USER_ID:
			ArrayList<Matriculaciones> ListMa = ((ArrayList<Matriculaciones>) Kontsultak
					.conectar(Kontsultak.getMatriculacionByUserId(params[1])));
			Matriculaciones ma = ListMa.get(0);
			return ma;

		case UPDATE_STATUS:
			Kontsultak.conectarCrud(Kontsultak.updateStatus(params[1], params[2]));
			return true;

		case GET_ALL_IKASTETXEAK:
			String json = readJsonFromFile(SRC_ELORSON_JSON);
			System.out.println(json);
			List<Ikastetxe> ikastetxeList = getIkastetxeListFromJson(json);
			return ikastetxeList;

		}

		return null;
	}

}