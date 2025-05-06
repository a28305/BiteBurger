<?php
$servername = "db";
$username = "example";  
$password = "example";
$dbname = "example_db";  

$conn = new mysqli($servername, $username, $password, $dbname);

// Verificar conexión
if ($conn->connect_error) {
    die(json_encode(["error" => "Error de conexión: " . $conn->connect_error]));
}
?>
