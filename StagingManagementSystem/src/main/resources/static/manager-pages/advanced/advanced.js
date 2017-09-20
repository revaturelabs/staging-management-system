function managerAdvancedCtrl($scope, $http, $state) {
  window.scope = $scope;
  
  $scope.$state = $state;
 
  $scope.ordering = ['name', 'name'];
  $scope.reverse = false;
  $scope.statusSelection = 'training';
  
  $scope.changeOrdering = function(option){
	  
	  if($scope.ordering[0] == option) { //Lets you click twice to reverse order but prevents it on the first click to change type
		  if($scope.reverse)
			  $scope.reverse=false;
		  else
			  $scope.reverse=true;
	  }
	  else
		  $scope.reverse = false; //Make sure the first time you order it, its not reversed
	  
	  $scope.ordering = [option, 'name']; //Keeps them alphabetical, even after changing ordering type
  }
  
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

  
  $scope.filterStatusList = {
			 list:
			[
				 {id : 1, name : 'Staging'},
				 {id : 2, name : 'Project'},
				 {id : 3, name : 'Bench'},
				 {id : 4, name : 'Training'},
				 {id : 4, name : 'All'},
			 ]
	  };

  $scope.filterType = {
		    type: $scope.filterList.list[0]
		  }
  
  $scope.filterType2 = {
		    type: $scope.filterList2.list[0]
		  }

  //Default Status filter for status
  $scope.userSearch =  {
		  associateStatus : {
			  status: 'Staging'
		  } 
  };
  
  //Default dropdown menu selection
  $scope.statusSelection = {
		    type: $scope.filterStatusList.list[0]
		  }
  
  $scope.selectStatusFilter = function (statusSelection) {
	  if (statusSelection === "All")
		  $scope.userSearch.associateStatus.status = undefined; //Seems like a janky fix, works though
	  else
		  $scope.userSearch.associateStatus.status = statusSelection;
  }
  
  
  //Portfolio Status Page
  //=====================
  $scope.displayPortfolioStatus = function (status) {
	  if(status)
		  return "Approved"
	  else
		  return "Pending Approval"
			 
  }
  
  $scope.selectedAssociates = {};
  $scope.selectAllAssociates = function(checked) {
	  for (let i = 0; i < $scope.associates.length; i++) {
		let associate = $scope.associates[i];
		
		if(associate.associateStatus.status.includes("STAGING")){ //Gotta make sure people not in staging dont get changed
			if(checked){
				$scope.selectedAssociates[associate.id] = true;
			}
			else $scope.selectedAssociates[associate.id] = undefined;
		}
	  }
  }
  
  $scope.massUpdatePortfolioStatus = (selection) => {		
	  
	 //Not efficient. Wanted to keep track of the array position of the associate and just update it like that, but I couldn't get that on the html/angularjs side to send in the array position.
	  $.each($scope.selectedAssociates, function(index, value){
		  for (let i = 0; i < $scope.associates.length; i++) {
			  
				if($scope.associates[i].associateStatus.status.includes("STAGING") && $scope.associates[i].id == index && value != undefined){ //Gotta make sure people not in staging dont get changed
					 $scope.associates[i].portfolioStatus = selection;
					$http.put('associate/updateAssociateStatus', $scope.associates[i]).then(() => {
					}).catch(() => {
						$scope.associates[i].portfolioStatus = !selection;
					});
					break;
				}
			  } 
	  });
  } 
  //=====================
  
$scope.updateAll = function(){
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
}

  
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
  
  // button for internal projects
  $scope.isProjects = () => {
	if($state.is('manager.advanced.projects'))
	  return true;
	return false;
  };
  
  //button to get to portfolio management
  $scope.isPortfolios = () => {
		if($state.is('manager.advanced.portfolios'))
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
	
	$scope.closeAssociateModal = () => {
		  $('#associateModal').modal('hide');
		  $scope.updateAll();
	  };
	  
	  $scope.closeBatchModal = () => {
		  $('#batchModal').modal('hide');
		  $scope.updateAll();
	  };
	  
	  $scope.closeProjectModal = () => {
		  $('#projectModal').modal('hide');
		  $scope.updateAll();
	  };
	  
	  $scope.updateAll();
	
}
export default managerAdvancedCtrl;
