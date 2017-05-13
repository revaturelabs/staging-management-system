var app = angular.module('formSubmit', []);
app
		.controller(
				'AddAssociateController',
				[
						'$scope',
						'$http',
						function($scope, $http) {

							$scope.list = [];
							$scope.headerText = 'AngularJS Post Form Spring MVC example: Submit below form';
							$scope.submit = function($scope, $http) {

								$http({
									method : 'POST',
									url : 'AddAssociateForm',
									data : $httpParamSerializer(formData), // pass
								}).success(function(data) {
									alert(response.data);
								}).error(
										function(data) {
											alert("Exception details: "
													+ JSON.stringify({
														data : data
													}));
										});

							};
						} ]);