export function associateStatusEditController($scope, $http, $state, $stateParams) {
	$scope.loading = true;
	$scope.submitting = false;
	$scope.submitted = false;
	$scope.error = false;
	$scope.associate = undefined;
	$scope.statusTypes = undefined;

	$('#statusModal').modal('show');
	
	$('#statusModal').on('hide.bs.modal', () => {
		$state.go('manager.advanced.status', {}, { reload: $scope.submitted });
	});
	
	Promise.all([
		$http.get(`associate/by-identifier/${$stateParams.id}`),
		$http.get('status/allStatusType')
	]).then(([associateResponse, statusTypesResponse]) => {
		$scope.associate = associateResponse.data;
		$scope.statusTypes = statusTypesResponse.data;
		$scope.loading = false;
		$scope.$digest();
	});

	$scope.updateAssociateStatus = () => {		
		$scope.submitting = true;
		$scope.submitted = false;
		$scope.error = false;
		
		// Hack! Business logic on the front-end.
		switch ($scope.associate.associateStatus.associateStatusId) {
		case 1:
			$scope.associate.associateStatus.status = 'STAGING';
			break;
		case 2:
			$scope.associate.associateStatus.status = 'PROJECT';
			break;
		case 3:
			$scope.associate.associateStatus.status = 'BENCH';
			$scope.associate.portfolioStatus = false;
			break;
		default:
			$scope.associate.associateStatus.status = 'TRAINING';
			break;
		}

		$http.put('associate/updateAssociateStatus', $scope.associate).then(() => {
			$scope.submitting = false;
			$scope.submitted = true;
		}).catch(() => {
			$scope.submitting = false;			
			$scope.error = true;
		});
	}
};