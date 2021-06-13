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
            ServletContext contexto = getServletContext();
            RequestDispatcher rd;
            if (sesion.getAttribute("user") != null) {
                user = (Usuario) sesion.getAttribute("user");
                log = true;
                nom = user.getNombre();
            } else {

                rd = contexto.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }
            if (user.getRol() < 2) {
                rd = contexto.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
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
            List<Usuario> lista = con.gettodosusurarios(request);
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
        <div class="container my-5 pt-3 bg-light rounded sombras">
            <form action="administrarusuarios.jsp" method="POST">
                <div class="form-group">
                    <input type="text" name="usuario" class="form-control" id="exampleFormControlInput1" placeholder="usuario">
                </div>
                <input type="hidden" value="1" name="buqueda">
                <input type="submit" class="btn btn-success mb-3" name="busfe" value="BUSCAR">
            </form>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">nick</th>
                        <th scope="col">email</th>
                        <th scope="col">rol</th>
                        <th scope="col">estado</th>
                        <th scope="col">opciones</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (Usuario lista1 : lista) {
                            String rol = "";
                            String estado = "";
                            if (lista1.getRol() == 0) {
                                rol = "user";
                            } else if (lista1.getRol() == 1) {
                                rol = "empleado";
                            } else if (lista1.getRol() == 2) {
                                rol = "admin";
                            }
                            int st = 0;
                            String boton = "bloquear";
                            if (lista1.getEstado() == 0) {
                                estado = "bloqueado";
                                boton = "desbloquear";
                                st = 1;
                            } else {
                                estado = "activo";
                            }


                    %>
                    <tr>
                        <th scope="row"><%=lista1.getNick()%></th>
                        <td><%=lista1.getEmail()%></td>
                        <td><%=rol%></td>
                        <td><%=estado%></td>
                        <td><a href="bloquear?id=<%=lista1.getId()%>&st=<%=st%>"><%=boton%></a>/<a href="editar.jsp?id=<%=lista1.getId()%>">editar</a></td>
                    </tr>
                    <%

                        }


                    %>
                </tbody>
            </table>
        </div>

        <!-- ------------------------contenido-------------------------- -->


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
