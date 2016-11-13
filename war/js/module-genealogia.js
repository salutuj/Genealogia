angular.module('genealogiaModule', ['ngRoute']).config(
  function($routeProvider){
    $routeProvider.when('/', {
  	  templateUrl: 'ng-views/familyDetails.html',
	    controller: 'FamilyController'
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

