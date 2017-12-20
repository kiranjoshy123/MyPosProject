<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">

	<div class="row">
	
		<!-- Status/Error message -->
		<c:if test="${not empty message}" >
			<div class="col-xs-12">
				<div class="alert alert-success alert-dismissible">
				
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					${message}
				
				</div>
			</div>
		</c:if>
	
	
		<div class="col-md-offset-2 col-md-8">
			<div class="panel panel-primary">
			
				<div class="panel-heading">
					<h4>Add/Edit a Category</h4>
				</div>
				
				<div class="panel-body">
					
					<!-- Form Elements -->
					
					<sf:form class="form-horizontal" modelAttribute="category" 
						action="${contextRoot}/manage/categories"
						method="POST"
						>
						<div class="form-group">
							<label class="control-label col-md-4" for="name">Category Name :</label>
							<div class="col-md-8">
								<sf:input type="text" path="name" id="name" placeholder="Name of the category" class="form-control"/>
								<sf:errors path="name" cssClass="help-block" element="em"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="description">Category Description :</label>
							<div class="col-md-8">
								<sf:textarea path="description" id="description" rows="4" placeholder="Write some description about the category." class="form-control" />
								<sf:errors path="description" cssClass="help-block" element="em"/>
							</div>
						</div>
				
						<div class="form-group">
							<div class="col-md-offset-5 col-md-7">
								<input type="submit" name="submit" id="submit" value="Save" class="btn btn-primary"/>
								
								<!-- Hidden fields for product -->
								<sf:hidden path="id"/>
								<sf:hidden path="active"/>
								
							</div>
						</div>
						
					</sf:form>
					
				</div>
			
			</div>
		
		</div>
	
	
	</div>


	<!-- List of all Categories -->
	<div class="row">
		<div class="col-md-offset-2 col-xs-8">
			<h3>Available Category</h3>
		</div>
		
		
		<div class="col-md-offset-2 col-xs-8" >
			<div style="overflow:auto">
				
				<table id="adminCategoryTable"
					class="table table-striped table-bordered">
					<thead>
						<tr>
							<th>Id</th>
							<th>Name</th>
							<th>Edit</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	
		
	
	</div>



</div>