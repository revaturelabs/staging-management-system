// ----------------------------------- Start Variables ----------------------------------- //

const YEAR = 'Year';
const MONTH = 'Month';
const WEEK = 'Week';

let scale;              // The scale of the graph equal to the constant values WEEK, MONTH, or YEAR.
let focalDate;          // The date that was used to create graph view.
let firstColumnIndex;   // The index of the first column relative dataSet the view was built from.

let originalData;       // The data retrieved from the data Base.
let displayData;        // A window of the data set being displayed determined by the focal Date.
let diaplayLabels;      // The column labels for displayData.

let weeklyData;         // Data grouped by day and displayed by week.
let monthlyData;        // Data grouped by week and displayed 5 weeks at a time with focal date
                        // determining the center.
let yearlyData;         // Data grouped by quarter year displaying the year focal point resides in.

let displayChart;

const weeklyLabels = [
  {
    label: 'Sunday',
  },
  {
    label: 'Monday',
  },
  {
    label: 'Tuesday',
  },
  {
    label: 'Wednesday',
  },
  {
    label: 'Thursday',
  },
  {
    label: 'Friday',
  },
  {
    label: 'Saturday',
  },
];

const yearlyLabels = [
  {
    label: '1st Quarter',
  },
  {
    label: '2nd Quarter',
  },
  {
    label: '3rd Quarter',
  },
  {
    label: '4th Quarter',
  },
];

/**
 * Chart display setup.
 */
const chartPoperties = {
  caption: 'Attendance Associates in Stagging',
  subCaption: scale,
  xAxisname: scale,
  yAxisName: 'Percentage of Attendance',
  numberPrefix: '%',
  paletteColors: '#0075c2',
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

// ----------------------------------- End Variables ----------------------------------- //


// ----------------------------------- Start Utilities ----------------------------------- //

/**
 * This function will conduct a recursive binary search on a section of an array.
 *
 * @param data - array to be searched
 * @param searchVal - value searching for
 * @param start - starting index
 * @param stop - stopping index
 * @param cmpFunction - a function of the form foo(searchVal, data[i]) that returns an integer
 *                      comparison value
 * @returns - the index corresponding to the closest value to searchVal.
 */
function binarySearch(data, searchVal, start, stop, cmpFunction) {
  if (start >= stop) {
    return stop;
  }
  const midpoint = Math.floor((start + stop) / 2);

  const value = cmpFunction(searchVal, data[midpoint]);
  if (value === 0) {
    return midpoint;
  } else if (value > 0) {
    return binarySearch(data, searchVal, midpoint + 1, stop, cmpFunction);
  }

  return binarySearch(data, searchVal, start, midpoint, cmpFunction);
}

/**
 * This is a wrapper function for the binary search it searches an entire array.
 *
 * @param data - array to be searched
 * @param searchVal - value searching for
 * @param cmpFunction - a function of the form foo(searchVal, data[i]) that returns an integer
 *                      comparison value
 * @returns - the index corresponding to the closest value to searchVal.
 */
function binarySearchHelper(data, searchVal, cmpFunction) {
  return binarySearch(data, searchVal, 0, data.length, cmpFunction);
}

/**
 * Converts a moment object to the first of the week.
 *
 * @param momentObj - date of interest.
 * @returns - sunday of the week containing momentObj.
 */
function convertToFirstOfTheWeek(momentObj) {
  const dayValue = momentObj.day();
  const newMoment = momentObj.subtract(dayValue, 'days');
  return moment(newMoment.format('YYYY-MM-DD'));
}

/**
 * Converts a moment object to a moment representing the first of the
 * month.
 *
 * @param momentObj - moment object to be evaluated
 * @returns - if momentObj is in month A then it returns the first of month A
 *            with time zeroed.
 */
function convertToFirstOfMonth(momentObj) {
  const dayValue = momentObj.format('DD') - 1;
  const newMoment = momentObj.subtract(dayValue, 'days');
  return moment(newMoment.format('YYYY-MM-DD'));
}

/**
 * Converts a moment object to the first day of the quarter year momentObj(A) is within.
 *
 * @param momentObj - date of interest.
 * @returns - jan 1st <= (A) <= mar 31st : jan 1st
 *            apr 1st <= (A) <= jun 30st : jun 1st
 *            jul 1st <= (A) <= sep 30st : jul 1st
 *            oct 1st <= (A) <= dec 31st : oct 1st
 *            (Time is zeroed)
 */
function convertToFirstOfQuarter(momentObj) {
  const monthValue = momentObj.month() % 3;
  let newMoment = momentObj.subtract(monthValue, 'months');
  newMoment = convertToFirstOfMonth(momentObj);

  return moment(newMoment.format('YYYY-MM-DD'));
}

/**
 * Converts to the first of the year that is contained in month.
 *
 * @param momentObj - moment object to be evaluated
 * @returns - if momentObj is in year A then it returns the jan 1st of year A
 *            with time zeroed.
 */
function convertToFirstOfYear(momentObj) {
  const monthValue = momentObj.month();
  let newMoment = momentObj.subtract(monthValue, 'months');
  newMoment = convertToFirstOfMonth(momentObj);
  return moment(newMoment.format('YYYY-MM-DD'));
}

/**
 * Compares searchVal(a) to currentVal.time(b) by creating date objects that ignore time.
 *
 * @searchVal - a moment object to be searched.
 * @currentVal - an object with an attribute time that can be parsed by moment.
 *
 * @return a == b (0), a < b (positive value), a > b (negative value)
 */
function cmpDay(searchVal, currentVal) {
  const parseMoment = moment(moment(currentVal.time).format('YYYY-MM-DD'));
  const zeroSearch = moment(searchVal.format('YYYY-MM-DD'));

  return zeroSearch.diff(parseMoment);
}

// ----------------------------------- End Utilities ----------------------------------- //


// ----------------------------------- Start Weekly ----------------------------------- //

/**
 * Function that "builds" weeklyData, for current implementation originalData is already in the
 * correct form. A Copy should be made if weeklyData needs to be edited in the future.
 */
function buildWeekly() {
  weeklyData = originalData;
}

/**
 * Creates a view of weeklyData using date as the focal point, if date is undefined it uses
 * todays date.
 *
 * @param $scope
 * @param date - a date within week to be viewed.
 * @returns
 */
function setWeekly($scope, tarDate) {
  let date = tarDate;
  if (date === undefined) {
    date = moment();
  }
  console.log("hello");
  alert(date.format('YYYY-MM-DDTHH:MM:SS'));
  const currDay = date.day();

  date.subtract(currDay, 'days');

  let index = binarySearchHelper(weeklyData, date, cmpDay);

  // Set global view properties.
  firstColumnIndex = index;
  focalDate = moment(date.format());
  scale = WEEK;

  let dataString = '[{"seriesname":"Weekly","data":[';

  let i;
  for (i = 0; i < 7; i += 1) {
    const hourCount = weeklyData[index].hourCount;
    const hourEstimate = weeklyData[index].hourEstimate;
    index += 1;
    const value = Math.floor((hourCount / hourEstimate) * 100);
    dataString += `{"value":"${value}"}`;
    if (i !== 6) {
      dataString += ',';
    }
  }
  dataString += ']}]';

  displayData = JSON.parse(dataString);
  diaplayLabels = weeklyLabels;

  displayChart($scope);
}

function weeklyColumnClick(ev, props, $scope) {
  // TODO: display modal.
  $scope.selectedValue = `$props.displayValue}/${props.categoryLabel}/${props.dataIndex}`;
}

// ----------------------------------- End Weekly ----------------------------------- //


// ----------------------------------- Start Monthly ----------------------------------- //

function buildMonthlyForEach(item) {
  const identityString = convertToFirstOfTheWeek(moment(item.time));
  const index = binarySearchHelper(monthlyData, moment(identityString), cmpDay);

  if (index < 0 || index >= monthlyData.length || monthlyData.length === 0) {
    const itemCpy = JSON.parse(JSON.stringify(item));
    itemCpy.time = identityString;
    monthlyData.push(itemCpy);
  } else {
    const existing = monthlyData[index];
    existing.hourCount = parseFloat(existing.hourCount) + parseFloat(item.hourCount);
    existing.hourCount = parseFloat(existing.hourEstimate) + parseFloat(item.hourEstimate);
  }
}

function buildMonthly() {
  monthlyData = [];
  originalData.forEach(buildMonthlyForEach);
}

function setMonthly($scope, tarDate) {
  let date = tarDate;
  if (date === undefined) {
    date = moment();
  }

  date = convertToFirstOfTheWeek(date);

  let index = binarySearchHelper(monthlyData, date, cmpDay) - 3;
  if (index < 0) {
    index = 0;
  }

  // Set global view properties
  firstColumnIndex = index;
  focalDate = moment(date.format());
  scale = MONTH;

  let dataString = '[{"seriesname":"Monthly","data":[';
  let valueString = '[';

  let i;
  for (i = 0; i < 5; i += 1) {
    const hourCount = monthlyData[index].hourCount;
    const hourEstimate = monthlyData[index].hourEstimate;
    index += 1;

    const start = moment(monthlyData[index].time).format('MM/DD');
    const stop = moment(monthlyData[index + 1].time).subtract(1, 'days').format('MM/DD');
    valueString += `{"label":"${start}-${stop}"}`;

    const value = Math.floor((hourCount / hourEstimate) * 100);
    dataString += `{"value":"${value}"}`;
    if (i !== 4) {
      dataString += ',';
      valueString += ',';
    }
  }
  dataString += ']}]';
  valueString += ']';

  displayData = JSON.parse(dataString);
  diaplayLabels = JSON.parse(valueString);

  displayChart($scope);
}

function monthlyColumnClick(ev, props, $scope) {
  const newDateIndex = props.dataIndex + firstColumnIndex;
  setWeekly($scope, moment(monthlyData[newDateIndex].time));

  $scope.selectedValue = `$props.displayValue}/${props.categoryLabel}/${props.dataIndex}`;
}

// ----------------------------------- End Monthly ----------------------------------- //


// ----------------------------------- Start Yearly ----------------------------------- //

function buildYearlyForEach(item) {
  const identityString = convertToFirstOfQuarter(moment(item.time));
  const index = binarySearchHelper(yearlyData, moment(identityString), cmpDay);

  if (index < 0 || index >= yearlyData.length || yearlyData.length === 0) {
    const itemCpy = JSON.parse(JSON.stringify(item));
    itemCpy.time = identityString;
    yearlyData.push(itemCpy);
  } else {
    const existing = yearlyData[index];
    existing.hourCount = parseFloat(existing.hourCount) + parseFloat(item.hourCount);
    existing.hourCount = parseFloat(existing.hourEstimate) + parseFloat(item.hourEstimate);
  }
}

function buildYearly() {
  yearlyData = [];
  originalData.forEach(buildYearlyForEach);
}

function setYearly($scope, tarDate) {
  let date = tarDate;
  if (date === undefined) {
    date = moment();
  }

  date = convertToFirstOfYear(date);

  let index = binarySearchHelper(yearlyData, date, cmpDay);

  // Set global view properties.
  firstColumnIndex = index;
  focalDate = moment(date.format());
  scale = YEAR;

  let dataString = '[{"seriesname":"Yearly","data":[';

  let i;
  for (i = 0; i < 3; i += 1) {
    const hourCount = yearlyData[index].hourCount;
    const hourEstimate = yearlyData[index].hourEstimate;
    index += 1;

    const value = Math.floor((hourCount / hourEstimate) * 100);
    dataString += `{"value":"${value}"}`;
    if (i !== 2) {
      dataString += ',';
    }
  }
  dataString += ']}]';

  displayData = JSON.parse(dataString);
  diaplayLabels = yearlyLabels;

  displayChart($scope);
}

function yearlyColumnClick(ev, props, $scope) {
  const newDateIndex = props.dataIndex + firstColumnIndex;
  setMonthly($scope, moment(yearlyData[newDateIndex].time));

  $scope.selectedValue = `$props.displayValue}/${props.categoryLabel}/${props.dataIndex}`;
}

// ----------------------------------- End Yearly ----------------------------------- //


// ----------------------------------- Start Nav ----------------------------------- //

function setNavFunctions($scope) {
  $scope.step = function step(steps) {
    switch (scale) {
      case WEEK:
        focalDate = focalDate.add(steps, 'days');
        setWeekly($scope, focalDate);
        break;
      case MONTH:
        focalDate = focalDate.add(steps, 'months');
        setMonthly($scope, focalDate);
        break;
      case YEAR:
        focalDate = focalDate.add(steps, 'years');
        setYearly($scope, focalDate);
        break;
      default:
    }
  };

  $scope.zoomOut = function zoomOut() {
    if (scale === WEEK) {
      setMonthly($scope, focalDate);
    } else if (scale === MONTH) {
      setYearly($scope, focalDate);
    }
  };
}

// ----------------------------------- End Nav ----------------------------------- //


// ----------------------------------- Start Main ----------------------------------- //

displayChart = function displayChartFunc($scope) {
  const categories = [
    {
      category: diaplayLabels,
    }];

  const dataset = displayData;

  const myDataSource = {
    chart: chartPoperties,
    categories,
    dataset,
  };

  $scope.selectedValue = 'nothing';

  const chart = new FusionCharts({
    type: 'stackedcolumn3d',
    renderAt: 'attn-chart-container',
    width: '550',
    height: '350',
    dataFormat: 'json',
    dataSource: myDataSource,
    events: {
      dataplotclick: function dataplotclick(ev, props) {
        $scope.$apply(() => {
          switch (scale) {
            case WEEK:
              weeklyColumnClick(ev, props, $scope);
              break;
            case MONTH:
              monthlyColumnClick(ev, props, $scope);
              break;
            case YEAR:
              yearlyColumnClick(ev, props, $scope);
              break;
            default:
          }
        });
      },
    },
  });

  chart.render();
};

/**
 * Build all graphs and set default graph to current week view.
 */
function buildGraphs($scope) {
  buildWeekly();
  setWeekly($scope);

  buildMonthly();
  buildYearly();
}

/**
 * Request checkin data from rest controller.
 */
function attendanceRequest($scope, $http) {
  $http({
    method: 'GET',
    url: '/checkin/report',
  }).then((response) => {
    originalData = response.data;
    buildGraphs($scope);
  });
}


/**
 * Request data and set scope bindings.
 */
const attendanceBarGraphCtrl = ($scope, $http) => {
  $scope.zoomOutStr = 'Zoom Out';
  attendanceRequest($scope, $http);
  setNavFunctions($scope);
};

export { attendanceBarGraphCtrl };

// ----------------------------------- End Main ----------------------------------- //
