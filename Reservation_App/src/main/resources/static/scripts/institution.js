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
			for(var i =0; i< data.length; i++){
				createInstitutionElements(data[i]);
			}
		}
	});
}

function createInstitutionElements(institutionRated){
	var str = "";
	str += '<div style=\"padding: 5px;\">';
	str += '<button type=\"button\" class=\"btn\" style=\"width: 100%; height: 30px; background: #d9d9d9;\" data-toggle=\"collapse\" data-target=\"#'+ institutionRated.institution.id + '\"\">'+ institutionRated.institution.name +'</button>';
	str += '<div id=\"'+ institutionRated.institution.id + '\" class=\"collapse\">';
	str += '<ul>';
	str += '<li>Address: '+ institutionRated.institution.address +'</li>';
	str += '<li>Description: '+ institutionRated.institution.description +'</li>';
	if(isNaN(institutionRated.rating)){
		str += '<li>Rating: Have not been rated yet.</li>';
	}else{
		str += '<li>Rating: '+ institutionRated.rating +'</li>';
	}
	str += '</ul>';
	str += '<div id="map_'+ institutionRated.institution.id  +'" style="height:300px; width:600px;"></div>';
	str += '<input id="pac-input_' + institutionRated.institution.id +'" class="controls" type="text" placeholder="Search Box">';
	str += "</div>";
	str += "</div>";
	
	$('#institutionDetails').append(str);
	displayMap(institutionRated);
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
			for(var i =0; i< data.length; i++){
				createInstitutionElements(data[i]);
			}
		}
		
	});
	return false;
}


function displayMap(data) {
    var map = new google.maps.Map(document.getElementById('map_'+data.institution.id), {
      center: {lat: -33.8688, lng: 151.2195},
      zoom: 13,
      mapTypeId: 'roadmap'
    });

    // Create the search box and link it to the UI element.
    var input = document.getElementById('pac-input_'+data.institution.id);
    input.value = "Ivana Antunovica";
    var searchBox = new google.maps.places.SearchBox(input);
    map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

    // Bias the SearchBox results towards current map's viewport.
    map.addListener('bounds_changed', function() {
      searchBox.setBounds(map.getBounds());
    });

    var markers = [];
    // Listen for the event fired when the user selects a prediction and retrieve
    // more details for that place.
    searchBox.addListener('places_changed', function() {
      var places = searchBox.getPlaces();

      if (places.length == 0) {
        return;
      }

      // Clear out the old markers.
      markers.forEach(function(marker) {
        marker.setMap(null);
      });
      markers = [];

      // For each place, get the icon, name and location.
      var bounds = new google.maps.LatLngBounds();
      places.forEach(function(place) {
        if (!place.geometry) {
          console.log("Returned place contains no geometry");
          return;
        }
        var icon = {
          url: place.icon,
          size: new google.maps.Size(71, 71),
          origin: new google.maps.Point(0, 0),
          anchor: new google.maps.Point(17, 34),
          scaledSize: new google.maps.Size(25, 25)
        };

        // Create a marker for each place.
        markers.push(new google.maps.Marker({
          map: map,
          icon: icon,
          title: place.name,
          position: place.geometry.location
        }));

        if (place.geometry.viewport) {
          // Only geocodes have viewport.
          bounds.union(place.geometry.viewport);
        } else {
          bounds.extend(place.geometry.location);
        }
      });
      map.fitBounds(bounds);
    });
  }

