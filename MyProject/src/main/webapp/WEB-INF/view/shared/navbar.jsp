<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" style="color:white" href="${contextRoot}/home">
				<span style="font-weight:bold">ePOS</span>
			</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li id="listProducts">
					<a href="${contextRoot}/show/all/products">All Products</a>
				</li>
				<li id="manageProducts">
					<a href="${contextRoot}/manage/products">Manage Products</a>
				</li>
				<li id="manageCategory">
					<a href="${contextRoot}/manage/categories">Manage Category</a>
				</li>
				<li id="manageUsers">
					<a href="${contextRoot}/manage/users">Manage Users</a>
				</li>
				
			</ul>
			
			<ul class="nav navbar-nav navbar-right">
				<li id="about">
					<a href="${contextRoot}/about">About</a>
				</li>
				<li id="contact">
					<a href="${contextRoot}/contact">Contact</a>
				</li>
			</ul>
			
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container -->
</nav>