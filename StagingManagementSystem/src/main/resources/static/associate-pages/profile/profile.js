const profileCtrl = ($scope) => {
  $scope.name = 'Yushi Canastra';
  $scope.batchType = 'Java';
  $scope.portfolioUrl = 'http://www.example-portfolio.com/';
  $scope.portfolioUrlInput = '';
  $scope.skills = ['Java', 'Spring', 'Hibernate', 'Servlets', 'JSP'];
  $scope.additionalSkills = ['hello', 'poop'];
  $scope.additionalSkillsInput = '';
  $scope.submit = () => {
  };
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
