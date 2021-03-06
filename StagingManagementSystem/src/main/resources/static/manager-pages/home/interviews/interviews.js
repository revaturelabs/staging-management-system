const interviewsCtrl = ($scope, $http) => {
  $http({
    method: 'GET',
    url: '/interviews/next-five-days',
  }).then((response) => {
    $scope.interviews = response.data;
  }, () => {
  });

  $http.get('/interviewStatus/all').then((successResponse) => {
    $scope.interviewStatuses = successResponse.data;
  }, () => {
  });

  // configure the modal for the interview selected
  $scope.interviewSelect = (interview) => {
    // give the interview the status object from the list of statuses
    interview.interviewStatus = $scope.interviewStatuses.filter((status) => {
      return status.value === interview.interviewStatus.value;
    })[0];
    $scope.interviewSideTable = { interview: interview };
    $scope.idSelectedInterview = interview.id;

    // incase edit mode was enabled from previously viewing a different interview
    $scope.edit = false;
    $scope.requestMade = false;

    // Date time picker setup
    $('#datetimepicker1').datetimepicker({
      defaultDate: interview.scheduled,
    });
    // $(".datepicker").datepicker("update", new Date());
    $scope.showDateTimePicker = () => {
      $('#datetimepicker1').datetimepicker('show');
    };
    $('#datetimepicker1').on('dp.change', () => {
      $scope.interviewSideTable.interview.scheduled = $('#datetimepicker1').val();
    });
  }

  // update the current interview
  $scope.updateInterview = () => {
    $scope.requestMade = true;
    $scope.updateMessage = 'Attempting to update interview';
    $scope.updateMessageStyle = { color: 'black' };
    $scope.interviewSideTable.interview.scheduled = moment($scope.interviewSideTable.interview.scheduled).toDate();
    $http.put('interviews', $scope.interviewSideTable.interview).then(
      () => {
        $scope.updateMessage = 'Successfully updated interview';
        $scope.updateMessageStyle = { color: 'green' };
      },
      () => {
        $scope.updateMessage = 'Failed to update interview';
        $scope.updateMessageStyle = { color: 'red' };
      }
    );
  };
};

export { interviewsCtrl };
