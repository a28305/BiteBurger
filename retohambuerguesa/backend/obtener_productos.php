<?php
// Configuración de errores para debugging
ini_set('display_errors', 1);
error_reporting(E_ALL);

// Configuración de encabezados para CORS y respuesta JSON
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type, Authorization");
header("Content-Type: application/json");

// Manejo de pre-flight requests (para solicitudes OPTIONS)
if ($_SERVER['REQUEST_METHOD'] === 'OPTIONS') {
    http_response_code(200);
    exit();
}

// Conexión con la base de datos
include 'db.php';

// Verificar conexión
if (!$conn) {
    echo json_encode(["error" => "Error de conexión a la base de datos"]);
    exit();
}

// Consultar los productos
$sql = "SELECT id_producto, nombre, precio, id_categoria, disponibilidad, imagenes FROM Productos";
$result = $conn->query($sql);

// Manejar error en la consulta
if (!$result) {
    echo json_encode(["error" => "Error en la consulta SQL"]);
    exit();
}

// Convertir resultados a JSON
$productos = [];
while ($row = $result->fetch_assoc()) {
    // Asegurar que la ruta de la imagen es completa
    $row["imagenes"] = "http://localhost:8000/public/imagenes/" . basename($row["imagenes"]);

    $productos[] = $row;
}

// Enviar respuesta JSON
echo json_encode($productos);
$conn->close();
?>
