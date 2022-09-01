//Para funcionalidad Drag & Drop
function allowDrop(ev) {
  ev.preventDefault();
}
  
function drag(ev) {
  ev.dataTransfer.setData("text", ev.target.id);
}
  
function drop(ev) {
  ev.preventDefault();
  var data = ev.dataTransfer.getData("text");
  ev.target.appendChild(document.getElementById(data));
}

//Para funcionalidad Canvas

window.onload = function(){
  iniTitle();
  uploadPic();
  degradePic();
}

function iniTitle(){
  var title = document.getElementById("titulo");
  var titletx = title.getContext("2d");
  titletx.font = "30px Arial";
  titletx.strokeStyle = "#FF0000";
  titletx.strokeText("Bienvenid@! Tenemos tu vuelo ideal", 20, 50);
}

function uploadPic(){
  var picMap = document.getElementById('picMap');
  var ctx = picMap.getContext('2d');
  var img = new Image();

  img.onload = function(){
    picMap.width = 500;
    picMap.height = 300;
    ctx.drawImage(img, 0, 0, img.width, img.height);
  }
  img.src = "img/mundo.gif";
}

function degradePic(){
  var picAvion = document.getElementById('picAvion');
  var ctxA = picAvion.getContext('2d');

  var lingrad = ctxA.createLinearGradient(0,0,0,picAvion.height);
  lingrad.addColorStop(0, 'orange');
  lingrad.addColorStop(.6, 'purple');
  lingrad.addColorStop(1, 'blue');

  ctxA.fillStyle = lingrad;
  ctxA.fillRect(10,10,200, 100);

  var img = new Image();
  img.onload = function(){
    var icvs = document.createElement('canvas');
    icvs.width = img.width;
    icvs.height = img.height;
    var ictx = icvs.getContext('2d');
    ictx.drawImage(img, 0, 0);

    ictx.globalCompositeOperation = 'destination-out';

    var gradient = ictx.createLinearGradient(0, 0, 0, icvs.height);
    gradient.addColorStop(.4, "transparent");
    gradient.addColorStop(1, "white");
    ictx.fillStyle = gradient;
    ictx.fillRect(0, 0, icvs.width, icvs.height);

    picAvion.width = 700;
    picAvion.height = 300;
    ctxA.drawImage(icvs, 0, 0, img.width, img.height, 0, 0, picAvion.width, picAvion.height);
  }
  img.src = "img/avion.jpg"; 


}