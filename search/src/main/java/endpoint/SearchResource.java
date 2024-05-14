/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endpoint;

import helper.Movie_Info;
import helper.MoviesXML;
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
import persistence.genreCRUD;

/**
 * REST Web Service
 *
 * @author student
 */
@Path("search")
public class SearchResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SearchResource
     */
    public SearchResource() {
    }

    /**
     * Retrieves representation of an instance of endpoint.SearchResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml(@PathParam("query")String genre) {
        //TODO return proper representation object
        System.out.println("\nConnected to search\n");
        StringWriter sw = new StringWriter();
        ArrayList<Movie_Info> list= new ArrayList<Movie_Info>();
        list= genreCRUD.readGen(genre);
        MoviesXML books = new MoviesXML();
        books.setMovie(list);
        System.out.println(">>>>>>>>>>>>>>>>>>>"+books);
        
        JAXBContext jaxbContext;
        try{
            jaxbContext=JAXBContext.newInstance(MoviesXML.class);
            
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            
            
            jaxbMarshaller.marshal(books, sw);
            
            return sw.toString();
            
        }catch(Exception e)
        {
            System.out.println(e);
        }
        return sw.toString();
    }

    /**
     * PUT method for updating or creating an instance of SearchResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
