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
	$.ajax({
		url: "../user/getUserRole",
		success: function(data){
			console.log(data);
			if(data!=3){
				window.location.href = "../Home.html";
			}
		},
		error: function(xhr, ajaxOptions, thrownError){
			console.log(thrownError);

		}
	});
	
	var title = "Fresh Requisites";
	document.title = title;
	$('#header').html(title);
		
	$.ajax({
		url: "../requisite/getFreshRequisites",
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
	var datum = reqs.endDate.split("T");
	str += '<div style=\"padding: 5px; border: 1px solid black; overflow: auto;\">'
		+ '<p><b>' + reqs.name + '</b></p>'
		+ '<div style="float:left; width: 500px;">'
		+ '<img src=\"data:image/png;base64,' +reqs.pictureDB +'\" alt=\"'+reqs.name+'\" style="width:500px;height:250px;">'
		+ '</div>'
		+ '<div style="float:center; width: auto;">'
		+ '<p><i>'+ reqs.description+ '</i></p>'
		+ '<p>Active till: '+ datum[0] +'</p>'
		+ '<p>Price: '+ reqs.price + ' din</p>'
		+ '</div>'
		+ '<div id="orDiv'+reqs.id+'" style="float:right; width: 100px;"> '
		+ '<button onClick="approve('+ reqs.id + ')">Approve</button>'
		+ '<button onClick="disprove('+ reqs.id + ')">Disprove</button>'
		+ '</div>'
		+ '</div>';
	
	$('#allRequisites').append(str);
	
}

function approve(reqsID){
	$.ajax({
    	url: "../requisite/approve/"+reqsID,
		type: "POST",
        success: function (data) {
        	window.location.href = "../Approve.html";
        },
		error: function(xhr, ajaxOptions, thrownError){
			console.log(thrownError);

		}
	});
}

function disprove(reqsID){
	$.ajax({
    	url: "../requisite/disprove/"+reqsID,
		type: "POST",
        success: function (data) {
        	window.location.href = "../Approve.html";
        },
		error: function(xhr, ajaxOptions, thrownError){
			console.log(thrownError);

		}
	});
}