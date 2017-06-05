const profileCtrl = ($scope) => {
  $scope.name = 'Yushi Canastra';
  $scope.batchType = 'Java';
  $scope.skills = ['Java', 'Spring', 'Hibernate', 'Servlets', 'JSP'];
  $scope.additionalSkills = ['hello', 'poop'];
  $scope.additionalSkillsInput = '';
  $scope.submit = () => {
  };
  $scope.toggleSkillsModal = () => {
    $scope.additionalSkillsInput = $scope.additionalSkills.join(',');
    $('#additionalSkillsModal').modal('show');
  };
  $scope.submitSkills = () => {
    $scope.additionalSkills = $scope.additionalSkillsInput.split(',')
      .filter(skill => skill !== '');
    $('#additionalSkillsModal').modal('hide');
  };
};

export { profileCtrl };
