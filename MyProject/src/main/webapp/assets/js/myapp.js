$(function() {
	
	switch(menu){
		case 'All Products' :
			$('#listProducts').addClass('active');
			break;	
		case 'Manage Products':
			$('#manageProducts').addClass('active');
			break;
		case 'Manage Category' :
			$('#manageCategory').addClass('active');
			break;
		case 'Manage User' :
			$('#manageUsers').addClass('active');
			break;
		default:
			break;
	}
	
	// Adding the csrf token
	var token = $('meta[name="_csrf"]').attr('content');
	var header = $('meta[name="_csrf_header"]').attr('content');
	if(token.length > 0 && header.length >0 ){
		// Set the token header for ajax request.
		$(document).ajaxSend(function(e,xhr,options){
			xhr.setRequestHeader(header,token);
		});
	}
	
	
	//Timer implementation for dismissing the alert
	var $alert = $('.alert');
	if($alert.length){
		setTimeout(function(){
			$alert.fadeOut('slow');
		}, 3000)
	}
	
	//-----------------------------------
	/*$('.switch input[type="checkbox"').on( 'change', function(){
		var checkbox = $(this);
		var checked = checkbox.prop('checked');
		var msg = (checked) ? 'You want to active the product ?' : 'You want to deactivate the product ?'
		var value = checkbox.prop('value');
		
		bootbox.confirm({
			size:'medium',
			title:'Product Activation & Deactivation',
			message : msg,
			callback : function(confirmed)
			{
				if(confirmed){
				}
				else{
					checkbox.prop('checked', !checked);
				}
			}
		})
	});*/
	
	
	// -----------------------------Data table for Product. Available only for admin-----------------------
	var $adminProductsTable = $('#adminProductTable');
	// Execute only when products are to be displayed.
	if($adminProductsTable.length){
		
		var jsonURL = window.contextRoot + '/json/data/admin/all/products';
		
		$adminProductsTable.DataTable({
			lengthMenu : [[10,30,50,-1],['10','30','50','All']],
			pageLength : 30,
			ajax :{
				url : jsonURL,
				dataSrc : ''
			},
			columns : [
				{
					data : 'category_id',
				},
				{
					data : 'name',
				},
				{
					data : 'brand',
				},
				{
					data : 'quantity',
					mRender : function(data,type,row){
						if(data < 1)
						{
							return '<span style="color:red">Out of Stock!</span>';
						}
						
						return data;
					}
				},
				{
					data : 'unit_price',
					/*mRender : function(data,type,row){
						return '&#8377;' + data
					}*/
				},
				{
					data : 'is_active',
					bSortable : false,
					mRender : function(data,type,row){
						var str = '';
						str += '<label class="switch">';
						
						if(data){
							str += '<input type="checkbox" checked="checked" value="' + row.id + '"/>';
						}
						else{
							str += '<input type="checkbox" value="' + row.id + '"/>';
						}
						
						str += '<div class="slider"></div></label>';
						return str;
					}
					
				},
				{
					data : 'id',
					bSortable : false,
					mRender : function(data, type, row){
						var str = '';
						str += '<a href="'+ window.contextRoot +'/manage/' + data + '/product" class="btn btn-warning" >';
						str += '<span class="glyphicon glyphicon-pencil" ></span></a>';
						return str;
					
					}
				}
				
			],
			
			initComplete : function(){
				var api = this.api();
				api.$('.switch input[type="checkbox"]').on( 'change', function(){
					var checkbox = $(this);
					var checked = checkbox.prop('checked');
					var msg = (checked) ? 'You want to activate the product ?' : 'You want to deactivate the product ?'
					var value = checkbox.prop('value');
					
					bootbox.confirm({
						size:'medium',
						title:'Product Activation & Deactivation',
						message : msg,
						callback : function(confirmed)
						{
							if(confirmed){
								
								var actURL = window.contextRoot + '/manage/product/' + value + '/activation';
								$.post(actURL, function( data ){
									console.log("post returned" + data);
									/*bootbox.confirm({
										size:'medium',
										title:'Information',
										message : data
									});*/
								});
							}
							else{
								checkbox.prop('checked', !checked);
							}
						}
					});
				});
			}
			
			
		});
	}
	
	
	//------------------------------Data table for Category - Admin -------------------------
	
	var $table = $('#adminCategoryTable');
	// Execute only when categories are to be displayed.
	if($table.length){
		var jsonURL = window.contextRoot + '/json/data/admin/all/categories';
		
		$table.DataTable({
			lengthMenu : [[10,30,50,-1],['10','30','50','All']],
			pageLength : 30,
			ajax :{
				url : jsonURL,
				dataSrc : ''
			},
			columns : [
				{
					data : 'name',
				},
				{
					data : 'description',
				}
				,
				{
					data : 'id',
					bSortable : false,
					mRender : function(data, type, row){
						var str = '';
						str += '<a href="'+ window.contextRoot +'/manage/' + data + '/categories" class="btn btn-warning" >';
						str += '<span class="glyphicon glyphicon-pencil" ></span></a>';
						return str;
					
					}
				}
				
			]
		});
	}
	
	//------------------------------Data table for User - Admin -------------------------
	var $table = $('#adminUserTable');
	// Execute only when categories are to be displayed.
	if($table.length){
		console.log("Inside the table!");
		
		var jsonURL = window.contextRoot + '/json/data/admin/all/users';
		console.log(jsonURL);
		
		$table.DataTable({
			lengthMenu : [[5,10,30,-1],['5','10','30','All']],
			pageLength : 10,
			ajax :{
				url : jsonURL,
				dataSrc : ''
			},
			columns : [
				{
					data : 'firstName',
				},
				{
					data : 'lastName',
				},
				{
					data : 'address',
				},
				{
					data : 'userName',
				},
				{
					data : 'email',
				},
				{
					data : 'contactNumber',
				},
				{
					data : 'enabled',
					bSortable : false,
					mRender : function(data,type,row){
						var str = '';
						str += '<label class="switch">';
						
						if(data){
							str += '<input type="checkbox" checked="checked" value="' + row.id + '"/>';
						}
						else{
							str += '<input type="checkbox" value="' + row.id + '"/>';
						}
						
						str += '<div class="slider"></div></label>';
						return str;
					}
					
				},
				{
					data : 'id',
					bSortable : false,
					mRender : function(data, type, row){
						var str = '';
						str += '<a href="'+ window.contextRoot +'/manage/' + data + '/users" class="btn btn-warning" >';
						str += '<span class="glyphicon glyphicon-pencil" ></span></a>';
						return str;
					
					}
				}
				
			],
			
			initComplete : function(){
				var api = this.api();
				api.$('.switch input[type="checkbox"]').on( 'change', function(){
					var checkbox = $(this);
					var checked = checkbox.prop('checked');
					var msg = (checked) ? 'You want to enable the user ?' : 'You want to disable the user ?'
					var value = checkbox.prop('value');
					
					bootbox.confirm({
						size:'medium',
						title:'User enabling & disabling',
						message : msg,
						callback : function(confirmed)
						{
							if(confirmed){
								
								var actURL = window.contextRoot + '/manage/user/' + value + '/activation';
								$.post(actURL, function( data ){
									console.log("post returned" + data);
									/*bootbox.confirm({
										size:'medium',
										title:'Information',
										message : data
									});*/
								});
							}
							else{
								checkbox.prop('checked', !checked);
							}
						}
					});
				});
			}
			
		});
	}
	
	
	//------------------------------Login form validation----------------------------
	var $loginForm = $('#loginForm');
	if($loginForm.length){
		$loginForm.validate({
			rules : {
				username : {
					required: true,
					//email: true
				},
				password:{
					required: true
				}
			},
			
			message : {
				username : {
					required: "Please enter the User ID."
				},
				password:{
					required: "Please enter the Password."
				}
			},
			
			errorElement : 'em',
			errorPlacement : function(error, element){
				error.addClass('help-block');
				error.insertAfter(element);
			}
			
		});
	}
	
	// Key-down function - Waiting for scanner input. 
	/*var barcode="";
    $(document).keydown(function(e) {
        var code = (e.keyCode ? e.keyCode : e.which);
        if(code==13)// Enter key hit
        {
            alert(barcode);
            var $table = $('#productlistTable').DataTable();
            $table.search( 'Mac' ).draw();
            barcode = "";
        }
        else if(code==9)// Tab key hit
        {
            alert(barcode);
        }
        else
        {
            barcode = barcode + String.fromCharCode(code);
        }
    });*/

	$(document).on('click','#buttonProduct',function(){
		var tableCart = document.getElementById("cartTable").getElementsByTagName('tbody')[0];
		var user = $(this);
		
		if(tableCart !=null && user != null)
		{
			// Check whether the product already exists using the productId.
			var row = $('tr[productId="' + $(user).attr("productId") + '"]');
			if(row.index() == -1)
			{
		        var tdProduct = document.createElement("td");
		        tdProduct.className = "col-md-4 Product";
		        //
		        tdProduct.innerHTML = '<div >' + 
					'<div >' + 
					'<h5 style="font-size: 15px;" >' + $(user).text() + '</h5>' + 
		        	'</div>	</div>';
		        
		        var tdQuantity = document.createElement("td");
		        tdQuantity.className = "col-md-5 text-center quantity";
		        tdQuantity.innerHTML = '<a type="button" class="btn btn-info btn-circle" id="itemCountMinus" disabled="disabled">' +
					'<span class="glyphicon glyphicon-minus"></span>' +
					'</a>' +
					'<label class="labelQuantity">&nbsp;&nbsp;1&nbsp;&nbsp;</label>' +
					'<a type="button" class="btn btn-info btn-circle" id="itemCountPlus" >' +
						'<span class="glyphicon glyphicon-plus"></span>' +
					'</a>';
		        
		        var tdPrice = document.createElement("td");
		        tdPrice.className = "col-md-1 text-center itemPrice";
		        tdPrice.innerHTML = $(user).attr("value");
		        
		        var tdTotal = document.createElement("td");
		        tdTotal.className = "col-md-1 text-center totalItemPrice";
		        tdTotal.innerHTML = $(user).attr("value");
		        
		        var tdRemove = document.createElement("td");
		        tdRemove.className = "col-md-1 removeItem";
		        tdRemove.innerHTML = '<a  type="button" class="btn icon-btn btn-danger" id="removeCartItemBtn">' + 
									'<span class="glyphicon btn-glyphicon glyphicon-trash img-circle text-danger"></span></a>';
		        
		        var newRow = document.createElement("tr");
		        newRow.setAttribute("height", 80);
		        newRow.setAttribute('productId', $(user).attr("productId"));
		        newRow.appendChild(tdProduct);
		        newRow.appendChild(tdQuantity);
		        newRow.appendChild(tdPrice);
		        newRow.appendChild(tdTotal);
		        newRow.appendChild(tdRemove);
		        
		        tableCart.appendChild(newRow);
		        
		        // Update the cart total.
		        var currentCartVal = parseInt($('#cartTotal').text(), 10);
		        var newItemPrice = parseInt($(user).attr("value"), 10);
		        $('#cartTotal').text(currentCartVal+newItemPrice);
			}else{
				// Product exists. Increase the count of existing product.
				incrementProductCount(row);
			}
		}else
		{
			
		}
	});
	
	$(document).on('click','#removeCartItemBtn',function(){
		// Reduce the price of item removed.
		var currentCartVal = parseInt($('#cartTotal').text(), 10);
		var itemRemoved = parseInt($(this).closest('tr').children('td.totalItemPrice').text(), 10);
		$('#cartTotal').text(currentCartVal-itemRemoved);
		
		// Finally remove the item.
		$(this).closest('tr').remove();
	});
	
	$(document).on('click','#itemCountPlus',function(){
		
		incrementProductCount($(this));
	});
	
	function incrementProductCount(row){
		if(row === undefined)
			return;
			
		var curItemCount = parseInt(row.closest('tr').find('.labelQuantity').text(), 10);
		var itemPrice = parseInt(row.closest('tr').find('.itemPrice').text(), 10);
		var currentCartVal = parseInt($('#cartTotal').text(), 10);
		
		row.closest('tr').find('.labelQuantity').html( '&nbsp;&nbsp;' + (curItemCount+1) + '&nbsp;&nbsp;');
		row.closest('tr').find('.totalItemPrice').text( itemPrice*(curItemCount+1));
		$('#cartTotal').html(currentCartVal+itemPrice);
		
		// Enable the minus button.
		row.closest('tr').find('#itemCountMinus').removeAttr('disabled');
	};
	
	$(document).on('click','#itemCountMinus:enabled',function(){
		var curItemCount = parseInt($(this).closest('tr').find('.labelQuantity').text(), 10);
		var itemPrice = parseInt($(this).closest('tr').find('.itemPrice').text(), 10);
		var currentCartVal = parseInt($('#cartTotal').text(), 10);
		
		$(this).closest('tr').find('.labelQuantity').html( '&nbsp;&nbsp;' + (curItemCount-1) + '&nbsp;&nbsp;');
		$(this).closest('tr').find('.totalItemPrice').text( itemPrice*(curItemCount-1));
		$('#cartTotal').html(currentCartVal-itemPrice);
		
		// Disable the minus button if count is <= 1 
		if((curItemCount-1) <= 1)
		{
			$(this).closest('tr').find('#itemCountMinus').attr('disabled','disabled');
		}
	});
	
	$(document).on('click','#clearCartBtn',function(){
		// Remove all the rows in cart.
		var tableCart = document.getElementById("cartTable");
		if(tableCart !=null )
		{
			tableCart.innerHTML = '<thead>' + 
					'<tr>' + 
						'<th>Product</th>' + 
						'<th style="text-align:center">Quantity</th>' + 
						'<th class="text-center">Price</th>' + 
						'<th class="text-center">Total</th>' + 
						'<th></th>' + 
					'</tr>' + 
					'</thead>' +
					'<tbody>' +
					'</tbody>';
			
			$('#cartTotal').text("0.0");
		}
	});
	
	$("#myBtn").click(function(){
        
		$('#myModal').modal('show');
   });
	 
	
});
