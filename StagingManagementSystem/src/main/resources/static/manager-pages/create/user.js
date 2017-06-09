const userCtrl = ($scope, $http) => {
  $scope.submit = () => {
  	const item = JSON.stringify($scope.user);
  	// need 2 different post requests for manager and associate
  	console.log(item);
  	console.log($scope.user.type == 'Associate');
  	if ($scope.user.type == 'Associate') {
  		console.log('in associate');
  	$http.post('/associate', item).then((response) => {
  		console.log('success');
  	}, () => {
  		console.log('failure');
  	});
  }
    if ($scope.user.type == 'Manager') {
      console.log('in manager');
  	$http.post('/manager', item).then((response) => {
  		console.log('success');
  	}, () => {
  		console.log('failure');
  	});
    }
  };
};

export { userCtrl };
