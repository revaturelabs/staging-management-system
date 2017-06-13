const userCtrl = ($scope, $http) => {
  $http.get('batchtype/all.json')
    .then((response) => {
		  $scope.posts = response.data
  	}, () => {
  		console.log('failure')
    });

  $scope.submit = () => {
    $scope.requestMade = true;
    $scope.createMessage = 'Attempting to create client';
    $scope.createMessageStyle = { color: 'black' };

  	//need 2 different post requests for manager and associate
  	if ($scope.user.type == 'associate'){
    	$http.post('/associate', $scope.user).then((response) => {
        $scope.createMessage = 'Successfully created client';
        $scope.createMessageStyle = { color: 'green' };
    	}, () => {
        $scope.createMessage = 'Failed to create client';
        $scope.createMessageStyle = { color: 'red' };
    	})
    };
  	if ($scope.user.type == 'manager'){
    	$http.post('/manager', $scope.user).then( (response) => {
        $scope.createMessage = 'Successfully created client';
        $scope.createMessageStyle = { color: 'green' };
    	}, () => {
        $scope.createMessage = 'Failed to create client';
        $scope.createMessageStyle = { color: 'red' };
    	})
    };
  };
};

export { userCtrl };
