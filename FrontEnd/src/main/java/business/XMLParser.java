 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author student
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


public class XMLParser {

    private static short TEXT=3;

    public static String ConvertXmlToHtmlTable(String xml) {
        StringBuilder html = new StringBuilder("<table border=\"1\"   cellpadding=\"5\" cellspacing=\"0\"> "
                + "\r\n");
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            System.out.println("*******************************");
            System.out.println(xml);
            Document document = builder.parse(new InputSource(new StringReader(xml)));
            Element root = document.getDocumentElement();

            NodeList childs = root.getChildNodes();

            for (int i = 0; i < childs.getLength(); i++) {
                String elename = "";
                if(childs.item(i).getNodeType()==Node.ELEMENT_NODE){
                html.append("<tr>");
          String text=getChildText(childs.item(i));
        
                    html.append(text);
                
                html.append("<td>  <button type=\"submit\">Hold</button> ");
        
                html.append("</td>");
                html.append("</tr>");
                }

            }

            html.append("</table>");
        } catch (Exception e) {
            e.printStackTrace();
            return xml;
            // Returning the original string incase of error.
        }
        System.out.println(html.toString());
        return html.toString();
    }

    private static String getChildText(Node node) {
        StringBuilder html=new StringBuilder("");
       /*if(!node.hasChildNodes()){
           html.append("<table> <tr><th>"+node.getNodeName()+"</th></tr><tr><td>"+node.getNodeValue()+"</td></tr>");
           return (html.toString());
       }*/
       
      if (node.getNodeType()==TEXT){
           
               html.append("<td>"+node.getNodeValue()+"</td>");
           return (html.toString());
        
    }
       else{
            html.append("<td><table width=\"100%\" border=\"0\"  cellpadding=\"10\"  cellspacing=\"1\"><tr><th>"+node.getNodeName()+"</th></tr>");
            NodeList nodes = node.getChildNodes();
           
           for(int i=0;i<nodes.getLength(); i++){
               
               html.append(getChildText(nodes.item(i)));
             
           }
           html.append("</table></td>");
           return(html.toString());
       }
    }

}

