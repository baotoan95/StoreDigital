<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<h3 class="box-title">Danh sách chi tiết đơn hàng: ${requestScope.order.id }</h3>
				<div class="box-tools">
					<div class="input-group" style="width: 150px;">
						<input type="text" name="table_search"
							class="form-control input-sm pull-right" placeholder="Search" />
						<div class="input-group-btn">
							<button class="btn btn-sm btn-default">
								<i class="fa fa-search"></i>
							</button>
						</div>
					</div>
				</div>
			</div>
			<div class="box-body table-responsive no-padding">
				<table class="table table-hover">
					<tr>
						<th></th>
						<th>Mã</th>
						<th>Tên</th>
						<th>Giá</th>
						<th>Số lượng</th>
						<th>Thành tiền</th>
						<th></th>
					</tr>
					<c:forEach var="detail" items="${requestScope.listDetailOrder }">
					<tr>
						<td><img class="adminShowImage" src="<c:url value="/resources/${detail.imageUrl }"/>"/></td>
						<td>${detail.id }</td>
						<td>${detail.name }</td>
						<td>${detail.price }</td>
						<td><input type="text" value="${detail.quantity }" onblur="updateDetailOrder(${detail.id}, this.value, this);" size="4"></td>
						<td>${detail.totalPay }</td>
						<td><a title="Xóa" style="cursor: pointer;" onclick="updateDetailOrder(${detail.id}, 0, this);"><i class="fa fa-trash-o"></i></a></td>
					</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</div>