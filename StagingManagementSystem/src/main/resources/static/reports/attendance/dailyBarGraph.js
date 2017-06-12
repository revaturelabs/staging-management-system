function binarySearch(data, searchVal, start, stop, cmpFunction) {
  if (start >= stop) {
    return stop;
  }
  const midpoint = Math.floor((start + stop) / 2);
  // console.log("midpoint: " + midpoint);

  const value = cmpFunction(searchVal, data[midpoint]);
  // console.log("cmp Value: " + value);
  if (value === 0) {
    return midpoint;
  } else if(value > 0) {
    return binarySearch(data, searchVal, midpoint + 1, stop, cmpFunction);
  } 

  return binarySearch(data, searchVal, start, midpoint, cmpFunction);
}

function binarySearchHelper(data, searchVal, cmpFunction) {
  return binarySearch(data, searchVal, 0, data.length, cmpFunction);
}


const weekly = {

  printHello: function printHello() {
    console.log('hello success');
  },
  
  set: function sety($scope, date) {

    if(date === undefined)
      date = moment();

    const currDay = date.day();
    // console.log("hi there");

    date.subtract(currDay, "days");
    let index = binarySearchHelper(weeklyData, date, cmpDay);
    console.log('index: ' + index);
    let dataString = '[{"seriesname":"Weekly","data":[';

    let i;
    for(i = 0; i < 7; i++){
      let hourCount = weeklyData[index].hourCount;
      let hourEstimate = weeklyData[index].hourEstimate;
      index += 1;
      dataString += '{"value":"' + Math.floor((hourCount / hourEstimate)*100000) + '"}';
      if (i != 6) {
        dataString += ',';
      }
    }
    dataString += ']}]';

    console.log(dataString);

    
    displayData = JSON.parse(dataString);
    diaplayLabels = weeklyLabels;
    displayChart($scope);
  },

};

export { weekly };
