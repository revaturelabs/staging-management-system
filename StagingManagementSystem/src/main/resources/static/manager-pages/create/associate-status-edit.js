export function associateStatusEditController($scope, $http, $state, $stateParams) {
	$scope.loading = true;
	$scope.submitting = false;
	$scope.submitted = false;
	$scope.error = false;
	$scope.associate = undefined;
	$scope.statusTypes = undefined;

	$('#statusModal').modal('show');
	
	$('#statusModal').on('hide.bs.modal', () => {
		$state.go('manager.advanced.status', {}, { reload: true });
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

		$http.put('associate/updateAssociateStatus', $scope.associate).then(() => {
			$scope.submitting = false;
			$scope.submitted = true;
		}).catch(() => {
			$scope.submitting = false;			
			$scope.error = true;
		});
	}
};