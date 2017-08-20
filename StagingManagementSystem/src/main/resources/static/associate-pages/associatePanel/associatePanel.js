const associatePanelCtrl = ($scope, $http, userService) => {

	$http ({
		method: 'GET',
		url: `/panel/associate/${userService.getUser().id}`,
	})
	.then((response) => {
		$scope.plist = response.data;
	});
};

export default associatePanelCtrl;