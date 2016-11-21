<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<h3 class="box-title">Danh sách đợt khuyến mãi</h3>
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
						<th>Ngày Bắt Đầu</th>
						<th>Ngày Kết Thúc</th>
						<th>Tiêu Đề</th>
						<th></th>
						<th></th>
					</tr>
					<c:forEach var="promotion" items="${requestScope.promotions }">
					<tr>
						<td>${promotion.id }</td>
						<td>${promotion.start }</td>
						<td>${promotion.end }</td>
						<td>${promotion.name }</td>
						<td><a title="Sửa" href="${pageContext.request.contextPath }/mngPromotions/view/${promotion.id}/1"><i class="fa fa-edit"></i></a></td>
						<td><a title="Xóa" style="cursor: pointer;" onclick="delPromotion(${promotion.id}, this, 'promotion')"><i class="fa fa-trash-o"></i></a></td>
					</tr>
					</c:forEach>
				</table>
			</div>
			<div class="box-footer clearfix">
				<button onclick="window.location='${pageContext.request.contextPath}/mngPromotions/addNew/1'" class="btn btn-primary">Thêm</button>
            	<ul class="pagination pagination-sm no-margin pull-right">
                 	<%= request.getAttribute("pagination") %>
              	</ul>
           	</div>
		</div>
	</div>
</div>