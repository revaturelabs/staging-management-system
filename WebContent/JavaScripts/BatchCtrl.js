/**
 * superuserhome.jsp -- retrieve batches to display in the associate modal
 */

var mainApp = angular.module('superuser', []);

mainApp.controller("client", function($scope, $http) {
	// making a call to get data for the dropdown button.
	$http.get("/StagingManagementSystem/displayClients").then(function(result) {
		$scope.clientList = result.data;
		$scope.lastItem = {
			name : "Select Client"
		};
		// add a last object the list of client to inform the user the select a
		// value.
		$scope.clientList.push($scope.lastItem);
		console.log($scope.clientList);
	});

	$http.get("/StagingManagementSystem/displayStats").then(function(result) {
		$scope.associatesList = result.data;
		console.log($scope.associatesList);

	});

	$scope.associateSelected = [];

	$scope.exist = function(item) {
		return $scope.associateSelected.indexOf(item) > -1;
	}
	// de-select and select associate accordingly.
	$scope.toggleSelection = function(item) {
		var idx = $scope.associateSelected.indexOf(item);

		if (idx > -1) {
			$scope.associateSelected.splice(idx, 1);
		} else {
			$scope.associateSelected.push(item);
		}
	}
	// submit the associates to be mapped.
	$scope.onSubmit = function() {
		// validation the make sure the select a radio button.
		var m = document.getElementById('test1').checked;
		var c = document.getElementById('test2').checked;
		var a = document.getElementById('test3').checked;
		var clientName = document.getElementById('sel1').value;

		if (m || c || a) {
			// validate to check if a client and an associate was selected.
			if (clientName != "Select Client"
					&& $scope.associateSelected.length != "0") {
				var associateIds = [];
				// add the status and the client to the list of item to send to
				// the rest-Controller
				associateIds.push($scope.modifyStatus.status);
				associateIds.push($scope.modifyStatus.clientName);
				// this add only the Ids of the associates selected.
				angular.forEach($scope.associateSelected,
						function(value, index) {
							associateIds.push(value.associateID);
						});
				// this add a label to the array created.
				var data = {
					associateId : associateIds
				};

				console.log(JSON.stringify(data));
				// convert into a JSON object ( which is a String ).
				var submittedData = JSON.stringify(data);
				// make a call to the Rest-controller to send the array full of
				// data
				$http.post("/StagingManagementSystem/updateAssociates",
						"submittedData").success(function(data) {
					// on success, reset the modal values.
					$scope.associateSelected = [];
					$scope.modifyStatus.status = "";
					$scope.modifyStatus.clientName = "Select Client";
					alert("Associate(s) Successfully Updated");
				}).error(function(data) {
					alert("Associate(s) did not Update Successfully");
				});
			} else
				alert("Please select an associate as well as a Client!");
		} else
			alert("Please select a status!");

	};
});

/*******************************************************************************
 * 
 * Ariel's Stuff - Don't Touch
 * 
 ******************************************************************************/

mainApp.controller("infoTable", function($scope, $http) {
	$http.get("/StagingManagementSystem/displayCurrent").then(function(result) {
		$scope.current = result.data;
		console.log(current);
		console.log(result.data);
	});

	$scope.getForecast = function() {
		$http.get("/StagingManagementSystem/displayWeeks").then(
				function(result) {
					$scope.weeks = result.data;
					console.log(weeks);
					console.log(result.data);
				});
	};
	
	$(".month").on("click", function(e) {

	});
});

mainApp.controller("BatchCtrl", function($scope, $http) {

	$http.get("/StagingManagementSystem/displayBatch").then(function(result) {
		$scope.batches = result.data;
		console.log(batches);
		console.log(result.data);
	});
});
