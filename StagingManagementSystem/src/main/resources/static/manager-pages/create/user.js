const userCtrl = ($scope) => {
  $scope.submit = () => {
  	let item = JSON.stringify($scope.user);
  	alert(item);
  }
};

export { userCtrl };
