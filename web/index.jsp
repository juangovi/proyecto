<%-- 
    Document   : index
    Created on : 24-mar-2021, 10:51:55
    Author     : juana
--%>

<%@page import="modelo.Linea_pedido"%>
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
            List<productos> listapro = con.obtenerultimospedidos();
            List<Categorias> categorias = con.obtenercategorias();
            if (sesion.getAttribute("user") != null) {
                user = (Usuario) sesion.getAttribute("user");
                log = true;
                nom = user.getNombre();
            }
            String disabled = "disabled";
            String active = "";
            if (log && sesion.getAttribute("carrito") != null) {
                List<Linea_pedido> lista = (List<Linea_pedido>) sesion.getAttribute("carrito");
                if (!lista.isEmpty()) {
                    disabled = "";
                    active = "active";
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


                        <li class="nav-item <%=active%>">
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

        <!-- ------------------------------------slicer---------------------------- -->
        <div class="container my-3 my-lg-5">
            <div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">

                <div class="carousel-inner border border-white zpos sombras">
                    <div class="carousel-item active">
                        <img src="img/slicer/WhatsApp Image 2021-03-09 at 10.23.48.jpeg" class="d-block w-100" alt="...">
                        <div class="carousel-caption ">
                            <h5>ULTIMO EN MODA</h5>
                            <p>compra tu ropa siempre a la moda</p>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <img src="img/slicer/WhatsApp Image 2021-03-11 at 11.59.54.jpeg" class="d-block w-100" alt="...">
                        <div class="carousel-caption ">
                            <h5>todo tipo de pantalones vaqueros</h5>
                            <p>los mejoras de toda andalicía</p>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <img src="img/slicer/WhatsApp Image 2021-03-10 at 13.09.19.jpeg" class="d-block w-100" alt="...">
                        <div class="carousel-caption ">
                            <h5>reserva tus pedidos</h5>
                            <p>rapido nos lo quitan de las manos</p>
                        </div>
                    </div>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleCaptions" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleCaptions" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
        <!-- ------------------------------------slicer---------------------------- -->
        <!-- ----------------------------ultimos------------------------------ -->
        <h1 class="text-center font-weight-bold">nuevos productos</h1>

        <div class="container mb-5">
            <div class="card-deck">  
                <%
                    for (productos pro : listapro) {
                %>
                <div class="card sombras">
                    <img src="<%=pro.getImagen()%>"  width="400" height="300"  class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title truncar"><%=pro.getDescripcion()%></h5>
                        <p class="card-text">Talla:<br>precio:<%=pro.getPrecio()%>€</p>
                    </div>
                    <%
                        if (log) {
                    %>
                    <a href="comprarproducto.jsp?pro=<%=pro.getId()%>" class="link"><div class="card-footer text-center">
                            <span class="comprar font-weight-bold">COMPRAR</span>
                        </div></a>

                    <%
                    } else {
                    %>
                    <div class="card-footer text-center">
                        <span class="comprar font-weight-bold">iniciar sesion</span>
                    </div>
                    <%
                        }
                    %>
                </div>
                <%
                    }
                %>
            </div>
        </div>
        <!-- ----------------------------ultimos------------------------------ -->
        <!-- -----------------------------------------------jumbotron--------------------------------------------- -->
        <div class="jumbo p-5">
            <h1 class="display-4">nuestras tienda</h1>
            <p class="lead">consulta la ubicación de las tiendas a lo largo de la semana y los horarios disponible de todo el mes</p>
            <hr class="my-4">
            <p>comparte tu ubicación para ver las tiendas mas cercanas para recoger sus pedidos o ver nuestros productos de cerca </p>
            <a class="btn btn-primary btn-lg" href="#" role="button">ver tiendas</a>
        </div>
        <!-- -----------------------------------------------jumbotron--------------------------------------------- -->
        <!-- categorias -->
        <h1 class="text-center font-weight-bold my-5">categorias</h1>
        <div class="container">
            <div class="row row-cols-1 row-cols-md-2">

                <%
                    for (Categorias cat : categorias) {
                %>

                <div class="col mb-4">
                    <div class="card sombras">
                        <img src="img/productos/<%=cat.getImg()%>" width="400" height="600" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h3 class="card-title"><%=cat.getNombre()%></h3>
                            <p class="card-text"> <a href="catalogo.jsp?categoria<%=cat.getId()%>=categoria<%=cat.getId()%>" class="link"><div class="card-footer text-center">
                                        <span class="comprar font-weight-bold">VER CATEGORIA</span>
                                    </div></a></p>
                        </div>
                    </div>
                </div>
                <%
                    }
                %>

            </div>
        </div>
        <!-- categorias -->
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
                            <input type="hidden" name="volver" value="index.jsp"/>
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
