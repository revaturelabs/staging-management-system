const managerCtrl = ($scope, $state, $location, $http, userService) => {
	//TODO: Must be redone to not be tied to /login/user?

  $http({
    method: 'GET',
    url: '/login/user',
  }).then((response) => {
    userService.setUser(response.data);
    if (response.data.gi=== undefined) { //It's checking username as a test. Will check role
      $state.go('associate.home');
    }
  }, () => {
    userService.setUser({});
    $state.go('login');
  });

  
  
	$scope.isActive = function (viewLocation) {
			return viewLocation === $location.path();
	};

	$scope.logout = function () {
		$http({
			method: 'GET',
			url: '/logout/',
		})
		.then((response) => {
      userService.setUser({});
			$state.go('login');
		});
	};

  $scope.manager = { name:'Joe'}; //TODO: what in tarnation, maybe remove
};

export { managerCtrl };
