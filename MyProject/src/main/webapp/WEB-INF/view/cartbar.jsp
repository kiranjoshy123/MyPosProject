<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="CartBarHeading">
    <h4 style="color:white;" >Cart</h4>
</div>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- <script>
	$(document).on('click', '#pay', function() {
		var ctx = "${pageContext.request.contextPath}";
		var currentCartVal = parseInt($('#cartTotal').text(), 10);
		var href = ctx + "/order/pay?name=" + currentCartVal;
		window.open(href, "_self");
	});
</script>
-->
<div style="height:70vh; overflow-y:auto;">
<table class="table table-borderless table-hover table-responsive" style="table-layout:fixed; overflow:auto" id="cartTable">
	<thead>
		<tr>
			<th style="width:30%; text-align:center">Product</th>
			<th style="width:25%; text-align:center">Quantity</th>
			<th style="width:15%; text-align:center">Price</th>
			<th style="width:15%; text-align:center">Total</th>
			<th style="width:15%;"> </th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>
</div>

<!-- Cart pay/hold/delete -->
<div class="cartFooter" style="height:10vh;" >
	<div style="position: fixed; bottom:1%; width:30vw;">
		<div class="col-md-offset-8">
			<h4>
				<span>Total </span> <span id="cartTotal">0.0</span>
			</h4>
		</div>
	
		<div class="col-md-3">
			<a href="#" class="btn btn-danger btn-block" id="clearCartBtn"> 
			    <span class="glyphicon glyphicon-trash"></span> <span>Clear</span>
			</a>
		</div>
		<div class="col-md-3">
			<a href="#" class="btn btn-info btn-block" id="cartHoldBtn"> 
				<span class="glyphicon glyphicon-pause"></span> <span style="padding-left: 10px;">Hold</span>
			</a>
		</div>
		<div class="col-md-6">
			<a class="btn btn-success btn-block" id="pay"> 
				<span style="vertical-align: middle;" class="glyphicon glyphicon-credit-card"></span> <span style="vertical-align: middle">Pay</span>
			</a>
		</div>
	</div>
</div>