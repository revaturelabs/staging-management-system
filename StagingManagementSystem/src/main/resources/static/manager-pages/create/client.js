function clientCtrl($scope, $http) {
  // $scope.requestMade = false;
  $scope.submit = () => {
    $scope.requestMade = true;
    $scope.createMessage = 'Attempting to create client';
    $scope.createMessageStyle = { color: 'black' };
    let item = JSON.stringify($scope.client);
    $http.post('/client', item).then(() => {
      $scope.createMessage = 'Successfully created client';
      $scope.createMessageStyle = { color: 'green' };
    }, () => {
      $scope.createMessage = 'Failed to create client';
      $scope.createMessageStyle = { color: 'red' };
    });
  };
}
export { clientCtrl };
