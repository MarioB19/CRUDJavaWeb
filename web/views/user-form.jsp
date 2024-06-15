<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.User" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulario de Usuario</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container">
    <h1 class="mt-5"><%= request.getAttribute("user") != null ? "Actualizar Usuario" : "Crear Usuario" %></h1>

    <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
    %>
        <div class="alert alert-danger"><%= errorMessage %></div>
    <%
        }
    %>
    <form action="<%= request.getAttribute("user") != null ? "update" : "insert" %>" method="post" novalidate>
        <input type="hidden" name="id" value="<%= request.getAttribute("user") != null ? ((User) request.getAttribute("user")).getId() : "" %>">
        <div class="form-group">
            <label for="fechaNacimiento">Fecha Nacimiento:</label>
            <input type="date" name="fechaNacimiento" class="form-control" required value="<%= request.getAttribute("fechaNacimiento") != null ? request.getAttribute("fechaNacimiento") : request.getAttribute("user") != null ? ((User) request.getAttribute("user")).getFechaNacimiento() : "" %>">
            <div class="invalid-feedback">Debe tener al menos 18 años.</div>
        </div>
        <div class="form-group">
            <label for="nombreCompleto">Nombre Completo:</label>
            <input type="text" name="nombreCompleto" class="form-control" required maxlength="100" value="<%= request.getAttribute("nombreCompleto") != null ? request.getAttribute("nombreCompleto") : request.getAttribute("user") != null ? ((User) request.getAttribute("user")).getNombreCompleto() : "" %>">
            <div class="invalid-feedback">Nombre completo no es válido.</div>
        </div>
        <div class="form-group">
            <label for="correoElectronico">Correo Electrónico:</label>
            <input type="email" name="correoElectronico" class="form-control" required maxlength="100" value="<%= request.getAttribute("correoElectronico") != null ? request.getAttribute("correoElectronico") : request.getAttribute("user") != null ? ((User) request.getAttribute("user")).getCorreoElectronico() : "" %>">
            <div class="invalid-feedback">Correo electrónico no es válido.</div>
        </div>
        <% if (request.getAttribute("user") == null) { %>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" name="password" class="form-control" required maxlength="250" value="<%= request.getAttribute("password") != null ? request.getAttribute("password") : "" %>">
                <small class="form-text text-muted">La contraseña debe tener al menos 8 caracteres, incluyendo un número, una letra mayúscula, una letra minúscula y un carácter especial (@#$%^&+=).</small>
                <div class="invalid-feedback">Contraseña no es válida.</div>
            </div>
        <% } %>
        <button type="submit" class="btn btn-success">Guardar</button>
    </form>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
