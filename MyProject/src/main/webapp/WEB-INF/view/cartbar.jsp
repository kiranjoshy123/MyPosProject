<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<div class="CartBarHeading">
	    <h4 style="color:white;" >Orders</h4>
	</div>
	
	<c:choose>
		<c:when test="${not empty cartLines}">
			<table class="table table-hover" id="cartTable">
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

					<tr>
						<td> </td>
						<td> </td>
						<td> </td>
						<td><h3>Total</h3></td>
						<td class="text-right">
							<h3><strong>${userModel.cart.grandTotal}</strong></h3>
						</td>
					</tr>
				</tbody>
			</table>
			
			<!-- Cart pay/hold/delete -->
			<div class="row">
				<div class="cartFooter" >
					<a href="#" style="margin-left:15px;" class="col-md-3 btn btn-danger" id="clearCartBtn">
						<span class="glyphicon glyphicon-trash" ></span>
      					<span style="padding-left:10px;" >Clear</span>
					</a>
					<a href="#" style="margin-left:10px;" class="col-md-3 btn btn-info">
						<span class="glyphicon glyphicon-pause" ></span>
      					<span style="padding-left:10px;" >Hold</span>
					</a>
					<a href="#" style="margin-left:10px;" class="col-md-5 btn btn-success">
						<span style="vertical-align: middle;" class="glyphicon glyphicon-credit-card" ></span>
      					<span style="vertical-align: middle;padding-left:10px;" >Pay</span>
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

</div>
