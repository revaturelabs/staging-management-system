const chart = {
            caption: "Asscoiates Available vs. Associate Confirmed",
            subCaption: "Revature LLC",
            xAxisname: "Batch Type",
            yAxisName: "Number of Associate",
        //    numberPrefix: "$",
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

const dataset = [
                {
                  "seriesname": "Food Products",
                  "data": [
                      {
                          "value": "121000"
                      },
                      {
                          "value": "135000"
                      },
                      {
                          "value": "123500"
                      },
                      {
                          "value": "145000"
                      }
                  ]
              },
              {
                  "seriesname": "Non-Food Products",
                  "data": [
                      {
                          "value": "131400"
                      },
                      {
                          "value": "154800"
                      },
                      {
                          "value": "98300"
                      },
                      {
                          "value": "131800"
                      }
                  ]
                }
            ];

const barCtrl = ($scope, $http) => {
  graphBuilder($scope, $http);
};

function graphBuilder($scope, $http){
  $http ({
    method: 'GET',
    url: '/batchtype/all',   
  }).then ((response) => {
      var stuff = [];
      var value = response.data;
          value.forEach(function(item){
          stuff.push({"label" : item.value});      
      });
      categories = [
        {
          "category": stuff   
        }];
        plainBarChart2($scope, chart, categories, dataset );
      });
 
}

function plainBarChart2($scope, chartstuff, categories, dataset ) {
  
  const myDataSource = {
      chart : chartstuff,
      categories,
      dataset,
  };
  
  const chart = new FusionCharts({
    type: 'stackedcolumn3d',
    renderAt: 'chart-container',
    width: '550',
    height: '350',
    dataFormat: 'json',
    dataSource: myDataSource,     
  });
  
  console.log("his chart: " + chart);
  chart.render();

}

export { barCtrl };
