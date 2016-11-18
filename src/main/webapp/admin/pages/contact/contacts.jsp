<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<h3 class="box-title">Danh sách phản hồi</h3>
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
						<th>Tên</th>
						<th>Điện Thoại</th>
						<th>Email</th>
						<th>Nội dung</th>
						<th></th>
						<th></th>
					</tr>
					<c:forEach var="contact" items="${requestScope.contacts }">
					<tr>
						<td>${contact.name }</td>
						<td>${contact.tel }</td>
						<td>${contact.mail }</td>
						<td>${contact.content }</td>
						<td><a title="Sửa" href="${pageContext.request.contextPath }/mngContacts/view/${contact.id}"><i class="fa fa-edit"></i></a></td>
						<td><a title="Xóa" href="#"><i class="fa fa-trash-o"></i></a></td>
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