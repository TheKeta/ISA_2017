$(document).ready(function() {
	defaultElements();
});

var url = window.location.href;
var str = url.split("=");

function defaultElements(){
	if(str.length < 2){
		window.location.href = "../Home.html";
		return;
	}
	$.ajax({
		url: "../reservation/getReservations/" + str[1],
		success: function(reservations){
			$("#showReservations").append(reservations.map(createElements));
		}
	});
}


function createElements(reservation){
	var str = "";
	str += '<div style=\"padding: 5px;\">';
	str += '<button type=\"button\" class=\"btn\" style=\"width: 100%; height: 30px; background: #d9d9d9;\" data-toggle=\"collapse\" data-target=\"#'+ reservation.id + '\"\">'+ reservation.event.show.name +'</button>';
	str += '<div id=\"'+ reservation.id + '\" class=\"collapse in\">';
	str += '<div class="row">';
	str += '<div class="col-sm-8">';
	str += '<ul>';
	str += '<li>Type: '+ reservation.event.show.type.name +'</li>';
	str += '<li>Genre: '+ reservation.event.show.genre.name +'</li>';
	str += '<li>Length: '+ reservation.event.show.length +'</li>';
	
	var date = new Date(reservation.event.eventDate);
	var day = date.getDate();
	var month = date.getMonth() + 1;
	var year = date.getFullYear();
	
	str += '<li>Date: ' + day + "/" + month + "/"+ year  +'</li>';
	str += '<li>Cast: '+ reservation.event.show.cast +'</li>';
//	if(isNaN(institutionRated.rating)){
//		str += '<li>Rating: Have not been rated yet.</li>';
//	}else{
//		str += '<li>Rating: '+ institutionRated.rating +'</li>';
//	}
	str += '<li>Description: '+ reservation.event.show.description +'</li>';
	str += '</ul>';
	str += '</div>';
	
	str += '<div class="col-sm-4">';	
	str += '<ul>';
	var hours = date.getHours();
	var minutes = date.getMinutes();
	str += '<li>Hall: ' + reservation.event.hall.name + '</li>'
	str += '<li>Row: ' + reservation.seats.row + ", Seat: " + reservation.seats.seatNumber + '</li>';
	str += '<li>Time: ' + hours + ":" + minutes + '</a></li>';
	str += '<li>Price:' + reservation.price + '</li>';
	str += '<li><button onClick="Reserve('+ reservation.id +')">Reserve</button></li>'
	str += '</ul>';
	str += '</div>';
	
	str += "</div>";
	str += "</div>";
	str += '</div>';
	return str;
}

function Reserve(id){
	$.ajax({
		url: "../reservation/reserve/" + id,
		type: "POST",
		contentType: "application/json",
		success: function(data){
			window.location.reload();
		}
	});
}

function Add(){
	window.location.href = "../CreateQuickReservation.html?id=" + str[1];
}