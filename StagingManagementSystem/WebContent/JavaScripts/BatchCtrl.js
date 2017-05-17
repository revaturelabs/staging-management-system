/**
 * superuserhome.jsp -- retrieve batches to display in the associate modal
 */

app.controller("BatchCtrl", function($scope, $http) {
	$scope.loadBatches = function() {
		$http.get('displayBatch').then(function(response) {
			$scope.batch = response.data;
		});
	};
});