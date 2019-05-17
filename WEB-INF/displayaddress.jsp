<%-- 
    Document   : displayaddress
    Created on : Apr 30, 2018, 4:32:11 PM
    Author     : victo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New address</title>
    </head>
    <body>
        <h1>You have these addresses</h1>
   <%--      <%
            String addressline = request.getParameter("addressline");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String zipcode = request.getParameter("zipcode");
            String region = request.getParameter("region");
            %>
            <table border="1">
                <tbody>
                    <tr>
                        <td>Address Line: </td>
                        <td><%= addressline %></td>
                    </tr>
                    <tr>
                        <td>City: </td>
                        <td><%= city %></td>
                    </tr>
                    <tr>
                        <td>State:</td>
                        <td><%= state %></td>
                    </tr>
                    <tr>
                        <td>Zip Code:</td>
                        <td><%= zipcode %></td>
                    </tr>
                    <tr>
                        <td>Region:</td>
                        <td><%= region %></td>
                    </tr>      
                </tbody>
            </table>
 --%>
 
	<div class="row" id = "elements">
	<c:forEach items="${addresess}" var="item">
		
		<div class="column" id="column">
			<c:out value="${item.country}" />
			<c:out value="${item.address}" />			
			<c:out value="${item.state}" />						
			<c:out value="${item.city}" />			
			<c:out value="${item.zipCode}" />				
			
  
		</div>
		
		<br><br><br>

	</c:forEach>
</div>
 
 
 
 
    </body>
</html>
