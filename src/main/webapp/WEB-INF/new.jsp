<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="/css/style.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <title>Add New Song</title>
</head>
<body>
	<!-- NAV BAR -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/dashboard">Dashboard</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="/songs/new"><strong>Add New Song</strong> <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/search/topTen">Top Songs</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/logout">Log Out</a>
                </li>
            </ul>
        </div>
    </nav>

    <div class="container addsong-page mt-2">
        <form:form action="/songs" method="post" modelAttribute="song" class="form-addsong">
            <h3 class="text-center mb-3">Add New Song</h3>
            <p>
                <form:input path="title" placeholder="Title" id="inputSongTitle" class="form-control" />
                <form:errors path="title" style="color: red;"/>
            </p>
            <p>
                <form:input path="artist" placeholder="Artist" id="inputSongArtist" class="form-control"/>
                <form:errors path="artist" style="color: red;"/>
            </p>   
            <p>
            	<form:select path="genre" id="inputSongGenre" class="form-control">
                	<form:option value=""> -- GENRE -- </form:option>
                	<form:option value="pop">Pop</form:option>
                	<form:option value="hip hop">Hip-Hop</form:option>
                	<form:option value="dance electronic">Dance/Electronic</form:option>
                	<form:option value="country">Country</form:option>
                	<form:option value="rock">Rock</form:option>
                	<form:option value="alternative">Alternative</form:option>
                	<form:option value="latin">Latin</form:option>
                	<form:option value="international">International</form:option>
                	<form:option value="rhythm and blues">Rhythm and Blues</form:option>
                </form:select>
                <form:errors path="genre" style="color: red;"/>
            </p>
            <p>
            	<form:select path="rating" id="inputSongRating" class="form-control">
            		<form:option value="5">5</form:option>
            		<form:option value="4">4</form:option>
            		<form:option value="3">3</form:option>
            		<form:option value="2">2</form:option>
            		<form:option value="1">1</form:option>
            	</form:select>
                <form:errors path="rating" style="color: red;"/>
            </p>
            <p>
                <form:input path="youtubelink" placeholder="YouTube Video ID" id="inputYouTubeLink" class="form-control"/>
                <small id="inputYouTubeLinkDesc">E.g: youtube.com/watch?v=<strong><span class="text-danger">YOUTUBE_VIDEO_ID<span></span></strong></small>
            	<form:errors path="youtubelink" style="color: red;"/>
            </p>  
            <form:input type="hidden" path="user" value="${user.id}"/>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
        </form:form>
    </div>
    
    <!-- JavaScript -->
	<script type="text/javascript" src="js/app.js"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  
</body>
</html>