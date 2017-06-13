const jobCtrl = ($scope, $http) => {

  $http.get('associate/all.json').then( (response) => {
  	//takes a while for associates to load...
		$scope.associates = response
  	}, () => {
  		console.log("failure")
  	})

    $http.get('client/all.json').then( (response) => {
		$scope.clients = response
  	}, () => {
  		console.log("failure")
  	})

 $(function () {
      $('#datetimepicker1').datetimepicker();
  });

  $(function () {
      $('#datetimepicker2').datetimepicker();
  });

  $(function () {
      $('#datetimepicker3').datetimepicker();
  });

  $(function () {
      $('#datetimepicker4').datetimepicker();
  });

  $(function () {
      $('#datetimepicker5').datetimepicker();
  });

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
  	let item = JSON.stringify($scope.job);
  	console.log(item);
  	$http.post('/job', item).then( (response) => {
  		console.log("success")
  	}, () => {
  		console.log("failure")
  	})
  };
};


export { jobCtrl };
