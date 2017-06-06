const associateCtrl = ($scope, $location) => {
	$scope.checkInBtn = "Check In";
	$scope.hasCheckedIn = false;
	$scope.isActive = function (viewLocation) { 
    return viewLocation === $location.path();
	};
	
	$scope.checkIn = function () {
		$scope.checkInBtn = "Checked In";
		$scope.hasCheckedIn = true;
	};
};

export default associateCtrl;