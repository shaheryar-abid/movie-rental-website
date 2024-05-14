/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import helper.Movie_Info;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author student
 */
public class genreCRUD {
  
   private static Connection getCon()
    {
    Connection con= null;
    
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
    
    public static ArrayList<Movie_Info> readGen(String genre)
    {
    ArrayList<Movie_Info> list= new ArrayList<Movie_Info>();
    Movie_Info bean= null;
    
        try
        {
            Connection con=getCon();
             
            String q="select * from MOVIE WHERE genres LIKE \""+ genre+"\"";
            System.out.println(q);
            Statement ps= con.createStatement();
            ResultSet rs= ps.executeQuery(q);
           System.out.println(rs.toString());
            while(rs.next())
            {   
                int movieID=rs.getInt("movieID");
                int catalogID=rs.getInt("catalogsID");
                String genres=rs.getString("genres");
                int price=rs.getInt("price");
                String title=rs.getString("title");
                int ratings=rs.getInt("ratings");
                if(genre.equals(genres))
                {
                System.out.println(title);
                bean=new Movie_Info(movieID,catalogID,price,ratings,genres,title);
                list.add(bean);
                }
                
               
            }
           con.close();
        }
        catch(Exception e){System.out.println(e);}
        System.out.println(list.size());
      return list;
    
    }
            
}
