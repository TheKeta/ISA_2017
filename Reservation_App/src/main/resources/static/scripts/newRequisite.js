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
	var title = "New Requisite";
	document.title = title;
	$('#header').html(title);
	
}

function create(){
//	var requisite = new Object();
//	requisite.name = $("#name").val();
//	requisite.description = $("#description").val();
//	requisite.endDate=new Date($("#endDate").val());
//	requisite.price=parseInt($("#price").val());
//	//requisite.picture = $('#picture').value;
//    
//    
//     var form = $('#newForm')[0];

    
    if (document.getElementById('image').files[0]!= undefined){
    	var data = new FormData();
        data.append("name", $("#name").val());
        data.append("description",$("#description").val());
        data.append("endDate",new Date($("#endDate").val()));
        data.append("price",parseInt($("#price").val()));
    	data.append("picture",document.getElementById('image').files[0]);
    	$.ajax({
        	url: "../requisite/addNewReqq",
        	 type: "POST",
             enctype: 'multipart/form-data',
             data: data,
            processData: false, //prevent jQuery from automatically transforming the data into a query string
            contentType: false,
            cache: false,
            success: function (data) {
            	$.ajax({
            		url: "../user/getUserRole",
            		success: function(data){
            			console.log(data);
            			if(data==3){
            				window.location.href = "../FunZone.html";  
            			}
            			else{
            				window.location.href = "../UserShop.html";
            			}
            		},
            		error: function(xhr, ajaxOptions, thrownError){
            			console.log(thrownError);

            		}
            	});
            	
            },
            error: function (e) {
            	alert("Enter all fields properly. (Image is not required)")
                console.log(e.responseText);
            }
        });
    }else{
    	var data = new Object();
        data.name= $("#name").val();
        data.description=$("#description").val();
        data.endDate=new Date($("#endDate").val());
        data.price=parseInt($("#price").val());
    	$.ajax({
        	url: "../requisite/addNewReq",
    		data: JSON.stringify(data),
    		type: "POST",
    		contentType: "application/json",
    		dataType: "json",
            success: function (data) {
            	$.ajax({
            		url: "../user/getUserRole",
            		success: function(data){
            			console.log(data);
            			if(data==3){
            				window.location.href = "../FunZone.html";  
            			}
            			else{
            				window.location.href = "../UserShop.html";
            			}
            		},
            		error: function(xhr, ajaxOptions, thrownError){
            			console.log(thrownError);

            		}
            	});
            },
            error: function (e) {
            	alert("Enter all fields properly. (Image is not required)")
                console.log(e.responseText);
            }
        });
    }
    	//    console.log(data)

}

function cancel(){
	window.location.href = "../FunZone.html";
}