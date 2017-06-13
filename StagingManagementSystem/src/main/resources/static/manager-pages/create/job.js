const jobCtrl = ($scope, $http) => {
  window.scope = $scope;
  $http.get('associate/all').then( (response) => {
  	//takes a while for associates to load...
		$scope.associates = response
  	}, () => {
  		console.log("failure")
  	})

    $http.get('client/all').then( (response) => {
		$scope.clients = response
  	}, () => {
  		console.log("failure")
  	})

  $('#datetimepicker1').datetimepicker();
  $('#datetimepicker2').datetimepicker();
  $('#datetimepicker3').datetimepicker();
  $('#datetimepicker4').datetimepicker();
  $('#datetimepicker5').datetimepicker();

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
