function cart(productId, type, element) {
	var url = "";
	if(type == "ADD") {
		url = "/StoreDigital/addCart";
	} else {
		url = "/StoreDigital/removeCart";
	}
	$.ajax({ 
	    url: url, 
	    type: 'GET', 
	    dataType: 'json', 
	    data: {
	    	"id" : productId
	    }, 
	    contentType: 'application/json',
	    mimeType: 'application/json',
	    success: function(data) {
	    	$('#cart-total').html(data.sizeCart);
	    	if(data.listRecenty != null && data.listRecenty.length > 0) {
	    	var listItem = "";
	        $.each(data.listRecenty, function(key, value) {
	        var item = "<li class='item even'>"
						+ "<a class='product-image' href='view?id="+value.id+"' title='"+value.name+"'>"
						+ "<img alt='"+value.name+"' src='/StoreDigital/resources"+value.imageUrl+"' width='80'>"
						+ "</a>"
							+ "<div class='detail-item'>"
								+ "<div class='product-details'>"
									+ "<a href='#' title='Xóa' onClick=\"cart("+value.id+", 'DEL')\" class='glyphicon glyphicon-remove'>&nbsp;</a>" 
									+ "<a class='glyphicon glyphicon-pencil' title='Sửa' href='cart'>&nbsp;</a>"
									+ "<p class='product-name'>"
										+ "<a href='view?id="+value.id+"' title='"+value.name+"'>"+value.name+"</a>"
									+ "</p>"
								+ "</div>"
								+ "<div class='product-details-bottom'>"
									+ "<span class='price'>Giá: "+value.price+" VNĐ</span> <span class='title-desc'>Số lượng: </span>"
									+ "<strong>"+value.quantity+"</strong>"
								+ "</div>"
							+ "</div>"
						+ "</li>";
	        listItem += item;
	        });
	        var container = "<div class='top-cart-content arrow_box'>"
								 +"<div class='block-subtitle'>Những sản phẩm đã thêm gần đây</div>"
								 +"<ul id='cart-sidebar' class='mini-products-list'>"+ listItem +"</ul>"
								 	+"<div class='top-subtotal'>Tổng giá trị: <span class='price'>"+data.totalPay+" VNĐ</span></div>"
										+"<div class='actions'>"
											+"<button class='btn-checkout' type='button' onclick=\"window.location='/StoreDigital/cart'\">"
												+"<span>Thanh Toán</span>"
											+"</button>"
											+"<button class='view-cart' type='button' onclick=\"window.location='/StoreDigital/cart'\">"
												+"<span>Xem Thêm</span>"
											+"</button>"
										+"</div>"
									+"</div>"
						   	+"</div>";
	    	} else {
	    		window.location = "/StoreDigital/index";
	    	}
			$('#showCart').empty();					
			$('#showCart').append(container);
			if(type != "ADD") {
				$(element).parent().remove();
				notification(data.current.id, data.current.name, data.current.imageUrl, 'DEL_CART');
			} else {
				notification(data.current.id, data.current.name, data.current.urlImage, 'ADD_CART');
			}
	    },
	    error:function(data,status,er) { 
	        alert("error: "+data+" status: "+status+" er:"+er);
	    }
	});
}

function updateCart(id, element) {
	var el = $(element);
	$.ajax({ 
	    url: "/StoreDigital/updateCart", 
	    type: 'GET', 
	    dataType: 'json', 
	    data: {
	    	"id" : id,
	    	"quantity" : el.val()
	    }, 
	    contentType: 'application/json',
	    mimeType: 'application/json',
	    success: function(data) {
	    	notification(data.current.id, data.current.name, data.current.imageUrl, 'UPDATE_CART');
	    	if(data.cartSize != 0) {
		    	el.parent().next().find('.price').html(data.pay + ' VNĐ');
		    	$('#totalPay').html(data.totalPay + ' VNĐ');
		    	if(data.pay == 0) {
		    		el.parent().parent().remove();
		    		notification(data.current.id, data.current.name, data.current.imageUrl, 'DEL_CART');
		    	}
	    	} else {
	    		window.location = "/StoreDigital/index";
	    	}
	    },
	    error:function(data,status,er) { 
	        alert("error: "+data+" status: "+status+" er:"+er);
	    }
	});
}
