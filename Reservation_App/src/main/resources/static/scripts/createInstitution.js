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
			$("#hiddenType").append('<input type="text" id="typeId" value="'+ data.institution.id +'" />');
			$("#hiddenType").append('<input type="text" id="typeName" value="'+ data.institution.name +'" />');
			$("#admin").append(data.users.map(generateDropDown));
		}	
	});

});

function generateDropDown(user){
	var str = "";
	str += '<option value="'+ user.id +'">'+ user.username +'</option>';
	return str;
}


function Add(){
	var obj = new Object();
	obj.name = $("#name").val();
	obj.address = $("#address").val();
	obj.description = $("#description").val();
	
	var type = new Object();
	type.id =  $("#typeId").val();
	type.name = $("#typeName").val();
	obj.type = type;
	
	var admin = new Object();
	admin.id = $("#admin").val();
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

