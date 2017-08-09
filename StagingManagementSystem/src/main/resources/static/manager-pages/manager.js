const managerCtrl = ($scope, $state, $location, $http, userService) => {
  $http({
    method: 'GET',
    url: '/login/user',
  }).then((response) => {
    userService.setUser(response.data);
    if (response.data.permission === undefined) {
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

  $scope.manager = { name:'Joe'};
};

export { managerCtrl };
