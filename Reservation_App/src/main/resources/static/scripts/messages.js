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
	var title = "Messages";
	document.title = title;
	$('#header').html(title);
	$.ajax({
		url: "../user/getUserRole",
		success: function(data){
			console.log(data);
			if(data==0){
				window.location.href = "../Login.html";			}
		},
		error: function(xhr, ajaxOptions, thrownError){
			console.log(thrownError);

		}
	});
	
	$.ajax({
		url: "../message/getRMessages",
		success: function(data){
			
			$('#recived').html("");
			for(var i =0; i< data.length; i++){
				console.log(data[0]);
				createRequisiteElement(data[i]);
			}
		},
		error: function(xhr, ajaxOptions, thrownError){
			console.log(thrownError);

		}
	});
	$.ajax({
		url: "../message/getSMessages",
		success: function(data){
			
			$('#sent').html("");
			for(var i =0; i< data.length; i++){
				console.log(data[0]);
				createRequisiteElementt(data[i]);
			}
		},
		error: function(xhr, ajaxOptions, thrownError){
			console.log(thrownError);

		}
	});
	
}

function createRequisiteElement(msg, name){
	var str = "";
	str += '<div style=\"padding: 5px; border: 1px solid black; overflow: auto;margin: 5px;\">'
		+ '<div id ="mmsg'+msg.id+'"></div>'
		+ '<div style=\"padding: 5px; border: 1px solid black; overflow: auto;"><p>'+msg.text+'</p></div>'
		+ '</div><br>';
	
	$('#received').append(str);
	
	$.ajax({
		url: "../message/"+msg.senderID,
		success: function(datas){
			$('#mmsg'+msg.id).html('<p><b>'+datas+'</b></p>');
			
		},
		error: function(xhr, ajaxOptions, thrownError){
			console.log(thrownError);

		}
	});
}

function createRequisiteElementt(msg, name){
	var str = "";
	str += '<div style=\"padding: 5px; border: 1px solid black; overflow: auto;margin: 5px;\">'
		+ '<div id ="mssg'+msg.id+'"></div>'
		+ '<div style=\"padding: 5px; border: 1px solid black; overflow: auto;"><p>'+msg.text+'</p></div>'
		+ '</div><br>';
	
	$('#sent').append(str);
	
	$.ajax({
		url: "../message/"+msg.reciverID,
		success: function(datas){
			$('#mssg'+msg.id).html('<p><b>'+datas+'</b></p>');
			
		},
		error: function(xhr, ajaxOptions, thrownError){
			console.log(thrownError);

		}
	});
}