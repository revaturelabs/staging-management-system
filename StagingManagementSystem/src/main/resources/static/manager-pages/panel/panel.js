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
		if(searchName){
			console.log(searchName);
			$scope.disabled_search=true;
			$scope.show_panel = false;
			$scope.PanelLoad= 'Loading Panel...';
			$http({
				method: 'GET',
				url: '/associate/search/'+searchName,
			}).then((response)=>{
				$scope.PanelLoad= '';
				$scope.disabled_search = false;
				console.log(response.data);
				$scope.associates = response.data;
				$scope.searchShowUp = true;
			});
		}
		
		$scope.associatePanelClick =(associate)=>{
			console.log(associate);
			$scope.searchShowUp = false;
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
	
	  $scope.showAddModal = function () {
		    $scope.errorMsgShow = false;
		    $scope.successMsgShow = false;

		    $scope.selectedClient = undefined;
		    $('#datetimepicker1').val('');
		    $scope.selectedMarketer = undefined;

		    $('#addModal').modal('show');
		  };
	
};
export default managerPanelCtrl;