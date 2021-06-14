<%-- 
    Document   : index
    Created on : 24-mar-2021, 10:51:55
    Author     : juana
--%>

<%@page import="modelo.Linea_pedido"%>
<%@page import="modelo.Paginacion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Arrays"%>
<%@page import="modelo.Categorias"%>
<%@page import="java.util.List"%>
<%@page import="modelo.productos"%>
<%@page import="coneccion.coneccion"%>
<%@page import="modelo.Usuario"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">

    <head>
        <!---------------------codigo----------------------->
        <%

            String nom = "iniciar sesion";
            Usuario user = null;
            boolean log = false;
            HttpSession sesion = request.getSession();

            coneccion con = new coneccion();
            List<Categorias> categorias = con.obtenercategorias();
            Paginacion res = con.todoslosproductospaginados(request, categorias);

            if (sesion.getAttribute("user") != null) {
                user = (Usuario) sesion.getAttribute("user");
                log = true;
                nom = user.getNombre();
            }
            String disabled = "disabled";
            String activate = "";
            if (log && sesion.getAttribute("carrito") != null) {
                List<Linea_pedido> lista = (List<Linea_pedido>) sesion.getAttribute("carrito");
                if (!lista.isEmpty()) {
                    disabled = "";
                    activate = "active";
                }

            }
            String fil = "";
            if (request.getQueryString() != null && !request.getQueryString().equals("")) {

                fil = request.getQueryString();
                String prueba[] = fil.split("&");
                if (request.getQueryString().contains("pag=")) {
                    fil = "";
                    for (int i = 0; i < prueba.length - 1; i++) {
                        if (i > 0) {
                            fil += "&";
                        }
                        fil += prueba[i];
                    }
                }
            }

        %>
        <!---------------------codigo----------------------->
        <!-- Required meta tags -->
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/estilos.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
              integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous" />
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
        <title>vaqueros J&D</title>
        <script src="js/slicermenonu.js"></script>
        <script src="https://kit.fontawesome.com/8bdbc3ead7.js" crossorigin="anonymous"></script>


    </head>

    <body class="fondo">
        <div class="d-none d-lg-block">
            <img src="img/banner.png" class="mx-auto img-fluid " alt="...">
        </div>
        <div class="d-block d-lg-none">
            <img src="img/bannersmall.png" class="mx-auto img-fluid " alt="...">
        </div>
        <!-- navar -->

        <div class="d-none d-lg-block sticky-top aver">
            <nav class="navbar navbar-expand navbar-light barra ">
                <a class="mr-3 letra" href="inicio">Juanito & Dolores</a>
                <div class="navbar-collapse">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="catalogo.jsp">catalogo <span class="sr-only">(current)</span></a>
                        </li>

                        <li class="nav-item <%=activate%>">
                            <a class="nav-link <%=disabled%>" href="carrito.jsp">carrito</a>
                        </li>
                    </ul>
                    <div class="btn-group">
                        <!--<button type="button" class="btn dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"
                          aria-expanded="false">
                          
                        </button>-->



                        <!-- Modal -->


                        <%
                            if (log) {
                        %>
                        <button type="button" class="btn dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"
                                aria-expanded="false">
                            <%=nom%>
                        </button>
                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuLink">
                            <a class="dropdown-item" href="modificarperfil.jsp">mi perfil</a>
                            <a class="dropdown-item" href="pedidos.jsp">mis pedidos</a>
                            <a class="dropdown-item" href="cerrarSesion">cerrar sesion</a>
                            <%
                                if (user.getRol() > 0) {
                            %>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="añadirproducto.jsp">nuevo producto</a>
                            <a class="dropdown-item" href="administrarpedidos.jsp">administrar pedidos</a>

                            <%
                                }
                            %>
                            <%
                                if (user.getRol() > 1) {
                            %>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="administrarusuarios.jsp">usuarios</a>
                             <a class="dropdown-item" href="aminnuevousuario.jsp">nuevo usuarios</a>
                            <%
                                }
                            %>
                        </div>
                        <%
                        } else {
                        %>
                        <button type="button" class="btn" data-toggle="modal" data-target="#exampleModal">
                            iniciar sesion
                        </button>
                        <%
                            }
                        %>
                    </div>
                </div>
            </nav>
        </div>
        <!-- navar -->
        <!-- navbar chikita -->
        <div class="d-block d-lg-none pegajoso">
            <nav class="py-1 text-center navbar-light barra">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-3 text-left">
                            <span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776;</span>
                        </div>
                        <div class="col-6 text-center">
                            <a class="letra" href="#">Juanito & Dolores</a>
                        </div>
                        <div class="col-3 text-right">
                            <div class="dropdown">
                                <!-- <button class="btn " type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true"
                                   aria-expanded="false">
                                   <i class="fas fa-user" style="font-size: 200%;"></i>
                                 </button>-->

                                <%
                                    if (log) {
                                %>
                                <button class="btn " type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true"
                                        aria-expanded="false">
                                    <i class="fas fa-user" style="font-size: 200%;"></i>
                                </button>
                                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuLink">
                                    <a class="dropdown-item" href="modificarperfil.jsp">mi perfil</a>
                                    <a class="dropdown-item" href="pedidos.jsp">mis pedidos</a>
                                    <a class="dropdown-item" href="cerrarSesion">cerrar sesion</a>
                                    <%
                                        if (user.getRol() > 0) {
                                    %>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="añadirproducto.jsp">nuevo producto</a>
                                    <a class="dropdown-item" href="administrarpedidos.jsp">administrar pedidos</a>
                                     <a class="dropdown-item" href="aminnuevousuario.jsp">nuevo usuarios</a>

                                    <%
                                        }
                                    %>
                                    <%
                                        if (user.getRol() > 1) {
                                    %>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="administrarusuarios.jsp">usuarios</a>
                                    <%
                                        }
                                    %>
                                </div>
                                <%
                                } else {
                                %>
                                <button type="button" class="btn" data-toggle="modal" data-target="#exampleModal">
                                    <i class="fas fa-user" style="font-size: 200%;"></i>
                                </button>
                                <%
                                    }
                                %>
                            </div>
                        </div>
                    </div>
                </div>
            </nav>

        </div>
        <!-- navbar chikita -->
        <!-- menusito whey -->
        <div id="mySidenav" class="sidenav">
            <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
            <a href="index.jsp">inicio</a>
            <a class="nav-link" href="catalogo.jsp">catalogo <span class="sr-only">(current)</span></a>
            <a class="nav-link <%=disabled%>" href="carrito.jsp">carrito</a>
        </div>
        <!-- menusito whey -->

        <!-- ------------------------contenido-------------------------- -->
        <div class="container-fluid">


            <div class="row">
                <div class="col-md-3">
                    <div class="container my-5">
                        <div class="tarjeta sombras rounded">
                            <div class="card-body">
                                <h3 class="card-title text-center">filtrar contenido</h3>
                                <div class="card-text">
                                    <form action="" method="get">
                                        <h5 class="card-title text-center">buscar</h5>
                                        <input type="text" name="busqueda" class="form-control" id="text">

                                        <h5 class="card-title text-center">Ropa</h5>
                                        <%
                                            for (Categorias cat : categorias) {
                                                String select = "";
                                                if (request.getParameter("categoria" + cat.getId()) != null) {
                                                    select = "checked";
                                                }
                                        %>
                                        <div class="form-check">
                                            <input class="form-check-input" <%=select%> type="checkbox" name="categoria<%=cat.getId()%>" value="<%=cat.getId()%>" id="<%=cat.getNombre()%>">
                                            <label class="form-check-label" for="<%=cat.getNombre()%>">
                                                <%=cat.getNombre()%>
                                            </label>
                                        </div>
                                        <%
                                            }
                                        %>
                                        <input type="submit" name="boton" class="btn btn-light" value="buscar"/>
                                    </form>
                                </div>

                            </div>
                        </div>
                    </div>

                </div>
                <div class="col-md-9">
                    <div class="container-fluid tarjeta my-5 sombras py-4 rounded">
                        <div class="row row-cols-1 row-cols-md-3">
                            <%
                                for (productos pro : res.getListapro()) {
                            %>
                            <div class="col mb-4">
                                <div class="card h-100">
                                    <img src="<%=pro.getImagen()%>" width="400" height="600" class="card-img-top" alt="">
                                    <div class="card-body">
                                        <h5 class="card-title"><%=pro.getDescripcion()%></h5>
                                        <p class="card-text">precio:<%=pro.getPrecio()%>€</p>
                                    </div>
                                    <%
                                        if (log) {
                                    %>
                                    <a href="comprarproducto.jsp?pro=<%=pro.getId()%>" class="link"><div class="card-footer text-center">
                                            <span class="comprar font-weight-bold">RESERVAR</span>
                                        </div></a>

                                    <%
                                        if (user.getRol()>0) {
                                    %>
                                    <a href="editarproducto.jsp?id=<%=pro.getId()%>" class="link"><div class="card-footer text-center">
                                            <span class="comprar font-weight-bold">modificar</span>
                                        </div></a>
                                    <%
                                    }} else {
                                    %>
                                    <div class="card-footer text-center">
                                        <span class="comprar font-weight-bold">iniciar sesion</span>
                                    </div>
                                    <%
                                        }
                                    %>
                                </div>
                            </div>
                            <%
                                }
                            %>


                        </div>
                    </div>
                </div>
            </div>
            <div>
                <nav aria-label="..." style="margin: auto;"> 
                    <ul class="pagination justify-content-center sombreado">
                        <%
                            String first = "";
                            if (res.getPag() <= 1) {
                                first = "disabled";
                            }
                        %>
                        <li class="page-item <%=first%>">
                            <a class="page-link" href="catalogo.jsp?<%=fil%>&pag=<%=res.getPag() - 1%>">atras</a>
                        </li>
                        <%
                            for (int paginas = 1; paginas <= res.getNumpag(); paginas++) {
                                String active = "";
                                if (paginas == res.getPag()) {
                                    active = "active";
                                }

                        %>
                        <li class="page-item <%=active%>"><a class="page-link" href="catalogo.jsp?<%=fil%>&pag=<%=paginas%>"><%=paginas%></a></li>
                            <%
                                }
                                String last = "";
                                if (res.getNumpag() <= res.getPag()) {

                                    last = "disabled";
                                }
                            %>

                        <li class="page-item <%=last%>">
                            <a class="page-link" href="catalogo.jsp?<%=fil%>&pag=<%=res.getPag() + 1%>">siguiente</a>
                        </li>
                    </ul>
                </nav>
            </div>

        </div>
        <!-- ventana modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1"  aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Iniciar sesion</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form class="px-4 py-3" method="post" action="inicio" id="formulario1" name="formulario1" onsubmit="return prueba(1)">
                            <input type="hidden" name="volver" value="catalogo.jsp"/>
                            <div class="form-group">
                                <label for="email1">usuario/email  <span class="erroruse" style="color: red;"></span></label>
                                <input type="text" class="form-control" name="log" id="email1" placeholder="email@ejemplo.com">
                            </div>
                            <div class="form-group">
                                <label for="password1">contraseña</label>
                                <input type="password" class="form-control" name="pass" id="password1" placeholder="contraseña">
                            </div>
                            <div class="form-group">
                                <div class="form-check">

                                </div>
                            </div>
                            <button type="submit" class="btn btn-primary">Entrar</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <a class="dropdown-item" href="iniciarSession.jsp">crear una cuenta nueva</a>

                    </div>
                </div>
            </div>
        </div>
        <!-- ------------------------contenido-------------------------- -->
        <footer class="footer text-center text-light">
            <!-- Grid container -->
            <div class="container p-4 pb-0">
                <!-- Section: Social media -->
                <section class="mb-4">
                    <!-- Facebook -->
                    <a class="btn btn-outline-light btn-floating m-1" href="https://www.facebook.com/vaquerosjuanito.jd" role="button"
                       ><i class="fab fa-facebook-f"></i>
                    </a>
                    <!-- Instagram -->
                    <a class="btn btn-outline-light btn-floating m-1" href="https://www.instagram.com/jagovi59/" role="button"
                       ><i class="fab fa-instagram"></i>
                    </a>
                    <!-- mail -->
                    <a class="btn btn-outline-light btn-floating m-1" href="mailto:juanantoniogovidal@gmail.com" role="button"
                       ><i class="fas fa-at"></i>
                    </a>
                    <!-- yo -->
                    <a class="btn btn-outline-light btn-floating m-1" href="#!" role="button"
                       ><i class="fas fa-user-tie"></i>
                    </a>

                </section>
                <!-- Section: Social media -->
            </div>
            <!-- Grid container -->

            <!-- Copyright -->
            <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2);">
                © 2021 Copyright:Juan Antonio Gonzalez vidal

            </div>
            <!-- Copyright -->
        </footer>

        <!-- Optional JavaScript; choose one of the two! -->

        <!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
        crossorigin="anonymous"></script>

        <!-- Option 2: Separate Popper and Bootstrap JS -->
        <!--
          <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
          <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
          <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>
        -->
        <script src="js/comprobaciones.js"></script>
        <script src="js/slicermenonu.js"></script>
    </body>

</html>
