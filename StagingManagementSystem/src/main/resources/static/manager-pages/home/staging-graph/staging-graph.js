import FusionCharts from 'fusioncharts';

const chartSetup = {
  caption: 'Associates Available vs. Associates Confirmed',
  subCaption: 'Revature LLC',
  xAxisname: 'Batch Type',
  yAxisName: 'Number of Associate',
  paletteColors: '#ff0000,#0075c2',
  bgColor: '#ffffff',
  borderAlpha: '20',
  showCanvasBorder: '0',
  usePlotGradientColor: '0',
  plotBorderAlpha: '10',
  legendBorderAlpha: '0',
  legendShadow: '0',
  valueFontColor: '#ffffff',
  showXAxisLine: '1',
  xAxisLineColor: '#999999',
  divlineColor: '#999999',
  divLineDashed: '1',
  showAlternateHGridColor: '0',
  subcaptionFontBold: '0',
  subcaptionFontSize: '14',
  showHoverEffect: '1',
};

let categories;
let dataset;

let responseData;

function ColumnClick(ev, props, $scope) {
  $('.availAssModal').modal('toggle');

  let columnData;
  responseData.forEach((item) => {
    if (props.categoryLabel === item.batchName) {
      columnData = item;
    }
  });
  if (props.datasetName === 'Available Associates') {
    $scope.associates = columnData.availible;
  }
  if (props.datasetName === 'Confirmed Associates') {
    $scope.associates = columnData.mapped;
  }
}

function plainBarChart2($scope, chartstuff) {
  const myDataSource = {
    chart: chartstuff,
    categories,
    dataset,
  };

  $scope.selectedValue = 'nothing';

  const chart = new FusionCharts({
    type: 'stackedcolumn3d',
    renderAt: 'chart-container',
    width: '650',
    height: '450',
    dataFormat: 'json',
    dataSource: myDataSource,
    events: {
      dataplotclick: function dataplotclick(ev, props) {
        $scope.$apply(() => {
          ColumnClick(ev, props, $scope);
        });
      },
    },
  });

  chart.render();
}

function graphBuilder($scope, $http) {
  $http({
    method: 'GET',
    url: '/associate/totaldata',
  }).then((response) => {
	$scope.$root.loading = false;
    responseData = response.data;
    const stuff1 = [];
    const stuff2 = [];
    const stuff3 = [];
    const value = response.data;
    value.forEach((item) => {
      stuff1.push({ label: item.batchName });
      stuff2.push({ value: item.totalAvailable - item.totalUnavailable });
      stuff3.push({ value: item.totalUnavailable });
    });
    categories = [
      {
        category: stuff1,
      }];
    dataset = [
      {
        seriesname: 'Confirmed Associates',
        data: stuff3,
      },
      {
        seriesname: 'Available Associates',
        data: stuff2,
      }];

    plainBarChart2($scope, chartSetup);
  });
}

const stagingGraphController = ($scope, $http) => {
  graphBuilder($scope, $http);
};


export default stagingGraphController;
