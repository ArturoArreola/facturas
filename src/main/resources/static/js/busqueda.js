$(document).ready(function () {
    camposBusquedaFechas();
});


function camposBusquedaFechas () {
    $('#datetimepicker7').datetimepicker({
        format: 'YYYY-MM-DD'
    });
    $('#datetimepicker8').datetimepicker({
        useCurrent: false,
        format: 'YYYY-MM-DD'
    });
    $("#datetimepicker7").on("change.datetimepicker", function (e) {
        $('#datetimepicker8').datetimepicker('minDate', e.date);
    });
    $("#datetimepicker8").on("change.datetimepicker", function (e) {
        $('#datetimepicker7').datetimepicker('maxDate', e.date);
    });
}

function busquedaPorFecha(){
    console.log('Llegando al busquedaPorFecha');
    
    if ($('#from').val() && $('#to').val()) {
        var fechaInicio = $('#from').val();
        var fechaFin = $('#to').val();

        consultaFacturasPorTiempo(fechaInicio, fechaFin, "generalesDataTable", "tablaFacturasGenerales", 1);
        consultaFacturasPorTiempo(fechaInicio, fechaFin, "viajesDataTable", "tablaFacturasViajes", 2);
        //inicializarDataTable();
        consultarTotalesPorTiempo(fechaInicio, fechaFin);
        
    } else {
        alert("Debes llenar los campos de fecha para realizar la búsqueda");
    }
}

function consultaFacturasPorTiempo(fechaInicio, fechaFin, idTablaDiv, idTabla, tipoFactura){
    console.log('Llegando al consultaFacturasPorTiempo');
    console.log('fi -> ' + fechaInicio );
    console.log('ff -> ' + fechaFin );
    console.log('iddiv -> ' + idTablaDiv );
    console.log('id -> ' + idTabla );
    console.log('tf -> ' + tipoFactura );
    
    jQuery.ajax({
        type: "GET",
        contentType: "application/json",
        datatype: "json",
        data: {
            fechaInicio: fechaInicio, 
            fechaFin: fechaFin, 
            tipoFactura: tipoFactura
        },
        url: "/factura/consultaFacturasPorTiempo",
        success: function (data) {
            console.log('respuesta ' + data);
            //var respuesta = checkIfJson(data);
            console.log('respuesta ' + data);
            if(data === null || data === '' || data.length === 0) {
                console.log('El data 1 viene vacío');
                $('#mensajeNoResultados').removeAttr('hidden');
            } else {
                $('#' + idTablaDiv).removeAttr('hidden');
                $('#mensajeNoResultados').attr("hidden",true);
                
                var table = $('#'+idTabla+' tbody');
                var len = data.length;
                for (var i = 0; i < len; i++) {
                    var id = data[i].id;
                    var descripcion  = data[i].descripcion;
                    var costo = data[i].costo;
                    var fecha = data[i].fecha;
                    $('#'+idTabla+' tbody').append("<tr><td>"+id+"</td><td>"+descripcion+"</td><td>"+costo+"</td><td>"+fecha+"</td></tr>");
                }
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}

function consultarTotalesPorTiempo(fechaInicio, fechaFin){
    console.log('Llegando al consultaTotalesPorTiempo');
    console.log('fi -> ' + fechaInicio );
    console.log('ff -> ' + fechaFin );
    
    jQuery.ajax({
        type: "GET",
        contentType: "application/json",
        datatype: "json",
        data: {
            fechaInicio: fechaInicio, 
            fechaFin: fechaFin
        },
        url: "/factura/consultaTotalesPorTiempo",
        success: function (data) {
            console.log('respuesta consulta ' + data);
            if(data[0] === "0.00" && data[1] === "0.00" && data[2] === "0.00") {
                console.log('El data 2 viene vacío');    
            } else {
                $('#widgetsTotales').removeAttr('hidden');
                $('#widgetTotalGenerales').html('$ ' + data[0]);
                $('#widgetTotalViajes').html('$ ' + data[1]);
                $('#widgetTotalDiferencia').html('$ ' + data[2]);
//                var table = $('#'+idTabla+' tbody');
//                var len = data.length;
//                for (var i = 0; i < len; i++) {
//                    var id = data[i].id;
//                    var descripcion  = data[i].descripcion;
//                    var costo = data[i].costo;
//                    var fecha = data[i].fecha;
//                    $('#'+idTabla+' tbody').append("<tr><td>"+id+"</td><td>"+descripcion+"</td><td>"+costo+"</td><td>"+fecha+"</td></tr>");
//                }
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}

function inicializarDataTable() {
    $('#tablaFacturasGenerales').DataTable({
        "language": {
            "sProcessing":     "Procesando...",
            "sLengthMenu":     "Mostrar _MENU_ registros",
            "sZeroRecords":    "No se encontraron resultados",
            "sEmptyTable":     "Ningún dato disponible en esta tabla",
            "sInfo":           "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
            "sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0 registros",
            "sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
            "sInfoPostFix":    "",
            "sSearch":         "Buscar:",
            "sUrl":            "",
            "sInfoThousands":  ",",
            "sLoadingRecords": "Cargando...",
            "oPaginate": {
                    "sFirst":    "Primero",
                    "sLast":     "Último",
                    "sNext":     "Siguiente",
                    "sPrevious": "Anterior"
            },
            "oAria": {
                    "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
                    "sSortDescending": ": Activar para ordenar la columna de manera descendente"
            }
        }
    });
    
    $('#tablaFacturasViajes').DataTable({
        "language": {
            "sProcessing":     "Procesando...",
            "sLengthMenu":     "Mostrar _MENU_ registros",
            "sZeroRecords":    "No se encontraron resultados",
            "sEmptyTable":     "Ningún dato disponible en esta tabla",
            "sInfo":           "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
            "sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0 registros",
            "sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
            "sInfoPostFix":    "",
            "sSearch":         "Buscar:",
            "sUrl":            "",
            "sInfoThousands":  ",",
            "sLoadingRecords": "Cargando...",
            "oPaginate": {
                    "sFirst":    "Primero",
                    "sLast":     "Último",
                    "sNext":     "Siguiente",
                    "sPrevious": "Anterior"
            },
            "oAria": {
                    "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
                    "sSortDescending": ": Activar para ordenar la columna de manera descendente"
            }
        }
    });
}