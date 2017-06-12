/**
 * Created by colts on 6/8/2017.
 */
const managerCheckinsCtrl = ($scope, $http) => {
    console.log("started");


    $http.get("checkin/allTodays").then(function(result) {
        $scope.checkins = result.data;


    });

    $scope.checkIfAllSelected = (checkin) => {
      window.checkin = checkin
    }



    $scope.isSelectAll = function() {
      $scope.checkins.forEach((checkin) => {
        checkin.selected = true;
      });
    };


$scope.selectAll = true;

};

export default managerCheckinsCtrl;
