function Confirm(){
	var fromDate = new Date($("#fromDate").val());
	var toDate = new Date($("#toDate").val());
	var date1 = formatDate(fromDate);
	var date2 = formatDate(toDate);
	var id = window.location.href.split("=")[1];
	$.ajax({
		url: "../reservation/report/"+id + "/" +date1+"/"+date2,
		success: function(res){
			$("#reports").html("");
			str = '<table class="table">';
			str += '<tr><th>Date</th><th>Show</th><th>User</th><th>Price</th></tr>';
			
			for(var i=0; i<res.reservations.length; i++){
				str += createTable(res.reservations[i]);
			}
			str += '<tr><td colspan="4" style="text-align: right;">'+ res.sum +'</td></tr>'
			str += '</table>';
			$("#reports").append(str);
		}
	});
}


function createTable(reservation){
	return '<tr><td>'+ createDate(reservation.event.eventDate) +'</td><td>'+ reservation.event.show.name +'</td><td>'+ reservation.user.email +'</td><td>'+ reservation.price +'</td></tr>';
}

function formatDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join('-');
}

function createDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [day, month, year].join('-');
}