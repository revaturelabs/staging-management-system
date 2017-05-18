/**
 * superuserhome.jsp -- retrieve batches to display in the associate modal
 */
var app = angular.module('sample', []);

    app.controller('formCtrl', function($scope) {
        $scope.master = {
            firstName : "John",
            lastName : "Doe"
        };
        $scope.reset = function() {
            $scope.user = angular.copy($scope.master);
        };
        $scope.reset();
    });
                        

    app.controller("sampleController", function($scope,$http){
        //makes a 
         $scope.getUser = [];
        $http.get("/StagingManagementSystem/getTableData")
        .then(function(result){
            $scope.getUser = [result.data];
            console.log(getUser);
            console.log(result.data);
        });
    });
    
    
//  // $scope is the application object (the owner of application variables and functions).
//  app.controller('sampleController', function($scope,$http){
//      //makes a 
//      $scope.getUser =  function()
//      {
//          $http.get("/StagingManagementSystem/getTableData")
//          .then(function(result){
//              $scope.getUser = [result.data];
//              console.log(getUser);
//              console.log(result.data);
//          });
//      };
//  });

    
    
    /**************************************
     * 
     * Ariel's Stuff - Don't Touch
     * 
     **************************************/

    
    var mainApp = angular.module('superuser', []);

// $scope is the application object (the owner of application variables and
// functions).
mainApp.controller("BatchCtrl", function($scope, $http) {
	// makes a
	$http.get("/StagingManagementSystem/displayBatch")
	.then(function(result) {
		$scope.batches = result.data;
		console.log(batches);
		console.log(result.data);
	});
});