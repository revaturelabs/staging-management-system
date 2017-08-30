function certificationCtrl($scope,$http,$state, $stateParams,$filter,$timeout,userService){
	$scope.certifications ={};
	$scope.certificationtype ={};
	
	const updateCert = document.getElementById('updateNewCert');
	

	
	$scope.getScheduleCert = function(){
	    $http({
	        method: 'GET',
	        url: `certifications/associate/${userService.getUser().id}`,
	      }).then(function (response) {
	    	  $scope.CERTIFICATIONS = response.data;
	      });
	}
	  $scope.ApplyCert = function() {
			
		    $('#datetimepicker1').val('');	    
			$('#getCert').modal('show');
		};
		
		  $scope.deletedCert = function(x) {  
			  let newDate = moment($scope.newSelectedDate).toDate();
			  $scope.newSelectedType = x.cert_type;
			  $scope.newSelectedDate = x.cert_testdate;
			  $scope.CERTIFICATIONS.cert_id = x.cert_id;
			  console.log($scope.CERTIFICATIONS.cert_id);
				$('#deleteCert').modal('show');
			};
			
		
		$http ({
			method: 'GET',
			url: 'certificationtype/all',
		})
		.then((response) => {
			
			$scope.CERTIFICATION_TYPE = response.data;
			console.log($scope.CERTIFICATION_TYPE);
		});
		
		$http ({
			method: 'GET',
			url: 'certifications/all',
		})
		.then((response) => {
			
			$scope.CERTIFICATIONS.associate_Id = response.data;
			console.log($scope.CERTIFICATIONS.associate_Id);
		});
		
		 $('#datetimepicker1').datetimepicker();
		  $scope.showDateTimePicker = () => {
		    $('#datetimepicker1').datetimepicker('show');
		  };
		  
		  $scope.getScheduleCert();
		  
		  $scope.updateCert=function(){
			  $scope.selectedDate = $('#datetimepicker1').val();
			  $scope.today = $filter('date')(new Date(),'MM/dd/yyyy');
			  $scope.associate_id=userService.getUser();
			  //let newDate = moment($scope.selectedDate).toDate();

			  var MS_PER_DAY = 1000 * 60 * 60 * 24;
			  $scope.dayOne = new Date($scope.selectedDate).getTime();
			  $scope.dayTwo = new Date($scope.today).getTime();
			  $scope.changeDiff = Math.floor(($scope.dayOne - $scope.dayTwo)/MS_PER_DAY);
			  console.log($scope.changeDiff);
				if( $scope.changeDiff > 14){
					//post data to database
					console.log($scope.today);
					console.log($scope.selectedDate);
					let newDate = moment($scope.selectedDate).toDate();
					 $http({
					        method: 'POST',
					        url: 'certifications/add/certification',
					        data: { cert_testdate:newDate, cert_status:$scope.formkey, associate_id:userService.getUser(), cert_type:$scope.selectedType.type_of_cert },
					      })
					      .then((response) => {
					    	  $scope.getScheduleCert();
					    	  console.log(response.data);
					      });
					 $scope.successMessage="You are now scheduled to take a certification";
					 $timeout(function(){
							$scope.successMessage=false;
						},2000);
					 }
				else{
					$scope.errorMessage='Date must be 2 weeks after today';
					console.log($scope.today);
					console.log($scope.selectedDate);
					$timeout(function(){
						$scope.errorMessage=false;
					},2000);
					
				}
		  };

		  
		  
		  $scope.updateNewCert=function(x){
			  let newDate = moment($scope.newSelectedDate).toDate();
			  $scope.cert_id= $scope.CERTIFICATIONS.cert_id;
			  $scope.cert_type= $scope.newSelectedType.type_of_cert;

				 $http({
				        method: 'PUT',
				        url: '/certifications',
				        data: { cert_id:$scope.CERTIFICATIONS.cert_id, cert_testdate:newDate, cert_status:$scope.newFormkey, associate_id:userService.getUser(), cert_type:$scope.newSelectedType, comments:$scope.selectedComment },
				      })
				      .then((response) => {
				    	  $scope.successMessage="Successfully sent to Manager";
				    	  console.log(response.data);
				    	  console.log($scope.newSelectedType);
				      });
		  };
		
}

export default certificationCtrl;
