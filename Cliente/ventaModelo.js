class solicitud{
    constructor(){
        //instrucciones de inicialización
    }
    
    agregar(){
        //la comuniacion con el servicio para registrar 
        //la solicitud
    }
    eliminar(){
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
};