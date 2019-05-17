<%-- 
    Document   : Messagealerts
    Created on : Apr 30, 2018, 6:13:36 PM
    Author     : victo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
* {
    box-sizing: border-box;
}

/* Create three equal columns that floats next to each other */
.column {
    float: left;
    width: 33.33%;
    padding: 10px;
    height: 300px; /* Should be removed. Only for demonstration */
}

/* Clear floats after the columns */
.row:after {
    content: "";
    display: table;
    clear: both;
}
</style>
</head>
<body>

<h2>Message Alerts</h2>

<div class="row">
  <div class="column" style="background-color:#aaa;">
    <h2>Warehouse current Capacity</h2>
    <p>Warehouse current Capacity is.. ${b}</p>
  </div>
  <div class="column" style="background-color:#bbb;">
    <h2>Remaining Capacity</h2>
    <p>Remaining Capacity is..${c}</p>
  </div>
</div>

</body>
</html>
