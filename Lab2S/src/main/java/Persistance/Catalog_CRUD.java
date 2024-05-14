/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistance;

import Helper.UserInfo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author student
 */
public class Catalog_CRUD {
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
    
    public static UserInfo read(String username, String password)
    {
    UserInfo bean= null;
    
        try
        {
            Connection con=getCon();
             
            String q="select * from ACCOUNT WHERE username LIKE \""+ username+"\"";
            System.out.println(q);
            Statement ps= con.createStatement();
            ResultSet rs= ps.executeQuery(q);
           System.out.println(rs.toString());
            while(rs.next())
            {   
                int userID=rs.getInt("userID");
                String userName=rs.getString("username");
                String pass=rs.getString("password");
                String paymentInfo=rs.getString("paymentInfo");
                System.out.println(paymentInfo);
                
                if(pass.equals(password))
                {
                System.out.println(password);
                bean=new UserInfo(userName,pass,paymentInfo,userID);
                break;
                }
            }
           con.close();
        }
        catch(Exception e){System.out.println(e);}
    
      return bean;
    
    }
       
}
