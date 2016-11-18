<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">Nội dung phản hồi</h3>
		<br/>
		<span style="color: red;">${requestScope.message }</span>
	</div>
	<form:form name="contact" method="POST" modelAttribute="contact" action="${pageContext.request.contextPath }/mngContacts/updateContact">
		<form:hidden path="id"/>
		<div class="box-body">
			<div class="form-group">
				<label for="exampleInputEmail1">Tên người gửi</label> 
				<form:input path="name" class="form-control"/>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Tên công ty</label> 
				<form:input path="company" class="form-control"/>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Địa chỉ</label> 
				<form:input path="address" class="form-control"/>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Số điện thoại</label> 
				<form:input path="tel" class="form-control"/>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Email</label> 
				<form:input path="mail" class="form-control"/>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Ngày gửi</label> 
				<form:input path="date" class="form-control"/>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Nội dung</label> 
				<form:textarea cols="3" rows="3" path="content" class="form-control"/>
			</div>
			<div class="form-group">
            	<label>Trạng thái</label>
               	<form:select path="status" class="form-control">
                	<option ${pro.id == requestScope.promotionDetail.promotionId ? 'selected' : '' } value="1">Đã xem</option>
                	<option ${pro.id == requestScope.promotionDetail.promotionId ? 'selected' : '' } value="0">Chưa xem</option>
            	</form:select>
           	</div>
		</div>
		<div class="box-footer">
			<button type="submit" class="btn btn-primary"> Cập Nhật </button>
		</div>
	</form:form>
</div>
