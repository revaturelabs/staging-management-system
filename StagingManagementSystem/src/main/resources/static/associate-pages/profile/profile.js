const profileCtrl = ($scope, $http, userService) => {
  const associateId = userService.getUser().id;
  const associateUrl = `/associate/${associateId}`;

  if (associateId === undefined) {
    return;
  }

  $http({
    method: 'GET',
    url: associateUrl,
  }).then((response) => {
    $scope.associate = response.data;
    $scope.portfolioUrl = response.data.portfolioLink;
  });

  $scope.portfolioUrlInput = '';
  $scope.status = 'Active';

  $scope.toggleSkillsModal = () => {
    $scope.sendingRequest = false;
    $scope.skillsModalButtonValue = 'Save';
    $scope.additionalSkillsInput = $scope.associate.skills.map(skill => skill.value).join(',');
    $('#additionalSkillsModal').modal('show');
  };
  $scope.openPortfolioUrlModal = () => {
    $scope.sendingRequest = false;
    $scope.portfolioModalButtonValue = 'Save';
    $scope.portfolioUrlInput = $scope.portfolioUrl;
    $('#portfolioUrlModal').modal('show');
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
    $scope.associate.skills = $scope.additionalSkillsInput.split(',')
      .filter(skill => skill !== '')
      .map((skill) => {
        const existingSkill = $scope.associate.skills.find(aSkill => aSkill.value === skill);
        return { id: (existingSkill !== undefined ? existingSkill.id : 0), value: skill };
      });

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
};

export default profileCtrl;
