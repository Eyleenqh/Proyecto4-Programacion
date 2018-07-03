/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Domain.Transmitter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author Steven
 */
public class XMLTransmitterManager {

    //variables
    private Document document;
    private Element root;
    private String path;
    private boolean successAddSon;
    
    public XMLTransmitterManager(String path) throws JDOMException, IOException {
        this.path = path;
        
        File fileEmisor = new File(this.path);
        if (fileEmisor.exists()) {
            //toma la estructura de datos y la carga en memoria
            SAXBuilder saxBuilder = new SAXBuilder();
            saxBuilder.setIgnoringElementContentWhitespace(true);

            //cargar en memoria
            this.document = saxBuilder.build(this.path);
            this.root = this.document.getRootElement();
        } else {
            //creamos el elemento raiz
            this.root = new Element("Chat");
            this.root.setAttribute("name", "spaceship war");

            //creamos el documento
            this.document = new Document(this.root);

            //guardamos el documento
            storeXML();
        }
    }//end method

    //almacena en disco duro nuestro documento XML en la ruta especificada
    private void storeXML() throws FileNotFoundException, IOException {
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.output(this.document, new PrintWriter(this.path));
    }//end method

    //metodo para insertar una persona nueva
    public boolean insertTransmitter(Element e) throws IOException {
        Element nTransmitter = new Element("Chat");
        nTransmitter.setAttribute("name", e.getAttributeValue("name"));
            //crear el msj
            Element eMessage = new Element("message");
            eMessage.addContent(e.getChildText("message"));
            
            nTransmitter.addContent(eMessage);

            //agregar a root
            root.addContent(nTransmitter);
            //guarde todo
            storeXML();
            return true;
     //   }
    }

    //metodo para obtener todos los objetos del xml
    public Transmitter[] getAllTransmitter() {
        //obtenemos la cantidad de estudiantes
        int transmitterQuantity = this.root.getContentSize();
        Transmitter[] transmitterArray = new Transmitter[transmitterQuantity];
        //obteemos una lista con todos los elementos del root
        List elementList = this.root.getChildren();

        //recorrer la lista para ir creando el arreglo
        int count = 0;
        for (Object currentObject : elementList) {//recorre todos los elementos y en cada iteracion va a guardar el elemento actual
            //transformo de object a element\
            Element currentElement = (Element) currentObject;

            //creamos el estudiante--atributo
            Transmitter currentTransmitter = new Transmitter();

            //establesco el nombre
            currentTransmitter.setName(currentElement.getAttributeValue("name"));

            //establesco el msj
            currentTransmitter.setMessage(currentElement.getChild("message").getValue());

            transmitterArray[count++] = currentTransmitter;
        }//end for
        return transmitterArray;
    }

}
