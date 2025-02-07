package Server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Esta clase representa un hilo del servidor que maneja las solicitudes de los clientes.
 * Cada instancia de este hilo gestiona una conexión individual con un cliente.
 * Lee una clave enviada por el cliente, realiza una consulta usando {@code ServerGeneral.consultar()}
 * y envía la respuesta de vuelta al cliente.
 */
public class ServerThread extends Thread {

    private DataInputStream dis;
    private ObjectOutputStream dos;
    private Socket socket;

    /**
     * Construye un nuevo {@code ServerThread} con el socket del cliente especificado.
     * Inicializa los flujos de entrada y salida para la comunicación con el cliente.
     *
     * @param socket el socket del cliente asociado a este hilo
     */
    public ServerThread(Socket socket) {
        this.socket = socket;
        try {
            dis = new DataInputStream(socket.getInputStream());
            dos = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ejecuta el hilo del servidor.
     * Lee una clave enviada por el cliente, obtiene la respuesta correspondiente usando {@code ServerGeneral.consultar()}
     * y envía la respuesta al cliente.
     * Flush controla la salida de datos y fuerza que todos los datos se envíen al cliente.
     */
    @Override
    public void run() {
        try {
            String key = dis.readUTF();  					// Lee la clave enviada por el cliente

            Object response = ServerGeneral.consultar(key); // Consulta la respuesta correspondiente a la clave

            dos.writeObject(response);            			// Envía la respuesta al cliente

            dos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            												// Cierra los recursos abiertos
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
