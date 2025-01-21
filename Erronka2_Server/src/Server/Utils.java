package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import modelo.Users;

public class Utils {
	
	
	public static String randomPass() {
		
		String pass = "";
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (int i = 0; i < 10; i++) {
            pass += chars.charAt((int) (Math.random() * chars.length()));
        }
        return pass;
	}
	

	public static void sendEmail(Users user) {
        
		File file = new File("email.txt");
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write("De: maiappmai@gmail.com\r\n"
					+ "Para: " + user.getEmail() + "\r\n"
					+ "Asunto: Pasahitz aldaketa\r\n"
					+ "\r\n"
					+ "Kaixo "+ user.getNombre() +",\r\n"
					+ "\r\n"
					+ "Hau da zure pasahitz berria, ez partekatu inorekin: "+ user.getPassword() +"\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "Agurrak,\r\n"
					+ "maiappmai");
			writer.close();
			
	
	        String filePath = "\"" + file.getAbsolutePath().replace("\\", "\\\\") + "\"";
			String email = "\"" + user.getEmail() + "\""; 
			
			
			ProcessBuilder pb = new ProcessBuilder("cmd", "/c","curl --url smtp://smtp.gmail.com:587 --ssl-reqd --mail-from \"maiappmai@gmail.com\" --mail-rcpt \"" + email  +"\" --upload-file " + filePath + " --user \"maiappmai@gmail.com:jfid xagl ytix psio\"");
			Process process = pb.start();
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} 
		
		
		
    }
	
}
