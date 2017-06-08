const profileCtrl = ($scope, $http) => {
  const associateId = 184;
  const method = 'GET';
  const associateUrl = `/associate/${associateId}`;

  $http({
    method: 'GET',
    url: associateUrl,
  }).then((response) => {
    $scope.associate = response.data;
    console.log(response);
    $scope.portfolioUrl = response.data.portfolioLink;
  });

  $scope.portfolioUrlInput = '';
  $scope.status = 'Active';

  $scope.shortenUrl = (url, length) => ( // used to display portfolioUrl
    (url === '' || url === undefined) ? '' : `${url.substring(0, length)}...`
  );
  $scope.toggleSkillsModal = () => {
    $scope.additionalSkillsInput = $scope.associate.skills.map(skill => skill.value).join(',');
    $('#additionalSkillsModal').modal('show');
  };
  $scope.openPortfolioUrlModal = () => {
    $scope.portfolioUrlInput = $scope.portfolioUrl;
    $('#portfolioUrlModal').modal('show');
  };
  $scope.submitPortfolioUrl = () => {
    $scope.associate.portfolioLink = $scope.portfolioUrlInput;

    $http({
      method: 'PUT',
      url: '/associate/',
      data: $scope.associate,
    }).then((response) => {
      console.log('success');
    }, () => {
      console.log('error');
    });

    $('#portfolioUrlModal').modal('hide');
  };
  $scope.submitSkills = () => {
    const skills = $scope.associate.skills.map(skill => skill.value);
    $scope.associate.skills = $scope.additionalSkillsInput.split(',')
      .filter(skill => skill !== '')
      .map(skill => {
        const existingSkill = $scope.associate.skills.find(skill => skill.value === skill);
        return { id: (existingSkill !== undefined ? existingSkill.id : 0), value: skill };
      });

      $http({
        method: 'PUT',
        url: '/associate/',
        data: $scope.associate,
      }).then((response) => {
        console.log('success');
      }, () => {
        console.log('error');
      });

    $('#additionalSkillsModal').modal('hide');
  };
};

export default profileCtrl;
