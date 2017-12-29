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
							<td class="col-md-2" style="text-align: center">
								<!-- <button type="button" class="glyphicon glyphicon glyphicon-plus" ></button> -->
								<!-- <input type="number" min="1" class="form-control" id="exampleInputEmail1" value="${cartLine.productCount}"> -->
								<label>${cartLine.productCount}</label>
								<!-- <button type="button" class="glyphicon glyphicon glyphicon-minus" ></button> -->
							</td>
							<td class="col-md-1 text-center"><strong>${cartLine.byingPrice}</strong></td>
							<td class="col-md-1 text-center"><strong>${cartLine.total}</strong></td>
							<td class="col-md-1">
								<a href="${contextRoot}/cart/${cartLine.id}/delete" type="button" class="btn icon-btn btn-warning">
									<span class="glyphicon btn-glyphicon glyphicon-trash img-circle text-warning"></span> Remove
								</a>
							</td>
						</tr>

					</c:forEach>

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
			<div class="cartFooter">
				<div class="row" >
					<a href="#" style="margin-left:10px;" class="col-md-3 btn btn-danger">
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
			<div class="jumbotron">
				<h2>Add items to the Cart!!</h2>
			</div>
		</c:otherwise>
	</c:choose>

</div>
