<%-- 
    Document   : userHome
    Created on : Feb 5, 2024, 9:40:38 PM
    Author     : student
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
        <style>
        body
        {
            
        background-color:#CBC9AD;
            
        }</style>
        
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Homepage</title>
    </head>
    <body>
        <h1><FONT COLOR="#514B23">Hello <%=session.getAttribute("uname")%> here are your details</FONT></h1>
       
     <table border="1" align="centre" cellpadding="5" cellspacing="5">
       
         <td>Name: 
             <%=request.getAttribute("name")%></td>>
         <td>UserName: 
             <%=request.getAttribute("Uname")%></td>>
         <td>Password: 
             <%=request.getAttribute("pass")%></td>>
         <td>Credit: 
             <%=request.getAttribute("Credit")%></td>>
     </table>
     <form action="DispGenres" method="post"> 
     <table border="1" align="centre" cellpadding="5" cellspacing="5">
     <tr>
         <td>Enter Genre You want to Search</td>
         <td><input type= "text" name="genre" size="30"></td>
     </tr>>
        
     <tr>
         <td>
          <input type="submit" value="Enter">
         </td>
         </tr>
     </table>>
     </form>
    </body>
</html>
