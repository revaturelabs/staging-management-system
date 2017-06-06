const profileCtrl = ($scope, $http) => {
  const associateId = 96;
  const method = 'GET';
  const url = `/associate/${associateId}`;

  $http({
    method,
    url,
  }).then((response) => {
    $scope.name = response.data.name;
    $scope.batchType = (response.data.batch === null) ? 'None' : response.data.batch;
    $scope.portfolioUrl = response.data.portfolioLink;
  });

  $scope.name = '';
  $scope.batchType = '';
  $scope.portfolioUrl = '';
  $scope.portfolioUrlInput = '';
  $scope.skills = ['Java', 'Spring', 'Hibernate', 'Servlets', 'JSP'];
  $scope.additionalSkills = ['hello', 'poop'];
  $scope.status = 'Active';
  $scope.additionalSkillsInput = '';
  $scope.shortenUrl = (urlToShorten, length) => ( // used to display portfolioUrl
    `${urlToShorten.substring(0, length)}...`
  );
  $scope.toggleSkillsModal = () => {
    $scope.additionalSkillsInput = $scope.additionalSkills.join(',');
    $('#additionalSkillsModal').modal('show');
  };
  $scope.openPortfolioUrlModal = () => {
    $scope.portfolioUrlInput = $scope.portfolioUrl;
    $('#portfolioUrlModal').modal('show');
  };
  $scope.submitPortfolioUrl = () => {
    $scope.portfolioUrl = $scope.portfolioUrlInput;
    $('#portfolioUrlModal').modal('hide');
  };
  $scope.submitSkills = () => {
    $scope.additionalSkills = $scope.additionalSkillsInput.split(',')
      .filter(skill => skill !== '');
    $('#additionalSkillsModal').modal('hide');
  };
};

export default profileCtrl;
