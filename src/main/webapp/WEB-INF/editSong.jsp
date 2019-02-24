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
	<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <title>Edit ${song.title}</title>
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
                    <a class="nav-link" href="/songs/new">Add New Song</a>
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
                    <a class="nav-link" href="/logout">Log Out</a>
                </li>
            </ul>
        </div>
    </nav>
    
    <div class="container mt-3 d-flex justify-content-between">
		<a href="/home"><button class="btn btn-sm btn-info my-2 my-sm-0">Back to Library</button></a>
	</div>

    <div class="container addsong-page mt-2">
        <form:form action="/songs/${song.id}/update" method="post" modelAttribute="song" class="form-addsong">
            <h3 class="text-center mb-3">Edit <span class="capitalize">${song.title}</span></h3>
            <p>
                <form:input path="title" id="inputSongTitle" placeholder="Title" class="form-control"  required="required"/>
                <form:errors path="title" style="color: red;"/>
            </p>
            <p>
                <form:input path="artist" id="inputSongArtist" placeholder="Artist" class="form-control" required="required"/>
                <form:errors path="artist" style="color: red;"/>
            </p>   
            <p>
            	<form:select path="genre" id="inputSongGenre" class="form-control" required="required">
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
                	<form:option value="classical">Classical</form:option>
                	<form:option value="podcast">Podcast</form:option>
                </form:select>
                <form:errors path="genre" style="color: red;"/>
            </p>
            <p>
            	<form:select path="rating" id="inputSongRating" class="form-control" required="required">
            		<form:option value="5">★★★★★</form:option>
            		<form:option value="4">★★★★☆</form:option>
            		<form:option value="3">★★★☆☆</form:option>
            		<form:option value="2">★★☆☆☆</form:option>
            		<form:option value="1">★☆☆☆☆</form:option>
            	</form:select>
                <form:errors path="rating" style="color: red;"/>
            </p>
            <p>
                <form:input path="youtubelink" id="inputYouTubeLink" placeholder="YouTube Video ID" class="form-control" required="required"/>
                <small id="inputYouTubeLinkDesc">E.g: youtube.com/watch?v=<strong><span class="text-danger">YOUTUBE_VIDEO_ID</span></strong></small>
            	<form:errors path="youtubelink" style="color: red;"/>
            </p>  
            <form:input type="hidden" path="user" value="${user.id}" required="required"/>
            <form:errors path="user" style="color: red;"/>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
        </form:form>
    </div>
    
    <!-- JavaScript -->
	<script type="text/javascript" src="js/app.js"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
	<script>
        $("select").each(function(){ // Auto fills the select dropdown
            $(this).find('option[value="'+$(this).attr("value")+'"]').prop('selected', true); 
        });
    </script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  
</body>
</html>