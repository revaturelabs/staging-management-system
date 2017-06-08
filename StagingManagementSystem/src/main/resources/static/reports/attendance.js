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

const attendanceCtrl = ($scope, $http) => {
	let chartInfo;
	$http({
		  method: 'GET',
		  url: '/associate/50'
		}).then((response) => {
		   chartInfo: response;
		  }, (response) => {
		  //
		  });
	const chart = new FusionCharts({
		type: 'pie3d',
		width: '600',
		height: '400',
		renderAt: 'chartContainer',
		dataFormat: 'json',
		dataSource: chartInfo,
	});
chart.render();
};



export { attendanceCtrl };