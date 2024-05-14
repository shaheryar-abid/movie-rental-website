/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

/**
 *
 * @author student
 */


import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

 @XmlRootElement(name = "movies")
@XmlAccessorType (XmlAccessType.FIELD)
   public class MoviesXML {
     @XmlElement(name="movie")
           private ArrayList<Movie_Info> Movie;
           
           
           public List<Movie_Info>getMovies(){
               return Movie;
               
           }
           MoviesXML(){
               
               
           }
           public void setBook(ArrayList<Movie_Info> bs){
                Movie=bs;
               
           }
           
       }