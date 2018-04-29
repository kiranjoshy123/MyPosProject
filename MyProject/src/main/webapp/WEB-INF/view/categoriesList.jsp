<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="contaier-fluid">

	<c:if test="${userClickedAllProducts == true or userClickedHome == true}">
		<c:forEach items="${categories}" var="category">
			<a href="${contextRoot}/show/category/${category.id}/subcategory"
				class="btn btn-primary btn-item">${category.name}</a>
		</c:forEach>
	</c:if>
	
	<c:if test="${userClickedSubCategory == true}">
		<c:forEach items="${subcategoryList}" var="subcategory">
			<a href="${contextRoot}/show/subcategory/${subcategory.id}/products"
				class="btn btn-primary btn-item">${subcategory.name}</a>
		</c:forEach>
	</c:if>

	<c:if test="${userClickedCategoryProducts == true}">
		<c:forEach items="${productList}" var="product">
			<button type="button" class="btn btn-primary btn-item" id="buttonProduct" value="${product.unit_price}" productId="${product.id}">${product.name}</button>
		</c:forEach>
	</c:if>
	
	<!-- Show only when payment page is requested from orders -->
	<c:if test="${userClickedOrderPayment == true}">
		<%@include file="payment.jsp"%>
	</c:if>

</div>