<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<h3 class="box-title">Danh sách người dùng</h3>
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
						<th>Email</th>
						<th>Tên</th>
						<th>Thành Phố</th>
						<th>Điện Thoại</th>
						<th>Điểm</th>
						<th></th>
						<th></th>
					</tr>
					<c:forEach var="user" items="${requestScope.users }">
					<tr>
						<td>${user.mail }</td>
						<td>${user.name }</td>
						<td>${user.city }</td>
						<td>${user.tel }</td>
						<td>${user.score }</td>
						<td><a title="Sửa" href="${pageContext.request.contextPath }/mngMembers/view/${user.mail}/"><i class="fa fa-edit"></i></a></td>
						<td><a title="Xóa" style="cursor: pointer;" onclick="delUser('${user.mail}', this);"><i class="fa fa-trash-o"></i></a></td>
					</tr>
					</c:forEach>
				</table>
			</div>
			<div class="box-footer clearfix">
				<button onclick="window.location='${pageContext.request.contextPath}/mngMembers/addNew'" class="btn btn-primary">Thêm</button>
            	<ul class="pagination pagination-sm no-margin pull-right">
                 	<%= request.getAttribute("pagination") %>
              	</ul>
           	</div>
		</div>
	</div>
</div>