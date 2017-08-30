export function statusController($scope, $http, $state) {
	$scope.associates = [];
	$scope.selectedStatusTypes = [];
  	$scope.isLoadingAssociates = true;

	$http.get('status/allStatusType').then((response) => {
        $scope.statusTypes = response.data;            
        $scope.statusTypes.forEach((statusType) => {
            $scope.selectedStatusTypes.push(statusType);
        });
    });

  	$http.get('associate/all').then((response) => {
		$scope.associates = response.data;
		$scope.isLoadingAssociates = false;
  	});

  	$scope.isSelectedStatusType = (statusType) => {
		return $scope.selectedStatusTypes.some((selectedStatusType) => {
			return selectedStatusType.id === statusType.id;
		});
	}

	$scope.toggleSelectedStatusTypes = (selectedStatus) => {
		let idx = $scope.selectedStatusTypes.indexOf(selectedStatus);

		// Is currently selected
		if (idx > -1) {
			$scope.selectedStatusTypes.splice(idx, 1);
		} else {
			$scope.selectedStatusTypes.push(selectedStatus);
		}
	};

	$scope.associatesFilter = (associate) => {
		var selectedStatusTypes = $scope.selectedStatusTypes;

		if (selectedStatusTypes.length === 0) {
			return false;
		}

		return selectedStatusTypes.some((selectedStatusType) => {
			return associate.associateStatus.associateStatusId === selectedStatusType.associateStatusId;
		});
	}
}	