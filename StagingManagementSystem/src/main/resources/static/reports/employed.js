//const data =  [
//    {
//        label: "Employed",
//        value: "1200"
//    },
//    {
//        label: "Unemployed",
//        value: "1463"
//    },
//];

const chart = {
        caption: "Employed Percentage versus Those awaiting placement",
        subcaption: "Last Year",
        startingangle: "120",
        showlabels: "0",
        showlegend: "1",
        enablemultislicing: "0",
        slicingdistance: "15",
        showpercentvalues: "1",
        showpercentintooltip: "0",
        plottooltext: "$label Total: $datavalue",
        theme: "fint"
    };

//    const attendanceCtrl = ($scope) => {
//    	threeDPie($scope, chart, data);
//    };
//
//    function threeDPie($scope, chartInfo, data){
//    	const myDataSource = {
//    			chart: chartInfo,
//    			data: data,
//    	};
//    const chart = new FusionCharts({
//    	type: 'pie3d',
//    	width: '600',
//    	height: '400',
//    	renderAt: 'chartContainer',
//    	dataFormat: 'json',
//    	dataSource: myDataSource,
//    });
//    chart.render();
//    }

const employedCtrl = ($scope, $http) => {
	console.log("hello calandra");
	let chartInfo, data;
	$http({
		  method: 'GET',
		  url: '/associate/all'
		}).then((response) => {
			console.log(response);
			
			renderChart(response);
		  },(error) => {
		  console.log('Unable to render chart');
		  });

	function renderChart(chartInfo) {
		var pieChart = new FusionCharts({
			type: 'pie3d',
			width: '600',
			height: '400',
			renderAt: 'chartContainer',
			dataFormat: 'json',
			dataSource: {
				'chart': chart,
				'data': chartInfo
			}
		});
	pieChart.render();
	}
	
};



export { employedCtrl };