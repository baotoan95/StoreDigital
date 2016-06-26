function like(id) {
	$.ajax({ 
	    url: "/StoreDigital/addWish", 
	    type: 'GET', 
	    dataType: 'json', 
	    data: {
	    	"id" : id
	    }, 
	    contentType: 'application/json',
	    mimeType: 'application/json',
	    success: function(data) {
	    	if(data.status == 'ok') {
	    		notification(data.product.id, data.product.name, data.product.urlImage, 'LIKE');
	    	} else {
	    		notification('', data.status, '', 'DEFAULT');
	    	}
	    },
	    error:function(data,status,er) { 
	        alert("error: "+data+" status: "+status+" er:"+er);
	    }
	});
}

function disLike(element, id) {
	$.ajax({ 
	    url: "/StoreDigital/delWish", 
	    type: 'GET', 
	    dataType: 'json', 
	    data: {
	    	"id" : id
	    }, 
	    contentType: 'application/json',
	    mimeType: 'application/json',
	    success: function(data) {
	    	if(data.status == 'ok') {
	    		notification(data.product.id, data.product.name, data.product.urlImage, 'DISLIKE');
		    	$(element).parent().parent().remove();
	    	} else {
	    		notification('', data.status, '', 'DEFAULT');
	    	}
	    },
	    error:function(data,status,er) { 
	        alert("error: "+data+" status: "+status+" er:"+er);
	    }
	});
}