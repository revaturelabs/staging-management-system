import dateformat from 'dateformat';

const associateInterviewCtrl = ($scope, $http, userService) => {
	const addInterviewBtn = document.getElementById('addInterviewBtn');
	
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
		$scope.associateInterviews = response.data;
		$scope.associateInterviews.sort(function(a,b) {
	    return new Date(b.scheduled).getTime() - new Date(a.scheduled).getTime();
		});
		console.log(response.data)
	});

//	$scope.errorMsgShow = true;
//	$scope.successMsgShow = true;

	$scope.addInterviewClick = function() {
		addInterviewBtn.disabled = true;
		addInterviewBtn.innerHTML = 'Adding...';
		$scope.errorMsgShow = false;
		$scope.successMsgShow = false;
	
		if($scope.selectedClient == undefined) {
			$scope.errorMsg = 'Please select a Client.';
      $scope.errorMsgShow = true;
		}
		else if($scope.selectedDate == undefined) {
			$scope.errorMsg = 'Please select a Date.';
      $scope.errorMsgShow = true;
		}
		else {
			let newDate = moment($scope.selectedDate).toDate();
			$http({
				method: 'POST',
				url: '/interviews',
				data: { associate: userService.getUser(), client: $scope.selectedClient, scheduled: newDate},
			})
			.then((response) => {
				$scope.successMsgShow = true;
				addInterviewBtn.disabled = false;
				addInterviewBtn.innerHTML = 'Add Interview';
				
				$http ({
					method: 'GET',
					url: `interviews/associate/${userService.getUser().id}`,
				})
				.then((response) => {
					$scope.associateInterviews = response.data;
					$scope.associateInterviews.sort(function(a,b) {
				    return new Date(b.scheduled).getTime() - new Date(a.scheduled).getTime();
					});
				});
			});
		}
	}
};

export default associateInterviewCtrl;
