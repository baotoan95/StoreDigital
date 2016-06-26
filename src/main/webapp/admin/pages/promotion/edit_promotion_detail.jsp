<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">${requestScope.title }</h3>
		<br/>
		<span style="color: red;">${requestScope.message }</span>
	</div>
	<form:form method="GET" modelAttribute="promotionDetail" action="${pageContext.request.contextPath }/mngPromotions/${requestScope.action }">
		<form:hidden path="id"/>
		<div class="box-body">
			<div class="form-group">
				<label for="exampleInputEmail1">Chi tiết</label> 
				<form:input path="detail" class="form-control" id="exampleInputEmail1"/>
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">Mô tả</label> 
				<form:input path="describe" class="form-control" id="exampleInputPassword1"/>
			</div>
			<div class="form-group">
            	<label>Đợt khuyến mãi</label>
               	<form:select path="promotionId" class="form-control">
               		<c:forEach var="pro" items="${requestScope.promotions }">
	                	<option selected="${pro.id == requestScope.promotionDetail.promotionId ? true : false }" >${pro.id }</option>
                	</c:forEach>
            	</form:select>
           	</div>
		</div>
		<div class="box-footer">
			<button type="submit" class="btn btn-primary">
				<%
					String action = (String)request.getAttribute("action");
					if(action.equalsIgnoreCase("addPromotionDetail")) {
						out.print("Thêm");
					} else {
						out.print("Cập Nhật");
					}
				%>
			</button>
		</div>
	</form:form>
</div>
