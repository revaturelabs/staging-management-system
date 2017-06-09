const userCtrl = ($scope, $http) => {

  $http.get('batchtype/all.json').then( (response) => {
		// console.log(response.data[0].id)
		console.log(response.data)
		$scope.posts = response.data
  	}, () => {
  		console.log("failure")
  	})


  $scope.submit = () => {
  	let item = JSON.stringify($scope.user);
  	//need 2 different post requests for manager and associate
  	console.log(item);
  	console.log($scope.user.type == 'associate');
  	if ($scope.user.type == 'associate'){
  		console.log('in associate');
  	$http.post('/associate', item).then( (response) => {
  		console.log("success")
  	}, () => {
  		console.log("failure")
  	})
};
	if ($scope.user.type == 'manager'){
		console.log('in manager');
  	$http.post('/manager', item).then( (response) => {
  		console.log("success")
  	}, () => {
  		console.log("failure")
  	})
};
  };
};

export { userCtrl };
