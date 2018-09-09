<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container-fluid" >
	<div class="row" >
		<div class="col-md-8">
			<!-- Added breadcrumb component -->
			<div class="row" >
				<div class="col-md-12">
					<c:if test="${showAllCategories == true or userClickedHome == true}" >
						<script>
							window.categoryName = '';
						</script>
						<ol class="breadcrumb clearfix">
							<li class="active">Home</li>
						</ol>
						
					</c:if>
					
					<c:if test="${showAllSubCategories == true}" >
						<script>
							window.categoryName = '${category.name}';
						</script>
						<ol class="breadcrumb">
							<li><a href="${contextRoot}/home">Home</a></li>
							<li class="active">${category.name}</li>
						</ol>
					</c:if>
					
					<c:if test="${showAllProducts == true}" >
						<ol class="breadcrumb">
							<li><a href="${contextRoot}/home">Home</a></li>
							<li><a href="${contextRoot}/show/category/${category.id}/subcategory">${category.name}</a></li>							
							<li class="active">${subcategory.name}</li>
						</ol>
					</c:if>
					
				</div>
			</div>
		
			<!-- Categories and product list page -->
			<div class="row">
				<div class="col-md-12" >
					<%@include file="categoriesList.jsp" %>
				</div>
			</div>
			
		</div>
		
		<!-- Cart bar -->
		<div class="col-md-4">
			<%@include file="cartbar.jsp" %>
		</div>
		
	</div>
	
</div>