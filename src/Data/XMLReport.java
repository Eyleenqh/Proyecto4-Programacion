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
public class XMLReport {

    //variables
    private Document document;
    private Element root;
    private String path;
    private boolean successAddSon;

    public XMLReport(String path) throws JDOMException, IOException {
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
            this.root = new Element("Report");
            this.root.setAttribute("game", "spaceShip war");

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
    public boolean insertReport(Element e) throws IOException {
        Element eReport = new Element("Report");
        eReport.setAttribute("game", e.getAttributeValue("game"));
        //crear el msj
        Element eResultAttack = new Element("resultAttack");
        eResultAttack.addContent(e.getChildText("resultAttack"));

        Element eStatus = new Element("status");
        eStatus.addContent(e.getChildText("status"));

        eReport.addContent(eResultAttack);
        eReport.addContent(eStatus);
        //agregar a root
        root.addContent(eReport);
        //guarde todo
        storeXML();
        return true;
        //   }
    }

    //metodo para obtener el ultimo elemento del xml
    public String getReport() {
        String report="";
        //obtenemos la cantidad de ataques
        int reportQuantity = this.root.getContentSize();
        //obtemos una lista con todos los elementos del root
        List elementList = this.root.getChildren();
        Element eReport = null;
        //recorrer la lista para ir creando el arreglo
        int count = 0;
        for (Object currentObject : elementList) {//recorre todos los elementos para obtener el ultimo
            if (count == reportQuantity) {
                //transformo de object a elemento
                eReport = (Element) currentObject;
                report=eReport.getChildText("resultAttack")+"---"+eReport.getChildText("status");
            }
            count++;
        }//end for
        return report;
    }
}
