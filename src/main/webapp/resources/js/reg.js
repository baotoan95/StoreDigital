$(function() {
	$('#newsletter1').keypress(function(e) {
		if (e.keyCode == 13) {
			reg();
		}
	});
});

function reg() {
	var email = $('#newsletter1').val();

	$.ajax({
		url : "/StoreDigital/reguser",
		type : 'GET',
		dataType : 'json',
		data : {
			"email" : email
		},
		contentType : 'application/json',
		mimeType : 'application/json',
		success : function(data) {
			notification('', data.message, '', 'NOTIF');
		},
		error : function(data, status, er) {
			alert("error: " + data + " status: " + status + " er:" + er);
		}
	});
}