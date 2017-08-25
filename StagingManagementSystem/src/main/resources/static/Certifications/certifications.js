const certificationCtrl= function($scope,$http,$state, $stateParams,$filter,$timeout){

		$scope.ApplyCert = function() {
		    $scope.selectedDate = undefined;
		    $('#datetimepicker1').val('');
		    
			$http ({
				method: 'GET',
				url: '/certifications/all',
			})
			.then((response) => {
				console.log(response);
				$scope.CERTIFICATIONS = response.data;
			});
			
			$('#getCert').modal('show');
		};
		

		 $('#datetimepicker1').datetimepicker();
		  $scope.showDateTimePicker = () => {
		    $('#datetimepicker1').datetimepicker('show');
		  };
		  
			
			    
		  $scope.updateCert=function(){
			  $scope.selectedDate = $('#datetimepicker1').val();
			  $scope.today = $filter('date')(new Date(),'MM/dd/yyyy');
			  //let newDate = moment($scope.selectedDate).toDate();

			  var MS_PER_DAY = 1000 * 60 * 60 * 24;
			  $scope.dayOne = new Date($scope.selectedDate).getTime();
			  $scope.dayTwo = new Date($scope.today).getTime();
			  $scope.changeDiff = Math.floor(($scope.dayOne - $scope.dayTwo)/MS_PER_DAY);
			  console.log($scope.changeDiff);
				if( $scope.changeDiff > 14){
					//post data to database
					 $scope.successMessage="You are now scheduled to take a certification";
				      $http({
				          method: 'POST',
				          url: '/certifications/add/cetification',
				          data: { certifications:$scope.certification, date: $scope.selectedDate.value},
				        })
				        .then((response) => {
				        	$scope.certifications= response.data;
				        });
				}else{
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
