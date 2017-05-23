
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
            console.log($scope.getUser);
        });
    });
    
    mainApp.controller("client", function($scope, $http) {
    	// making a call to get data for the dropdown button.
        $http.get("/StagingManagementSystem/displayClients")
        .then(function(result){
        $scope.clientList = result.data;
        $scope.lastItem = {
        	name: "Select Client"
        };
        //add a last object the list of client to inform the user the select a value.
        $scope.clientList.push ( $scope.lastItem );
        console.log($scope.clientList);
        });



/*			
 * this is a string a used to check my tables and modal from home. 
 * Since i did not have access to the database at home.
 * 
 * 
 * 	$scope.associatesList=[{associateName:'John', associateID: 25, status:'mapped'},
						{associateName:'Jessie', associateID: 30, status:'mapped'},
						{associateName:'Johanna', associateID:28, status:'mapped'},
						{associateName:'Joy', associateID:15, status:'mapped'},
						{associateName:'Mary', associateID:29, status:'mapped'},
						{associateName:'Peter', associateID:95, status:'mapped'},
						{associateName:'Sebastian', associateID:50, status:'mapped'},
						{associateName:'Erika', associateID:27, status:'mapped'},
						{associateName:'Patrick', associateID:40, status:'mapped'},
					   {associateName:'Samantha', associateID:60, status:'mapped'}];*/


        $http.get("/StagingManagementSystem/displayStats")
        .then(function(result){
        $scope.associatesList = result.data;
        console.log($scope.clientList);

        });
        // store all the associates chosen
		$scope.associateSelected =[];
        
		$scope.exist = function(item){
		return $scope.associateSelected.indexOf(item) > -1;
		}
		// de-select and select associate accordingly.  
		$scope.toggleSelection = function(item){
			var idx = $scope.associateSelected.indexOf(item);
			
			if( idx > -1 ){
				$scope.associateSelected.splice(idx, 1);
			}else{
				$scope.associateSelected.push(item);
			}
		}
		//submit the associates to be mapped. 
        $scope.onSubmit = function(){
        	//validation the make sure the select a radio button.
        	var m = document.getElementById('test1').checked;
        	var c = document.getElementById('test2').checked;
        	var a = document.getElementById('test3').checked;
        	var clientName = document.getElementById('sel1').value;

      if( m || c || a ){
    	  //validate to check if a client and an associate was selected.
    	  if(clientName != "Select Client" && $scope.associateSelected.length != "0" ){
        	var associateIds = [];
        	// add the status and the client to the list of item to send to the rest-Controller
        	associateIds.push($scope.modifyStatus.status);
        	associateIds.push($scope.modifyStatus.clientName);
        	//this add only the Ids of the associates selected. 
        	angular.forEach($scope.associateSelected, function(value, index){
        		associateIds.push(value.associateID); 
        	});
        	// this add a label to the array created. 
        	var data = {
        			associateId: associateIds
        	};
        	
        	console.log(JSON.stringify(data) );
        	// convert into a JSON object ( which is a String ).
        	var submittedData = JSON.stringify(data) ;
        	//make a call to the Rest-controller to send the array full of data
        	$http.post("/StagingManagementSystem/changeStatus", "submittedData" ).
        	success(function(data){
        		//on success, reset the modal values.
        		$scope.associateSelected =[];
        		$scope.modifyStatus.status="";
        		$scope.modifyStatus.clientName="Select Client";
        		alert("Associate(s) Successfully Updated");
        	}).error(function(data){
        		alert("Associate(s) did not Update Successfully");        		
        	});
        }else alert("Please select an associate as well as a Client!");
      }else alert("Please select a status!");
      
        	};
    });
    
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
