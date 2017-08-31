function managerCtrl($scope, $state, $location, $http, userService, $rootScope) {
 $rootScope.loading = false;
	
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
  
  $scope.getLogo = function(){ 
	  $scope.loading = true;
	  let style = {};
	  if($http.pendingRequests.length !== 0 ){
		  style.width = '95px';
		  //This is done because the gif is slightly larger than the image and it shouldn't expand the border
		  style.transform = 'translate(-1.65px, 16.2px)';
		  style.margin= '-20px 0 0 0px';
	  } else {
		  style.width = '90px';
		  style.transform = 'translate(0px, 0px)';
		  $scope.loading = false;
  		}
	  console.log($http.pendingRequests.length)
	  return style;
  	}
  
  $scope.currState_GetSF = 'getSF_Ready';
  $scope.updateSMS = function ($event) {

	  $event.target.innerHTML = "Loading!";
	  if( $event.target.disabled !== 'disabled') {
		$scope.currState_GetSF = 'getSF_Getting';
	    $http({
	        method: 'GET',
	        url:  '/sfdata/batches'
	      })
	      .then((response) => { //Successes
	    	  $event.target.innerHTML = "Update SMS Data from Salesforce";
	    	  $event.target.disabled = 'enabled';
	    	  $scope.currState_GetSF = 'getSF_Ready';  
	    	  $.notify($event.target, "Finished loading data!", "success");
	    	  
	      }, function(response) { //Errors
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
