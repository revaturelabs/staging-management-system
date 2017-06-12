const batchCtrl = ($scope, $http) => {
	console.log('starting');

  $(function () {
      $('#datetimepicker1').datetimepicker();
  });

  $(function () {
      $('#datetimepicker2').datetimepicker();
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
    console.log($scope.batch.startDate)
    var now = new Date($scope.batch.startDate).toISOString(); 
    $scope.batch.startDate = now;
      var now2 = new Date($scope.batch.endDate).toISOString(); 
    $scope.batch.endDate = now2;
    console.log(now);
    //var now_utc = new Date(now.getUTCFullYear(), now.getUTCMonth(), now.getUTCDate(),  now.getUTCHours(), now.getUTCMinutes(), now.getUTCSeconds());
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
