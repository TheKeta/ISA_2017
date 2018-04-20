var url = window.location.href;
var str = url.split("=");

$(document).ready(function() {
	$.ajax({
		url: "../seat/getSeats/" + str[1],
		success: function(hallSeats){			
			for(var i = 0; i < hallSeats.halls.length; i++){
				$("#halls").append(createHallElements(hallSeats.halls[i], hallSeats.seats[i]));
				showSeats(hallSeats.seats[i], hallSeats.halls[i].id)
			}
		}
	});
});


function showSeats(seats, hallId){
	var balconyLeft = [];
	var balconyRight = [];
	var regular = [];
	var couples = [];
	
	for(var i=0; i<seats.length; i++){
		var seat = seats[i];
		if(seat.seatType.name === 'BALCONY-LEFT'){
			balconyLeft.push(seat);
		}
		else if(seat.seatType.name === 'BALCONY-RIGHT'){
			balconyRight.push(seat);
		}
		else if(seat.seatType.name === 'COUPLES'){
			couples.push(seat);
		}
		else if(seat.seatType.name === 'REGULAR'){
			regular.push(seat);
		}
	}
	
	$("#balconyLeft_"+hallId).append(createSeats(balconyLeft));
	$("#balconyRight_"+hallId).append(createSeats(balconyRight));
	$("#couples_"+hallId).append(createSeats(couples));
	$("#regular_"+hallId).append(createSeats(regular));
}

function createSeats(seats){
	if(seats.length === 0){
		return "";
	}
	var rows = seats[seats.length-1].row;
	var seatNum = seats[seats.length-1].seatNumber;
		
	var str = "";
	str += '<table>';
	str += '<tr>';
	str += '<th width=60></th>';
	for(var i = 1; i <= seatNum; i++){
		str += '<td width=60><p style="font-size: 20px;">' + i + '</p></td>';
	}
	str += '</tr>';
	for(var i = 1; i <= rows; i++){
		str += '<tr><td width=60 height=60><p style="font-size: 20px;">' + i + '</p></td>';
			for(var j=1; j <= seatNum; j++){
				str += '<td width=60 height=60><input style="width: 20px; height:20px;" type="checkbox" value="'+ i + "x" + j +'" /></td>'
			}
		str += '</tr>';
	}
	str += '</table>';
	return str;
}

function createHallElements(hall, seats){
	var str = "";
	str += '<div style=\"padding: 5px;\" id="div_'+ hall.id +'">';
	str += '<button type=\"button\" class=\"btn\" style=\"width: 100%; height: 30px; background: #d9d9d9;\" data-toggle=\"collapse\"  data-target=\"#'+ hall.id + '\"\">'+ hall.name +'</button>';
	str += '<div id=\"'+ hall.id + '\" class=\"collapse\">';
	str += '<div class="row">';
	str += '<div class="col-sm-3">';
	str += "</div>";
	str += '<div class="col-sm-6">';
//	if(seats.length > 0){
//		str += createSeats(seats[seats.length-1].row, seats[seats.length-1].seatNumber);	
//	}
	str += '</div>';
	str += '<div class="col-sm-3">';
	str += '<button style="margin-right: 20px;" onClick="Edit('+ hall.id +')" >Edit</button>';
	str += '<button onClick="Delete('+ hall.id +')">Delete</button>';
	str += "</div>";
	str += "</div>"; 
	
	str += '<div class="row">';
	str += '<div id="balconyLeft_'+ hall.id +'" class="col-sm-4">';
	str += '</div>';
	str += '<div id="regular_'+ hall.id +'" class="col-sm-4">';
	str += '</div>';
	str += '<div id="balconyRight_'+ hall.id +'" class="col-sm-4">';
	str += '</div>';
	str += '</div>';
	
	str += '<div class="row">';
	str += '<div class="col-sm-4">';
	str += '</div>';
	str += '<div id="couples_'+ hall.id +'" class="col-sm-4">';
	str += '</div>';
	str += '<div class="col-sm-4">';
	str += '</div>';
	str += '</div>';
	
	
	str += "</div>"; 
	str += '</div>';
	
	return str;
}

function Create(){
	var hall = new Object();
	hall.name = $("#name").val();
	
	var institution = new Object();
	institution.id = str[1];
	
	hall.institution = institution;
	
	$.ajax({
		url: "../hall",
		type: "POST",
		data: JSON.stringify(hall),
		contentType: "application/json",
		dataType: "json",
		success: function(hall){
			window.location.href="../Halls.html?id=" + hall.institution.id; 
		}
	});
}

function Edit(id){
	window.location.href = "../EditHall.html?id=" + id;
}

function Delete(id){
	$.ajax({
		url: "../hall/" + id,
		type: "DELETE",
		success: function(hall){
			$("#div_"+hall.id).remove();
		}
	});
}
