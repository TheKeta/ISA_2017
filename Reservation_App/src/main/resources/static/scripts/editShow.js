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
			$("#poster").val(showGenresTypes.show.pictureDB)
			$("#id").val(showGenresTypes.show.id);
			
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
	var show = new FormData();
	if(document.getElementById('picture').files[0]!= undefined){
		show.append("picture", document.getElementById('picture').files[0]);
	}else{
		show.append("pictureDB", document.getElementById('poster').value);	
	}
	var id = window.location.href.split("=")[1];
	
	show.append("name", $("#name").val());
	show.append("cast", $("#cast").val());
	show.append("description", $("#description").val());
	show.append("length", $("#length").val());
	show.append("id", $("#id").val());
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