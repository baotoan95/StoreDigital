<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">${requestScope.title }</h3>
		<br/>
		<span style="color: red;">${requestScope.message }</span>
	</div>
	
	<form:form method="POST" id="promotion" modelAttribute="promotion" action="${pageContext.request.contextPath }/mngPromotions/addPromotion" enctype="multipart/form-data">
		<div class="box-body">
			<div class="form-group">
				<label for="exampleform:inputEmail1">Ngày bắt đầu</label> 
				<form:input path="start" class="form-control datetimepicker_dark" />
			</div>
			<div class="form-group">
				<label for="exampleform:inputPassword1">Ngày kết thúc</label> 
				<form:input path="end" class="form-control datetimepicker_dark" />
			</div>
			<div class="form-group">
				<label for="exampleform:inputPassword1">Tên</label> 
				<form:input path="name" class="form-control"/>
			</div>
			<div class="form-group">
				<label for="exampleform:inputPassword1">Hình ảnh</label>
				<input id="file" name="file" type="file"/><br/>
				<input type="text" id="imageName" name="imageName" class="form-control" placeholder="Nhập tên ảnh"/>
			</div>
		</div>
		<div class="box-footer">
			<script type="text/javascript">
				function actionForm() {
 					var fileName = $('#file').val();
 					var name = $('#imageName').val();
 					if(name.trim().length > 0) {
 						var extra = fileName.substring(fileName.length - 4, fileName.length);
	 					$('#imageName').val(name + extra);
 					} else {
 						$('#imageName').val(fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.length));
 					}
 					$('#promotion').submit();
 				}
 			</script>
			<button onclick="actionForm();" class="btn btn-primary">Thêm</button>
		</div>
	</form:form>
	
</div>
