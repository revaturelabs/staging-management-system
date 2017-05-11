/**
 * 
 */

<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.8/angular.min.js"></script>

	var app = angular.module('formSubmit', []);
	
	app.controller('addAssociateController',[ '$scope', '$http', function($scope, $http) {
			
		$scope.list = [];
			$scope.headerText = 'AngularJS Post Form Spring MVC example: Submit below form';
			$scope.submit = function() {
				
				var formData = {
						"name" : $scope.name,
						"location" : $scope.location,
						"phone" : $scope.phone	
				};
				
				var response = $http.post('AddAssociateForm', formData);
				response.success(function(data, status) {
					$scope.list.push(data);
				});
				response.error(function(data, status, headers, config) {
					alert( "Exception details: " + JSON.stringify({data: data}));
				});
				
				//Empty list data after process
				$scope.list = [];
				
			};
		}]);
