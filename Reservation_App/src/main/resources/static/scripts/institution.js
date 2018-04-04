$(document).ready(function() {
	defaultElements();
});


function defaultElements(){
	var url = window.location.href;
	var str = url.split("=");
	document.title = str[1] + "s";
	$('#header').html(str[1]+"s");
	
	$.ajax({
		url: "../institution/getInstitutions/" + str[1],
		success: function(data){
			var str = "";
			for(var i =0; i< data.length; i++){
				str += createInstitutionElements(data[i]);
			}
			$('#institutionDetails').html(str);
		}
	});
}

function createInstitutionElements(data){
	var str = "";
	str += '<div style=\"padding: 5px;\">';
	str += '<button type=\"button\" class=\"btn\" style=\"width: 100%; height: 30px; background: #d9d9d9;\" data-toggle=\"collapse\" data-target=\"#'+ data.institution.id + '\"\">'+ data.institution.name +'</button>';
	str += '<div id=\"'+ data.institution.id + '\" class=\"collapse\">';
	str += '<ul>';
	str += '<li>Address: '+ data.institution.address +'</li>';
	str += '<li>Description: '+ data.institution.description +'</li>';
	if(isNaN(data.rating)){
		str += '<li>Rating: Have not been rated yet.</li>';
	}else{
		str += '<li>Rating: '+ data.rating +'</li>';
	}
	str += '</ul>';
	str += "</div>";
	str += "</div>";
	
	return str;
}



function search(){
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
			var str = "";
			for(var i =0; i< data.length; i++){
				str += createInstitutionElements(data[i]);
			}
			$('#institutionDetails').html(str);
		}
		
	});
}

