/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import business.userinfo;
import java.util.ArrayList; 
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;  
import javax.xml.bind.annotation.XmlRootElement; 

/**
 *
 * @author student
 */
@XmlRootElement(name= "info")
@XmlAccessorType(XmlAccessType.FIELD)
public class infoXML {
      //public static ArrayList<Movie_Info> readGen(String genre)
  //@XmlElement(name="info")
   private userinfo info;
  
  public userinfo getInfo()
  {
      return info;
  }
  public infoXML()
  {
  
  }
  public void setInfo(userinfo g)
  {
      info=g;
  }
    
}
