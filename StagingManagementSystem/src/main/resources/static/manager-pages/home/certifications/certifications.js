const certificationManCtrl=($scope, $http, userService) =>{
	
	const commentDocument = document.getElementById("cm");
	$scope.newSelectedType={};
	
	$scope.getScheduleCert = function(){
	    $http({
	        method: 'GET',
	        url: `certifications/associate/${userService.getUser().name}`,
	      }).then(function (response) {
	    	  console.log($scope.CERTIFICATIONS.associate_id.name);
	      });
	}
	
	//get all certifications
	$scope.CERTIFICATIONS = {};
	$scope.noCert = true;
	$http({
		method: 'GET',
		url: 'certifications/all',
	})
	.then((response) => {
		angular.forEach(response.data, function(value, key){
			if(value.comments == null||value.comments===""){
				value.comments = "....";
			}
			if(value.cert_filename == null||value.cert_filename===""){
				value.cert_filename = "....";
			}

		});
		
		
		$scope.CERTIFICATIONS = response.data;
		
		console.log($scope.CERTIFICATIONS);

	});
	
	//filter associate by certification
	$scope.hasNoCert= function(CERTIFICATIONS){
		if(CERTIFICATIONS.cert_status=="completed"){
			return false;
		}
		return CERTIFICATIONS;
	}
	
	$scope.hasCert = function(CERTIFICATIONS){
		if(CERTIFICATIONS.cert_status=="pending" || CERTIFICATIONS.cert_status=="inprogress" ){
			return false;
		}
		return CERTIFICATIONS;
	}
	//click on certification list
	$scope.clickedCert = function(x){
		$scope.newSelectedType=x.associate_id.name;
		$scope.selectedType = x.cert_type;
		$scope.newFormkey = x.cert_status;
		$scope.selectedDate = x.cert_testdate;
		$scope.selectedComment = x.comments;
		$scope.selectedFilename = x.cert_filename;
		$scope.CERTIFICATIONS.cert_id = x.cert_id;
		  console.log($scope.newSelectedType);
		$("#lookUpCert").modal('show');
	}
	
	$scope.updateApprove = function(x){
		let newDate = moment($scope.selectedDate).toDate();
        $scope.cert_id= $scope.CERTIFICATIONS.cert_id;
        $scope.cert_type= $scope.selectedType.type_of_cert;
        console.log("i pass ",$scope.newSelectedType);
        //$scope.associate_id=$scope.CERTIFICATIONS.associate_id.id;
     
        $scope.newFormkey = "completed";
               $http({
                 method: 'PUT',
                 url: 'certifications',
                 data: { associate_id:$scope.newSelectedType, cert_id:$scope.CERTIFICATIONS.cert_id, cert_testdate:newDate,  cert_status:$scope.newFormkey="completed",cert_type:$scope.selectedType, comments:$scope.selectedComment, cert_filename:$scope.selectedFilename },
               })
               .then((response) => {
            	   console.log($scope.CERTIFICATIONS.associate_id.name);
                   console.log($scope.CERTIFICATIONS.cert_id);
                   getScheduleCert();
               }); 
		};
		
		$scope.denied=function(){
			if($scope.CERTIFICATIONS.comments ){
			    $http({
	                 method: 'DELETE',
	                 url: 'certifications',
	                 data: { associate_id:$scope.newSelectedType, cert_id:$scope.CERTIFICATIONS.cert_id, cert_testdate:newDate,  cert_status:$scope.newFormkey="completed",cert_type:$scope.selectedType, comments:$scope.selectedComment, cert_filename:$scope.selectedFilename },
	               })
	               .then((response) => {
	            	   console.log($scope.CERTIFICATIONS.associate_id.name);
	                   console.log($scope.CERTIFICATIONS.cert_id);
	                   getScheduleCert();
	               }); 
			}
		}
		
	}


export default certificationManCtrl ;