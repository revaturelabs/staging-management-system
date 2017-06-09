
const data = [
  {
    label: 'Q1',
    value: '1950000',
    link: 'newchart-json-q1',
  },
  {
    label: 'Q2',
    value: '1970000',
    link: 'newchart-json-q2',
  },
  {
    label: 'Q3',
    value: '1910000',
    link: 'newchart-json-q3',
  },
  {
    label: 'Q4',
    value: '2120000',
    link: 'newchart-json-q4',
  },
];

const linkedData = [{
  id: 'q1',
  linkedchart: {
    chart: {
      caption: 'Monthly Revenue',
      subcaption: 'First Quarter',
      xAxisName: 'Month',
      yAxisName: 'Amount',
      numberPrefix: '$',
      paletteColors: '#008ee4',
      showBorder: '1',
      borderAlpha: '20',
      divLineAlpha: '50',
      showValues: '0',
      bgAlpha: '0',
      canvasBorderAlpha: '0',
      showBorder: '0',
      plotBorderAlpha: '0',
      usePlotGradientColor: '0',
      showAlternateHgridcolor: '0',
    },
    data: [
      {
        label: 'Jan',
        value: '420000',
      }, {
        label: 'Feb',
        value: '810000',
      }, {
        label: 'Mar',
        value: '720000',
      },
    ],
  },
},
{
  id: 'q2',
  linkedchart: {
    chart: {
      caption: 'Monthly Revenue',
      subcaption: 'Second Quarter',
      xAxisName: 'Month',
      yAxisName: 'Amount',
      numberPrefix: '$',
      paletteColors: '#008ee4',
      showBorder: '1',
      borderAlpha: '20',
      divLineAlpha: '50',
      showValues: '0',
      bgAlpha: '0',
      canvasBorderAlpha: '0',
      showBorder: '0',
      plotBorderAlpha: '0',
      usePlotGradientColor: '0',
      showAlternateHgridcolor: '0',
    },
    data: [
      {
        label: 'Apr',
        value: '550000',
      }, {
        label: 'May',
        value: '910000',
      }, {
        label: 'Jun',
        value: '510000',
      },
    ],
  },
},
{
  id: 'q3',
  linkedchart: {
    chart: {
      caption: 'Monthly Revenue',
      subcaption: 'Third Quarter',
      xAxisName: 'Month',
      yAxisName: 'Amount',
      numberPrefix: '$',
      paletteColors: '#008ee4',
      showBorder: '1',
      borderAlpha: '20',
      divLineAlpha: '50',
      showValues: '0',
      bgAlpha: '0',
      canvasBorderAlpha: '0',
      showBorder: '0',
      plotBorderAlpha: '0',
      usePlotGradientColor: '0',
      showAlternateHgridcolor: '0',
    },
    data: [
      {
        label: 'Jul',
        value: '680000',
      }, {
        label: 'Aug',
        value: '620000',
      }, {
        label: 'Sep',
        value: '610000',
      },
    ],
  },
},
{
  id: 'q4',
  linkedchart: {
    chart: {
      caption: 'Monthly Revenue',
      subcaption: 'Fourth Quarter',
      xAxisName: 'Month',
      yAxisName: 'Amount',
      numberPrefix: '$',
      paletteColors: '#008ee4',
      showBorder: '1',
      borderAlpha: '20',
      divLineAlpha: '50',
      showValues: '0',
      bgAlpha: '0',
      canvasBorderAlpha: '0',
      showBorder: '0',
      plotBorderAlpha: '0',
      usePlotGradientColor: '0',
      showAlternateHgridcolor: '0',
    },
    data: [
      {
        label: 'Oct',
        value: '490000',
      }, {
        label: 'Nov',
        value: '900000',
      }, {
        label: 'Dec',
        value: '730000',
      },
    ],
  },
},
];


const chart = {
  caption: 'Quarterly revenue',
  subCaption: 'Last year',
  xAxisName: 'Quarter',
  yAxisName: 'Amount',
  numberPrefix: '$',
  paletteColors: '#008ee4',
  showBorder: '1',
  borderAlpha: '20',
  divLineAlpha: '50',
  showValues: '0',
  bgAlpha: '0',
  canvasBorderAlpha: '0',
  showBorder: '0',
  plotBorderAlpha: '0',
  usePlotGradientColor: '0',
  showAlternateHgridcolor: '0',
};


const nestedCtrl = ($scope) => {
  plainBarChart($scope, chart, data, linkedData);
};

function plainBarChart($scope, chartInfo, data, linkedData) {
  $scope.greeting = 'test';
  const myDataSource = {
    chart: chartInfo,
    data,
    linkedData,
  };
  console.log(`hello console: ${linkedData}`);

  const chart = new FusionCharts({
    type: 'column2d',
    width: '500',
    height: '300',
    renderAt: 'chartContainer',
    dataFormat: 'json',
    dataSource: myDataSource,
  });

  chart.addEventListener('chartrollover', () => {
    $('#slide-in').show('slow');
  });

  chart.addEventListener('chartrollout', () => {
    $('#slide-in').hide('slow');
  });

  chart.render();
}

export { nestedCtrl };
