<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@	taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Hi Admin</h2>
	<div>
		<h2>WareHouses We Have</h2>
		<select id="dropdown" onchange="cs();">
			<option value="">Select..</option>
			<c:forEach items="${list}" var="item">
				<option value="${item}">${item}</option>
			</c:forEach>
		</select>
		<div>
			<!-- <img
				src="http://tigerday.org/wp-content/uploads/2013/04/Siberischer_tiger.jpg"
				width="100" height="100"> -->
		</div>
		<div id="div" display="none"></div>
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"
			type="text/javascript"></script>
		<script>
			function cs() {
				var selectBox = document.getElementById("dropdown").value;
				alert(selectBox);
				alert("hi");
				//$(".div").css("visibility", "visible");
				$('#div').show();
				$.ajax({
					type : "GET",
					url : '/webproject/warehouse',
					dataType : "text",
					contentType : "application/json; charset=utf-8",
					data : {
						"data" : selectBox
					},
					async : false,
					/* 		        		     success: function(response) { 
					 $("#div").html(response);  */
					success : function(xml, status, xhr) {
						divhtml = xhr.responseText;
						$('#div').html(divhtml);
					}
				});
			}
		</script>
	</div>
</body>
</html>