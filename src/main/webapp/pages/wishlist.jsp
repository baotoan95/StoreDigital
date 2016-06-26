<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="main-container col2-right-layout">
	<div class="main container">
		<div class="row">
			<section class="col-main col-sm-9 wow">
				<div class="my-account">
					<div class="page-title">
						<h2>Danh sách ưu thích của tôi</h2>
					</div>
					<div class="my-wishlist">
						<div class="table-responsive">
							<form method="post"
								action="#/wishlist/index/update/wishlist_id/1/"
								id="wishlist-view-form">
								<fieldset>
									<input type="hidden" value="ROBdJO5tIbODPZHZ" name="form_key">
									<table id="wishlist-table"
										class="clean-table linearize-table data-table">
										<thead>
											<tr class="first last">
												<th class="customer-wishlist-item-image"></th>
												<th class="customer-wishlist-item-info"></th>
												<th class="customer-wishlist-item-price">Price</th>
												<th class="customer-wishlist-item-cart"></th>
												<th class="customer-wishlist-item-remove"></th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="product" items="${requestScope.listWish }">
												<tr id="item_31" class="first odd">
													<td class="wishlist-cell0 customer-wishlist-item-image">
														<a title="Softwear Women's Designer" href="#/"
														class="product-image"> <img width="150"
															alt="Softwear Women's Designer"
															src="${pageContext.request.contextPath }/resources${product.urlImage}">
													</a>
													</td>
													<td class="wishlist-cell1 customer-wishlist-item-info"><h3
															class="product-name">
															<a title="${product.name }" href="view?id=${product.id }">${product.name }</a>
														</h3>
														<div class="description std">
															<div class="inner">Sản phẩm ưa thích của bạn</div>
														</div>
													<td data-rwd-label="Price"
														class="wishlist-cell3 customer-wishlist-item-price"><div
															class="cart-cell">
															<div class="price-box">
																<span id="product-price-39" class="regular-price">
																	<span class="price">${product.newPrice } VNĐ</span>
																</span>
															</div>
														</div></td>
													<td class="wishlist-cell4 customer-wishlist-item-cart"><div
															class="cart-cell">
															<button class="button btn-cart"
																onclick="cart(${product.id}, 'ADD', this)"
																title="Thêm vào giỏ hàng" type="button">
																<span><span>Thêm vào giỏ</span></span>
															</button>
														</div>
													<td
														class="wishlist-cell5 customer-wishlist-item-remove last"><a
														class="remove-item" title="Xóa khỏi danh sách"
														onclick="disLike(this, ${product.id})"><span><span></span></span></a></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</fieldset>
							</form>
						</div>
					</div>
					<div class="buttons-set">
						<p class="back-link">
							<a href="index"><small></small>Trở lại cửa hàng</a>
						</p>
					</div>
				</div>
			</section>
			<aside class="col-right sidebar col-sm-3 wow">
				<div class="block block-compare">
					<div class="block-title ">
						<span>Thông tin</span>
					</div>
					<div class="block-content">
						<p>
							Đây là danh sách giúp bạn ghi nhớ những sản phẩm mà mình
							quan tâm, nếu bạn có một tài khoản, danh sách này sẽ được lưu
							trực tiếp vào tài khoản của bạn.
						</p>
					</div>
				</div>
			</aside>
		</div>
	</div>
</div>