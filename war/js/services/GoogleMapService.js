(function(){  
  'use strict';
  angular.module('genealogiaModule').service('GoogleMapService', GoogleMapService); 
      
  function GoogleMapService() {  
    var googleMapObject = null;  
    var personMarker = null;
    var ancestorPolylines = new Array();
    var ancestorMarkers = new Array();
  
    this.loadGoogleMap = function(personData) {  
      var zoomAndCenter = this.calculateZoomAndCenter(personData);  
      // TODO: use angular model way to turn googleMap element into map
      googleMapObject = new google.maps.Map(document.getElementById("googleMap"), {
        center : new google.maps.LatLng(zoomAndCenter.center.latitude, zoomAndCenter.center.longitude),
        zoom : zoomAndCenter.zoom,
        mapTypeId : google.maps.MapTypeId.ROADMAP
      }); 
    }
  
    this.calculateZoomAndCenter = function(personData){
      //TODO: calculate it 
      return {'zoom': 9, 'center' : {'latitude' : 50.255555, 'longitude' : 18.463611}};
    }
    
    this.drawAncestryPathsOfPerson = function(person, level){
      var latiPerson = person.birth.place.latitude;
      var longPerson = person.birth.place.longitude
      var strokeWeight = 5;
      drawMarker(person);
      if ( person.father != null){
        var strokeColor = "#1010" + (0xFF - Math.min(0xFF,level * 0x8)).toString(16);    
        var opacity = Math.pow(0.9, level);        
        var latiFather = person.father.birth.place.latitude;
        var longFather = person.father.birth.place.longitude;
        drawPath(latiFather, longFather, latiPerson, longPerson, strokeColor, strokeWeight, opacity);
        this.drawAncestryPathsOfPerson(person.father, level+1);
      }
      if ( person.mother != null){
        var strokeColor = "#" + (0xFF - Math.min(0xFF,level * 0x8)).toString(16) + "1010";            
        var opacity = Math.pow(0.9, level);        
        var latiMother = person.mother.birth.place.latitude;
        var longMother = person.mother.birth.place.longitude;
        drawPath(latiMother, longMother, latiPerson, longPerson, strokeColor, strokeWeight, opacity);
        this.drawAncestryPathsOfPerson(person.mother,level+1);
      }
    }
    
    function drawPath(lati1, long1, lati2, long2, strokeColor, strokeWeight, strokeOpacity){
      var polyline = new google.maps.Polyline();
      var arrowSymbol = { path: google.maps.SymbolPath.FORWARD_CLOSED_ARROW };
      var polylineIcons = [{ icon: arrowSymbol, offset: '33%', repeat: '33%'}];
      polyline.setOptions({
        'strokeColor' : strokeColor, 'strokeWeight' : strokeWeight, 'strokeOpacity': strokeOpacity,
        'icons' : polylineIcons
        }); 
      polyline.setPath([new google.maps.LatLng(lati1, long1),new google.maps.LatLng(lati2, long2)]);      
      polyline.setMap(googleMapObject);      
      ancestorPolylines.push(polyline);
    }
    
    function drawMarker(person){
      var marker = new google.maps.Marker({title : person.firstName + " " + person.lastName});
      marker.setPosition(new google.maps.LatLng(person.birth.place.latitude, person.birth.place.longitude));
      marker.setMap(googleMapObject);
      ancestorMarkers.push(marker);      
    }
  
    this.drawMainPersonStar = function(person){
      personMarker = new google.maps.Marker({
        position: new google.maps.LatLng(person.birth.place.latitude, person.birth.place.longitude),
        icon: {
          path: 'M 0,-24 6,-7 24,-6 10,4 15,21 0,11 -15,21 -10,4 -24,-7 -6,-7 z',
          fillColor: 'yellow',
          fillOpacity: 0.9,
          scale: 0.5,
          strokeColor: 'gold',
          strokeWeight: 3
          },
          map: googleMapObject
        });
    } 
    
    this.clearMap = function(){
      for ( var i = 0; i < ancestorPolylines.length; i++)
        ancestorPolylines[i].setMap(null);
      for ( var i = 0; i < ancestorPolylines.length; i++)
        ancestorMarkers[i].setMap(null);      
      personMarker.setMap(null)      
    }
  }
})();