<!-- By Andy N.H. Nguyen - https://andynhn.me/ - https://github.com/andynhn/
Welcome! Thanks for checking out my application. I had a fun time building it, and I hope you like it!

This site is for educational purposes and to demonstrate my ability to build Java/Spring applications.

Version 1 deployed February 2019
-->
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
    <style>
		body, html {
		  height: 100%;
		  margin: 0;
		  background: black;
		}
		.bg {
			background-image: url("/img/dj-mix.jpg");
			height: 100%; 
			background-position: center;
			background-repeat: no-repeat;
			background-size: cover;
		}
	</style>
</head>
<body>
	<div class="bg">
		<div id="wrapper">
			<div id="wrapper-opacity"></div>
			<div id="wrapper-text">
				<p style="color: red;"><c:out value="${deletion}" /></p>
				<h1 id="home-title"><strong>My Tunes Mix</strong></h1>
				<p id="home-sub-title">Add your favorite YouTube music videos</p>
				<p class="mb-3" id="home-sub-title">Discover new music</p>
				<a href="/register" class="btn btn-lg btn-warning"><strong>Register</strong></a><a href="/login" class="btn btn-lg btn-light ml-2"><strong>Log In</strong></a>
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