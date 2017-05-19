<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<script src="https://unpkg.com/ng-table@2.0.2/bundles/ng-table.min.js"></script>
	<script
    src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-route.min.js"></script>
	
<!-- DataTable Library -->
<link type="text/css" rel="stylesheet"
	href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css">
	
<!-- Angular Table Library -->	
<link rel="stylesheet"; href="https://unpkg.com/ng-table@2.0.2/bundles/ng-table.min.css">

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
								data-target="#addAssociate"><span
									class="glyphicon glyphicon-plus"></span> Add Associate</a></li>

							<!-- navbar link add batch -->
							<li><a href="#addBatch" data-toggle="modal" id="batches"
								data-target="#addBatch"><span
									class="glyphicon glyphicon-plus"></span> Add Batch</a></li>

							<!-- navbar link add client -->
							<li><a href="#addClient" data-toggle="modal" id="clients"
								data-target="#addClient"><span
									class="glyphicon glyphicon-plus"></span> Add Client</a></li>

						</ul></li>

				</ul>
			</div>

		</div>
	</nav>

	<div ng-controller="associateTableCtrl" class="row">
		<!-- left side bar -->
		<div class="col-sm-2">
			<button data-toggle="modal" data-target="#AssociateInfo">Modal
				Test</button>
		</div>
					
		<!-- right side bar -->
		<div class="col-sm-7">
			<ul class="nav nav-pills">
				<li class="active"><a data-toggle="pill" href="#current">Current</a></li>
				<li><a data-toggle="pill" href="#forecast">Forecast</a></li>
				<li><a data-toggle="pill" href="#history">History</a></li>
				<li><a data-toggle="pill" href="#solitaire">Solitaire</a></li>
			</ul>

			<div class="tab-content">
				<div id="current" class="tab-pane fade in active" >
					<table class="table table-bordered table-striped table-hover">
						<thead>
							<tr>
								<th>Status</th>
								<th>Java</th>
								<th>.NET</th>
								<th>JTA</th>
								<th>Big Data</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Available</td>
								<td>
									<button data-toggle="modal" data-target="#AssociateInfo">{{master}}</button>
								</td>
								<td><button data-toggle="modal"
										data-target="#AssociateInfo">35</button></td>
								<td><button data-toggle="modal"
										data-target="#AssociateInfo">50</button></td>
								<td><button data-toggle="modal"
										data-target="#AssociateInfo">55</button></td>
							</tr>
							<tr>
								<td>Mapped</td>
								<td><button data-toggle="modal"
										data-target="#AssociateInfo">10</button></td>
								<td><button data-toggle="modal"
										data-target="#AssociateInfo">5</button></td>
								<td><button data-toggle="modal"
										data-target="#AssociateInfo">15</button></td>
								<td><button data-toggle="modal"
										data-target="#AssociateInfo">7</button></td>
							</tr>
							<tr>
								<td>Interviewed</td>
								<td><button data-toggle="modal"
										data-target="#AssociateInfo">5</button></td>
								<td><button data-toggle="modal"
										data-target="#AssociateInfo">50</button></td>
								<td><button data-toggle="modal"
										data-target="#AssociateInfo">10</button></td>
								<td><button data-toggle="modal"
										data-target="#AssociateInfo">3</button></td>
							</tr>
							<tr>
								<td>Confirmed</td>
								<td><button data-toggle="modal"
										data-target="#AssociateInfo">6</button></td>
								<td><button data-toggle="modal"
										data-target="#AssociateInfo">3</button></td>
								<td><button data-toggle="modal"
										data-target="#AssociateInfo">1</button></td>
								<td><button data-toggle="modal"
										data-target="#AssociateInfo">5</button></td>
							</tr>
						</tbody>
					</table>

				</div>

				<div id="forecast" class="tab-pane fade"></div>

				<div id="history" class="tab-pane fade">history</div>
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
						<label for="batchSelector">Batch:</label> 
						<select
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
		<div ng-controller="client" class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">
					<center>Associate Information</center>
				</h4>
			</div>
			<div class="modal-body">

				<form id="insert">
					<div>
						<table ng-table="vm.tableParams" show-filter="true" class="table table-striped">
							 
								<tr ng-repeat="a in associatesList">
									<td title="'Check'"><input type="checkbox" value=""></td>
									<td title="'Name'" filter="{ name: 'text'}" sortable="'name'">{{a.associateName}}</td>
									<td title="'EmpID'" filter="{ EmpID: 'text'}" sortable="'EmpID'">{{a.associateID}}</td>
									<td title="'Status'" filter="{ Status: 'text'}" sortable="'Status'">{{a.status}}</td>
									<td title="'StartDate'" filter="{ StartDate: 'text'}" sortable="'StartDate'">{{a.startDate}}</td>
									<td title="'EndDate'" filter="{ EndDate: 'text'}" sortable="'EndDate'">{{a.endDate}}</td>
									<td title="'Company'" filter="{ Company: 'text'}" sortable="'Company'">nothing</td>
								</tr>
							 
						</table>
					</div>
					
				</form>
			</div>
			<div class="modal-footer">
				<div class=pull-left >
					<label class="radio-inline">
						<input type="radio" value="available" name="optradio"> 
						Available
					</label> 
					<label class="radio-inline">
						<input type="radio" value="mapped" name="optradio">
						Mapped
					</label> 
					
					<label class="radio-inline">
						<input type="radio" value="interviewed" name="optradio">
						Interviewed
					</label>
					<label class="radio-inline">
						<input type="radio" value="confirmed" name="optradio">
						Confirmed
					</label>
				<br>
				<table>
				<tr>
					<td>
					<select class="form-control" id="sel1" name="clients">
	        				<option ng-repeat="t in clientList" value="{{t.clientID}}">{{t.name}}</option>
	        				
	      			</select>
	      			
	      			<!--  <div class="form-group" ng-controller="BatchCtrl">
						<label for="batchSelector">Batch:</label> 
						<select
							id="BatchSelector" style="width: 100%;" name="batch"
							class="form-control">
							<option ng-repeat="b in batches" value="{{b.trainingName}}">{{b.trainingName}}</option>
						</select> -->
	      			
      				</td>
      				<td style="padding: 20px">
					<button ng-click="updateStatus()" type="button">Ok</button>
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