$(document).ready(function () {
    inicializarDataTable();
    campoDatepicker();
    camposBusquedaFechas();
});

function campoDatepicker () {
    $('#datetimepicker4').datetimepicker({
        format: 'YYYY-MM-DD'
    });
}

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

function consultaFacturasPorTiempo(fechaInicio, fechaFin, idTabla, tipoFactura){
    console.log('Llegando al consultaFacturasPorTiempo');
    console.log('fi -> ' + fechaInicio );
    console.log('fi -> ' + fechaFin );
    console.log('fi -> ' + idTabla );
    console.log('fi -> ' + tipoFactura );
    
    jQuery.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/factura/consultaFacturasPorTiempo",
        data: {
            fechaInicio: fechaInicio, 
            fechaFin: fechaFin, 
            tipoFactura: tipoFactura
        },
        datatype: "json",
        success: function (response) {
            var respuesta = checkIfJson(response);
            if(respuesta.error) {
                
            } else {
                $('#' + idTabla).removeAttr('hidden');
                $('#' + idTabla).html(respuesta);
                inicializarDataTable(idTabla);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {}
    });
}

function busquedaPorFecha(){
    console.log('Llegando al busquedaPorFecha');
    
    if ($('#from').val() && $('#to').val()) {
        var fechaInicio = $('#from').val();
        var fechaFin = $('#to').val();

        consultaFacturasPorTiempo(fechaInicio, fechaFin, "generalesDataTable", 1);
        consultaFacturasPorTiempo(fechaInicio, fechaFin, "viajesDataTable", 2);
        
        $('#widgetsTotales').removeAttr('hidden');
        
    } else {
        alert("Debes llenar los campos de fecha para realizar la búsqueda");
    }
}

function inicializarDataTable(idTabla) {
    $('#' + idTabla).DataTable({
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