$(function(){
    $('#alert-ok').hide();
    $('#alert-error').hide();
    listarCliente();
    
    crearCliente();
    eliminarCliente();
    mostarEditarClienteModal();
    editarCliente();

});

function listarCliente(){
    let url = 'http://193.123.110.232:8080/api/Client/all';
    $.getJSON(url).done(function(data){
        console.log(data);
        tbody = $('#tabla-farm tbody');
        //data [{},{},{},{},{}]
        $.each(data,function(index,item){
            let row = `<tr data-id=${item.idClient}><td>${item.name}</td><td>${item.email}</td><td>${item.age}</td><td>${item.password}</td>
           <td> <button type="button" id="editar" class="btn btn-success editar">Editar</button>
                        <button type="button" id="eliminar" class="btn btn-danger eliminar">Eliminar</button></td></tr>`;

            tbody.append(row);
        });
    });
}

function crearCliente(){
    
    $("#add-btn").on('click',function(ev){
        ev.preventDefault();
        let inputname = $('#inputName').val();
        let inputemail = $('#inputEmail').val();
        let inputage = $('#inputAge').val();
        let inputpassword = $('#inputPassword').val();
        let url = 'http://193.123.110.232:8080/api/Client/save';
        $.ajaxSetup({
            contentType: "application/json; charset=utf-8"
          });
          if($("#inputName").val() === '' || $("#inputEmail").val() === '' || $("#inputAge").val() === '' || $("inputPassword").val() === '') {
            alert("Se deben completar todos los campos");
           return false;
           }else{
        $.post( url, JSON.stringify({ name: inputname, email: inputemail, age: inputage, password: inputpassword}) )
        .done(function(item){
            let tbody = $('#tabla-farm tbody');
            let row = `<tr data-id=${item.idClient}><td>${item.name}</td><td>${item.email}</td><td>${item.age}</td><td>${item.password}</td>
                        <td><button type="button" class="btn btn-success editar">Editar</button>
                        <button type="button" class="btn btn-danger eliminar">Eliminar</button></td>
            </tr>`;
            tbody.append(row);
            var myModalEl = document.getElementById('fincaModal');
            var modal = bootstrap.Modal.getInstance(myModalEl)
            $("#inputName").val("");
            $("#inputEmail").val("");
            $("#inputAge").val("");
            $("#inputPassword").val("");
                        
            $("#clienteCreado").css('visibility', 'visible');
            let timer = setTimeout(function(){  $("#clienteCreado").css('visibility', 'hidden'); console.log("contando") }, 4000);
        })
        .fail(function(){
            $('#alert-error').html('<span>ERROR! No se pudo crear correctamente la finca</span>');
            $('#alert-error').show(100);
            $('#alert-error').html();
        });
    }
    });
    
}

function eliminarCliente(){
    $('#tabla-farm tbody').on('click','.eliminar', function(ev){
        ev.preventDefault();
        let tr = $(this).closest('tr');
        let id = tr.data('id');
        let myurl = 'http://193.123.110.232:8080/api/Client/'+id;
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
$("#clienteEditado").hide();
}

function mostarEditarClienteModal(){
    $('#tabla-farm tbody').on('click','.editar', function(ev){
        ev.preventDefault();
        let tr = $(this).closest('tr');
        let id = tr.data('id');
        let myurl = 'http://193.123.110.232:8080/api/Client/'+id;
        $.getJSON(myurl)
        .done(function(data){
            $('#einputID').val(data.idClient);
            $('#einputName').val(data.name);
             $('#einputEmail').val(data.email);
            $('#einputAge').val(data.age);
            $('#einputPassword').val(data.password);
            
            var myModal = new bootstrap.Modal(document.getElementById('editarfincaModal'));
            myModal.show()
        });
        
      
    });
}

function editarCliente(){
    $("#edit-btn").on('click',function(ev){
        let myurl = 'http://193.123.110.232:8080/api/Client/update';
        let eID = $('#einputID').val();
        let eName= $('#einputName').val();
        let eEmail = $('#einputEmail').val();
        let eAge = $('#einputAge').val();
        let ePassword = $('#einputPassword').val();
        
        $.ajax({
            url:myurl,
            type:'PUT',
            contentType: "application/json; charset=utf-8",
            data:JSON.stringify({idClient:eID, name: eName, email: eEmail, age: eAge, password: ePassword}), 
            success:function(result){
                $('#tabla-farm tbody').html('');
                listarCliente();
                var myModalEl = document.getElementById('editarfincaModal');
                var modal = bootstrap.Modal.getInstance(myModalEl)
                modal.hide()
                $("#clienteEditado").show();
                let timer = setTimeout(function(){  $("#clienteEditado").css('visibility', 'hidden'); console.log("contando") }, 4000);
            }
        });
    });
    
    
}