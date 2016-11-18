/**
 * 
 */

function delPromotion(id, element, type) {
	$.ajax({
		url : "/StoreDigital/mngPromotions/delete",
		type : 'GET',
		dataType : 'json',
		data : {
			"id" : id,
			"type" : type
		},
		contentType : 'application/json',
		mimeType : 'application/json',
		success : function(data) {
			if(data.status == 'ok') {
				$(element).parent().parent().remove();
			} else {
				alert("error");
			}
		}
	});
}

function delCate(id, element) {
	$.ajax({
		url : "/StoreDigital/mngCates/delete",
		type : 'GET',
		dataType : 'json',
		data : {
			"id" : id
		},
		contentType : 'application/json',
		mimeType : 'application/json',
		success : function(data) {
			if(data.status == 'ok') {
				$(element).parent().parent().remove();
			} else {
				alert("error");
			}
		}
	});
}

function delAdvert(id, element) {
	$.ajax({
		url : "/StoreDigital/mngAdvertiments/delete",
		type : 'GET',
		dataType : 'json',
		data : {
			"id" : id
		},
		contentType : 'application/json',
		mimeType : 'application/json',
		success : function(data) {
			if(data.status == 'ok') {
				$(element).parent().parent().remove();
			} else {
				alert("error");
			}
		}
	});
}

function delUser(mail, element) {
	$.ajax({
		url : "/StoreDigital/mngMembers/delete",
		type : 'GET',
		dataType : 'json',
		data : {
			"mail" : mail
		},
		contentType : 'application/json',
		mimeType : 'application/json',
		success : function(data) {
			if(data.status == 'ok') {
				$(element).parent().parent().remove();
			} else {
				alert("error");
			}
		}
	});
}

function delOrder(id, element) {
	$.ajax({
		url : "/StoreDigital/mngOrders/delete",
		type : 'GET',
		dataType : 'json',
		data : {
			"id" : id
		},
		contentType : 'application/json',
		mimeType : 'application/json',
		success : function(data) {
			if(data.status == 'ok') {
				$(element).parent().parent().parent().remove();
				$('#totalOrderNotApproved').html(data.totalOrderNotApproved);
			} else {
				alert("error");
			}
		}
	});
}

function updateDetailOrder(id, quantity, element) {
	$.ajax({
		url : "/StoreDigital/mngOrders/updateDetailOrder",
		type : 'GET',
		dataType : 'json',
		data : {
			"id" : id,
			"quantity" : quantity
		},
		contentType : 'application/json',
		mimeType : 'application/json',
		success : function(data) {
			if(data.status == "deleteOrder") {
				window.location = "";
			} else if(data.status == "delete") {
				$(element).parent().parent().remove();
			} else if(data.status == "update") {
				$(element).parent().next().html(data.pay);
			} else {
				alert("Error");
			}
		}
	});
}

function delPost(id, element) {
	$.ajax({
		url : "/StoreDigital/mngPosts/delPost",
		type : 'GET',
		dataType : 'json',
		data : {
			"id" : id
		},
		contentType : 'application/json',
		mimeType : 'application/json',
		success : function(data) {
			if(data.status == "ok") {
				$(element).parent().parent().remove();
			} else {
				alert(data.status);
			}
		}
	});
}