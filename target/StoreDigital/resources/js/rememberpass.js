function remember() {
	var email = $('#email').val();
	alert(email);
	$.ajax({
		url : "/StoreDigital/requestRemember",
		type : 'GET',
		dataType : 'json',
		data : {
			"email" : email
		},
		contentType : 'application/json',
		mimeType : 'application/json',
		success : function(data) {
			$('#error').html(data.status);
		},
		error : function(data, status, er) {
			alert("error: " + data + " status: " + status + " er:" + er);
		}
	});
}