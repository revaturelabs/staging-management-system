const managerAdvancedCtrl = ($scope, $http, $state) => {
  window.scope = $scope

  $http.get('batchtype/all')
    .then((data) => {
      $scope.batchtypes = data.data;
      $scope.selectedBatchTypes = [];
      $scope.batchtypes.forEach((type) => $scope.selectedBatchTypes.push(type))
    })

  $http.get('associate/all')
    .then((data) => {
      $scope.associates = data.data;
    }, (data) => {
      console.log('failed');
    })

  $http.get('batch/all')
    .then((data) => {
      $scope.batches = data.data;
    }, (data) => {
      console.log('failed');
    })

  $scope.isAssociates = () => {
    if($state.is('manager.advanced.allassociates'))
      return true;
    return false;
  }

  $scope.isBatches = () => {
    if($state.is('manager.advanced.batches'))
      return true;
    return false;
  }
  
  // button for internal projects
  $scope.isProjects = () => {
	if($state.is('manager.advanced.projects'))
	  return true;
	return false;
  }

  $scope.isInterviews = () => {
    if($state.is('manager.advanced.interviews'))
      return true;
    return false;
  }

  $scope.trainerFilter = (associate) => {
    return true;
  }

  $scope.isSelectedBatchType = (batchType) => {
    return $scope.selectedBatchType.filter((type) => type.value === batchType.value) >= 1;
  }

  $scope.toggleSelectedBatchTypes = (selectedBatch) => {
    let idx = $scope.selectedBatchTypes.indexOf(selectedBatch);

    // Is currently selected
    if (idx > -1) {
      $scope.selectedBatchTypes.splice(idx, 1);
    }

    // Is newly selected
    else {
      $scope.selectedBatchTypes.push(selectedBatch);
    }
  }

  $scope.associateBatchFilter = (associate) => {
    if(!associate.batch) {
      return false;
    }
    return $scope.selectedBatchTypes.filter((batchType) => batchType.value === associate.batch.batchType.value).length >= 1;
  }

  $scope.batchBatchFilter = (batch) => {
    if(!batch) {
      return false;
    }
    return $scope.selectedBatchTypes.filter((batchType) => batchType.value === batch.batchType.value).length >= 1;
  }
};

export default managerAdvancedCtrl;
