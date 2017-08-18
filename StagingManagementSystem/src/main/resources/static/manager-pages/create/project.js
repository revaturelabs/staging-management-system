const projectCtrl = function($scope, $http, $state, $stateParams) {
	$scope.project = {associates: []}

	$http.get('associate/no-project').then(function(response1) {
		$scope.associates = response1.data;
		if($state.includes('manager.advanced')) {
			$http.get('project/' + $stateParams.id).then(function(response2) {
				$http.get('associate/by-project/' + $stateParams.id).then(function(response3) {
					$scope.project.associates = response3.data;
				})
					$scope.project = response2.data;
			})
		}
	});
	
	$scope.active = function(){
		
	}
	
	$scope.addAssociate = function()  {
		    if(!$scope.selectedAssociate) {
		      return;
		    }
		    $scope.project.associates.push($scope.selectedAssociate);
		    $scope.associates = $scope.associates.filter(function(associate){ 
		    	associate.id !== $scope.selectedAssociate.id
		    	});
		  }



    $scope.removeAssociate = function(selected) {
    	$scope.project.associates = $scope.project.associates.filter(function(associate) {
    		associate.id !== selected.id
    	})
    		$scope.associates.push(selected);
    };

    $scope.submit = function() {
    	$scope.requestMade = true;
    	$scope.createMessage = 'Attempting to create project';
    	$scope.createMessageStyle = { color: 'black' };
    	if($scope.project.projectDescription==null){
    		$scope.project.projectDescription = "No project description.";
    	}

    	const projectCreation = JSON.parse(JSON.stringify($scope.project))

    	$http.post('/project', projectCreation).then(function (response) {
    		$scope.createMessage = 'Successfully created project';
    		$scope.createMessageStyle = { color: 'green' };
    	}, function() {
    		$scope.createMessage = 'Failed to create project';
    		$scope.createMessageStyle = { color: 'red' };
    	})
    };
};

export { projectCtrl };
