$(document).ready(function() {
	defaultElements();
});


function defaultElements(){
	var url = window.location.href;
	var str = url.split("=");
//	if(str.length < 2){
//		window.location.href = "../FunZone.html";
//		return;
//	}
	var title = "Fun Zone";
	document.title = title;
	$('#header').html(title);
	$.ajax({
		url: "../requisite/getRequisites/",
		success: function(data){
			$('#allRequisites').html("");
			console.log(data);
			for(var i =0; i< data.length; i++){
				createRequisiteElement(data[i]);
			}
		},
		error: function(xhr, ajaxOptions, thrownError){
			console.log(thrownError);

		}
	});
}

function createRequisiteElement(reqs){
	
	var str = "";
	str += '<div style=\"padding: 5px; border: 1px solid black\">'
		+ ''
		+ '</div>'
}
