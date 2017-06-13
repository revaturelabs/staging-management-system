const interviewsCtrl = ($scope, $http) => {
    $http({
        method : "GET",
        url : "interviews/all"
    }).then(function mySuccess(response) {
        $scope.interviews = response.data;
    }, function myError(response) {
        console.log("error!");
    });


};

export { interviewsCtrl };
