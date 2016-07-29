var genealogiaMod = angular.module('genealogiaMod', ['ngRoute']);
genealogiaMod.config(function($routeProvider){
  $routeProvider.when('/', {
	  templateUrl: 'ng-views/familyDetails.html',
	  controller: 'DefaultFamilyCtrl'
  }).when('/family-:familyId',{
	  template: 'ng-views/familyDetails.html',
	  controller: 'FamilyCtrl'
  }).when('/person-:personId',{
	  template: 'ng-views/personDetails.html',
	  controller: 'PersonCtrl'
  }).otherwise({
	  redirectTo: '/'
  });
  
});

genealogiaMod.controller('DefaultFamilyCtrl', function ($scope, $http){
	  $http.get('defaultFamily.json').success(function(data){
	    $scope.family = data;
	  })	
	});

genealogiaMod.controller('PersonCtrl', function ($scope, $http){
  $http.get('person-{{personId}}.json').success(function(data){
    $scope.person = data;
  })	
});

genealogiaMod.controller('FamilyCtrl', function ($scope, $http){
  $http.get('family-{{familyId}}.json').success(function(data){
    $scope.family = data;
  })	
});
