<%@page import="javax.xml.soap.Detail"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="com.baotoan.spring.entities.DetailProduct"%>
<%@page import="java.util.Map"%>
<%@page import="com.baotoan.spring.dao.PostDAOImpl"%>
<%@page import="com.baotoan.spring.entities.Post"%>
<%@page import="com.baotoan.spring.dao.PostDAO"%>
<%@page import="com.baotoan.spring.entities.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.baotoan.spring.dao.ImageDAO"%>
<%@page import="com.baotoan.spring.dao.ImageDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Facebook JDK -->
<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v2.4&appId=1664871357062161";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>
<!-- Breadcrumbs -->
<div class="breadcrumbs">
	<div class="container">
		<div class="row">
			<ul>
				<li class="home"><a title="Go to Home Page"
					href="${pageContext.request.contextPath }/index">Home</a><span>&mdash;>></span></li>
				<li class="category13"><strong>Chi tiết sản phẩm </strong></li>
			</ul>
		</div>
	</div>
</div>
<!-- main-container -->
<section class="main-container col1-layout">
	<div class="main container">
		<div class="col-main">
			<div class="row">
				<div class="product-view wow">
					<div class="product-essential">
						<form action="#" method="post" id="product_addtocart_form">
							<div class="product-img-box col-lg-6 col-sm-6 col-xs-12">
								<ul class="moreview" id="moreview">
									
									<c:forEach var="image" items="${requestScope.images }">
										<li class="moreview_thumb thumb_1"><img
											class="moreview_thumb_image"
											src="<c:url value="/resources${image.url }"/>"> <img
											class="moreview_source_image"
											src="<c:url value="/resources${image.url }"/>" alt="">
											<span class="roll-over">Roll over image to zoom in</span> <img
											style="position: absolute;" class="zoomImg"
											src="<c:url value="/resources${image.url }"/>"></li>
									</c:forEach>
								</ul>
								<div class="moreview-control">
									<a style="right: 42px;" href="javascript:void(0)"
										class="moreview-prev"></a> <a style="right: 42px;"
										href="javascript:void(0)" class="moreview-next"></a>
								</div>
							</div>

							<!-- end: more-images -->
							<div class="product-shop col-lg-6 col-sm-6 col-xs-12">
								<div class="product-name">
									<h1>${requestScope.product.name }</h1>
								</div>
								<div class="ratings">
									<div class="rating-box">
										<div
											style="width:${(100 / 5) * requestScope.product.reviews }%"
											class="rating"></div>
									</div>
								</div>
								<p class="availability in-stock">
									<span>Còn Hàng</span>
								</p>
								<div class="price-block">
									<div class="price-box">
										<p class="old-price">
											<span class="price"> ${requestScope.product.oldPrice }
												VNĐ</span>
										</p>
										<p class="special-price">
											<span class="price"> ${requestScope.product.newPrice }
												VNĐ </span>
										</p>
									</div>
								</div>
								<div class="short-description">
									<p>${requestScope.product.describe }</p>
								</div>
								<div class="add-to-box">
									<div class="add-to-cart">
										<br />
										<button
											onClick="cart(${requestScope.product.id}, 'ADD', this)"
											class="button btn-cart" title="Thêm vào giỏ hàng"
											type="button">
											<span><i class="icon-basket"></i>Thêm vào giỏ hàng</span>
										</button>
									</div>
									<br /> <br /> <br />
									<div class="email-addto-box">
										<ul class="add-to-links">
											<li><a class="link-wishlist" style="cursor: pointer;"
												onclick="like(${requestScope.product.id})"><span>Thích</span></a></li>
											<li><span class="separator">|</span> <a
												class="link-compare"
												onclick="addCompare(${requestScope.product.id})"><span>So
														sánh</span></a></li>
										</ul>
										<p class="email-friend">
											<a href="#" class=""><span>Thông tin khuyến mãi</span></a>
										</p>
									</div>
								</div>
								<div class="custom-box">
									<img alt="banner"
										src="<c:url value="/resources/images/cus-img.png"/>">
								</div>
							</div>
						</form>
					</div>
					<div class="product-collateral">
						<div class="col-sm-12 wow">
							<ul id="product-detail-tab" class="nav nav-tabs product-tabs">
								<li class="active"><a href="#product_tabs_description"
									data-toggle="tab"> Bài Viết </a></li>
								<li><a href="#digital" data-toggle="tab">Thông Số Kĩ
										Thuật</a></li>
								<li><a href="#product_tabs_tags" data-toggle="tab">Tags</a></li>
								<li><a href="#reviews_tabs" data-toggle="tab">Đánh giá</a>
								</li>
								<li><a href="#product_tabs_custom" data-toggle="tab">Bình Luận</a></li>
							</ul>
							<div id="productTabContent" class="tab-content">
								<div class="tab-pane fade in active"
									id="product_tabs_description">
									<div class="std">
										<%
											Product pro = (Product) request.getAttribute("product");
											Post post = new PostDAOImpl().getPostById(pro.getId());
										%>
										<p class="posttitle" style="font-size: 30px; font-weight: bolt; text-align: center;"><%=post.getTitle()%></p>
										<%=post.getContent()%>
										<br/><br/>
										<p class="date" style="text-align: right; color: blue;">Ngày đăng: <%=post.getPublishDate()%></p>
										<p class="author" style="text-align: right; color: blue;">Tác giả: <%=post.getAuthor()%></p>
									</div>
								</div>
								<div class="tab-pane fade" id="digital">
									<div class="table-responsive">
										<table class="table table-striped compare-table">
											<colgroup>
												<col width="5">
												<col class="widthCol" width="100%">
											</colgroup>
											<tbody>
												<%
													Map<String, Map<String, DetailProduct>> detail = pro.getDetail();
													Set<String> groups = detail.keySet();
													Iterator<String> groupNames = groups.iterator();
													while (groupNames.hasNext()) {
														String groupName = groupNames.next();
												%>
												<tr class="even">
													<th class="groupName"
														style="background: #eb8415; color: white;" colspan="2"><%=groupName%></th>
												</tr>
												<%
													Map<String, DetailProduct> details = detail.get(groupName);
														Set<String> names = details.keySet();
														Iterator<String> detailNames = names.iterator();
														while (detailNames.hasNext()) {
															String detailName = detailNames.next();
															DetailProduct detailPro = details.get(detailName);
												%>
												<tr class="odd">
													<th><%=detailName%></th>
													<td><div>
															<%=detailPro.getValue()%>
														</div></td>
												</tr>
												<%
													}
													}
												%>
											</tbody>
										</table>
									</div>
								</div>
								<div class="tab-pane fade" id="product_tabs_tags">
									<div class="box-collateral box-tags">
										<div class="tags">
											<form id="addTagForm" action="#" method="get">
												<div class="form-add-tags">
													<label for="productTagName">Add Tags:</label>
													<div class="input-box">
														<input class="input-text required-entry"
															name="productTagName" id="productTagName" type="text"
															style="width: 35%;"
															value="${requestScope.product.tags }'">
														<button type="button" title="Add Tags"
															class=" button btn-add" onClick="submitTagForm()">
															<span>Add Tags</span>
														</button>
													</div>
												</div>
											</form>
										</div>
										<p class="note">Use spaces to separate tags. Use single
											quotes (') for phrases.</p>
									</div>
								</div>
								<div class="tab-pane fade" id="reviews_tabs">
									<div class="box-collateral box-reviews" id="customer-reviews">
										<div class="box-reviews1">
											<div class="form-add">
												<form id="review-form" method="post" action="#">
													<h3>Write Your Own Review</h3>
													<fieldset>
														<h4>
															How do you rate this product? <em class="required">*</em>
														</h4>
														<span id="input-message-box"></span>
														<table id="product-review-table" class="data-table">
															<colgroup>
																<col>
																<col width="1">
																<col width="1">
																<col width="1">
																<col width="1">
																<col width="1">
															</colgroup>
															<thead>
																<tr class="first last">
																	<th>&nbsp;</th>
																	<th><span class="nobr">1 *</span></th>
																	<th><span class="nobr">2 *</span></th>
																	<th><span class="nobr">3 *</span></th>
																	<th><span class="nobr">4 *</span></th>
																	<th><span class="nobr">5 *</span></th>
																</tr>
															</thead>
															<tbody>
																<tr class="first odd">
																	<th>Price</th>
																	<td class="value"><input type="radio"
																		class="radio" value="11" id="Price_1"
																		name="ratings[3]"></td>
																	<td class="value"><input type="radio"
																		class="radio" value="12" id="Price_2"
																		name="ratings[3]"></td>
																	<td class="value"><input type="radio"
																		class="radio" value="13" id="Price_3"
																		name="ratings[3]"></td>
																	<td class="value"><input type="radio"
																		class="radio" value="14" id="Price_4"
																		name="ratings[3]"></td>
																	<td class="value last"><input type="radio"
																		class="radio" value="15" id="Price_5"
																		name="ratings[3]"></td>
																</tr>
																<tr class="even">
																	<th>Value</th>
																	<td class="value"><input type="radio"
																		class="radio" value="6" id="Value_1" name="ratings[2]"></td>
																	<td class="value"><input type="radio"
																		class="radio" value="7" id="Value_2" name="ratings[2]"></td>
																	<td class="value"><input type="radio"
																		class="radio" value="8" id="Value_3" name="ratings[2]"></td>
																	<td class="value"><input type="radio"
																		class="radio" value="9" id="Value_4" name="ratings[2]"></td>
																	<td class="value last"><input type="radio"
																		class="radio" value="10" id="Value_5"
																		name="ratings[2]"></td>
																</tr>
																<tr class="last odd">
																	<th>Quality</th>
																	<td class="value"><input type="radio"
																		class="radio" value="1" id="Quality_1"
																		name="ratings[1]"></td>
																	<td class="value"><input type="radio"
																		class="radio" value="2" id="Quality_2"
																		name="ratings[1]"></td>
																	<td class="value"><input type="radio"
																		class="radio" value="3" id="Quality_3"
																		name="ratings[1]"></td>
																	<td class="value"><input type="radio"
																		class="radio" value="4" id="Quality_4"
																		name="ratings[1]"></td>
																	<td class="value last"><input type="radio"
																		class="radio" value="5" id="Quality_5"
																		name="ratings[1]"></td>
																</tr>
															</tbody>
														</table>
														<input type="hidden" value="" class="validate-rating"
															name="validate_rating">
														<div class="review1">
															<ul class="form-list">
																<li><label class="required" for="nickname_field">Nickname<em>*</em></label>
																	<div class="input-box">
																		<input type="text" class="input-text required-entry"
																			id="nickname_field" name="nickname">
																	</div></li>
																<li><label class="required" for="summary_field">Summary<em>*</em></label>
																	<div class="input-box">
																		<input type="text" class="input-text required-entry"
																			id="summary_field" name="title">
																	</div></li>
															</ul>
														</div>
														<div class="review2">
															<ul>
																<li><label class="required label-wide"
																	for="review_field">Review<em>*</em></label>
																	<div class="input-box">
																		<textarea class="required-entry" rows="3" cols="5"
																			id="review_field" name="detail"></textarea>
																	</div></li>
															</ul>
															<div class="buttons-set">
																<button class="button submit" title="Submit Review"
																	type="submit">
																	<span>Submit Review</span>
																</button>
															</div>
														</div>
													</fieldset>
												</form>
											</div>
										</div>
										<div class="box-reviews2">
											<h3>Customer Reviews</h3>
											<div class="box visible">
												<ul>
													<li>
														<table class="ratings-table">
															<colgroup>
																<col width="1">
																<col>
															</colgroup>
															<tbody>
																<tr>
																	<th>Value</th>
																	<td><div class="rating-box">
																			<div class="rating" style="width: 100%;"></div>
																		</div></td>
																</tr>
																<tr>
																	<th>Quality</th>
																	<td><div class="rating-box">
																			<div class="rating" style="width: 100%;"></div>
																		</div></td>
																</tr>
																<tr>
																	<th>Price</th>
																	<td><div class="rating-box">
																			<div class="rating" style="width: 100%;"></div>
																		</div></td>
																</tr>
															</tbody>
														</table>
														<div class="review">
															<h6>
																<a href="#/catalog/product/view/id/61/">Excellent</a>
															</h6>
															<small>Review by <span>Leslie Prichard </span>on
																1/3/2014
															</small>
															<div class="review-txt">I have purchased shirts
																from Minimalism a few times and am never disappointed.
																The quality is excellent and the shipping is amazing. It
																seems like it's at your front door the minute you get
																off your pc. I have received my purchases within two
																days - amazing.</div>
														</div>
													</li>
													<li class="even">
														<table class="ratings-table">
															<colgroup>
																<col width="1">
																<col>
															</colgroup>
															<tbody>
																<tr>
																	<th>Value</th>
																	<td><div class="rating-box">
																			<div class="rating" style="width: 100%;"></div>
																		</div></td>
																</tr>
																<tr>
																	<th>Quality</th>
																	<td><div class="rating-box">
																			<div class="rating" style="width: 100%;"></div>
																		</div></td>
																</tr>
																<tr>
																	<th>Price</th>
																	<td><div class="rating-box">
																			<div class="rating" style="width: 80%;"></div>
																		</div></td>
																</tr>
															</tbody>
														</table>
														<div class="review">
															<h6>
																<a href="#/catalog/product/view/id/60/">Amazing</a>
															</h6>
															<small>Review by <span>Sandra Parker</span>on
																1/3/2014
															</small>
															<div class="review-txt">Minimalism is the online !
															</div>
														</div>
													</li>
													<li>
														<table class="ratings-table">
															<colgroup>
																<col width="1">
																<col>
															</colgroup>
															<tbody>
																<tr>
																	<th>Value</th>
																	<td><div class="rating-box">
																			<div class="rating" style="width: 100%;"></div>
																		</div></td>
																</tr>
																<tr>
																	<th>Quality</th>
																	<td><div class="rating-box">
																			<div class="rating" style="width: 100%;"></div>
																		</div></td>
																</tr>
																<tr>
																	<th>Price</th>
																	<td><div class="rating-box">
																			<div class="rating" style="width: 80%;"></div>
																		</div></td>
																</tr>
															</tbody>
														</table>
														<div class="review">
															<h6>
																<a href="#/catalog/product/view/id/59/">Nicely</a>
															</h6>
															<small>Review by <span>Anthony Lewis</span>on
																1/3/2014
															</small>
															<div class="review-txt">Unbeatable service and
																selection. This store has the best business model I have
																seen on the net. They are true to their word, and go the
																extra mile for their customers. I felt like a purchasing
																partner more than a customer. You have a lifetime client
																in me.</div>
														</div>
													</li>
												</ul>
											</div>
											<div class="actions">
												<a class="button view-all" id="revies-button"><span><span>View
															all</span></span></a>
											</div>
										</div>
										<div class="clear"></div>
									</div>
								</div>
								<div class="tab-pane fade" id="product_tabs_custom">
									<div class="product-tabs-content-inner clearfix" style="text-align: center;">
										<div class="fb-comments" data-href="" data-width="800" data-numposts="10"></div>
									</div>
								</div>

							</div>
						</div>
						<div class="col-sm-12">
							<div class="box-additional">
								<div class="related-pro wow">
									<div class="slider-items-products">
										<div class="new_title center">
											<h2>Sản phẩm liên quan</h2>
										</div>
										<div id="related-products-slider"
											class="product-flexslider hidden-buttons">
											<div class="slider-items slider-width-col4">
												<c:forEach var="pro" items="${requestScope.relateds }">
													<div class="item">
														<div class="col-item">
															<div class="sale-label sale-top-right">Sale</div>
															<div class="images-container">
																<a class="product-image" title="${pro.name }"
																	href="view?id=${pro.id }"> <img
																	src="${pageContext.request.contextPath }/resources${pro.urlImage}"
																	class="img-responsive" alt="a" />
																</a>
																<div class="actions">
																	<div class="actions-inner">
																		<button onclick="cart(${pro.id}, 'ADD', this)"
																			type="button" title="Thêm vào giỏ hàng"
																			class="button btn-cart">
																			<span>Thêm Vào</span>
																		</button>
																		<ul class="add-to-links">
																			<li><a style="cursor: pointer;"
																				onclick="like(${pro.id})" title="Add to Wishlist"
																				class="link-wishlist"><span>Thích</span></a></li>
																			<li><a style="cursor: pointer;"
																				onclick="addCompare(${pro.id})"
																				title="Add to Compare" class="link-compare"><span>So
																						Sánh</span></a></li>
																		</ul>
																	</div>
																</div>
																<div class="qv-button-container">
																	<a class="qv-e-button btn-quickview-1" href="${pageContext.request.contextPath }/view?id=${pro.id }"><span><span>Xem
																				Nhanh</span></span></a>
																</div>
															</div>
															<div class="info">
																<div class="info-inner">
																	<div class="item-title">
																		<a href="view?id=${pro.id }" title="${pro.name }">${pro.name }</a>
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
																				<span class="price">${pro.newPrice } VNĐ </span>
																			</p>
																			<p class="old-price">
																				<span class="price-sep">-</span> <span class="price">${pro.oldPrice } VNĐ </span>
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
								<div class="upsell-pro wow">
									<div class="slider-items-products">
										<div class="new_title center">
											<h2>Nên quan tâm</h2>
										</div>
										<div id="upsell-products-slider"
											class="product-flexslider hidden-buttons">
											<div class="slider-items slider-width-col4">
												<c:forEach var="pro" items="${requestScope.upsells }">
													<div class="item">
														<div class="col-item">
															<div class="sale-label sale-top-right">Sale</div>
															<div class="images-container">
																<a class="product-image" title="${pro.name }"
																	href="view?id=${pro.id }"> <img
																	src="${pageContext.request.contextPath }/resources${pro.urlImage}"
																	class="img-responsive" alt="a" />
																</a>
																<div class="actions">
																	<div class="actions-inner">
																		<button type="button"
																			onclick="cart(${pro.id}, 'ADD', this)"
																			title="Thêm vào giỏ hàng" class="button btn-cart">
																			<span>Thêm Vào</span>
																		</button>
																		<ul class="add-to-links">
																			<li><a title="Thêm vào danh sách ưu thích"
																				style="cursor: pointer;" onclick="like(${pro.id})"
																				class="link-wishlist"><span>Thích</span></a></li>
																			<li><a title="Thêm vào so sánh"
																				style="cursor: pointer;"
																				onclick="addCompare(${pro.id})" class="link-compare"><span>So
																						Sánh</span></a></li>
																		</ul>
																	</div>
																</div>
																<div class="qv-button-container">
																	<a class="qv-e-button btn-quickview-1" href="${pageContext.request.contextPath }/view?id=${pro.id }"><span><span>Xem
																				Nhanh</span></span></a>
																</div>
															</div>
															<div class="info">
																<div class="info-inner">
																	<div class="item-title">
																		<a href="view?id=${pro.id }" title="${pro.name }">${pro.name }</a>
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
																				<span class="price">${pro.newPrice } VNĐ </span>
																			</p>
																			<p class="old-price">
																				<span class="price-sep">-</span> <span class="price">${pro.oldPrice }
																					VNĐ </span>
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
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>