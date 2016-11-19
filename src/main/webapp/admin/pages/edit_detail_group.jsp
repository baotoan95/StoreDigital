<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">${requestScope.title }</h3>
		<br/>
		<span style="color: red;">${requestScope.message }</span>
	</div>
	
	<form:form method="POST" modelAttribute="detailGroup" action="${pageContext.request.contextPath }/mngProductDetails/${requestScope.action }">
		<form:hidden path="id"/>
		<div class="box-body">
			<div class="form-group">
				<label for="name">Tên</label> 
				<form:input path="name"  class="form-control" />
				<form:errors path="name"></form:errors>
			</div>
		</div>
		<div class="box-footer">
			<button onclick="submit" class="btn btn-primary">
				${requestScope.action == "updateGroupDetail" ? "Cập Nhật" : "Thêm" }
			</button>
		</div>
	</form:form>
	
</div>
