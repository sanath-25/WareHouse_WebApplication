<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Product</title>
</head>
<body>
   hello customer! Please see your products
   
	<div class="row" id = "elements">
	<c:forEach items="${products}" var="item">
		
		<div class="column" id="column">
			<c:out value="${item.productName}" />
		<%-- 	<img src="${pic}" width="100" height="100" > --%>
			<c:out value="${item.quantity}" />
					
	 <br> 
		</div>

	</c:forEach>
</div>
   
   
</body>
</html>