const locCtrl = ($scope, $http) => {
  $scope.submit = () => {
  	let item = JSON.stringify($scope.client);
  	console.log(item);
  	$http.post('/client', item).then( (response) => {
  		console.log("success")
  	}, () => {
  		console.log("failure")
  	})
  };
};


export { locCtrl };
