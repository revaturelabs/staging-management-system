<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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
<script
	src="/StagingManagementSystem/JavaScripts/AddAssociateControllerScript.js"></script>
<title>SMS</title>
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

	
	
	
	<!-- Display the sample data read from get -->
	<script>
		var app = angular.module('sample', []);
		app.controller('sampleController', function($scope, $http)
		{
			$scope.submit = function()
			{
				$http
				({
					method: 'POST', url : '/getTableData'
				}).then(function(response)
					{
						$scope.sampledata = response.data;
					});
			}
		});
	</script>
		
	<div ng-app="sample" ng-controller = "sampleController" id = "sample" align = "center" ng-bind="">{{sampledata}}
	<form ng-submit = submit()>
		<input type="submit" value="here i am lord, please reach down and cool my burning angular with a drop of luck"></input>
	 </form> 
	</div>

<script>
$(document).on("click", "#addBatch", function() {

	$("#addBatchForm").unbind('submit').bind('submit', function(e) {
		e.preventDefault();

		$.ajax({
			type : 'POST',
			url : '/StagingManagementSystem/addBatch',
			data : $(this).serialize(),
			success : function(data) {
				console.log("SUCCESS: ", data);
			    $('#addBatch').modal('hide');

			},
			error : function(e) {
				console.log("ERROR: ", e);
				display(e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	});
});


$(document).on("click", "#addAssociate", function() {

	$("#addAssociateForm").unbind('submit').bind('submit', function(e) {
		e.preventDefault();

		$.ajax({
			type : 'POST',
			url : '/StagingManagementSystem/addAssociate',
			data : $(this).serialize(),
			success : function(data) {
				console.log("SUCCESS: ", data);
			    $('#addAssociate').modal('hide');

			},
			error : function(e) {
				console.log("ERROR: ", e);
				display(e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	});
});


$(document).on("click", "#addClient", function() {

	$("#addClientForm").unbind('submit').bind('submit', function(e) {
		e.preventDefault();

		$.ajax({
			type : 'POST',
			url : '/StagingManagementSystem/addClient',
			data : $(this).serialize(),
			success : function(data) {
				console.log("SUCCESS: ", data);
			    $('#addClient').modal('hide');

			},
			error : function(e) {
				console.log("ERROR: ", e);
				display(e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	});
});



</script>

</body>
</html>

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
					<div class="form-group">
						<label for="name">Full Name:</label> <input type="text"
							class="form-control" id="name" name="name" required>
					</div>
					<div class="form-group">
						<label for="associatestatus">Associate Status:</label> <select
							class="form-control" id="associatestatus" name="associatestatus">
							<option>Available</option>
							<option>Mapped</option>
							<option>Confirmed</option>
						</select>
					</div>
					<button type="submit" id="addAssociate" class="btn btn-default">Register
						Associate</button>
					<button type="reset" class="btn btn-default">Clear Form</button>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>

	</div>
</div>

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
						<label for="trainingname">Training Name: </label> <input
							type="text" class="form-control" id="trainingname"
							name="trainingname" required>
					</div>
					<div class="form-group">
						<label for="location">Location: </label> <input type="text"
							class="form-control" id="location" name="location" required>
					</div>
					<div class="form-group">
						<label for="trainer">Trainer:</label> <input type="text"
							class="form-control" id="trainer" name="trainer" required>
					</div>
					<div class="form-group">
						<label for="startdate">Start Date:</label> <input type="date"
							class="form-control" data-date-format="mm-dd-yyyy" id="startdate"
							name="startdate" required>
					</div>
					<div class="form-group">
						<label for="enddate">End Date:</label> <input type="date"
							class="form-control" data-date-format="mm-dd-yyyy" id="enddate"
							name="enddate" required>
					</div>
					<div class="form-group">
						<label for="batchtype">Batch Type:</label> <select
							class="form-control" id="batchtype" name="batchtype">
							<option value="java">Java</option>
							<option value="net">.NET</option>
							<option value="jta">JTA</option>
						</select>
					</div>
					<button type="submit" id="addBatch" class="btn btn-default">Create
						Batch</button>
					<button type="reset" class="btn btn-default">Clear Form</button>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>

	</div>
</div>

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
					<div class="form-group">
						<label for="clientname">Client Name: </label> <input type="text"
							class="form-control" id="clientname" name="clientname" required>
					</div>
					<div class="form-group">
						<label for="location">Location: </label> <input type="text"
							class="form-control" id="location" name="location" required>
					</div>
					<button type="submit" id="addClient" class="btn btn-default">Create
						Batch</button>
					<button type="reset" class="btn btn-default">Clear Form</button>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>

	</div>
</div>