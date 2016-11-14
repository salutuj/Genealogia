(function(){
  'use strict';
  angular.module('genealogiaModule').controller('PersonController', PersonController);
  PersonController.$inject = ['$scope', '$routeParams', '$http', 'PersonService', 'GoogleMapService'];    

  function PersonController($scope, $routeParams, $http, PersonService, GoogleMapService){
    $scope.map = { isLoaded: false, showAncestry: true, maxAncestryLevel: 4};
    $scope.personId = $routeParams.personId;
    $scope.$on('$includeContentLoaded', function(funObj, url) {
      if ( url == 'ng-views/mapWidget.html'){
        var promisso = PersonService.getAncestors( $scope.personId, $scope.map.maxAncestryLevel);
        promisso.then(function(personData) {
          $scope.person = personData;
          GoogleMapService.loadGoogleMap(personData);
          GoogleMapService.drawAncestryPathsOfPerson(personData, 1);
        });
      }
    });
  }
})();