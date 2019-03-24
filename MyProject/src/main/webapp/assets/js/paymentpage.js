$(function() {
	
	$(document).on('click','#pay',function(){
		// disable all cart buttons.
		$("#clearCartBtn").addClass('disabled');
		$("#cartHoldBtn").addClass('disabled');
		$("#pay").addClass('disabled');
		
		$("#catSubCatProductdiv").empty();
		var totalAmout = parseInt($('#cartTotal').text(), 10);
		
		var breakupText = 
			'<div class="col-md-2"></div>' +
			'<div class="col-md-4">' +
				'<div class="form-group">' +
					'<div id="whiteSquareBox" class="panel panel-default">' +
			    		'<div class="panel-body">' +
							'<h4 style="color:black">Amount Due</h4>' +
							'<h6 style="color:black"><strong>' + totalAmout + '</strong></h6>' +
							'<br/>' +
					
							'<h4 style="color:black">Total Tax</h4>' +
							'<h6 style="color:black"><strong>$ 0.0</strong></h6>' +
					
							'<h4 style="color:black">Total Discount</h4>' +
							'<h6 style="color:black"><strong>$ 0.0</strong></h6>' +
							'<br/>' +
					
							'<h4 style="color:black">Points earned</h4>' +
							'<h6 style="color:black"><strong>$ 0.0</strong></h6>' +
						'</div>' +
			 		'</div>' +
				'</div>' +
			'</div>';
	
		var numPadDiv = 
			'<div class="col-md-6">' +
				'<h3>Amount to be paid</h3>' +
				'<input type="number" name="name" id="numpadInput" class="form-control" value="' + totalAmout + '" />' +
				'<ul id="keyboard">' +
					'<li class="letter">1</li>' +
					'<li class="letter">2</li>' +
					'<li class="letter">3</li>' +
					'<li class="letter smallBtn">$5</li>' +
					'<li class="letter clearl">4</li>' +
					'<li class="letter">5</li>' +
					'<li class="letter">6</li>' +
					'<li class="letter smallBtn">$10</li>' +
					'<li class="letter clearl">7</li>' +
					'<li class="letter ">8</li>' +
					'<li class="letter">9</li>' +
					'<li class="letter smallBtn">$20</li>' +	
					'<li class="switch">.</li>' +
					'<li class="letter">0</li>' +
					'<li class="delete lastitem"></li>' + 
					'<li class="letter smallBtn">$55</li>' +
				'</ul>' +
				'<a href="${contextRoot}/order/payment/cash" type="button" class="btn btn-primary btn-item">Cash</a>' +
				'<a href="${contextRoot}/order/payment/card" type="button" class="btn btn-primary btn-item">Credit/Debit Card</a>' +
				'<a href="#" type="button" class="btn btn-primary btn-item">Gift Card</a>' +
			'</div>';
		$('#posMainDiv').html((breakupText+numPadDiv));
	
		var breadcrumbCnt = '<ol class="breadcrumb">' + 
			'<li id="brdCmbShowCategory"><a href="#">Home</a></li>'  +
			'</ol>';				
		$('#breadcrumbDiv').html(breadcrumbCnt);
	});

});