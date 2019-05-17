<%-- 
    Document   : Add_product
    Created on : Apr 30, 2018, 5:06:37 PM
    Author     : victo
--%>

<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

input[type=text], select, textarea {
	width: 100%;
	padding: 12px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	margin-top: 6px;
	margin-bottom: 16px;
	resize: vertical;
}

input[type=submit] {
	background-color: #4CAF50;
	color: white;
	padding: 12px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

input[type=submit]:hover {
	background-color: #45a049;
}

.container {
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
}
</style>
</head>
<body>

	<h3>Add Product</h3>
	<form name="myForm" action="/webproject/displayproduct" method="GET">

		<div class="container">
			<label>select category</label> <select id="cat" name="cat">
				<c:forEach items="${categories}" var="item">
					<option value="${item}">${item}</option>
					<c:out value="${item}" />
				</c:forEach>
			</select>
		</div>
		<div class="container">
			<label>select Region</label> <select id="region" name="region">

				<option value="Center America">Center America</option>
				<option value="North America">North America</option>
				<option value="South America">South America</option>
				<option value="Asia">Asia</option>
				<option value="Europe">Europe</option>
				<option value="Africa/Middle East">Africa/Middle East</option>
			</select>


			<%--  <c:forEach  items="${Region}" var="item">
			<option value="${item}">${item}</option>
			<c:out value="${item}" />			
		</c:forEach> --%>

		</div>

		<div class="container">
			<label for="fname">Product Name</label> <input type="text" id="name"
				name="productname" placeholder="Product name is.."> <label
				for="fname">Product Quantity</label> <input type="text"
				id="quantity" name="productquantity"
				placeholder="Please put number here.."> <label for="fname">Product
				colour</label> <input type="text" id="name" name="productcolour"
				placeholder="Product colour is.."> <label for="fname">Product
				size</label> <input type="text" id="name" name="productsize"
				placeholder="Product size is.."> <label for="fname">Product
				weight</label> <input type="text" id="name" name="productweight"
				placeholder="Product weight is.."> <label for="fname">Product
				price</label> <input type="text" id="name" name="productprice"
				placeholder="Product price is.."> <label for="subject">Product
				Description</label>
			<textarea id="subject" name="productdescription"
				placeholder="Please describe your product.." style="height: 200px"></textarea>

			<input type="submit" value="Submit"> <input type="reset"
				value="Clear" />
		</div>



	</form>

</body>
</html>