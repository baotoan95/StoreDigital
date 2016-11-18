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
					<h2>Chi tiết đơn hàng</h2>
					<span class="error">${requestScope.message }</span>
				</div>
				<div class="table-responsive">
					<input type="hidden" value="Vwww7itR3zQFe86m" name="form_key">
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
											name="update_cart_action"
											onclick="window.location='${pageContext.request.contextPath }/delOrder?orId=${requestScope.orderId}'">
											<span><span>Hủy</span></span>
										</button></td>
								</tr>
							</tfoot>
							<tbody>
								<c:forEach var="item" items="${requestScope.listDetailOrder }">
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
											onblur="updateOrder(${item.id}, this.value, this);"
											value="${item.quantity }" name="quantity"></td>
										<td class="a-right movewishlist"><span class="cart-price">
												<span class="price">${item.totalPay } VNĐ</span>
										</span></td>
										<td class="a-center last"
											onclick="updateOrder(${item.id}, 0, this);"><a
											class="button remove-item" title="Xóa" href="#"><span><span>Xóa</span></span></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</fieldset>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- Footer -->
