const userCtrl = ($scope, $http) => {

  $scope.user.type = 'manager'

  $http.get('batchtype/all.json')
    .then((response) => {
		  $scope.posts = response.data
  	}, () => {
  		console.log('failure')
    });

  $scope.submit = () => {
  	let item = JSON.stringify($scope.user);
  	//need 2 different post requests for manager and associate
  	if ($scope.user.type == 'associate'){
    	$http.post('/associate', item).then( (response) => {
    		console.log('success')
    	}, () => {
    		console.log('failure')
    	})
    };
  	if ($scope.user.type == 'manager'){
    	$http.post('/manager', item).then( (response) => {
    		console.log('success')
    	}, () => {
    		console.log('failure')
    	})
    };
  };
};

export { userCtrl };
