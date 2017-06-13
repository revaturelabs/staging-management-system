const jobCtrl = ($scope, $http) => {
  window.scope = $scope;
  $('#datetimepicker1').datetimepicker();
  $('#datetimepicker2').datetimepicker();
  $('#datetimepicker3').datetimepicker();
  $('#datetimepicker4').datetimepicker();
  $('#datetimepicker5').datetimepicker();

  $http.get('associate/all').then( (response) => {
  	//takes a while for associates to load...
		$scope.associates = response.data
  	}, () => {
  		console.log("failure")
  	})

    $http.get('client/all').then( (response) => {
		$scope.clients = response.data
  	}, () => {
  		console.log("failure")
  	})

  $('#datetimepicker1').on('dp.change', () => {
    $scope.job.startDate = $('#datetimepicker1').val();
   var now = new Date($scope.job.startDate).toISOString(); 
   $scope.job.startDate = now;
  });

  $('#datetimepicker2').on('dp.change', () => {
    $scope.job.projectedEndDate = $('#datetimepicker2').val();
    var now2 = new Date($scope.job.projectedEndDate).toISOString(); 
    $scope.job.projectedEndDate = now2;
  });

  $('#datetimepicker3').on('dp.change', () => {
    $scope.job.endDate = $('#datetimepicker3').val();
    var now3 = new Date($scope.job.endDate).toISOString(); 
    $scope.job.endDate = now3;
  });

  $('#datetimepicker4').on('dp.change', () => {
    $scope.job.buyoutDate = $('#datetimepicker4').val();
    var now4 = new Date($scope.job.buyoutDate).toISOString(); 
    $scope.job.buyoutDate = now4;
  });

  $('#datetimepicker5').on('dp.change', () => {
    $scope.job.confirmedDate = $('#datetimepicker5').val();
    var now5 = new Date($scope.job.confirmedDate).toISOString(); 
    $scope.job.confirmedDate = now5;
  });





  $scope.submit = () => {
 	$scope.requestMade = true;
  $scope.createMessage = 'Attempting to create job';
  $scope.createMessageStyle = { color: 'black' };
	$scope.job.startDate = moment($scope.job.startDate).toDate();
	$scope.job.projectedEndDate = moment($scope.job.projectedEndDate).toDate();
	$scope.job.endDate = moment($scope.job.endDate).toDate();
	$scope.job.buyoutDate = moment($scope.job.buyoutDate).toDate();
	$scope.job.confirmedDate = moment($scope.job.confirmedDate).toDate();

  $http.post('/job', JSON.stringify($scope.job)).then((response) => {
      $scope.createMessage = 'Successfully created job';
      $scope.createMessageStyle = { color: 'green' };
    }, () => {
      $scope.createMessage = 'Failed to create job';
      $scope.createMessageStyle = { color: 'red' };
    })
  };
}

export { jobCtrl };
