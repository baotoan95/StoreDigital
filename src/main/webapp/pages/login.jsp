<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<section class="main-container col1-layout">
	<div class="main container">
		<div class="account-login">
			<div class="page-title">
				<h2>Đăng Nhập hoặc Đăng Ký</h2>
			</div>
			<fieldset class="col2-set">
				<div class="col-1 new-users">
					<strong>Đăng Ký</strong>
					<div class="content">
						<p>Bằng việc tạo một tài khoản với cửa hàng của chúng tôi, bạn
							sẽ có thể lưu trữ địa chỉ của mình, xem danh sách đơn hàng của
							bạn và nhiều ưu đãi khác.</p>
						<div class="buttons-set">
							<button
								onclick="window.location='${pageContext.request.contextPath}/regis'"
								class="button create-account" type="button">
								<span>Tạo Một Tài Khoản</span>
							</button>
						</div>
					</div>
				</div>
				<div class="col-2 registered-users">
					<strong>Đăng Nhập</strong>
					<div class="content">
						<form:form action="auth" commandName="user" method="POST">
							<form:hidden path="name" />
							<form:hidden path="tel" />
							<p>Nếu bạn đã có một tài khoản, vui lòng đăng nhập.</p>
							<br />
							<span id="error" class="error">${requestScope.message }</span>
							<ul class="form-list">
								<li><label for="email">Email<span class="required">*</span></label>
									<br /> <form:errors class="error" path="mail"></form:errors> <br />
									<form:input path="mail" title="Email Address"
										class="input-text required-entry" id="email"
										name="login[username]" /></li>
								<li><label for="pass">Mật khẩu<span
										class="required">*</span></label> <br /> <form:errors class="error"
										path="pass"></form:errors> <br /> <form:password path="pass"
										title="Password" id="pass"
										class="input-text required-entry validate-password"
										name="login[password]" /></li>
							</ul>
							<p class="required">* Trường bắt buộc</p>
							<div class="buttons-set">
								<button id="send2" name="send" type="submit"
									class="button login">
									<span>Đăng Nhập</span>
								</button>
								<a title="Nhập email và nhấn vào đây" class="forgot-word"
									onclick="remember();">Quên mật khẩu của bạn?</a>
							</div>
						</form:form>
					</div>
				</div>
			</fieldset>
		</div>
		<br> <br> <br> <br> <br>
	</div>
</section>