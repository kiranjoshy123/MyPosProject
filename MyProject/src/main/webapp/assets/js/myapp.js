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
	
	// Code for jquery dataTable
	
	var $table = $('#productlistTable');
	// Execute only when products are to be displayed.
	if($table.length){
		console.log("Inside the table!");
		console.log(window.contextRoot);
		
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
						str += '<a href ="' + window.contextRoot + '/show/' + data + '/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160';
						if(row.quantity < 1){
							str += '<a href ="javascript:void(0) class="btn btn-success disabled""><span class="glyphicon glyphicon-shopping-cart"></a>';
						}
						else{
							str += '<a href ="' + window.contextRoot + '/cart/add/' + data + '/product class="btn btn-success""><span class="glyphicon glyphicon-shopping-cart"></a>';
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
	
	
	// -----------------------------Data table for admin-----------------------
	var $adminProductsTable = $('#adminProductTable');
	// Execute only when products are to be displayed.
	if($adminProductsTable.length){
		console.log("Inside the table!");
		console.log(window.contextRoot);
		
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
					var msg = (checked) ? 'You want to active the product ?' : 'You want to deactivate the product ?'
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
	
	//------------------------------Data table for Admin-------------------------
	
});