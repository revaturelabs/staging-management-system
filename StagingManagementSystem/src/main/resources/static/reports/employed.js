const chart = {
        caption: "Employed Percentage versus Those awaiting placement",
        subcaption: "Revature, LLC",
        startingangle: "120",
        showlabels: "0",
        showlegend: "1",
        enablemultislicing: "0",
        slicingdistance: "25",
        showpercentvalues: "1",
        showpercentintooltip: "0",
        palettecolors: "#0075c2,#ff0000",
        plottooltext: "$label Total: $datavalue",
        theme: "fint"
    };

const employedCtrl = ($scope, $http, $cacheFactory) => {
$scope.cache = $cacheFactory.get('myCache') || $cacheFactory('myCache');
	
	//Init controller Data
	if(angular.isUndefined($scope.cache.get('chartData')))
	{
		httpRequest();

	} else {
		var cachedData = $scope.cache.get('chartData');
		renderChart(cachedData);
	}
	

	function httpRequest(){
		$http({
			  method: 'GET',
			  url: '/associate/all'
			}).then((response) => {
				processChartData(response);
			  },(error) => {
			  console.log('Unable to render chart');
			  });
	}
	function processChartData(responseData){
		var chartData = [
			{
				label: "Employed",
				value: 0
			},
			{
				label: "Awaiting placement",
				value: 0
			}
		];
		
		for(var i = 0; i < responseData.data.length; i++){
			if(responseData.data[i].active){
				chartData[0].value = chartData[0].value+1
			} else {
				chartData[1].value = chartData[1].value+1
			}
		}
		$scope.cache.put('chartData', chartData);
		renderChart(chartData);
	}
	function renderChart(chartData) {
		var pieChart = new FusionCharts({
			type: 'pie3d',
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
};
export { employedCtrl };