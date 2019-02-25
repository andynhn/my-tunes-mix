<!-- By Andy N.H. Nguyen - https://andynhn.me/ - https://github.com/andynhn/ -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="/css/style.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
	<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <title>Edit ${user.username}'s Profile</title>
</head>
<body>
	<!-- NAV BAR -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-black">
    	<a class="navbar-brand" href="/"><span class="brand"><span class="fa fa-music"></span> <strong>My Tunes Mix</strong></span></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/home">Library</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/songs/new">Add New Tunes</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/search/topTen">Your Top 10</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/discover">Discover</a>
                </li>
				<li class="nav-item dropdown active">
					<a class="dropdown-toggle nav-link" data-toggle="dropdown" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><strong>Settings<span class="caret"></span></strong> <span class="caret"></span></a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="/users/${user.id}/edit">Edit Profile</a>
					</div>
				</li>
                <li class="nav-item">
                    <a class="nav-link" href="/logout">Log Out <span class="fa fa-sign-out"></span></a>
                </li>
            </ul>
        </div>
    </nav>
    
    <div class="container mt-3 d-flex justify-content-between">
		<a href="/home"><button class="btn btn-sm btn-info my-2 my-sm-0">Cancel</button></a>
    	<span class="text-center my-2 my-sm-0 h5">Edit Profile</span>
		<span><button class="btn btn-sm btn-danger my-2 my-sm-0" data-toggle="modal" data-target="#confirmDeletionModalCenter">
			Delete Account
		</button></span>
	</div>
	
	<div class="container editprofile-page mt-2">
	    <form:form method="POST" action="/users/${user.id}/update" modelAttribute="user" class="form-updateprofile">
	    	<p class="edit-profile-block">
	    		<form:label path="email" class="edit-profile-label">Email:</form:label>
		        <form:input type="email" path="email" id="inputEmailUpdate" class="form-control" required="required"/>
	            <form:errors path="email" class="red edit-profile-errors"/>
            </p>
            <p class="edit-profile-block">
            	<form:label path="username" class="edit-profile-label">Username (3-20 characters):</form:label>
		        <form:input type="text" path="username" id="inputUsernameUpdate" class="form-control" required="required"/>
	            <form:errors path="username" class="red edit-profile-errors"/>
            </p>
            <p class="edit-profile-block">
            	<form:label path="fname" class="edit-profile-label">First Name:</form:label>
		        <form:input type="text" path="fname" id="inputFnameUpdate" class="form-control" required="required"/>
		        <form:errors path="fname" class="red edit-profile-errors"/>
	        </p>
	        <p class="edit-profile-block">
	        	<form:label path="lname" class="edit-profile-label">Last Name:</form:label>
		        <form:input type="text" path="lname" id="inputLnameUpdate" class="form-control" required="required"/>
		        <form:errors path="lname" class="red edit-profile-errors"/>
	        </p>
	        <p class="edit-profile-block">
	        	<form:label path="password" class="edit-profile-label">New Password (at least 8 characters):</form:label>
		        <form:password path="password" id="inputPasswordUpdate" class="form-control" required="required"/>
		        <form:errors path="password" class="red edit-profile-errors"/>
	        </p>
	        <p class="edit-profile-block">
	        	<form:label path="passwordConfirmation" class="edit-profile-label">Confirm New Password:</form:label>
		       	<form:password path="passwordConfirmation" id="inputConfirmPasswordUpdate" class="form-control" required="required"/>
		        <form:errors path="passwordConfirmation" class="red edit-profile-errors"/>
	        </p>
	        <button class="btn btn-md btn-primary btn-block mt-4" type="submit">Submit Changes</button>
	    </form:form>
   	</div>
   	
   	<!-- Modal -->
	<div class="modal fade" id="confirmDeletionModalCenter" tabindex="-1" role="dialog" aria-labelledby="confirmDeletionModalCenter" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="confirmDeletionnModalCenterLongTitle">Delete Account</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<p>Are you sure you want to permanently delete your account?</p>
			</div>
			<div class="modal-footer">
				<form action="/users/${user.id}" method="post">
                    <input type="hidden" name="_method" value="delete">
                    <button class="btn btn-danger" type="submit">Confirm Account Deletion</button>
                </form>
			</div>
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