import FusionCharts from 'fusioncharts';

const chart = {
  caption: 'Employed Percentage versus Those awaiting placement',
  subcaption: 'Revature, LLC',
  startingangle: '120',
  showlabels: '1',
  showlegend: '1',
  enablemultislicing: '0',
  slicingdistance: '25',
  showpercentvalues: '0',
  showpercentintooltip: '1',
  palettecolors: '#0075c2,#ff0000,#FF8000',
  theme: 'fint',
};

const employmentGraphCtrl = ($scope, $http, $cacheFactory) => {
  $scope.cache = $cacheFactory.get('myCache') || $cacheFactory('myCache');

  function renderChart(chartData) {
    const pieChart = new FusionCharts({
      type: 'pie3d',
      width: '600',
      height: '400',
      renderAt: 'chartContainer',
      dataFormat: 'json',
      dataSource: {
        chart,
        data: chartData,
      },
    });
    pieChart.render();
  }

  function processChartData(responseData) {
    const chartData = [
      {
        label: 'Employed',
        value: 0,
      },
      {
        label: 'Awaiting placement',
        value: 0,
      },
      {
          label: 'In Training',
          value: 0,
        },
    ];
    for (let i = 0; i < responseData.data.length; i += 1) {
      if (responseData.data[i].associateStatus.status == 'STAGING' || responseData.data[i].associateStatus.status == 'BENCH') {
        chartData[1].value += 1;
      } else if(responseData.data[i].associateStatus.status == 'PROJECT'){
        chartData[0].value += 1;
      }	else {
    	chartData[2].value += 1;
      }
    }
    $scope.cache.put('chartData', chartData);
    return chartData;
  }


  function httpRequest() {
    $http({
      method: 'GET',
      url: '/associate/all',
    }).then((response) => {
      const chartData = processChartData(response);
      renderChart(chartData);
    });
  }

  // Init controller Data
  if (angular.isUndefined($scope.cache.get('chartData'))) {
    httpRequest();
  } else {
    const cachedData = $scope.cache.get('chartData');
    renderChart(cachedData);
  }
};


export default employmentGraphCtrl;
