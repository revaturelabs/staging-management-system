const associateInterviewCtrl = ($scope, $http) => {
	$('#datetimepicker1').datetimepicker();
	$scope.showDateTimePicker = () => {
		$('#datetimepicker1').datetimepicker("show");
	}
	$("#datetimepicker1").on("dp.change", function() {
    $scope.selectedDate = $("#datetimepicker1").val();
	});
	
	$http({
		method: 'GET',
		url: '/client/all',
	})
	.then((response) => {
		$scope.clients = response.data;
		$scope.clients.sort( (pre, cur) => {
			return pre.name.localeCompare(cur.name);
		});
	});
	
	$http ({
		method: 'GET',
		url: 'interviews/all',
	})
	.then((response) => {
		console.log(response.data)
	});
	
//	$scope.errorMsgShow = true;
//	$scope.successMsgShow = true;
	$scope.names = [1,2,3,4,5];
	
	$scope.onDateChange = function(date) {
		console.log(date)
		$scope.selectDate = date;
	}
	
	$scope.addInterviewClick = function() {
		console.log($scope.selectedClient)
		console.log($scope.selectedDate)
	}
};

export default associateInterviewCtrl;