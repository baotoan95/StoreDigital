<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">${requestScope.title }</h3>
		<br/>
		<span style="color: red;">${requestScope.message }</span>
	</div>
	<form:form method="POST" id="advert" modelAttribute="advert" action="${pageContext.request.contextPath }/mngAdvertiments/${requestScope.action }"
	enctype="multipart/form-data">
		<form:hidden path="id"/>
		<div class="box-body">
			<div class="form-group">
				<label for="exampleInputEmail1">Tên</label> 
				<form:input path="name" class="form-control" id="exampleInputEmail1"/>
			</div>
			<div class="form-group">
				<label for="exampleform:inputPassword1">Hình ảnh</label>
				<input id="file" name="file" type="file"/><br/>
				<span>Cũ: ${requestScope.advert.imageUrl }</span>
				<form:hidden path="imageUrl"/>
				<input type="text" id="imageName" name="imageName" class="form-control" placeholder="Nhập tên ảnh"/>
			</div>
			<div class="form-group">
            	<label>Trạng thái</label>
               	<form:select path="status" class="form-control">
	               	<option ${0 == requestScope.advert.status ? 'selected' : '' } value="0">Không hoạt động</option>
	               	<option ${1 == requestScope.advert.status ? 'selected' : '' } value="1">Hoạt động</option>
            	</form:select>
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
 					$('#advert').submit();
 				}
 			</script>
			<button onclick="actionForm();" class="btn btn-primary">
				${requestScope.action == "updateAdvert" ? "Cập Nhật" : "Thêm" }
			</button>
		</div>
	</form:form>
</div>
