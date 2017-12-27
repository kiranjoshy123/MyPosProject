<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<c:choose>
		<c:when test="${not empty cartLines}">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Product</th>
						<th>Quantity</th>
						<th class="text-center">Price</th>
						<th class="text-center">Total</th>
						<th> </th>
					</tr>
				</thead>
				<tbody>

					<c:forEach items="${cartLines}" var="cartLine">
						<tr>
							<td class="col-md-1">
								<div class="media">
									<div class="media-body">
										<h5 class="media-heading"> ${cartLine.product.name} </h5>
									</div>
								</div>
							</td>
							<td class="col-md-1" style="text-align: center"><input
								type="number" min="1" class="form-control" id="exampleInputEmail1"
								value="${cartLine.productCount}"></td>
							<td class="col-md-1 text-center"><strong>${cartLine.byingPrice}</strong></td>
							<td class="col-md-1 text-center"><strong>${cartLine.total}</strong></td>
							<td class="col-md-1">
								<button type="button" class="btn btn-danger">
									<span class="glyphicon glyphicon-remove"></span> Remove
								</button>
							</td>
						</tr>

					</c:forEach>


					<tr>
						<td> </td>
						<td> </td>
						<td> </td>
						<td><h5>Subtotal</h5></td>
						<td class="text-right"><h5>
								<strong>${userModel.cart.grandTotal}</strong>
							</h5></td>
					</tr>
					<tr>
						<td> </td>
						<td> </td>
						<td> </td>
						<td><h5>Estimated shipping</h5></td>
						<td class="text-right"><h5>
								<strong>$6.94</strong>
							</h5></td>
					</tr>
					<tr>
						<td> </td>
						<td> </td>
						<td> </td>
						<td><h3>Total</h3></td>
						<td class="text-right"><h3>
								<strong>$31.53</strong>
							</h3></td>
					</tr>
					<tr>
						<td> </td>
						<td> </td>
						<td> </td>
						<td></td>
						<td>
							<button type="button" class="btn btn-success">
								Checkout <span class="glyphicon glyphicon-play"></span>
							</button>
						</td>
					</tr>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			<div class="jumbotron">
				<h2>Add items to the Cart!!</h2>
			</div>
		</c:otherwise>
	</c:choose>

</div>
