<%@page import="com.baotoan.spring.dao.MenuCateDAOImpl"%>
<%@page import="com.baotoan.spring.dao.MenuCateDAO"%>
<%@page import="com.baotoan.spring.entitys.MenuCate"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<section class="main-container col1-layout">
	<div class="main container">
		<div class="col-main">
			<div class="cart wow">
				<div class="page-title">
					<h2>Sitemap</h2>
				</div>
				<div class="row content-row">
<!-- 					<div class="col-xs-6 col-sm-3 col-md-3 col-lg-4"> -->
<!-- 						<ul class="simple-list arrow-list bold-list"> -->
<!-- 							<li><a href="grid.html">Woman</a> -->
<!-- 								<ul> -->
<!-- 									<li><a href="grid.html">Featured products</a></li> -->
<!-- 									<li><a href="grid.html">New arrivals</a></li> -->
<!-- 									<li><a href="grid.html">Bestsellers</a></li> -->
<!-- 									<li><a href="grid.html">Footwear Womens</a></li> -->
<!-- 									<li><a href="grid.html">Shorts</a></li> -->
<!-- 								</ul> -->
<!-- 							</li> -->
<!-- 							<li> -->
<!-- 								<a href="grid.html">Man</a> -->
<!-- 								<ul> -->
<!-- 									<li><a href="grid.html">Polo Shirts</a></li> -->
<!-- 									<li><a href="grid.html">Shirts</a></li> -->
<!-- 									<li><a href="grid.html">Big &amp; Tall</a></li> -->
<!-- 									<li><a href="grid.html">Jeans</a></li> -->
<!-- 									<li><a href="grid.html">Sweaters</a></li> -->
<!-- 								</ul> -->
<!-- 							</li> -->
<!-- 							<li><a href="grid.html">Electronics</a></li> -->
<!-- 							<li><a href="grid.html">Furniture</a></li> -->
<!-- 							<li><a href="grid.html">Sale</a></li> -->
<!-- 						</ul> -->
<!-- 					</div> -->
<!-- 					<div class="col-xs-6 col-sm-3 col-md-3 col-lg-4"> -->
<!-- 						<ul class="simple-list arrow-list bold-list"> -->
<!-- 							<li><a href="shopping_cart.html">Shopping Cart</a></li> -->
<!-- 							<li><a href="login.html">My Account</a> -->
<!-- 								<ul> -->
<!-- 									<li><a href="login.html">My Account</a></li> -->
<!-- 									<li><a href="#">Order history</a></li> -->
<!-- 									<li><a href="#">Advanced search</a></li> -->
<!-- 									<li><a href="#">Reviews</a></li> -->
<!-- 								</ul> -->
<!-- 							</li> -->
<!-- 							<li><a href="#">Customer service</a> -->
<!-- 								<ul> -->
<!-- 									<li><a href="#">Online support</a></li> -->
<!-- 									<li><a href="#">Help & FAQs</a></li> -->
<!-- 									<li><a href="#">Call Center</a></li> -->
<!-- 								</ul> -->
<!-- 							</li> -->
<!-- 							<li><a href="#">Information</a> -->
<!-- 								<ul> -->
<!-- 									<li><a href="about_us.html">About Us</a></li> -->
<!-- 									<li><a href="#">Shipping &amp; Returns</a></li> -->
<!-- 									<li><a href="#">Privacy Notice</a></li> -->
<!-- 									<li><a href="#">Conditions of Use</a></li> -->
<!-- 								</ul> -->
<!-- 							</li> -->
<!-- 						</ul> -->
<!-- 					</div> -->
							<%!MenuCateDAO menuDAO = new MenuCateDAOImpl(); %>
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
					<div class="col-xs-6 col-sm-3 col-md-3 col-lg-4">
						<ul class="simple-list arrow-list bold-list">
							<%
								if(nav.length() == 0) {
									getMenu(0, 0);
								}
							%>
							<!--mega menu-->
							<%= nav %>
						</ul>
					</div>
					<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
						<img class="img-responsive animate scale"
							src="<c:url value="/resources/images/large-icon-sitemap.png"/>"
							alt="">
					</div>
				</div>
			</div>
		</div>
	</div>
</section>