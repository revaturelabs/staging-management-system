const batchCtrl = ($scope, $http) => {
	console.log($scope.batch);
  $scope.submit = () => {
  	let item = JSON.stringify($scope.batch);
  	alert(item);
  			// var res = $http.post('/batch', $scope.batch);
		// res.success(function(data, status, headers, config) {
		// 	$scope.message = data;
		// });
		// res.error(function(data, status, headers, config) {
		// 	alert( "failure message: " + JSON.stringify({data: data}));
		// });	
  };
};

export { batchCtrl };
