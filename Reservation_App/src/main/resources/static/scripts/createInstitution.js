var url = window.location.href;
var str = url.split("=");

$(document).ready(function() {
	if(str.length < 2){
		window.location.href = "../Home.html";
		return;
	}
	var title = str[1].charAt(0).toLowerCase() + str[1].slice(1);
	document.title = "Create " +  title;
	$('#header').html("Create " +  title);
	
	$.ajax({
		url: "../institutionType/institutionTypeId/" + str[1],
		success: function(data){
			$("#hiddenType").append('<input type="text" id="typeId" value="'+ data.id +'" />')
			$("#hiddenType").append('<input type="text" id="typeName" value="'+ data.name +'" />')
		}	
	});

});


function Add(){
	var obj = new Object();
	obj.name = $("#name").val();
	obj.address = $("#address").val();
	obj.description = $("#description").val();
	var type = new Object();
	type.id =  $("#typeId").val();
	obj.type = type;
	
	var admin = new Object();
	admin.id = "5";
	obj.admin = admin;
	
	$.ajax({
		url: "../institution",
		data: JSON.stringify(obj),
		type: "POST",
		contentType: "application/json",
		dataType: "json",
		success: function(data){
			window.location.href="../Institutions.html?type=" + str[1]; 
		}
	});
}

