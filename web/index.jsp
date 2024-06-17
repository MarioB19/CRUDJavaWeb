<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Index</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container">
    <div class="jumbotron mt-5">
        <h1 class="display-4">Bienvenido al CRUD de Usuarios</h1>
        <%
             session = request.getSession();
            session.setAttribute("access", "granted");
            String debugMessage = (String) request.getAttribute("debugMessage");
        %>
        <nav class="mt-3">
            <form action="list" method="post">
                <input type="hidden" name="fromIndex" value="true">
                <button type="submit" class="btn btn-primary btn-lg">Listar Usuarios (CRUD)</button>
            </form>
            <form action="calculateAverageAge" method="post">
                <input type="hidden" name="fromIndex" value="true">
                <button type="submit" class="btn btn-secondary btn-lg">Calcular Promedio de Edad</button>
            </form>
        </nav>
        <% if (debugMessage != null) { %>
            <div class="alert alert-info mt-3"><%= debugMessage %></div>
        <% } %>
    </div>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
