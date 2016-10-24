var googleMapMod = angular.module('googleMapMod', []);

googleMapMod.service('GoogleMapService', function() {
  this.loadGoogleMap = function() {
    var mapProp = {
      center : new google.maps.LatLng(50.255555, 18.463611),
      zoom : 9,
      mapTypeId : google.maps.MapTypeId.ROADMAP
    };

    var map = new google.maps.Map(document.getElementById("googleMap"), mapProp);

    var tourplan = new google.maps.Polyline({
      path : [ 
        new google.maps.LatLng(50.305555, 18.503611),
        new google.maps.LatLng(50.205555, 18.403611),
        new google.maps.LatLng(50.355555, 18.553611),
        new google.maps.LatLng(50.155555, 18.353611) ],
      strokeColor : "#6F8FAF",
      strokeOpacity : 0.6,
      strokeWeight : 2
    });
    tourplan.setMap(map);
      // to remove plylines
      // tourplan.setmap(null);
  }
});