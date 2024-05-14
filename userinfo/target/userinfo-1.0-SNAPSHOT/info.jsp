<%-- 
    Document   : info
    Created on : Mar 25, 2024, 2:57:16 PM
    Author     : student
--%>

<%-- 
    Document   : list
    Created on : Mar 24, 2024, 9:50:07 PM
    Author     : student
--%>

<%@page import="helper.account_Info"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account information</title>
    </head>
    <body>
      <% 
      ArrayList<User_Info> list= (ArrayList)session.getAttribute("userInfo");
      
      if (list != null&& list.size()>0) {
            %>
            <table>
                <tr>
                    <th>Username</th>
                    <th>Password</th>
                </tr>
                <% for (account_Info info : list) { %>
                    <tr>
                        <td><%= info.getUsername() %></td>
                        <td><%= info.getPassword() %></td>
                    </tr>
                <% } %>
            </table>
        <% } else { %>
            <p>Account does not exist.</p>
        <% } %>
    </body>
</html>

