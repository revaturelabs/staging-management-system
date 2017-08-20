const managerPanelCtrl = ($scope, $state, $location, $http, userService) => {
	$scope.PanelLoad= '';
	$scope.show_panel = false;
	/*$http({
	    method: 'GET',
	    url: '/associate/all',
	  }).then((response) => {
		  
	    $scope.associates = response.data;
	    console.log(response.data);
	    $scope.PanelLoad= '';
	    $scope.search_disabled = false;
	});*/
	$scope.searchClick =(searchName)=>{
		/*console.log(associate);
		$scope.search.name='';
		$scope.show_panel = true;
		var associateId = associate.id;
		$http({
			method: 'GET',
			url: '/panel/associate/'+associateId,
		}).then((response) =>{
			console.log(response.data);
			$scope.plist = response.data;
		});*/
		console.log(searchName);
		$scope.disabled_search=true;
		$scope.PanelLoad= 'Loading Panel...';
		$http({
			method: 'GET',
			url: '/associate/search/'+searchName,
		}).then((response)=>{
			$scope.PanelLoad= '';
			$scope.disabled_search = false;
			console.log(response.data);
		});
		
	};
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