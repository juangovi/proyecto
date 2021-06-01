<%-- 
    Document   : iniciarSession
    Created on : 29-may-2021, 20:22:38
    Author     : juana
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">
    <head>
        <%
            
        %>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

        <link rel="apple-touch-icon" sizes="57x57" href="img/icon/apple-icon-57x57.png" />
        <link rel="apple-touch-icon" sizes="60x60" href="img/icon/apple-icon-60x60.png" />
        <link rel="apple-touch-icon" sizes="72x72" href="img/icon/apple-icon-72x72.png" />
        <link rel="apple-touch-icon" sizes="76x76" href="img/icon/apple-icon-76x76.png" />
        <link rel="apple-touch-icon" sizes="114x114" href="img/icon/apple-icon-114x114.png" />
        <link rel="apple-touch-icon" sizes="120x120" href="img/icon/apple-icon-120x120.png" />
        <link rel="apple-touch-icon" sizes="144x144" href="img/icon/apple-icon-144x144.png" />
        <link rel="apple-touch-icon" sizes="152x152" href="img/icon/apple-icon-152x152.png" />
        <link rel="apple-touch-icon" sizes="180x180" href="img/icon/apple-icon-180x180.png" />
        <link rel="icon" type="image/png" sizes="192x192" href="img/icon/android-icon-192x192.png" />
        <link rel="icon" type="image/png" sizes="32x32" href="img/icon/favicon-32x32.png" />
        <link rel="icon" type="image/png" sizes="96x96" href="img/icon/favicon-96x96.png" />
        <link rel="icon" type="image/png" sizes="16x16" href="img/icon/favicon-16x16.png" />
        <link rel="manifest" href="img/icon/manifest.json" />
        <meta name="msapplication-TileColor" content="#ffffff" />
        <meta name="msapplication-TileImage" content="img/icon/ms-icon-144x144.png" />
        <meta name="theme-color" content="#ffffff" />
        <link rel="stylesheet" href="css/estilos.css">

        <title>vaqueros J&D</title>
    </head>
    <body class="fondo">
        <div class="container bg-light rounded mt-5 p-3 sombras login">
            <div>
                <h1 class="text-center p-3">
                    REGISTRO
                </h1>
            </div>
            <div class="px-md-5">
                <form method="post" action="/nuevousuario">
                    <div class="form-group">
                        <label for="usuario">usuario</label>
                        <input type="text" name="usuario" class="form-control" id="usuario" required > 
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" name="Email" class="form-control" id="email" required>
                    </div>
                    <div class="form-group">
                        <label for="nombre">nombre</label>
                        <input type="text" name="nombre" class="form-control" id="nombre" required>
                    </div>
                    <div class="form-group">
                        <label for="apellido">apellido</label>
                        <input type="text" name="apellido" class="form-control" id="apellido" required>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">direccion</label>
                        <input type="direccion" name="direccion" class="form-control" id="direccion" required>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">contraseña</label>
                        <input type="password" name="password" class="form-control" id="exampleInputPassword1" required>
                    </div>
                    <input type="hidden" name="geo" id="geo">
                    
                    <div class="alert d-none alert-danger" role="alert">
                       cagaste
                    </div>
                    
                    
                    <button type="submit" class="btn btn-primary">Registrarse</button>
                </form>
            </div>
        </div>

        <!-- Optional JavaScript; choose one of the two! -->

        <!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>

        <!-- Option 2: Separate Popper and Bootstrap JS -->
        <!--
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>
        -->
    </body>
    <footer>
        <script src="js/geolocalizacion.js"></script>
    </footer>
</html>