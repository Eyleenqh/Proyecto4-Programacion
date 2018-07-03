package Socket;

import Data.XMLAttack;
import Data.XMLReport;
import Data.XMLTransmitterManager;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.LinkedList;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 *
 * @author Eyleen
 */
public class ServerThread extends Thread {

    //Declaramos las variables que utiliza el hilo para estar recibiendo y mandando mensajes
    private Socket client;
    private InetAddress address;
    private DataOutputStream out;
    private DataInputStream in;

    private XMLTransmitterManager xmlT;
    private XMLAttack xmlA;
    private XMLReport xmlR;
    private String[] recibido;
    private PrintStream send;
    //Turno
    //private boolean turno;
    //Lista de los usuarios conectados al servidor
    private LinkedList<Socket> usuarios;

    //Constructor que recibe el client que atendera el hilo y la lista de los jugadores el turno y la matriz del juego
    public ServerThread(Socket socket, LinkedList users) {
        this.client = socket;
        this.address=client.getInetAddress();
        this.usuarios = users;
    }

    @Override
    public void run() {
        try {
            this.send = new PrintStream(client.getOutputStream());

            //para recibir/leer datos
            BufferedReader receive = new BufferedReader(
                    new InputStreamReader(
                            client.getInputStream()));

            //Inicializamos los canales de comunicacion y mandamos el turno a cada jugador
            /* in = new DataInputStream(client.getInputStream());
            out = new DataOutputStream(client.getOutputStream());*/
            //Leer los datos que se mandan cuando se selecciona una posicion
            //String msjFromClient = in.readUTF();
            String msjFromClient = receive.readLine();
            this.recibido = msjFromClient.split("---");
            send = null;
            if (this.recibido[0].equalsIgnoreCase("C")) {
                xmlT = new XMLTransmitterManager("./chatSpaceshipWar.xml");
                xmlEmisor();

            }
            if (this.recibido[0].equalsIgnoreCase("A")) {
                xmlA = new XMLAttack("./AttackSpaceshipWar.xml");
                xmlAttack();
            }

            if (this.recibido[0].equalsIgnoreCase("R")) {
                xmlR = new XMLReport("./ReportSpaceshipWar.xml");
                xmlReport();
            }
            //finalizamos la comunicacion con ese cliente
            client.close();

        } catch (Exception e) {

            //Si ocurre un excepcion lo mas seguro es que sea por que algun jugador se desconecto asi que lo quitamos de la lista de conectados
            for (int i = 0; i < usuarios.size(); i++) {
                if (usuarios.get(i) == client) {
                    usuarios.remove(i);
                    break;
                }
            }
        }
    }
    //Funcion comprueba si algun jugador ha ganado el juego

    public boolean gano() {
        /*  if (madre.getVida==0) {
                return true;
            }else{*/
        return false;
        // }
    }

    public void xmlEmisor() throws JDOMException, IOException {
        String recibido2 = this.recibido[1];
        SAXBuilder builder = new SAXBuilder();
        StringReader stringR = new StringReader(recibido2);
        Document doc = builder.build(stringR);
        Element e = doc.getRootElement();

        System.out.println("Client send message");
        xmlT.insertTransmitter(e);

        String cad = "Message";
        for (Socket usuario : usuarios) {
            /*out = new DataOutputStream(usuario.getOutputStream());
            out.writeUTF(cad);*/
            this.send = new PrintStream(usuario.getOutputStream());
            this.send.println(cad);
        }
    }

    public void xmlAttack() throws JDOMException, IOException {
        String message = this.recibido[1];
        SAXBuilder builder = new SAXBuilder();
        StringReader stringR = new StringReader(message);
        Document doc = builder.build(stringR);
        Element e = doc.getRootElement();

        System.out.println("Client send attack");
        xmlA.insertAttack(e);

        //posicion x del ataque
        int x = Integer.parseInt(e.getChildText("x"));
        //posicion y del ataque
        int y = Integer.parseInt(e.getChildText("y"));

        //Mensaje a los jugadores sobre el movimiento realizado
        String cad =x+";"+y;

        //Comprueba si ya algun jugador gano
        /*  boolean ganador = gano();

        if (!ganador) {
            cad += "NADIE";
        } else {
           // cad +=...;
        }*/
        for (Socket usuario : usuarios) {
            /* out = new DataOutputStream(usuario.getOutputStream());
            out.writeUTF(cad);*/
        //    if (this.address != usuario.getInetAddress()) {
                this.address=null;
                this.send = new PrintStream(usuario.getOutputStream());
                this.send.println(cad);
           // }
        }
    }

    public void xmlReport() throws JDOMException, IOException {
        String recibido = this.recibido[1];
        SAXBuilder builder = new SAXBuilder();
        StringReader stringR = new StringReader(recibido);
        Document doc = builder.build(stringR);
        Element e = doc.getRootElement();

        System.out.println("Client send report");
        xmlR.insertReport(e);

        String cad = "Report";
        for (Socket usuario : usuarios) {
            /*out = new DataOutputStream(usuario.getOutputStream());
            out.writeUTF(cad);*/
            if (this.address != usuario.getInetAddress()) {
                this.address=null;
                this.send = new PrintStream(usuario.getOutputStream());
                this.send.println(cad);
            }
        }
    }
}
