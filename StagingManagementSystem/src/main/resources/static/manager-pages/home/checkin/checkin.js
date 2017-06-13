/**
 * Created by colts on 6/8/2017.
 */

var app = angular.module('myApp', []);
app.controller('checkinCtrl', function($scope, $http) {
    $http({
        method : "GET",
        url : "checkin/todaysCheckins"
    }).then(function mySuccess(response) {
        $scope.checkins = response.data;
    }, function myError(response) {
        console.log("error!");
    });


});