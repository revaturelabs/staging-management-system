const loginCtrl = ($scope, $http, $window) => {
	$scope.username = "";
	$scope.password = "";
	$scope.errorMsgShow = false;
	
  $scope.submit = () => {
  	$scope.errorMsgShow = false;
  	
  	if($scope.username === "" || $scope.username === undefined) {
  		$scope.errorMsg = "Please input a Username.";
	    $scope.errorMsgShow = true;
  	}
  	else if($scope.password === "" || $scope.password === undefined) {
  		$scope.errorMsg = "Please input a Password.";
	    $scope.errorMsgShow = true;
  	}
  	else {
  		$http({
    	  method: 'POST',
    	  url: '/login',
    	  data: { username: $scope.username, password: $scope.password },
    	}).then(function successCallback(response) {
    			$window.location.href = "/manager";
    	  }, function errorCallback(response) {
    	    $scope.errorMsg = "Username or Password is incorrect.";
    	    $scope.errorMsgShow = true;
    	  });
  	}
  }
};

export { loginCtrl };
