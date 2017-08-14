/**
 * Created by colts on 6/8/2017.
 */
const managerCheckinsCtrl = ($scope, $http) => {
  $http.get('checkin/allTodays').then((result) => {
      $scope.checkins = result.data;
      $http.get('associate/allActive').then((result) => {
        $scope.associates = result.data;

        $scope.associates.forEach((associate) => {
          associate.checkin = {};
          let associatesCheckin = $scope.checkins.some((checkin) => {
            if(checkin.associate.name === associate.name) {
              associate.checkin = checkin;
              return true;
            }
          })
        })
        window.associates = $scope.associates;
      })
  });

  $scope.approvedFilter = (associate) => {
	  return (associate.checkin.approvedBy) ? false : true;

  }

  $scope.selectAllAssociatesWhoCheckedIn = () => {
    $scope.checkins.forEach((checkin) => {
      checkin.selected = true;
    });
  };


  $scope.approveSelectedCheckins = () => {
    let approvedCheckins = [];
    $scope.checkins.forEach((checkin) => {
      if (!checkin.approvedBy) {
        if (checkin.selected) {
          approvedCheckins.push(checkin);
        }
      }
    })

    $http.patch('checkin/approve-multiple', approvedCheckins).then(() => {
      approvedCheckins.forEach((checkin) => {
        checkin.approvedBy = true;
      })
    })
  }
};

export default managerCheckinsCtrl;
