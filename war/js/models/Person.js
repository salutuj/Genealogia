var Person = (function(){
  
  var birthPlaceCoords;
  
  var getBirthPlaceCoords = function(){
    return birthPlaceCoords;
  }
  
  var setBirthPlaceCoords = function(birthPlaceCoords){
    return this.birthPlaceCoords = birthPlaceCoords;
  }
  
  return {
    getBirthPlaceCoords : getBirthPlaceCoords,
    setBirthPlaceCoords : setBirthPlaceCoords
  }
})();