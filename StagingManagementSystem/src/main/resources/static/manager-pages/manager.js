const managerCtrl = ($scope, $state, $location, $http) => {
	$scope.isActive = function (viewLocation) {
			return viewLocation === $location.path();
	};

	$scope.logout = function () {
		$http({
			method: 'GET',
			url: '/logout/',
		})
		.then((response) => {
			$state.go('login');
		});
	};

  $scope.manager = { name:'Joe'};
};

export { managerCtrl };
