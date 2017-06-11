<<<<<<< HEAD
const interviewsCtrl = ($scope) => {
  $scope.interviewSelect = (interview) => {
    $scope.interviewSideTable = { interview };
  };

  $scope.interviews = [
    {
      associateName: 'Billy',
      batch: 'Java',
      client: 'Infosys',
      scheduled_time: '6-5-2017',
    },
    {
      associateName: 'Bob',
      batch: 'Java',
      client: 'CapitolOne',
      scheduled_time: '6-23-2017',
    },
    {
      associateName: 'Sally',
      batch: '.Net',
      client: 'BofA',
      scheduled_time: '6-10-2017',
    },
    {
      associateName: 'Jim',
      batch: 'SDET',
      client: 'Oracle',
      scheduled_time: '7-2-2017',
    },
    {
      associateName: 'Jimbo',
      batch: 'Java',
      client: 'CapitalOne',
      scheduled_time: '6-23-2017',
    },
    {
      associateName: 'Peter',
      batch: 'Java',
      client: 'Wells Fargo',
      scheduled_time: '6-15-2017',
    }];
=======
const interviewsCtrl = ($scope, $http) => {
    $http({
        method : "GET",
        url : "interviews/all"
    }).then(function mySuccess(response) {
        $scope.interviews = response.data;
    }, function myError(response) {
        console.log("error!");
    });


>>>>>>> 40e8335202f9099d2b62eaffa32ed7bc17d50acc
};

export { interviewsCtrl };
