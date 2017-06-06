const managerCtrl = ($scope, $state, $location) => {
	$scope.isActive = function (viewLocation) {
			return viewLocation === $location.path();
	};
	
  $scope.manager = { name:'Joe'};

  //initialize our named views
  $scope.view1 = 'available';
  $scope.view2 = 'interviews';


  $scope.$state = $state;
  $scope.updateView2 = (view2) => {
    $scope.view2 = view2;
    $scope.$apply();
  }
};

export { managerCtrl };
