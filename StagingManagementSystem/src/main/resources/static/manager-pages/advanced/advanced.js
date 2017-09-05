const defaultFilterStatus = {
	columnName: 'status',
	searchText: undefined,
};

function managerAdvancedCtrl($scope, $http, $state) {
  window.scope = $scope;
  
  $scope.$state = $state;
  
  $scope.userSearch;
  
  $scope.associateList = {};
  
  $scope.filterStatus = defaultFilterStatus; 
  
  $scope.filterList = {
		 list:
		[
			 {id : 2, name : 'Associate'},
			 {id : 3, name : 'Batch'},
			 {id : 4, name : 'Trainer'},
		 ]
  };
  
  $scope.filterList2 = {
			 list:
			[
				 {id : 1, name : 'Start Date'},
				 {id : 2, name : 'End Date'},
				 {id : 3, name : 'Batch'},
				 {id : 4, name : 'Trainer'},
			 ]
	  };

  $scope.filterType = {
		    type: $scope.filterList.list[0]
		  }
  
  $scope.filterType2 = {
		    type: $scope.filterList2.list[0]
		  }

  $http.get('batchtype/all')
    .then((data) => {
      $scope.batchtypes = data.data;
      $scope.selectedBatchTypes = [];
      $scope.batchtypes.forEach((type) => {
        $scope.selectedBatchTypes.push(type);
      });
    });

    $http.get('status/allStatusType')
    .then((data) => {
        $scope.statusTypes = data.data;            
        $scope.selectedStatusTypes = [];
        $scope.statusTypes.forEach((statusType) => {
            $scope.selectedStatusTypes.push(statusType);
        });
    });

  	$http.get('associate/all')
  	.then((response) => {
		$scope.associates = response.data;
  	});

  $http.get('batch/all')
    .then((data) => {
      $scope.batches = data.data;
    }, (data) => {
    });
  
    // fetching all project data
   $http.get('project/all')
    .then((data) => {
      $scope.projects = data.data;
    }, (data) => {
    });

  
  $scope.isAssociates = () => {
    if ($state.is('manager.advanced.allassociates')) {
      return true;
    }
    return false;
  };

  $scope.isBatches = () => {
    if ($state.is('manager.advanced.batches')) {
      return true;
    }
    return false;
  };

  $scope.isStatus = () =>{
    if($state.is('manager.advanced.status')){
      return true;
    }
    return false;
  };
  
  // button for internal projects
  $scope.isProjects = () => {
	if($state.is('manager.advanced.projects'))
	  return true;
	return false;
  };

  $scope.isInterviews = () => {
    if ($state.is('manager.advanced.interviews')) {
      return true;
    }
    return false;
  };

  $scope.trainerFilter = (associate) => {
    return true;
  };

  $scope.isSelectedBatchType = (batchType) => {
    return $scope.selectedBatchType.filter((type) => type.value === batchType.value) >= 1;
  }

  $scope.toggleSelectedBatchTypes = (selectedBatch) => {
    let idx = $scope.selectedBatchTypes.indexOf(selectedBatch);

    // Is currently selected
    if (idx > -1) {
      $scope.selectedBatchTypes.splice(idx, 1);
    } else {
      $scope.selectedBatchTypes.push(selectedBatch);
    }
  };
  
  $scope.associateBatchFilter = (associate) => {
    if (!associate.batch) {
      return false;
    }
    return $scope.selectedBatchTypes.filter((batchType) => batchType.value === associate.batch.batchType.value).length >= 1;
  };
  
  $scope.batchBatchFilter = (batch) => {
    if (!batch) {
      return false;
    }
    return $scope.selectedBatchTypes.filter((batchType) => batchType.value === batch.batchType.value).length >= 1;
  };
  
  $scope.isSelectedStatusType = (statusType) => {
		return $scope.selectedStatusTypes.some((selectedStatusType) => {
			return selectedStatusType.id === statusType.id;
		});
	}

	$scope.toggleSelectedStatusTypes = (selectedStatus) => {
		let idx = $scope.selectedStatusTypes.indexOf(selectedStatus);

		// Is currently selected
		if (idx > -1) {
			$scope.selectedStatusTypes.splice(idx, 1);
		} else {
			$scope.selectedStatusTypes.push(selectedStatus);
		}
	};
	
	function formatPortfolioStatus(portfolioStatus) {
		return portfolioStatus ? 'COMPLETE' : 'INCOMPLETE';
	};
	
	function caseInsensitiveFilter(value, searchText) {		
		return value.toLowerCase().substr(0, searchText.length) === searchText.toLowerCase();
	};
	
	$scope.formatPortfolioStatus = formatPortfolioStatus;

	$scope.associatesFilter = (associate) => {
		let searchText = $scope.filterStatus.searchText;
		let filterValue;
		
		switch ($scope.filterStatus.columnName) {
		case "associate":
			filterValue = associate.name;
			break;
		case "status":
			searchText = typeof searchText === 'undefined' ? 'staging' : searchText;
			filterValue = associate.associateStatus.status;
			break;
		case "portfolioStatus":
			filterValue = formatPortfolioStatus(associate.portfolioStatus);
			break;
		default:
			break;
		}
		
		return caseInsensitiveFilter(filterValue, searchText);
	}
}
export default managerAdvancedCtrl;
