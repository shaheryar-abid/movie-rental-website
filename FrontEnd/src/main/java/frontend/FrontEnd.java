/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;


import business.Business;
import helper.MoviesXML;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.AbstractMap;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.NewCookie;


/**
 *
 * @author student
 */
@WebServlet(name = "FrontEnd", urlPatterns = {"/FrontEnd"})
public class FrontEnd extends HttpServlet {

    Authenticate autho;

    public FrontEnd() {
        autho = new Authenticate();
    }
    private final String authenticationCookieName = "login_token";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private Map.Entry<String, String> isAuthenticated(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String token = "";
        
        System.out.println("TOKEN IS");
        try {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName());
                if (cookie.getName().equals(authenticationCookieName)) {
                    token = cookie.getValue();
                }
            }
        } catch (Exception e) {

        }
        if (!token.isEmpty())
           try {
            if (this.autho.verify(token).getKey()) {
                  Map.Entry entry= new  AbstractMap.SimpleEntry<String, String>
                             (token,this.autho.verify(token).getValue());
            return entry;

            } else {
                 Map.Entry entry= new  AbstractMap.SimpleEntry<String, String>("","");
            return entry;
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FrontEnd.class.getName()).log(Level.SEVERE, null, ex);
        }

       Map.Entry entry= new  AbstractMap.SimpleEntry<String, String>("","");
            return entry;

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String token = isAuthenticated(request).getKey();
        String uname = isAuthenticated(request).getValue();
        String hiddenParam = request.getParameter("pageName");
        switch (hiddenParam) {
            case "login":
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                boolean isAuthenticated = Business.isAuthenticated(username, password);
                if (isAuthenticated) {
                       request.setAttribute("username", username);
                    token = autho.createJWT("FrontEnd", username, 100000);

                    Cookie newCookie = new Cookie(authenticationCookieName, token);
                    response.addCookie(newCookie);
                    RequestDispatcher requestDispatcher = request.
                            getRequestDispatcher("frontpageWithLogin.jsp");

                    requestDispatcher.forward(request, response);

                }
                else
                {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("frontpageWithoutLogin.jsp");

                    requestDispatcher.forward(request, response);
                    break;
                }
                break;
            case "search":

                String query=(String) request.getParameter("query");
        MoviesXML result;
                
        System.out.println(query+" 1");
     
        //processRequest(request, response);
        
                
                //String query = request.getParameter("query");
                if (token.isEmpty()) {
                    
                    result = retreiveServicesFromBackend(query, null);
                    request.setAttribute("genMovies", result);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("frontpageWithoutLogin.jsp");

                    requestDispatcher.forward(request, response);
                    break;
                    } else {
                    request.setAttribute("username", uname);
                    result = retreiveServicesFromBackend(query, token);

                    request.setAttribute("genMovies", result);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("frontpageWithLogin.jsp");

                    requestDispatcher.forward(request, response);
                    break;
                }
               
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
        
        
        processRequest(request, response);
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

    private MoviesXML retreiveServicesFromBackend(String query, String token) {
        try {
            return (Business.getServices(query, token));
        } catch (Exception ex) {
            Logger.getLogger(FrontEnd.class.getName()).log(Level.SEVERE, null, ex);
            return (null);
        }

    }

}
