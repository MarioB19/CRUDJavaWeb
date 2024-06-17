<%-- 
    Document   : user-age
    Created on : 16 jun 2024, 22:15:48
    Author     : BRUNO
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Promedio de Edad</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container">
    <div class="jumbotron mt-5">
        <h1 class="display-4">Promedio de Edad de los Usuarios</h1>
        <p class="lead">El promedio de edad de los usuarios es: <%= request.getAttribute("averageAge") %> años</p>
        <hr class="my-4">
        <a class="btn btn-primary btn-lg" href="index.jsp" role="button">Volver a Inicio</a>
    </div>
</body>
</html>

