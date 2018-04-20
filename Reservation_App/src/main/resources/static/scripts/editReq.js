$(document).ready(function() {
	defaultElements();
});


function defaultElements(){
	var url = window.location.href;
	var str = url.split("=");
	var title = "Edit Requisite";
	document.title = title;
	$('#header').html(title);
	
}


function create(){
    
    if (document.getElementById('image').files[0]!= undefined){
    	var data = new FormData();
    	var url = window.location.href;
    	var str = url.split("=");
    	data.append("id",str[1]);
    	if(($("#name").val()).trim()!=""){
    		data.append("name", $("#name").val());
    	}
    	if(($("#description").val()).trim()!="")
    		data.append("description",$("#description").val());
    	if(($("#endDate").val()).trim()!="")
    		data.append("endDate",new Date($("#endDate").val()));
    	if(($("#price").val()).trim()!="")
    		data.append("price",parseInt($("#price").val()));
    	data.append("picture",document.getElementById('image').files[0]);
//    	for (var pair of data.entries()) {
//    	    console.log(pair[0]+ ', ' + pair[1]); 
//    	}
    	$.ajax({
        	url: "../requisite/editReqq",
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
    	var url = window.location.href;
    	var str = url.split("=");
    	data.id =str[1];
    	if(($("#name").val()).trim()!=""){
    		data.name= $("#name").val();
    	}
    	if(($("#description").val()).trim()!="")
    		data.description=$("#description").val();
    	if(($("#endDate").val()).trim()!="")
            data.endDate=new Date($("#endDate").val());
    	if(($("#price").val()).trim()!="")
    		 data.price=parseInt($("#price").val());
//    	console.log(data);
    	$.ajax({
        	url: "../requisite/editReq",
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
