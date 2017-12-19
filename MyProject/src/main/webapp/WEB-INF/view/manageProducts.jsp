<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<div class="container">

	<div class="row">
	
		<div class="col-md-offset-2 col-md-8">
			<div class="panel panel-primary">
			
				<div class="panel-heading">
					<h4>Product Management</h4>
				</div>
				
				<div class="panel-body">
					
					<!-- Form Elements -->
					
					<sf:form class="form-horizontal" modelAttribute="product">
						<div class="form-group">
							<label class="control-label col-md-4" for="name">Product Name :</label>
							<div class="col-md-8">
								<sf:input type="text" path="name" id="name" placeholder="Name of the product" class="form-control"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="Brand name">Brand Name :</label>
							<div class="col-md-8">
								<sf:input type="text" path="brand" id="brand" placeholder="Name of the Brand" class="form-control"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="description">Product Description :</label>
							<div class="col-md-8">
								<sf:textarea path="description" id="description" rows="4" placeholder="Write some description about the item." class="form-control" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="unitPrice">Unit Price : </label>
							<div class="col-md-8">
								<sf:input type="number" path="unit_price" id="unitPrice" placeholder="Price of each item." class="form-control"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="quantity">Quantity : </label>
							<div class="col-md-8">
								<sf:input type="number" path="quantity" id="quantity" placeholder="Available quantity." class="form-control"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="categoryId">Select Category : </label>
							<div class="col-md-8">
								<sf:select class="form-control" id="categoryId" path="category_id" 
									items="${categories}" 
									itemLabel="name" 
									itemValue="id"								
								/>
							</div>
						</div>
						
						<div class="form-group">
							<div class="col-md-offset-4 col-md-8">
								<input type="submit" name="submit" id="submit" value="Submit" class="btn btn-primary"/>
								
								<!-- Hidden fields for product -->
								<sf:hidden path="id"/>
								<sf:hidden path="code"/>
								<sf:hidden path="supplier_id"/>
								<sf:hidden path="purchases"/>
								<sf:hidden path="views"/>
								<sf:hidden path="is_active"/>
								
							</div>
						</div>
						
					</sf:form>
					
				</div>
			
			</div>
		
		</div>
	
	
	</div>


</div>