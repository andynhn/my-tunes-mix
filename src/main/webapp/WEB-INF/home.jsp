<!-- By Andy N.H. Nguyen - https://andynhn.me/ - https://github.com/andynhn/ -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="/css/style.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
	<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <title>Library</title>
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
                <li class="nav-item active">
                    <a class="nav-link" href="/home"><strong>Library</strong> <span class="sr-only">(current)</span></a>
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
				<li class="nav-item dropdown">
					<a class="dropdown-toggle nav-link" data-toggle="dropdown" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Settings<span class="caret"></span></a>
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
    
	<div class="container text-center mt-3">
	    <p style="color: red;"><c:out value="${error}" /></p>
	    <p style="color: green;"><c:out value="${success}" /></p>
	</div>
    
    <div class="container mt-3">
    	<div class="container">
	    	<c:if test="${emptylibrary != true}">
		    	<div class="row d-flex justify-content-between">
					<form action="/home" method="get" class="form-inline my-2 my-lg-0">
					       <% if(request.getParameter("search") != null && request.getParameter("searchgenre") == null) { %>
					           <input class="form-control form-control-sm mr-sm-2" type="search" placeholder="Search Artists" name="search" value='<%= request.getParameter("search") %>' aria-label="Search" size="15">
					       <% } else { %>
					           <input class="form-control form-control-sm mr-sm-2" type="search" placeholder="Search Artists" name="search" aria-label="Search" size="15">
					       <% } %>
					    <button class="btn btn-sm btn-success my-2 my-sm-0" type="submit">Search</button>
					</form>
			       <form action="/home" method="get" class="form-inline my-2 my-lg-0">
			       		<select name="searchgenre" id="inputSongGenre" class="form-control form-control-sm mr-sm-2">
				           	<option value=""> -- GENRE -- </option>
		                	<option value="acoustic">Acoustic</option>
		                	<option value="alternative">Alternative</option>
		                	<option value="classical">Classical</option>
		                	<option value="comedy">Comedy</option>
		                	<option value="country">Country</option>
		                	<option value="dance electronic">Dance/Electronic</option>
		                	<option value="easy listening">Easy Listening</option>
		                	<option value="folk">Folk</option>
		                	<option value="hip hop">Hip-Hop</option>
		                	<option value="holiday">Holiday</option>
		                	<option value="instrumental">Instrumental</option>
		                	<option value="jazz">Jazz</option>
		                	<option value="orchestral">Orchestral</option>
		                	<option value="podcast">Podcast</option>
		                	<option value="pop">Pop</option>
		                	<option value="rhythm and blues">Rhythm and Blues</option>
		                	<option value="rock">Rock</option>
		                	<option value="soundtrack">Soundtrack</option>
		                	<option value="world">World</option>
						</select>
			           <button class="btn btn-sm btn-success my-2 my-sm-0" type="submit">Search</button>
			       </form>	
		    	</div>
		    </c:if>
    	</div>
       
        <table class="table table-sm mt-2">
            <thead>
                <tr>
                	<th scope="col">Play</th>
                    <th scope="col">Title</th>
                    <th scope="col">Artist</th>
                    <th scope="col">Genre</th>
                    <th scope="col">Rating</th>
                    <th scope="col">Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${songs}" var="song">
                    <tr>
                    	<td>
							<button class="btn modal-button" data-toggle="modal" data-target="#viewVideoModalCenter" data-id="${song.youtubelink}">
								<i class="fa fa-play triangle-play-button"></i>
							</button>
                    	</td>
                        <td class="capitalize"><a href="/songs/${song.id}">${song.title}</a></td>
                        <td class="capitalize">${song.artist}</td>
                        <td class="capitalize">${song.genre}</td>
                        <td>
                        	<c:choose>
								<c:when test="${song.rating == 5}">★★★★★</c:when>
								<c:when test="${song.rating == 4}">★★★★☆</c:when>
								<c:when test="${song.rating == 3}">★★★☆☆</c:when>
								<c:when test="${song.rating == 2}">★★☆☆☆</c:when>
								<c:when test="${song.rating == 1}">★☆☆☆☆</c:when>
								<c:otherwise>N/A</c:otherwise>
							</c:choose>
                        </td>
                        <td>
                            <a href="/songs/${song.id}/edit" class="btn btn-sm btn-info mb-1 mr-1">Edit</a>
							<span><button class="btn modal-button-delete btn-sm btn-danger mb-1" data-toggle="modal" data-target="#confirmSongDeletionModalCenter" data-id="${song.id}" data-title="${song.title}" data-artist="${song.artist}">
								Delete
							</button></span>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <p class="text-center"><c:out value="${noresults}"></c:out></p>
        <c:if test="${emptylibrary == true}">
        	<p class="text-center">Start by <a href="/songs/new">adding tunes</a> to your library or <a href="/discover">discovering tunes</a> added by others</p>
        </c:if>
    </div>
    
    <!-- Modal -->
	<div class="modal fade" id="viewVideoModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-body">
					<div class="youtube-iframe">
					
						<iframe width="100%" height="261" 
			        	src="" 
			        	frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>
			       		</iframe>
			       		<p><small>Some YouTube videos do not allow embedding. If the video fails to load, try a different browser (I recommend Firefox or Chrome), or <a href="https://www.youtube.com/watch?v=${song.youtubelink}" target="_blank">watch it on YouTube</a>.</small></p>
			       		<p><small id="disclaimer">I do not own the content from the embedded YouTube video. All rights belong to their respective owners.</small></p>
			       	
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-sm btn-info" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	
    <!-- Modal -->
	<div class="modal fade" id="confirmSongDeletionModalCenter" tabindex="-1" role="dialog" aria-labelledby="confirmSongDeletionModalCenter" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="#confirmSongDeletionModalCenterLongTitle"><strong><span class="capitalize selected-song-to-delete">${song.title}</span></strong> <br> <em>by <span class="capitalize selected-song-to-delete-artist">${song.artist}</span></em></h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<p>Are you sure you want to delete this from your library?</p>
			</div>
			<div class="modal-footer delete-song-footer">
				<form action="/songs/${song.id}" method="post">
                    <input type="hidden" name="_method" value="delete">
                    <button class="btn btn-sm btn-danger" type="submit">Delete</button>
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
	<script>
	
		$(document).on("click", ".modal-button", function() {
			var video = "https://www.youtube.com/embed/" + $(this).data('id') + "?autoplay=1&enablejsapi=1";
			$("#viewVideoModalCenter iframe").attr("src", video);
			var externalVideoLink = "https://www.youtube.com/watch?v=" + $(this).data('id');
			$("#viewVideoModalCenter .youtube-iframe a").attr("href", externalVideoLink);
		})
		
		$("#viewVideoModalCenter").on('hidden.bs.modal', function(e) {
		    $("#viewVideoModalCenter iframe").attr("src", "");
		    $("#viewVideoModalCenter .youtube-iframe a").attr("href", "");
		});
		
		$(document).on("click", ".modal-button-delete", function() {
			var songid = "/songs/" + $(this).data('id');
			$(".selected-song-to-delete").html($(this).data('title'));
			$(".selected-song-to-delete-artist").html($(this).data('artist'));
			$(".delete-song-footer form").attr("action", songid);
		})

	</script>
	
</body>
</html>