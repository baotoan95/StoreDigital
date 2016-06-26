
<%@page import="com.baotoan.spring.entitys.Advertiment"%>
<%@page import="com.baotoan.spring.dao.ImageDAOImpl"%>
<%@page import="com.baotoan.spring.dao.ImageDAO"%>
<%@page import="com.baotoan.spring.entitys.Image"%>
<%@page import="com.baotoan.spring.entitys.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Slider -->
<div id="magik-slideshow" class="magik-slideshow">
	<div class="wow">
		<div id='rev_slider_4_wrapper'
			class='rev_slider_wrapper fullwidthbanner-container'>
			<div id='rev_slider_4' class='rev_slider fullwidthabanner'>
				<ul>
					<c:forEach var="adver" items="${advers }">
          	${adver.describe } 	
          </c:forEach>
				</ul>
				<div class="tp-bannertimer"></div>
			</div>
		</div>
	</div>
</div>
<!-- End Slider -->
<!-- header service  -->
<div class="header-service">
	<div class="container">
		<div class="row">
			<div class="col-lg-3 col-sm-6 col-xs-12">
				<div class="content">
					<div class="icon-truck">&nbsp;</div>
					<span><strong>MIỄN PHÍ GIAO HÀNG</strong> trên $300</span>
				</div>
			</div>
			<div class="col-lg-3 col-sm-6 col-xs-12">
				<div class="content">
					<div class="icon-support">&nbsp;</div>
					<span><strong>HỖ TRỢ KHÁCH HÀNG</strong> 24/7</span>
				</div>
			</div>
			<div class="col-lg-3 col-sm-6 col-xs-12">
				<div class="content">
					<div class="icon-money">&nbsp;</div>
					<span><strong>HOÀN LẠI TIỀN</strong> bảo hành trọn gói</span>
				</div>
			</div>
			<div class="col-lg-3 col-sm-6 col-xs-12">
				<div class="content">
					<div class="icon-dis">&nbsp;</div>
					<span><strong class="orange">GIẢM GIÁ</strong> đơn hàng trên
						$300</span>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- end header service  -->

<!-- main container  -->
<section class="main-container col1-layout home-content-container">
	<div class="container">
		<div class="row">
			<div class="std">
				<div class="col-lg-8 col-xs-12 col-sm-8 best-seller-pro wow">
					<div class="slider-items-products">
						<div class="new_title center">
							<h2>Sản phẩm bán chạy nhất</h2>
						</div>
						<div id="best-seller-slider"
							class="product-flexslider hidden-buttons">
							<div class="slider-items slider-width-col4">
								<c:forEach var="pro" items="${requestScope.bestsell }">
									<div class="item">
										<div class="col-item">
											<c:if test="${pro.promotionId != 0}">
												<div class="sale-label sale-top-right">Sale</div>
											</c:if>
											<div class="images-container">
												<a class="product-image" title="Sample Product"
													href="${pageContext.request.contextPath }/view?id=${pro.id }"> <img
													src="${pageContext.request.contextPath }/resources${pro.urlImage}"
													class="img-responsive" alt="product-image" />
												</a>
												<div class="actions">
													<div class="actions-inner">
														<button type="button" title="Thêm vào giỏ hàng"
															class="button btn-cart">
															<span onclick="cart(${pro.id}, 'ADD',this)">Thêm Vào</span>
														</button>
														<ul class="add-to-links">
															<li><a style="cursor: pointer;"
																onclick="like(${pro.id})"
																title="Thêm vào danh sách ưu thích"
																class="link-wishlist"><span>Thích</span></a></li>
															<li><a style="cursor: pointer;"
																onclick="addCompare(${pro.id})"
																title="Thêm vào để so sánh" class="link-compare "><span>So
																		Sánh</span></a></li>
														</ul>
													</div>
												</div>
												<div class="qv-button-container">
													<a href="${pageContext.request.contextPath }/view?id=${pro.id }"
														class="qv-e-button btn-quickview-1"><span> <span>Xem
																Nhanh</span></span></a>
												</div>
											</div>
											<div class="info">
												<div class="info-inner">
													<div class="item-title">
														<a title="${pro.name }" href="view?id=${pro.id}">${pro.name }</a>
													</div>
													<div class="item-content">
														<div class="ratings">
															<div class="rating-box">
																<div style="width:${(100 / 5) * pro.reviews }%"
																	class="rating"></div>
															</div>
														</div>
														<div class="price-box">
															<p class="special-price">
																<span class="price"> ${pro.newPrice } VNĐ </span>
															</p>
															<p class="old-price">
																<span class="price-sep">-</span> <span class="price">
																	${pro.oldPrice } VNĐ </span>
															</p>
														</div>
													</div>
												</div>
												<div class="clearfix"></div>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
				<div
					class="col-lg-4 col-xs-12 col-sm-4 wow latest-pro small-pr-slider">
					<div class="slider-items-products">
						<div class="new_title center">
							<h2>Sản phẩm mới</h2>
						</div>
						<div id="latest-deals-slider"
							class="product-flexslider hidden-buttons latest-item">
							<div class="slider-items slider-width-col4">
								<c:forEach var="pro" items="${requestScope.latest }">
									<div class="item">
										<div class="col-item">
											<div class="images-container">
												<a class="product-image" title="Sample Product"
													href="${pageContext.request.contextPath }/view?id=${pro.id }"> <img
													src="${pageContext.request.contextPath }/resources${pro.urlImage}"
													class="img-responsive" alt="product-image" />
												</a>
												<div class="actions">
													<div class="actions-inner">
														<ul class="add-to-links">
															<li><a title="Thêm vào danh sách ưu thích"
																onclick="like(${pro.id})" class="link-wishlist"><span>Thích</span></a></li>
															<li><a style="cursor: pointer;"
																onclick="addCompare(${pro.id})"
																title="Thêm vào để so sánh" class="link-compare "><span>So
																		Sánh</span></a></li>
														</ul>
													</div>
												</div>
												<div class="qv-button-container">
													<a href="${pageContext.request.contextPath }/view?id=${pro.id }"
														class="qv-e-button btn-quickview-1"> <span><span>Xem
																Nhanh</span></span></a>
												</div>
											</div>
											<div class="info">
												<div class="info-inner">
													<div class="item-title">
														<a title="${pro.name }" href="${pageContext.request.contextPath }/view?id=${pro.id }">
															${pro.name } </a>
													</div>
													<div class="item-content">
														<div class="ratings">
															<div class="rating-box">
																<div style="width:${(100 / 5) * pro.reviews }%"
																	class="rating"></div>
															</div>
														</div>
														<div class="price-box">
															<p class="special-price">
																<span class="price"> ${pro.newPrice } VNĐ</span>
															</p>
															<p class="old-price">
																<span class="price-sep"> </span> <span class="price">
																	${pro.oldPrice } VNĐ </span>
															</p>
														</div>
													</div>
												</div>
												<div class="actions">
													<button class="button btn-cart" title="Thêm vào giỏ hàng"
														type="button">
														<span onclick="cart(${pro.id}, 'ADD',this)">Thêm
															Vào</span>
													</button>
												</div>
												<div class="clearfix"></div>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- End main container  -->

<!-- Recommend slider  -->
<section class="recommend container">
	<div class="new-pro-slider small-pr-slider" style="overflow: visible">
		<div class="slider-items-products">
			<div class="new_title center">
				<h2>Sản phẩm được quan tâm</h2>
			</div>
			<div id="recommend-slider" class="product-flexslider hidden-buttons">
				<div class="slider-items slider-width-col3">
					<c:forEach var="pro" items="${requestScope.views }">
						<div class="item">
							<div class="col-item">
								<div class="images-container">
									<a class="product-image" title="Sample Product"
										href="${pageContext.request.contextPath }/view?id=${pro.id }"> <img
										src="${pageContext.request.contextPath }/resources${pro.urlImage}"
										class="img-responsive" alt="a" />
									</a>
									<div class="actions">
										<div class="actions-inner">
											<ul class="add-to-links">
												<li><a style="cursor: pointer;"
													onclick="like(${pro.id})"
													title="Thêm vào danh sách ưu thích" class="link-wishlist"><span>Thích</span></a></li>
												<li><a style="cursor: pointer;"
													title="Thêm vào để so sánh" onclick="addCompare(${pro.id})"
													class="link-compare "><span>So Sánh</span></a></li>
											</ul>
										</div>
									</div>
									<div class="qv-button-container">
										<a href="${pageContext.request.contextPath }/view?id=${pro.id }" class="qv-e-button btn-quickview-1">
											<span><span>Xem Nhanh</span></span>
										</a>
									</div>
								</div>
								<div class="info">
									<div class="info-inner">
										<div class="item-title">
											<a title="${pro.name }" href="${pageContext.request.contextPath }/view?id=${pro.id }">${pro.name }</a>
										</div>
										<div class="item-content">
											<div class="ratings">
												<div class="rating-box">
													<div style="width:${(100 / 5) * pro.reviews }%"
														class="rating"></div>
												</div>
											</div>
											<div class="price-box">
												<p class="special-price">
													<span class="price"> ${pro.newPrice } VNĐ </span>
												</p>
												<p class="old-price">
													<span class="price-sep"> </span> <span class="price">
														${pro.oldPrice } VNĐ </span>
												</p>
											</div>
										</div>
									</div>
									<div class="actions">
										<button type="button" title="Add to Cart"
											class="button btn-cart">
											<span onclick="cart(${pro.id}, 'ADD',this)">Thêm Vào</span>
										</button>
									</div>
									<div class="clearfix"></div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- End Recommend slider  -->
<!-- banner section  -->
<div class="top-offer-banner wow">
	<div class="container">
		<div class="row">
			<div class="offer-inner col-lg-12">
				<!-- newsletter-wrap  -->
				<div class="block-subscribe">
					<div class="newsletter">
						<h4>
							<span>Đăng Ký Inspire</span>
						</h4>
						<h5>Nhận tin mới nhất được cập nhật từ Inspire</h5>
						<input type="text" placeholder="Địa chỉ email của bạn"
							class="input-text required-entry validate-email"
							title="Sign up for our newsletter" id="newsletter1">
						<button onclick="reg();" class="subscribe" title="Subscribe">
							<span>Đăng Ký</span>
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- End banner section  -->

<!-- middle slider  -->
<section class="middle-slider container">
	<div class="row">
		<div class="col-sm-4 custom-slider">
			<div>
				<div id="carousel-example-generic" class="carousel slide"
					data-ride="carousel">
					<!-- Indicators  -->
					<ol class="carousel-indicators">
						<%
							@SuppressWarnings("unchecked")
							List<Product> listRand = (List<Product>) request
									.getAttribute("rands");
							for (int i = 0; i < listRand.size(); i++) {
								Product pro = listRand.get(i);
								if (i == 0) {
						%>
						<li data-target="#carousel-example-generic" data-slide-to="<%=i%>"
							class="active"></li>
						<%
							} else {
						%>
						<li data-target="#carousel-example-generic" data-slide-to="<%=i%>"></li>
						<%
							}
							}
						%>
					</ol>

					<!-- Wrapper for slides  -->
					<div class="carousel-inner" role="listbox">
						<%
							for (int i = 0; i < listRand.size(); i++) {
								Product pro = listRand.get(i);
								if (i == 0) {
						%>
						<div class="item active">
							<img
								src="${pageContext.request.contextPath }/resources<%= pro.getUrlImage() %>"
								alt="slide1">
							<div class="carousel-caption">
								<h3>
									<a href="${pageContext.request.contextPath }/view?id=<%=pro.getId()%>" title="<%=pro.getName()%>"><%=pro.getName()%></a>
								</h3>
								<p><%=pro.getDescribe()%></p>
							</div>
						</div>
						<%
							} else {
						%>
						<div class="item">
							<img
								src="${pageContext.request.contextPath }/resources<%= pro.getUrlImage() %>"
								alt="slide1">
							<div class="carousel-caption">
								<h3>
									<a href="${pageContext.request.contextPath }/view?id=<%=pro.getId()%>" title="<%=pro.getName()%>"><%=pro.getName()%></a>
								</h3>
								<p><%=pro.getDescribe()%></p>
							</div>
						</div>
						<%
							}
							}
						%>
					</div>
					<a class="left carousel-control" href="#carousel-example-generic"
						role="button" data-slide="prev"> <span
						class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						<span class="sr-only">Previous</span>
					</a> <a class="right carousel-control" href="#carousel-example-generic"
						role="button" data-slide="next"> <span
						class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						<span class="sr-only">Next</span>
					</a>
				</div>
			</div>
		</div>
		<div class="col-sm-4 pro-block">
			<div class="inner-div">
				<h2 class="category-pro-title">
					<span>Đánh Giá Cao</span>
				</h2>
				<div class="category-products">
					<div class="products small-list">
						<c:forEach var="pro" items="${requestScope.topReviews }">
							<div class="item">
								<div class="item-area">
									<div class="product-image-area">
										<a href="${pageContext.request.contextPath }/view?id=${pro.id }" class="product-image"> <img
											src="${pageContext.request.contextPath }/resources${pro.urlImage}"
											alt="products images">
										</a>
									</div>
									<div class="details-area">
										<h2 class="product-name">
											<a href="${pageContext.request.contextPath }/view?id=${pro.id }">${pro.name }</a>
										</h2>
										<div class="ratings">
											<div class="rating-box">
												<div style="width: ${(100 / 5) * pro.reviews }%"
													class="rating"></div>
											</div>
										</div>
										<div class="price-box">
											<span class="regular-price"> <span class="price">${pro.newPrice }
													VNĐ</span></span>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-4 pro-block1">
			<div class="inner-div1">
				<h2 class="category-pro-title">
					<span>Mới</span>
				</h2>
				<div class="category-products">
					<div class="products small-list">
						<c:forEach var="pro" items="${requestScope.topLatest }">
							<div class="item">
								<div class="item-area">
									<div class="product-image-area">
										<a href="${pageContext.request.contextPath }/view?id=${pro.id }" class="product-image"> <img
											src="${pageContext.request.contextPath }/resources${pro.urlImage}"
											alt="products images">
										</a>
									</div>
									<div class="details-area">
										<h2 class="product-name">
											<a href="${pageContext.request.contextPath }/view?id=${pro.id }">${pro.name }</a>
										</h2>
										<div class="ratings">
											<div class="rating-box">
												<div style="width: ${(100 / 5) * pro.reviews }%"
													class="rating"></div>
											</div>
										</div>
										<div class="price-box">
											<p class="old-price">
												<span class="price-label">Regular Price:</span> <span
													class="price">${pro.oldPrice } VNĐ </span>
											</p>
											<p class="special-price">
												<span class="price-label">Special Price</span> <span
													class="price">${pro.newPrice } VNĐ </span>
											</p>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!--  		End middle slider  -->

<!--  		Featured Slider  -->
<section class="featured-pro wow animated parallax parallax-2">
	<div class="container">
		<div class="std">
			<div class="slider-items-products">
				<div class="featured_title center">
					<h2>Sản Phẩm Khuyến Mại</h2>
				</div>
				<div id="featured-slider" class="product-flexslider hidden-buttons">
					<div class="slider-items slider-width-col4">
						<c:forEach var="pro" items="${promotions }">
							<div class="item">
								<div class="col-item">
									<div class="sale-label sale-top-right">Sale</div>
									<div class="images-container">
										<a class="product-image" title="Sample Product"
											href="${pageContext.request.contextPath }/view?id=${pro.id }"> <img
											src="${pageContext.request.contextPath }/resources${pro.urlImage}"
											class="img-responsive" alt="a" />
										</a>
										<div class="actions">
											<div class="actions-inner">
												<button type="button" title="Thêm vào giỏ hàng"
													class="button btn-cart">
													<span onclick="cart(${pro.id}, 'ADD',this)">Thêm Vào</span>
												</button>
												<ul class="add-to-links">
													<li><a style="cursor: pointer;"
														onclick="like(${pro.id})"
														title="Thêm vào danh sách ưu thích" class="link-wishlist"><span>Thích</span></a></li>
													<li><a style="cursor: pointer;"
														title="Thêm vào danh sách so sánh"
														onclick="addCompare(${pro.id})" class="link-compare "><span>So
																Sánh</span></a></li>
												</ul>
											</div>
										</div>
										<div class="qv-button-container">
											<a href="${pageContext.request.contextPath }/view?id=${pro.id }" class="qv-e-button btn-quickview-1">
												<span><span>Xem Nhanh</span></span>
											</a>
										</div>
									</div>
									<div class="info">
										<div class="info-inner">
											<div class="item-title">
												<a title="${pro.name }" href="${pageContext.request.contextPath }/view?id=${pro.id }">${pro.name }</a>
											</div>
											<div class="item-content">
												<div class="ratings">
													<div class="rating-box">
														<div style="width: ${(100 / 5) * pro.reviews }%"
															class="rating"></div>
													</div>
												</div>
												<div class="price-box">
													<p class="special-price">
														<span class="price"> ${pro.newPrice } VNĐ </span>
													</p>
													<p class="old-price">
														<span class="price-sep">-</span> <span class="price">
															${pro.oldPrice } VNĐ </span>
													</p>
												</div>
											</div>
										</div>
										<div class="clearfix"></div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!--  		End Featured Slider  -->
