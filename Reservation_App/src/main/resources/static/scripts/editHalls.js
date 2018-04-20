var url = window.location.href;
var str = url.split("=");

$(document).ready(function() {
	$.ajax({
		url: "../hall/" + str[1],
		success: function(seats){

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
			
			$("#balcony-left").append(createSeats(balconyLeft, "balconyLeft"));
			$("#balcony-right").append(createSeats(balconyRight, "balconyRight"));
			$("#couples").append(createSeats(couples, "couples"));
			$("#regular").append(createSeats(regular, "regular"));
		}
	});
});


function createSeats(seats, type){
	
	
	if(seats.length === 0){
		$("#"+type+"Row").val(0);
		$("#"+type+"Col").val(0);
		return "";
	}
	var rows = seats[seats.length-1].row;
	var seatNum = seats[seats.length-1].seatNumber;

	$("#"+type+"Row").val(rows);
	$("#"+type+"Col").val(seatNum);
	
	
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

function Save(){

	sendSaveRequest("BALCONY-LEFT", "balconyLeft");
	sendSaveRequest("BALCONY-RIGHT", "balconyRight");
	sendSaveRequest("REGULAR", "regular");
	sendSaveRequest("COUPLES", "couples");
	window.history.back();
}

function sendSaveRequest(typeName, inputName){
	var createSeats = new Object();
	
	var hallId = str[1];
	var rows = $("#"+ inputName +"Row").val();
	var cols = $("#"+ inputName +"Col").val();
	
	createSeats.hallId = hallId;
	createSeats.rows = rows;
	createSeats.cols = cols;
	createSeats.typeName = typeName;

	
	$.ajax({
		url: "../seat",
		type: "POST",
		data: JSON.stringify(createSeats),
		contentType: "application/json",
		dataType: "json",
	});	
}

function Cancel(){
	window.history.back();
}

function seatBox(row, column){
	var str = '<td width=60 height=60><input style="width: 20px; height:20px;" type="checkbox" value="'+ row + "x" + column +'" /></td>';
	return str;
}