function associateCtrl($scope, $location, $http, $state, userService) {
  const authenticatedUser = userService.getUser();
  const checkBtnDOM = document.getElementById('checkBtn');
  $scope.checkInBtn = 'Loading...';
  checkBtnDOM.disabled = true;

  const isAssociate = authenticatedUser.id !== undefined &&
    authenticatedUser.permission === undefined;

  if (!isAssociate) {
    $state.go('login');
    return;
  }

  $http({
    method: 'GET',
    url: '/checkin',
  })
    .then((response) => {
      if (response.data === true) {
        $scope.checkInBtn = 'Checked In';
        $scope.hasCheckedIn = true;
      } else {
        $scope.checkInBtn = 'Check In';
        checkBtnDOM.disabled = false;
      }
    });

  $scope.hasCheckedIn = false;
  $scope.isActive = viewLocation => viewLocation === $location.path();

  $scope.checkIn = () => {
    $http({
      method: 'PUT',
      url: '/checkin',
    })
      .then((response) => {
        if (response.data === true) {
          $scope.checkInBtn = 'Checked In';
          $scope.hasCheckedIn = true;
        }
      });
  };

  $scope.logout = () => {
    $http({
      method: 'GET',
      url: '/logout/',
    })
      .then(() => {
        userService.setUser({});
        $state.transitionTo('login');
      });
  };
}

export default associateCtrl;
