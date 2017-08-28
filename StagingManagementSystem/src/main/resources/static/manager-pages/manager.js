function managerCtrl($scope, $state, $location, $http, userService) {
 $scope.loading = true;
	
  $http({
    method: 'GET',
    url: '/login/user',
  }).then((response) => {
    userService.setUser(response.data);
    if (response.data.is_lightning_login_user === undefined) { //TODO: Tie to role once role is properly set. Lightning user is just something in salesforceuser
      $state.go('associate.home');
    }
  }, () => {
    userService.setUser({});
    $state.go('login');
  });
  $scope.isActive = function (viewLocation) {
    return viewLocation === $location.path();
  };

  $scope.logout = function () {
    $http({
      method: 'GET',
      url: '/logout/',
    })
      .then((response) => {
        userService.setUser({});
        $state.go('login');
      });
  };
  
  $scope.currState_GetSF = 'getSF_Ready';
  $scope.updateSMS = function ($event) {

	 //console.log($event.target);
	  $event.target.innerHTML = "Loading!";
	  if( $event.target.disabled !== 'disabled') {
		$scope.currState_GetSF = 'getSF_Getting';
	    $http({
	        method: 'GET',
	        url: '/dingus', //TODO Return to being /sfdata/batches
	      })
	      .then((response) => {
	    	  $event.target.innerHTML = "Update SMS Data from Salesforce";
	    	  $event.target.disabled = 'enabled';
	    	  $scope.currState_GetSF = 'getSF_Ready';  
	    	  
	    	  $.notify($event.target, "Finished loading data!", "success");
	      }, function(response) {
	    	  $event.target.innerHTML = "Update SMS Data from Salesforce";
	    	  $event.target.disabled = 'enabled';
	    	  $scope.currState_GetSF = 'getSF_Ready';  
	    	  
	    	  $.notify($event.target, "Could not load data!", "error");
	      });
	  }
	  $event.target.disabled = 'disabled';
  }

  $scope.manager = { name: 'Joe' };
}

export { managerCtrl };
