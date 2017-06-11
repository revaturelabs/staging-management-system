/**
 * Created by colts on 6/8/2017.
 */
const managerCheckinsCtrl = ($scope, $http) => {
    console.log("started")


    $http.get("checkin/allTodays").then(function(result) {
        $scope.checkins = result.data;
    });


};

export default managerCheckinsCtrl;