const clientCtrl = ($scope) => {
  $scope.client = {};
  $scope.client.name = 'client name';
  $scope.submit = () => {
  	alert('Yay!');
  }
};

export { clientCtrl };
