var GPSPosition = (function(){
  var name = "";
  var latitude = 0;
  var longitude = 0;
  
  var getName = function(){
    return name;
  }
  
  var setName = function(name){
    this.name = name;
  } 
  
  var getLatitude = function (){
    return latitude;
  }
  
  var setLatitude = function (latitude){
    this.latitude = latitude;    
  }
  
  var getLongitude = function (){
    return longitude;
  }
  
  var setLongitude = function (longitude){
    this.longitude = longitude;    
  }
  
  return {
    getName : getName,
    setName : setName,
    getLatitude : getLatitude,
    setLatitude : setLatitude,
    getLongitude : getLongitude,
    setLongitude : setLongitude,
  }
  
});