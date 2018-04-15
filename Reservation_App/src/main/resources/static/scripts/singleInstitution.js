var url = window.location.href;
var str = url.split("=");

$(document).ready(function() {
	$.ajax({
		url: "../event/getEvents/" + str[1],
		success: function(data){
			$('#institutionDetails').html("");
			data.map(createEventElements);
		}
	});
});



function createEventElements(event){
	var str = "";
	str += '<div style=\"padding: 5px;\">';
	str += '<button type=\"button\" class=\"btn\" style=\"width: 100%; height: 30px; background: #d9d9d9;\" data-toggle=\"collapse\" data-target=\"#'+ event.id + '\"\">'+ event.show.name +'</button>';
	str += '<div id=\"'+ event.id + '\" class=\"collapse in\">';
	str += '<div class="row">';
	str += '<div class="col-sm-8">';
	str += '<ul>';
	str += '<li>Type: '+ event.show.type.name +'</li>';
	str += '<li>Genre: '+ event.show.genre.name +'</li>';
	str += '<li>Length: '+ event.show.length +'</li>';
	var date = new Date(event.eventDate);
	var day = date.getDate();
	var month = date.getMonth() + 1;
	var year = date.getFullYear();
	var hour = date.getHours();
	var minutes = date.getMinutes();
	
	str += '<li>Date: ' + day + "/" + month + "/"+ year  +'</li>';
	str += '<li>Time: ' + hour + ":" + minutes  +'</li>';
	//str += '<li>Cast: '+ eventRated.event.show.cast +'</li>';
	//str += '<li>Description: '+ eventRated.event.show.description +'</li>';
//	if(isNaN(institutionRated.rating)){
//		str += '<li>Rating: Have not been rated yet.</li>';
//	}else{
//		str += '<li>Rating: '+ institutionRated.rating +'</li>';
//	}
	str += '</ul>';
	str += '</div>';
	str += '<div class="col-sm-4">';
	str += '<button onClick="Edit('+ event.id +')">Edit</button>';
	str += '<button onClick="Remove('+ event.id +')">Remove</button>';
	str += '<button onClick="Reservation('+ event.id +')">Reservation</button>';
	
	str += '</div>';
	str += "</div>";
	str += "</div>";
	str += '</div>';
	$('#showEvents').append(str);
}


function Remove(id){
	$.ajax({
		url: "../institution/delete/" + id,
		type: "DELETE",
		success: function(data){
			window.location.href = "../Institution.html?type="+ str[1];
		},
		error: function(data){
			alert('This show can\'t be deleted.');
		}
	});
}







