const interviewsCtrl = ($scope) => {

    $scope.interviews = [
        {
            associateName: 'Billy',
            batch: 'Java',
            client: 'Infosys',
            scheduled_time: '6-5-2017'
        },
        {
            associateName: 'Bob',
            batch: 'Java',
            client: 'CapitolOne',
            scheduled_time: '6-23-2017'
        },
        {
            associateName: 'Sally',
            batch: '.Net',
            client: 'BofA',
            scheduled_time: '6-10-2017'
        },
        {
            associateName: 'Jim',
            batch: 'SDET',
            client: 'Oracle',
            scheduled_time: '7-2-2017'
        },
        {
            associateName: 'Jimbo',
            batch: 'Java',
            client: 'CapitalOne',
            scheduled_time: '6-23-2017'
        },
        {
            associateName: 'Peter',
            batch: 'Java',
            client: 'Wells Fargo',
            scheduled_time: '6-15-2017'
        }];
}

export { interviewsCtrl };
