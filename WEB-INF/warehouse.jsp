<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<br>
	<h2>The warehouse manager is</h2>
	<h1>${wm}</h1>
	<br>
	
	<div class="row" id = "elements">
	<c:forEach items="${items}" var="item">
		
		<div class="column" id="column">
			<c:out value="${item.tree}" />
			<img src="${pic}" width="100" height="100" >
			<c:out value="${item.branches}" />
					
	 <br> 
		</div>

	</c:forEach>
</div>
	<!--   <img src="https://plants.usda.gov/gallery/large/qupa2_002_lhp.jpg" width="100" height="100" >
    -->
	<h2>${data}</h2>
</body>

<script>

var elements = document.getElementById("column");
var i;
function listView() {
    for (i = 0; i < elements.length; i++) {
        elements[i].style.width = "50%";
    }
}


</script>

</html>