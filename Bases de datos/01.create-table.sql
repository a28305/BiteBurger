CREATE TABLE usuarios (
    id_usuario INT PRIMARY KEY,
    nombre VARCHAR(100),
    contrasena VARCHAR(100),
    correo VARCHAR(100),
    telefono VARCHAR(20),
    direccion VARCHAR(255)
);

CREATE TABLE Metodo_Pago (
    id_metodo INT PRIMARY KEY,
    Tipo VARCHAR(50)
);

CREATE TABLE Facturas (
    id_factura INT PRIMARY KEY,
    id_metodo INT,
    fecha_emision DATE,
    total DECIMAL(10, 2),
    iva DECIMAL(5, 2),
    FOREIGN KEY (id_metodo) REFERENCES Metodo_Pago(id_metodo)
);

CREATE TABLE Pedido (
    id_pedido INT PRIMARY KEY,
    id_factura INT,
    id_usuario INT,
    fecha DATE,
    estado_pedido VARCHAR(50),
    FOREIGN KEY (id_factura) REFERENCES Facturas(id_factura),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);
CREATE TABLE Categorias (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(50),
    descripcion VARCHAR(255)
);;
CREATE TABLE Productos (
    id_producto INT PRIMARY KEY,
    nombre VARCHAR(100),
    precio DECIMAL(10,2),
    id_categoria INT,
    disponibilidad VARCHAR(50),
    imagenes VARCHAR(255),
    FOREIGN KEY (id_categoria) REFERENCES Categorias(id_categoria)
);

CREATE TABLE Detalles_pedido (
    id_detalle INT PRIMARY KEY,
    id_pedido INT,
    id_producto INT,
    cantidad INT,
    precio DECIMAL(10, 2),
    total DECIMAL(10, 2),
    FOREIGN KEY (id_pedido) REFERENCES Pedido(id_pedido),
    FOREIGN KEY (id_producto) REFERENCES Productos(id_producto)
);


CREATE TABLE Ingredientes (
    id_ingrediente INT PRIMARY KEY,
    unidad_medida VARCHAR(50),
    stock_actual INT
);
CREATE TABLE Restaurante (
    id_restaurante INT PRIMARY KEY,
    nombre VARCHAR(100),
    direccion VARCHAR(255)
);


CREATE TABLE Almacen (
    id_almacen INT PRIMARY KEY,
    id_restaurante INT,
    ubicacion VARCHAR(100),
    Capacidaad_maxima INT,
    observaciones TEXT,
    FOREIGN KEY (id_restaurante) REFERENCES Restaurante(id_restaurante)
);

CREATE TABLE Producto_Ingrediente (
    id_producto INT,
    id_ingrediente INT,
    cantidad_usada INT,
    FOREIGN KEY (id_producto) REFERENCES Productos(id_producto),
    FOREIGN KEY (id_ingrediente) REFERENCES Ingredientes(id_ingrediente)
);

CREATE TABLE Proveedores (
    id_proveedor INT PRIMARY KEY,
    nombre_empresa VARCHAR(100),
    email VARCHAR(100)
);

CREATE TABLE Proveedores_Almacen (
    id_proveedor INT,
    id_almacen INT,
    fecha_ultima_entrega DATE,
    FOREIGN KEY (id_proveedor) REFERENCES Proveedores(id_proveedor),
    FOREIGN KEY (id_almacen) REFERENCES Almacen(id_almacen)
);


CREATE TABLE Resenas (
    id_resena INT PRIMARY KEY,
    id_restaurante INT,
    id_usuario INT,
    puntuacion INT,
    comentario TEXT,
    FOREIGN KEY (id_restaurante) REFERENCES Restaurante(id_restaurante),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);

CREATE TABLE Departamento (
    id_departamento INT PRIMARY KEY,
    nombre VARCHAR(100)
);

CREATE TABLE Empleados (
    id_empleado INT PRIMARY KEY,
    nombre VARCHAR(100),
    apellido VARCHAR(100)
);

CREATE TABLE Trabajo (
    id_empleado INT,
    id_departamento INT,
    fecha_inicio DATE,
    salario DECIMAL(10, 2),
    FOREIGN KEY (id_empleado) REFERENCES Empleados(id_empleado),
    FOREIGN KEY (id_departamento) REFERENCES Departamento(id_departamento)
);

CREATE TABLE Estado (
    id_estado INT PRIMARY KEY,
    nombre VARCHAR(100)
);

CREATE TABLE Ciudad (
    id_ciudad INT PRIMARY KEY,
    nombre VARCHAR(100)
);
