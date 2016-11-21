function notification(productId, productName, urlImage, type) {
	var text = "";
	if(type == 'ADD_CART') {
		text = 'Bạn vừa thêm thành công <a href="view?id='+ productId +'">'+ productName +'</a> vào giỏ hàng';
	} else if(type == 'DEL_CART') {
		text = 'Bạn vừa xóa thành công <a href="view?id='+ productId +'">'+ productName +'</a> trong giỏ hàng';
	} else if(type == 'UPDATE_CART') {
		text = 'Bạn vừa cập nhật thành công <a href="view?id='+ productId +'">'+ productName +'</a> trong giỏ hàng';
	} else if(type == 'LIKE') {
		text = 'Bạn vừa thêm thành công <a href="view?id='+ productId +'">'+ productName +'</a> vào danh sách ưu thích';
	} else if(type == 'DISLIKE') {
		text = 'Bạn vừa xóa thành công <a href="view?id='+ productId +'">'+ productName +'</a> trong danh sách ưa thích';
	} else if(type == 'ADD_COMPARE') {
		text = 'Bạn vừa thêm thành công <a href="view?id='+ productId +'">'+ productName +'</a> vào danh sách so sánh';
	} else if(type == 'DEL_COMPARE') {
		text = 'Bạn vừa xóa thành công <a href="view?id='+ productId +'">'+ productName +'</a> trong danh sách so sánh';
	} else if(type == 'UPDATE_ORDER_DETAIL') {
		text = 'Cập nhật thành công <a href="view?id='+ productId +'">'+ productName +'</a> trong đơn hàng';
	} else if(type == 'DEL_ORDER_DETAIL') {
		text = 'Đã xóa <a href="view?id='+ productId +'">'+ productName +'</a> trong đơn hàng';
	} else {
		text = productName;
	}
	if(urlImage == '') {
		urlImage = "/images/error.png";
	}
	//sinh id tự động cho notification-item ngẫu nhiên
	var d = new Date();
	var idForNotif = "notif"+d.getTime();
	
	//div notification-item
	var notiContent = '<div id="'+ idForNotif +'" class="notification-item">'
							+'<div class="notification-img">'
								+'<img src="/StoreDigital/resources'+ urlImage +'" alt="notification-img"/>'
							+'</div>'
							+'<div class="notification-content">'
								+'<p>'+ text +'</p>'
							+'</div>'
							+'<span onclick="closeNotifFas(this)" class="btn-close-notification"></span>'
					  +'</div>';
	$('#notification-container').append(notiContent);
	playSound('notification');
	closeNotif('#'+idForNotif);
}

//=========== Common ===================
function closeNotifFas(e) {
	$(e).parent().remove();
}

function playSound(filename){ 
	$("#sound").html('<audio autoplay="autoplay"><source src="/StoreDigital/resources/sounds/' + filename + '.mp3" type="audio/mpeg" /><source src="/StoreDigital/resources/sounds/' + filename + '.ogg" type="audio/ogg" /><embed hidden="true" autostart="true" loop="false" src="/StoreDigital/resources/sounds/' + filename +'.mp3" /></audio>');
}

var notifTimeout;
//remove notification
function closeNotif(idForNotif) {
	notifTimeout = setTimeout(function() {
		$(idForNotif).animate({opacity:'0'}, 500).delay(1000).animate({marginTop: '0'}, 0, function(){
			$(idForNotif).remove();
		});
	}, 10000);
};

//clear timeout when hover
function saveNotif() {
	clearTimeout(notifTimeout);
}
