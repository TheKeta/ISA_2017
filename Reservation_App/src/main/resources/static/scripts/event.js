var url = window.location.href;
var str = url.split("=");

$(document).ready(function() {	
	$.ajax({
		url: "../event/getEvent/" + str[1],
		success: function(event){
			var date = new Date(event.eventDate);
			var day = date.getDate();
			var month = date.getMonth() + 1;
			var year = date.getFullYear();
			var hours = date.getHours();
			var minutes = date.getMinutes();
			
			$("#name").append(event.show.name);
			$("#hall").append(event.hall.name);
			$("#cast").append(event.show.cast);
			$("#type").append(event.show.type.name);
			$("#genre").append(event.show.genre.name);
			$("#length").append(event.show.length);
			$("#description").append(event.show.description);
			$("#date").append(day + "/" + month + "/"+ year);
			$("#time").append(hours + ":" + minutes);	
		} 
	});
});


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