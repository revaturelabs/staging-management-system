const managerPanelCtrl = ($scope, $state, $location, $http, userService) => {
	$scope.PanelLoad= '';
	$scope.show_panel = false;
	$scope.errorUpdateMsgShow = false;
    $scope.successUpdateMsgShow = false;
    
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
		
		$scope.PanelClick = function (panel) {
		  $scope.statusOption  = panel.status;
		  $scope.panelChoose = panel;
		  console.log("I got the panel ",  $scope.panelChoose);
		    $('#PanelCommentModal').modal('show');
		    $scope.updateInterviewClick = (statusOption, updateComment)=>{
				console.log(statusOption + " " +updateComment);
				panel.comments = updateComment;
				panel.status = statusOption;
				console.log(panel);
				$http({
					method: 'PUT',
					url: '/panel',
					data: panel,
				}).then((response)=>{
					$scope.successUpdateMsgShow = true;
					$scope.associatePanelClick;
				});
			}
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
		  
		  
    
	
}
export default managerPanelCtrl;