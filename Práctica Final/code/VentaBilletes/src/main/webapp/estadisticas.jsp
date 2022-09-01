﻿<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.min.js"></script>
	<title>Alcala Airlines | Realización de Pagos</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="css/style.css" type="text/css">
	<link rel="shortcut icon" href="img/Alcala airlines.png" type="image/x-icon">
	<img class="fotoCabecera" src="img/iconoNombre.png">
</head>
        <% if (session.getAttribute("admin") == null) { %>
            <%-- Si no está iniciada la sesión como administrador/cliente, se vuelve al prncipio --%>
            <meta http-equiv="refresh" content="0; url=index.html" />
        <% }%>
        
	<script type="text/javascript">
		window.onload = function(){
                    barrasPrecio();
                    solicitarValores("NumeroVuelos");
                    solicitarValores("RangoEdades");
                    solicitarValores("Menores");
                    solicitarValores("HombreMujer");
		}
		
		function pieVuelosRuta(numVuelos)
		{
                    var pastel  = document.getElementById("pastelVuelosRuta").getContext("2d");
                    const myChart = new Chart(pastel, {
                        type: 'pie',
                        data: {
                            labels: ['Avión1', 'Avión2', 'Avión3', 'Avión4', 'Avión5', 'Avión6'],
                            datasets: [{
                                label: '# de vuelos por avión',
                                data: numVuelos,
                                backgroundColor: [
                                    'rgba(255, 99, 132, 0.2)',
                                    'rgba(54, 162, 235, 0.2)',
                                    'rgba(255, 206, 86, 0.2)',
                                    'rgba(75, 192, 192, 0.2)',
                                    'rgba(153, 102, 255, 0.2)',
                                    'rgba(255, 159, 64, 0.2)'
                                ],
                                borderColor: [
                                    'rgba(255, 99, 132, 1)',
                                    'rgba(54, 162, 235, 1)',
                                    'rgba(255, 206, 86, 1)',
                                    'rgba(75, 192, 192, 1)',
                                    'rgba(153, 102, 255, 1)',
                                    'rgba(255, 159, 64, 1)'
                                ],
                                borderWidth: 1
                            }]
                        },
                        options: {
                            scales: {
                                y: {
                                    beginAtZero: true
                                }
                            }
                        }
                    });
		}

		function pieViajerosMenorMayor(datoMenorMayor)
		{
                    var pastel  = document.getElementById("pastelViajerosMenorEdad").getContext("2d");
                    const myChart = new Chart(pastel, {
                        type: 'pie',
                        data: {
                             labels: ['< 18', '>= 18'],
                            datasets: [{
                                label: '# Viajeros menores o mayores de edad',
                                data: datoMenorMayor,
                                backgroundColor: [
                                    '#f44f2e',
                                    '#64e31c'
                                ],
                                borderColor: [
                                    '#379900',
                                    '#a11e03'
                                ],
                                borderWidth: 1
                            }]
                        },
                        options: {
                            scales: {
                                y: {
                                    beginAtZero: true
                                }
                            }
                        }
                    });
		}

		function barrasPrecio()
		{
			var barras  = document.getElementById("barrasPrecio").getContext("2d");
			var chartCalend = new Chart(barras,{
		        type: 'bar',
		        data: {
		            labels: ['Madrid-Paris','Madrid-Londres','Madrid-Berlin','Barcelona-Paris','Barcelona-Londres','Barcelona-Berlin'],
		            datasets: [{
		                label: 'Precios por ruta',
		                data: [1,4,5,6,1,4],
		                backgroundColor: [
		                    'rgba(255, 99, 132, 1)',
		                    'rgba(54, 162, 235, 1)',
		                    'rgba(255, 206, 86, 1)',
		                    'rgba(255, 99, 132, 1)',
		                    'rgba(54, 162, 235, 1)',
		                    'rgba(255, 206, 86, 1)'
		                ],
		                borderWidth: 5,
		                fill: false
		            }]
		        },
		        options: {
		            title: {
		                display: true,
		                text: 'Ganancias por rutas'
		            },
		            scales: {
		                yAxes: [{
		                    ticks: {
		                        beginAtZero: true
		                    }
		                }]
		            }
		        }
		    });
		}

		function pieHombreMujer(valorHombreMujer)
		{
			var pastel  = document.getElementById("pastelHombreMujer").getContext("2d");
			const myChart = new Chart(pastel, {
			    type: 'pie',
			    data: {
			        labels: ['Hombres', 'Mujeres'],
			        datasets: [{
			            label: 'Sexo de los viajeros',
			            data: valorHombreMujer,
			            backgroundColor: [
			                '#2759f8',
			                '#f623f3'
			            ],
			            borderColor: [
			                '#042fb8',
			                '#a0009e'
			            ],
			            borderWidth: 1
			        }]
			    },
			    options: {
			        scales: {
			            y: {
			                beginAtZero: true
			            }
			        }
			    }
			});

		}
                
                function histogramaEdades(rangoEdades){
                    var ctx = document.getElementById("histogramaEdades").getContext('2d');
                    var dataValues = rangoEdades;
                    var dataLabels = ['0-20', '21-30', '31-40', '>= 41'];
                    var myChart = new Chart(ctx, {
                      type: 'bar',
                      data: {
                        labels: dataLabels,
                        datasets: [{
                          label: 'Viajeros',
                          data: dataValues,
                          backgroundColor: '#6764ff',
                        }]
                      },
                      options: {
                        scales: {
                          xAxes: [{
                            display: false,
                            barPercentage: 1.3,
                            ticks: {
                                max: 3,
                            }
                         }, {
                            display: true,
                            ticks: {
                                autoSkip: false,
                                max: 4,
                            }
                          }],
                          yAxes: [{
                            ticks: {
                              beginAtZero:true
                            }
                          }]
                        }
                      }
                    });
		}
                
      
		function solicitarValores(tipo)
		{
		    var request = new XMLHttpRequest();
		    request.onreadystatechange = function(){
		         if(this.readyState == 4 && this.status == 200){//Respuesta del servidor OK
		            var respuesta = JSON.parse(this.responseText); //Respuesta tiene lo q respondió el servidor 
                            switch (tipo){
                                case 'HombreMujer':
                                    pieHombreMujer(respuesta);
                                break;
                                case 'Menores':
                                    pieViajerosMenorMayor(respuesta);
                                    break;
                                case 'RangoEdades':
                                    histogramaEdades(respuesta);
                                    break;
                                case 'NumeroVuelos':
                                    pieVuelosRuta(respuesta);
                                    break;
                            }
		           
		        }
		    };
		    var parametro = "?tipo=" + tipo;
		    request.open("GET", "Estadisticas" + parametro,true);//Se elige la forma de envío q fue con get,
		    request.send();//SE envía
		}

		
	</script>
<body>
	<h1 class="h1Estad">Estadísticas</h1>
	<table class="tableEstad">
		<tr>
			<td class="tdEstad" colspan="2">
				<div class="divEstad">
					<p>1- Número de vuelos realizados por cada uno de los aviones.</p>
					<canvas id="pastelVuelosRuta"></canvas>
				</div>
			</td> 
			<td class="tdEstad">
				<div class="comportamientoEstad">
					<p>4- Histograma (rango de edades de las personas).</p>
					<canvas id="histogramaEdades"></canvas>
				</div>
			</td>
		</tr>
		<tr>
			<td class="tdEstad">
				<div class="divEstad">
					<p>2- Porcentaje de menores y mayores de edad</p>
					<canvas id="pastelViajerosMenorEdad"></canvas>
				</div>
			</td> 
			<td class="tdEstad">
				<div class="divEstad">
					<p>3- Porcentaje de hombres y mujeres.</p>
					<canvas id="pastelHombreMujer"></canvas>
				</div>
			</td> 
			<td class="tdEstad">
				<div class="comportamientoEstad">
					<p>5- Ganancias por ruta (se han utilizado datos de pruebas)</p>
					<canvas id="barrasPrecio"></canvas>
				</div>
			</td>
		</tr>
	</table>
		
	<br></br>
        
        <footer class="footerAdmin">En caso de querer cerrar sesión, pulse
            <a href="logout.jsp"> aquí</a>
        </footer>

</body>
</html>
