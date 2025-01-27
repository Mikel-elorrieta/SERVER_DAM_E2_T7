package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import modelo.Horarios;
import modelo.Reuniones;
import modelo.Users;

public class ServerGeneral {

	public static void main(String[] args) {

		try {
			ServerSocket serverSocket = new ServerSocket(10000);
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

		
		String[] k = key.split("/");

		System.out.println(k[0]);

		switch (k[0]) {

		case "getAllUsers":
			listU = ((ArrayList<Users>) Kontsultak.conectar(Kontsultak.getAllUsers()));
			return listU;
			
		case "getUserByName":
			listU = ((ArrayList<Users>) Kontsultak.conectar(Kontsultak.getUserByName(k[1])));
			return listU.get(0);
			
		case "getHorariosByUserId":
			listH = ((ArrayList<Horarios>) Kontsultak.conectar(Kontsultak.getHorariosByUserId(k[1])));
			return listH;
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
			
		case "getBilerakByUserId":
			listB = ((ArrayList<Reuniones>) Kontsultak.conectar(Kontsultak.getBilerakByUserId(k[1])));
			return listB;
			
		case "getAllTeachers":
			listU = ((ArrayList<Users>) Kontsultak.conectar(Kontsultak.getAllTeachers()));
			return listU;
		
		
		}
		


		return null;
	}

}
