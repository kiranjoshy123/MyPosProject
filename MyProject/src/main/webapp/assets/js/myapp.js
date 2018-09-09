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
					data : 'subcategory_id',
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
	
//------------------------------Data table for SubCategory - Admin -------------------------
	
	var $table = $('#adminSubCategoryTable');
	// Execute only when Sub-categories are to be displayed.
	if($table.length){
		var jsonURL = window.contextRoot + '/json/data/admin/all/subcategories';
		
		$table.DataTable({
			lengthMenu : [[10,30,50,-1],['10','30','50','All']],
			pageLength : 30,
			ajax :{
				url : jsonURL,
				dataSrc : ''
			},
			drawCallback: function ( settings ) {
				var api = this.api();
	            var rows = api.rows( {page:'current'} ).nodes();
	            var last=null;
	 
	            api.column(0, {page:'current'} ).data().each( function ( group, i ) {
	                if ( last !== group ) {
	                    $(rows).eq( i ).before(
	                        '<tr class="group" style="background-color:black;"><td colspan="5">'+group+'</td></tr>'
	                    );
	 
	                    last = group;
	                }
	            } );
	    },
			columns : [
				{
					data : 'categoryId',
					visible : false,
				},
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
						str += '<a href="'+ window.contextRoot +'/manage/' + data + '/subcategories" class="btn btn-warning" >';
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
	
	//------------------------------ Todays/All days history----------------------------
	var $table = $('#todaysHistoryTable');
	if($table.length){
		var jsonURL = window.contextRoot + '/json/data/sales/today';
		
		$table.DataTable({
			lengthMenu : [[10,30,50,-1],['10','30','50','All']],
			pageLength : 30,
			ajax :{
				url : jsonURL,
				dataSrc : ''
			},
			drawCallback: function ( settings ) {
				var api = this.api();
	            var rows = api.rows( {page:'current'} ).nodes();
	            var last=null;
	 
	            api.column(0, {page:'current'} ).data().each( function ( group, i ) {
	                if ( last !== group ) {
	                    $(rows).eq( i ).before(
	                        '<tr class="group" style="background-color:black;"><td colspan="10">'+group+'</td></tr>'
	                    );
	 
	                    last = group;
	                }
	            } );
			},
			columnDefs : [
		    	{
		    		"targets": "_all",
		    	    "className": "text-center",
		    	},
		    ],
			columns : [
				{
					data : 'staffId',
					visible : false,
				},
				{
					data : 'id',
				},
				{
					data : 'dateTime',
				}
				,
				{
					data : 'productId',
				}
				,
				{
					data : 'byingPrice',
				}
				,
				{
					data : 'productCount',
				}
				,
				{
					data : 'discount',
				}
				,
				{
					data : 'taxPaid',
				}
				,
				{
					data : 'total',
				}
				,
				{
					data : 'paymentMethod',
					mRender : function(data, type, row){
						console.log('INput data customer ID - ' + data);
						var str = '';
						if(data == 1){
							str = 'Cash';
						}else if(data == 2){
							str = 'Credit\Debit Card';
						}else if(data == 3){
							str = 'Gift Card';
						}
						
						console.log('Output str - ' + str);
						return str;
					}
				}
				,
				{
					data : 'customerId',
				}

			]
		});
	}
	
	var $table = $('#allHistoryTable');
	if($table.length){
		var jsonURL = window.contextRoot + '/json/data/sales/complete';
		
		$table.DataTable({
			lengthMenu : [[10,30,50,-1],['10','30','50','All']],
			pageLength : 30,
			ajax :{
				url : jsonURL,
				dataSrc : ''
			},
			drawCallback: function ( settings ) {
				var api = this.api();
	            var rows = api.rows( {page:'current'} ).nodes();
	            var last=null;
	 
	            api.column(0, {page:'current'} ).data().each( function ( group, i ) {
	                if ( last !== group ) {
	                    $(rows).eq( i ).before(
	                        '<tr class="group" style="background-color:black;"><td colspan="10">'+group+'</td></tr>'
	                    );
	 
	                    last = group;
	                }
	            } );
		    },
		    columnDefs : [
		    	{
		    		"targets": "_all",
		    	    "className": "text-center",
		    	},
		    ],
			columns : [
				{
					data : 'staffId',
					visible : false,
				},
				{
					data : 'id',
				},
				{
					data : 'dateTime',
				}
				,
				{
					data : 'productId',
				}
				,
				{
					data : 'byingPrice',
				}
				,
				{
					data : 'productCount',
				}
				,
				{
					data : 'discount',
				}
				,
				{
					data : 'taxPaid',
				}
				,
				{
					data : 'total',
				}
				,
				{
					data : 'paymentMethod',
					mRender : function(data, type, row){
						var str = '';
						if(data == 1){
							str = 'Cash';
						}else if(data == 2){
							str = 'Credit\Debit Card';
						}else if(data == 3){
							str = 'Gift Card';
						}
						
						console.log('Output str - ' + str);
						return str;
					}
				}
				,
				{
					data : 'customerId',
				}
			]
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
	
	//--------------------- Cart bar functionality ---------------------------------------------
	// Key-down function - Waiting for scanner input. 
	var barcode='';
    $(document).keypress(function(e) {
        var code = (e.keyCode ? e.keyCode : e.which);
        if(code==13)// Enter key hit
        {
        	var $cartTable = $('#cartTable');
        	if($('#cartTable').length)
        	{
        		addScannedItemToCart();
        	}
        	else if($('#adminProductTable').length)
    		{
        		addScannedItemToProductList();
    		}
        	
            barcode = '';
        }
        else if(code==9)// Tab key hit
        {
            barcode='';
        }
        else
        {
            barcode = barcode + String.fromCharCode(code);
        }
    });
    
    function addScannedItemToProductList()
    {
    	var barCodeEdit = document.getElementById("code");
		if(barCodeEdit == null){
			console.log("Faild to find barcode box");
			return;
		}
		
		console.log("Succesfully found barcode box");
		barCodeEdit.value = barcode;
    }
    
    function addScannedItemToCart()
    {
    	var request = $.ajax({
    		url: '/search/products',
    		async: false,
    		type: "POST",
    		data: { code: barcode },
    		success: function(product)
    		{        			
    			console.log("Mapping Successful" + product.id);
    			if(product.id != null)
				{
    				addProduct(product.id,product.unit_price,product.name);
				}
    			else
				{
    				if($('#searchProductTable').length)
    				{
    					updateCodeEditDialog(barcode,"","","","");
    					$('#productsModal').modal('show');
    				}
				}
    		},
    		
    		error: function (error) {
    			console.log("Error : " + error);
    		}
    	});
    }
    
  //--------------------- Search button click for barcode from the modal dialog ---------------------------------------------
    $(document).on('click','#productNotFoundSearch',function(){
    	var userEnteredCode = searchProductTable.rows[1].cells[0].children[0].value;
    	var request = $.ajax({
    		url: '/search/products',
    		async: false,
    		type: "POST",
    		data: { code: userEnteredCode },
    		success: function(product)
    		{        			
    			console.log("Mapping Successful" + product.id);
    			(product.id != null) ? updateCodeEditDialog(userEnteredCode,product.name,product.brand,product.unit_price,product.id) :
    				updateCodeEditDialog(userEnteredCode,"","","","");
    		},
    		
    		error: function (error) {
    			console.log("Error : " + error);
    		}
    	});
	});
    
    function updateCodeEditDialog(code,name,brand,price,productId){
    	$("#searchProductTable tbody").html("");
		$("#searchProductTable").append('<tbody>' +
			'<tr>' +
				'<td> <input type="text" style="color:blue;width: 100%;" autofocus value="' + code + '" spellcheck="false" onfocus="var temp_value=this.value; this.value=\'\'; this.value=temp_value"></td>' + 
				'<td>' + name + '</td>' + 
				'<td>' + brand + '</td>' + 
				'<td>' + price + '</td>' + 
				'<td style="display:none">' + productId + '</td>' + 
			'</tr>' +
			'</tbody>');
		if(!name){
			$("#productNoFoundWarning").show();
			$('#productNotFoundAddBtn').prop('disabled', true);
		}
		else{
			$("#productNoFoundWarning").hide();
			$('#productNotFoundAddBtn').prop('disabled', false);
		}
    }
    
  //--------------------- Add to cart button from the Modal dialog ---------------------------------------------
    $(document).on('click','#productNotFoundAddBtn',function(){
    	addProduct(searchProductTable.rows[1].cells[4].innerHTML,
    			searchProductTable.rows[1].cells[2].innerHTML,
    			searchProductTable.rows[1].cells[1].innerHTML);
    	$('#productsModal').modal('toggle');
	});

  //--------------------- Add to cart from the button product(listproducts.jsp) ---------------------------------------------
	$(document).on('click','#buttonProduct',function(){
		var user = $(this);
		if(user != null)
		{
			addProduct($(user).attr("productId"),$(user).attr("value"),$(user).text());
		}
	});
	
	function addProduct(productId, productValue, productName){
		
		var tableCart = document.getElementById("cartTable").getElementsByTagName('tbody')[0];
		if((tableCart == null) || (productId == null) || (productValue == null) ||(productName == null)){
			return;
		}
		
		// Check whether the product already exists using the productId.
		var row = $('tr[productId="' + productId + '"]');
		if(row.index() == -1)
		{
	        var tdProduct = document.createElement("td");
	        tdProduct.className = "col-md-4 Product";
	        tdProduct.innerHTML = '<div >' + 
				'<div >' + 
				'<h5 style="font-size: 15px;" >' + productName + '</h5>' + 
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
	        tdPrice.innerHTML = productValue;
	        
	        var tdTotal = document.createElement("td");
	        tdTotal.className = "col-md-1 text-center totalItemPrice";
	        tdTotal.innerHTML = productValue;
	        
	        var tdRemove = document.createElement("td");
	        tdRemove.className = "col-md-1 removeItem";
	        tdRemove.innerHTML = '<a  type="button" class="btn icon-btn btn-danger" id="removeCartItemBtn">' + 
								'<span class="glyphicon btn-glyphicon glyphicon-trash img-circle text-danger"></span></a>';
	        
	        var newRow = document.createElement("tr");
	        newRow.setAttribute("height", 80);
	        newRow.setAttribute('productId', productId);
	        newRow.appendChild(tdProduct);
	        newRow.appendChild(tdQuantity);
	        newRow.appendChild(tdPrice);
	        newRow.appendChild(tdTotal);
	        newRow.appendChild(tdRemove);
	        
	        tableCart.appendChild(newRow);
	        
	        // Update the cart total.
	        var currentCartVal = parseInt($('#cartTotal').text(), 10);
	        var newItemPrice = parseInt(productValue, 10);
	        $('#cartTotal').text(currentCartVal+newItemPrice);
		}else{
			// Product exists. Increase the count of existing product.
			incrementProductCount(row);
		}
	}
	
	
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
	
});
