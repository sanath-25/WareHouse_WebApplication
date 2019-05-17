<%-- 
    Document   : Firstpage_warehouse
    Created on : Apr 30, 2018, 6:09:35 PM
    Author     : victo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial;}

/* Style the tab */
.tab {
    overflow: hidden;
    border: 1px solid #ccc;
    background-color: #f1f1f1;
}

/* Style the buttons inside the tab */
.tab button {
    background-color: inherit;
    float: left;
    border: none;
    outline: none;
    cursor: pointer;
    padding: 14px 16px;
    transition: 0.3s;
    font-size: 17px;
}

/* Change background color of buttons on hover */
.tab button:hover {
    background-color: #ddd;
}

/* Create an active/current tablink class */
.tab button.active {
    background-color: #ccc;
}

/* Style the tab content */
.tabcontent {
    display: none;
    padding: 6px 12px;
    -webkit-animation: fadeEffect 1s;
    animation: fadeEffect 1s;
}

/* Fade in tabs */
@-webkit-keyframes fadeEffect {
    from {opacity: 0;}
    to {opacity: 1;}
}

@keyframes fadeEffect {
    from {opacity: 0;}
    to {opacity: 1;}
}
</style>
</head>
<body>

<h3>Hello Warehouse Manager!</h3>
<div class="tab">

<button id="sub1" class="tablinks" onclick="cs();">Product information</button>
<button id="sub2" class="tablinks" onclick="pr();">Customers</button>
<button id="sub3" class="tablinks" onclick="vx();">Message Alerts</button>
<button id="sub3" class="tablinks" onclick="xn();">Stock Status</button>
    
 </div>

<div id="div" display="none"></div>
<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"
			type="text/javascript"></script>

<script>
			function cs() {
				var selectBox = document.getElementById("sub1").value;
				
				//$(".div").css("visibility", "visible");
				$('#div').show();
				$.ajax({
					type : "GET",
					url : '/webproject/Productinformation',
					dataType : "text",
					contentType : "application/json; charset=utf-8",
					
					async : false,
					/* 		        		     success: function(response) { 
					 $("#div").html(response);  */
					success : function(xml, status, xhr) {
						divhtml = xhr.responseText;
						$('#div').html(divhtml);
					}
				});
			}
                        
                 
			function pr() {
				var selectBox = document.getElementById("sub2").value;
				
				//$(".div").css("visibility", "visible");
				$('#div').show();
				$.ajax({
					type : "GET",
					url : '/webproject/Customers',
					dataType : "text",
					contentType : "application/json; charset=utf-8",
					
					async : false,
					/* 		        		     success: function(response) { 
					 $("#div").html(response);  */
					success : function(xml, status, xhr) {
						divhtml = xhr.responseText;
						$('#div').html(divhtml);
					}
				});
			}       
                        
                        
                       function vx() {
				var selectBox = document.getElementById("sub3").value;
				
				//$(".div").css("visibility", "visible");
				$('#div').show();
				$.ajax({
					type : "GET",
					url : '/webproject/Messagealerts',
					dataType : "text",
					contentType : "application/json; charset=utf-8",
					
					async : false,
					/* 		        		     success: function(response) { 
					 $("#div").html(response);  */
					success : function(xml, status, xhr) {
						divhtml = xhr.responseText;
						$('#div').html(divhtml);
					}
				});
                            }
                            
                            
                              function xn() {
				var selectBox = document.getElementById("sub3").value;
				
				//$(".div").css("visibility", "visible");
				$('#div').show();
				$.ajax({
					type : "GET",
					url : '/webproject/Stockstatus',
					dataType : "text",
					contentType : "application/json; charset=utf-8",
					
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
     
</body>
</html> 
