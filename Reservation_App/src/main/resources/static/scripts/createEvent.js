$(document).ready(function() {
	$.ajax({
		url: "../show/getShows",
		success: function(shows){
			$("show").append(shows.map(generateDropDown));
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
	
	var institution = new Object();
	institution.id = window.location.href.split("=")[1];
	event.institution = institution;
	
	
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
			window.location.href="../Institution.html?type=" + event.institution.id; 
		}
	});
}