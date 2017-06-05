const clientCtrl = ($scope) => {
  $scope.submit = () => {
  	let item = JSON.stringify($scope.client);
  	alert(item);
  }
};

export { clientCtrl };
