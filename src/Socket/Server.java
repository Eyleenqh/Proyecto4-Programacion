package Socket;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {

    //Creamos una lista de sockets para guardar el socket de cada jugador
    private LinkedList<Socket> usuarios = new LinkedList<Socket>();

    //Funcion para que el servidor empieze a recibir conexiones de clientes
    public void escuchar() {
        try {
            //Creamos el socket servidor
            ServerSocket servidor = new ServerSocket(5025, 2);
            //Ciclo infinito para estar escuchando por nuevos jugadores
            System.out.println("Esperando jugadores....");
            while (true) {
                //Cuando un jugador se conecte guardamos el socket en nuestra lista
                Socket cliente = servidor.accept();
                //Se agrega el socket a la lista
                this.usuarios.add(cliente);
                //Instanciamos un hilo que estara atendiendo al cliente
                ServerThread server = new ServerThread(cliente, usuarios);
                server.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Funcion main para correr el servidor
    public static void main(String[] args) {
        Server servidor = new Server();
        servidor.escuchar();
    }
}
