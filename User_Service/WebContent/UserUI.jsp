<%@ page import="com.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Service</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.4.1.min.js"></script> 
<script src="Components/validation.js"></script> 
</head>
<body>
<div class="container"> 
	<div class="row">  
		<div class="col-6"> 
			<h1>USER SERVICE</h1>
				<form id="formUser" name="formUser" method="post" action="UserUI.jsp">  
					<!-- NAME -->
	<div class="input-group input-group-sm mb-3">
	<div class="input-group-prepend">
 	<span class="input-group-text" id="lblName">Name: </span>
 	</div>
 	<input type="text" id="txtName" name="txtName">
 	</div>
 
 	<!-- EMAIL -->
 	<div class="input-group input-group-sm mb-3">
	<div class="input-group-prepend">
 	<span class="input-group-text" id="lblName">Email: </span>
	</div>
	<input type="text" id="txtEmail" name="txtEmail">
	</div>
	
	<!-- PASSWORD -->
	<div class="input-group input-group-sm mb-3">
	<div class="input-group-prepend">
 	<span class="input-group-text" id="lblName">Password: </span>
	</div>
	<input type="text" id="txtPassword" name="txtPassword">
	</div>
	
	<!-- CONTACT NO -->
	<div class="input-group input-group-sm mb-3">
	<div class="input-group-prepend">
 	<span class="input-group-text" id="lblName">Contact No: </span>
	</div>
	<input type="text" id="txtcontactNo" name="txtcontactNo">
	</div>
	
	<!-- ADDRESS -->
	<div class="input-group input-group-sm mb-3">
	<div class="input-group-prepend">
 	<span class="input-group-text" id="lblName">Address: </span>
	</div>
	<input type="text" id="txtAddress" name="txtAddress">
	</div>
	
	<!-- GENDER --
	<div class="input-group input-group-sm mb-3">
 	<div class="input-group-prepend">
 	<span class="input-group-text" id="lblName">Gender: </span>
	</div>
	 &nbsp;&nbsp;Male 
	<input type="radio" id="rdoGenderMale" name="rdoGender" value="Male">
 	&nbsp;&nbsp;Female 
	<input type="radio" id="rdoGenderFemale" name="rdoGender" value="Female">
 	</div>>
 	
 	<!-- USER TYPE -->
 	<div class="input-group input-group-sm mb-3">
	<div class="input-group-prepend">
 	<span class="input-group-text" id="lblName">User Type: </span>
	</div>
	<input type="text" id="txtAddress" name="txtAddress">
	</div>
 	</form>
				
				<div id="alertSuccess" class="alert alert-success"> </div>
				
			   <div id="alertError" class="alert alert-danger"></div>
				
			   <br>
				<div id="divUserGrid">
					<%
						User userObj = new User();
									out.print(userObj.readUser());
					%>
				</div>
				
				 
			</div>
		</div>
</div>
 
</body>
</html>