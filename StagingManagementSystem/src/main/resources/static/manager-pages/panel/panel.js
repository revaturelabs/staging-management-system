const managerPanelCtrl = ($scope, $state, $location, $http, userService) => {
	$scope.PanelLoad= '';
	$scope.show_panel = false;
	/*$http({
	    method: 'GET',
	    url: '/associate/all',
	  }).then((response) => {
		  
	    $scope.associates = response.data;
	    $scope.PanelLoad= '';
	    $scope.search_disabled = false;
	});*/
	$scope.searchClick =(searchName)=>{
		/*$scope.search.name='';
		$scope.show_panel = true;
		var associateId = associate.id;
		$http({
			method: 'GET',
			url: '/panel/associate/'+associateId,
		}).then((response) =>{
			$scope.plist = response.data;
		});*/
		if(searchName){
			$scope.disabled_search=true;
			$scope.show_panel = false;
			$scope.PanelLoad= 'Loading Panel...';
			$http({
				method: 'GET',
				url: '/associate/search/'+searchName,
			}).then((response)=>{
				$scope.PanelLoad= '';
				$scope.disabled_search = false;
				$scope.associates = response.data;
				$scope.searchShowUp = true;
			});
		}
		
		$scope.associatePanelClick =(associate)=>{
			$scope.searchShowUp = false;
			$scope.show_panel = true;
			var associateId = associate.id;
			$http({
				method: 'GET',
				url: '/panel/associate/'+associateId,
			}).then((response) =>{
				$scope.plist = response.data;
			});
		};
		
		
		
	};
	
	
};
export default managerPanelCtrl;