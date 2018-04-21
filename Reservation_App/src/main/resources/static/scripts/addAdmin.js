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
	var title = "Set Admin";
	document.title = title;
	$('#header').html(title);
	$.ajax({
		url: "../user/getUserRole",
		success: function(data){
			console.log(data);
			if(data!=1){
				window.location.href = "../Home.html";			}
		},
		error: function(xhr, ajaxOptions, thrownError){
			console.log(thrownError);

		}
	});
	
}

function change(){
	if($("#email").val()=="" || $("#role").val()==""){
		alert("Enter all fields properly");
		return;
	}
	$.ajax({
    	url: "../user/role/"+$("#email").val()+"/"+$("#role").val(),
		type: "POST",
		success: function (data) {
        	alert("Done");
        },
        error: function (e) {
        	alert("Enter all fields properly.")
            console.log(e.responseText);
        }
    });
}