<%-- 
    Document   : Add_address
    Created on : Apr 30, 2018, 4:21:57 PM
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
        <h1>Add New address</h1>
  <form name="myForm" action="/webproject/displayaddress" method="GET">
            <table border="0">
                <tbody>
                    <tr>
                        <td>Address line: </td>
                        <td><input type="text" name="addressline" placeholder="addressline" value="" size="50" /></td>
                    </tr>
                    <tr>
                        <td>City: </td>
                        <td><input type="text" name="city" placeholder="city" value="" size="50" /></td>
                    </tr>
                    <tr>
                        <td>State: </td>
                        <td><input type="text" name="state" placeholder="state" value="" size="50" /></td>
                    </tr>
                    <tr>
                        <td>Zip code:</td>
                        <td><input type="text" name="zipcode" placeholder="zipcode" value="" size="5" /></td>
                    </tr>                    
                    <tr>
                        <td>Region: </td>
                        <td><select name="region" id="region">
                                <option>Center America</option>
                                <option>North America</option>
                                <option>South America</option>
                                <option>Africa</option>
                                <option>Middle East</option>
                                <option>Asia</option>
                                <option>Europe</option>
                            </select></td>
                    </tr>
                    </tbody>
            </table>
            <input type="reset" value="Clear" name="clear" />
            <input type="submit" value="Submit" name="submit" />
        </form>
    </body>
</html>

