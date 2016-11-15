var Polyline = (function(){
  
  var strokeColor; 
  var opacity = 1;
  var strokeWeight = 5;
  //var path = array();
  
  var getStrokeColor = function(){
    return strokeColor;
  }
  
  var setStrokeColor = function(strokeColor){
    return this.strokeColor = strokeColor;
  }
  
  var getOpacity = function(){
    return opacity;
  }
  
  var setOpacity = function(opacity){
    return this.opacity = opacity;
  }
  
  var getStrokeWeight= function(){
    return strokeWeight;
  }
  
  var setStrokeWeight = function(strokeWeight){
    return this.strokeWeight = strokeWeight;
  }
  
 
  return {
    getStrokeColor : getStrokeColor,
    setStrokeColor : setStrokeColor,
    getOpacity : getOpacity,
    getStrokeWeight : getStrokeWeight
  }
})();