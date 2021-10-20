
var map;
//this will draw the map inside the div section in html
function initMap(){
			var options = {
				zoom: 8,
				center: {lat: 34.5199, lng: -105.8701},
				mapTypeId: google.maps.MapTypeId.ROADMAP 
			}
			map = new google.maps.Map(document.getElementById('map'), options)
}