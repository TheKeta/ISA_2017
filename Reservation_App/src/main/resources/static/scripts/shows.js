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


function createShowElement(showRated){
	
	var str = "";
	str += '<div id="show_'+ showRated.show.id + '" style=\"padding: 5px;\">';
		str += '<button type="button" class="btn" style="width: 100%; height: 30px; background: #d9d9d9;" data-toggle="collapse" data-target="#'+ showRated.show.id + '">'+ showRated.show.name +'</button>';
		str += '<div id=\"'+ showRated.show.id + '\" class=\"collapse in\">';

		str += '<div class="row">';
		
			str += '<div class="col-sm-2">';
			str += '<img src=\"data:image/png;base64,' +showRated.show.pictureDB +'\" alt=\"'+showRated.show.name+'\" style="width:100px;height:100px;" />';
			str += '</div>';
		
			str += '<div class="col-sm-7">';
				str += '<ul>';
				str += '<li>Type: '+ showRated.show.type.name +'</li>';
				str += '<li>Genre: '+ showRated.show.genre.name +'</li>';
				str += '<li>Length: '+ showRated.show.length +'</li>';
				str += '<li>Cast: '+ showRated.show.cast +'</li>';
			if(isNaN(showRated.rating)){
				str += '<li>Rating: Have not been rated yet.</li>';
			}else{
				str += '<li>Rating: '+ showRated.rating +'</li>';
			}
				str += '<li>Description: '+ showRated.show.description +'</li>';
				str += '</ul>';
			str += '</div>';
	
			str += '<div class="col-sm-3" style="text-align: right;">';
			str += '<button style="margin-right: 20px;" onClick="Edit('+ showRated.show.id +')">Edit</button>';
			str += '<button onClick="Delete('+ showRated.show.id +')">Delete</button>';
			str += '</div>';
		
		str += '</div>';
		str += "</div>";
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