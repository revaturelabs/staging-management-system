function userCtrl($scope, $http) {
  $http.get('batchtype/all.json')
    .then((response) => {
      $scope.posts = response.data;
    }, () => {
    });

  $scope.submit = () => {
    $scope.requestMade = true;
    $scope.createMessage = 'Attempting to create client';
    $scope.createMessageStyle = { color: 'black' };
    $scope.user.type = 'associate';
    
    // need 2 different post requests for manager and associate
   // if ($scope.user.type == 'associate') {
    	console.log( $scope.user);
      $http.post('/associate', $scope.user).then((response) => {
        $scope.createMessage = 'Successfully created client';
        $scope.createMessageStyle = { color: 'green' };
      }, () => {
        $scope.createMessage = 'Failed to create client';
        $scope.createMessageStyle = { color: 'red' };
      });
    //}
    //Old Manager creation function. Replaced by Salesforce and salesforce login
   /* if ($scope.user.type == 'manager') {
      $http.post('/manager', $scope.user).then((response) => {
        $scope.createMessage = 'Successfully created client';
        $scope.createMessageStyle = { color: 'green' };
      }, () => {
        $scope.createMessage = 'Failed to create client';
        $scope.createMessageStyle = { color: 'red' };
      });
    }*/
  }
}

export { userCtrl };
