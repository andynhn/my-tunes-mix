<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="/css/style.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <title>My Tunes Mix</title>
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
				<li class="nav-item">
					<a class="nav-link" href="/register">Register</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/login">Log In</a>
				</li>
			</ul>
		</div>
	</nav>
			
    <!-- JUMBOTRON -->
   	<div class="jumbotron text-center">
   		<a href="/register" class="btn btn-lg btn-warning"><strong>Register</strong></a><a href="/login" class="btn btn-lg btn-light ml-2"><strong>Log In</strong></a>
 	</div>
    <!-- INFO BOXES -->
    <div class="container">
    	<div class="row footer">
    		<div class="col-xs-4 col-md-4">
    			<h5>Register in seconds</h5>
    			<p>Register in seconds.</p>
   			</div>
  			<div class="col-xs-4 col-md-4">
  				<h5>Save Your Favorite Songs</h5>
  				<p>Add your favorite YouTube music videos</p>
 			</div>
 			<div class="col-xs-4 col-md-4">
 				<h5>View the most popular songs</h5>
 				<p>See which songs are the most popular in the app</p>
			</div>
		</div>
	</div>
	
    <!-- JavaScript -->
	<script type="text/javascript" src="js/app.js"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  
</body>
</html>