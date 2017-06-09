const managerAdvancedAssociatesCtrl = ($scope, $http) => {
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
      window.associates = data.data;
    }, (data) => {
      console.log('failed');
    })

  $scope.trainerFilter = (associate) => {
    return true;
  }

  $scope.isSelectedBatchType = (batchType) => {
    return $scope.selectedBatchType.filter((type) => type.value === batchType.value) >= 1;
  }

  $scope.toggleSelectedBatchTypes = (selectedBatch) => {
    let idx = $scope.selectedBatchTypes.indexOf(selectedBatch);
    console.log(idx)

    // Is currently selected
    if (idx > -1) {
      $scope.selectedBatchTypes.splice(idx, 1);
    }

    // Is newly selected
    else {
      $scope.selectedBatchTypes.push(selectedBatch);
    }
  }

  $scope.batchFilter = (associate) => {
    return $scope.selectedBatchTypes.filter((batchType) => batchType.value === associate.batch.batchType.value).length >= 1;
  }
};

export default managerAdvancedAssociatesCtrl;
