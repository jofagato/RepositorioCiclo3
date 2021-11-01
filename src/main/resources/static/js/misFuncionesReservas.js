$(function(){
    $('#alert-ok').hide();
    $('#alert-error').hide();
    listarReserva();
    
    crearReserva();
    eliminarReserva();
    mostarEditarReservaModal();
    editarReserva();

});

function listarReserva(){
    let url = 'http://193.123.110.232:8080/api/Reservation/all';
    $.getJSON(url).done(function(data){
        console.log(data);
        tbody = $('#tabla-farm tbody');
        //data [{},{},{},{},{}]
        $.each(data,function(index,item){
            let row = `<tr data-id=${item.idReservation}><td>${item.startDate}</td><td>${item.devolutionDate}</td><td>${item.status}</td>
           <td> <button type="button" id="editar" class="btn btn-success editar">Editar</button>
                        <button type="button" id="eliminar" class="btn btn-danger eliminar">Eliminar</button></td></tr>`;

            tbody.append(row);
        });
    });
}


function crearReserva(){
        
    $("#crearReserva").on('click',function(ev){
        ev.preventDefault();
        let inputinicial = $('#inicial').val();
        let inputfinal = $('#final').val();
        let estatus = $('#estatus').val();
        let url = 'http://193.123.110.232:8080/api/Reservation/save';
        $.ajaxSetup({
            contentType: "application/json; charset=utf-8"
          });
          if($("#inicial").val() === '' || $("#final").val() === '' || $("#estatus").val() === '') {
            alert("Se deben completar todos los campos");
           return false;
           }else{
        $.post( url, JSON.stringify({ startDate: inputinicial, devolutionDate: inputfinal, status: estatus}) )
        .done(function(item){
            let tbody = $('#tabla-farm tbody');
            let row = `<tr data-id=${item.idReservation}><td>${item.startDate}</td><td>${item.devolutionDate}</td><td>${item.status}</td></td>
                        <td><button type="button" class="btn btn-success editar">Editar</button>
                        <button type="button" class="btn btn-danger eliminar">Eliminar</button></td>
            </tr>`;
            tbody.append(row);
            var myModalEl = document.getElementById('fincaModal');
            var modal = bootstrap.Modal.getInstance(myModalEl)
            $("#inicial").val("");
            $("#final").val("");
            $("#estatus").val("");   

            $("#reservacionCreada").css('visibility', 'visible');
            let timer = setTimeout(function(){  $("#reservacionCreada").css('visibility', 'hidden'); console.log("contando") }, 4000);
        })
        .fail(function(){
            $('#alert-error').html('<span>ERROR! No se pudo crear correctamente la finca</span>');
            $('#alert-error').show(100);
            $('#alert-error').html();
        });
    }
    });
    
}

function eliminarReserva(){
    $('#tabla-farm tbody').on('click','.eliminar', function(ev){
        ev.preventDefault();
        let tr = $(this).closest('tr');
        let id = tr.data('id');
        let myurl = 'http://193.123.110.232:8080/api/Reservation/'+id;
        $.ajax({
            url:myurl,
            type:'DELETE',
            success:function(result){
                tr.remove();
            }
        });
      
    });
}

function ocultarAviso(){
$("#reservacionEditada").hide();
}

function mostarEditarReservaModal(){
    $('#tabla-farm tbody').on('click','.editar', function(ev){
        ev.preventDefault();
        let tr = $(this).closest('tr');
        let id = tr.data('id');
        let myurl = 'http://193.123.110.232:8080/api/Reservation/'+id;
        $.getJSON(myurl)
        .done(function(data){
            $('#einputID').val(data.idReservation);
            $('#einicial').val(data.startDate);
            $('#efinal').val(data.devolutionDate);
                        
            var myModal = new bootstrap.Modal(document.getElementById('editarfincaModal'));
            myModal.show()
        });
        
      
    });
}

function editarReserva(){
    console.log("se lanzó")    
    $("#add-btn").on('click',function(ev){
        let myurl = 'http://193.123.110.232:8080/api/Reservation/update';
        let eID = $('#einputID').val();
        let eInicial= $('#editarInicial').val();
        let eFinal= $('#eFinal').val();
        let eEstatus= $('#eEstatus').val();
        
                
        $.ajax({
            url:myurl,
            type:'PUT',
            contentType: "application/json; charset=utf-8",
            data:JSON.stringify({idReservation:eID, startDate: eInicial, devolutionDate: eFinal, status: eEstatus}), 
            success:function(result){
                $('#tabla-farm tbody').html('');
                listarReserva();
                var myModalEl = document.getElementById('editarfincaModal');
                var modal = bootstrap.Modal.getInstance(myModalEl)
                modal.hide()
                $("#reservacionEditada").show();
                let timer = setTimeout(function(){  $("#reservacionEditada").css('visibility', 'hidden'); console.log("contando") }, 4000);
            }
        });
    });
    
    
}