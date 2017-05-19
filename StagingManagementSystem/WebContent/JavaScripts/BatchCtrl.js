/**
 * superuserhome.jsp -- retrieve batches to display in the associate modal
 */

    var mainApp = angular.module('superuser',["ngTable"]);


    mainApp.controller("associateTableCtrl", function($scope , $http) {
        $scope.master = {
            firstName : "John",
            lastName : "Doe"
        };

    });
                        
//    mainApp.controller("BatchCtrl", function($scope, $http) {
//    	// makes a
//    	$http.get("/StagingManagementSystem/displayBatch")
//    	.then(function(result) {
//    		$scope.batches = result.data;
//    		console.log(batches);
//    		console.log(result.data);
//    	});
//    });
    mainApp.controller("sampleController", function($scope,$http){
        //makes a 
         
        $http.get("/StagingManagementSystem/getTableData")
        .then(function(result){
            $scope.getUser = result.data;
            console.log(getUser);
        });
    });
    
    mainApp.controller("client", function($scope, $http) {
    	
        $http.get("/StagingManagementSystem/displayClients")
        .then(function(result){
        $scope.clientList = result.data;
        console.log(clientList);	
        console.log(result.data);
        });
        
        $http.get("/StagingManagementSystem/displayStats")
        .then(function(result){
        $scope.associatesList = result.data;
        console.log(clientList);	
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
