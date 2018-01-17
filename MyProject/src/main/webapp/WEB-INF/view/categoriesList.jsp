<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="contaier-fluid">

	<c:if
		test="${userClickedAllProducts == true or userClickedHome == true}">
		<c:forEach items="${categories}" var="category">
			<a href="${contextRoot}/show/category/${category.id}/products"
				class="btn btn-primary btn-item">${category.name}</a>
		</c:forEach>
	</c:if>

	<c:if test="${userClickedCategoryProducts == true}">
		<c:forEach items="${productList}" var="product">
			<button class="btn btn-primary btn-item" id="buttonProduct" value="${product.unit_price}">${product.name}</button>
		</c:forEach>
	</c:if>

	
</div>