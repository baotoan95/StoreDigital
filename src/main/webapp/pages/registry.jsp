<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<section class="main-container col1-layout">
	<div class="main container">
		<div class="account-login">
			<div class="page-title">
				<h2>Đăng Ký</h2>
			</div>
			<fieldset class="col2-set">
				<legend>Đăng Nhập hoặc Đăng Ký</legend>
				<div class="col-1 new-users">
					<strong>Đăng Ký</strong>
					<div class="content">
						<form:form action="${pageContext.request.contextPath }/auRegis"
							commandName="user" method="POST">
							<form:hidden path="role" />
							<form:hidden path="status" />
							<form:hidden path="score" />
							<span>${requestScope.message }</span>
							<ul class="form-list">
								<li><label for="email">Email<span class="required">*</span></label>
									<br> <form:input path="mail" title="Email Address"
										class="input-text required-entry" id="email"
										name="login[username]" /> <br /> <form:errors class="error"
										path="mail"></form:errors></li>
								<li><label for="pass">Mật khẩu<span
										class="required">*</span></label> <br> <form:password path="pass"
										title="Password" id="pass"
										class="input-text required-entry validate-password"
										name="login[password]" /> <br /> <form:errors class="error"
										path="pass"></form:errors></li>
								<li><label for="pass">Họ tên<span class="required">*</span></label>
									<br> <form:input path="name" title="Tên" id="pass"
										class="input-text required-entry" /> <br /> <form:errors
										class="error" path="name"></form:errors></li>
								<li><label for="pass">Chứng minh thư</label> <br> <form:input
										path="id" title="Password" id="pass"
										class="input-text required-entry" /> <br /> <form:errors
										class="error" path="id"></form:errors></li>
								<li><label for="pass">Địa chỉ</label> <br> <form:input
										path="address" title="Password" id="pass"
										class="input-text required-entry" /> <br /> <form:errors
										class="error" path="address"></form:errors></li>
								<li><label for="pass">Thành phố</label> <br> <form:input
										path="city" title="Password" id="pass"
										class="input-text required-entry" /> <br /> <form:errors
										class="error" path="city"></form:errors></li>
								<li><label for="pass">Số điện thoại<span
										class="required">*</span></label> <br> <form:input path="tel"
										title="Password" id="pass" class="input-text required-entry" />
									<br /> <form:errors class="error" path="tel"></form:errors></li>
							</ul>
							<p class="required">* Trường bắt buộc</p>
							<div class="buttons-set">
								<button id="send2" name="send" type="submit"
									class="button login">
									<span>Đăng Kí</span>
								</button>
							</div>
						</form:form>
					</div>
				</div>
			</fieldset>
		</div>
		<br> <br> <br> <br> <br>
	</div>
</section>