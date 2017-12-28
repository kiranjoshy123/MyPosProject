<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container-fluid">
	<div class="row">
	
	
		<!-- Would be to display the sidebar -->
		<div class="col-md-2">
			<%@include file="./shared/sidebar.jsp" %>
		</div>
		
		
		<!-- Would be to display the actual products -->
		<div class="col-md-6">
			
			<!-- Added breadcrumb component -->
			<div class="row">
				<div class="col-md-12">
					<c:if test="${userClickedAllProducts == true}" >
						<script>
							window.categoryId = '';
						</script>
						<ol class="breadcrumb">
							<li><a href="${contextRoot}/home">Home</a></li>
							<li class="active">All Products</li>
						</ol>
					</c:if>
					
					<c:if test="${userClickedCategoryProducts == true}" >
						<script>
							window.categoryId = '${category.id}';
						</script>
						<ol class="breadcrumb">
							<li><a href="${contextRoot}/home">Home</a></li>
							<li class="active">Category</li>
							<li class="active">${category.name}</li>
						</ol>
					</c:if>
				</div>
			
			</div>
		
			<div class="row>">
				<div class="col-md-12">
					
					<div class="contaier-fluid">
						<div class="table-responsive">
							<table id="productlistTable"
								class="table table-striped table-borderd">

								<thead>
									<tr>
										<th>Name</th>
										<th>Brand</th>
										<th>Price</th>
										<th>Qnty</th>
										<th></th>
									</tr>
								</thead>

							</table>
						</div>

					</div>

				</div>
			</div>
		</div>
		
		<!-- Cart bar -->
		<div class="col-md-4">
-			<p class="h2" style="color:ForestGreen;" >Cart</p> 
-			<%@include file="cartbar.jsp" %>
-		</div>
		
		
	
	</div>



</div>