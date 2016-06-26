<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">Mã đơn hàng: ${requestScope.order.id }</h3>
		<br/>
		<span style="color: red;">${requestScope.message }</span>
	</div>
	
	<form:form method="POST" modelAttribute="order" action="${pageContext.request.contextPath }/mngOrders/updateOrder">
		<form:hidden path="id"/>
		<div class="box-body">
			<div class="form-group">
				<label for="exampleform:inputEmail1">Tên khách hàng</label>
				<form:input path="name" class="form-control" />
			</div>
			<div class="form-group">
				<label for="exampleform:inputEmail1">Địa chỉ nhận hàng</label> 
				<form:input path="address" class="form-control" />
			</div>
			<div class="form-group">
				<label for="exampleform:inputEmail1">Số điện thoại</label> 
				<form:input path="tel" class="form-control" />
			</div>
			<div class="form-group">
				<label for="exampleform:inputEmail1">Ngày đặt hàng</label> 
				<form:input path="orderDate" class="form-control  datetimepicker_dark" />
			</div>
			<div class="form-group">
				<label for="exampleform:inputEmail1">Ngày giao hàng</label> 
				<form:input path="deliverDate" class="form-control  datetimepicker_dark" />
			</div>
			<div class="form-group">
				<label for="exampleform:inputEmail1">Hình thức thanh toán</label> 
				<form:select path="paymentId" class="form-control select2 select2-hidden-accessible">
					<c:forEach var="payment" items="${requestScope.payments }">
						<option ${payment.id == requestScope.order.paymentId ? 'selected' : '' } value="${payment.id }">${payment.name }</option>
					</c:forEach>
				</form:select>
			</div>
			<div class="form-group">
				<label for="exampleform:inputEmail1">Tổng giá trị đơn hàng</label> 
				<form:input path="totalPay" class="form-control" />
			</div>
			<div class="form-group">
				<label for="exampleform:inputEmail1">Tài khoản người dùng</label> 
				<form:input path="user" class="form-control" />
			</div>
			<div class="form-group">
				<label for="exampleform:inputEmail1">Mã khuyến mãi</label> 
				<form:input path="sellCoupon" class="form-control" />
			</div>
			<div class="form-group">
				<label for="exampleform:inputEmail1">Trạng thái</label> 
				<form:select path="status" class="form-control select2 select2-hidden-accessible">
					<c:forEach var="status" items="${requestScope.listStatus }">
						<option ${status.id == requestScope.order.status ? 'selected' : '' } value="${status.id }">${status.name }</option>
					</c:forEach>
				</form:select>
			</div>
			<div class="form-group">
				<a href="${pageContext.request.contextPath }/mngOrders/viewLsDetail/${requestScope.order.id }/" class="btn btn-primary"> Danh sách sản phẩm đã đặt </a>
			</div>
		</div>
		<div class="box-footer">
			<button type="submit" class="btn btn-primary"> Cập Nhật </button>
		</div>
	</form:form>
	
</div>
