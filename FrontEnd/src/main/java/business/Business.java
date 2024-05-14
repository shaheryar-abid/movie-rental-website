/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import helper.Movie_Info;
import helper.MoviesXML;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.io.IOUtils;


/**
 *
 * @author student
 */
public class Business {
private static Connection getCon()
    {
    Connection con= null;
        System.out.println("Rahh");
        try
        {//jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL [root on Default schema]
           Class.forName("com.mysql.jdbc.Driver");
           con= DriverManager.getConnection("jdbc:mysql://localhost:3306/RentalWeb?autoReconnect=true&useSSL=false","root","student");
           System.out.println("Connection Established");
        }catch(Exception e)
        {
            System.out.println(e);
        }
        return con;
    }
    public static boolean isAuthenticated(String username, String password) {
       
    
    
    Boolean bean= false;
    
        try
        {
            Connection con=getCon();
             
            String q="select * from ACCOUNT WHERE username LIKE \""+ username+"\"";
            System.out.println(q);
            Statement ps= con.createStatement();
            ResultSet rs= ps.executeQuery(q);
          // System.out.println(rs.toString());
            while(rs.next())
            {   
                int userID=rs.getInt("userID");
                String userName=rs.getString("username");
                String pass=rs.getString("password");
                String paymentInfo=rs.getString("paymentInfo");
                System.out.println(pass+password);

                
                if(pass.equals(password))
                {
                System.out.println(password+"ww");
                bean=true;
                break;
                }
            }
           con.close();
        }
        catch(Exception e){System.out.println(e+"yuhhh");}
    
      return bean;
    
    }
    
    

    public static MoviesXML getServices(String query, String token) throws IOException {

        Client searchclient = ClientBuilder.newClient();
        WebTarget searchwebTarget
                = searchclient.target("http://localhost:8080/search/webresources/search");
        InputStream is
                = searchwebTarget.path(query).request(MediaType.APPLICATION_XML).get(InputStream.class);
        String xml = IOUtils.toString(is, "utf-8");
        MoviesXML Movies = moviexmltoObjects(xml);
        if (token != null) {
           /*  Client holdclient = ClientBuilder.newClient();
            WebTarget holdwebTarget
                    = holdclient.target("http://localhost:8080/HoldBook/webresources/hold/isOnHold");
           for ( Movie_Info movie : Movies.getMovies()) {

                InputStream holddata
                        = holdwebTarget.path(movie.getIs()).queryParam("token", token).
                                request(MediaType.APPLICATION_XML).get(InputStream.class);
                try{
                    Book a=bookholdxmltoObjects(IOUtils.toString(holddata, "utf-8"));
                    if(a!=null)
                        book.setTobeHold(true);
                    else
                        book.setTobeHold(false);
                }
                catch(Exception e){
                    book.setTobeHold(false);
                }
                
                
            }*/
        }

        return (Movies);

    }

    private static MoviesXML moviexmltoObjects(String xml) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(MoviesXML.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            MoviesXML books = (MoviesXML) jaxbUnmarshaller.unmarshal(new StringReader(xml));
            return books;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    

}
