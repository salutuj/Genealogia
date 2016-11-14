(function(){  
  'use strict';
  angular.module('genealogiaModule').service('GoogleMapService', GoogleMapService); 
      
  function GoogleMapService() {  
    var googleMapObject = null;  
    var ancestorMarker = null;
    var ancestorPolylines = new Array();
  
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
      return {"zoom": 9, "center" : {"latitude" : 50.255555, "longitude" : 18.463611}};
    }
    
    this.drawAncestryPathsOfPerson = function(person, level){
      var latiPerson = person.birth.place.latitude;
      var longPerson = person.birth.place.longitude
      var strokeWeight = 5;
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
      var polylineIcons = [{ icon: arrowSymbol, offset: '33%' }, { icon: arrowSymbol, offset: '66%' },
        { icon: arrowSymbol, offset: '99%' }];
      polyline.setOptions({
        "strokeColor" : strokeColor, "strokeWeight" : strokeWeight, "strokeOpacity": strokeOpacity,
        "icons" : polylineIcons
        }); 
      polyline.setPath([new google.maps.LatLng(lati1, long1),new google.maps.LatLng(lati2, long2)]);      
      polyline.setMap(googleMapObject);      
      ancestorPolylines.push(polyline);
    }
    
    this.drawMainPersonStar = function(person){
      var goldStar = {
          path: 'M 25,1 31,18 49,18 35,29 40,46 25,36 10,46 15,29 1,18 19,18 z',
          fillColor: 'yellow',
          fillOpacity: 0.8,
          scale: 1,
          strokeColor: 'gold',
          strokeWeight: 3
        };

        ancestorMarker = new google.maps.Marker({
          position: new google.maps.LatLng(person.birth.place.latitude, person.birth.place.longitude),
          icon: goldStar,
          map: googleMapObject
        });
    } 
    
    this.clearMap = function(){
      for ( var i = 0; i < ancestorPolylines.length; i++)
        ancestorPolylines[i].setMap(null);
      ancestorMarker.setMap(null);
    }
  }
})();