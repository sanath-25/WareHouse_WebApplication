<%-- 
    Document   : displaycategory
    Created on : Apr 30, 2018, 4:54:04 PM
    Author     : victoria
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Added New Category</title>
    </head>
    <body>
        <h1>Added New Category</h1>
       
            <table border="1">
                <tbody>
                    <tr>
                        <td>Category Name </td>
                        <td> ${cname}</td>
                    </tr>
                    <tr>
                        <td>Category Description: </td>
                        <td>${cdec}</td>
                    </tr>                   
                </tbody>
            </table>

    </body>
</html>

