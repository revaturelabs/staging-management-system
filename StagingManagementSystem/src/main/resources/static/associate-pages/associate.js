const associateCtrl = ($scope, $location, $http, $state) => {
	$http({
		method: 'GET',
		url: '/login/isAssociate',
	})
	.then((response) => {
		if(!response.data)
			$state.go('login');
		else {
			$http({
				method: 'GET',
				url: '/checkin',
			})
			.then((response) => {
				if(response.data === true) {
					$scope.checkInBtn = "Checked In";
					$scope.hasCheckedIn = true;
				}
				else
					$scope.checkInBtn = "Check In";
			});
		}
	});

	$scope.hasCheckedIn = false;
	$scope.isActive = function (viewLocation) {
    return viewLocation === $location.path();
	};

	$scope.checkIn = function () {
		$http({
			method: 'PUT',
			url: '/checkin',
		})
		.then((response) => {
			if(response.data === true) {
				$scope.checkInBtn = "Checked In";
				$scope.hasCheckedIn = true;
			}
		});
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
