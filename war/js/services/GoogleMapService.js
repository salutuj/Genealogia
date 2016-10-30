var googleMapModule = angular.module('googleMapModule', []);

googleMapModule.service('GoogleMapService', function() {  
  var googleMapObject;  
  var ancestorPolylines = new Array();
  
  this.loadGoogleMap = function() {  
    googleMapObject = new google.maps.Map(document.getElementById("googleMap"), {
      center : new google.maps.LatLng(50.255555, 18.463611),
      zoom : 9,
      mapTypeId : google.maps.MapTypeId.ROADMAP
    }); 
  }
  
  this.drawAncestor = function(ancestor){    
    var man = ancestor.getSex() === 'M';
    var varColour = (0xFF - ancestor.getLevel() * 0x8).toString();    
    var polyline = new google.maps.Polyline();
    polyline.setStrokeColor(man ? "#1010" + varColour : "#" + varColour + "1010"); 
    polyline.setOpacity(pow(0.9, ancestor.getLevel()));
    polyline.setStrokeWeight(5);
    polyline.setPath([
      new google.maps.LatLng(ancestor.getBirthPlaceCoords().getLatitude(), ancestor.getBirthPlaceCoords().getLongitude()),
      new google.maps.LatLng(ancestor.getDescendant().getBirthPlaceCoords().getLatitude(), ancestor.getDescendant().getBirthPlaceCoords().getLongitude())]);
    polyline.setMap(googleMapObject);      
    ancestorPolylines.push(polyline);
  }
  
  this.clearMap = function(){
    for ( var i = 0; i < ancestorPolylines.length; i++)
      ancestorPolylines[i].setmap(null);
  }
});