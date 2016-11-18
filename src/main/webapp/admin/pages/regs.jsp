<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<h3 class="box-title">Danh sách thành viên đã đăng ký</h3>
				<div class="box-tools">
					<div class="input-group" style="width: 150px;">
						<input type="text" name="table_search"
							class="form-control input-sm pull-right" placeholder="Search" />
						<div class="input-group-btn">
							<button class="btn btn-sm btn-default">
								<i class="fa fa-search"></i>
							</button>
						</div>
					</div>
				</div>
			</div>
			<div class="box-body table-responsive no-padding">
				<table class="table table-hover">
					<tr>
						<th>ID</th>
						<th>Email</th>
						<th></th>
					</tr>
					<c:forEach var="reg" items="${requestScope.regs }">
					<tr>
						<td>${reg.id }</td>
						<td>${reg.mail }</td>
						<td><a title="Xóa" href="#"><i class="fa fa-trash-o"></i></a></td>
					</tr>
					</c:forEach>
				</table>
			</div>
			<div class="box-footer clearfix">
            	<ul class="pagination pagination-sm no-margin pull-right">
                 	<%= request.getAttribute("pagination") %>
              	</ul>
           	</div>
		</div>
	</div>
	
	<section class="col-lg-12 connectedSortable">
				<!-- quick email widget -->
				<div class="box box-info">
					<div class="box-header">
						<i class="fa fa-envelope"></i>
						<h3 class="box-title">Gửi email</h3>
						<!-- tools box -->
						<div class="pull-right box-tools">
							<button class="btn btn-info btn-sm" data-widget="remove"
								data-toggle="tooltip" title="Remove">
								<i class="fa fa-times"></i>
							</button>
						</div>
						<!-- /. tools -->
					</div>
						<form:form action="${pageContext.request.contextPath }/mngRegUsers/sendMail" method="POST" modelAttribute="email">
					<div class="box-body">
							<div class="form-group">
								<form:input path="mailTo" type="email" class="form-control" name="emailto"
									placeholder="Email to:" />
							</div>
							<div class="form-group">
								<form:input path="subject" type="text" class="form-control" name="subject"
									placeholder="Subject" />
							</div>
							<div>
								<form:textarea path="content" class="textarea" placeholder="Message"
									style="width: 100%; height: 125px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"></form:textarea>
							</div>
					</div>
					<div class="box-footer clearfix">
						<button type="submit" class="pull-right btn btn-default" id="sendEmail">
							Send <i class="fa fa-arrow-circle-right"></i>
						</button>
					</div>
						</form:form>
				</div>
			</section>
</div>