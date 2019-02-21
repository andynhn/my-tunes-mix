<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="/css/style.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <title>${song.title}</title>
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
                <li class="nav-item">
                    <a class="nav-link" href="/songs/new">Add New Song</a>
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
    
	<div class="container mt-3">
		<a href="/dashboard"><button class="btn btn-sm btn-primary">Back to Dashboard</button></a>	
	</div>
    
    <div class="container showsong-page mt-3">
    	<div class="row">
    		<div class="col-xs-12 col-md-12 col-lg-4">
    		   	<h5><strong><c:out value="${song.title}"/></strong></h5>
    			<h5 class="mb-4"><em>by <c:out value="${song.artist}"/></em></h5>
		    	<h6>genre: <c:out value="${song.genre}"/></h6>
		    	<h6 class="mb-4">rating: 
		    		<c:choose>
						<c:when test="${song.rating == 5}">★★★★★</c:when>
						<c:when test="${song.rating == 4}">★★★★☆</c:when>
						<c:when test="${song.rating == 3}">★★★☆☆</c:when>
						<c:when test="${song.rating == 2}">★★☆☆☆</c:when>
						<c:when test="${song.rating == 1}">★☆☆☆☆</c:when>
						<c:otherwise> N/A</c:otherwise>
					</c:choose>
		    	</h6>
		        <form action="/songs/${song.id}" method="post">
		    		<input type="hidden" name="_method" value="delete">
		    		<button class="btn btn-sm btn-danger" type="submit">Delete Song</button>
		    	</form>
    		</div>
    		<div class="col-xs-0 col-md-0 col-lg-8">
    			<iframe width="640" height="360" 
		        	src="https://www.youtube.com/embed/${song.youtubelink}?autoplay=1" 
		        	frameborder="0">
		       	</iframe>
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