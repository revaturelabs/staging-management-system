const managerHomeCtrl = ($scope) => {
  $scope.view2 = 'interviews';
  $scope.selectView1 = (selectedView) => {
    if(selectedView === 'available') {
      $scope.availableSelecter = {'background-color':'gray'};
      $scope.prioritySelecter = {'background-color':'#f8f8f8'};
      $scope.view1 = 'available';
    } else if (selectedView === 'priority'){
      $scope.availableSelecter = {'background-color':'#f8f8f8'};
      $scope.prioritySelecter = {'background-color':'gray'};
      $scope.view1 = 'priorityMapped';
    }
  }
  $scope.selectView2 = (selectedView) => {
    if(selectedView === 'interviews') {
      $scope.interviewsSelecter = {'background-color':'gray'};
      $scope.checkinsSelecter = {'background-color':'#f8f8f8'};
      $scope.view2 = 'interviews';
    } else if (selectedView === 'checkins'){
      $scope.interviewsSelecter = {'background-color':'#f8f8f8'};
      $scope.checkinsSelecter = {'background-color':'gray'};
      $scope.view2 = 'checkins';
    }
  }

  //initialize our named views
  $scope.selectView1('available');
  $scope.selectView2('interviews');

};

export { managerHomeCtrl };
