var genealogiaMod = angular.module('genealogiaMod', ['ngRoute']);
genealogiaMod.config(function($routeProvider){
  $routeProvider.when('/', {
	  template: '<div><p>Default family</p>{{defaultFamily.details}}</div>',
	  controller: 'DefaultFamilyCtrl'
  }).when('/person-:personId',{
	  template: '<div><p>Person details:</p>{{person.details}}</div>',
	  controller: 'PersonCtrl'
  }).when('/json/person-:personId',{
	  template: '<div><p>Person details:</p>{{person.details}}</div>',
	  controller: 'PersonCtrl'
  }).when('/family-:familyId',{
	  template: '<div><p>Family details:</p>{{family.details}}</div>',
	  controller: 'FamilyCtrl'
  }).when('/json/family-:familyId',{
	  template: '<div><p>Family details:</p>{{family.details}}</div>',
	  controller: 'FamilyCtrl'
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
