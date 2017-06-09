const loginCtrl = ($scope, $http, $state) => {
  const loginBtn = document.getElementById('loginBtn');
  $scope.username = '';
  $scope.password = '';
  $scope.errorMsgShow = false;

  $scope.submit = () => {
  	loginBtn.disabled = true;
  	loginBtn.innerHTML = 'Logging in...';
    $scope.errorMsgShow = false;

    if ($scope.username === '' || $scope.username === undefined) {
      $scope.errorMsg = 'Please input a Username.';
      $scope.errorMsgShow = true;
      loginBtn.disabled = false;
    	loginBtn.innerHTML = 'Log In';
    } else if ($scope.password === '' || $scope.password === undefined) {
      $scope.errorMsg = 'Please input a Password.';
      $scope.errorMsgShow = true;
      loginBtn.disabled = false;
    	loginBtn.innerHTML = 'Log In';
    } else {
    	$http({
    		method: 'POST',
    		url: '/login',
    		data: { username: $scope.username, password: $scope.password },
    	}).then((response) => {
<<<<<<< HEAD
    		if (response.data.permission !== undefined)    			{ $state.go('manager.home'); }    		else    			{ $state.go('associate'); }
=======
    		if(response.data.permission !== undefined)
    			$state.go('manager.home');
    		else
    			$state.go('associate.home');
>>>>>>> 40e8335202f9099d2b62eaffa32ed7bc17d50acc
    	}, () => {
      $scope.errorMsg = 'Username or Password is incorrect.';
      $scope.errorMsgShow = true;
      loginBtn.disabled = false;
      	loginBtn.innerHTML = 'Log In';
    });
    }
  };
};

export default loginCtrl;
