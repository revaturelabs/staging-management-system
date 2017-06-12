const interviewsCtrl = ($scope, $http) => {
  window.scope = $scope
  $http({
      method : "GET",
      url : "interviews/next-five-days"
  }).then(function mySuccess(response) {
      $scope.interviews = response.data;
      // response.data.forEach((interview) {
  		// 	let day = new Date(response.data[0].scheduled[0], response.data[0].scheduled[1], response.data[0].scheduled[2], response.data[0].scheduled[3],
  		// 			response.data[0].scheduled[4], response.data[0].scheduled[5], 0);
  		// 	e['day'] = dateformat(day, "dddd, mmmm dS, yyyy, h:MM TT");;
  		// });
  }, function myError(response) {
      console.log("error!");
  });

  $http.get('interviewStatus/all').then((successResponse) => {
    $scope.interviewStatuses = successResponse.data;
  }, (failResponse) => {
    console.log('failed to retreive interview statuses')
  })

  // configure the modal for the interview selected
  $scope.interviewSelect = (interview) => {
    //give the interview the status object from the list of statuses
    interview.interviewStatus = $scope.interviewStatuses.filter((status) => status.value === interview.interviewStatus.value)[0];
    $scope.interviewSideTable = { "interview": interview };
    $scope.idSelectedInterview = interview.id;

    // incase edit mode was enabled from previously viewing a different interview
    $scope.edit = false;

    // Date time picker setup
    $('#datetimepicker1').datetimepicker({
      "defaultDate": interview.scheduled
    });
    // $(".datepicker").datepicker("update", new Date());
  	$scope.showDateTimePicker = () => {
  		$('#datetimepicker1').datetimepicker("show");
  	}
  	$("#datetimepicker1").on("dp.change", function() {
      $scope.interviewSideTable.interview.scheduled = $("#datetimepicker1").val();
  	});
  }

  // update the current interview
  $scope.updateInterview = () => {
    $scope.requestMade = true;
    $scope.updateMessage = 'Attempting to update interview';
    $scope.updateMessageStyle = { 'color': 'white' };
    $scope.interviewSideTable.interview.scheduled =moment($scope.interviewSideTable.interview.scheduled).toDate()
    $http.put('interviews', $scope.interviewSideTable.interview).then(
      () => {
        $scope.updateMessage = 'Successfully updated interview';
        $scope.updateMessageStyle = { 'color': 'green' };
      }, () => {
        $scope.updateMessage = 'Failed to update interview';
        $scope.updateMessageStyle = { 'color': 'red' };
      }
    )
  }

};

export { interviewsCtrl };
