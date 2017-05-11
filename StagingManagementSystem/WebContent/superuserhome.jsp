<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script src = "/StagingManagementSystem/JavaScripts/AddAssociateControllerScript.js"></script>
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand"><span class="glyphicon glyphicon-globe"></span>
				TWMS</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">

			<ul class="nav navbar-nav navbar-right text-uppercase">

				<!-- Employee dropdown-->
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span>
						Employees </a>

					<ul class="dropdown-menu dropdown-menu-right" role="menu" style="">

						<!-- navbar link add associate -->
						<li><a href="#addAssociate" data-toggle="modal"
							data-target="#addAssociate"><span
								class="glyphicon glyphicon-plus"></span> Add Associate</a></li>

						<!-- navbar link add batch -->
						<li><a href="#addBatch" data-toggle="modal"
							data-target="#addBatch"><span
								class="glyphicon glyphicon-plus"></span> Add Batch</a></li>

						<!-- navbar link add client -->
						<li><a href="#addClient" data-toggle="modal"
							data-target="#addClient"><span
								class="glyphicon glyphicon-plus"></span> Add Client</a></li>

					</ul></li>

			</ul>
		</div>

	</div>
	</nav>
</body>
</html>

<div id="addAssociate" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Register Employee</h4>
			</div>
			<div class="modal-body">
				<div ng-app = "formSubmit" ng-controller = "AddAssociateController">
					<form data-ng-submit="submit()">
						<h3>{{headerText}}</h3>
						<p>
							Name: <input type="text" name = "name" data-ng-model="fields.name">
						</p>
						<p>
							Location: <input type="text" name = "location" data-ng-model="fields.location">
						</p>
						<p>
							Phone: <input type="text" name = "phone" data-ng-model="fields.phone">
						</p>
						<input type="submit" id="submit" value="Submit" /><br>

						<h4>You submitted below data through post:</h4>
						<pre>Form data ={{list}}</pre>
					</form>
				</div>
				
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>

	</div>
</div>