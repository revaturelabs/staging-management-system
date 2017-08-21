function profileCtrl($scope, $http, userService, $stateParams, $state, $window) {
  if ($state.includes('manager')) {
    $scope.isManager = true;
    $http.get('client/priority').then((response) => {
      $scope.clients = response.data;
    });
  }
  const associateId = $scope.isManager ? $stateParams.id : userService.getUser().id;

  if (associateId === undefined) {
    return;
  }

  const associateUrl = `/associate/${associateId}`;
  $http({
    method: 'GET',
    url: associateUrl,
  }).then((response) => {
    $scope.associate = { ...response.data };
  });

  $scope.portfolioUrlInput = '';

  $scope.addSkill = () => {
    const skillAlreadyExists = $scope.additionalSkillsValues
      .find(skill => skill.value === $scope.newSkillValue) !== undefined;
    if (skillAlreadyExists || $scope.newSkillValue === '') {
      return;
    }
    const skillToAdd = { id: 0, value: $scope.newSkillValue };
    $scope.additionalSkillsValues = [...$scope.additionalSkillsValues, skillToAdd];
    $scope.newSkillValue = '';
  };

  $scope.removeSkill = (skillToDelete) => {
    $scope.additionalSkillsValues = $scope.additionalSkillsValues
      .filter(skill => skill.value !== skillToDelete.value);
  };

  $scope.toggleSkillsModal = () => {
    $scope.sendingRequest = false;
    $scope.skillsModalButtonValue = 'Save';
    $scope.additionalSkillsValues = [...$scope.associate.skills];
    $scope.newSkillValue = '';
    $('#additionalSkillsModal').modal('show');
  };

  $scope.openPortfolioUrlModal = () => {
    $scope.sendingRequest = false;
    $scope.portfolioModalButtonValue = 'Save';
    $scope.portfolioUrlInput = $scope.associate.portfolioLink;
    $('#portfolioUrlModal').modal('show');
  };

  $scope.toggleMappedModal = () => {
    window.scope = $scope;
    $scope.sendingRequest = false;
    $scope.mappedModalButtonValue = 'Save';
    if ($scope.associate.lockedTo) {
      $scope.clients.some((client) => {
        if (client.name === $scope.associate.lockedTo.name) {
          $scope.associate.lockedTo = client;
          return true;
        }
      });
    }

    $('#mappedToClientModal').modal('show');
  };

  $scope.submitPortfolioUrl = () => {
    $scope.associate.portfolioLink = $scope.portfolioUrlInput;

    $scope.portfolioModalButtonValue = 'Saving...';
    $scope.sendingRequest = true;
    $http({
      method: 'PUT',
      url: '/associate/',
      data: $scope.associate,
    }).then(() => {
      $('#portfolioUrlModal').modal('hide');
    }, () => {
      $('#portfolioUrlModal').modal('hide');
    });
  };

  $scope.submitSkills = () => {
    $scope.associate.skills = [...$scope.additionalSkillsValues]
      .filter(skill => skill.value !== '');

    $scope.sendingRequest = true;
    $scope.skillsModalButtonValue = 'Saving...';
    $http({
      method: 'PUT',
      url: '/associate/',
      data: $scope.associate,
    }).then(() => {
      $('#additionalSkillsModal').modal('hide');
    }, () => {
      $('#additionalSkillsModal').modal('hide');
    });
  };

  $scope.updateLockedTo = () => {
    $scope.sendingRequest = true;
    $scope.mappedModalButtonValue = 'Saving...';
    $http({
      method: 'PUT',
      url: '/associate/',
      data: $scope.associate,
    }).then(() => {
      $('#mappedToClientModal').modal('hide');
    }, () => {
      $('#mappedToClientModal').modal('hide');
    });
  };

  $scope.openPortfolioLink = () => {
    $window.open($scope.associate.portfolioLink);
  };

  $scope.associateHasNoSkills = () => {
    if ($scope.associate === undefined) {
      return true;
    }
    return $scope.associate.batch.batchType.skills.concat($scope.associate.skills).length === 0;
  };

  $scope.onEnterAddSkill = (event) => {
    if (event.which === 13) {
      $scope.addSkill();
    }
  };
}

export default profileCtrl;
