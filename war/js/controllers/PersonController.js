var PersonController = function ($scope, $routeParams, $http, GoogleMapService){   
  $http.get('person/' + $routeParams.personId + '.json').success(function(data){
    $scope.person = data;
  });    
  $scope.$on('$includeContentLoaded', function(funObj, url) {
    if ( url == 'ng-views/mapWidget.html')
      GoogleMapService.loadGoogleMap();     
  });
};
