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
	
	<form:form method="POST" modelAttribute="productDetail" action="${pageContext.request.contextPath }/mngProductDetails/${requestScope.action }">
		<form:hidden path="id"/>
		<div class="box-body">
			<div class="form-group">
				<label for="name">Tên</label> 
				<form:input path="name"  class="form-control" />
				<form:errors path="name"></form:errors>
			</div>
			<div class="form-group">
				<label for="groupId">Nhóm</label> 
				<form:select path="groupId" cssClass="form-control">
					<c:forEach var="group" items="${ requestScope.detailGroups}">
						<option ${productDetail.groupId == group.id ? "selected" : "" } value="${group.id }">${group.name }</option>
					</c:forEach>
				</form:select>
			</div>
		</div>
		<div class="box-footer">
			<button onclick="submit" class="btn btn-primary">
				${requestScope.action == "updateDetail" ? "Cập Nhật" : "Thêm" }
			</button>
		</div>
	</form:form>
	
</div>
