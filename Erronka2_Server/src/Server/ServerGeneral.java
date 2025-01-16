package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import modelo.Users;

public class ServerGeneral {

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

	public static Object consultar(String key) {
		
		ArrayList<Users> list = new ArrayList();
		
		String[] k = key.split("/");

		System.out.println(k[0]);

		switch (k[0]) {

		case "getAllUsers":
			
			list = ((ArrayList<Users>) Kontsultak.conectar(Kontsultak.getAllUsers()));
			return list;
		case "getUserByName":
			
			ArrayList<Users> list2 = new ArrayList();
			list = ((ArrayList<Users>) Kontsultak.conectar(Kontsultak.getUserByName(k[1])));
			return list.get(0);
		
		
		}
		
			
		
		
		

	

		return null;
	}

}
