const batchCtrl = ($scope, $http) => {
	console.log('starting');

  $(function () {
      $('#datetimepicker1').datetimepicker();
  });

  $(function () {
      $('#datetimepicker2').datetimepicker();
  });

  $('#datetimepicker1').on('dp.change', () => {
    $scope.batch.startDate = $('#datetimepicker1').val();
   var now = new Date($scope.batch.startDate).toISOString(); 
   $scope.batch.startDate = now;
  });

  $('#datetimepicker2').on('dp.change', () => {
    $scope.batch.endDate = $('#datetimepicker2').val();
    var now2 = new Date($scope.batch.endDate).toISOString(); 
    $scope.batch.endDate = now2;
  });

  $http.get('batchtype/all.json').then( (response) => {
		// console.log(response.data[0].id)
		console.log(response)
		$scope.posts2 = response
  	}, () => {
  		console.log("failure")
  	})

  $http.get('location/all.json').then( (response) => {
		$scope.posts = response
  	}, () => {
  		console.log("failure")
  	})



  $scope.submit = () => {

  	let item = (JSON.stringify($scope.batch));
  	console.log(item)

  	let str = item.replace(/\\/g, '')
  	let str2 = str.replace('"{', '{')
  	let str3 = str2.replace('}"', '}')
  	let str4 = str3.replace('"{', '{')
  	let str5 = str4.replace('}"', '}')
  	let str6 = str5.replace('"{', '{')
  	let str7 = str6.replace('}"', '}')
  	console.log(str7);
  	$http.post('/batch', str7).then( (response) => {
  		console.log("success")
  		console.log(response)
  	}, () => {
  		console.log("failure")
  	})
  };
};

export { batchCtrl };
