var str = window.location.href.split("=");

$(document).ready(function(){
	$.ajax({
		url: "../show/" + str[1],
		success: function(showGenresTypes){
			for(var i = 0; i< showGenresTypes.genres.length; i++){
				$("#genre").append(generateDropdowns(showGenresTypes.genres[i], showGenresTypes.show.genre.id));
			}
			
			for(var i = 0; i< showGenresTypes.types.length; i++){
				$("#type").append(generateDropdowns(showGenresTypes.types[i], showGenresTypes.show.type.id));
			}
		
			$("#name").val(showGenresTypes.show.name);
			$("#description").val(showGenresTypes.show.description);
			$("#length").val(showGenresTypes.show.length);
			$("#cast").val(showGenresTypes.show.cast);
			
		}
	});
});

function generateDropdowns(data, id){
	
	if(data.id === id){
		str = '<option selected value="'+ data.id +'">'+ data.name +'</option>';
	}else{
		str = '<option value="'+ data.id +'">'+ data.name +'</option>';
	}
	
	return str;
}

function Save(){
	var show = new Object();
	show.id = window.location.href.split("=")[1];
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