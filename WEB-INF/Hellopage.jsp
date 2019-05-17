<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	HI this is hello Page

	<h2>${message}</h2>

	<form action="/webproject/submit1" method="post">
		First name: <input type="text" name="fname"><br> Last
		name: <input type="text" name="lname"><br> Select: <select>
			<option value="volvo">Volvo</option>
			<option value="saab">Saab</option>
			<option value="mercedes">Mercedes</option>
			<option value="audi">Audi</option>
		</select>
		 <input type="submit" value="Submit">
	</form>


</body>
</html>