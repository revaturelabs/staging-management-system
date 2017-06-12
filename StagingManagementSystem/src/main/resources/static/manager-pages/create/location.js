const locCtrl = ($scope, $http) => {
  $scope.submit = () => {
  	let item = JSON.stringify($scope.location);
  	console.log(item);
  	$http.post('/location', item).then( (response) => {
  		console.log("success")
  	}, () => {
  		console.log("failure")
  	})
  };
};


export { locCtrl };
