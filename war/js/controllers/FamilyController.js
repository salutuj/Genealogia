(function(){
  'use strict';
  angular.module('genealogiaModule').controller('FamilyController', FamilyController);
  FamilyController.$inject = ['$scope', '$routeParams', '$http'];
   
  function FamilyController($scope, $routeParams, $http){
    if ($routeParams.familyId == null)
      $http.get('family/default.json').success(function(data){
        $scope.family = data;
      });  
    else
      $http.get('family/' + $routeParams.familyId + '.json').success(function(data){
        $scope.family = data;
      });    
  };
})();