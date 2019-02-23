<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   
 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="/css/style.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <title>Register</title>
</head>
<body>
	<!-- NAV BAR -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    	<a class="navbar-brand" href="/"><span class="brand"><strong>My Tunes Mix</strong></span></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active">
					<a class="nav-link" href="/register"><strong>Register</strong> <span class="sr-only">(current)</span></a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/login">Login</a>
				</li>
			</ul>
		</div>
	</nav>

	<div class="container text-center mt-3">
		<!-- All errors displayed here if result from @Valid BindResult in controls returns errors -->
	    <p style="color: red;"><form:errors path="user.*"/></p>
	</div>
	
	<div class="container register-page">
	   	<!-- Register Form (Spring form) -->
	    <form:form method="POST" action="/register" modelAttribute="user" class="form-register">
	        <form:input type="email" path="email" placeholder="Email" id="inputEmailRegister" class="form-control" />
	        <form:input type="text" path="username" placeholder="Username" id="inputUsername" class="form-control" />
	        <form:input type="text" path="fname" placeholder="First Name" id="inputFname" class="form-control" />
	        <form:input type="text" path="lname" placeholder="Last Name" id="inputLname" class="form-control" />
	        <form:password path="password" placeholder="Password" id="inputPasswordRegister" class="form-control" />
	        <form:password path="passwordConfirmation" placeholder="Confirm Password" id="inputConfirmPassword" class="form-control" />
	        <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
            <!-- REDIRECT TO LOGIN -->
            <p class="mt-3"><a href="/login">Already have an account? Log in.</a></p>
	    </form:form>
   	</div>
   	
   <!-- JavaScript -->
	<script type="text/javascript" src="js/app.js"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  
</body>
</html>