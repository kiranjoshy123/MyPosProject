$(function() {
	
	/*switch(menu){
		
		case 'About Us':
			$('#about').addClass('active');
			break;
		case 'Contact Us' :
			$('#contact').addClass('active');
			break;
		case 'All Products' :
			$('#listProducts').addClass('active');
			break;
		default:
			$('#listProducts').addClass('active');
			$('#a_'+menu).addClass('active');
			break;
	}*/
	
	// Adding the csrf token
	var token = $('meta[name="_csrf"]').attr('content');
	var header = $('meta[name="_csrf_header"]').attr('content');
	if(token.length > 0 && header.length >0 ){
		// Set the token header for ajax request.
		$(document).ajaxSend(function(e,xhr,options){
			xhr.setRequestHeader(header,token);
		});
	}
	
	// Code for jquery dataTable
	
	var $table = $('#productlistTable');
	// Execute only when products are to be displayed.
	if($table.length){
		console.log("Inside the table!");
		
		
		var jsonURL = '';
		if(window.categoryId == ''){
			jsonURL = window.contextRoot + '/json/data/all/products';
		}
		else{
			jsonURL = window.contextRoot + '/json/data/category/' + window.categoryId + '/products';
		}
		
		
		$table.DataTable({
			lengthMenu : [[3,5,10,-1],['3','5','10','All']],
			pageLength : 10,
			ajax :{
				url : jsonURL,
				dataSrc : ''
			},
			columns : [
				{
					data : 'name',
					mRender : function(data,type,row){
						var str = '';
						str += '<a href ="' + window.contextRoot + '/show/' + row.id + '/product" >' + data + '</a>';
						return str;
					}
				},
				{
					data : 'brand',
				},
				{
					data : 'unit_price',
					/*mRender : function(data,type,row){
						return '&#8377;' + data
					}*/
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
					data : 'id',
					bSortable : false,
					mRender : function(data,type,row){
						var str = '';
						if(row.quantity < 1){
							str += '<a href ="javascript:void(0) class="btn btn-success disabled""><span class="glyphicon glyphicon-shopping-cart"></a>';
						}
						else{
							str += '<a href ="' + window.contextRoot + '/cart/add/' + data + '/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></a>';
						}
						return str;
					}
				}
				
			]
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
	
	//------------------------------Data table for Product - Admin -------------------------
	
	
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
	

	 $('.num').click(function () {
	        var num = $(this);
	        var text = $.trim(num.find('.txt').clone().children().remove().end().text());
	        var telNumber = $('#telNumber');
	        $(telNumber).val(telNumber.val() + text);
	    });
	
});
