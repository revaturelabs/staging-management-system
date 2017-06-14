const batchCtrl = ($scope, $http, $state, $stateParams) => {
  window.scope = $scope;
  $scope.batch = {associates: []}

  $('#datetimepicker1').datetimepicker();
	$('#datetimepicker2').datetimepicker();

  $scope.showDateTimePicker = (id) => {
		$('#datetimepicker' + id ).datetimepicker("show");
	}

  $http.get('batchtype/all').then((response1) => {
			$scope.batchTypes = response1.data;

      $http.get('location/all').then((response2) => {
    		$scope.locations = response2.data;

        $http.get('associate/no-batch').then((response3) => {
      		$scope.associates = response3.data;

          if($state.includes('manager.advanced')) {
            $http.get('batch/' + $stateParams.id).then((response4) => {
                $http.get('associate/by-batch/' + $stateParams.id).then((response5) => {
                  $scope.batch.associates = response5.data;
                })

          			$scope.batch = response4.data;
                $scope.batch.batchType = $scope.batchTypes.filter((batchType) => batchType.value === response4.data.batchType.value)[0];
                $scope.batch.location = $scope.locations.filter((location) => location.name === response4.data.location.name)[0];
            	}, () => {
            		// console.log('failure')
            	})
          }
        	}, () => {
        		console.log('failure')
        	})
      	}, () => {
      		console.log('failure')
      	})
  	}, () => {
  		// console.log('failure')
  	})





  $scope.addAssociate = () => {
    if(!$scope.selectedAssociate) {
      return;
    }
    $scope.batch.associates.push($scope.selectedAssociate);
    $scope.associates = $scope.associates.filter((associate) => associate.id !== $scope.selectedAssociate.id);
  }

  $scope.removeAssociate = (selected) => {
    $scope.batch.associates = $scope.batch.associates.filter((associate) => associate.id !== selected.id)
    $scope.associates.push(selected);
  }


  $('#datetimepicker1').on('dp.change', () => {
    $scope.batch.startDate = $('#datetimepicker1').val();
  });

  $('#datetimepicker2').on('dp.change', () => {
    $scope.batch.endDate = $('#datetimepicker2').val();
  });



  $scope.submit = () => {
		$scope.requestMade = true;
    $scope.createMessage = 'Attempting to create batch';
    $scope.createMessageStyle = { color: 'black' };

    const batchCreation = JSON.parse(JSON.stringify($scope.batch))
		batchCreation.startDate = moment($scope.batch.startDate).toDate();
		batchCreation.endDate = moment($scope.batch.endDate).toDate();

  	$http.post('/batch', batchCreation).then( (response) => {
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
