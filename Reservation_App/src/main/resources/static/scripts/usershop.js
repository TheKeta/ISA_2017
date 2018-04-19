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
	str += '<div style=\"padding: 5px; border: 1px solid black; overflow: auto;\">'
		+ '<p><b>' + reqs.name + '</b></p>'
		+ '<div style="float:left; width: 500px;">'
		+ '<img src=\"data:image/png;base64,' +reqs.pictureDB +'\" alt=\"'+reqs.name+'\" style="width:900px;height:250px;">'
		+ '</div>'
		+ '<div style="float:center; width: auto;">'
		+ '<p><i>'+ reqs.description+ '</i></p>'
		+ '<p>Price: '+ reqs.price + ' din</p>'
		+ '</div>'
		+ '<div style="float:right; width: auto;"> '
		+ '<p>Currently highest bid is: '+ reqs.price + ' din.</p>'
		+ '<p> Place your bid: <input type="text" name="placeBid" id="placeBid"> </p>'
		+ '<button onClick="SendBid('+ reqs.id + ')">Send Bid</button>'
		+ '</div>'
		+ '</div>';
	
	$('#allRequisites').append(str);
	
}

function addItem(){
	window.location.href = "../NewRequisite.html";
}