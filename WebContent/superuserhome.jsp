<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html ng-app="superuser">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Superuser Home</title>
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
<link rel="stylesheet"
	href="https://unpkg.com/ng-table@2.0.2/bundles/ng-table.min.css">

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
				<a href="#" class="navbar-brand">
					<img src="https://files.slack.com/files-pri/T1CTFL5A7-F5J52GGJ2/logo_1.png" alt="Revature" width="10%" />
				</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">

				<ul class="nav navbar-nav navbar-right text-uppercase">

					<!-- Employee dropdown-->
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span>
							Employees </a>

						<ul class="dropdown-menu dropdown-menu-right" role="menu" style="">

							<!-- documentation link -->
							<li><a href="Documentation/index.html" id="clients"
								data-target="#addClient"><span
									class="glyphicon glyphicon-file"></span> Documentation</a></li>
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
			<ul class="nav nav-tabs nav-justified">
				<li class="active"><a data-toggle="tab" href="#current">Current</a></li>
				<li><a data-toggle="tab" href="#forecast"
					ng-click="getForecast()">Forecast</a></li>
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
								
								<!-- Not currently available. 
								<th>Big Data</th> -->
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Available</td>
								<td>
									<button id="available" data-toggle="modal" class="btn btn-xs" data-target="#AvailableJavaAssociateInfo">{{javaAvailable}}</button>
								</td>
								<td><button id="available" data-toggle="modal" class="btn btn-xs"
										data-target="#AvailableNetAssociateInfo">{{netAvailable}}</button></td>
								<td><button id="available" data-toggle="modal" class="btn btn-xs"
										data-target="#AvailableSDETAssociateInfo">{{sdetAvailable}}</button></td>
								
								<!-- Not currently available.
								<td><button id="available" data-toggle="modal"
										data-target="#AssociateInfo">{{bigDataAvailable}}</button></td> -->
							</tr>
							<tr>
								<td>Mapped</td>
								<td><button id="mapped" data-toggle="modal" class="btn btn-xs"
										data-target="#MappedJavaAssociateInfo">{{javaMapped}}</button></td>
								<td><button id="mapped" data-toggle="modal" class="btn btn-xs"
										data-target="#MappedNetAssociateInfo">{{netMapped}}</button></td>
								<td><button id="mapped" data-toggle="modal" class="btn btn-xs"
										data-target="#MappedSDETAssociateInfo">{{sdetMapped}}</button></td>
										
								<!-- Not currently available. 
								<td><button id="mapped" data-toggle="modal"
										data-target="#AssociateInfo">{{bigDataMapped}}</button></td> -->
							</tr>
							<tr>
								<td>Confirmed</td>
								<td><button id="confirmed" data-toggle="modal" class="btn btn-xs"
										data-target="#ConfirmedJavaAssociateInfo">{{javaConfirmed}}</button></td>
								<td><button id="confirmed" data-toggle="modal" class="btn btn-xs"
										data-target="#ConfirmedNetAssociateInfo">{{netConfirmed}}</button></td>
								<td><button id="confirmed" data-toggle="modal" class="btn btn-xs"
										data-target="#ConfirmedSDETAssociateInfo">{{sdetConfirmed}}</button></td>
										
								<!-- Not currently available.
								<td><button id="confirmed" data-toggle="modal"
										data-target="#AssociateInfo">{{bigDataConfirmed}}</button></td> -->
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
							</tr>
						</thead>
						</thead>
						<tbody id="showForecast">
						
							<tr>
								<td>Available</td>
								<td>
									<button data-toggle="modal" class="associateBtn btn btn-xs"
										id="Available" name="JAVA" data-target="#ForecastModal">{{month[0]}}</button>
								</td>
								<td><button data-toggle="modal"
										class="associateBtn btn btn-xs" id="Available" name=".NET"
										data-target="#ForecastModal">{{month[1]}}</button></td>
								<td><button data-toggle="modal"
										class="associateBtn btn btn-xs" id="Available" name="SDET"
										data-target="#ForecastModal">{{month[2]}}</button></td>

							</tr>
							<tr>
								<td>Mapped</td>
								<td>
									<button data-toggle="modal" class="associateBtn btn btn-xs"
										id="Mapped" name="JAVA" data-target="#ForecastModal">{{month[3]}}</button>
								</td>
								<td><button data-toggle="modal"
										class="associateBtn btn btn-xs" id="Mapped" name=".NET"
										data-target="#ForecastModal">{{month[4]}}</button></td>
								<td><button data-toggle="modal"
										class="associateBtn btn btn-xs" id="Mapped" name="SDET"
										data-target="#ForecastModal">{{month[5]}}</button></td>

							</tr>
							<tr>
								<td>Confirmed</td>
								<td>
									<button data-toggle="modal" class="associateBtn btn btn-xs"
										id="Confirmed" name="JAVA" data-target="#ForecastModal">{{month[6]}}</button>
								</td>
								<td><button data-toggle="modal"
										class="associateBtn btn btn-xs" id="Confirmed" name=".NET"
										data-target="#ForecastModal">{{month[7]}}</button></td>
								<td><button data-toggle="modal"
										class="associateBtn btn btn-xs" id="Confirmed" name="SDET"
										data-target="#ForecastModal">{{month[8]}}</button></td>

							</tr>
						</tbody>
					</table>
					<ul class="nav nav-pills" style="background-color: #474c55;">
						<center><div class="btn-group">
							<button id="1" class="month btn btn-primary">January</button>
							<button id="2" class="month btn btn-primary">February</button>
							<button id="3" class="month btn btn-primary">March</button>
							<button id="4" class="month btn btn-primary">April</button>
							<button id="5" class="month btn btn-primary">May</button>
							<button id="6" class="month btn btn-primary">June</button>
							<button id="7" class="month btn btn-primary">July</button>
							<button id="8" class="month btn btn-primary">August</button>
							<button id="9" class="month btn btn-primary">September</button>
							<button id="10" class="month btn btn-primary">October</button>
							<button id="11" class="month btn btn-primary">November</button>
							<button id="12" class="month btn btn-primary">December</button>
						</div></center>
					</ul>
					
					{{selMonth}}
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
						<label for="batchSelector">Batch:</label> <select
							id="BatchSelector" style="width: 100%;" name="batch"
							class="form-control">
							<option ng-repeat="b in batches" value="{{b.trainingName}}">{{b.trainingName}}</option>
						</select>

					</div>
					<!--  
					- Input: Hidden Token for Spring Security that will allow the administrator to access
					-->
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />

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
							<option value="JAVA">Java</option>
							<option value=".NET">.NET</option>
							<option value="SDET">JTA</option>
							<option value="BIGDATA">Big Data</option>
						</select>
					</div>
					<!--  
					- Input: Hidden Token for Spring Security that will allow the administrator to access
					-->
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />

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
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />


					<!--  
					- Input: submit the form
					-->
					<button type="submit" id="addClient" class="btn btn-default">Create
						Client</button>
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


<!-----------------------
	 Available Java associates modal
 ------------------------->
 <div ng-controller="infoTable">
<div id="AvailableJavaAssociateInfo" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			
			<form ng-submit="onSubmitAvailable()">
			
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title" style="text-align: center">
					Available Associates
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
							 	<th>Employee ID</th>
							 	<th>Status</th>
							 	<th>Start Date</th>
							 	<th>End Date</th>
							 </tr>
							 </thead>
							 <tbody>
								<tr ng-repeat="a in javaAvailableList |filter:search">
									<td><input ng-model="associateSelected" type="checkbox" ng-checked="exist(a)" ng-click="toggleSelection(a)" ng-true-value="{{a.associateID}}"></td>
									<td>{{a.associateName}}</td>
									<td>{{a.associateID}}</td>
									<td>{{a.status}}</td>
									<td>{{a.batch.startDate}}</td>
									<td>{{a.batch.endDate}}</td>
								</tr>
							 </tbody>
						</table>
					</div>
			</div>
			<div class="modal-footer">
				<div class=pull-left >
					
				<br>
				<!--  The table below is the to how the dropdown button as well as the radio buttons are positioned -->
				<table>
				<tr>
					<td> <label> Select a Client:</label> </td>
					<td style="padding: 5px">
						<select ng-model="modifyStatus.clientName" class="form-control" id="sel1" name="clients" required>
		        				<option ng-repeat="t in clientList" value="{{t.clientID}}" selected>{{t.name}} {{t.location}}</option>		
		      			</select>
      				</td>
      				<td style="padding: 20px" rowspan="2">
						<button type="submit">Ok</button>
					</td>
				</tr>
				
				</table>
				</div>
				<button type="button" class="btn btn-default" data-dismiss="modal" ng-click="infoTable.refresh()">Close</button>

			</div>
			</form>
		</div>

	</div>
</div>

<!-----------------------
	 Mapped Java associates modal
 ------------------------->
<div id="MappedJavaAssociateInfo" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			
			<form ng-submit="onSubmitMapped()">
			
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title" style="text-align: center">
					Mapped Associates
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
							 	<th>Employee ID</th>
							 	<th>Status</th>
							 	<th>Start Date</th>
							 	<th>End Date</th>
							 </tr>
							 </thead>
							 <tbody>
								<tr ng-repeat="a in javaMappedList |filter:search">
									<td><input ng-model="associateSelected" type="checkbox" ng-checked="exist(a)" ng-click="toggleSelection(a)" ng-true-value="{{a.associateID}}"></td>
									<td>{{a.associateName}}</td>
									<td>{{a.associateID}}</td>
									<td>{{a.status}}</td>
									<td>{{a.batch.startDate}}</td>
									<td>{{a.batch.endDate}}</td>
								</tr>
							 </tbody>
						</table>
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
						<input ng-model="modifyStatus.status" type="radio" value="Available" name="optradio" id="test3"> 
						Available
					</label>
					<label id="confirmedRadio" class="radio-inline">
						<input ng-model="modifyStatus.status" type="radio" value="Confirmed" name="optradio" id="test2">
						Confirmed
					</label>
					</td>
      				<td style="padding: 20px" rowspan="2">
						<button type="submit">Submit</button>
					</td>
				</tr>
				</table>
				</div>
				<button type="button" class="btn btn-default" data-dismiss="modal" ng-click="infoTable.refresh()">Close</button>

			</div>
			</form>
		</div>

	</div>
</div>
<!-----------------------
	 Confirmed Java associates modal
 ------------------------->
<div id="ConfirmedJavaAssociateInfo" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			
			<form ng-submit="onSubmitConfirmed()">
			
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title" style="text-align: center">
					Confirmed Associates
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
							 	<th>Employee ID</th>
							 	<th>Status</th>
							 	<th>Start Date</th>
							 	<th>End Date</th>
							 </tr>
							 </thead>
							 <tbody>
								<tr ng-repeat="a in javaConfirmedList |filter:search">
									<td><input ng-model="associateSelected" type="checkbox" ng-checked="exist(a)" ng-click="toggleSelection(a)" ng-true-value="{{a.associateID}}"></td>
									<td>{{a.associateName}}</td>
									<td>{{a.associateID}}</td>
									<td>{{a.status}}</td>
									<td>{{a.batch.startDate}}</td>
									<td>{{a.batch.endDate}}</td>
								</tr>
							 </tbody>
						</table>
					</div>
			</div>
			<div class="modal-footer">
				<div class=pull-left >
					
				<br>
						<button type="submit">Submit</button>
				</div>
				<button type="button" class="btn btn-default" data-dismiss="modal" ng-click="infoTable.refresh()">Close</button>

			</div>
			</form>
		</div>

	</div>
</div>
<!-----------------------
	 Available .Net associates modal
 ------------------------->
<div id="AvailableNetAssociateInfo" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			
			<form ng-submit="onSubmitAvailable()">
			
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title" style="text-align: center">
					Available Associates
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
							 	<th>Employee ID</th>
							 	<th>Status</th>
							 	<th>Start Date</th>
							 	<th>End Date</th>
							 </tr>
							 </thead>
							 <tbody>
								<tr ng-repeat="a in netAvailableList |filter:search | limitTo:5">
									<td><input ng-model="associateSelected" type="checkbox" ng-checked="exist(a)" ng-click="toggleSelection(a)" ng-true-value="{{a.associateID}}"></td>
									<td>{{a.associateName}}</td>
									<td>{{a.associateID}}</td>
									<td>{{a.status}}</td>
									<td>{{a.batch.startDate}}</td>
									<td>{{a.batch.endDate}}</td>
								</tr>
							 </tbody>
						</table>
					</div>
			</div>
			<div class="modal-footer">
				<div class=pull-left >
					
				<br>
				<!--  The table below is the to how the dropdown button as well as the radio buttons are positioned -->
				<table>
				<tr>
					<td> <label> Select a Client:</label> </td>
					<td style="padding: 5px">
						<select ng-model="modifyStatus.clientName" class="form-control" id="sel1" name="clients" required>
		        				<option ng-repeat="t in clientList" value="{{t.clientid}}" selected>{{t.name}} {{t.location}}</option>		
		      			</select>
      				</td>
      				<td style="padding: 20px" rowspan="2">
						<button type="submit">Ok</button>
					</td>
				</tr>
				
				</table>
				</div>
				<button type="button" class="btn btn-default" data-dismiss="modal" ng-click="infoTable.refresh()">Close</button>

			</div>
			</form>
		</div>

	</div>
</div>

<!-----------------------
	 Mapped .Net associates modal
 ------------------------->
<div id="MappedNetAssociateInfo" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			
			<form ng-submit="onSubmitMapped()">
			
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title" style="text-align: center">
					Mapped Associates
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
							 	<th>Employee ID</th>
							 	<th>Status</th>
							 	<th>Start Date</th>
							 	<th>End Date</th>
							 </tr>
							 </thead>
							 <tbody>
								<tr ng-repeat="a in netMappedList |filter:search | limitTo:5">
									<td><input ng-model="associateSelected" type="checkbox" ng-checked="exist(a)" ng-click="toggleSelection(a)" ng-true-value="{{a.associateID}}"></td>
									<td>{{a.associateName}}</td>
									<td>{{a.associateID}}</td>
									<td>{{a.status}}</td>
									<td>{{a.batch.startDate}}</td>
									<td>{{a.batch.endDate}}</td>
								</tr>
							 </tbody>
						</table>
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
						<input ng-model="modifyStatus.status" type="radio" value="Available" name="optradio" id="test3"> 
						Available
					</label>
					<label id="confirmedRadio" class="radio-inline">
						<input ng-model="modifyStatus.status" type="radio" value="Confirmed" name="optradio" id="test2">
						Confirmed
					</label>
					</td>
      				<td style="padding: 20px" rowspan="2">
						<button type="submit">Submit</button>
					</td>
				</tr>
				</table>
				</div>
				<button type="button" class="btn btn-default" data-dismiss="modal" ng-click="infoTable.refresh()">Close</button>

			</div>
			</form>
		</div>

	</div>
</div>
<!-----------------------
	 Confirmed .Net associates modal
 ------------------------->
<div id="ConfirmedNetAssociateInfo" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			
			<form ng-submit="onSubmitConfirmed()">
			
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title" style="text-align: center">
					Confirmed Associates
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
							 	<th>Employee ID</th>
							 	<th>Status</th>
							 	<th>Start Date</th>
							 	<th>End Date</th>
							 </tr>
							 </thead>
							 <tbody>
								<tr ng-repeat="a in netConfirmedList |filter:search | limitTo:5">
									<td><input ng-model="associateSelected" type="checkbox" ng-checked="exist(a)" ng-click="toggleSelection(a)" ng-true-value="{{a.associateID}}"></td>
									<td>{{a.associateName}}</td>
									<td>{{a.associateID}}</td>
									<td>{{a.status}}</td>
									<td>{{a.batch.startDate}}</td>
									<td>{{a.batch.endDate}}</td>
								</tr>
							 </tbody>
						</table>
					</div>
			</div>
						<div class="modal-footer">
				<div class=pull-left >
					
				<br>
						<button type="submit">Submit</button>
				</div>
				<button type="button" class="btn btn-default" data-dismiss="modal" ng-click="infoTable.refresh()">Close</button>

			</div>
			</form>
		</div>

	</div>
</div>
<!-----------------------
	 Available SDet associates modal
 ------------------------->
<div id="AvailableSDETAssociateInfo" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			
			<form ng-submit="onSubmitAvailable()">
			
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title" style="text-align: center">
					Available Associates
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
							 	<th>Employee ID</th>
							 	<th>Status</th>
							 	<th>Start Date</th>
							 	<th>End Date</th>
							 </tr>
							 </thead>
							 <tbody>
								<tr ng-repeat="a in sdetAvailableList |filter:search | limitTo:5">
									<td><input ng-model="associateSelected" type="checkbox" ng-checked="exist(a)" ng-click="toggleSelection(a)" ng-true-value="{{a.associateID}}"></td>
									<td>{{a.associateName}}</td>
									<td>{{a.associateID}}</td>
									<td>{{a.status}}</td>
									<td>{{a.batch.startDate}}</td>
									<td>{{a.batch.endDate}}</td>
								</tr>
							 </tbody>
						</table>
					</div>
			</div>
			<div class="modal-footer">
				<div class=pull-left >
					
				<br>
				<!--  The table below is the to how the dropdown button as well as the radio buttons are positioned -->
				<table>
				<tr>
					<td> <label> Select a Client:</label> </td>
					<td style="padding: 5px">
						<select ng-model="modifyStatus.clientName" class="form-control" id="sel1" name="clients" required>
		        				<option ng-repeat="t in clientList" value="{{t.clientid}}" selected>{{t.name}} {{t.location}}</option>		
		      			</select>
      				</td>
      				<td style="padding: 20px" rowspan="2">
						<button type="submit">Ok</button>
					</td>
				</tr>
				
				</table>
				</div>
				<button type="button" class="btn btn-default" data-dismiss="modal" ng-click="infoTable.refresh()">Close</button>

			</div>
			</form>
		</div>

	</div>
</div>

<!-----------------------
	 Mapped SDet associates modal
 ------------------------->
<div id="MappedSDETAssociateInfo" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			
			<form ng-submit="onSubmitMapped()">
			
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title" style="text-align: center">
					Mapped Associates
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
							 	<th>Employee ID</th>
							 	<th>Status</th>
							 	<th>Start Date</th>
							 	<th>End Date</th>
							 </tr>
							 </thead>
							 <tbody>
								<tr ng-repeat="a in sdetMappedList |filter:search | limitTo:5">
									<td><input ng-model="associateSelected" type="checkbox" ng-checked="exist(a)" ng-click="toggleSelection(a)" ng-true-value="{{a.associateID}}"></td>
									<td>{{a.associateName}}</td>
									<td>{{a.associateID}}</td>
									<td>{{a.status}}</td>
									<td>{{a.batch.startDate}}</td>
									<td>{{a.batch.endDate}}</td>
								</tr>
							 </tbody>
						</table>
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
						<input ng-model="modifyStatus.status" type="radio" value="Available" name="optradio" id="test3"> 
						Available
					</label>
					<label id="confirmedRadio" class="radio-inline">
						<input ng-model="modifyStatus.status" type="radio" value="Confirmed" name="optradio" id="test2">
						Confirmed
					</label>
					</td>
      				<td style="padding: 20px" rowspan="2">
						<button type="submit">Submit</button>
					</td>
				</tr>
				
				</table>
				</div>
				<button type="button" class="btn btn-default" data-dismiss="modal" ng-click="infoTable.refresh()">Close</button>

			</div>
			</form>
		</div>

	</div>
</div>
<!-----------------------
	 Confirmed SDet associates modal
 ------------------------->
<div id="ConfirmedSDETAssociateInfo" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			
			<form ng-submit="onSubmitConfirmed()">
			
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title" style="text-align: center">
					Confirmed Associates
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
							 	<th>Employee ID</th>
							 	<th>Status</th>
							 	<th>Start Date</th>
							 	<th>End Date</th>
							 </tr>
							 </thead>
							 <tbody>
								<tr ng-repeat="a in sdetConfirmedList |filter:search | limitTo:5">
									<td><input ng-model="associateSelected" type="checkbox" ng-checked="exist(a)" ng-click="toggleSelection(a)" ng-true-value="{{a.associateID}}"></td>
									<td>{{a.associateName}}</td>
									<td>{{a.associateID}}</td>
									<td>{{a.status}}</td>
									<td>{{a.batch.startDate}}</td>
									<td>{{a.batch.endDate}}</td>
								</tr>
							 </tbody>
						</table>
					</div>
			</div>
			<div class="modal-footer">
				<div class=pull-left >
					
				<br>
						<button type="submit">Submit</button>
				</div>
				<button type="button" class="btn btn-default" data-dismiss="modal" ng-click="infoTable.refresh()">Close</button>

			</div>
			</form>
		</div>

	</div>
</div>
</div>
</html>
