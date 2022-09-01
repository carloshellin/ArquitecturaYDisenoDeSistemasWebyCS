function geoFindMe() {

    const estado = document.querySelector('#estado');
    const enlaceMap = document.querySelector('#enlaceMap');
  
    enlaceMap.href = '';
    enlaceMap.textContent = '';
  
    function success(position) {
      const latitude  = position.coords.latitude;
      const longitude = position.coords.longitude;
  
      estado.textContent = '';
      enlaceMap.href = `https://www.google.com/maps/@${latitude},${longitude},17z?hl=es-ES`;
      enlaceMap.textContent = `Latitud: ${latitude} °, Longitud: ${longitude} °`;
    }
  
    function error() {
      estado.textContent = 'No se puede mostrar su ubicación';
    }
  
    if(!navigator.geolocation) {
      estado.textContent = 'La geolocalización no está disponible para su navegador';
    } else {
      estado.textContent = 'Localizando…';
      navigator.geolocation.getCurrentPosition(success, error);
    }
  
  }
  
  document.querySelector('#miLoc').addEventListener('click', geoFindMe);