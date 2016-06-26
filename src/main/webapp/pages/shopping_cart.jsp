<%@page import="com.baotoan.spring.dao.ImageDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<section class="main-container col1-layout">
	<div class="main container">
		<div class="col-main">
			<div class="cart wow">
				<div class="page-title">
					<h2>Giỏ hàng của bạn</h2>
				</div>
				<div class="table-responsive">
					<fieldset>
						<table class="data-table cart-table" id="shopping-cart-table">
							<colgroup>
								<col width="1">
								<col>
								<col width="1">
								<col width="1">
								<col width="1">
								<col width="1">
								<col width="1">
							</colgroup>
							<thead>
								<tr class="first last">
									<th rowspan="1">&nbsp;</th>
									<th rowspan="1"><span class="nobr">Tên Sản Phẩm</span></th>
									<th colspan="2">Giá</th>
									<th colspan="1" class="a-center"><span class="nobr">Số
											Lượng</span></th>
									<th class="a-center" rowspan="1">Thành Tiền</th>
									<th class="a-center" rowspan="1">&nbsp;</th>
								</tr>
							</thead>
							<tfoot>
								<tr class="first last">
									<td class="a-right last" colspan="50"><a href="index"><button
												class="button btn-continue" title="Continue Shopping"
												type="button">
												<span><span>Quay Lại Cửa Hàng</span></span>
											</button></a>
										<button id="empty_cart_button" class="button btn-empty"
											title="Clear Cart" value="empty_cart"
											name="update_cart_action" onclick="cart(-1, 'DEL')">
											<span><span>Hủy</span></span>
										</button></td>
								</tr>
							</tfoot>
							<tbody>
								<c:forEach var="item"
									items="${sessionScope.cartInfor.listCart }">
									<tr class="first odd">
										<td class="image"><a class="product-image"
											title="Sample Product" href="view?id=${item.id }"><img
												width="75" alt="Sample Product"
												src="${pageContext.request.contextPath }/resources${item.imageUrl}"></a></td>
										<td><h2 class="product-name">
												<a href="view?id=${item.id }">${item.name }</a>
											</h2></td>
										<td class="a-right"><span class="cart-price"> <span
												class="price">${item.price } VNĐ</span>
										</span></td>
										<td class="a-center movewishlist"><input maxlength="12"
											class="input-text qty" title="Số lượng" size="4"
											onblur="updateCart(${item.id}, this)"
											value="${item.quantity }" name="cart[15945][qty]"></td>
										<td class="a-right movewishlist"><span class="cart-price">
												<span class="price">${item.totalPay } VNĐ</span>
										</span></td>
										<td class="a-center last"
											onclick="cart(${item.id}, 'DEL', this)"><a
											class="button remove-item" title="Xóa" href="#"><span><span>Xóa</span></span></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</fieldset>
				</div>
				<!-- BEGIN CART COLLATERALS -->
				<form:form modelAttribute="order" id="order" action="order"
					method="POST">
					<div class="cart-collaterals row">
						<div class="col-sm-4">
							<div class="shipping">
								<h3>Thông Tin Phiếu Hàng</h3>
								<div class="shipping-form">
									<form id="shipping-zip-form" method="post"
										action="#estimatePost/">
										<p>Vui lòng nhập đầy đủ thông tin của bạn, chúng tôi sẽ
											liên lạc lại với bạn.</p>
										<ul class="form-list">
											<li><label class="required" for="postcode"><em>*</em>Tên</label>
												<div class="input-box">
													<form:input path="name" type="text" id="postcode"
														class="input-text validate-postcode" />
													<form:errors class="error" path="name" />
												</div></li>
											<li><label class="required" for="postcode"><em>*</em>Địa
													chỉ nhận hàng</label>
												<div class="input-box">
													<form:input path="address" type="text" id="postcode"
														class="input-text validate-postcode" />
													<form:errors class="error" path="address" />
												</div></li>
											<li><label class="required" for="postcode"><em>*</em>Số
													điện thoại di động</label>
												<div class="input-box">
													<form:input path="tel" type="text" id="postcode"
														class="input-text validate-postcode" />
													<form:errors class="error" path="tel" />
												</div></li>
											<li><label class="required" for="postcode"><em>*</em>Ngày
													nhận hàng</label>
												<div class="input-box">
													<form:input path="deliverDate" id="datetimepicker_dark"
														type="text" class="input-text validate-postcode" />
													<form:errors class="error" path="deliverDate" />
													<script type="text/javascript"
														src="<c:url value="/resources/js/jquery.js"/>"></script>
													<script type="text/javascript"
														src="<c:url value="/resources/js/jquery.datetimepicker.js"/>"></script>
													<script>
														$('#datetimepicker_dark').datetimepicker({theme:'dark'});
													</script>
												</div></li>
											<li><label for="region_id">Hình thức thanh toán</label>
												<div class="input-box">
													<form:select path="paymentId"
														title="Chọn hình thức thanh toán" id="region_id"
														class="required-entry validate-select">
														<c:forEach var="pm" items="${payments}">
															<form:option value="${pm.id }">${pm.name }</form:option>
														</c:forEach>
													</form:select>
													<input type="text" style="display: none;"
														class="input-text required-entry" title="State/Province"
														value="" name="region" id="region">
													<form:errors class="error" path="paymentId" />
												</div></li>
										</ul>
										<div class="buttons-set11">
											<button class="button get-quote" title="Get a Quote"
												type="button">
												<span>Hướng Dẫn</span>
											</button>
										</div>
									</form>
								</div>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="discount">
								<h3>Mã Giảm Giá</h3>
								<form method="post" action="#couponPost/"
									id="discount-coupon-form">
									<label for="coupon_code">Nếu bạn có mã giảm giá, vui
										lòng nhập vào:</label>
									<form:input path="sellCoupon" type="text" name="coupon_code"
										id="coupon_code" class="input-text fullwidth" />
									<form:errors class="error" path="sellCoupon" />
								</form>
							</div>
						</div>
						<div class="totals col-sm-4">
							<h3>Giá Trị Giỏ Hàng</h3>
							<div class="inner">
								<table class="table shopping-cart-table-total"
									id="shopping-cart-totals-table">
									<colgroup>
										<col>
										<col width="1">
									</colgroup>
									<tfoot>
										<tr>
											<td colspan="1" class="a-left" style=""><strong>Tổng
													tiền phải trả</strong></td>
											<td class="a-right" style=""><strong><span
													class="price" id='totalPay'>${sessionScope.cartInfor.totalPay }
														VNĐ</span></strong></td>
										</tr>
									</tfoot>
								</table>
								<ul class="checkout">
									<li>
										<button class="button btn-proceed-checkout"
											title="Proceed to Checkout" type="submit">
											<span>Gửi Đơn Hàng</span>
										</button> <span style="color: red;">${message }</span>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</form:form>
			</div>
			<div class="crosssel">
				<div class="new_title center">
					<h2>Bạn có thể quan tâm:</h2>
				</div>
				<div class="category-products">
					<ul id="crosssell-products-list" class="products-grid first odd">
						<c:forEach var="pro" items="${requestScope.upsells }">
							<li class="item col-md-3 col-sm-6 col-xs-6">
								<div class="col-item">
									<div class="sale-label sale-top-right">Sale</div>
									<div class="images-container">
										<a class="product-image" title="${pro.name }"
											href="view?id=${pro.id }"> <img alt="Avatar"
											class="img-responsive"
											src="${pageContext.request.contextPath }/resources${pro.urlImage}"></a>
										<div class="actions">
											<div class="actions-inner">
												<button type="button" onclick="cart(${pro.id}, 'ADD', this)"
													title="Thêm vào giỏ hàng" class="button btn-cart">
													<span>Thêm Vào</span>
												</button>
												<ul class="add-to-links">
													<li><a style="cursor: pointer;"
														onclick="like(${pro.id})"
														title="Thêm vào danh sách ưa thích" class="link-wishlist"><span>Thích</span></a></li>
													<li><a style="cursor: pointer;"
														onclick="addCompare(${pro.id})"
														title="Thêm vào danh sách so sánh" class="link-compare "><span>So
																sánh</span></a></li>
												</ul>
											</div>
										</div>
										<div class="qv-button-container">
											<a class="qv-e-button btn-quickview-1"><span><span>Xem
														Nhanh</span></span></a>
										</div>
									</div>
									<div class="info">
										<div class="info-inner">
											<div class="item-title">
												<a title="${pro.name }" href="view?id=${pro.id }">${pro.name }</a>
											</div>
											<div class="item-content">
												<div class="ratings">
													<div class="rating-box">
														<div class="rating" style="width: 50%"></div>
													</div>
												</div>
												<div class="price-box">
													<p class="special-price">
														<span class="price"> ${pro.newPrice } VNĐ </span>
													</p>
													<p class="old-price">
														<span class="price-sep">-</span> <span class="price">
															${pro.oldPrice } VNĐ </span>
													</p>
												</div>
											</div>
										</div>
										<div class="clearfix"></div>
									</div>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
</section>
