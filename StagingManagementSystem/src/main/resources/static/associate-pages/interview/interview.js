import dateformat from 'dateformat';

const associateInterviewCtrl = ($scope, $http, userService) => {
	$scope.associateInterviews;
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
		url: `interviews/associate/${userService.getUser().id}`,
	})
	.then((response) => {
		response.data.forEach(function(e) {
			let day = new Date(response.data[0].scheduled[0], response.data[0].scheduled[1], response.data[0].scheduled[2], response.data[0].scheduled[3],
					response.data[0].scheduled[4], response.data[0].scheduled[5], 0);
			e['day'] = dateformat(day, "dddd, mmmm dS, yyyy, h:MM TT");;
		});
		$scope.associateInterviews = response.data;
	});

//	$scope.errorMsgShow = true;
//	$scope.successMsgShow = true;

	$scope.addInterviewClick = function() {
		$http({
			method: 'POST',
			url: '/interviews',
			data: { associate: userService.getUser(), client: $scope.selectedClient},
		})
		.then((response) => {

		});
		console.log(userService.getUser())
		console.log($scope.selectedClient)
		console.log($scope.selectedDate)
	}
};

export default associateInterviewCtrl;
