<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- main-container -->
<div class="main-container col2-right-layout">
	<div class="main container">
		<div class="row">
			<section class="col-main col-sm-9 wow">
				<div class="my-account">
					<div class="page-title">
						<h2>Thông Tin Tài Khoản</h2>
					</div>
					<div class="dashboard">
						<div class="welcome-msg">
							<strong>Xin chào ${requestScope.user.name }</strong>
							<p>Từ trang Thông Tin Tài Khoản của bạn, bạn có thể xem nhanh
								các hoạt động gần đây và cập nhật thông tin của mình.</p>
						</div>
						<div class="recent-orders">
							<div class="title-buttons">
								<strong>Đơn hàng gần đây</strong>
							</div>
							<br />
							<br />
							<c:if test="${empty requestScope.orders }">
								<p class="error">Không có đơn hàng nào gần đây.</p>
							</c:if>
							<c:if test="${not empty requestScope.orders }">
								<div class="table-responsive">
									<table class="data-table" id="my-orders-table">
										<col>
										<col>
										<col>
										<col width="1">
										<col width="1">
										<col width="1">
										<thead>
											<tr class="first last">
												<th>Mã Đơn Hàng</th>
												<th>Ngày Đặt</th>
												<th>Giao Tới</th>
												<th><span class="nobr">Tổng Tiền</span></th>
												<th colspan="2">Trạng Thái</th>
												<th>&nbsp;</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="order" items="${requestScope.orders }">
												<tr class="first odd">
													<td>${order.id }</td>
													<td>${order.orderDate }</td>
													<td>${order.address }</td>
													<td><span class="price">${order.totalPay } VNĐ</span></td>
													<td colspan="2"><em>${order.statusName }</em></td>
													<td class="a-center last"><span class="nobr"> <a
															href="${pageContext.request.contextPath }/dOrder?id=${order.id}">Chi
																Tiết</a></span></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</c:if>
						</div>
						<div class="account-login">
							<div class="page-title">
								<h2>Thông tin cơ bản</h2>
								<span class="error">${message }</span>
							</div>
							<fieldset class="col2-set">
								<div class="col-1 new-users">
									<strong>Tài khoản</strong>
									<div class="content">
										<form:form
											action="${pageContext.request.contextPath }/updateUser"
											commandName="user" method="POST">
											<form:hidden path="role" />
											<form:hidden path="status" />
											<form:hidden path="score" />
											<span>${requestScope.message }</span>
											<ul class="form-list">
												<li><label for="email">Email<span
														class="required">*</span></label> <br> <form:input
														path="mail" title="Email Address"
														class="input-text required-entry" id="email"
														name="login[username]" /> <br /> <form:errors
														class="error" path="mail"></form:errors></li>
												<li><label for="pass">Mật khẩu<span
														class="required">*</span></label> <br> <form:input
														path="pass" title="Mật khẩu" id="pass"
														class="input-text required-entry" /> <br /> <form:errors
														class="error" path="pass"></form:errors></li>
												<li><label for="pass">Họ tên<span
														class="required">*</span></label> <br> <form:input
														path="name" title="Tên" id="pass"
														class="input-text required-entry" /> <br /> <form:errors
														class="error" path="name"></form:errors></li>
												<li><label for="pass">Chứng minh thư</label> <br>
													<form:input path="id" title="Password" id="pass"
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
														title="Password" id="pass"
														class="input-text required-entry" /> <br /> <form:errors
														class="error" path="tel"></form:errors></li>
											</ul>
											<p class="required">* Trường bắt buộc</p>
											<div class="buttons-set">
												<button id="send2" name="send" type="submit"
													class="button login">
													<span>Cập Nhật</span>
												</button>
											</div>
										</form:form>
									</div>
								</div>
							</fieldset>
						</div>
					</div>
				</div>
			</section>
			<aside class="col-right sidebar col-sm-3 wow">
				<div class="block block-compare">
					<div class="block-title ">
						<span>Hướng Dẫn</span>
					</div>
					<div class="block-content">
						<p>Để chỉnh sửa tài khoản, thay đổi các thông tin sau đó nhấn
							Cập Nhật.</p>
					</div>
				</div>
			</aside>
		</div>
	</div>
</div>
<!--End main-container -->
