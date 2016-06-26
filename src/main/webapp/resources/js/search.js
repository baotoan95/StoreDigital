function submitForm(element, type, val) {
	if(type == 'page') {
		var value = $(element).text();
		$('#currentPage').attr("value", value);
	} else if(type == 'sort' || type == 'view') {
		$('#currentPage').attr("value", 1);
	}
	$('#searchForm').submit();
}

function autoComplete(key) {
	if(key != "") {
	var type = $('#cateId').val();
	$('.searchBoxAutoComplete #autocomplete').empty();
	$('.searchBoxAutoComplete #autocomplete').empty();
	$.ajax({ 
	    url: "/StoreDigital/autoComplete", 
	    type: 'GET', 
	    dataType: 'json', 
	    data: {
	    	"type" : type,
	    	"key" : key
	    }, 
	    contentType: 'application/json',
	    mimeType: 'application/json',
	    success: function(data) {
	    	$.each(data, function(key, value) {
	    		var item = "<a href='view?id="+value.id+"'><div class='autoItem'>"
						    	+"<img class='itemLeft' alt='' src='/StoreDigital/resources"+value.urlImage+"'>"
						    	+"<div class='itemRight'>"
						    		+"<span class='title'>"+value.name+"</span><br/>"
						    		+"<span class='price'>"+value.newPrice+"</span>"
						    	+"</div>"
						    +"</div></a>";
	    		$(item).appendTo($('#autocomplete'));
	    		$('#autocomplete').css("display","block");
	    	});
	    },
	    error:function(data,status,er) { 
	        alert("error: "+data+" status: "+status+" er:"+er);
	    }
	});
	} else {
		$('.searchBoxAutoComplete #autocomplete').empty();
		$('#autocomplete').css("display","none");
	}
}