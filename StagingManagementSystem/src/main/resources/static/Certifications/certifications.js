const certificationCtrl= function($scope,$http,$state, $stateParams){
	$scope.certification={};

	  
		$scope.ApplyCert = function() {
			
			$('#getCert').modal('show');
		};
		
		$http.get('certifications/all').then(function(response){
			console.log(response);
		});
		
		
}

export { certificationCtrl };
