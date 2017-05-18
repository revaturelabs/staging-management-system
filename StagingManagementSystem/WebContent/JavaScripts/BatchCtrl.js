/**
 * superuserhome.jsp -- retrieve batches to display in the associate modal
 */
var mainApp = angular.module('superuser', []);

// $scope is the application object (the owner of application variables and
// functions).
mainApp.controller("BatchCtrl", function($scope, $http) {
	// makes a
	$http.get("/StagingManagementSystem/displayBatch")
	.then(function(result) {
		$scope.batches = result.data;
		console.log(batches);
		console.log(result.data);
	});
});