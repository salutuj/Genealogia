var path = [];

path.push(new google.maps.LatLng(position.coords.latitude, position.coords.longitude));

// Create the map
var myOptions = {
  zoom : 16,
  center : path[0],
  mapTypeId : google.maps.MapTypeId.ROADMAP
}
var map = new google.maps.Map(document.getElementById("map"), myOptions);