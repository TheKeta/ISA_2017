function Confirm(){
	var fromDate = new Date($("#fromDate").val());
	var toDate = new Date($("#toDate").val());
	var date1 = formatDate(fromDate);
	var date2 = formatDate(toDate);
	$.ajax({
		url: "../reservation/report/"+date1+"/"+date2,
		success: function(event){
			
		}
	});
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