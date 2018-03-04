package com.exsposit.practica.Parsers;

import com.exsposit.practica.Beans.File;
import com.exsposit.practica.Beans.IParse;
import com.exsposit.practica.Utilites.Downloader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

public class XmlParser implements IParse {

    private String file;

    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    List<File> xmlFilesList = new ArrayList<>();

    public XmlParser(String file){
        this.file = file;
    }

    public List<File> parse(){
        try {
            dbFactory.setValidating(false);
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("file");

            for (int i = 0; i < nList.getLength(); i++) {
                File file = new File();
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nNode;
                    file.setName(getTagValue("name", element));
                    file.setUrl(getTagValue("url", element));
                }
                xmlFilesList.add(file);
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return xmlFilesList;
    }

    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
}
