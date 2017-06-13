const chart = {
            caption: "Asscoiates Available vs. Associate Confirmed",
            subCaption: "Revature LLC",
            xAxisname: "Batch Type",
            yAxisName: "Number of Associate",
            paletteColors: "#ff0000,#0075c2",
            bgColor: "#ffffff",
            borderAlpha: "20",
            showCanvasBorder: "0",
            usePlotGradientColor: "0",
            plotBorderAlpha: "10",
            legendBorderAlpha: "0",
            legendShadow: "0",
            valueFontColor: "#ffffff",
            showXAxisLine: "1",
            xAxisLineColor: "#999999",
            divlineColor: "#999999",
            divLineDashed: "1",
            showAlternateHGridColor: "0",
            subcaptionFontBold: "0",
            subcaptionFontSize: "14",
            showHoverEffect: "1"
            };

let categories;
let dataset;



function graphBuilder($scope, $http){
  $http ({
    method: 'GET',
    url: '/associate/totaldata',   
  }).then ((response) => {
      console.log("new stuff: " + JSON.stringify(response.data));
      var stuff1 = [];
      var stuff2 = [];
      var stuff3 = [];
      var value = response.data;
          value.forEach(function(item){
          stuff1.push({"label" : item.batchName});      
          stuff2.push({"value" : item.totalAvailable - item.totalUnavailable});
          stuff3.push({"value" : item.totalUnavailable});
      });
      categories = [
        {
          "category": stuff1  
        }];
      dataset = [
        {
          "seriesname": "Confirmed Associates",
          "data": stuff3
        },
        {
          "seriesname": " Available Associates",
          "data": stuff2
        }];
        plainBarChart2($scope, chart, categories, dataset );

      });
 
}

function ColumnClick(ev, props, $scope) {
  
  
  
  $scope.selectedValue = `${props.displayValue}/${props.categoryLabel}/${props.dataIndex}`;
}


function plainBarChart2($scope, chartstuff, categories, dataset ) {
  
  const myDataSource = {
      chart : chartstuff,
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
          switch (scale) {
          case Stuff1:
            ColumnClick(ev, props, $scope);
            break;
            default:
          }
        });
      },
    },
  });
  
  chart.render();

}





const stagingGraphController = ($scope, $http) => {
  graphBuilder($scope, $http);
};


export default stagingGraphController;
