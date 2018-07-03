package Socket;

import Domain.Transmitter;
import GUI.Game5x5Mode;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JOptionPane;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author Eyleen
 */
public class Client2 implements Runnable {

    //Declaramos las variables necesarias para la conexion y comunicacion
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;

    //anadidas
    private InetAddress address;
    private PrintStream send;

    //Variables del frame 
    private String message;
    private Game5x5Mode frame;
    private ActionListener ac;

    //Variables para cargar las imagenes de la X y O
    private Image X;
    private Image O;

    private boolean turno;
    private char petitionType;

    //Constructor recibe como parametro la ventana (Frame), para poder hacer modificaciones sobre los botones
    public Client2(Game5x5Mode frame) {
        try {
            this.frame = frame;
            this.address = InetAddress.getLocalHost();
            //Creamos el socket con el host y el puerto, declaramos los streams de comunicacion
            this.socket = new Socket(this.address, 5025);
            this.send = new PrintStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            //Cuando conectamos con el servidor, este nos devuelve el turno de juego
            message = in.readUTF();
            String split[] = message.split(";");
            String XO = split[0].split(" ")[1];
            turno = Boolean.valueOf(split[1]);

            while (true) {
                //Recibimos el message
                message = in.readUTF();

                if (!message.equalsIgnoreCase("Message")) {
                    String[] mensajes = message.split(";");
                    int xo = Integer.parseInt(mensajes[0]);//X O
                    int f = Integer.parseInt(mensajes[1]);//fila
                    int c = Integer.parseInt(mensajes[2]);//columna

                    turno = !turno;

                    /*
                Dependiendo del mensajes[3] que nos dice el estado del juego, mostramos el message
                     */
                    if (XO.equals(mensajes[3])) {//estado
                        JOptionPane.showMessageDialog(frame, "WON!");
                        frame.dispose();
                    } else if (!"NADIE".equals(mensajes[3]) && !mensajes[3].equals(mensajes[0])) {
                        JOptionPane.showMessageDialog(frame, "You Lost!");
                        frame.dispose();
                    }
                }else{
                    //leer xml
                  //  frame.setMessage(message);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Funcion sirve para enviar la jugada al servidor
    public void enviarTurno(int f, int c) {
        /*
        Comprobamos que sea nuestro turno para jugar, si no es devolmemos un message
        Si es el turno entonces mandamos un message al servidor con los datos de la jugada que hicimos
         */
        try {
            if (turno) {
                String datos = "";
                datos += f + ";";
                datos += c + ";";
                out.writeUTF(datos);
            } else {
                JOptionPane.showMessageDialog(frame, "Espera tu turno");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Funcion sirve para enviar la jugada al servidor
    public void enviarMessage(String message) {
        try {
            //se comprueba si es el turno del jugador
            if (turno) {
                //se envia los datos al servidor
                String datos = message;
                out.writeUTF(datos);
            } else {
                JOptionPane.showMessageDialog(frame, "Wait your turn");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //metodo para agregar estudiantes
    public String create(Transmitter newTransmitter) throws IOException {
        Element eTransmitter = new Element("chat");
        //agregamos un atributo
        eTransmitter.setAttribute("name", newTransmitter.getName());

        //crear el message
        Element nMessage = new Element("message");
        nMessage.addContent(newTransmitter.getMessage());

        //agregar al elemento emisor
        eTransmitter.addContent(nMessage);

        XMLOutputter outputter = new XMLOutputter(Format.getCompactFormat());
        String s = /*this.petitionType +*/ "C---" + outputter.outputString(eTransmitter);

        this.send.println(s);

        //Instancio un lector
        BufferedReader messageFromServer = new BufferedReader(
                new InputStreamReader(
                        this.socket.getInputStream()
                )
        );
        //leo el message
        String infoResponse = messageFromServer.readLine();
        return infoResponse;
    }

    //metodo que envia finalize al servidor para cerrar la conexion
    public void finalize(String command) {
        this.send.println(command);
    }

    //metodos accesores
    public char getPetitionType() {
        return petitionType;
    }

    public void setPetitionType(char petitionType) {
        this.petitionType = petitionType;
    }
}
