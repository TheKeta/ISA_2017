$(document).ready(function() {
	$.ajax({
		url: "../show/getShows",
		success: function(shows){
			for(var i=0; i<shows.length; i++){
				$("#show").append(generateDropDown(shows[i]));
			}
		}
	});
	
	$.ajax({
		url: "../hall/getHalls/" + window.location.href.split("=")[1],
		success: function(halls){
			for(var i=0; i<halls.length; i++){
				$("#hall").append(generateDropDown(halls[i]));
			}
		}
	});
});



function generateDropDown(data){
	var str = "";
	str += '<option value="'+ data.id +'">'+ data.name +'</option>';
	return str;
}


function Cancel(){
	window.history.back();
}

function Create(){
	var event = new Object();
	
	var hall = new Object();
	hall.id = $("#hall").val();
	event.hall = hall;

	var show = new Object();
	show.id = $("#show").val();
	event.show = show;
	
	event.eventDate = new Date($("#date").val());
	var time = $("#time").val();
	var hoursAndMins = time.split(":");
	event.eventDate.setHours(hoursAndMins[0]);
	event.eventDate.setMinutes(hoursAndMins[1]);
	
	$.ajax({
		url: "../event",
		data: JSON.stringify(event),
		type: "POST",
		contentType: "application/json",
		dataType: "json",
		success: function(event){
			window.location.href="../Institution.html?type=" + window.location.href.split("=")[1]; 
		}
	});
}