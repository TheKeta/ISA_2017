$(document).ready(function() {
	$.ajax({
		url: "../genre/getGenres",
		success: function(genres){
			$("#genre").append(genres.map(generateDropdowns));
		}
	});
	
	$.ajax({
		url: "../showType/getShowTypes",
		success: function(types){
			$("#type").append(types.map(generateDropdowns));
		}
	});
});

function generateDropdowns(data){
	str = '<option value="'+ data.id +'">'+ data.name +'</option>';
	return str;
}

function Create(){
	var show = new Object();
	show.name = $("#name").val();
	show.cast = $("#cast").val();
	show.description = $("#description").val();
	show.length = $("#length").val();
	
	var type = new Object();
	type.id = $("#type").val();
	show.type = type;
	
	var genre = new Object();
	genre.id = $("#genre").val();
	show.genre = genre;
	
	$.ajax({
		url: "../show",
		type: "POST",
		data: JSON.stringify(show),
		contentType: "application/json",
		dataType: "json",
		success: function(show){
			window.location.href = "../Shows.html";
		}
	});
}

function Cancel(){
	window.history.back();
}