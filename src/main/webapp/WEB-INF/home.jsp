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
    <title>${user.fname}'s Library</title>
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
                    <a class="nav-link" href="/home"><strong>Library</strong> <span class="sr-only">(current)</span></a>
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
                <li class="nav-item">
                    <a class="nav-link" href="/logout">Log Out</a>
                </li>
            </ul>
        </div>
    </nav>
    
	<div class="container text-center mt-3">
		<!-- Errors displayed only if they are added to the model attribute in the controller -->
	    <p style="color: red;"><c:out value="${error}" /></p>
	</div>
    
    <div class="container mt-3">
    	<div class="container">
	    	<div class="row d-flex justify-content-between">
				<form action="/home" method="get" class="form-inline my-2 my-lg-0">
				       <% if(request.getParameter("search") != null && request.getParameter("searchgenre") == null) { %>
				           <input class="form-control form-control-sm mr-sm-2" type="search" placeholder="Search Artists" name="search" value='<%= request.getParameter("search") %>' aria-label="Search">
				       <% } else { %>
				           <input class="form-control form-control-sm mr-sm-2" type="search" placeholder="Search Artists" name="search" aria-label="Search">
				       <% } %>
				    <button class="btn btn-sm btn-success my-2 my-sm-0" type="submit">Search by Artist</button>
				</form>
		       <form action="/home" method="get" class="form-inline my-2 my-lg-0">
		       		<select name="searchgenre" id="inputSongGenre" class="form-control form-control-sm mr-sm-2">
			           	<option value=""> -- GENRE -- </option>
			           	<option value="pop">Pop</option>
			           	<option value="hip hop">Hip-Hop</option>
			           	<option value="dance electronic">Dance/Electronic</option>
			           	<option value="country">Country</option>
			           	<option value="rock">Rock</option>
			           	<option value="alternative">Alternative</option>
			           	<option value="latin">Latin</option>
			           	<option value="international">International</option>
			           	<option value="rhythm and blues">Rhythm and Blues</option>
					</select>
		           <button class="btn btn-sm btn-success my-2 my-sm-0" type="submit">Search by Genre</button>
		       </form>	
	    	</div>
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
		                   	<!-- Button trigger modal -->
							<button class="btn modal-button" data-toggle="modal" data-target="#viewVideoModalCenter" data-id="${song.youtubelink}">
								<i class="fa fa-play" style="font-size:25px;color:teal"></i>
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
                            <form action="/songs/${song.id}" method="post">
                            	<a href="/songs/${song.id}/edit" class="btn btn-sm btn-info">Edit</a>
                                <input type="hidden" name="_method" value="delete">
                                <button class="btn btn-sm btn-danger" type="submit">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    
    <!-- Modal -->
	<div class="modal fade" id="viewVideoModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-body">
					<div class="youtube-iframe">
					
						<iframe width="464" height="261" 
			        	src="" 
			        	frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>
			       		</iframe>
			       		<p><small>Some YouTube videos do not allow embedding. If the video fails to load, try the app in a different browser (I recommend Firefox), or <a href="https://www.youtube.com/watch?v=${song.youtubelink}" target="_blank">watch it on YouTube</a>.</small></p>
			       		<p><small id="disclaimer">This application is for educational purposes only and is meant to demonstrate my ability to build Java/Spring applications. I do not own the content from the embedded YouTube video. All rights belong to their respective owners.</small></p>
			       	
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-sm btn-info" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
   	
   <!-- JavaScript -->
	<script type="text/javascript" src="js/app.js"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
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


	</script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  
</body>
</html>