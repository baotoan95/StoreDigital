<%@page import="com.baotoan.spring.entities.DetailProduct"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="com.baotoan.spring.entities.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="<c:url value="/resources/js/notification.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/compare.js"/>"></script>

  <!-- End header -->
  <section class="main-container col1-layout">
    <div class="main container">
      <div class="col-main">
        <div class="cart wow">
          <div class="page-title">
            <h2>Compare Products</h2>
          </div>
          <div class="table-responsive">
          <table class="table table-striped compare-table">
            <colgroup>
            <col width="5">
            <%
            @SuppressWarnings("unchecked")
            List<Product> listCompare = (List<Product>)session.getAttribute("listCompare");
            for(int i = 0; i < listCompare.size(); i++) {
            %>
            <col class="widthCol" width="<%= 100 / listCompare.size() %>%">
            <%
           	}
            %>
            </colgroup>
            <tbody>
              <tr class="product-shop-row first odd">
                <th>&nbsp;</th>
                <c:forEach var="product" items="${sessionScope.listCompare }">
                <td class="${product.id }"><a style="cursor: pointer;" onclick="delCompare(${product.id})" class="btn btn-cancel icon-remove" title="Remove This Item"></a> 
	                <a class="product-image" href="view?id=${product.id }" title="${product.name }">
	                	<img src="/StoreDigital/resources${product.urlImage }" alt="Avatar" width="200">
	                </a>
                  	<h2 class="product-name"><a href="view?id=${product.id }" title="${product.name }">${product.name }</a></h2>
                  	<div class="price-box">
                    	<p class="special-price"> <span class="price"> ${product.newPrice } VNĐ </span> </p>
                        <p class="old-price"> <span class="price-sep">-</span> <span class="price"> ${product.oldPrice } VNĐ </span> </p>
                    </div>
                  	<p>
                    	<button type="button" onclick="cart(${product.id}, 'ADD', this)" title="Thêm vào giỏ hàng" class="button"><span><span>Thêm Vào</span></span></button>
                  	</p>
               		<a onclick="like(${product.id})" class="button wishlist">Thích</a>
              	</td>
                </c:forEach>
              </tr>
            </tbody>
            <tbody>
            <%
            	@SuppressWarnings("unchecked")
            	Map<String, Set<String>> groups = (Map<String, Set<String>>) request.getAttribute("groups");
            	Set<String> groupNames = groups.keySet();
            	Iterator<String> iterGroupNames = groupNames.iterator();
            	while(iterGroupNames.hasNext()) {
            		String groupName = iterGroupNames.next();
            %>
              <tr class="even">
                <th class="groupName" style="background: #eb8415; color: white;" colspan="<%= listCompare.size() + 1%>"><%= groupName %></th>
              </tr>
              <%
	              	Set<String> detailNames = groups.get(groupName);
	              	Iterator<String> iterDetailName = detailNames.iterator();
	              	while(iterDetailName.hasNext()) {
	              		String detailName = iterDetailName.next();
              %>
              <tr class="odd">
                <th><%= detailName %></th>
                		<%
                		for(Product pro : listCompare) {
                			Map<String, DetailProduct> details = pro.getDetail().get(groupName);
                			String value = details.get(detailName).getValue(); 
                			if(null != value) {
                		%>
                			<td class="<%= pro.getId() %>"><div> <%= value %> </div></td>
                		<%
                			} else {
                		%>
                			<td class="<%= pro.getId() %>"><div> - </div></td>
                		<%
                			}
                		}
                		%>
              </tr>
            <%
              		}
            	}
            %>
            </tbody>
          </table>
          </div>
        </div>
      </div>
    </div>
  </section>