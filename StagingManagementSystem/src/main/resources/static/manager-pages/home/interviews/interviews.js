const interviewsCtrl = ($scope, $http) => {
  window.scope = $scope
  $http({
      method : "GET",
      url : "interviews/next-five-days"
  }).then(function mySuccess(response) {
      $scope.interviews = response.data;
  }, function myError(response) {
      console.log("error!");
  });

  $http.get('interviewStatus/all').then((successResponse) => {
    $scope.interviewStatuses = successResponse.data;
  }, (failResponse) => {
    console.log('failed to retreive interview statuses')
  })

  $scope.interviewSelect = (interview) => {
    //give the interview the status object from the list of statuses
    interview.interviewStatus = $scope.interviewStatuses.filter((status) => status.value === interview.interviewStatus.value)[0];
    $scope.interviewSideTable = { "interview": interview };
    $scope.idSelectedInterview = interview.id;

    window.scope = $scope
  }

};

export { interviewsCtrl };
