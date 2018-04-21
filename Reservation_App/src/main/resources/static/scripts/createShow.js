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
	
	var show = new FormData();
	show.append("picture",document.getElementById('picture').files[0]);
	show.append("name", $("#name").val());
	show.append("cast", $("#cast").val());
	show.append("description", $("#description").val());
	show.append("length", $("#length").val());
	
	var type = new Object();
	type.id = $("#type").val();
	//show.append("type", type);
	
	var genre = new Object();
	genre.id = $("#genre").val();
	//show.append("genre", genre);
	
	
	$.ajax({
    	url: "../show/add/"+ genre.id + "/" + type.id,
		type: "POST",
	    enctype: 'multipart/form-data',
	    data: show,
        processData: false, //prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        cache: false,
        success: function (data) {
        	window.location.href = "../Shows.html"
        },
		error: function(thrownError){
			console.log(thrownError.responseText);
		}
	});
}

function Cancel(){
	window.history.back();
}