/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endpoint;

import business.userinfo;
import helper.infoXML;
import java.io.StringWriter;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import persistence.accountCRUD;

/**
 * REST Web Service
 *
 * @author student
 */
@Path("userinfo/{username}")
public class UserinfoResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UserinfoResource
     */
    public UserinfoResource() {
        
    }

    /**
     * Retrieves representation of an instance of endpoint.UserinfoResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML+";charset=utf-8")
    public String getXml(@PathParam("username")String username) {
        //TODO return proper representation object
        System.out.println("\nConnected to userinfo\n");
       
       
         userinfo info= accountCRUD.readInfo(username);
         
        infoXML information = new infoXML();
        information.setInfo(info);
        //information.getInfo();

        System.out.println(">>>>>>>>>>>>>>>>>>>"+information);
        
        JAXBContext jaxbContext;
        try{
            jaxbContext=JAXBContext.newInstance(infoXML.class);
            
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
             StringWriter sw = new StringWriter();
            
            jaxbMarshaller.marshal(information, sw);
            
            return sw.toString();
            
        }catch(Exception e)
        {
            System.out.println(e);
            return ("error");
        }
        
    }

    /**
     * PUT method for updating or creating an instance of UserinfoResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(java.lang.String content) {
    }
}
