var url = window.location.href;
var str = url.split("=");
$(document).ready(function() {	
	$.ajax({
		url: "../institution/" + str[1],
		success: function(data){
			$("#name").val(data.institution.name);
			$("#address").val(data.institution.address);
			$("#description").val(data.institution.description);
			$("#hiddenType").append('<input type="text" id="id" value="'+ data.institution.id +'" />')
			$("#hiddenType").append('<input type="text" id="typeId" value="'+ data.institution.type.id +'" />')
			$("#hiddenType").append('<input type="text" id="typeName" value="'+ data.institution.type.name +'" />')
			for(var i=0; i< data.users.length; i++){
				$("#admin").append(generateDropDown(data.users[i], data.institution.id))
			}
		} 
	});
});

function generateDropDown(data, id){
	var str = "";
	if(data.id == id){
		str += '<option selected value="'+ data.id +'">'+ data.firstName + " " +data.lastName +'</option>'
	}else{
		str += '<option value="'+ data.id +'">'+ data.firstName + " " +data.lastName +'</option>'
	}
	return str;
}


function Save(){
	
	var obj = new Object();
	
	obj.id = $("#id").val();
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
		success: function(institution){
			window.location.href="../Institutions.html?type=" + institution.type.name; 
		}
	});
}

function Cancel(){
	window.history.back();
}