
navigator.geolocation.getCurrentPosition(function(position) {
  document.getElementById("geo").value=""+position.coords.latitude+"*"+position.coords.longitude;
});


