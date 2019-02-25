<!-- By Andy N.H. Nguyen - https://andynhn.me/ - https://github.com/andynhn/ -->
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
	<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <title>Register</title>
    <style>
   		body, html {
		  height: 100%;
		  margin: 0;
		  background: black;
		}
   		.bg {
			background-image: url("/img/record-player.jpg");
			height: 100%; 
			background-position: center;
			background-repeat: repeat;
			background-size: cover;
		}
		#redirect-to-login {
			color: gold;
		}
    </style>
</head>
<body>
	<div class="bg">
		<!-- NAV BAR -->
		<nav class="navbar navbar-expand-lg navbar-dark bg-black-opacity">
	    	<a class="navbar-brand" href="/"><span class="brand"><span class="fa fa-music"></span> <strong>My Tunes Mix</strong></span></a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active">
						<a class="nav-link" href="/register"><span class="fa fa-user"></span> <strong>Register</strong> <span class="sr-only">(current)</span></a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/login"><span class="fa fa-sign-in"></span> Login</a>
					</li>
				</ul>
			</div>
		</nav>
		
		<div class="container register-page">
		    <form:form method="POST" action="/register" modelAttribute="user" class="form-register">
		        <form:input type="email" path="email" placeholder="Email" id="inputEmailRegister" class="form-control" required="required" autofocus="autofocus"/>
	            <form:errors path="email" class="gold"/>
		        <form:input type="text" path="username" placeholder="Username (3-20 characters)" id="inputUsername" class="form-control" required="required"/>
	            <form:errors path="username" class="gold"/>
		        <form:input type="text" path="fname" placeholder="First Name" id="inputFname" class="form-control" required="required"/>
		        <form:errors path="fname" class="gold"/>
		        <form:input type="text" path="lname" placeholder="Last Name" id="inputLname" class="form-control" required="required"/>
		        <form:errors path="lname" class="gold"/>
		        <form:password path="password" placeholder="Password (at least 8 characters)" id="inputPasswordRegister" class="form-control" required="required"/>
		        <form:errors path="password" class="gold"/>
		        <form:errors path="passwordConfirmation" class="gold"/>
		        <form:password path="passwordConfirmation" placeholder="Confirm Password" id="inputConfirmPassword" class="form-control" required="required"/>
		        <button class="btn btn-md btn-warning btn-block" type="submit">Register</button>
	            <p class="mt-3 white">Already have an account? <a id="redirect-to-login" href="/login">Log in.</a></p>
		    </form:form>
	
	   	</div>
   	
   	</div>
   	
   <!-- JavaScript -->
	<script type="text/javascript" src="js/app.js"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  
</body>
</html>