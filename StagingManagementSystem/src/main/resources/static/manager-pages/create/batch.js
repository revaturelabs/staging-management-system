const batchCtrl = ($scope, $http) => {
  $scope.batch = {}
  $('#datetimepicker1').datetimepicker();
	$('#datetimepicker2').datetimepicker();

  $scope.showDateTimePicker = (id) => {
		$('#datetimepicker' + id ).datetimepicker("show");
	}

  $http.get('batchtype/all').then((response) => {
			$scope.batchTypes = response.data
  	}, () => {
  		// console.log('failure')
  	})

  $http.get('location/all').then((response) => {
		$scope.locations = response.data
  	}, () => {
  		console.log('failure')
  	})


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



  $scope.submit = () => {
		$scope.requestMade = true;
    $scope.createMessage = 'Attempting to create batch';
    $scope.createMessageStyle = { color: 'black' };

		// $scope.batch.startDate = moment($scope.batch.startDate).toDate();
		// $scope.batch.endDate = moment($scope.batch.endDate).toDate();

  	$http.post('/batch', JSON.stringify($scope.batch)).then( (response) => {
			$scope.createMessage = 'Successfully created batch';
  		$scope.createMessageStyle = { color: 'green' };
  	}, () => {
			$scope.createMessage = 'Failed to create batch';
      $scope.createMessageStyle = { color: 'red' };
  	})
  };
  $scope.openNewBatchTypeForm = () => {
    $scope.newBatchTypeShow = true;
    $scope.newBatchType = {};
    $scope.newBatchType.skills = [];

    $scope.addSkill = (newBatchTypeSkill) => {
      if(!newBatchTypeSkill) {
        return;
      }
      if($scope.newBatchType.skills.filter((skill) => skill.value.toUpperCase() === newBatchTypeSkill.toUpperCase()).length === 0) {
        $scope.newBatchType.skills.push({value:newBatchTypeSkill})
      }
      $scope.newBatchTypeSkill = '';
    }
    $scope.removeSkill = (skill) => {
      $scope.newBatchType.skills = $scope.newBatchType.skills.filter((skl) => skill.value != skl.value);
    }
  }
  $scope.addBatchType = (batchType) => {
    $scope.batchTypeRequestMade = true;
    $scope.addBatchTypeMessage = 'Attempting to create batch type';
    $scope.addBatchTypeMessageStyle = { color: 'black' };
    $http.post('batchtype', batchType).then((response) => {
      $scope.addBatchTypeMessage = 'Successfully created batch type';
      $scope.addBatchTypeMessageStyle = { color: 'green' };
      $scope.batchTypes.push(response.data)
    }, () => {
      $scope.addBatchTypeMessage = 'Failed to create batch type';
      $scope.addBatchTypeMessageStyle = { color: 'red' };
    })
  }
};

export { batchCtrl };
