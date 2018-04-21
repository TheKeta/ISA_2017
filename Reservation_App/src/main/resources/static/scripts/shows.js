$(document).ready(function() {
	defaultElements();
});
function defaultElements(){
	$.ajax({
		url: "../show/getShows" ,
		success: function(shows){
			$("#shows").append(shows.map(createShowElement));
		}
	});
}


function createShowElement(show){
	
	var str = "";
	str += '<div id="show_'+ show.id + '" style=\"padding: 5px;\">';
	str += '<button type="button" class="btn" style="width: 100%; height: 30px; background: #d9d9d9;" data-toggle="collapse" data-target="#'+ show.id + '">'+ show.name +'</button>';
	str += '<div id=\"'+ show.id + '\" class=\"collapse in\">';
	str += '<div class="row">';
	str += '<div class="col-sm-9">';
	str += '<ul>';
	str += '<li>Type: '+ show.type.name +'</li>';
	str += '<li>Genre: '+ show.genre.name +'</li>';
	str += '<li>Length: '+ show.length +'</li>';
	str += '<li>Cast: '+ show.cast +'</li>';
//	if(isNaN(institutionRated.rating)){
//		str += '<li>Rating: Have not been rated yet.</li>';
//	}else{
//		str += '<li>Rating: '+ institutionRated.rating +'</li>';
//	}
	str += '<li>Description: '+ show.description +'</li>';
	str += '</ul>';
	str += '</div>';

	str += '<div class="col-sm-3" style="text-align: right;">';
	str += '<button style="margin-right: 20px;" onClick="Edit('+ show.id +')">Edit</button>';
	str += '<button onClick="Delete('+ show.id +')">Delete</button>';
	str += '</div>';
	
	str += '</div>';
	str += "</div>";
	
	return str;
}


function Edit(id){
	window.location.href = "../EditShow.html?id="+ id
}

function Delete(id){
	$.ajax({
		url: "../show/" + id,
		type: "DELETE",
		success: function(data){
			$("#show_"+ id).remove();
		},
		error: function(data){
			alert('This show can\'t be deleted.');
		}
	});
}

function Create(){
	window.location.href= "../CreateShow.html";
}