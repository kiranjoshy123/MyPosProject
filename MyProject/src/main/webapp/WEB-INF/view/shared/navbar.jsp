<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<!-- Sliding side bar -->
<div id="mySidenav" class="sidenav">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
  <a href="#">Settings</a>
  <a href="#">History</a>
  <a href="#">Calc</a>
  <a href="${contextRoot}/contact">Contact</a>
  <a href="${contextRoot}/about">About</a>
</div>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" style="color:white" href="${contextRoot}/home">
				<span style="font-weight:bold">ePOS</span>
			</a>
		</div>
		
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li id="openNav">
					<span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776;</span>
				</li>
			</ul>
			
			<ul class="nav navbar-nav navbar-right">
				<security:authorize access="isAuthenticated()">
					<li id="listProducts">
						<a href="${contextRoot}/show/all/products">All Products</a>
					</li>
				</security:authorize>
				
				<!-- Show 'Manage Products', 'Manage Category' and 'Manage Users' only for AMIN -->
				<security:authorize access="hasAuthority('ADMIN')">
					<li id="manageProducts">
						<a href="${contextRoot}/manage/products">Manage Products</a>
					</li>
					<li id="manageCategory">
						<a href="${contextRoot}/manage/categories">Manage Category</a>
					</li>
					<li id="manageUsers">
						<a href="${contextRoot}/manage/users">Manage Users</a>
					</li>
				</security:authorize>
				
				<security:authorize access="isAnonymous()">
					<li id="login">
						<a href="${contextRoot}/login">Log In</a>
					</li>
				</security:authorize>
				<security:authorize access="isAuthenticated()">
					<li class="dropdown">
						<a  href="javascript:void(0)"
							class="btn btn-default dropdown-toggle"
							id="dropdownMenu1"
							data-toggle="dropdown">
								${userModel.fullName}
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<li><a href="${contextRoot}/perform-logout">Logout</a></li>
						</ul>
					</li>
				</security:authorize>
			</ul>
			
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container -->
</nav>

<!-- Script for handling opening and closing of side bar -->
<script>
function openNav() {
    document.getElementById("mySidenav").style.width = "15%";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
}
</script>