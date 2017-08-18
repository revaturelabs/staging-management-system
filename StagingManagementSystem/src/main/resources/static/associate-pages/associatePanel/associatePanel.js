const associatePanelCtrl = ($scope, $http, userService) => {
	
//	$http ({
//		method: 'GET',
//		url: '/panel/associatetepanel',
//	})
//	.then((response) => {
//		$scope.plist = response.data;
//	});
//	
//};


	$http ({
		method: 'GET',
		url: `/panel/associate/${userService.getUser().id}`,
	})
	.then((response) => {
		$scope.plist = response.data;
//		$scope.associateInterviews.sort(function(a,b) {
//		return new Date(b.scheduled).getTime() - new Date(a.scheduled).getTime();
//		});
	});
};

export default associatePanelCtrl;
