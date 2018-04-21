var url = window.location.href;
var str = url.split("=");

$(document).ready(function() {	
	$.ajax({
		url: "../event/eventShowsHalls/" + str[1],
		success: function(eventShow){
			$("#show").append(generateDropDown(eventShow.shows, eventShow.event.show.id))
			$("#hall").append(generateDropDown(eventShow.halls, eventShow.event.hall.id))
			$("#date").val(transformDate(new Date(eventShow.event.eventDate)));
			$("#time").val(transformTime(new Date(eventShow.event.eventDate)));
			$("#price").val(eventShow.event.price);
			$("#id").val(eventShow.event.id);
		} 
	});
});

function Save(){
	var event = new Object();
	event.id = $("#id").val();
	
	var hall = new Object();
	hall.id = $("#hall").val();
	event.hall = hall;
	
	event.price = $("#price").val();
	
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
			window.location.href="../Institution.html?type=" + event.hall.institution.id; 
		}
	});
}

function Cancel(){
	window.history.back();
}

function generateDropDown(data, id){
	var str = "";
	for(var i = 0; i < data.length; i++){
		if(data[i].id == id){
			str += '<option selected value="'+ data[i].id +'">'+ data[i].name +'</option>'
		}else{
			str += '<option value="'+ data[i].id +'">'+ data[i].name +'</option>'
		}
	}
	return str;
}

function transformTime(date){
	var minutes = date.getMinutes();
	var hours = date.getHours();
	if(minutes < 10){
		minutes = "0"+minutes;
	}
	
	if(hours < 10){
		hours = "0" + hours;
	}
	
	return hours + ":" + minutes;
}


function transformDate(date){
	var day = date.getDate();
	if(day < 10){
		day = "0" + day;
	}
	var month = date.getMonth()+1;
	if(month < 10){
		month = "0" + month;
	}
	var year = date.getFullYear();
	return year + "-" + month + "-" + day;
}