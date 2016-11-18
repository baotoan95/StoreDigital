<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
	<script type="text/javascript" src="<c:url value="/resources/admin/dist/js/delete.js"/>"></script>
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<h3 class="box-title">
					Danh sách khuyến mãi: ${promotion.name }
					<br/>
					Ngày bắt đầu: ${promotion.start } | Ngày kết thúc: ${promotion.end }
				</h3>
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
						<th>Chi tiết</th>
						<th>Mô Tả</th>
						<th></th>
						<th></th>
					</tr>
					<c:forEach var="promotion" items="${requestScope.promotionsDetail }">
					<tr>
						<td>${promotion.detail }</td>
						<td>${promotion.describe }</td>
						<td><a title="Sửa" href="${pageContext.request.contextPath }/mngPromotions/viewDetail/${promotion.id}"><i class="fa fa-edit"></i></a></td>
						<td><a title="Xóa" style="cursor: pointer;" onclick="delPromotion(${promotion.id}, this, 'promotionDetail')"><i class="fa fa-trash-o"></i></a></td>
					</tr>
					</c:forEach>
				</table>
			</div>
			<div class="box-footer clearfix">
            	<button onclick="window.location='${pageContext.request.contextPath}/mngPromotions/addNew/2'" class="btn btn-primary">Thêm</button>
            	<ul class="pagination pagination-sm no-margin pull-right">
                 	<%= request.getAttribute("pagination") %>
              	</ul>
           	</div>
		</div>
	</div>
</div>