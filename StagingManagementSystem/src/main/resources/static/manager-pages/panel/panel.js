const managerPanelCtrl = ($scope, $state, $location, $http, userService) => {
	$scope.PanelLoad= 'Loading Panel...';
	$scope.search_disabled = true;
	$scope.show_panel = false;
	$http({
	    method: 'GET',
	    url: '/associate/all',
	  }).then((response) => {
		  
	    $scope.associates = response.data;
	    console.log(response.data);
	    $scope.PanelLoad= '';
	    $scope.search_disabled = false;
	});
	$scope.associatePanelClick =(associate)=>{
		console.log(associate);
		$scope.search.name='';
		$scope.show_panel = true;
		var associateId = associate.id;
		$http({
			method: 'GET',
			url: '/panel/associate/'+associateId,
		}).then((response) =>{
			console.log(response.data);
			$scope.plist = response.data;
		});
	};
	
};
export default managerPanelCtrl;