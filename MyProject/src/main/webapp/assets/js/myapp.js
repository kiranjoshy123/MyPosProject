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
		var tableCart = document.getElementById("cartTable");
		var user = $(this);
		if(tableCart !=null && user != null)
		{
	        var tdProduct = document.createElement("td");
	        tdProduct.className = "col-md-1";
	        tdProduct.innerHTML = '<div class="media">' + 
				'<div class="media-body">' + 
				'<h5 class="media-heading">' + $(user).text() + '</h5>' + 
	        	'</div>	</div>';
	        
	        var tdQuantity = document.createElement("td");
	        tdQuantity.className = "col-md-4";
	        tdQuantity.innerHTML = '<a type="button"class="btn btn-info btn-circle" >' +
				'<span class="glyphicon glyphicon-minus"></span>' +
				'</a>' +
				'<label>&nbsp;1&nbsp;</label>' +
				'<a type="button"class="btn btn-info btn-circle" >' +
					'<span class="glyphicon glyphicon-plus"></span>' +
				'</a>';
	        
	        var tdPrice = document.createElement("td");
	        tdPrice.className = "col-md-1 text-center";
	        tdPrice.innerHTML = "<strong>" + $(user).attr("value") + "</strong>";
	        
	        var tdTotal = document.createElement("td");
	        tdTotal.className = "col-md-1 text-center";
	        tdTotal.innerHTML = "<strong>" + $(user).attr("value") + "</strong>";
	        
	        var tdRemove = document.createElement("td");
	        tdRemove.className = "col-md-1";
	        tdRemove.innerHTML = '<a  type="button" class="btn icon-btn btn-danger" id="removeCartItemBtn">' + 
								'<span class="glyphicon btn-glyphicon glyphicon-trash img-circle text-danger"></span></a>';
	        
	        var newRow = document.createElement("tr");
	        newRow.setAttribute("height", 80);
	        newRow.appendChild(tdProduct);
	        newRow.appendChild(tdQuantity);
	        newRow.appendChild(tdPrice);
	        newRow.appendChild(tdTotal);
	        newRow.appendChild(tdRemove);
	        
	        tableCart.appendChild(newRow);
		}
		else
		{
			
		}
	});
	
	$(document).on('click','#removeCartItemBtn',function(){
		// Remove the selected row.
		$(this).closest ('tr').remove();
	});
	
	

	 
	
});
