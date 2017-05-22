<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page session="true"%>

<!DOCTYPE html>
<html ng-app="superuser">
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

<title>SMS</title>
<!-- AngularJs Library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>

<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-route.min.js"></script>

<!-- DataTable Library -->
<link type="text/css" rel="stylesheet"
	href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css">

<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>



<!-- Import Javascript Files -->
<script src="JavaScripts/BatchCtrl.js"></script>
<link rel="stylesheet" href="css/modalView.css" />
<script src="JavaScripts/AddAssociateControllerScript.js"></script>


</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand"><span class="glyphicon glyphicon-globe"></span>
					Staging Management System</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">

				<ul class="nav navbar-nav navbar-right text-uppercase">

					<!-- Employee dropdown-->
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span>
							Employees </a>

						<ul class="dropdown-menu dropdown-menu-right" role="menu" style="">

							<!-- navbar link add associate -->
							<li><a id="associates" data-toggle="modal"
								data-target="#addAssociate" ng-click="getBatches()"><span
									class="glyphicon glyphicon-plus"></span> Add Associate</a></li>

							<!-- navbar link add batch -->
							<li><a href="#addBatch" data-toggle="modal" id="batches"
								data-target="#addBatch"><span
									class="glyphicon glyphicon-plus"></span> Add Batch</a></li>

							<!-- navbar link add client -->
							<li><a href="#addClient" data-toggle="modal" id="clients"
								data-target="#addClient"><span
									class="glyphicon glyphicon-plus"></span> Add Client</a></li>

							<!-- navbar link logout -->
							<li><a href="javascript:formSubmit()"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>

							<script>
								function formSubmit() {
									document.getElementById("logoutForm")
											.submit();
								}
							</script>

							<!-- csrt for log out-->
							<form action="logout" method="post" id="logoutForm">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
							</form>


						</ul></li>

				</ul>
			</div>

		</div>
	</nav>

	<div class="row" ng-controller="infoTable">
		<!-- right side bar -->
		<div class="col-sm-offset-2 col-sm-8">
			<ul class="nav nav-pills">
				<li class="active"><a data-toggle="pill" href="#current">Current</a></li>
				<li><a data-toggle="pill" href="#forecast" ng-click="getForecast()">Forecast</a></li>
			</ul>

			<div class="tab-content">
				<div id="current" class="tab-pane fade in active">
					<table class="table table-bordered table-striped table-hover">
						<thead>
							<tr>
								<th>Status</th>
								<th>Java</th>
								<th>.NET</th>
								<th>JTA</th>
								<th>Total</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="c in current">
								<td>{{c[0]}}</td>
								<td>
									<button data-toggle="modal" data-target="#AssociateInfo" id="{{c[1][0]}}">{{c[5]}}</button>
								</td>
								<td><button data-toggle="modal"
										data-target="#AssociateInfo" id="{{c[1]}}">{{c[6]}}</button></td>
								<td><button data-toggle="modal"
										data-target="#AssociateInfo" id="{{c[1]}}">{{c[7]}}</button></td>
								<td><button data-toggle="modal"
										data-target="#AssociateInfo" id="all-{{c[0]}}">{{c[5] + c[6] + c[7]}}</button></td>
							</tr>
						</tbody>
					</table>
				</div>

				<div id="forecast" class="tab-pane fade">
				<table class="table table-bordered table-striped table-hover">
						<thead>
							<tr>
								<th>Week</th>
								<th>Java</th>
								<th>.NET</th>
								<th>JTA</th>
								<th>Total</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="w in weeks">
								<td>{{w.daterange}}</td>
								<td>
									<button data-toggle="modal" data-target="#AssociateInfo">{{w.javacount}}</button>
								</td>
								<td><button data-toggle="modal"
										data-target="#AssociateInfo">{{w.dotNetCount}}</button></td>
								<td><button data-toggle="modal"
										data-target="#AssociateInfo">{{w.sdetcount}}</button></td>
								<td><button data-toggle="modal"
										data-target="#AssociateInfo">{{w.javacount + w.dotNetCount + w.sdetcount}}</button></td>
							</tr>
						</tbody>
					</table>
				</div>

			</div>
		</div>
	</div>


</body>


<!-----------------------
Add associate modal
 ------------------------->
<div id="addAssociate" class="modal slide" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Add Associate</h4>
			</div>
			<div class="modal-body">
				<form id="addAssociateForm">
					<!--  
					- Input: text field for full associate name
					-->
					<div class="form-group">
						<label for="name">Full Name:</label> <input type="text"
							class="form-control" id="name" name="name" required>
					</div>
					<!--  
					- Input: select statement for collecting the availability status of an associate
					-->
					<div class="form-group">
						<label for="associatestatus">Associate Status:</label> <select
							class="form-control" id="associatestatus" name="associatestatus">
							<option>Available</option>
							<option>Mapped</option>
							<option>Interviewed</option>
							<option>Confirmed</option>
						</select>
					</div>
					<!--  
					- Input: select statement for collecting the batch that an associate belongs to
					- options retrieved from a controller
					-->
					<div class="form-group" ng-controller="BatchCtrl">
						<label for="batchSelector">Batch:</label> <select
							id="BatchSelector" style="width: 100%;" name="batch"
							class="form-control">
							<option ng-repeat="b in batches" value="{{b.trainingName}}">{{b.trainingName}}</option>
						</select>

					</div>
					<!--  
					- Input: submit the form
					-->
					<button type="submit" id="addAssociate" class="btn btn-default">Register
						Associate</button>
					<!--  
					- Input: clear the form
					-->
					<button type="reset" class="btn btn-default">Clear Form</button>
				</form>
			</div>
			<div class="modal-footer">
				<!--  
					- Input: close the modal
					-->
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>

	</div>
</div>

<!-----------------------
Add batch modal
 ------------------------->

<div id="addBatch" class="modal slide" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Add Batch</h4>
			</div>
			<div class="modal-body">
				<form id="addBatchForm">
					<div class="form-group">
						<!--  
					- Input: text field to get the name of the batch
					-->
						<label for="trainingname">Training Name: </label> <input
							type="text" class="form-control" id="trainingname"
							name="trainingname" required>
					</div>
					<!--  
					- Input: text field to get the location where the training is being held
					-->
					<div class="form-group">
						<label for="location">Location: </label> <input type="text"
							class="form-control" id="location" name="location" required>
					</div>
					<!--  
					- Input: text field to get the batch trainer
					-->
					<div class="form-group">
						<label for="trainer">Trainer:</label> <input type="text"
							class="form-control" id="trainer" name="trainer" required>
					</div>
					<!--  
					- Input: date field to get the start date of the batch
					-->
					<div class="form-group">
						<label for="startdate">Start Date:</label> <input type="date"
							class="form-control" data-date-format="mm-dd-yyyy" id="startdate"
							name="startdate" required>
					</div>
					<!--  
					- Input: date field to get the projected end date of the batch
					-->
					<div class="form-group">
						<label for="enddate">End Date:</label> <input type="date"
							class="form-control" data-date-format="mm-dd-yyyy" id="enddate"
							name="enddate" required>
					</div>
					<!--  
					- Input: select statement for collecting the type of batch
					-->
					<div class="form-group">
						<label for="batchtype">Batch Type:</label> <select
							class="form-control" id="batchtype" name="batchtype">
							<option value="java">Java</option>
							<option value="net">.NET</option>
							<option value="jta">JTA</option>
							<option value="jta">Big Data</option>
						</select>
					</div>
					<!--  
					- Input: submit the form
					-->
					<button type="submit" id="addBatch" class="btn btn-default">Create
						Batch</button>
					<!--  
					- Input: clear the form
					-->
					<button type="reset" class="btn btn-default">Clear Form</button>
				</form>
			</div>
			<div class="modal-footer">
				<!--  
				- Input: close the form
				-->
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>

	</div>
</div>

<!-----------------------
Add client modal
 ------------------------->

<div id="addClient" class="modal slide" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Add Client</h4>
			</div>
			<div class="modal-body">
				<form id="addClientForm">
					<!--  
				- Input: text field to collect the client name
				-->
					<div class="form-group">
						<label for="clientname">Client Name: </label> <input type="text"
							class="form-control" id="clientname" name="clientname" required>
					</div>
					<!--  
					- Input: text field to collect the client's location
					-->
					<div class="form-group">
						<label for="location">Location: </label> <input type="text"
							class="form-control" id="location" name="location" required>
					</div>
					<!--  
					- Input: submit the form
					-->
					<button type="submit" id="addClient" class="btn btn-default">Create
						Batch</button>
					<!--  
					- Input: clear the form
					-->
					<button type="reset" class="btn btn-default">Clear Form</button>
				</form>
			</div>
			<div class="modal-footer">
				<!--  
					- Input: close the button
					-->
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>

	</div>
</div>
<div id="AssociateInfo" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">
					<center>Associate Information</center>
				</h4>
			</div>
			<div class="modal-body">

				<form id="insert">
					<div ng-app="myApp2">
						<table id="myTable" class="table table-striped">
							<thead>
								<tr>
									<th>Check to Select</th>
									<th>Firstname</th>
									<th>Lastname</th>
									<th>EmId</th>
									<th>Status</th>
									<th>Company</th>
									<th>Start Date</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><input type="checkbox" value=""></td>
									<td>John</td>
									<td>Doe</td>
									<td>mark@sample.comuyfytdykjrsdjrcdtr</td>
									<td>John</td>
									<td>Doe</td>
									<td>john@example.comutykdfyutdykjidrtyjurdstjurs</td>
								</tr>
								<tr>
									<td><input type="checkbox" value=""></td>
									<td>Mary</td>
									<td>Moe</td>
									<td>mary@example.com</td>
									<td>John</td>
									<td>Doe</td>
									<td>john@example.com</td>
								</tr>
								<tr>
									<td><input type="checkbox" value=""></td>
									<td>July</td>
									<td>Dooley</td>
									<td>july@example.com</td>
									<td>John</td>
									<td>Doe</td>
									<td>john@example.com</td>
								</tr>
								<tr>
									<td><input type="checkbox" value=""></td>
									<td>John</td>
									<td>Doe</td>
									<td>mark@example.comuyfytdykjrsdjrcdtr</td>
									<td>John</td>
									<td>Doe</td>
									<td>john@example.comutykdfyutdykjidrtyjurdstjurs</td>
								</tr>
								<tr>
									<td><input type="checkbox" value=""></td>
									<td>John</td>
									<td>Doe</td>
									<td>john@example.comuyfytdykjrsdjrcdtr</td>
									<td>John</td>
									<td>Doe</td>
									<td>john@example.comutykdfyutdykjidrtyjurdstjurs</td>
								</tr>
								<tr>
									<td><input type="checkbox" value=""></td>
									<td>John</td>
									<td>Doe</td>
									<td>mark@example.comuyfytdykjrsdjrcdtr</td>
									<td>John</td>
									<td>Doe</td>
									<td>john@example.comutykdfyutdykjidrtyjurdstjurs</td>
								</tr>
								<tr>
									<td><input type="checkbox" value=""></td>
									<td>John</td>
									<td>Doe</td>
									<td>john@example.comuyfytdykjrsdjrcdtr</td>
									<td>John</td>
									<td>Doe</td>
									<td>john@example.comutykdfyutdykjidrtyjurdstjurs</td>
								</tr>
								<tr>
									<td><input type="checkbox" value=""></td>
									<td>John</td>
									<td>Doe</td>
									<td>john@example.comuyfytdykjrsdjrcdtr</td>
									<td>John</td>
									<td>Doe</td>
									<td>john@example.comutykdfyutdykjidrtyjurdstjurs</td>
								</tr>
								<tr>
									<td><input type="checkbox" value=""></td>
									<td>John</td>
									<td>Doe</td>
									<td>john@example.comuyfytdykjrsdjrcdtr</td>
									<td>John</td>
									<td>Doe</td>
									<td>john@example.comutykdfyutdykjidrtyjurdstjurs</td>
								</tr>
								<tr>
									<td><input type="checkbox" value=""></td>
									<td>John</td>
									<td>Doe</td>
									<td>john@example.comuyfytdykjrsdjrcdtr</td>
									<td>John</td>
									<td>Doe</td>
									<td>john@example.comutykdfyutdykjidrtyjurdstjurs</td>
								</tr>
								<tr>
									<td><input type="checkbox" value=""></td>
									<td>John</td>
									<td>Doe</td>
									<td>john@example.comuyfytdykjrsdjrcdtr</td>
									<td>John</td>
									<td>Doe</td>
									<td>john@example.comutykdfyutdykjidrtyjurdstjurs</td>
								</tr>
								<tr>
									<td><input type="checkbox" value=""></td>
									<td>John</td>
									<td>Doe</td>
									<td>john@example.comuyfytdykjrsdjrcdtr</td>
									<td>John</td>
									<td>Doe</td>
									<td>john@example.comutykdfyutdykjidrtyjurdstjurs</td>
								</tr>
							</tbody>

						</table>
					</div>




				</form>
			</div>
			<div class="modal-footer">
				<div class=pull-left>
					<label class="radio-inline"> <input type="radio"
						name="optradio"> Available
					</label> <label class="radio-inline"> <input type="radio"
						name="optradio"> Mapped
					</label> <label class="radio-inline"> <input type="radio"
						name="optradio"> Interviewed
					</label> <label class="radio-inline"> <input type="radio"
						name="optradio"> Confirmed
					</label> <br>
					<table>
						<tr>
							<td><select class="form-control" id="sel1">
									<option>Available</option>
									<option>Infosys</option>
									<option>Capital One</option>
									<option>IBM</option>
							</select></td>
							<td style="padding: 20px">
								<button type="button">Ok</button>
							</td>
						</tr>
					</table>
				</div>
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>

			</div>
		</div>

	</div>
</div>
</html>