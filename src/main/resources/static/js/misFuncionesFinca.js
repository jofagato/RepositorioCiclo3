$(function(){
    $('#alert-ok').hide();
    $('#alert-error').hide();
    listarFarm();
    
    crearFarm();
    eliminarFarm();
    mostarEditarFarmModal();
    editarFarm();

});

function listarFarm(){
    let url = 'http://193.123.110.232:8080/api/Farm/all';
    $.getJSON(url).done(function(data){
        console.log(data);
        tbody = $('#tabla-farm tbody');
        //data [{},{},{},{},{}]
        $.each(data,function(index,item){
            let row = `<tr data-id=${item.id}><td>${item.name}</td><td>${item.address}</td><td>${item.extension}</td><td>${item.category_id}</td><td>${item.description}</td>
           <td> <button type="button" id="editar" class="btn btn-success editar">Editar</button>
                        <button type="button" id="eliminar" class="btn btn-danger eliminar">Eliminar</button></td></tr>`;

            tbody.append(row);
        });
    });
}

function crearFarm(){
    
    $("#add-btn").on('click',function(ev){
        ev.preventDefault();
        let inputname = $('#inputName').val();
        let inputaddress = $('#inputAddress').val();
        let inputexension = $('#inputExension').val();
        let inputcategory = $('#inputCategory').val();
        let inputdescription = $('#inputDescription').val();
        let url = 'http://193.123.110.232:8080/api/Farm/save';
        $.ajaxSetup({
            contentType: "application/json; charset=utf-8"
          });
          if($("#inputAddress").val() === '' || $("#inputExension").val() === '' || $("#inputCategory").val() === '' || $("inputName").val() === '' || $("inputDescription").val() === '') {
            alert("Se deben completar todos los campos");
           return false;
           }else{
        $.post( url, JSON.stringify({ name: inputname, address: inputaddress, extension: inputexension, category_id: inputcategory, description: inputdescription }) )
        .done(function(item){
            let tbody = $('#tabla-farm tbody');
            let row = `<tr data-id=${item.id}><td>${item.name}</td><td>${item.address}</td><td>${item.extension}</td><td>${item.category_id}</td><td>${item.description}</td>
                        <td><button type="button" class="btn btn-success editar">Editar</button>
                        <button type="button" class="btn btn-danger eliminar">Eliminar</button></td>
            </tr>`;
            tbody.append(row);
            var myModalEl = document.getElementById('fincaModal');
            var modal = bootstrap.Modal.getInstance(myModalEl)
            $("#inputName").val("");
            $("#inputAddress").val("");
            $("#inputExension").val("");
            $("#inputCategory").val("");
            $("#inputDescription").val("");
            
            $("#fincaCreada").css('visibility', 'visible');
            let timer = setTimeout(function(){  $("#fincaCreada").css('visibility', 'hidden'); console.log("contando") }, 4000);
        })
        .fail(function(){
            $('#alert-error').html('<span>ERROR! No se pudo crear correctamente la finca</span>');
            $('#alert-error').show(100);
            $('#alert-error').html();
        });
    }
    });
    
}

function eliminarFarm(){
    $('#tabla-farm tbody').on('click','.eliminar', function(ev){
        ev.preventDefault();
        let tr = $(this).closest('tr');
        let id = tr.data('id');
        let myurl = 'http://193.123.110.232:8080/api/Farm/'+id;
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
$("#fincaEditada").hide();
}

function mostarEditarFarmModal(){
    $('#tabla-farm tbody').on('click','.editar', function(ev){
        ev.preventDefault();
        let tr = $(this).closest('tr');
        let id = tr.data('id');
        let myurl = 'http://193.123.110.232:8080/api/Farm/'+id;
        $.getJSON(myurl)
        .done(function(data){
            $('#einputID').val(data.id);
            $('#einputName').val(data.name);
             $('#einputAddress').val(data.address);
            $('#einputExension').val(data.extension);
            $('#einputCategory').val(data.category_id);
            $('#einputDescripcion').val(data.description);
            var myModal = new bootstrap.Modal(document.getElementById('editarfincaModal'));
            myModal.show()
        });
        
      
    });
}

function editarFarm(){
    $("#edit-btn").on('click',function(ev){
        let myurl = 'http://193.123.110.232:8080/api/Farm/update';
        let eID = $('#einputID').val();
        let eName= $('#einputName').val();
        let eAddress = $('#einputAddress').val();
        let eExension = $('#einputExension').val();
        let eCategory = $('#einputCategory').val();
        let eDescripcion = $('#einputDescripcion').val();
        $.ajax({
            url:myurl,
            type:'PUT',
            contentType: "application/json; charset=utf-8",
            data:JSON.stringify({id:eID, name: eName, address: eAddress,extension: eExension, category_id: eCategory, description: eDescripcion }), 
            success:function(result){
                $('#tabla-farm tbody').html('');
                listarFarm();
                var myModalEl = document.getElementById('editarfincaModal');
                var modal = bootstrap.Modal.getInstance(myModalEl)
                modal.hide()
                $("#fincaEditada").show();
                let timer = setTimeout(function(){  $("#fincaEditada").css('visibility', 'hidden'); console.log("contando") }, 4000);
            }
        });
    });
    
    
}