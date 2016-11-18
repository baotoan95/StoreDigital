function addCompare(id) {
	$.ajax({ 
	    url: "/StoreDigital/addCompare", 
	    type: 'GET', 
	    dataType: 'json', 
	    data: {
	    	"id" : id
	    }, 
	    contentType: 'application/json',
	    mimeType: 'application/json',
	    success: function(data) {
	    	if(data.status != 'ok') {
	    		notification('', data.status,'','NOTIF');
	    	} else {
	    		notification(data.product.id, data.product.name, data.product.urlImage, 'ADD_COMPARE');
	    	}
	    },
	    error:function(data,status,er) { 
	        alert("error: "+data+" status: "+status+" er:"+er);
	    }
	});
}

function delCompare(id) {
	$.ajax({ 
	    url: "/StoreDigital/delCompare", 
	    type: 'GET', 
	    dataType: 'json', 
	    data: {
	    	"id" : id
	    },
	    contentType: 'application/json',
	    mimeType: 'application/json',
	    success: function(data) {
	    	if(data.status == 'ok') {
		    	notification(data.product.id, data.product.name, data.product.urlImage, 'DEL_COMPARE');
		    	$('.groupName').attr('colspan', data.quantity + 1);
		    	$('.widthCol').attr('width', (100 / data.quantity) + '%'); 
		    	$("." + id).remove();
	    	} else {
	    		notification('', data.status, '', 'DEFAULT');
	    	}
	    },
	    error:function(data,status,er) { 
	        alert("error: "+data+" status: "+status+" er:"+er);
	    }
	});
}