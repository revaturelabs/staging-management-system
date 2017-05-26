/*******************************************************************************
 * 
 * All the controllers
 * 
 ******************************************************************************/
var mainApp = angular.module('superuser', []);

mainApp.controller("infoTable", function($scope, $http) {

	var subm;	 
	 
		/*
		 * All the following is for the current tab.
		 * Return the number of Available associate
		 */
			$http.get("/StagingManagementSystem/displayCurrentJavaAvailable").then(function(result) {
				$scope.javaAvailable = result.data;
			});
			$http.get("/StagingManagementSystem/displayCurrentNETAvailable").then(function(result) {
				$scope.netAvailable = result.data;
			});	
			$http.get("/StagingManagementSystem/displayCurrentSDETAvailable").then(function(result) {
				$scope.sdetAvailable = result.data;
			});
			
			/*
			 * Return the number of Mapped associate
			 */
			$http.get("/StagingManagementSystem/displayCurrentJavaMapped").then(function(result) {
				$scope.javaMapped = result.data;
			});
			$http.get("/StagingManagementSystem/displayCurrentNETMapped").then(function(result) {
				$scope.netMapped = result.data;
			});	
			$http.get("/StagingManagementSystem/displayCurrentSDETMapped").then(function(result) {
				$scope.sdetMapped = result.data;
			});
			
			/*
			 * Return the number of Confirmed associate
			 */
			$http.get("/StagingManagementSystem/displayCurrentJavaConfirmed").then(function(result) {
				$scope.javaConfirmed = result.data;
			});
			$http.get("/StagingManagementSystem/displayCurrentNETConfirmed").then(function(result) {
				$scope.netConfirmed = result.data;
			});	
			$http.get("/StagingManagementSystem/displayCurrentSDETConfirmed").then(function(result) {
				$scope.sdetConfirmed = result.data;
			});
			
			/*
			 * Return the List of Available associate
			 */
			
			$http.get("/StagingManagementSystem/displayCurrentJavaAvailableList").then(function(result) {
				$scope.javaAvailableList = result.data;
			});
			$http.get("/StagingManagementSystem/displayCurrentNETAvailableList").then(function(result) {
				$scope.netAvailableList = result.data;
			});	
			$http.get("/StagingManagementSystem/displayCurrentSDETAvailableList").then(function(result) {
				$scope.sdetAvailableList = result.data;
			});
			/*
			 * Return the List of Mapped associate
			 */
			$http.get("/StagingManagementSystem/displayCurrentJavaMappedList").then(function(result) {
				$scope.javaMappedList = result.data;
			});
			$http.get("/StagingManagementSystem/displayCurrentNETMappedList").then(function(result) {
				$scope.netMappedList = result.data;
			});	
			$http.get("/StagingManagementSystem/displayCurrentSDETMappedList").then(function(result) {
				$scope.sdetMappedList = result.data;
			});
			
			/*
			 * Return the List of Confirmed associate
			 */
			$http.get("/StagingManagementSystem/displayCurrentJavaConfirmedList").then(function(result) {
				$scope.javaConfirmedList = result.data;
			});
			$http.get("/StagingManagementSystem/displayCurrentNETConfirmedList").then(function(result) {
				$scope.netConfirmedList = result.data;
			});	
			$http.get("/StagingManagementSystem/displayCurrentSDETConfirmedList").then(function(result) {
				$scope.sdetConfirmedList = result.data;
			});
	//	}

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

	$http.get("/StagingManagementSystem/displayStats").then(function(result) {
		$scope.associatesList = result.data;
		console.log($scope.associatesList);

	});

	// submit the associates to be mapped.
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

	$(".associateBtn").hide();

	$(".month").on("click",	function(e) {
		subm = e.target.id;

		$http.get("/StagingManagementSystem/getMonth?month=" + subm)
		.then(function(result) {
			$scope.month = result.data;
			console.log(month);
			console.log(result.data);
		});

		$(".associateBtn").on("click", function(e) {
			var status = e.target.id;
			var type = e.target.name;

							$http.get(
									"/StagingManagementSystem/getAssociates?month="
											+ subm + "&status=" + status
											+ "&type=" + type).then(
									function(result) {
										$scope.associates = result.data;
										console.log(associates);
										console.log(result.data);
									});

							$(".statusheader").html(status);
							$(".typeheader").html(type);
							var stat = $("#status").val(status);

						});

				$(".associateBtn").show();

				$scope.submitAssociates = function() {
					// validation the make sure the select a radio button.
					var status = $scope.modifyStatus.status;
					var clientName;

					if ($scope.associateSelected.length != "0") {
						// validate to check if a client and an associate was
						// selected.
						var associateIds = [];
						// add the status and the client to the list of item to
						// send to
						// the rest-Controller
						if (status == "Available") {
							clientName = $('#sel1').val();
						}
						// this add only the Ids of the associates selected.
						angular.forEach($scope.associateSelected, function(
								value, index) {
							associateIds.push(value.associateID);
						});
						// this add a label to the array created.
						var data = {
							associateId : associateIds,
							status : status
						};

						// convert into a JSON object ( which is a String ).
						var submittedData = JSON.stringify(data);
						console.log(submittedData);
						// make a call to the controller to send the array full
						// of
						// data
						$http.post("/StagingManagementSystem/updateAssociates",
								submittedData).success(function(data) {
							// on success, reset the modal values.
							$scope.associateSelected = [];
							$scope.modifyStatus.status = "";
							// close the modal
							$('#ForecastModal').modal('hide');
							
							// find some way to refresh the table asynchronously
							
						}).error(function(data) {
							alert("Associate(s) did not Update Successfully");
						});
					} else
						alert("Please select an associate!");

				};
			});
	
	$scope.onSubmitAvailable = function() {
		var clientName = $scope.modifyStatus.clientName ;
			// validate to check if a client and an associate was selected.
		if(	$scope.associateSelected.length != "0") {
			if (clientName != "Select Client"){
				var associateIds = [];
				var client;
				// add the status and the client to the list of item to send to
				// the rest-Controller
				client = $scope.modifyStatus.clientName;
				// this add only the Ids of the associates selected.
				angular.forEach($scope.associateSelected,
						function(value, index) {
							associateIds.push(value.associateID);
						});
				// this add a label to the array created.
				var data = {
					associateId : associateIds,
					client: client
				};

				// convert into a JSON object ( which is a String ).
				var submittedData = JSON.stringify(data);
				// make a call to the Rest-controller to send the array full of
				// data
				$http.post("/StagingManagementSystem/updateAvailableAssociates", submittedData )
				.success(function(data) {
					// on success, reset the modal values.
					$scope.associateSelected = [];
					$scope.modifyStatus.clientName = "Select Client";
					alert("Associate(s) Successfully Updated");
					refresh();
				}).error(function(data) {
					alert("Associate(s) did not Update Successfully");
				});
				
			}else alert("Please select a Client!");
	} else
				alert("Please select an associate");
	};
	
	$scope.onSubmitMapped = function() {
		// validation the make sure the select a radio button.
		var c = document.getElementById('test2').checked;
		var a = document.getElementById('test3').checked;
		var clientName = document.getElementById('sel1').value;

		if($scope.associateSelected.length != "0") {
		if ( c || a) {
			// validate to check if a client and an associate was selected.
				var associateIds = [];
				var status;
				// add the status and the client to the list of item to send to
				// the rest-Controller
				status = $scope.modifyStatus.status;
				// this add only the Ids of the associates selected.
				angular.forEach($scope.associateSelected,
						function(value, index) {
							associateIds.push(value.associateID);
						});
				// this add a label to the array created.
				var data = {
					associateId : associateIds,
					status: status
				};
				
				// convert into a JSON object ( which is a String ).
				var submittedData = JSON.stringify(data);
				console.log(submittedData);
				// make a call to the controller to send the array full of
				// data
				$http.post("/StagingManagementSystem/updateMappedAssociates", submittedData )
				.success(function(data) {
					// on success, reset the modal values.
					$scope.associateSelected = [];
					$scope.modifyStatus.status = "";
					alert("Associate(s) Successfully Updated");
					refresh();
				}).error(function(data) {
					alert("Associate(s) did not Update Successfully");
				});
			} else
				alert("Please select a status!");
		} else
			alert("Please select an associate!");

	};
	
	$scope.onSubmitConfirmed = function() {
		if(	$scope.associateSelected.length != "0") {
				var associateIds = [];

				// this add only the Ids of the associates selected.
				angular.forEach($scope.associateSelected,
						function(value, index) {
							associateIds.push(value.associateID);
						});
				// this add a label to the array created.
				var data = {
					associateId : associateIds
				};

				// convert into a JSON object ( which is a String ).
				var submittedData = JSON.stringify(data);
				// make a call to the Rest-controller to send the array full of
				// data
				$http.post("/StagingManagementSystem/updateConfirmedAssociates", submittedData )
				.success(function(data) {
					// on success, reset the modal values.
					$scope.associateSelected = [];
				//	$scope.modifyStatus.clientName = "Select Client";
					alert("Associate(s) Successfully Updated");
					refresh();
				}).error(function(data) {
					alert("Associate(s) did not Update Successfully");
				});
			} else
				alert("Please select an associate!");

	};
	
	function refresh(){
		$http.get("/StagingManagementSystem/displayCurrentJavaAvailable").then(function(result) {
			$scope.javaAvailable = result.data;
		});
		$http.get("/StagingManagementSystem/displayCurrentNETAvailable").then(function(result) {
			$scope.netAvailable = result.data;
		});	
		$http.get("/StagingManagementSystem/displayCurrentSDETAvailable").then(function(result) {
			$scope.sdetAvailable = result.data;
		});
		
		/*
		 * Return the number of Mapped associate
		 */
		$http.get("/StagingManagementSystem/displayCurrentJavaMapped").then(function(result) {
			$scope.javaMapped = result.data;
		});
		$http.get("/StagingManagementSystem/displayCurrentNETMapped").then(function(result) {
			$scope.netMapped = result.data;
		});	
		$http.get("/StagingManagementSystem/displayCurrentSDETMapped").then(function(result) {
			$scope.sdetMapped = result.data;
		});
		
		/*
		 * Return the number of Confirmed associate
		 */
		$http.get("/StagingManagementSystem/displayCurrentJavaConfirmed").then(function(result) {
			$scope.javaConfirmed = result.data;
		});
		$http.get("/StagingManagementSystem/displayCurrentNETConfirmed").then(function(result) {
			$scope.netConfirmed = result.data;
		});	
		$http.get("/StagingManagementSystem/displayCurrentSDETConfirmed").then(function(result) {
			$scope.sdetConfirmed = result.data;
		});
		
		/*
		 * Return the List of Available associate
		 */
		
		$http.get("/StagingManagementSystem/displayCurrentJavaAvailableList").then(function(result) {
			$scope.javaAvailableList = result.data;
		});
		$http.get("/StagingManagementSystem/displayCurrentNETAvailableList").then(function(result) {
			$scope.netAvailableList = result.data;
		});	
		$http.get("/StagingManagementSystem/displayCurrentSDETAvailableList").then(function(result) {
			$scope.sdetAvailableList = result.data;
		});
		/*
		 * Return the List of Mapped associate
		 */
		$http.get("/StagingManagementSystem/displayCurrentJavaMappedList").then(function(result) {
			$scope.javaMappedList = result.data;
		});
		$http.get("/StagingManagementSystem/displayCurrentNETMappedList").then(function(result) {
			$scope.netMappedList = result.data;
		});	
		$http.get("/StagingManagementSystem/displayCurrentSDETMappedList").then(function(result) {
			$scope.sdetMappedList = result.data;
		});
		
		/*
		 * Return the List of Confirmed associate
		 */
		$http.get("/StagingManagementSystem/displayCurrentJavaConfirmedList").then(function(result) {
			$scope.javaConfirmedList = result.data;
		});
		$http.get("/StagingManagementSystem/displayCurrentNETConfirmedList").then(function(result) {
			$scope.netConfirmedList = result.data;
		});	
		$http.get("/StagingManagementSystem/displayCurrentSDETConfirmedList").then(function(result) {
			$scope.sdetConfirmedList = result.data;
		});

};

});

mainApp.controller("BatchCtrl", function($scope, $http) {

	$http.get("/StagingManagementSystem/displayBatch").then(function(result) {
		$scope.batches = result.data;
		console.log(batches);
		console.log(result.data);
	});
});
