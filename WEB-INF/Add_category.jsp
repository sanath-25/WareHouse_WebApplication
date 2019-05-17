<%-- 
    Document   : Add_category
    Created on : Apr 30, 2018, 4:40:07 PM
    Author     : victo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}

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

<h3>Add Category</h3>
<form name="myForm" action="/webproject/displaycategory" method="GET">
    
    <div class="container">
      <!--   <form action="">
       -->  <label for="fname">Category Name</label>
        <input type="text" id="fname" name="categoryname" placeholder="Category name is..">

        <label for="subject">Category Description</label>
        <textarea id="subject" name="categorydescription" placeholder="Please describe this category.." style="height:200px"></textarea>

        <input type="submit" value="Submit">
</div>
</form>    

</body>
</html>