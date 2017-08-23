function loginCtrl($scope, $http, $state, userService) {
  const loginBtn = document.getElementById('loginBtn');
  $scope.username = '';
  $scope.password = '';
  $scope.errorMsgShow = false;

  const authenticatedUser = userService.getUser();

  const isAssociate = authenticatedUser.id !== undefined;
  const isManager = authenticatedUser.is_lightning_login_user !== undefined; //TODO: Change to role whenever that gets in

  if (isManager) {
    $state.go('manager.home');
  }
  if (isAssociate) {
    $state.go('associate.home');
  }

  $scope.submit = () => {
    loginBtn.disabled = true;
    loginBtn.innerHTML = 'Logging in...';
    $scope.errorMsgShow = false;

    if ($scope.username === '' || $scope.username === undefined) {
      $scope.errorMsg = 'Please input a Username.';
      $scope.errorMsgShow = true;
      loginBtn.disabled = false;
      loginBtn.innerHTML = 'Log In';
    } else if ($scope.password === '' || $scope.password === undefined) {
      $scope.errorMsg = 'Please input a Password.';
      $scope.errorMsgShow = true;
      loginBtn.disabled = false;
      loginBtn.innerHTML = 'Log In';
    } else {
      $http({ //Login post request
        method: 'POST',
        url: '/login',
        data: { username: $scope.username, password: $scope.password },
      })
        .then((response) => {
          userService.setUser(response.data); //NOTE: Anything to do with manager login is handled through salesforce login and handling. See manager.js
        }, () => {
          $scope.errorMsg = 'Username or Password is incorrect.';
          $scope.errorMsgShow = true;
          loginBtn.disabled = false;
          loginBtn.innerHTML = 'Log In';
        });
    }
  };
}

export default loginCtrl;