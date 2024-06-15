<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.User" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Usuarios</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container">
    <h1 class="mt-5">Lista de Usuarios</h1>
    <form action="new" method="post">
        <input type="hidden" name="fromList" value="true">
        <button type="submit" class="btn btn-primary mb-3">Crear Nuevo Usuario</button>
    </form>
    <table class="table table-striped table-hover table-bordered">
        <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <th>Fecha Nacimiento</th>
                <th>Nombre Completo</th>
                <th>Correo Electrónico</th>
                <th>Password</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<User> listUser = (List<User>) request.getAttribute("listUser");
                if (listUser != null) {
                    for (User user : listUser) {
            %>
            <tr>
                <td><%= user.getId() %></td>
                <td><%= user.getFechaNacimiento() %></td>
                <td><%= user.getNombreCompleto() %></td>
                <td><%= user.getCorreoElectronico() %></td>
                <td><%= user.getPassword() %></td>
                <td>
                    <form action="edit" method="post" style="display:inline;">
                        <input type="hidden" name="fromList" value="true">
                        <input type="hidden" name="id" value="<%= user.getId() %>">
                        <button type="submit" class="btn btn-warning btn-sm">Editar</button>
                    </form>
                    <a href="delete?id=<%= user.getId() %>" class="btn btn-danger btn-sm">Eliminar</a>
                </td>
            </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
