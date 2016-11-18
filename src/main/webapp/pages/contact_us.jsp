<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- breadcrumbs -->
<div class="breadcrumbs">
	<div class="container">
		<div class="row">
			<ul>
				<li class="home"><a title="Go to Home Page"
					href="${pageContext.request.contextPath }/index">Trang chủ</a><span>&mdash;›</span></li>
				<li class="category13"><strong>Liên hệ</strong></li>
			</ul>
		</div>
	</div>
</div>
<!-- main-container -->
<div class="main-container col2-right-layout">
	<div class="main container">
		<div class="row">
			<section class="col-main col-sm-9 wow">
				<div class="page-title">
					<h2>Liên hệ với chúng tôi</h2>
					<br/><span style="color: red;">${requestScope.message }</span>
				</div>
				<form:form modelAttribute="contact" action="${pageContext.request.contextPath }/send" method="POST">
				<div class="static-contain">
					<fieldset class="group-select">
						<ul>
							<li id="billing-new-address-form">
								<fieldset>
									<legend>New Address</legend>
									<ul>
										<li>
											<div class="customer-name">
												<div class="input-box name-firstname">
													<label for="billing:firstname"> Tên
													<span class="required">*</span></label> <br> 
													<form:input path="name" class="input-text "/><br/>
													<form:errors style="color: red;" path="name"></form:errors>
												</div>
												<div class="input-box name-lastname">
													<label for="billing:lastname"> Địa chỉ email <span class="required">*</span> </label> <br> 
													<form:input path="mail" class="input-text"/><br/>
													<form:errors style="color: red;" path="mail"></form:errors>
												</div>
											</div>
										</li>
										<li>
											<div class="input-box">
												<label for="billing:company">Tên công ty</label> <br/> 
												<form:input path="company" id="billing:company" title="Company" class="input-text"/>
											</div>
											<div class="input-box">
												<label for="billing:email">Số điện thoại di động <span class="required">*</span></label>
												<br>
												<form:input path="tel" class="input-text validate-email"/><br/>
												<form:errors style="color: red;" path="tel"></form:errors>
											</div>
										</li>
										<li>
											<label for="billing:street1">Địa chỉ 
												<span class="required">*</span>
											</label> <br> 
											<form:input path="address" class="input-text required-entry"/><br/>
											<form:errors style="color: red;" path="address"></form:errors>
										</li>
										<li>
											<label for="comment">Nội dung<em class="required">*</em></label> <br>
											<div style="float: none" class="">
												<form:textarea path="content" class="required-entry input-text" cols="5" rows="3"/><br/>
												<form:errors style="color: red;" path="content"></form:errors>
											</div>
										</li>
									</ul>
								</fieldset>
							</li>
							<li>
								<p class="require">
									<em class="required">* </em>Trường bắt buộc
								</p>
								<div class="buttons-set">
									<button type="submit" title="Submit" class="button submit">
										<span> Gửi </span>
									</button>
								</div>
							</li>
						</ul>
					</fieldset>
				</div>
				</form:form>
			</section>
		</div>
	</div>
</div>
<!--End main-container -->
