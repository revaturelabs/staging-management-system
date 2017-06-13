const managerCtrl = ($scope, $state, $location, $http, userService) => {
	$http({
		method: 'GET',
		url: '/login/isManager',
	})
	.then((response) => {
		if(!response.data)
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
