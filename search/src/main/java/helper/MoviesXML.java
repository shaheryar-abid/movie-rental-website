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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;  
import javax.xml.bind.annotation.XmlRootElement; 
/**
 *
 * @author student
 */
@XmlRootElement(name= "movies")
@XmlAccessorType(XmlAccessType.FIELD)
public class MoviesXML {
  //public static ArrayList<Movie_Info> readGen(String genre)
  @XmlElement(name="movie")
   private ArrayList<Movie_Info> movies;
  
  public ArrayList<Movie_Info>getMovies()
  {
      return movies;
  }
  public MoviesXML()
  {
  
  }
  public void setMovie(ArrayList<Movie_Info> g)
  {
      movies=g;
  }
    
}
