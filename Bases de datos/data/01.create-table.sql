CREATE TABLE Usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) UNIQUE,
    contrasena TEXT,
    correo VARCHAR(100) UNIQUE,
    telefono VARCHAR(20),
    direccion VARCHAR(255)
);

CREATE TABLE Metodo_Pago (
    id_metodo INT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(50)
);

CREATE TABLE Factura (
    id_factura INT AUTO_INCREMENT PRIMARY KEY,
    id_metodo INT,
    fecha_emision DATE,
    total DECIMAL(10,2),
    iva DECIMAL(5,2),
    FOREIGN KEY (id_metodo) REFERENCES Metodo_Pago(id_metodo)
);

CREATE TABLE Pedido (
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    id_factura INT,
    id_usuario INT,
    fecha DATE,
    estado_pedido VARCHAR(50),
    FOREIGN KEY (id_factura) REFERENCES Factura(id_factura),
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);

CREATE TABLE Categoria (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(50),
    descripcion VARCHAR(255)
);

CREATE TABLE Ingrediente (
    id_ingrediente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    imagen VARCHAR(255)
);

CREATE TABLE Producto (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    precio DECIMAL(10,2),
    id_categoria INT,
    disponibilidad VARCHAR(50),
    imagenes VARCHAR(255),
    FOREIGN KEY (id_categoria) REFERENCES Categoria(id_categoria)
);

CREATE TABLE Producto_Ingrediente (
    id_producto INT,
    id_ingrediente INT,
    PRIMARY KEY (id_producto, id_ingrediente),
    FOREIGN KEY (id_producto) REFERENCES Producto(id_producto),
    FOREIGN KEY (id_ingrediente) REFERENCES Ingrediente(id_ingrediente)
);

CREATE TABLE Detalle_Pedido (
    id_detalle INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT,
    id_producto INT,
    cantidad INT CHECK (cantidad > 0),
    precio DECIMAL(10,2),
    total DECIMAL(10,2),
    FOREIGN KEY (id_pedido) REFERENCES Pedido(id_pedido),
    FOREIGN KEY (id_producto) REFERENCES Producto(id_producto)
);

CREATE TABLE Restaurante (
    id_restaurante INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    direccion VARCHAR(255)
);

CREATE TABLE Almacen (
    id_almacen INT AUTO_INCREMENT PRIMARY KEY,
    id_restaurante INT,
    ubicacion VARCHAR(100),
    capacidad_maxima INT,
    observaciones TEXT,
    id_ingrediente INT,
    cantidad INT CHECK (cantidad >= 0),
    FOREIGN KEY (id_restaurante) REFERENCES Restaurante(id_restaurante),
    FOREIGN KEY (id_ingrediente) REFERENCES Ingrediente(id_ingrediente)
);

CREATE TABLE Proveedor (
    id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
    nombre_empresa VARCHAR(100),
    email VARCHAR(100) UNIQUE
);

CREATE TABLE Proveedor_Almacen (
    id_proveedor INT,
    id_almacen INT,
    fecha_ultima_entrega DATE,
    PRIMARY KEY (id_proveedor, id_almacen),
    FOREIGN KEY (id_proveedor) REFERENCES Proveedor(id_proveedor),
    FOREIGN KEY (id_almacen) REFERENCES Almacen(id_almacen)
);

CREATE TABLE Resena (
    id_resena INT AUTO_INCREMENT PRIMARY KEY,
    id_restaurante INT,
    id_usuario INT,
    puntuacion INT CHECK (puntuacion BETWEEN 1 AND 5),
    comentario TEXT,
    FOREIGN KEY (id_restaurante) REFERENCES Restaurante(id_restaurante),
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);

CREATE TABLE Departamento (
    id_departamento INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100)
);

CREATE TABLE Empleado (
    id_empleado INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    DNI VARCHAR(100) UNIQUE,
    contrasena TEXT
);

CREATE TABLE Trabajo (
    id_empleado INT,
    id_departamento INT,
    fecha_inicio DATE,
    salario DECIMAL(10,2),
    PRIMARY KEY (id_empleado, id_departamento),
    FOREIGN KEY (id_empleado) REFERENCES Empleado(id_empleado),
    FOREIGN KEY (id_departamento) REFERENCES Departamento(id_departamento)
);

CREATE TABLE Estado (
    id_estado INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100)
);

CREATE TABLE Ciudad (
    id_ciudad INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    id_estado INT,
    FOREIGN KEY (id_estado) REFERENCES Estado(id_estado)
);

CREATE TABLE Ofertas_Trabajo (
    id_oferta INT AUTO_INCREMENT PRIMARY KEY,
    nombre_oferta VARCHAR(100),
    descripcion TEXT,
    imagen VARCHAR(255),
    fecha_publicacion DATE,
    id_departamento INT,
    FOREIGN KEY (id_departamento) REFERENCES Departamento(id_departamento)
);

CREATE TABLE Curriculum (
    id_curriculum INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    id_oferta INT,
    archivo VARCHAR(255),
    fecha_envio DATE,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario),
    FOREIGN KEY (id_oferta) REFERENCES Ofertas_Trabajo(id_oferta)
);

CREATE TABLE Carrito (
    id_carrito INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    id_producto INT,
    cantidad INT CHECK (cantidad > 0),
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario),
    FOREIGN KEY (id_producto) REFERENCES Producto(id_producto)
);

