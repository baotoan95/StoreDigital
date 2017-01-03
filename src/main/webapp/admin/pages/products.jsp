<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<h3 class="box-title">Danh sách sản phẩm</h3>
				<div class="box-tools">
					<div class="input-group" style="width: 150px;">
<!-- 						<input type="text" name="table_search" -->
<!-- 							class="form-control input-sm pull-right" placeholder="Search" /> -->
						<div class="input-group-btn">
							<a class="btn btn-sm btn-default" href="${pageContext.request.contextPath }/mngProducts/productOutOfStockReport">
								Sản phẩm sắp hết hàng
							</a>
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
						<th>Giá Cũ</th>
						<th>Giá Mới</th>
						<th>Ngày cập nhật</th>
						<th></th>
						<th></th>
					</tr>
					<c:forEach var="product" items="${products }">
					<tr>
						<td><img class="adminShowImage" src="<c:url value="/resources/${product.urlImage }"/>"/></td>
						<td>${product.id }</td>
						<td>${product.name }</td>
						<td>${product.oldPrice }</td>
						<td>${product.newPrice }</td>
						<td>${product.importDate }</td>
						<td><a title="Sửa" href="${pageContext.request.contextPath }/mngProducts/edit/${product.id}"><i class="fa fa-edit"></i></a></td>
						<td><a title="Xóa" href="${pageContext.request.contextPath }/mngProducts/delete/${product.id}"><i class="fa fa-trash-o"></i></a></td>
					</tr>
					</c:forEach>
				</table>
				
			</div>
			<div class="box-footer clearfix">
            	<ul class="pagination pagination-sm no-margin pull-right">
                 	<%= request.getAttribute("pagination") %>
              	</ul>
           	</div>
		</div>
	</div>
</div>