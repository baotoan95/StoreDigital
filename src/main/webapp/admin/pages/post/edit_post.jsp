<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">${requestScope.title }</h3>
		<br/>
		<span style="color: red;">${requestScope.message }</span>
	</div>
	<form:form method="POST" modelAttribute="post" action="${pageContext.request.contextPath }/mngPosts/${requestScope.action }">
	<c:if test="${requestScope.action != 'addPost'}">
		<form:hidden path="id"/>
	</c:if>
		<div class="box-body">
			<div class="form-group">
				<label for="exampleInputEmail1">Tiêu đề</label> 
				<form:input path="title" class="form-control"/>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Tác giả</label> 
				<form:input path="author" class="form-control"/>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Nội dung</label> 
				<form:textarea cols="20" rows="80" path="content" class="form-control ckeditor"/>
			</div>
		</div>
		<div class="box-footer">
			<button type="submit" class="btn btn-primary">
				${requestScope.action == "addPost" ? "Đăng Bài" : "Cập Nhật"} 
			</button>
		</div>
	</form:form>
</div>
