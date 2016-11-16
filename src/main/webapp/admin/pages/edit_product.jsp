<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">${requestScope.title }</h3>
		<br/>
		<span style="color: red;">${requestScope.message }</span>
	</div>
	<form:form method="POST" id="product" modelAttribute="product" action="${pageContext.request.contextPath }/mngAdvertiments/${requestScope.action }"
	enctype="multipart/form-data">
		<form:hidden path="id"/>
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
				<label for="postId">Post ID</label> 
				<form:input path="postId" class="form-control" id="postId"/>
			</div>
			<div class="form-group">
				<label for="promotionId">Khuyến mãi</label> 
				<form:input path="promotionId" class="form-control" id="promotionId"/>
			</div>
			<div class="form-group">
				<label for="importDate">Ngày nhập</label> 
				<form:input path="importDate" class="form-control" id="importDate"/>
			</div>
			<div class="form-group">
				<label for="cateId">Thể loại</label> 
				<form:input path="cateId" class="form-control" id="cateId"/>
			</div>
			<div class="form-group">
				<label for="describe">Mô tả</label>
				<form:textarea path="describe" id="describe" class="form-control"/>
			</div>
		</div>
		<div class="box-footer">
			<button onclick="actionForm();" class="btn btn-primary">
				Tiếp
			</button>
		</div>
	</form:form>
</div>
