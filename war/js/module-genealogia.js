var genealogiaMod = angular.module('genealogiaMod', ['ngRoute', 'personServiceModule', 'googleMapModule']);
genealogiaMod.config(function($routeProvider){
  $routeProvider.when('/', {
	  templateUrl: 'ng-views/familyDetails.html',
	  controller: 'DefaultFamilyController'
  }).when('/family/:familyId',{
	  templateUrl: 'ng-views/familyDetails.html',
	  controller: 'FamilyController'
  }).when('/person/:personId',{
	  templateUrl: 'ng-views/personDetails.html',
	  controller: 'PersonController'
  }).otherwise({
	  redirectTo: '/'
  });
  
});

var DefaultFamilyController = function ($scope, $http){
  $http.get('family/default.json').success(function(data){
    $scope.family = data;
  });    
};
DefaultFamilyController.$inject = ['$scope', '$http'];
genealogiaMod.controller('DefaultFamilyController', DefaultFamilyController);


var FamilyController = function ($scope, $routeParams, $http){
  $http.get('family/' + $routeParams.familyId + '.json').success(function(data){
    $scope.family = data;
  });    
};
FamilyController.$inject = ['$scope', '$routeParams', '$http'];
genealogiaMod.controller('FamilyController', FamilyController);


PersonController.$inject = ['$scope', '$routeParams', '$http', 'GoogleMapService', 'PersonService'];
genealogiaMod.controller('PersonController', PersonController);
