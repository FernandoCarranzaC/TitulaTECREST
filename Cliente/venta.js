var op="";
var json={
        idv:null,
        codigov:null,
        idc:null,
        nombrec:null,
        emailc:null,
        telefonoc:null,
        direccionc:null,
        productosv:null,
        productos_pendientesv:null,
        impuestov:null,
        netov:null,
        totalv:null,
        metodo_pagov:null,
        fechav:null,
        ide:null,
        vendedorv:null

    };

function inicializarDivs(){
    document.getElementById("listadoIndividual").style.display='none';
    document.getElementById("listadoGeneral").style.display='block';
    consultaGeneral();
}
function consultaGeneral(){
    var url="http://localhost:8080/PosWS/DatosVenta";
    var ajax=new XMLHttpRequest();

    ajax.open("get",url,true);
    ajax.onreadystatechange=function(){
        if(this.status=200 && this.readyState==4)
         generarTabla(this.responseText);
    };
    ajax.send();
}
function generarTabla(respuesta){
    limpiarTabla();
    var ventas=JSON.parse(respuesta);
    if(ventas.length>0){
        var table=document.getElementById("datos");
        for(i=0;i<ventas.length;i++){
            var v=ventas[i];
            var tr=document.createElement("tr");
            for(p in v){
               
                if(v[p]!=""){
                    var td=document.createElement("td");
                    var texto=document.createTextNode(v[p]);
                    td.appendChild(texto);
                    tr.appendChild(td);
                
            }
            }
            var link=crearlink(ventas[i].idv,"editar");
            var td=document.createElement("td");
            td.appendChild(link);
            tr.appendChild(td);
            link=crearlink(ventas[i].idv,"eliminar");
            td=document.createElement("td");
            td.appendChild(link);
            tr.appendChild(td);
            table.appendChild(tr);
            
        }
   }
   else{
      alert(ventas.mensaje);
   }
}
function crearlink(id,operacion){
    var link=document.createElement("a");
    link.setAttribute("href","javascript:"+operacion+"("+id+")");
    var img=document.createElement("img");
    img.setAttribute("src","./img/"+operacion+".png");
    link.appendChild(img);
    return link;
}
function limpiarTabla(){
    var table=document.getElementById("datos");
    for(i=table.rows.length-1;i>0;i--){
        table.removeChild(table.rows[i]);
    }
}
function consultarOpciones(){
    var url="http://localhost:8080/TitulaTECREST/Opciones";
    var ajax=new XMLHttpRequest();
    ajax.open("get",url,true);
    ajax.onreadystatechange=function(){
        if(this.status=200 && this.readyState==4)
        llenarCombo(this.responseText);
    };
    ajax.send();
}
function llenarCombo(respuesta){
    limpiarCombo();
    var opciones=JSON.parse(respuesta);
    var combo=document.getElementById("opcion");
    for(i=0;i<opciones.length;i++){
        var option=document.createElement("option");
        var texto=document.createTextNode(opciones[i].nombre);
        option.appendChild(texto);
        option.setAttribute("value",opciones[i].idOpcion);
        combo.appendChild(option);
    }
}
function nuevo(){
    op="c";
    document.getElementById("listadoIndividual").style.display='block';
    reset();
    consultarOpciones();
    
}
function limpiarCombo(){
    var combo=document.getElementById("opcion");
    for(i=combo.options.length-1;i>0;i--){
        combo.removeChild(combo.options[i]);
    } 
}
function cancelar(){
    op="";
    document.getElementById("listadoIndividual").style.display='none';
}
function guardar(){
    switch(op){
        case "c":
            solicitud.insertar();
            break;
        case "u":
            modificar();
            break;
    }
}
function insertar(){
    var url="http://localhost:8080/TitulaTECREST/Solicitudes";
    json.alumno.idAlumno=document.getElementById("idAlumno").value;
    json.tituloProyecto=document.getElementById("tituloProyecto").value;
    json.opcion.idOpcion=document.getElementById("opcion").options[document.getElementById("opcion").options.selectedIndex].value;
    //alert(JSON.stringify(json))
    var ajax=new XMLHttpRequest();
    ajax.open("post",url,true);
    ajax.onreadystatechange=function(){
        if(this.status=200 && this.readyState==4){
            var respuesta=JSON.parse(this.responseText);
            alert(respuesta.mensaje);
            inicializarDivs()
        }
    };
    ajax.setRequestHeader("Content-Type","application/json");
    ajax.send(JSON.stringify(json)); 
}
function modificar(){
    json.tituloProyecto=document.getElementById("tituloProyecto").value;
    json.opcion.idOpcion=document.getElementById("opcion").options[document.getElementById("opcion").options.selectedIndex].value;
    //alert(JSON.stringify(json))
    var url="http://localhost:8080/TitulaTECREST/Solicitudes";
    var ajax=new XMLHttpRequest();
    ajax.open("put",url,true);
    ajax.onreadystatechange=function(){
        if(this.status=200 && this.readyState==4){
            var respuesta=JSON.parse(this.responseText);
            alert(respuesta.mensaje);
            inicializarDivs()
        }
    };
    ajax.setRequestHeader("Content-Type","application/json");
    ajax.send(JSON.stringify(json)); 
}
function eliminar(id){
    if(confirm("¿Estas seguro de eliminar la solicitud con folio:"+id+"?")){
        var url="http://localhost:8080/TitulaTECREST/Solicitudes/"+id;
        var ajax=new XMLHttpRequest();
        ajax.open("delete",url,true);
        ajax.onreadystatechange=function(){
        if(this.status=200 && this.readyState==4){
            var respuesta=JSON.parse(this.responseText);
            alert(respuesta.mensaje);
            inicializarDivs()
        }
        };
        ajax.send();         
    }
}
function reset(){
    document.getElementById("idAlumno").value="";
    document.getElementById("tituloProyecto").value="";
    document.getElementById("opcion").options[0].selected=true;
    document.getElementById("nombreAlumno").value="";
    document.getElementById("nocontrol").value="";
    document.getElementById("idCarrera").value="";
    document.getElementById("nombreCarrera").value="";    
    mostrarDivs("none");
}
function mostrarDivs(valor){
    document.getElementById("folio").style.display=valor;
    document.getElementById("complemento").style.display=valor;
    document.getElementById("coordinador").style.display=valor;
}
function editar(id){
    consultarOpciones();
    op="u";
    var url="http://localhost:8080/TitulaTECREST/Solicitudes/"+id;
    var ajax=new XMLHttpRequest();
    ajax.open("get",url,true);
    ajax.onreadystatechange=function(){
    if(this.status=200 && this.readyState==4)
        cargarSolicitud(this.responseText);
    };
    ajax.send();
}
function cargarSolicitud(respuesta){
    var obj=JSON.parse(respuesta);
    if(obj.mensaje){
        alert(respuesta.mensaje);
    }
    else{
        json=obj;
        document.getElementById("idSolicitud").value=json.idSolicitud;
        document.getElementById("tituloProyecto").value=json.tituloProyecto;
        document.getElementById("idSolicitud").value=json.idSolicitud;
        for(i=0;i<document.getElementById("opcion").options.length;i++){
            if(document.getElementById("opcion").options[i].text==json.opcion.nombre){
                document.getElementById("opcion").options[i].selected=true;
                break;
            }    
        }
        document.getElementById("fechaRegistro").value=json.fechaRegistro;
        document.getElementById("fechaAtencion").value=json.fechaAtencion;
        document.getElementById("estatus").value=json.estatus;
        document.getElementById("idAlumno").value=json.alumno.idAlumno;
        document.getElementById("nombreAlumno").value=json.alumno.nombre;
        document.getElementById("nocontrol").value=json.alumno.noControl;
        document.getElementById("idCarrera").value=json.alumno.carrera.idCarrera;
        document.getElementById("nombreCarrera").value=json.alumno.carrera.nombre;
        document.getElementById("idAdministrativo").value=json.administrativo.idAdministrativo;
        document.getElementById("nombreAdministrativo").value=json.administrativo.nombre;
        json.tipoUsuario="E";
        mostrarDivs("block");
        document.getElementById("listadoIndividual").style.display='block';
    }
}
function mostrarDivAlumno(){
    document.getElementById("nocontrol").removeAttribute("readonly");
   document.getElementById("listadoIndividual").style.display='block';
}
function consultarAlumno(){
    var nocontrol=document.getElementById("nocontrol").value;
    var ajax=new XMLHttpRequest();
    ajax.open("get","http://localhost:8080/TitulaTECREST/Alumnos/"+nocontrol,true);
    ajax.onreadystatechange=function(){
        if(this.status==200 && this.readyState==4){
            cargarDatosAlumno(this.responseText);
        }
    };
    ajax.send();
}
function cargarDatosAlumno(respuesta){
    alert(respuesta);
    var alumno=JSON.parse(respuesta);
    alert(alumno);
    document.getElementById("nombreAlumno").value=alumno.usuario.nombre;
    //document.getElementById("nocontrol").value=json.alumno.noControl;
    document.getElementById("idCarrera").value=alumno.carrera.idCarrera;
    document.getElementById("nombreCarrera").value=alumno.carrera.nombre;
        
}
