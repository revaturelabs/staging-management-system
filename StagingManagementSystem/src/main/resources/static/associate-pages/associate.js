const associateCtrl = ($scope, $location, $http, $state) => {
	$scope.checkInBtn = "Check In";
	$scope.hasCheckedIn = false;
	$scope.isActive = function (viewLocation) { 
    return viewLocation === $location.path();
	};
	
	$scope.checkIn = function () {
		$scope.checkInBtn = "Checked In";
		$scope.hasCheckedIn = true;
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
};

export default associateCtrl;