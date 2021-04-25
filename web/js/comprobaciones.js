            var form=false;
            var nfo=0;
            document.getElementById("email1").addEventListener("keypress",cambio,true);
            document.getElementById("password1").addEventListener("keypress",cambio,true);
            function consultausuario(num){
                peticion_http = inicializa_xhr();
                if(peticion_http) {
                    peticion_http.onreadystatechange = procesalogin;
                    peticion_http.open("post", "usuario.jsp", true);
                    peticion_http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                    var query_string = crea_query_string(num);
                    //alert(query_string);
                    peticion_http.send(query_string);
                }
            }
            function prueba(num){
                //console.log("sdlfjksdkfjksjfhksjdfhkjsdhfksjdhf");
                console.log(this.nfo);
                this.nfo=num;
                consultausuario(num);
                
                
                return this.form;
            }
            function escribir(){
                if(!this.form){
                    console.log("entra");
                    var errorcon=document.getElementsByClassName("erroruse");
                    console.log(errorcon[0]);
                    errorcon[0].innerHTML="usuario invalido";
                }
            }
            function cambio(){
                var errorcon=document.getElementsByClassName("erroruse");
                errorcon[0].innerHTML="";
            }
            function crea_query_string(num) {
                var email = document.getElementById("email"+num);
                var pass = document.getElementById("password"+num);
                //alert (categoria.value);
                //alert (videoclub2.value);
                return "email=" + encodeURIComponent(email.value) +
                "&pass=" + encodeURIComponent(pass.value);
            }
            function inicializa_xhr() {
                if(window.XMLHttpRequest) {
                    return new XMLHttpRequest();
                }
                else if(window.ActiveXObject) {
                    return new ActiveXObject("Microsoft.XMLHTTP");
                }
            }
            
            function procesalogin(){
                if(peticion_http.readyState == 4) {
                    if(peticion_http.status == 200) {
                        var respuesta_json=peticion_http.responseText;
                        var usuario= eval("("+respuesta_json+")");
                        if (usuario.length>0){
                            console.log("jeje boyy");
                            enviar();
                            
                        }else{
                            escribir();
                        }
                       /* for(var x=0; x<categorias.length; x++){
                            var codigo=categorias[x].codigoCategoria;
                            var nombre=categorias[x].nombreCategoria;
                            cat.innerHTML+="<option value='"+codigo+"'>"+nombre+"</option>";
                        }*/

                    }
                }
            }
            function enviar(){
                            console.log("enviar");
                            
                                document.formulario1.submit();
                                
                            
                           
            }
            
          
          