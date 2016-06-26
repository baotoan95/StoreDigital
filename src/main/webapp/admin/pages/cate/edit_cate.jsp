<%@page import="com.baotoan.spring.entitys.MenuCate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">${requestScope.title }</h3>
	</div>
	<span style="color: red;"> ${requestScope.message }</span>
	<form:form method="${requestScope.action == 'addCate' ? 'POST' : 'GET' }" enctype="multipart/form-data"
	modelAttribute="cate" action="${pageContext.request.contextPath }/mngCates/${requestScope.action }">
		<form:hidden path="id"/>
		<div class="box-body">
			<div class="form-group">
				<label for="exampleInputEmail1">Tên thể loại</label> 
				<form:input path="name" class="form-control" id="exampleInputEmail1" placeholder="Tên thể loại"/>
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">ParentID</label>
				<form:select path="parentId" class="form-control">
					<option ${0 == requestScope.cate.parentId ? 'selected' : '' }>0</option>
					<c:forEach var="parent" items="${requestScope.listParent }">
					<option ${parent == requestScope.cate.parentId ? 'selected' : '' }>${parent }</option>
					</c:forEach>
				</form:select>
			</div>
			<%
				MenuCate menu = (MenuCate)request.getAttribute("cate");
				if(menu.getParentId() == 0) {
			%>
			<div class="form-group">
				<label for="exampleform:inputPassword1">Hình ảnh</label>
				<input id="file" name="file" type="file"/><span>Cũ: ${requestScope.cate.imageUrl }</span> <br/>
				<form:hidden path="imageUrl"/>
				<input type="text" id="imageName" name="imageName" class="form-control" placeholder="Nhập tên ảnh"/>
				<p class="help-block">Chỉ được áp dụng cho menu có parentId = 0</p>
			</div>
			<script type="text/javascript">
				function actionForm() {
 					var fileName = $('#file').val();
 					if(fileName.trim().length > 0) {
	 					var name = $('#imageName').val();
	 					if(name.trim().length > 0) {
	 						var extra = fileName.substring(fileName.length - 4, fileName.length);
		 					$('#imageName').val(name + extra);
	 					} else {
	 						$('#imageName').val(fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.length));
	 					}
 					}
 					$('#promotion').submit();
 				}
 			</script>
			<%
				}
			%>
		</div>
		<div class="box-footer">
			<button onclick="actionForm();" type="submit" class="btn btn-primary">
				${requestScope.action == "addCate" ? "Thêm" : "Cập Nhật" }
			</button>
		</div>
	</form:form>
</div>
