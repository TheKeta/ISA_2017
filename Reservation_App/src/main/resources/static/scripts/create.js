$(document).ready(function() {
	var url = window.location.href;
	var str = url.split("=");
	var title = str[1].charAt(0).toLowerCase() + str[1].slice(1) + "s";
	document.title = "Create " +  title;
});

