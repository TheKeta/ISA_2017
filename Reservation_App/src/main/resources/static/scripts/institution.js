$(document).ready(function() {
	defaultElements();
});


function defaultElements(){
	var url = window.location.href;
	var str = url.split("=");
	if(str.length < 2){
		window.location.href = "../Home.html";
		return;
	}
	var title = str[1].charAt(0).toUpperCase() + str[1].slice(1) + "s";
	document.title = title;
	$('#header').html(title);
	$.ajax({
		url: "../institution/getInstitutions/" + str[1],
		success: function(data){
			$('#institutionDetails').html("");
			data.map(createInstitutionElements);
		}
	});
}


function createInstitutionElements(institutionRated){
	var str = "";
	str += '<div style=\"padding: 5px;\">';
	str += '<button type="button" class="btn" style="width: 100%; height: 30px; background: #d9d9d9;" data-toggle="collapse" data-target="#'+ institutionRated.institution.id + '">'+ institutionRated.institution.name +'</button>';
	str += '<div id=\"'+ institutionRated.institution.id + '\" class=\"collapse in\">';
	str += '<div class="row">';
	str += '<div class="col-sm-9">';
	str += '<ul>';
	str += '<li>Address: '+ institutionRated.institution.address +'</li>';
	str += '<li>Description: '+ institutionRated.institution.description +'</li>';
	if(isNaN(institutionRated.rating)){
		str += '<li>Rating: Have not been rated yet.</li>';
	}else{
		str += '<li>Rating: '+ institutionRated.rating +'</li>';
	}
	str += '</ul>';
	str += '</div>';
	str += '<div class="col-sm-3">';
	str += '<button onClick="Edit('+ institutionRated.institution.id +')">Edit</button>';
	str += '<button onClick="Delete('+ institutionRated.institution.id +')">Delete</button>';
	str += '<button onClick="Visit('+ institutionRated.institution.id +')">Visit</button>';
	str += '</div>';
	
	str += '</div>';
	str += '<div id="map_'+ institutionRated.institution.id  +'" style="height:300px; width:100%;"></div>';
	str += "</div>";
	str += "</div>";
	
	$('#institutionDetails').append(str);
	displayMap(institutionRated);
}

function Edit(id){
	window.location.href = "../EditInstitution.html?id="+id;
}

function Visit(id){
	window.location.href = "../Institution.html?id="+id;
}

function Delete(id){
	$.ajax({
		url: "../institution/delete/" + id,
		type: "DELETE",
		success: function(data){
			window.location.href = "../Institutions.html?type="+ data.type.name;
		},
		error: function(data){
			alert('This institution can\'t be deleted.');
		}
	});
}

function search(){
	event.preventDefault();
	var searchText = document.getElementById("searchInstitutions").value;
	if(searchText === ""){
		defaultElements();
		return;
	}
	var url = window.location.href;
	var str = url.split("=");
	$.ajax({
		url: "../institution/search/"+ str[1] +"/"+ searchText,
		success: function(data){
			$('#institutionDetails').html("");
			data.map(createInstitutionElements);
		}
		
	});
	return false;
}


function Add(){
	var url = window.location.href;
	var str = url.split("=");
	if(str.length < 2){
		window.location.href = "../Home.html";
		return;
	}
	window.location.href = "../CreateInstitution.html?type=" + str[1];
}


function displayMap(data) {
	var geocoder = new google.maps.Geocoder();
	
	
    geocoder.geocode( { 'address': data.institution.address}, function(results, status) {

    if (status == google.maps.GeocoderStatus.OK) {
    	var latitude = results[0].geometry.location.lat();
    	var longitude = results[0].geometry.location.lng();
    	
    	var map = new google.maps.Map(document.getElementById('map_'+data.institution.id), {
    	      center: {lat: latitude, lng: longitude},
    	      zoom: 16,
    	      mapTypeId: 'roadmap'
    	    });
        
        
    	var myLatlng = new google.maps.LatLng(latitude, longitude);
		var marker = new google.maps.Marker({
    	    position: myLatlng,
    	    map: map,
    	    title: 'Hello World!'
		});
	
		    
    } 
  });
}

