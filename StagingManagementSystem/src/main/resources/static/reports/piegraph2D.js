const chart = {
	caption: "",
	showvalues: "0",
	showlabels: "0",
	showlegend: "1",
	showborder: "0",
	showpercentageinlabel: "1",
	palettecolors: "#0075c2,#ff0000",
	bgAlpha: "0",
	theme: "fint"
};

const pie2DCtrl = ($http, $scope, $cacheFactory) => {
			
	$scope.cache = $cacheFactory.get('employmentCache') || $cacheFactory('employmentCache');
	
	//Init controller Data
	if(angular.isUndefined($scope.cache.get('chartData')))
	{
		getHttpRequest();

	} else {
		var cachedData = $scope.cache.get('chartData');
		renderChart(cachedData);
	}
	
	/**********************************************/
	function getHttpRequest() {
		$http({
			method: 'GET',
			url: '/associate/all'
		}).then((response) => {
			processChartData(response);				
			
		}, (error) => {
			console.log('Unable to render chart');
		});
	}

	function processChartData(responseData) {
					var chartData = [{
				label: "",
				value: 0
			}, {
				label: "",
				value: 0
			}
		];

		for (var i = 0; i < responseData.data.length; i++) {
			if (responseData.data[i].active) {
				chartData[0].value = chartData[0].value + 1
			} else {
				chartData[1].value = chartData[1].value + 1
			}
		}
		
		$scope.cache.put('chartData', chartData);
		
		renderChart(chartData)
	}
	
	function renderChart(chartData) {
		
		var pieChart = new FusionCharts({
				type: 'pie2d',
				width: '600',
				height: '400',
				renderAt: 'chartContainer',
				dataFormat: 'json',
				dataSource: {
					'chart': chart,
					'data': chartData
				}
			});
		pieChart.render();
	}
/**********************************************/
};
export { pie2DCtrl };