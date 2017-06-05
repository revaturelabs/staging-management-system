const managerCtrl = ($scope, $state) => {
  $scope.manager = { name:'Joe'};
  $scope.view2 = 'interviews';
  $scope.$state = $state;
  $scope.updateView2 = (view2) => {
    $scope.view2 = view2;
    $scope.$apply();
  }
};

export { managerCtrl };
