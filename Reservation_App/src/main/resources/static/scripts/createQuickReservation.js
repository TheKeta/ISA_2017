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
		url: "../event/events/" + str[1],
		success: function(events){
			$("#chooseEvent").append(events.map(createElements));
		}
	});
}

function createElements(event){
	var date = new Date(event.eventDate);
	var day = date.getDate();
	var month = date.getMonth() + 1;
	var year = date.getFullYear();
	var hours = date.getHours();
	var minutes = date.getMinutes();
	
	str = '';
	str += '<option id="'+ event.hall.id +'" value="' + event.id + '">';
	str += event.show.name + " - Date: "+ day + "/" + month + "/"+ year  + ", Time: " + hours + ":" + minutes + ", Hall: " + event.hall.name ;
	str += '</option>';
	return str;
}


function Create(){
	var checkboxes = $(":checkbox:checked");
	var reservations = [];
	
	for(var i=0; i<checkboxes.length; i++){
		var seat = new Object();	
		seat.row = checkboxes[i].id.split("_")[1].split("x")[0]; 
		seat.seatNumber = checkboxes[i].id.split("x")[1];
		var type = new Object();
		type.name = checkboxes[i].id.split("_")[0];
		if(type.name === 'balconyLeft'){
			type.name = 'BALCONY-LEFT';
		}else if(type.name ==="balconyRight"){
			type.name = 'BALCONY-RIGHT';
		}else if(type.name === 'couples'){
			type.name = 'COUPLES';
		}else if(type.name === 'regular'){
			type.name = 'REGULAR';
		}
		var hall = new Object();
		hall.id = $("#chooseEvent").find(":selected").attr("id");
		seat.seatType = type;
		seat.hall = hall;
		
		var reservation = new Object();
		reservation.seats = seat;
		
		var event = new Object();
		event.id = $("#chooseEvent").val();
		reservation.event = event;
		
		var user = new Object();
		reservation.user = user;
		
		reservation.price = $("price").val();
		
		reservations.push(reservation);
	}
	
	$.ajax({
		url: "../reservation",
		data: JSON.stringify(reservations),
		type: "POST",
		contentType: "application/json",
		dataType: "json",
		success: function(data){
			window.location.href = "../QuickReservations.html?id="+str[1];
		}
	});
}


function loadSeats(){
	var hall = "";
	hall = $("#chooseEvent").find(":selected").attr("id");
	if(hall === 'selectEvent'){
		$("#balcony-left").html("");
		$("#balcony-right").html("");
		$("#couples").html("");
		$("#regular").html("");
	}
	var eventId = $("#chooseEvent").val();
	$.ajax({
		url: "../hall/getByEvent/" + eventId,
		success: function(reservedSeats){
			seats = reservedSeats.seats;
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
			
			$("#balcony-left").html("");
			$("#balcony-right").html("");
			$("#couples").html("");
			$("#regular").html("");
			
			$("#balcony-left").append(createSeats(balconyLeft, "balconyLeft"));
			$("#balcony-right").append(createSeats(balconyRight, "balconyRight"));
			$("#couples").append(createSeats(couples, "couples"));
			$("#regular").append(createSeats(regular, "regular"));
			
			reservedSeats.reservations.map(disableSeats);
		}
	});
}


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
				str += '<td width=60 height=60><input style="width: 20px; height:20px;" type="checkbox" id="'+ type +"_"+ i + "x" + j +'" /></td>'
			}
		str += '</tr>';
	}
	str += '</table>';
	return str;
}

function disableSeats(reservation){
	var type = ""
	if(reservation.seats.seatType.name === "REGULAR"){
		type = "regular";
	}else if(reservation.seats.seatType.name === "COUPLES"){
		type = "couples";
	}else if(reservation.seats.seatType.name === "BALCONY-RIGHT"){
		type = "balconyRight";
	}else if(reservation.seats.seatType.name === "BALCONY-LEFT"){
		type = "balconyLeft"
	}
	$("#"+type+"_"+reservation.seats.row + "x" + reservation.seats.seatNumber).attr("disabled", true);
}