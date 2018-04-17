var url = window.location.href;
var str = url.split("=");

$(document).ready(function() {
	$.ajax({
		url: "../seat/getSeats/" + str[1],
		success: function(hallSeats){
			$("#halls").append(hallSeats.halls.map(createHallElements));
		}
	});
});


function createHallElements(seats){
	var str = "";
	str += '<div style=\"padding: 5px;\">';
	str += '<button type=\"button\" class=\"btn\" style=\"width: 100%; height: 30px; background: #d9d9d9;\" data-toggle=\"collapse\" data-target=\"#'+ seats[0].hall.id + '\"\">'+ seats[0].hall.name +'</button>';
	str += '<div id=\"'+ seats[0].hall.id + '\" class=\"collapse in\">';
	str += '<div class="row">';
	str += '<div class="col-sm-6">';
	str += '<ul>';
	str += '<li>Rows:'+ seats[seats.length-1].row +'</li>';
	str += '<li>Seats:'+ seats[seats.length-1].seatNumber +'</li>';
	
	str += createSeats(seats[seats.length-1].row, seats[seats.length-1].seatNumber);
	str += '</ul>';
	str += '</div>';
	
	str += '<div class="col-sm-4">';	
	str += '</div>'; 
	
	str += "</div>";
	str += "</div>";

	str += '</div>';
	return str;
}

function createSeats(rows, seatNum){
	var str = "";
	
	return str;
}