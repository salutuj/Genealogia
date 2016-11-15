(function(){
  'use strict';
  angular.module('genealogiaModule').service('PersonService', PersonService);
  PersonService.$inject = ['$http'];
  
  function PersonService($http) {
    this.getPerson = function(id) {
      return $http.get('person/' + id + '.json').then(function(result) {
        return result.data; // or TODO model
      });
    }

    this.getAncestors = function(id, maxLevel) {
      return $http.get('person/' + id + '/ancestryTree.json?maxLevel=' + maxLevel).then(function(result) {
        return result.data; // or TODO model
      });
    }
  } 
})();