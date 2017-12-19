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
					mRender : function(data,type,row){
						return '&#8377;' + data
					}
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
});