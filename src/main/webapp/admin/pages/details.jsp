<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<h3 class="box-title">Danh sách thuộc tính chi tiết sản phẩm</h3>
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
						<th>ID</th>
						<th>Tên chi tiết</th>
						<th>Group ID</th>
						<th></th>
					</tr>
					<c:forEach var="detail" items="${requestScope.details }">
					<tr>
						<td>${detail.id }</td>
						<td>${detail.name }</td>
						<td>${detail.groupId }</td>
						<td><a title="Sửa" href="${pageContext.request.contextPath }/mngProductDetails/updateDetail/${detail.id}"><i class="fa fa-edit"></i></a></td>
						<td><a title="Xóa" href="#"><i class="fa fa-trash-o"></i></a></td>
					</tr>
					</c:forEach>
				</table>
			</div>
			<div class="box-footer clearfix">
				<a href="${pageContext.request.contextPath }/mngProductDetails/addDetail" class="btn btn-primary">Thêm</a>
           	</div>
		</div>
	</div>
	
</div>