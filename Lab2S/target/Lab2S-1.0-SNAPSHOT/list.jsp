<%-- 
    Document   : list
    Created on : Feb 26, 2024, 10:35:09 PM
    Author     : student
--%>

<%@page import="Helper.Movie_Info"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List of books</title>
    </head>
    <body>
      <% 
      ArrayList<Movie_Info> list= (ArrayList)session.getAttribute("genMovies");
      
      if (list != null&& list.size()>0) {
            %>
            <table>
                <tr>
                    <th>Price</th>
                    <th>Rating</th>
                    <th>Title</th>
                </tr>
                <% for (Movie_Info movie : list) { %>
                    <tr>
                        <td><%= movie.getPrice() %></td>
                        <td><%= movie.getRatings() %></td>
                        <td><%= movie.getTitle() %></td>
                    </tr>
                <% } %>
            </table>
        <% } else { %>
            <p>No movies available.</p>
        <% } %>
    </body>
</html>
