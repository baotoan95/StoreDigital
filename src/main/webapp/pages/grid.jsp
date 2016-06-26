<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- breadcrumbs -->
<div class="breadcrumbs">
	<div class="container">
		<div class="row">
			<ul>
				<li class="home"><a href="${pageContext.request.contextPath }/index" title="Tới trang chủ">Trang chủ</a><span>&mdash;›</span></li>
				<li class=""><a title="Danh sách sản phẩm">Danh sách sản phẩm</a></li>
			</ul>
		</div>
	</div>
</div>
<!-- End breadcrumbs -->

<!-- Two columns content -->
<section class="main-container col2-left-layout">
	<div class="main container">
		<div class="row">
			<section class="col-main col-sm-9 col-sm-push-3 wow">
				<div class="category-title">
					<h1>Tops &amp; Tees</h1>
				</div>
				<!-- category banner -->
				<div class="category-description std">
					<div class="slider-items-products">
						<div id="category-desc-slider"
							class="product-flexslider hidden-buttons">
							<div class="slider-items slider-width-col1">
								<div class="item">
									<a href="#"><img alt="category-banner"
										src="<c:url value="/resources/images/category-banner-img.jpg"/>"></a>
									<div class="cat-img-title cat-bg cat-box">
										<h2 class="cat-heading">New Fashion 2015</h2>
										<p>Lorem ipsum dolor sit amet, consectetur adipiscing
											elit. Phasellus diam arcu.</p>
									</div>
								</div>
								<div class="item">
									<a href="#x"><img alt="category-banner"
										src="<c:url value="/resources/images/category-banner-img1.jpg"/>"></a>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- category banner -->
				<div class="category-products">
					<form:form id="searchForm" commandName="search"
						action="${pageContext.request.contextPath }/searchad"
						method="POST" style="width: 100%; height: 100%;">
						<form:hidden path="key" />
						<form:hidden path="getBy" />
						<div class="toolbar">
							<div class="sorter">
								<div class="view-mode">
									<span title="Grid" class="button button-active button-grid"></span>
								</div>
							</div>
							<div id="sort-by">
								<label class="left">Sort By: </label>
								<ul>
									<form:select onchange="submitForm(this, 'sort', 0)"
										path="sortBy" class="right-arrow"
										style="padding: 0px; front-size: 12px; height: 28px;">
										<form:option
											selected="${search.sortBy == 0 ? 'selected' : '' }" value="0">=== Chọn ===</form:option>
										<form:option
											selected="${search.sortBy == 12 ? 'selected' : '' }"
											value="12">Tăng Dần</form:option>
										<form:option
											selected="${search.sortBy == 13 ? 'selected' : '' }"
											value="13">Giảm Dần</form:option>
										<form:option
											selected="${search.sortBy == 14 ? 'selected' : '' }"
											value="14">Theo Tên</form:option>
										<form:option
											selected="${search.sortBy == 15 ? 'selected' : '' }"
											value="15">Giá Thấp</form:option>
										<form:option
											selected="${search.sortBy == 16 ? 'selected' : '' }"
											value="16">Giá Trung Bình</form:option>
										<form:option
											selected="${search.sortBy == 17 ? 'selected' : '' }"
											value="17">Giá Cao</form:option>
									</form:select>
								</ul>
								<a class="button-asc left" style="height: 28px;"
									title="Set Descending Direction"><span
									style="color: #999; font-size: 11px;"
									class="glyphicon glyphicon-arrow-up"></span></a>
							</div>
							<div class="pager">
								<div id="limiter">
									<label>View: </label>
									<ul>
										<form:select onchange="submitForm(this, 'view', 0)"
											path="numRecord" class="right-arrow"
											style="padding: 0px; front-size: 12px; height: 28px;">
											<form:option
												selected="${search.numRecord == 15 ? 'selected' : '' }"
												value="15">15</form:option>
											<form:option
												selected="${search.numRecord == 30 ? 'selected' : '' }"
												value="30">30</form:option>
											<form:option
												selected="${search.numRecord == 45 ? 'selected' : '' }"
												value="45">45</form:option>
										</form:select>
									</ul>
								</div>
								<div class="pages">
									<label>Page:</label>
									<ul class="pagination">
										<form:hidden path="currentPage" id="currentPage" />
										<%= request.getAttribute("pagination") %>
									</ul>
								</div>
							</div>
						</div>
					</form:form>
					<ul class="products-grid" style="clear: both;">
						<c:forEach var="product" items="${requestScope.result }">
						<li class="item col-lg-4 col-md-4 col-sm-6 col-xs-6">
							<div class="col-item">
								<div class="sale-label sale-top-right">Sale</div>
								<div class="images-container">
									<a class="product-image" title="Sample Product"
										href="${pageContext.request.contextPath }/view?id=${product.id }"> <img
										src="${pageContext.request.contextPath }/resources${product.urlImage}"
										class="img-responsive" alt="a" />
									</a>
									<div class="actions">
										<div class="actions-inner">
											<button type="button"
												onclick="cart(${product.id}, 'ADD', this)"
												title="Thêm vào giỏ hàng" class="button btn-cart">
												<span>Thêm Vào</span>
											</button>
											<ul class="add-to-links">
												<li><a onclick="like(${product.id })"
													title="Thêm vào danh sách ưa thích" class="link-wishlist"><span>Thích</span></a></li>
												<li><a title="Thêm vào để so sánh" onclick="addCompare(${product.id })"
													class="link-compare "><span>So Sánh</span></a></li>
											</ul>
										</div>
									</div>
									<div class="qv-button-container">
										<a class="qv-e-button btn-quickview-1" href="${pageContext.request.contextPath }/view?id=${product.id }"><span><span>Xem
													Nhanh</span></span></a>
									</div>
								</div>
								<div class="info">
									<div class="info-inner">
										<div class="item-title">
											<a title="${product.name }"
												href="view?id=${product.id }"> ${product.name }
											</a>
										</div>
										<div class="item-content">
											<div class="ratings">
												<div class="rating-box">
													<div style="width: 60%" class="rating"></div>
												</div>
											</div>
											<div class="price-box">
												<p class="special-price">
													<span class="price"> ${product.newPrice } VNĐ
													</span>
												</p>
												<p class="old-price">
													<span class="price-sep">-</span> <span class="price">
														${product.oldPrice } VNĐ
													</span>
												</p>
											</div>
										</div>
									</div>
									<div class="clearfix"></div>
								</div>
							</div>
						</li>
						</c:forEach>
					</ul>
				</div>
			</section>
			<aside class="col-left sidebar col-sm-3 col-xs-12 col-sm-pull-9 wow">
				<div class="block block-subscribe">
					<div class="block-title">
						<span>Newsletter</span>
					</div>
						<div class="block-content">
							<div class="form-subscribe-header">Đăng ký nhận tin từ StoreDigital</div>
							<input type="text" name="email" id="newsletter1" title=""
								class="input-text required-entry validate-email"
								placeholder="Nhập địa chỉ email của bạn">
							<div class="actions">
								<button onclick="reg();" title="Submit" class="subscribe">
									<span>Đăng Ký</span>
								</button>
							</div>
						</div>
				</div>
				<div class="block block-banner">
					<a><img src="<c:url value="/resources/images/block-banner.png"/>" alt="block-banner"></a>
				</div>
			</aside>
		</div>
	</div>
</section>