<%@page import="com.baotoan.spring.entities.DetailProduct"%>
<%@page import="java.util.List"%>
<%@page import="com.baotoan.spring.dto.ProductDetailFormDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<% int count = 0; %>
<form:form modelAttribute="productDetailForm" action="${pageContext.request.contextPath }/mngProducts/${requestScope.action }" method="post">
	<form:hidden path="productId"/>
	<c:forEach var="group" items="${requestScope.details }">
		<div class="box">
			<div class="box-header with-border">
				<h3 class="box-title">${group.key.name }</h3>
				<div class="box-tools pull-right">
					<button type="button" class="btn btn-box-tool"
						data-widget="collapse" data-toggle="tooltip" title=""
						data-original-title="Collapse">
						<i class="fa fa-minus"></i>
					</button>
					<button type="button" class="btn btn-box-tool" data-widget="remove"
						data-toggle="tooltip" title="" data-original-title="Remove">
						<i class="fa fa-times"></i>
					</button>
				</div>
			</div>
			
			<div class="box-body" style="display: block;">
				<c:forEach var="detail" items="${group.value }">
					<div class="form-group">
						<label for="name">${detail.name }</label>
						<input type="hidden" name="detailsProduct[<%= count %>].id" value="${detail.id }"/>
						<input type="hidden" name="detailsProduct[<%= count %>].name" value="${detail.name }">
						<input type="hidden" name="detailsProduct[<%= count %>].productId" value="${productDetailForm.productId}">
						<input class="form-control" name="detailsProduct[<%= count %>].value" value="${productDetailForm.getValue(detail.name, group.key.id) }">
						<input type="hidden" name="detailsProduct[<%= count %>].group" value="${group.key.id }">
						<% count++; %>
					</div>
				</c:forEach>
			</div>
			<!-- /.box-body -->
		</div>
	</c:forEach>
	
	<div class="box-footer">
		<input type="submit" value="Lưu" class="btn btn-primary"/>
	</div>
</form:form>