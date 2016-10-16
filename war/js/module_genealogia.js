var genealogiaMod = angular.module('genealogiaMod', ['ngRoute']);
genealogiaMod.config(function($routeProvider){
  $routeProvider.when('/', {
	  templateUrl: 'ng-views/familyDetails.html',
	  controller: 'DefaultFamilyCtrl'
  }).when('/family/:familyId',{
	  templateUrl: 'ng-views/familyDetails.html',
	  controller: 'FamilyCtrl'
  }).when('/person/:personId',{
	  templateUrl: 'ng-views/personDetails.html',
	  controller: 'PersonCtrl'
  }).otherwise({
	  redirectTo: '/'
  });
  
});

genealogiaMod.controller('DefaultFamilyCtrl', function ($scope, $http){
	  $http.get('family/default.json').success(function(data){
	    $scope.family = data;
	  })	
	});

genealogiaMod.controller('PersonCtrl', function ($scope, $routeParams, $http){
  $http.get('person/' + $routeParams.personId + '.json').success(function(data){
    $scope.person = data;
  })	
});

genealogiaMod.controller('FamilyCtrl', function ($scope, $routeParams, $http){
  $http.get('family/' + $routeParams.familyId + '.json').success(function(data){
    $scope.family = data;
  })	
});
