const assoiciateCtrl= function($scope,$http,$state, $stateParams){
	$scope.certification={};
	$('#dateforpicker').dateforexam();
	 $scope.showDateTimePicker = (id) => {
			$('#datetimepicker' + id ).datetimepicker("show");
		}

	  $http.get('associate/all').then( (response) => {
	  	//takes a while for associates to load...
			$scope.associates = response.data
	  	}, () => {
	  		console.log("failure")
	  	})

	    $http.get('certification/all').then( (response) => {
			$scope.clients = response.data
	  	}, () => {
	  		console.log("failure")
	  	})

	  $('#datetimepicker').on('dp.change', () => {
	    $scope.job.startDate = $('#datetimepicker').val();
	  });
}
$scope.submit = () => {
   	$scope.requestMade = true;
    $scope.createMessage = 'Attempting to create job';
    $scope.createMessageStyle = { color: 'black' };

    let jobCreation = JSON.parse(JSON.stringify($scope.job))
  	jobCreation.startDate = moment($scope.certification.startDate).toDate();
  	
    $http.post('/certifications', jobCreation).then((response) => {
        $scope.createMessage = 'Successfully created job';
        $scope.createMessageStyle = { color: 'green' };
      }, () => {
        $scope.createMessage = 'Failed to create job';
        $scope.createMessageStyle = { color: 'red' };
      })
  };
}

export { associateCtrl };
