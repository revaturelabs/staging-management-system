/**
 * Created by colts on 6/8/2017.
 */

var app = angular.module('checkin', []);
app.controller('checkinCtrl', function($scope, $http) {


    $http.get("checkin/allTodays").then(function(result) {
        $scope.checkins = result.data;
    });


});