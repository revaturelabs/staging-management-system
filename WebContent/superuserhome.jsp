<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html ng-app="superuser">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="css/modalView.css" />
	
<link rel="stylesheet" href="css/login.css" />

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
<link rel="stylesheet" href="https://unpkg.com/ng-table@2.0.2/bundles/ng-table.min.css">

<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
	
	
	
<!-- Import Javascript Files -->
<script src="JavaScripts/BatchCtrl.js"></script>
<script src="JavaScripts/AddAssociateControllerScript.js"></script>

	
</head> 
<body>
<jsp:include page="ForecastModals.jsp" />
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

							<!-- documentation link -->
							<li><a href="/Documentation/Frameset.html" id="clients"
								data-target="#addClient"><span
									class="glyphicon glyphicon-plus"></span> Documentation</a></li>
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
				<thead>
							<tr>
								<th>Status</th>
								<th>Java</th>
								<th>.NET</th>
								<th>JTA</th>
								<th>Big Data</th>
							</tr>
						</thead>
				</thead>
						<tbody>
							<tr>
								<td>Available</td>
								<td>
									<button data-toggle="modal" class="associateBtn" id="Available" name="JAVA" data-target="#ForecastModal">{{month[0]}}</button>
								</td>
								<td><button data-toggle="modal" class="associateBtn" id="Available" name=".NET"
										data-target="#ForecastModal">{{month[1]}}</button></td>
								<td><button data-toggle="modal" class="associateBtn" id="Available" name="SDET"
										data-target="#ForecastModal">{{month[2]}}</button></td>
						
							</tr>
							<tr>
								<td>Mapped</td>
								<td>
									<button data-toggle="modal" class="associateBtn" id="Mapped" name="JAVA" data-target="#ForecastModal">{{month[3]}}</button>
								</td>
								<td><button data-toggle="modal" class="associateBtn" id="Mapped" name=".NET"
										data-target="#ForecastModal">{{month[4]}}</button></td>
								<td><button data-toggle="modal" class="associateBtn" id="Mapped" name="SDET"
										data-target="#ForecastModal">{{month[5]}}</button></td>
						
							</tr>
							<tr>
								<td>Confirmed</td>
								<td>
									<button data-toggle="modal" class="associateBtn" id="Confirmed" name="JAVA" data-target="#ForecastModal">{{month[6]}}</button>
								</td>
								<td><button data-toggle="modal" class="associateBtn" id="Confirmed" name=".NET"
										data-target="#ForecastModal">{{month[7]}}</button></td>
								<td><button data-toggle="modal" class="associateBtn" id="Confirmed" name="SDET"
										data-target="#ForecastModal">{{month[8]}}</button></td>
			
							</tr>
						</tbody>
					</table>
					<div class="col-md-12">
						<button id="1" class="month">January</button>
						<button id="2" class="month">February</button>
						<button id="3" class="month">March</button>
						<button id="4" class="month">April</button>
						<button id="5" class="month">May</button>
						<button id="6" class="month">June</button>
						<button id="7" class="month">July</button>
						<button id="8" class="month">August</button>
						<button id="9" class="month">September</button>
						<button id="10" class="month">October</button>
						<button id="11" class="month">November</button>			
						<button id="12" class="month">December</button>			
					</div>
			
				</div>

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
					- Input: Hidden Token for Spring Security that will allow the administrator to access
					-->
					  <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
					
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
						<label for="batchtype">Batch Type:</label> 
						<select
							class="form-control" id="batchtype" name="batchtype">
							<option value="java">Java</option>
							<option value="net">.NET</option>
							<option value="jta">JTA</option>
							<option value="bigdata">Big Data</option>
						</select>
					</div>
										<!--  
					- Input: Hidden Token for Spring Security that will allow the administrator to access
					-->
					  <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
					
					<!--  
					- Input: submit the form
					-->
					<button type="submit" id="addBatchBtn" class="btn btn-default">Create
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
					- Input: Hidden Token for Spring Security that will allow the administrator to access
					-->
					  <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
					
					
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
			
			<form ng-submit="onSubmit()">
			
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title" style="text-align: center">
					Associate Information
				</h4>
			</div>
			<div class="modal-body">

					<div>
						<div class="form-group">
							<label>Search</label>
							<input type="text" ng-model="search" placeholder="search">
						</div>
						<table ng-table="vm.tableParams" show-filter="true" class="table table-striped">
							 <thead>
							 <tr>
							 	<th>Check</th>
							 	<th>Name</th>
							 	<th>EmpID</th>
							 	<th>Status</th>
							 	<th>StartDate</th>
							 	<th>EndDate</th>
							 	<th>Company</th>
							 </tr>
							 </thead>
							 <tbody>
								<tr ng-repeat="a in associatesList |filter:search | limitTo:5">
									<td><input ng-model="associateSelected" type="checkbox" ng-checked="exist(a)" ng-click="toggleSelection(a)" ng-true-value="{{a.associateID}}"></td>
									<td>{{a.associateName}}</td>
									<td>{{a.associateID}}</td>
									<td>{{a.status}}</td>
									<td>{{a.batch.startDate}}</td>
									<td>{{a.batch.endDate}}</td>
									<td>nothing</td>
								</tr>
							 </tbody>
						</table>
						<!-- This div below is just to check the data of the associate selected by showing on this div element -->
						 <div> List: {{associateSelected}}</div>
					</div>
			</div>
			<div class="modal-footer">
				<div class=pull-left >
					
				<br>
				<!--  The table below is the to how the dropdown button as well as the radio buttons are positioned -->
				<table>
				<tr>
					<td style="text-align: left;">
					<label> Select Status:</label>
					</td>
					<td>
					<label id="availableRadio" class="radio-inline">
						<input ng-model="modifyStatus.status" type="radio" value="available" name="optradio" id="test3"> 
						Available
					</label>
					<label id="mappedRadio" class="radio-inline">
						<input ng-model="modifyStatus.status" type="radio" value="mapped" name="optradio" id="test1">
						Mapped
					</label> 
					<label id="confirmedRadio" class="radio-inline">
						<input ng-model="modifyStatus.status" type="radio" value="confirmed" name="optradio" id="test2">
						Confirmed
					</label>
					</td>

				</tr>
				<tr>
					<td> <label> Select a Client:</label> </td>
					<td style="padding: 5px">
						<select ng-model="modifyStatus.clientName" class="form-control" id="sel1" name="clients" required>
		        				<option ng-repeat="t in clientList" value="{{t.name}}" selected>{{t.name}}</option>		
		      			</select>
      				</td>
      				<td style="padding: 20px" rowspan="2">
						<button type="submit">Ok</button>
					</td>
				</tr>
				
				</table>
				</div>
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>

			</div>
			</form>
		</div>

	</div>
</div>
</html>
