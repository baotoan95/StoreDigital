<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="box box-primary">
	<div class="box-header">
		<i class="ion ion-clipboard"></i>
		<h3 class="box-title">${requestScope.titlePage }</h3>
		<br/>
		<c:if test="${empty requestScope.orders }">Không có đơn hàng nào</c:if>
	</div>
	<!-- /.box-header -->
	<div class="box-body">
		<ul class="todo-list">
			<c:forEach var="order" items="${requestScope.orders }">
				<li>
					<span class="text">${order.name } </span> 
					<small class="label label-info" title="Ngày đặt">
						<i class="fa fa-clock-o"></i>Ngày đặt: ${order.orderDate } 
					</small>
					<small class="label label-warning" title="Ngày giao">
						<i class="fa fa-clock-o"></i>Ngày giao: ${order.deliverDate } 
					</small>
					<small class="label label-danger" title="Tổng tiền">
						Tổng tiền: ${order.totalPay } VNĐ
					</small>
					<div class="tools">
						<a href="${pageContext.request.contextPath }/mngOrders/view/${order.id }/"><i class="fa fa-edit" title="Xem"></i></a>
						<a><i class="fa fa-trash-o" style="cursor: pointer;" onclick="delOrder('${order.id}', this);" title="Xóa"></i></a>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
	<!-- /.box-body -->
	<div class="box-footer clearfix no-border">
		<ul class="pagination pagination-sm inline">
			<%= request.getAttribute("pagination") %>
		</ul>
	</div>
</div>
<!-- /.box -->