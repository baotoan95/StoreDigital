<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Facebook JDK -->
<div id="fb-root"></div>
<script>
	(function(d, s, id) {
		var js, fjs = d.getElementsByTagName(s)[0];
		if (d.getElementById(id))
			return;
		js = d.createElement(s);
		js.id = id;
		js.src = "//connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v2.8&appId=1664871357062161";
		fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));
</script>
<!-- End facebook JDK -->
<footer class="footer">
	<div class="brand-logo ">
		<div class="container">
			<div class="slider-items-products">
				<div id="brand-logo-slider"
					class="product-flexslider hidden-buttons">
					<div class="slider-items slider-width-col6">
						<div class="item">
							<a href="#x"><img
								src="<c:url value="/resources/images/b-logo1.png"/>" alt="Image"></a>
						</div>
						<div class="item">
							<a href="#x"><img
								src="<c:url value="/resources/images/b-logo2.png"/>" alt="Image"></a>
						</div>
						<div class="item">
							<a href="#x"><img
								src="<c:url value="/resources/images/b-logo3.png"/>" alt="Image"></a>
						</div>
						<div class="item">
							<a href="#x"><img
								src="<c:url value="/resources/images/b-logo4.png"/>" alt="Image"></a>
						</div>
						<div class="item">
							<a href="#x"><img
								src="<c:url value="/resources/images/b-logo5.png"/>" alt="Image"></a>
						</div>
						<div class="item">
							<a href="#x"><img
								src="<c:url value="/resources/images/b-logo6.png"/>" alt="Image"></a>
						</div>
						<div class="item">
							<a href="#x"><img
								src="<c:url value="/resources/images/b-logo1.png"/>" alt="Image"></a>
						</div>
						<div class="item">
							<a href="#x"><img
								src="<c:url value="/resources/images/b-logo4.png"/>" alt="Image"></a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="footer-middle container">
		<div class="col-md-3 col-sm-4">
			<div class="footer-logo">
				<a href="index.html" title="Logo"><img
					src="<c:url value="/resources/images/footer-logo.png"/>" alt="logo"></a>
			</div>
			<p>Món quà cho mọi nhà</p>
			<div class="payment-accept">
				<div>
					<img src="<c:url value="/resources/images/payment-1.png"/>"
						alt="payment"> <img
						src="<c:url value="/resources/images/payment-2.png"/>"
						alt="payment"> <img
						src="<c:url value="/resources/images/payment-3.png"/>"
						alt="payment"> <img
						src="<c:url value="/resources/images/payment-4.png"/>"
						alt="payment">
				</div>
			</div>
		</div>
		<div class="col-md-2 col-sm-4">
			<h4>Hướng Dẫn</h4>
			<ul class="links">
				<li><a href="faq" title="FAQs">Hỏi đáp</a></li>
				<li><a href="delivery" title="delivery">Giao hàng</a></li>
			</ul>
		</div>
		<div class="col-md-2 col-sm-4">
			<h4>Người Dùng</h4>
			<ul class="links">
				<li class="first"><a title="Login" href="login">Đăng nhập</a></li>
				<li><a title="Information" href="profile">Đơn hàng đã đặt</a></li>
			</ul>
			<br />
			<div class="fb-page"
				data-href="https://www.facebook.com/StoreDigital-1614227788832273/"
				data-small-header="true" data-adapt-container-width="true"
				data-hide-cover="true" data-show-facepile="true">
				<blockquote
					cite="https://www.facebook.com/StoreDigital-1614227788832273/"
					class="fb-xfbml-parse-ignore">
					<a href="https://www.facebook.com/StoreDigital-1614227788832273/">StoreDigital</a>
				</blockquote>
			</div>
		</div>
		<div class="col-md-2 col-sm-4">
			<h4>Tiện Ích</h4>
			<ul class="links">
				<li class="first"><a href="sitemap" title="Site Map">Site
						Map</a></li>
			</ul>
		</div>
		<div class="col-md-3 col-sm-4">
			<h4>Liên Hệ</h4>
			<div class="contacts-info">
				<address>
					<i class="add-icon">&nbsp;</i>Dĩ An - Bình Dương, <br>
					&nbsp;Việt Nam
				</address>
				<div class="phone-footer">
					<i class="phone-icon">&nbsp;</i> +1 800 123 1234
				</div>
				<div class="email-footer">
					<i class="email-icon">&nbsp;</i> <a
						href="mailto:support@magikcommerce.com">support@inspire.com</a>
				</div>
			</div>
		</div>
	</div>
	<div class="footer-bottom container">
		<div class="col-sm-5 col-xs-12 coppyright">&copy; 2015 BTIT95.
			All Rights Reserved.</div>
		<div class="col-sm-7 col-xs-12 company-links">
			<ul class="links">
				<li><a href="http://www.google.com" title="Goole">Google</a></li>
				<li><a href="http://www.youtube.com" title="Youtube">Youtube</a></li>
				<li><a href="http://www.facebook.com" title="Facebook">Facebook</a></li>
			</ul>
		</div>
	</div>
</footer>
<!-- Notification -->
<div id="notification-container"></div>
<div id="sound"></div>
<div class="social">
	<ul>
		<li class="fb"><a
			href="https://www.facebook.com/StoreDigital-1614227788832273"></a></li>
		<!--  			<li class="tw"><a href="#"></a></li>  -->
		<!--  			<li class="googleplus"><a href="#"></a></li>  -->
		<!--  			<li class="rss"><a href="#"></a></li>  -->
		<!--  			<li class="pintrest"><a href="#"></a></li>  -->
		<!--  			<li class="linkedin"><a href="#"></a></li>  -->
		<!--  			<li class="youtube"><a href="#"></a></li>  -->
	</ul>
</div>

<script type='text/javascript'>
	jQuery(document).ready(function() {
		jQuery('#rev_slider_4').show().revolution({
			dottedOverlay : 'none',
			delay : 5000,
			startwidth : 1170,
			startheight : 580,

			hideThumbs : 200,
			thumbWidth : 200,
			thumbHeight : 50,
			thumbAmount : 2,

			navigationType : 'thumb',
			navigationArrows : 'solo',
			navigationStyle : 'round',

			touchenabled : 'on',
			onHoverStop : 'on',

			swipe_velocity : 0.7,
			swipe_min_touches : 1,
			swipe_max_touches : 1,
			drag_block_vertical : false,

			spinner : 'spinner0',
			keyboardNavigation : 'off',

			navigationHAlign : 'center',
			navigationVAlign : 'bottom',
			navigationHOffset : 0,
			navigationVOffset : 20,

			soloArrowLeftHalign : 'left',
			soloArrowLeftValign : 'center',
			soloArrowLeftHOffset : 20,
			soloArrowLeftVOffset : 0,

			soloArrowRightHalign : 'right',
			soloArrowRightValign : 'center',
			soloArrowRightHOffset : 20,
			soloArrowRightVOffset : 0,

			shadow : 0,
			fullWidth : 'on',
			fullScreen : 'off',

			stopLoop : 'off',
			stopAfterLoops : -1,
			stopAtSlide : -1,

			shuffle : 'off',

			autoHeight : 'off',
			forceFullWidth : 'on',
			fullScreenAlignForce : 'off',
			minFullScreenHeight : 0,
			hideNavDelayOnMobile : 1500,

			hideThumbsOnMobile : 'off',
			hideBulletsOnMobile : 'off',
			hideArrowsOnMobile : 'off',
			hideThumbsUnderResolution : 0,

			hideSliderAtLimit : 0,
			hideCaptionAtLimit : 0,
			hideAllCaptionAtLilmit : 0,
			startWithSlide : 0,
			fullScreenOffsetContainer : ''
		});
	});
</script>