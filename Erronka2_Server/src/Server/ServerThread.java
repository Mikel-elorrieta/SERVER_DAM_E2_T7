package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread {

    private DataInputStream dis;
    private ObjectOutputStream dos;
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
        try {
           
            dis = new DataInputStream(socket.getInputStream());
            dos = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String key = dis.readUTF(); 

           
            Object response = ServerGeneral.consultar(key);

         
            dos.writeObject(response);
            dos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
           
            try {
                if (dis != null) dis.close();
                if (dos != null) dos.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
