const batchCtrl = ($scope, $http) => {
	console.log('starting');
  $scope.submit = () => {
  	let item = JSON.stringify($scope.batch);
  	console.log(item);
  	$http.post('/batch', item).then( (response) => {
  		console.log("success")
  	}, () => {
  		console.log("failure")
  	})
  };
};

export { batchCtrl };
