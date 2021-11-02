
function traerReporte() {
    $.ajax({
        url: "http://193.123.110.232:8080/api/Reservation/report-status",
        type: "GET",
        datatype: "JSON",
        success: function (respuesta) {
            console.log(respuesta);
            let object = JSON.stringify(respuesta)
            let text = "";
            for (let i = 0; i < object.length; i++) {
                text += object[i];
                document.getElementById("reportePintado").innerHTML = text;
              }

        }
                

    });
}

function traerTop() {
    $.ajax({
        url: "http://193.123.110.232:8080/api/Reservation/report-clients",
        type: "GET",
        datatype: "JSON",
        success: function (respuesta) {
            console.log(respuesta);
            let object2 = JSON.stringify(respuesta)
            let text2 = "";
            for (let i = 0; i < object2.length; i++) {
                text2+= object2[i];
                document.getElementById("reporteTop").innerHTML = text2;
              }

        }
                

    });
}



function traerFechas(){
         
        let fecha1 = document.querySelector("#fechaInicial").value;
        let fecha2 = document.querySelector("#fechaFinal").value;
        let myurl = 'http://193.123.110.232:8080/api/Reservation/report-dates/'+fecha1 + '/' + fecha2;
        $.getJSON(myurl)
        .done(function(data){
            let array = JSON.stringify(data)
            console.log(array)
            document.getElementById("reporteFechas").innerHTML = array;
            
        });
        
}

