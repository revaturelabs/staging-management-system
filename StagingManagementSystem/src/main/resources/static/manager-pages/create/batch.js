const batchCtrl = ($scope, $http) => {
  $('#datetimepicker1').datetimepicker();
	$('#datetimepicker2').datetimepicker();

  $http.get('batchtype/all').then((response) => {
			$scope.batchTypes = response.data
  	}, () => {
  		console.log("failure")
  	})

  $http.get('location/all').then( (response) => {
		$scope.locations = response.data
  	}, () => {
  		console.log("failure")
  	})

  $scope.submit = () => {
		$scope.requestMade = true;
    $scope.createMessage = 'Attempting to create batch';
    $scope.createMessageStyle = { color: 'black' };

		$scope.batch.startDate = moment($scope.batch.startDate).toDate();
		$scope.batch.endDate = moment($scope.batch.endDate).toDate();

  	$http.post('/batch', JSON.stringify($scope.batch)).then( (response) => {
			$scope.createMessage = 'Successfully created batch';
  		$scope.createMessageStyle = { color: 'green' };
  	}, () => {
			$scope.createMessage = 'Failed to create batch';
      $scope.createMessageStyle = { color: 'red' };
  	})
  };
};

export { batchCtrl };
