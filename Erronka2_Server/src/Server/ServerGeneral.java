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
	
	public static List<Ikastetxe> getIkastetxeListFromJson(String json) {
	    ObjectMapper objectMapper = new ObjectMapper();
	    List<Ikastetxe> ikastetxeList = null;

	    try {
	        // Deserializamos el JSON directamente a una lista de Ikastetxe
	        ikastetxeList = objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, Ikastetxe.class));
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
	            jsonContent.append(line);  // No agregar "\n"
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    return jsonContent.toString();
	}


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

	
	
	/**
	 * Metodo que recibe un string con una key y devuelve un objeto
	 * 
	 * getAllUsers -> devuelve un arraylist de usuarios
	 * getUserByName/<name> -> devuelve un usuario
	 * getHorariosByUserId/<id> -> devuelve un arraylist de horarios
	 * isLoginOk/<user>/<pass> -> devuelve true o false
	 * forgotPassword/<email> -> devuelve true o false
	 * getBilerakByUserId/<id> -> devuelve un arraylist de reuniones
	 * 
	 * @param key
	 * @return
	 */
	public static  Object consultar(String key) {
		
		ArrayList<Users> listU = new ArrayList();
		ArrayList<Horarios> listH = new ArrayList();
		ArrayList<Reuniones> listB = new ArrayList();
		ArrayList<VistaHorariosUsuarios> listV = new ArrayList();

		
		String[] k = key.split("/");

		System.out.println(k[0]);

		switch (k[0]) {

		case "getAllUsers":
			listU = ((ArrayList<Users>) Kontsultak.conectar(Kontsultak.getAllUsers()));
			return listU;
		
		case "getAllAlumnos":
			listU = ((ArrayList<Users>) Kontsultak.conectar(Kontsultak.getAllAlumnos()));
			return listU;
			
		case "getUserByName":
			listU = ((ArrayList<Users>) Kontsultak.conectar(Kontsultak.getUserByName(k[1])));
			return listU.get(0);
			
		case "getHorariosByProfeId":
			listH = ((ArrayList<Horarios>) Kontsultak.conectar(Kontsultak.getHorariosByProfeId(k[1])));
			return listH;
			
		case "getHorariosByAlumnoId":
			listV = ((ArrayList<VistaHorariosUsuarios>) Kontsultak.conectar(Kontsultak.getHorariosByAlumnoId(k[1])));
			return listV;
			
		case "isLoginOk":
			listU = ((ArrayList<Users>) Kontsultak.conectar(Kontsultak.isLoginOk(k[1], k[2])));
			if (listU.size() > 0) {
				return true;
			}else {
				return  false;
			}
		case "forgotPassword":
			listU = ((ArrayList<Users>) Kontsultak.conectar(Kontsultak.getUserByEmail(k[1])));
			if (listU.size() > 0) {
			Kontsultak.conectarCrud(Kontsultak.updatePassword(listU.get(0).getId() + "", Utils.randomPass()));
			listU = ((ArrayList<Users>) Kontsultak.conectar(Kontsultak.getUserByEmail(k[1])));
			Utils.sendEmail(listU.get(0));
			return true;
			}else {
			return  false;
			}
			
		case "getBilerakByProfesorId":
			listB = ((ArrayList<Reuniones>) Kontsultak.conectar(Kontsultak.getBilerakByProfesorId(k[1])));
			return listB;
			
		case "getBilerakByAlumnoId":
			listB = ((ArrayList<Reuniones>) Kontsultak.conectar(Kontsultak.getBilerakByAlumnoId(k[1])));
			return listB;
			
		case "getAllTeachers":
			listU = ((ArrayList<Users>) Kontsultak.conectar(Kontsultak.getAllTeachers()));
			return listU;
		
		case "updateImg":
			listU = ((ArrayList<Users>) Kontsultak.conectar(Kontsultak.getAllTeachers()));
			return listU;
			
		case "getMatriculacionByUserId":
			ArrayList<Matriculaciones> ListMa = ((ArrayList<Matriculaciones>) Kontsultak.conectar(Kontsultak.getMatriculacionByUserId(k[1])));
			Matriculaciones ma = ListMa.get(0);
			return ma ;
			
		case "updateStatus":
            Kontsultak.conectarCrud(Kontsultak.updateStatus(k[1], k[2]));
            return true;
            
		case "getAllIkastetxeak":
		    String json = readJsonFromFile("src/elorson.json");
		    System.out.println(json);
		    List<Ikastetxe> ikastetxeList = getIkastetxeListFromJson(json);
		    return ikastetxeList;
			
			
		
		}
		


		return null;
	}

}
