<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="contaier-fluid">

	<!-- Display all the available categories + hot products -->
	<c:if test="${showAllCategories == true or userClickedHome == true}">
		<c:forEach items="${categories}" var="category">
			<a href="${contextRoot}/show/category/${category.id}/subcategory" class="btn btn-primary btn-item" style="line-height: 50px;">${category.name}</a>
		</c:forEach>
		<br/>
		<br/>
		<br/>
		<c:forEach items="${productList}" var="product">
			<button type="button" class="btn btn-primary btn-item btn-item-product" id="buttonProduct" value="${product.unit_price}" productId="${product.id}">
				<span class="button-left-top" >${product.name}</span>
				<span class="button-left-bottom" >${product.unit_price}</span>
			</button>
		</c:forEach>
	</c:if>
	
	<!-- Display all the sub-categories for a category -->
	<c:if test="${showAllSubCategories == true}">
		<c:forEach items="${subcategoryList}" var="subcategory">
			<a href="${contextRoot}/show/subcategory/${subcategory.id}/products" class="btn btn-primary btn-item" style="line-height: 50px;">${subcategory.name}</a>
		</c:forEach>
	</c:if>

	<!-- Display all the available products under a category. -->
	<c:if test="${showAllProducts == true}">
		<c:forEach items="${productList}" var="product">
			<button type="button" class="btn btn-primary btn-item btn-item-product" id="buttonProduct" value="${product.unit_price}" productId="${product.id}">
				<span class="button-left-top" >${product.name}</span>
				<span class="button-left-bottom" >${product.unit_price}</span>
			</button>
		</c:forEach>
	</c:if>
	
	<!-- Show only when payment page is requested from orders -->
	<c:if test="${userClickedOrderPayment == true}">
		<%@include file="payment.jsp"%>
	</c:if>

</div>