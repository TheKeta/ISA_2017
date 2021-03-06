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
		url: "../user/getUserRole",
		success: function(data){
			console.log(data);
			if(data==3){
				$('#forAdm').append('<button onClick="addnewRequisite()">Add New Requisite</button>');
			}
		},
		error: function(xhr, ajaxOptions, thrownError){
			console.log(thrownError);

		}
	});
	
	$.ajax({
		url: "../requisite/getRequisites/new",
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
		+ '<button onClick="order('+ reqs.id + ')">Order Item</button>'
		+ '</div>'
		+ '</div>';
	
	$('#allRequisites').append(str);
	$.ajax({
		url: "../user/getCurrentUser",
		success: function(datas){
			if(datas==null){
				window.location.href = "../Login.html";			
			}
			else{
				console.log(datas);
				if(datas.userType.id == 3){
					var pom = "";
					pom += '<button onClick="editRequisite('+ reqs.id + ')">Edit</button></br>'
					+ '<button onClick="deleteRequisite('+ reqs.id + ')">Delete</button>'
					$('#orDiv'+reqs.id).html(pom);
				}
			}
		},
		error: function(xhr, ajaxOptions, thrownError){
			console.log(thrownError);

		}
	});
}

function editRequisite(reqsID){
	window.location.href = "../EditReq.html?id="+reqsID;	
}

function deleteRequisite(reqsID){
	$.ajax({
    	url: "../requisite/delete/"+reqsID,
		type: "DELETE",
        success: function (data) {
        	window.location.href = "../FunZone.html";
        },
		error: function(xhr, ajaxOptions, thrownError){
			console.log(thrownError);

		}
	});
}

function order(reqsID){
	var data = new Object();
	data.itemsID = reqsID;
	$.ajax({
    	url: "../bid/order",
		data: JSON.stringify(data),
		type: "POST",
		contentType: "application/json",
		dataType: "json",
        success: function (data) {
        	alert("Order is complete.");
        },
		error: function(xhr, ajaxOptions, thrownError){
			console.log(thrownError);

		}
	});
}

function userShop(){
	window.location.href = "../UserShop.html";
}

function addnewRequisite(){
	window.location.href = "../NewRequisite.html";
}