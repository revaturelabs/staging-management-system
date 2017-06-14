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
		url: '/interviewStatus/all',
	})
	.then((response) => {
		$scope.interviewStatuses = response.data;
	});
	
	$http ({
		method: 'GET',
		url: '/marketer/all',
	})
	.then((response) => {
		$scope.marketers = response.data;
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
	});



	$scope.addInterviewClick = function() {

		$scope.errorMsgShow = false;
		$scope.successMsgShow = false;
		
		$scope.selectedDate = $("#datetimepicker1").val();
	
		if($scope.selectedClient == undefined) {
			$scope.errorMsg = 'Please select a Client.';
      $scope.errorMsgShow = true;
		}
		else if($scope.selectedDate == undefined || $scope.selectedDate === "") {
			$scope.errorMsg = 'Please select a Date.';
      $scope.errorMsgShow = true;
		}
		else if($scope.selectedMarketer == undefined) {
			$scope.errorMsg = 'Please select a Marketer.';
      $scope.errorMsgShow = true;
		}
		else {
			let newDate = moment($scope.selectedDate).toDate();
			addInterviewBtn.disabled = true;
			addInterviewBtn.innerHTML = 'Adding...';
			$http({
				method: 'POST',
				url: '/interviews',
				data: { associate: userService.getUser(), client: $scope.selectedClient, scheduled: newDate, marketer: $scope.selectedMarketer},
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
	
	$scope.showAddModal = function() {
		$scope.errorMsgShow = false;
		$scope.successMsgShow = false;
		
		$scope.selectedClient = undefined;
		$("#datetimepicker1").val("");
		$scope.selectedMarketer = undefined;
		
		$('#addModal').modal('show');
	}
	
	$scope.interviewClick = function(interview) {
		$scope.clickedInterview = interview;
		for(let i=0;i<$scope.interviewStatuses.length;i++) {
			if($scope.interviewStatuses[i].value === interview.interviewStatus.value)
				$scope.updateStatus = $scope.interviewStatuses[i];
		}
		for(let i=0;i<$scope.marketers.length;i++) {
			if($scope.marketers[i].name === interview.marketer.name)
				$scope.updateMarketer = $scope.marketers[i];
		}
		$scope.updateComment = $scope.clickedInterview.comment;
		
		$scope.errorUpdateMsgShow = false;
		$scope.successUpdateMsgShow = false;
		$('#interviewModal').modal('show');
	}
	
	$scope.updateInterviewClick = function() {
		$scope.errorUpdateMsgShow = false;
		$scope.successUpdateMsgShow = false;
		
		if($scope.updateComment === undefined || $scope.updateComment === "") {
			$scope.errorUpdateMsg = 'Please add a comment.';
      $scope.errorUpdateMsgShow = true;
		}
		else {
			$http({ 
				method: 'PUT',
				url: '/interviews',
				data: { id: $scope.clickedInterview.id, associate: $scope.clickedInterview.associate, client: $scope.clickedInterview.client, 
					scheduled: $scope.clickedInterview.scheduled, marketer: $scope.updateMarketer, interviewStatus: $scope.updateStatus, comment: $scope.updateComment},
			})
			.then ((response) => {
	      $scope.successUpdateMsgShow = true;
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
			})
		}
	}
};

export default associateInterviewCtrl;
