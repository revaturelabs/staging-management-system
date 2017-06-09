const batchCtrl = ($scope, $http) => {
	console.log('starting');

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

$scope.open = function($event) {
        $event.preventDefault();
        $event.stopPropagation();

        $scope.obj = {
          opened : true
          };
      };

  $scope.submit = () => {
    $scope.batch.startDate = $filter('date')($scope.batch.startDate, "dd/MM/yyyy", "UTC");
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
