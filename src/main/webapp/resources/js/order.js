function submitForm() {
	var order = $('#order');
	alert(order);
	$.ajax({
	    type: "POST",
	    data: order,
	    url: "/StoreDigital/order",
	    dataType: "JSON",
	    success: function(){
	       alert("success");
	    }
	});
}

function updateOrder(detailOrderId, quantity, element) {
	$.ajax({
	    type: "POST",
	    data: {
	    	"deId" : detailOrderId,
	    	"q" : quantity
	    },
	    url: "/StoreDigital/updateOrder",
	    dataType: "JSON",
	    success: function(data) {
	    	if(data.status == 'ok') {
	    		notification(data.product.id, data.product.name, data.product.urlImage, 'UPDATE_ORDER_DETAIL');
	    		$(element).parent().next().find('.price').html(data.price + ' VNĐ');
	    	} else if(data.status == 'delete') {
	    		notification(data.product.id, data.product.name, data.product.urlImage, 'DEL_ORDER_DETAIL');
	    		if($(element).attr('name') == 'quantity') {
	    			$(element).parent().parent().remove();
	    		} else {
	    			$(element).parent().remove();
	    		}
	    	} else {
	    		notification('', data.status, '', 'DEFAULT');
	    	}
	    }
	});
}

function deleteOrder(orderId) {
	$.ajax({
	    type: "POST",
	    data: order,
	    url: "/StoreDigital/delOrder",
	    dataType: "JSON",
	    success: function(){
	       alert("success");
	    }
	});
}