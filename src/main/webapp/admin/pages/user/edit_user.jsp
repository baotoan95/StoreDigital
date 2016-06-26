<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">${requestScope.title }</h3>
		<br/>
		<span style="color: red;">${requestScope.message }</span>
	</div>
	
	<form:form method="POST" modelAttribute="user" action="${pageContext.request.contextPath }/mngMembers/${requestScope.action }">
		<div class="box-body">
			<div class="form-group">
				<label for="exampleform:inputEmail1">Mail</label> 
				<form:input path="mail" disabled="${requestScope.action == 'updateUser' ? 'true' : 'false' }" class="form-control" />
				<c:if test="${requestScope.action == 'updateUser' }">
					<form:hidden path="mail"/>
				</c:if>
				<form:errors path="mail"></form:errors>
			</div>
			<div class="form-group">
				<label for="exampleform:inputEmail1">Pass</label> 
				<form:input path="pass"  class="form-control" />
				<form:errors path="pass"></form:errors>
			</div>
			<div class="form-group">
				<label for="exampleform:inputEmail1">Chứng minh thư</label> 
				<form:input path="id"  class="form-control" />
				<form:errors path="id"></form:errors>
			</div>
			<div class="form-group">
				<label for="exampleform:inputEmail1">Tên</label> 
				<form:input path="name"  class="form-control" />
				<form:errors path="name"></form:errors>
			</div>
			<div class="form-group">
				<label for="exampleform:inputEmail1">Địa chỉ</label> 
				<form:input path="address"  class="form-control" />
				<form:errors path="address"></form:errors>
			</div>
			<div class="form-group">
				<label for="exampleform:inputEmail1">Số điện thoại</label> 
				<form:input path="tel"  class="form-control" />
				<form:errors path="tel"></form:errors>
			</div>
			<div class="form-group">
				<label for="exampleform:inputEmail1">Điểm</label> 
				<form:input path="score"  class="form-control" />
				<form:errors path="score"></form:errors>
			</div>
			<div class="form-group">
				<label for="exampleform:inputEmail1">Trạng thái</label> 
				<form:select path="status"  class="form-control">
					<option ${"actived" == requestScope.user.status ? 'selected' : '' } value="actived">Đã kích hoạt</option>
					<option ${"actived" != requestScope.user.status ? 'selected' : '' } value="not">Chưa kích hoạt</option>
				</form:select>
				<form:errors path="status"></form:errors>
			</div>
			<div class="form-group">
				<label for="exampleform:inputEmail1">Vai trò</label> 
				<form:select path="role"  class="form-control">
					<c:forEach var="role" items="${requestScope.roles }">
					<option ${role.id == requestScope.user.role ? 'selected' : '' } value="${role.id }">${role.name }</option>
					</c:forEach>
				</form:select>
				<form:errors path="role"></form:errors>
			</div>
		</div>
		<div class="box-footer">
			<button onclick="submit" class="btn btn-primary">
				${requestScope.action == "updateUser" ? "Cập Nhật" : "Thêm" }
			</button>
		</div>
	</form:form>
	
</div>
