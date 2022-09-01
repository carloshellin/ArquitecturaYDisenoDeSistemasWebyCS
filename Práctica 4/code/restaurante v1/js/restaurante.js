const primerosPlatos = [['Puré de patata', 10], ['Macarrones con queso', 8], ['Garbanzos con chorizo', 11], ['Judías verdes', 12], ['Sopa de pescado', 9]];
const segundosPlatos = [['Costillas de cerdo', 25], ['Merluza a la plancha', 20], ['Escalope de ternera', 23]];
const postres = [['Trufas de chocolate', 10], ['Natillas', 1], ['Helado de sabores', 3], ['Sandía', 2]];
const estadosMesa = [['mesa1',[]], ['mesa2',[]], ['mesa3',[]], ['mesa4',[]], ['mesa5',[]]]; 

const mapPlatos1 = new Map(primerosPlatos);
const mapPlatos2 = new Map(segundosPlatos);
const mapPostres = new Map(postres);
const mapEstados = new Map(estadosMesa);

const platos = [mapPlatos1, mapPlatos2, mapPostres];
const platosArray = [primerosPlatos, segundosPlatos, postres];

function mostrarPlatos(platos){ 
    const mapPlatos = new Map(platos);
    const iterador = mapPlatos.keys();
    for(var i = 0; i < platos.length; i++) {
        $('#listaplatos').append($('<option>', {
            value: i,
            text: iterador.next().value
        }));
    }
} 

function setPriceExtra(extra){
    var valorFinal = parseFloat($("#preciototal").val());
    if (extra == $('#copacheck').val()){
        if (valorFinal <= 3.5){
            $("#preciototal").val(0);
        } else{
            $("#preciototal").val(valorFinal - 3.5);
        } 
    } else {
        if (valorFinal <= 1){
            $("#preciototal").val(0);
        } else{
            $("#preciototal").val(valorFinal - 1);
        }
    }
}


function obtenerPrecio(plato) {
    var precio = -1;
    for (let i = 0; i < platosArray.length; i++) {
        for (let j = 0; j < platosArray[i].length; j++) {
            if (platosArray[i][j][0] == plato) {
                precio = platosArray[i][j][1];
                return precio;
            }
        }
    }
    return precio;
}

function refreshPrice(e) {
    if ((e.which || e.keyCode) == 116 || (e.which || e.keyCode) == 82) {
        $("#preciototal").val(0);
    }
};

$(document).ready(function(){
    var idxplatos = 0;
    $("#primeros").on("click", function(){
        if($(this).is(':checked')){
            idxplatos = 0;
        }
    }); 
    
    $("#segundos").on("click", function(){
        if($(this).is(':checked')){
            idxplatos = 1;
        }
    });

    $("#postres").on("click", function(){
        if($(this).is(':checked')){
            idxplatos = 2;
        }
    });

    $("#listaplatos").dblclick(function(){
        var precioactual = $("#preciototal").val();
        var seleccion = $("#listaplatos").children("option:selected").text();

        var precioplato = platos[idxplatos].get(seleccion);
        $("#preciototal").val(parseInt(precioactual)+parseInt(precioplato));        

        $('#platosseleccion').append($('<option>', {
            text: seleccion
        }));
    })
});

$(document).ready(function() {
    $("#platosseleccion").dblclick(function(){
        var seleccion = $("#platosseleccion").children("option:selected").text();
        var precio = obtenerPrecio(seleccion);
        var nuevoprecio = $("#preciototal").val() - parseInt(precio);        
        $("#preciototal").val(nuevoprecio);
        $("#platosseleccion").children("option:selected").remove();
    });
});

mostrarPlatos(primerosPlatos);

$("#primeros").on("click", function() {$('#listaplatos').empty(); 
mostrarPlatos(primerosPlatos)});
$("#segundos").on("click", function() {$('#listaplatos').empty();
mostrarPlatos(segundosPlatos)});
$("#postres").on("click", function() {$('#listaplatos').empty();
mostrarPlatos(postres)});

$(document).ready(function() {
    $("#botonpagar").click(function(){
        $("#preciototal").val(0);
        $('#platosseleccion').empty();
        vaciarCantidad();
    }); 
});

$(document).ready(function(){
    $('#cafecheck').on("click", function(){
        var valorFinal = parseFloat($("#preciototal").val());
        var tipoExtra = $('#cafecheck').val();
        if($(this).is(':checked')){
            $("#preciototal").val(valorFinal + 1);
        } else{
            setPriceExtra(tipoExtra);
        }
    });
});

$(document).ready(function(){
    $('#copacheck').on("click", function(){
        var valorFinal = parseFloat($("#preciototal").val());
        var tipoExtra = $('#copacheck').val();
        if($('#copacheck').is(':checked')){
            $("#preciototal").val(valorFinal + 3.5);
        } else{
            setPriceExtra(tipoExtra);
        }
    });
});

$(document).ready(function(){
    $(document).on("keydown", refreshPrice);
});


$(document).ready(function(){
    $("#mesas").on("click", function(){
        var pedidos = [];
        var mesa = ($("#mesas").val());
        pedidos.push($('#cafecheck').prop('checked'));
        pedidos.push($('#copacheck').prop('checked'));
        jQuery('#platosseleccion option').each(function(i){
            var option = new Option($(this).text(),$(this).val());
            pedidos.push(option);
        }); 
        mapEstados.set(mesa,pedidos);
    });

    $("#mesas").on("change", function(){
        var precio = 0;
        var mesa = ($("#mesas").val());
        var pedidos = mapEstados.get(mesa);
        $("#cafecheck").prop("checked", false);
        $("#copacheck").prop("checked", false);
        $('#platosseleccion')[0].options.length = 0;      
        if (pedidos[0]){
            $("#cafecheck").prop("checked", true);
            precio += 1;
        }
        if (pedidos[1]){
            $("#copacheck").prop("checked", true);
            precio += 3.5;
        }
        for (let i = 2 ; i<pedidos.length;i++) {
            $("#platosseleccion").append(pedidos[i]);
            precio += obtenerPrecio(pedidos[i].text);
        }
        $("#preciototal").val(precio);
    });
});

