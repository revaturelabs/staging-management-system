const managerPanelCtrl = ($scope, $state, $location, $http, userService) => {
	$scope.PanelLoad= '';
	$scope.show_panel = false;
	$scope.defaultCommnt = '';
	$scope.choose = {};
	
	$scope.searchClick =(searchName)=>{
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
			$scope.choose = associate;
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
			    $scope.plist.sort(function (a,b) {
			          return  a.id - b.id;
			        });
			});
		};
	};
	
	  $scope.addPanelClick = function () {
		    $scope.errorMsgShow = false;
		    $scope.successMsgShow = false;
		   
		    addPanelBtn.disabled = true;
		    addPanelBtn.innerHTML = 'Adding...';
		      $http({
		        method: 'POST',
		        url: '/panel',
		        data: { associate:$scope.choose, comments: $scope.defaultCommnt},
		      })
		      .then((response) => {
		        $scope.successMsgShow = true;
		        addPanelBtn.disabled = false;
		        addPanelBtn.innerHTML = 'Add Panel';
		        $scope.associatePanelClick($scope.choose)
		      });
		    
		  };
	
	
	
	
	
	  $scope.showAddModal = function () {
		    $scope.errorMsgShow = false;
		    $scope.successMsgShow = false;

		    $scope.selectedClient = undefined;
		    $('#datetimepicker1').val('');
		    $scope.selectedMarketer = undefined;

		    $('#addModal').modal('show');	    
		  };
		  
		  
		  $scope.PanelClick = function (panel) {
			  $scope.panelChoose = panel;
			  console.log("I got the panel ",$scope.panelChoose) 
			    $scope.errorUpdateMsgShow = false;
			    $scope.successUpdateMsgShow = false;
			    $('#PanelCommentModal').modal('show');
			  };
	
}
export default managerPanelCtrl;