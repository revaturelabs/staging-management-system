const managerPanelCtrl = ($scope, $state, $location, $http, userService) => {
	$scope.PanelLoad= '';
	$scope.show_panel = false;
	$scope.defaultCommnt = '';
	$scope.choose = {};
	$scope.plist = {};
	
	$scope.searchClick =(searchName)=>{
		$scope.choose ={};
		$scope.plist = {};
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
			$scope.choose = associate;
			$scope.searchShowUp = false;
			$scope.show_panel = true;
			var associateId = associate.id;
			$http({
				method: 'GET',
				url: '/panel/associate/'+associateId,
			}).then((response) =>{
				$scope.plist = response.data;
			    $scope.plist.sort(function (a,b) {
			          return  a.id - b.id;
			        });
			});
			
			$scope.PanelClick = function (panel) {
				
				  $scope.statusOption  = panel.status;
				  $scope.panelChoose = panel;
				  $scope.errorUpdateMsgShow = false;
			      $scope.successUpdateMsgShow = false;
				  $scope.updateComment = panel.comments;
				  $('#PanelCommentModal').modal('show');
				  
			      $scope.updateInterviewClick = (statusOption, updateComment)=>{
					panel.comments = updateComment;
					panel.status = statusOption;
					$http({
						method: 'PUT',
						url: '/panel',
						data: panel,
					}).then((response)=>{
						$scope.successUpdateMsgShow = true;
						$scope.associatePanelClick(associate);
					},(response)=>{
						$scope.errorUpdateMsgShow = true;
					});
				  };
			};	
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
		        $scope.defaultCommnt = '';
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
		  
	  
	
}
export default managerPanelCtrl;