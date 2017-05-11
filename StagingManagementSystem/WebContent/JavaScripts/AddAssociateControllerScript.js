/**
 * 
 */

	var app = angular.module('formSubmit', []);
	
	app.controller('AddAssociateController',[ '$scope', '$http', function($scope, $http) {
			
		$scope.list = [];
			$scope.headerText = 'AngularJS Post Form Spring MVC example: Submit below form';
			$scope.submit = function() {
				/*
				var formData = {
						"name" : $scope.name,
						"location" : $scope.location,
						"phone" : $scope.phone	
				};*/
				var formData = $scope.fields;
				console.log(formData);
				var whatever = JSON.stringify(formData);
				console.log(whatever);
				var response = $http.post('AddAssociateForm', whatever);
				response.success(function(data) {
					$scope.list.push(response.data);
				});
				response.error(function(data) {
					alert( "Exception details: " + JSON.stringify({data: data}));
				});
				
				//Empty list data after process
				$scope.list = [];
				
			};
		}]);
