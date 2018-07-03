/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

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
 * @author Eyleen
 */
public class XMLAttack {
    //variables

    private Document document;
    private Element root;
    private String path;
    private boolean successAddSon;

    public XMLAttack(String path) throws JDOMException, IOException {
        this.path = path;

        File fileAttack = new File(this.path);
        if (fileAttack.exists()) {
            //toma la estructura de datos y la carga en memoria
            SAXBuilder saxBuilder = new SAXBuilder();
            saxBuilder.setIgnoringElementContentWhitespace(true);

            //cargar en memoria
            this.document = saxBuilder.build(this.path);
            this.root = this.document.getRootElement();

        } else {
            //creamos el elemento raiz
            this.root = new Element("Attack");
            this.root.setAttribute("position", "xy");

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
    public boolean insertAttack(Element e) throws IOException {
        Element eAttack = new Element("Attack");
        eAttack.setAttribute("position", e.getAttributeValue("position"));
        //crear el msj
        Element eX = new Element("x");
        eX.addContent(e.getChildText("x"));

        Element eY = new Element("y");
        eY.addContent(e.getChildText("y"));

        eAttack.addContent(eX);
        eAttack.addContent(eY);
        //agregar a root
        root.addContent(eAttack);
        //guarde todo
        storeXML();
        return true;
        //   }
    }

    //metodo para obtener el ultimo elemento del xml
    public Element getAttack() {
        //obtenemos la cantidad de ataques
        int AttackQuantity = this.root.getContentSize();
        //obtemos una lista con todos los elementos del root
        List elementList = this.root.getChildren();
        Element attack=null;
        //recorrer la lista para ir creando el arreglo
        int count = 0;
        for (Object currentObject : elementList) {//recorre todos los elementos para obtener el ultimo
            if (count==AttackQuantity) {
                //transformo de object a elemento
                attack = (Element) currentObject;
            }
            count++;
        }//end for
        return attack;
    }
}
