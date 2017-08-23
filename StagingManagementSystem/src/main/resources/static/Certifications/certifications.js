const certificationCtrl= function($scope,$http,$state, $stateParams){
	$scope.certification={};

	  
		$scope.ApplyCert = function() {
			
			$('#getCert').modal('show');
		};
		

		$http ({
			method: 'GET',
			url: '/certifications/all',
		})
		.then((response) => {
			$scope.certification = response.data;
		});
		
		
}

export { certificationCtrl };
