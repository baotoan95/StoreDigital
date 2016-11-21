<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">${requestScope.title }</h3>
		<br/>
		<span style="color: red;">${requestScope.message }</span>
	</div>
	<form:form method="POST" id="product" modelAttribute="product" 
	action="${pageContext.request.contextPath }/mngProducts/${requestScope.action }"
	enctype="multipart/form-data">
		<form:hidden path="id"/>
		<form:hidden path="importDate" class="form-controlb" id="importDate"/>
		
		<div class="box-body">
			<div class="form-group">
				<label for="name">Tên</label>
				<form:input path="name" class="form-control" id="name"/>
			</div>
			<div class="form-group">
				<label for="oldPrice">Giá cũ</label> 
				<form:input path="oldPrice" class="form-control" id="oldPrice"/>
			</div>
			<div class="form-group">
				<label for="newPrice">Giá mới</label> 
				<form:input path="newPrice" class="form-control" id="newPrice"/>
			</div>
			<div class="form-group">
				<label for="tags">Tags <span>(Cách nhau mới dấu ',')</span></label> 
				<form:input path="tags" class="form-control" id="tags"/>
			</div>
			<div class="form-group">
				<label for="postId">Post ID</label> 
				<form:select path="postId" cssClass="form-control">
					<c:forEach var="post" items="${posts }">
						<option value="${post.id }">${post.title }</option>
					</c:forEach>
				</form:select>
			</div>
			<div class="form-group">
				<label for="promotionId">Khuyến mãi</label> 
				<form:select path="promotionId" cssClass="form-control">
					<c:forEach var="prom" items="${promotions }">
						<option value="${prom.id }">${prom.name }</option>
					</c:forEach>
				</form:select>
			</div>
			<div class="form-group">
				<label for="cateId">Thể loại</label>
				<form:select class="form-control" id="cateId" path="cateId">
					<%= request.getAttribute("categories") %>
				</form:select>
			</div>
			<div class="form-group">
				<label for="description">Mô tả</label>
				<form:textarea path="description" id="description" class="form-control"/>
			</div>
			<div class="form-group">
				<label for="avatarUrl">Ảnh đại diện</label>
				<input type="file" name="avatarUrl" id="avatarUrl" class="form-control">
			</div>
		</div>
		<div class="box-footer">
			<input type="submit" value="Lưu và tiếp tục" class="btn btn-primary"/>
		</div>
	</form:form>
</div>
