var genealogiaMod = angular.module('genealogiaMod', ['ngRoute', 'googleMapMod']);
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


var PersonController = function ($scope, $routeParams, $http, GoogleMapService){   
  $http.get('person/' + $routeParams.personId + '.json').success(function(data){
    $scope.person = data;
  });    
  $scope.$on('$includeContentLoaded', function(funObj, url) {
    if ( url == 'ng-views/mapWidget.html')
      GoogleMapService.loadGoogleMap();
  });
};
PersonController.$inject = ['$scope', '$routeParams', '$http', 'GoogleMapService'];
genealogiaMod.controller('PersonController', PersonController);
