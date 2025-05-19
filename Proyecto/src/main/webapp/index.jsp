<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Crear Usuario</title>
</head>
<body>
    <h1>Crear Usuario Nuevo</h1>
    <form action="CrearUsuarioServlet" method="post">
        Nombre: <input type="text" name="nombre" required><br>
        Contraseña: <input type="password" name="contrasena" required><br>
        Correo: <input type="email" name="correo" required><br>
        Teléfono: <input type="text" name="telefono"><br>
        Dirección: <input type="text" name="direccion"><br>
        <input type="submit" value="Crear Usuario">
    </form>
</body>
</html>
