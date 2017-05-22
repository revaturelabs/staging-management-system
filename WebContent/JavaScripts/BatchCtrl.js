/**
 * superuserhome.jsp -- retrieve batches to display in the associate modal
 */

var mainApp = angular.module('superuser', []);

/*
 * mainApp.controller('formCtrl', function($scope) { $scope.master = { firstName :
 * "John", lastName : "Doe" }; $scope.reset = function() { $scope.user =
 * angular.copy($scope.master); }; $scope.reset(); });
 * 
 * 
 * mainApp.controller("sampleController", function($scope,$http){ //makes a
 * $scope.getUser = []; $http.get("/StagingManagementSystem/getTableData")
 * .then(function(result){ $scope.getUser = [result.data]; console.log(getUser);
 * console.log(result.data); }); });
 */

// // $scope is the application object (the owner of application variables and
// functions).
// app.controller('sampleController', function($scope,$http){
// //makes a
// $scope.getUser = function()
// {
// $http.get("/StagingManagementSystem/getTableData")
// .then(function(result){
// $scope.getUser = [result.data];
// console.log(getUser);
// console.log(result.data);
// });
// };
// });
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
		$http.get("/StagingManagementSystem/displayWeeks").then(function(result) {
			$scope.weeks = result.data;
			console.log(weeks);
			console.log(result.data);
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

