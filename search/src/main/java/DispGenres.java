/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import helper.Movie_Info;

import persistence.genreCRUD;
import java.util.ArrayList;

/**
 *
 * @author student
 */
@WebServlet(urlPatterns = {"/DispGenres"})
public class DispGenres extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DispGenres</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DispGenres at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String genre=(String) request.getParameter("genre");
        
        System.out.println(genre);
       
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
       
        
            request.getSession().setAttribute("genMovies", getGenList(genre));
            
          
            RequestDispatcher rd= request.getRequestDispatcher("list.jsp");
            rd.forward(request, response);
        processRequest(request, response);
    
    }
    private ArrayList<Movie_Info> getGenList(String genre) {
        /**
         * to be completed. For now, we just return a user info object that has a default book in a default date;
         * This method must return null when user name and password is incorrect
         * otherwise it must return an object containing all books that have been borrowed by the user, in addition to user information like name, address, ...
         */
        //userInfo uf=null;
        ArrayList<Movie_Info> list= new ArrayList<Movie_Info>();
        list= genreCRUD.readGen(genre);
        return list;
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
