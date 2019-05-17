<%-- 
    Document   : displayproduct
    Created on : Apr 30, 2018, 5:12:03 PM
    Author     : victo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New Product</title>
    </head>
    <body>
        <h1>Add New Product</h1>
        <%
            String productname = request.getParameter("productname");
            String productquantity = request.getParameter("productquantity");
            String productdescription = request.getParameter("productdescription");
                 %>
            <table border="1">
                <tbody>
                    <tr>
                        <td>Product Name: </td>
                        <td><%= productname %></td>
                    </tr>
                    <tr>
                        <td>Product Quantity: </td>
                        <td><%= productquantity %></td>
                    </tr> 
                    <tr>
                        <td>Product Description: </td>
                        <td><%= productdescription %></td>
                    </tr>                   
                </tbody>
            </table>

    </body>
</html>
