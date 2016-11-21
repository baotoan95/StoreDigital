<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- Sidebar user panel -->
		<div class="user-panel">
			<div class="pull-left image">
				<img
					src="<c:url value="/resources/admin/dist/img/user2-160x160.jpg"/>"
					class="img-circle" alt="User Image" />
			</div>
			<div class="pull-left info">
				<p>
					<c:if test="${not empty sessionScope.user }">
						${sessionScope.user.name }
					</c:if>
				</p>
				<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
			</div>
		</div>
		<!-- search form -->
		<form action="#" method="get" class="sidebar-form">
			<div class="input-group">
				<input type="text" name="q" class="form-control"
					placeholder="Search..." /> <span class="input-group-btn">
					<button type="submit" name="search" id="search-btn"
						class="btn btn-flat">
						<i class="fa fa-search"></i>
					</button>
				</span>
			</div>
		</form>
		<!-- /.search form -->
		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu">
			<li class="header">MAIN NAVIGATION</li>
			<li
				class="treeview ${sessionScope.adminCurrentPage == 'admin' ? 'active' : ''}">
				<a href="${pageContext.request.contextPath }/admin"> <i
					class="fa fa-dashboard"></i> <span>Dashboard</span>
			</a>
			</li>
			<li
				class="treeview ${sessionScope.adminCurrentPage == 'mngCates' ? 'active' : ''}">
				<a href="${pageContext.request.contextPath }/mngCates/show"> <i
					class="fa fa-th"></i> <span>Thể loại</span>
			</a>
			</li>
			<li
				class="treeview ${sessionScope.adminCurrentPage == 'mngProducts' ? 'active' : ''}">
				<a href="#"> <i class="fa fa-pie-chart"></i> <span>Sản
						phẩm</span> <i class="fa fa-angle-left pull-right"></i>
			</a>
				<ul class="treeview-menu">
					<li><a
						href="${pageContext.request.contextPath }/mngProducts/show/1/"><i
							class="fa fa-circle-o"></i>Tất cả</a></li>
					<li><a
						href="${pageContext.request.contextPath }/mngProducts/add"><i
							class="fa fa-circle-o"></i>Thêm sản phẩm</a></li>
					<li><a
						href="${pageContext.request.contextPath }/mngProductDetails/details"><i
							class="fa fa-circle-o"></i>Chi tiết sản phẩm</a></li>
					<li><a
						href="${pageContext.request.contextPath }/mngProductDetails/detailGroups"><i
							class="fa fa-circle-o"></i>Nhóm chi tiết</a></li>
				</ul>
			</li>
			<li
				class="treeview ${sessionScope.adminCurrentPage == 'mngPromotions' ? 'active' : ''}">
				<a href="#"> <i class="fa fa-laptop"></i> <span>Khuyến
						mãi</span> <i class="fa fa-angle-left pull-right"></i>
			</a>
				<ul class="treeview-menu">
					<li><a
						href="${pageContext.request.contextPath }/mngPromotions/show/1"><i
							class="fa fa-circle-o"></i>Tất cả</a></li>
					<li><a
						href="${pageContext.request.contextPath }/mngPromotions/addNew/1"><i
							class="fa fa-circle-o"></i>Thêm đợt khuyến mãi</a></li>
					<li><a
						href="${pageContext.request.contextPath }/mngPromotions/addNew/2"><i
							class="fa fa-circle-o"></i>Thêm chi tiết</a></li>
				</ul>
			</li>
			<li
				class="treeview ${sessionScope.adminCurrentPage == 'mngOrders' ? 'active' : ''}">
				<a href="#"> <i class="fa fa-edit"></i> <span>Đơn hàng</span> <small
					class="label bg-green" id="totalOrderNotApproved">
						${sessionScope.totalOrderNotApproved } </small> <i
					class="fa fa-angle-left pull-right"></i>
			</a>
				<ul class="treeview-menu">
					<li><a
						href="${pageContext.request.contextPath }/mngOrders/NotApproved/1"><i
							class="fa fa-circle-o"></i>Chưa duyệt</a></li>
					<li><a
						href="${pageContext.request.contextPath }/mngOrders/Approved/1"><i
							class="fa fa-circle-o"></i>Đã duyệt</a></li>
					<li><a
						href="${pageContext.request.contextPath }/mngOrders/Delivered/1"><i
							class="fa fa-circle-o"></i>Đã giao</a></li>
				</ul>
			</li>
			<li
				class="treeview ${sessionScope.adminCurrentPage == 'mngRegUsers' ? 'active' : ''}">
				<a href="${pageContext.request.contextPath }/mngRegUsers/show/1/">
					<i class="fa fa-table"></i> <span>Người dùng nhận email</span>
			</a>
			</li>

			<li
				class="treeview ${sessionScope.adminCurrentPage == 'mngContacts' ? 'active' : ''}">
				<a href="${pageContext.request.contextPath }/mngContacts/show/1/">
					<i class="fa fa-envelope"></i> <span>Phản hồi</span> <small
					class="label pull-right bg-yellow">${sessionScope.totalContact }</small>
			</a>
			</li>
			<li
				class="treeview ${sessionScope.adminCurrentPage == 'mngMembers' ? 'active' : ''}">
				<a href="${pageContext.request.contextPath }/mngMembers/show/1/">
					<i class="fa fa-book"></i> <span>Thành viên</span>
			</a>
			</li>
			<li
				class="treeview ${sessionScope.adminCurrentPage == 'mngAdvertiments' ? 'active' : ''}">
				<a
				href="${pageContext.request.contextPath }/mngAdvertiments/show/1/">
					<i class="fa fa-circle-o text-aqua"></i> <span>Quảng cáo</span>
			</a>
			</li>
			<li
				class="treeview ${sessionScope.adminCurrentPage == 'mngPosts' ? 'active' : ''}">
				<a href="#"> <i class="fa fa-edit"></i> <span>Bài viết</span> <i
					class="fa fa-angle-left pull-right"></i>
			</a>
				<ul class="treeview-menu">
					<li><a
						href="${pageContext.request.contextPath }/mngPosts/show/1/"><i
							class="fa fa-circle-o"></i>Tất cả</a></li>
					<li><a
						href="${pageContext.request.contextPath }/mngPosts/addNew"><i
							class="fa fa-circle-o"></i>Viết bài</a></li>
				</ul>
			</li>
		</ul>
	</section>
	<!-- /.sidebar -->
</aside>