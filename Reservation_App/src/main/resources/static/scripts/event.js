var url = window.location.href;
var str = url.split("=");

$(document).ready(function() {	
	$.ajax({
		url: "../event/getEventSeats/" + str[1],
		success: function(reservedSeats){
			var date = new Date(reservedSeats.event.eventDate);
			var day = date.getDate();
			var month = date.getMonth() + 1;
			var year = date.getFullYear();
			var hours = date.getHours();
			var minutes = date.getMinutes();
			
			document.getElementById("event").value = reservedSeats.event.id;
			document.getElementById("hall").value = reservedSeats.event.hall.id;
			document.getElementById("price").value = reservedSeats.event.price;
			
			
			$("#name").append(reservedSeats.event.show.name);
			$("#hall").append(reservedSeats.event.hall.name);
			$("#cast").append(reservedSeats.event.show.cast);
			$("#type").append(reservedSeats.event.show.type.name);
			$("#genre").append(reservedSeats.event.show.genre.name);
			$("#length").append(reservedSeats.event.show.length);
			$("#description").append(reservedSeats.event.show.description);
			$("#date").append(day + "/" + month + "/"+ year);
			$("#time").append(hours + ":" + minutes);	
			
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
});

function Reserve(){
	var checkboxes = $(":checkbox:checked");
	var reservations = [];
	
	for(var i=0; i<checkboxes.length; i++){
		var seat = new Object();	
		if(checkboxes[i].id != "sendToFriends"){
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
			hall.id = document.getElementById("hall").value;
			seat.seatType = type;
			seat.hall = hall;
			
			var reservation = new Object();
			reservation.seats = seat;
			
			var event = new Object();
			event.id = document.getElementById("event").value;
			reservation.event = event;
			
			var user = new Object();
			reservation.user = user;
			
			reservation.price = document.getElementById("price").value;
			
			reservation.quick = false;
			
			reservations.push(reservation);
		
		}	
	}
	var sendToFriends = document.getElementById("sendToFriends").checked;
	if(sendToFriends == true){
		$.ajax({
			url: "../reservation/reservationWithFriends",
			data: JSON.stringify(reservations),
			type: "POST",
			contentType: "application/json",
			dataType: "json",
			success: function(data){
				window.history.back();
			}
		});
	}else{
		$.ajax({
			url: "../reservation/regularReservation",
			data: JSON.stringify(reservations),
			type: "POST",
			contentType: "application/json",
			dataType: "json",
			success: function(data){
				window.history.back();
			}
		});
	}
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

function Delete(){
	$.ajax({
		url: "../event/delete/" + str[1],
		type: "DELETE",
		success: function(event){
			window.location.href = "../Institution.html?id="+ event.hall.institution.id;
		},
		error: function(data){
			alert('This show can\'t be deleted.');
		}
	});
}

function Edit() {
	window.location.href="../EditEvent.html?id="+str[1]
}