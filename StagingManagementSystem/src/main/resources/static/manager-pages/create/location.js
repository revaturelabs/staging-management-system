function locCtrl($scope, $http) {
  $scope.submit = () => {
    $scope.requestMade = true;
    $scope.createMessage = 'Attempting to create location';
    $scope.createMessageStyle = { color: 'black' };
    $http.post('/location', JSON.stringify($scope.location)).then((response) => {
      $scope.createMessage = 'Successfully created location';
      $scope.createMessageStyle = { color: 'green' };
    }, () => {
      $scope.createMessage = 'Failed to create location';
      $scope.createMessageStyle = { color: 'red' };
    });
  };
}


export { locCtrl };
