<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<div class="CartBarHeading">
	    <h4 style="color:white;" >Orders</h4>
	</div>
	
	<c:choose>
		<c:when test="${not empty cartLines}">
			<table class="table table-hover table-bordered" id="cartTable">
				<thead>
					<tr>
						<th>Product</th>
						<th style="text-align:center">Quantity</th>
						<th class="text-center">Price</th>
						<th class="text-center">Total</th>
						<th> </th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			
			<!-- Cart pay/hold/delete -->
			<div class="cartFooter">
				<div class="col-md-offset-8">
					<h4 >
						<span>Total  </span>
						<span id="cartTotal">0.0</span>
					</h4>
				</div>
				
				<div class="col-md-3">
					<a href="#" class="btn btn-danger btn-block" id="clearCartBtn">
						<span class="glyphicon glyphicon-trash" ></span>
	     				<span >Clear</span>
					</a>
				</div>
				<div class="col-md-3">
					<a href="#" class="btn btn-info btn-block">
						<span class="glyphicon glyphicon-pause" ></span>
	     				<span style="padding-left:10px;" >Hold</span>
					</a>
				</div>
				
				<div class="col-md-6">
					<a href="${contextRoot}/order/pay" class="btn btn-success btn-block">
						<span style="vertical-align: middle;" class="glyphicon glyphicon-credit-card" ></span>
	     				<span style="vertical-align: middle" >Pay</span>
					</a>
				</div>
			</div>
					
		</c:when>
		<c:otherwise>
			<br></br>
			<div class="jumbotron">
				<h2>Add items to the Cart!!</h2>
			</div>
		</c:otherwise>
	</c:choose>
