function jobCtrl($scope, $http) {
  $scope.job = {};
  $('#datetimepicker1').datetimepicker();
  $('#datetimepicker2').datetimepicker();
  $('#datetimepicker3').datetimepicker();
  $('#datetimepicker4').datetimepicker();
  $('#datetimepicker5').datetimepicker();

  $scope.showDateTimePicker = (id) => {
    $('#datetimepicker' + id ).datetimepicker('show');
  };

  $http.get('associate/all').then((response) => {
    // takes a while for associates to load...
    $scope.associates = response.data;
  }, () => {
  });

  $http.get('client/all').then((response) => {
    $scope.clients = response.data;
  }, () => {
  });

  $('#datetimepicker1').on('dp.change', () => {
    $scope.job.startDate = $('#datetimepicker1').val();
  });

  $('#datetimepicker2').on('dp.change', () => {
    $scope.job.projectedEndDate = $('#datetimepicker2').val();
  });

  $('#datetimepicker3').on('dp.change', () => {
    $scope.job.endDate = $('#datetimepicker3').val();
  });

  $('#datetimepicker4').on('dp.change', () => {
    $scope.job.buyoutDate = $('#datetimepicker4').val();
  });

  $('#datetimepicker5').on('dp.change', () => {
    $scope.job.confirmedDate = $('#datetimepicker5').val();
  });

  $scope.submit = () => {
    $scope.requestMade = true;
    $scope.createMessage = 'Attempting to create job';
    $scope.createMessageStyle = { color: 'black' };

    let jobCreation = JSON.parse(JSON.stringify($scope.job));
    jobCreation.startDate = moment($scope.job.startDate).toDate();
    jobCreation.projectedEndDate = moment($scope.job.projectedEndDate).toDate();
    jobCreation.endDate = moment($scope.job.endDate).toDate();
    jobCreation.buyoutDate = moment($scope.job.buyoutDate).toDate();
    jobCreation.confirmedDate = moment($scope.job.confirmedDate).toDate();

    $http.post('/job', jobCreation).then((response) => {
      $scope.createMessage = 'Successfully created job';
      $scope.createMessageStyle = { color: 'green' };
    }, () => {
      $scope.createMessage = 'Failed to create job';
      $scope.createMessageStyle = { color: 'red' };
    });
  };
}

export { jobCtrl };
