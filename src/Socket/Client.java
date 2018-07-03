package Socket;

import Domain.Transmitter;
import GUI.Game3x3Mode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author Eyleen
 */
public class Client extends Thread {

    
   // private DataOutputStream out;
  //  private DataInputStream in;
     
    //Variables
    private PrintStream send;

    //Variables del frame 
    private String message;
    private Game3x3Mode frame;

    //private boolean turno;
    //Constructor recibe como parametro la ventana (Frame)
    public Client(Game3x3Mode frame) {
        try {
            this.frame = frame;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        InetAddress address;
        Socket socket;

        try {

            address = InetAddress.getLocalHost();
            //address=new Inet4Address("");
            socket = new Socket(address, 5025);
            /*in = new DataInputStream(socket.getInputStream());*/
            //out = new DataOutputStream(socket.getOutputStream());
            //mensaje =  in.readUTF();
            this.send = new PrintStream(socket.getOutputStream());
            //para recibir/leer datos
            BufferedReader receive = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()
                    )
            );
            //Cuando conectamos con el servidor, este nos devuelve el turno de juego
            message = receive.readLine();

            if (message.equalsIgnoreCase("Message")) {
                frame.setChat();
            } else {
                if (message.equalsIgnoreCase("Report")) {
                    this.frame.setReportAttack();
                    //msj servidor quien gano quien no y guardar
                } else {
                    String[] received = message.split(";");
                    /* String messageReceived = received[0].split(" ")[1];
                    turno = Boolean.valueOf(received[1]);
                    String[] message3 = message.split(";");*/
                    int x = Integer.parseInt(received[0]);//pos x
                    int y = Integer.parseInt(received[1]);//pos y

                    // turno = !turno;
                    this.frame.attack(x, y);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Funcion sirve para enviar la jugada al servidor
    public void enviar(String message) {
        try {
            //se envia los datos al servidor
            String datos = message;
            this.send.println(datos);
            //out.writeUTF(datos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //metodo para agregar estudiantes
    public void createMessage(Transmitter newTransmitter) throws IOException {
        Element eTransmitter = new Element("chat");
        //agregamos un atributo
        eTransmitter.setAttribute("name", newTransmitter.getName());

        //crear el message
        Element nMessage = new Element("message");
        nMessage.addContent(newTransmitter.getMessage());

        //agregar al elemento emisor
        eTransmitter.addContent(nMessage);

        XMLOutputter outputter = new XMLOutputter(Format.getCompactFormat());
        String s = "C---" + outputter.outputString(eTransmitter);

        enviar(s);
        /*  this.send.println(s);

        //Instancio un lector
        BufferedReader messageFromServer = new BufferedReader(
                new InputStreamReader(
                        this.socket.getInputStream()
                )
        );
        //leo el message
        String infoResponse = messageFromServer.readLine();
        return infoResponse;*/
    }

    public void createAttack(int x, int y) throws IOException {
        Element eAttack = new Element("Attack");
        //agregamos un atributo
        eAttack.setAttribute("position", "xy");

        //crear el message
        Element eX = new Element("x");
        eX.addContent(String.valueOf(x));

        Element eY = new Element("y");
        eY.addContent(String.valueOf(y));

        //agregar al elemento emisor
        eAttack.addContent(eX);
        eAttack.addContent(eY);

        XMLOutputter outputter = new XMLOutputter(Format.getCompactFormat());
        String s = "A---" + outputter.outputString(eAttack);
        enviar(s);

    }

    public void createReport(String report) throws IOException {
        String[] reports = report.split("---");
        Element eReport = new Element("report");
        //agregamos un atributo
        eReport.setAttribute("game", "spaceship war");

        //crear el message
        Element eResulAttack = new Element("resultAttack");
        eResulAttack.addContent(reports[0]);

        Element eStatus = new Element("status");
        eStatus.addContent(reports[1]);

        //agregar al elemento emisor
        eReport.addContent(eResulAttack);
        eReport.addContent(eStatus);

        XMLOutputter outputter = new XMLOutputter(Format.getCompactFormat());
        String s = "R---" + outputter.outputString(eReport);
        enviar(s);

    }

    //metodo que envia finalize al servidor para cerrar la conexion
    public void finalize(String command) {
        send.println(command);
    }
}

