const certificationCtrl= function($scope,$http,$state, $stateParams,$filter,$timeout,userService){
	$scope.certifications ={};
	$scope.certificationtype ={};
	
		$scope.ApplyCert = function() {
			
		    $('#datetimepicker1').val('');	    
			$('#getCert').modal('show');
		};
		
		$http ({
			method: 'GET',
			url: 'certificationtype/all',
		})
		.then((response) => {
			
			$scope.Certification_Type = response.data;
			console.log($scope.Certification_Type);
		});
		

//		$scope.findCert = function(){
//			$http({
//				method:'GET',
//				url:'/certifications/all',
//			})
//			.then (function(response){
//				$scope.certifications=response.data;
//				console.log($scope.certifications);
//			});
//		};
//		

		 $('#datetimepicker1').datetimepicker();
		  $scope.showDateTimePicker = () => {
		    $('#datetimepicker1').datetimepicker('show');
		  };
		  
			
		  $scope.updateCert=function(){
			  $scope.selectedDate = $('#datetimepicker1').val();
			  $scope.selectedType = undefined;
			  $scope.today = $filter('date')(new Date(),'MM/dd/yyyy');
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
					        data: {cert_type:$scope.selectedType, cert_testdate:newDate, cert_status:$scope.selectedStatus, associate_id:userService.getUser().id },
					      })
					      .then((response) => {
					    	  $state.reload();
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

		
}

export default certificationCtrl;
