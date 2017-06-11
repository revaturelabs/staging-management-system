const interviewsCtrl = ($scope, $http) => {
    console.log("started")
    $http({
        method : "GET",
        url : "interviews/all"
    }).then(function mySuccess(response) {
        window.interviews = response.data
        $scope.interviews = response.data;
    }, function myError(response) {
        console.log("error!");
    });

    $scope.interviewSelect = (interview) => {
      $scope.interviewSideTable = { "interview": interview };
    }
};

export { interviewsCtrl };
