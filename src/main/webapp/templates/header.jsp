<%@page import="com.baotoan.spring.entities.Search"%>
<%@page import="com.baotoan.spring.dao.ProductDAO"%>
<%@page import="com.baotoan.spring.entities.MenuCate"%>
<%@page import="java.util.List"%>
<%@page import="com.baotoan.spring.dao.MenuCateDAOImpl"%>
<%@page import="com.baotoan.spring.dao.MenuCateDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Header -->
<header class="header-container">
	<div class="header-top">
		<div class="container">
			<div class="row">
				<!-- Header Language -->
				<div class="col-xs-6">
					<div class="dropdown block-language-wrapper">
						<a role="button" data-toggle="dropdown" data-target="#" class="block-language dropdown-toggle" href="#"> 
							<img src="<c:url value="/resources/images/english.png"/>" alt="language"> Việt Nam <span class="caret"></span>
						</a>
						<ul class="dropdown-menu" role="menu">
							<li role="presentation"><a role="menuitem" tabindex="-1" href="#">
								<img src="<c:url value="/resources/images/english.png"/>" alt="language"> English </a>
							</li>
							<li role="presentation"><a role="menuitem" tabindex="-1" href="#">
								<img src="<c:url value="/resources/images/francais.png"/>" alt="language"> Việt Nam </a>
							</li>
						</ul>
					</div>
					<!-- End Header Language -->
					<!-- Header Currency -->
					<div class="dropdown block-currency-wrapper">
						<a role="button" data-toggle="dropdown" data-target="#" class="block-currency dropdown-toggle" href="#"> USD <span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li role="presentation"><a role="menuitem" tabindex="-1" href="#"> $ - Dollar </a></li>
							<li role="presentation"><a role="menuitem" tabindex="-1" href="#"> VNĐ - VN </a></li>
						</ul>
					</div>
					<!-- End Header Currency -->
					<div class="welcome-msg hidden-xs">
						<c:if test="${empty sessionScope.user }">
							Chào bạn đến với Inspire!
						</c:if>
						<c:if test="${not empty sessionScope.user }">
							Chào ${sessionScope.user.name } đã trở lại Inspire!
						</c:if>
					</div>
				</div>
				<div class="col-xs-6">
					<!-- Header Top Links -->
					<div class="toplinks">
						<div class="links">
							<div class="myaccount">
								<a title="Tài khoản của tôi" href="profile"><span class="hidden-xs">Tài khoản của tôi</span></a>
							</div>
							<div class="wishlist">
								<a title="Danh sách yêu thích" href="wish"><span class="hidden-xs">Danh sách yêu thích</span></a>
							</div>
							<div class="myaccount">
								<a title="So sánh" href="viewCompare"><span class="hidden-xs">So sánh</span></a>
							</div>
							<div class="phone hidden-xs">1 800 123 1234</div>
						</div>
					</div>
					<!-- End Header Top Links -->
				</div>
			</div>
		</div>
	</div>
	<div class="header container">
		<div class="row">
			<div class="col-lg-2 col-sm-3 col-md-2 col-xs-12">
				<!-- Header Logo -->
				<a class="logo" title="Magento Commerce" href="${pageContext.request.contextPath }/index">
					<img alt="Magento Commerce" src="<c:url value="/resources/images/logo.png"/>">
				</a>
				<!-- End Header Logo -->
			</div>
			<div class="col-lg-7 col-sm-4 col-md-6 col-xs-12">
				<!-- Search-col -->
				<div class="search-box">
					<form action="search" method="POST" id="search_mini_form">
					<%!MenuCateDAO menuDAO = new MenuCateDAOImpl(); %>
						<select class="cate-dropdown hidden-xs" name="type" id="cateId">
							<option value="-1">All Categories</option>
					<%
						List<MenuCate> parents = menuDAO.getMenuCatesByParentId(0);
						for(MenuCate parent : parents) {
					%>
							<option value="<%= parent.getId() %>"><%= parent.getName() %></option>
					<% 
							List<MenuCate> subs = menuDAO.getMenuCatesByParentId(parent.getId());
							for(MenuCate sub : subs) {
					%>
								<option value="<%= sub.getId() %>">&nbsp;&nbsp;&nbsp;<%= sub.getName() %></option>
					<%
							}
						}
					%>
						</select>
						<div id="search" class="searchBoxAutoComplete">
							<input autocomplete="off" oninput="autoComplete(this.value)" type="text" placeholder="Search here..." maxlength="70" name="key" id="search"/>
							<br/>
							<div id="autocomplete"></div>
						</div>
						<button id="submit-button" type="submit" class="search-btn-bg">
							<span>Search</span>
						</button>
					</form>
				</div>
				<!-- End Search-col -->
			</div>
			<!-- Top Cart -->
			<div class="col-lg-3 col-sm-5 col-md-4 col-xs-12">
				<div class="top-cart-contain">
					<div class="mini-cart">
						<div data-toggle="dropdown" data-hover="dropdown" class="basket dropdown-toggle">
							<a href="cart"> <i class="icon-cart"></i>
								<div class="cart-box">
									<span class="title">Giỏ hàng</span>
									<span id="cart-total"> <c:out value="${not empty sessionScope.cartInfor ? sessionScope.cartInfor.sizeCart : 0 }"/> </span>
								</div>
							</a>
						</div>
						<div id="showCart">
						<c:if test="${not empty sessionScope.cartInfor or sessionScope.cartInfor.listRecenty.lengh > 0 }">
							<div class="top-cart-content arrow_box">
								<div class="block-subtitle">Những sản phẩm đã thêm gần đây</div>
								<ul id="cart-sidebar" class="mini-products-list">
									<c:forEach var="item" items="${sessionScope.cartInfor.listRecenty }">
									<li class="item even">
										<a class="product-image" href="view?id=${item.id }" title="${item.name }">
											<img alt="${item.name }" src="${pageContext.request.contextPath }/resources${item.imageUrl }" width="80">
										</a>
										<div class="detail-item">
											<div class="product-details">
												<a href="#" title="Xóa" onClick="cart(${item.id}, 'DEL')" class="glyphicon glyphicon-remove">&nbsp;</a> 
												<a class="glyphicon glyphicon-pencil" title="Sửa" href="cart">&nbsp;</a>
												<p class="product-name">
													<a href="view?id=${item.id }" title="${item.name }">${item.name }</a>
												</p>
											</div>
											<div class="product-details-bottom">
												<span class="price">Giá: ${item.price } VNĐ</span> 
												<span class="title-desc">Số lượng: </span><strong>${item.quantity }</strong>
											</div>
										</div>
									</li>
									</c:forEach>
								</ul>
								<div class="top-subtotal">Tổng giá trị: <span class="price">${sessionScope.cartInfor.totalPay } VNĐ</span></div>
								<div class="actions">
									<button class="btn-checkout" type="button" onclick="window.location='${pageContext.request.contextPath}/cart'">
										<span>Thanh Toán</span>
									</button>
									<button class="view-cart" type="button" onclick="window.location='${pageContext.request.contextPath}/cart'">
										<span>Xem Thêm</span>
									</button>
								</div>
							</div>
						</c:if>
						</div>
					</div>
					<div id="ajaxconfig_info" style="display: none">
						<a href="#"></a> <input value="" type="hidden"> 
							<input id="enable_module" value="1" type="hidden"> 
							<input class="effect_to_cart" value="1" type="hidden"> 
							<input class="title_shopping_cart" value="Go to shopping cart" type="hidden">
					</div>
				</div>
				<div class="signup">
					<a title='Đăng ký' href='${pageContext.request.contextPath }/regis'><span>ĐăngKý</span></a>
				</div>
				<span class="or"> | </span>
				<div class="login">
					<%
						if(session.getAttribute("user") != null) {
							out.print("<a title='Đăng xuất' href='logout'><span>ĐăngXuất</span></a>");
						} else {
							out.print("<a title='Đăng nhập' href='login'><span>ĐăngNhập</span></a>");
						}
					%>
				</div>
			</div>
			<!-- End Top Cart -->
		</div>
	</div>
</header>
<!-- end header -->
<!-- Navbar -->
<nav>
	<div class="container">
		<div class="nav-inner">
			<!-- mobile-menu -->
			<div class="hidden-desktop" id="mobile-menu">
				<ul class="navmenu">
					<li>
						<div class="menutop">
							<div class="toggle">
								<span class="icon-bar"></span> <span class="icon-bar"></span> 
								<span class="icon-bar"></span>
							</div>
							<h2>Menu</h2>
						</div>
						<ul style="display: none;" class="submenu">
							<li>
								<ul class="topnav">
									<li class="level0 nav-6 level-top first parent">
										<a class="level-top" href="${pageContext.request.contextPath }/index"> <span>Trang Chủ</span></a>
									</li>
									<li class="level0 nav-7 level-top parent">
										<a class="level-top" href="sitemap"> <span>Danh Mục</span></a>
									</li>
									<li class="level0 nav-9 level-top last parent ">
										<a class="level-top" href="contact"> <span>Liên Hệ</span></a>
									</li>
									<li class="level0 nav-9 level-top last parent ">
										<a class="level-top" href="about"> <span>Thông Tin</span></a>
									</li>
								</ul>
							</li>
						</ul>
					</li>
				</ul>
				<!--navmenu-->
			</div>
			<!--End mobile-menu -->
			<a class="logo-small" title="Magento Commerce" href="${pageContext.request.contextPath }/index">
				<img alt="Magento Commerce" src="<c:url value="/resources/images/logo-small.png"/>">
			</a>
			<ul id="nav" class="hidden-xs">
				<li class="level0 parent drop-menu"><a href="${pageContext.request.contextPath }/index" class=""><span>Trang Chủ</span></a></li>
				<li class="level0 nav-5 level-top first"><a class="level-top" href="sitemap"> <span>Danh Mục</span></a>
					<div style="display: none;" class="level0-wrapper dropdown-6col">
						<div class="level0-wrapper2">
							<div class="nav-block nav-block-center grid12-8 itemgrid itemgrid-4col">
							<%!
							String nav = "";
							private void getMenu(int parentID, int level) {
								List<MenuCate> rs = menuDAO.getMenuCatesByParentId(parentID);
								MenuCate root = null;
								level++;
								if (rs.size() > 0) {
									nav += "<ul class='level" + (level - 1) + "'>";
									for (MenuCate item : rs) {
										root = item;
										String cless = "";
										if (level == 1) {
											cless = level + " nav-6-1 parent item";
										} else {
											cless = level + " nav-6-1-1 ";
										}
										nav += "<li class='" + cless + "'><a href='/StoreDigital/show/"+item.getId()+"/'><span>"
												+ item.getName() + "</span></a>";
										getMenu(item.getId(), level);
									}
									nav += "</ul></li>";
								} else {
									nav += "</li>";
								}
							}
							%>
							<%
								if(nav.length() == 0) {
									getMenu(0, 0);
								}
							%>
								<!--mega menu-->
								<ul class="level3">
									<%= nav %>
								</ul>
							</div>
							<!--nav-block nav-block-center-->
							<div class="nav-block nav-block-right std grid12-4">
								<p>
									<a href="#"><img class="fade-on-hover" src="<c:url value="/resources/images/nav-img1.jpg"/>" alt="nav img"></a>
								</p>
								<h3 class="heading">Inspire Store</h3>
								<p>Món quà cho mọi nhà</p>
							</div>
						</div>
					</div>
				</li>
				<li class="level0 parent drop-menu"><a href="contact"><span>Liên Hệ</span></a></li>
				<li class="level0 parent drop-menu"><a href="about"><span>Thông Tin</span></a></li>
			</ul>
		</div>
	</div>
</nav>
<!-- end nav -->