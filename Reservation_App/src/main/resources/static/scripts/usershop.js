$(document).ready(function() {
	defaultElements();
});


function defaultElements(){
	var url = window.location.href;
	var str = url.split("=");

	var title = "Shop";
	document.title = title;
	$('#header').html(title);
	$.ajax({
		url: "../requisite/getRequisites/used",
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
		+ '</div>'
		+ '<div style="float:right; width: auto;"> '
		+ '<p>Currently highest bid is: <p id="curPrice'+reqs.id+'">'+ reqs.price + '</p> din.</p>'
		+ '<div id = bidForm'+reqs.id+'>'
		+ '<p>Place your bid: <input type="text" name="placeBid" id="placeBid'+reqs.id+'"> </p>'
		+ '<button onClick="SendBid('+ reqs.id + ')">Send Bid</button>'
		+ '</div>'
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
				if(datas.id == reqs.creator){
					var pom = "";
					pom += '<button onClick="acceptBid('+ reqs.id + ')">Accept Bid</button>'
					$('#bidForm'+reqs.id).html(pom);
				}
			}
		},
		error: function(xhr, ajaxOptions, thrownError){
			console.log(thrownError);

		}
	});
	
}

function acceptBid(reqsID){
	var data = new Object();
	data.itemsID = reqsID;
	$.ajax({
		url: "../bid/acceptBid",
		data: JSON.stringify(data),
		type: "POST",
		contentType: "application/json",
		dataType: "json",
        success: function (data) {
        	window.location.href = "../UserShop.html";
        },
		error: function(xhr, ajaxOptions, thrownError){
			console.log(thrownError);

		}
	});
}

function SendBid(reqsID){
	var data = new Object();
	data.itemsID = reqsID;
	var price = parseInt($("#placeBid"+reqsID).val());
	if($("#placeBid").val() == ""){
		alert("Enter bidding price.");
		return;
	}
	data.price = price;
	$.ajax({
    	url: "../bid/addNewBid",
		data: JSON.stringify(data),
		type: "POST",
		contentType: "application/json",
		dataType: "json",
        success: function (data) {
        	console.log(data)
        	$('#curPrice'+reqsID).html(price);
        },
		error: function(xhr, ajaxOptions, thrownError){
			console.log(thrownError);

		}
	});
}

function addItem(){
	window.location.href = "../NewRequisite.html";
}