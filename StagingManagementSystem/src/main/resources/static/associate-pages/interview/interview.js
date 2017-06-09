const associateInterviewCtrl = ($scope, $http) => {
	$('#datetimepicker1').datetimepicker();
	$scope.showDateTimePicker = () => {
		$('#datetimepicker1').datetimepicker("show");
	}
	$scope.names = [1,2,3,4,5];
	console.log("hi")
};

export default associateInterviewCtrl;