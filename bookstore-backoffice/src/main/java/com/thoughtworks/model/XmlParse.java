package com.thoughtworks.model;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

@Component
public class XmlParse {

    public String url;

    public String ParseXml(String addr, String isbn) throws ParserConfigurationException, IOException, SAXException {
        String strXml = null;
        File f = new File(addr);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(f);
        NodeList nl = doc.getElementsByTagName("url");
        for (int i = 0; i < nl.getLength(); i++) {
            if (doc.getElementsByTagName("id").item(i).getFirstChild().getNodeValue().equals("1")) {
                strXml = doc.getElementsByTagName("value").item(i).getFirstChild().getNodeValue();
            }
        }
        if (isbn.isEmpty()) {
            return strXml;
        } else {
            return strXml + "/" + isbn;
        }
    }

    public String pathParse(String isbn) throws IOException, SAXException, ParserConfigurationException {
        String str = XmlParse.class.getResource("../../").getPath();
        int pos = str.indexOf("classes");
        str = str.substring(0, pos);
        str += "resources/config.xml";
        url = this.ParseXml(str, isbn);
        return url;
    }
}
