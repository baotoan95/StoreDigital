<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<h3 class="box-title">Danh sách quảng cáo</h3>
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
						<th>Tiêu Đề</th>
						<th>Hình Ảnh</th>
						<th>Trạng Thái</th>
						<th></th>
						<th></th>
					</tr>
					<c:forEach var="advert" items="${requestScope.adverts }">
					<tr>
						<td>${advert.id }</td>
						<td>${advert.name }</td>
						<td><img class="adminShowImage" src="<c:url value="/resources${advert.imageUrl }"/>"/></td>
						<td>${advert.status }</td>
						<td><a title="Sửa" href="${pageContext.request.contextPath }/mngAdvertiments/view/${advert.id}/"><i class="fa fa-edit"></i></a></td>
						<td><a title="Xóa" style="cursor: pointer;" onclick="delAdvert(${advert.id}, this);"><i class="fa fa-trash-o"></i></a></td>
					</tr>
					</c:forEach>
				</table>
			</div>
			<div class="box-footer clearfix">
				<button onclick="window.location='${pageContext.request.contextPath}/mngAdvertiments/addNew'" class="btn btn-primary">Thêm</button>
            	<ul class="pagination pagination-sm no-margin pull-right">
                 	<%= request.getAttribute("pagination") %>
              	</ul>
           	</div>
		</div>
	</div>
</div>