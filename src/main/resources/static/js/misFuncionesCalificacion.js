$(function(){
    $('#alert-ok').hide();
    $('#alert-error').hide();
    listarMensajes();
    
    crearMensajes();
    eliminarMensajes();
    mostarEditarMensajesModal();
    editarMensajes();

});

function listarMensajes(){
    let url = 'http://193.123.110.232:8080/api/Message/all';
    $.getJSON(url).done(function(data){
        console.log(data);
        tbody = $('#tabla-farm tbody');
        //data [{},{},{},{},{}]
        $.each(data,function(index,item){
            let row = `<tr data-id=${item.idMessage}><td>${item.messageText}</td>
           <td> <button type="button" id="editar" class="btn btn-success editar">Editar</button>
                        <button type="button" id="eliminar" class="btn btn-danger eliminar">Eliminar</button></td></tr>`;

            tbody.append(row);
        });
    });
}

function crearMensajes(){
    
    $("#add-btn").on('click',function(ev){
        ev.preventDefault();
        let inputmessageText = $('#inputMessage').val();
        let url = 'http://193.123.110.232:8080/api/Message/save';
        $.ajaxSetup({
            contentType: "application/json; charset=utf-8"
          });
          if($("#inputMessage").val() === '') {
            alert("Se deben completar todos los campos");
           return false;
           }else{
        $.post( url, JSON.stringify({ messageText: inputmessageText}) )
        .done(function(item){
            let tbody = $('#tabla-farm tbody');
            let row = `<tr data-id=${item.idMessage}><td>${item.messageText}</td></td>
                        <td><button type="button" class="btn btn-success editar">Editar</button>
                        <button type="button" class="btn btn-danger eliminar">Eliminar</button></td>
            </tr>`;
            tbody.append(row);
            var myModalEl = document.getElementById('fincaModal');
            var modal = bootstrap.Modal.getInstance(myModalEl)
            $("#inputMessage").val("");
                                   
            $("#mensajeCreado").css('visibility', 'visible');
            let timer = setTimeout(function(){  $("#mensajeCreado").css('visibility', 'hidden'); console.log("contando") }, 4000);
        })
        .fail(function(){
            $('#alert-error').html('<span>ERROR! No se pudo crear correctamente la finca</span>');
            $('#alert-error').show(100);
            $('#alert-error').html();
        });
    }
    });
    
}

function eliminarMensajes(){
    $('#tabla-farm tbody').on('click','.eliminar', function(ev){
        ev.preventDefault();
        let tr = $(this).closest('tr');
        let id = tr.data('id');
        let myurl = 'http://193.123.110.232:8080/api/Message/'+id;
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
$("#mensajeEditado").hide();
}

function mostarEditarMensajesModal(){
    $('#tabla-farm tbody').on('click','.editar', function(ev){
        ev.preventDefault();
        let tr = $(this).closest('tr');
        let id = tr.data('id');
        let myurl = 'http://193.123.110.232:8080/api/Message/'+id;
        $.getJSON(myurl)
        .done(function(data){
            $('#einputID').val(data.idMessage);
            $('#einputMessage').val(data.messageText);
                        
            var myModal = new bootstrap.Modal(document.getElementById('editarfincaModal'));
            myModal.show()
        });
        
      
    });
}

function editarMensajes(){
    $("#edit-btn").on('click',function(ev){
        let myurl = 'http://193.123.110.232:8080/api/Message/update';
        let eID = $('#einputID').val();
        let eMessage= $('#einputMessage').val();
                
        $.ajax({
            url:myurl,
            type:'PUT',
            contentType: "application/json; charset=utf-8",
            data:JSON.stringify({idMessage:eID, messageText: eMessage}), 
            success:function(result){
                $('#tabla-farm tbody').html('');
                listarMensajes();
                var myModalEl = document.getElementById('editarfincaModal');
                var modal = bootstrap.Modal.getInstance(myModalEl)
                modal.hide()
                $("#mensajeEditado").show();
                let timer = setTimeout(function(){  $("#mensajeEditado").css('visibility', 'hidden'); console.log("contando") }, 4000);
            }
        });
    });
    
    
}