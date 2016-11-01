var PersonController = function ($scope, $routeParams, $http, GoogleMapService){
  $scope.map = { showAncestry: true, maxAncestryLevel: 4};
  $http.get('person/' + $routeParams.personId + '.json').success(function(data){
    $scope.person = data;
  });      
  $scope.$on('$includeContentLoaded', function(funObj, url) {
    if ( url == 'ng-views/mapWidget.html')
      GoogleMapService.loadGoogleMap();     
  });
};
