var personServiceModule = angular.module('personServiceModule', []);

personServiceModule.service('PersonService', ['$http', function($http) {  

  this.getAncestors = function(person){
    
    $http.get('person/' + person.personId + '.json').success(function(data){
      $scope.person = data;
    });    
  }
}]);
