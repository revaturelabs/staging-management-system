const managerCtrl = ($scope, $state, $location) => {
	console.log("there")
	$scope.isActive = function (viewLocation) {
			return viewLocation === $location.path();
	};
  $scope.manager = { name:'Joe'};
};

export { managerCtrl };
