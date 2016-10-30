var GPSPosition = (function(){
  var latitude = 0;
  var longitude = 0;
  
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
    getLatitude : getLatitude,
    setLatitude : setLatitude,
    getLongitude : getLongitude,
    setLongitude : setLongitude,
  }
  
});