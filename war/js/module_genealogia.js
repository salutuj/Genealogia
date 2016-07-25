var genealogiaMod = angular.module('genealogiaMod', ['ngRoute']);
genealogiaMod.config(function($routeProvider){
  $routeProvider.when('/', {
	  template: 'ng-views/familyDetails.html',
	  controller: 'DefaultFamilyCtrl'
  }).when('/family-:familyId',{
	  template: 'ng-views/familyDetails.html',
	  controller: 'FamilyCtrl'
  }).when('/person-:personId',{
	  template: 'ng-views/personDetails.html',
	  controller: 'PersonCtrl'
  }).otherewise({
	  redirectTo: '/'
  });
  
}

genealogiaApp.controller('PersonCtrl', function ($scope, $http){
  $http.get('json/person-{{personId}}.json').success(function(data){
    $scope.person = data;
  })	
});

genealogiaApp.controller('FamiliaCtrl', function ($scope, $http){
  $http.get('json/familia-{{familiaId}}.json').success(function(data){
    $scope.familia = data;
  })	
});
