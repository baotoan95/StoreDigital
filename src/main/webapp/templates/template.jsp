<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html> 
<html lang="en"> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
<meta name="description" content=""> 
<meta name="author" content=""> 
<!--  Favicons Icon  -->
<link rel="icon" href="http:demo.magikthemes.com/skin/frontend/base/default/favicon.ico" type="image/x-icon" /> 
<link rel="shortcut icon" href="http:demo.magikthemes.com/skin/frontend/base/default/favicon.ico" type="image/x-icon" /> 
<!--  Mobile Specific  -->
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"> 
<!--  CSS Style  -->
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>" type="text/css"> 
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>" type="text/css">
<link rel="stylesheet" href="<c:url value="/resources/css/revslider.css"/>" type="text/css"> 
<link rel="stylesheet" href="<c:url value="/resources/css/owl.carousel.css"/>" type="text/css"> 
<link rel="stylesheet" href="<c:url value="/resources/css/owl.theme.css"/>" type="text/css"> 
<link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.css"/>" type="text/css">
<!-- Datetimeticker -->
<link rel="stylesheet" href="<c:url value="/resources/css/jquery.datetimepicker.css"/>" type="text/css">
<script type="text/javascript" src="<c:url value="/resources/js/jquery.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.datetimepicker.js"/>"></script>
<!--  Google Fonts  -->
<link href='https:fonts.googleapis.com/css?family=Roboto:400,500,700' rel='stylesheet' type='text/css'>

<title><tiles:getAsString name="title"></tiles:getAsString> </title>
</head>
<body class="cms-index-index">
 	<div class="page">
	<tiles:insertAttribute name="header"/>
	<tiles:insertAttribute name="body"/>
	<tiles:insertAttribute name="footer"/>
	</div>
<!--  JavaScript  -->
<script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script> 
<script type="text/javascript" src="<c:url value="/resources/js/jquery.jcarousel.min.js"/>"></script> 
<script type="text/javascript" src="<c:url value="/resources/js/cloudzoom.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/common.js"/>"></script> 
<script type="text/javascript" src="<c:url value="/resources/js/revslider.js"/>"></script> 
<script type="text/javascript" src="<c:url value="/resources/js/owl.carousel.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/cart.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/search.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/like.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/compare.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/notification.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/reg.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/order.js"/>"></script>
</body>
</html>