/**
 * 
 */

app.controller("BatchCtrl", function($scope, $http) {
	$scope.loadBatches = function() {
		$http.get('displayBatch').then(function(response) {
			$scope.batch = response.data;
		});
	};
});