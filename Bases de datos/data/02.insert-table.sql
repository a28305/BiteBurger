USE example_db;

use biteburguer;
show tables;
INSERT INTO Categoria (tipo, descripcion) VALUES
('normal', 'Beef-animal burgers'),
('vegan', 'non beef-animal burgers'),
('chicken', 'Chicken burgers'),
('gluten free', 'Products without gluten'),
('complements', 'Products to complement a menu'),
('drinks', 'Drinks to complement a menu');


INSERT INTO Producto (nombre, precio, id_categoria, disponibilidad, imagenes) VALUES
('classic burger', 5.99, 1, 'Disponible', 'meet_burger.png'),
('double-classic', 8.99, 1, 'Disponible', 'doble_burger.png'),
('veggie', 7.49, 2, 'Disponible', 'veggie_burger.png'),
('BBQ', 6.99, 1, 'Disponible', 'BBQ_burger.png'),
('chicken', 5.99, 3, 'Disponible', 'chicken_burger.jpg'),
('doble-chicken', 8.99, 3, 'Disponible', 'doble_chicken_burger.png'),
('gluten-free', 6.50, 4, 'Disponible', 'gluten_free_burger.png'),
('chips', 1.50, 5, 'Disponible', 'normal_chips.png'),
('deluxe-chips', 1.99, 5, 'Disponible', 'deluxe_chips.png'),
('water', 1.00, 6, 'Disponible', 'water.png'),
('orange soda', 1.99, 6, 'Disponible', 'refresco_naranja.png'),
('lemon soda', 1.99, 6, 'Disponible', 'refresco_limon.png'),
('cola soda', 1.99, 6, 'Disponible', 'refresco_cola.png'),
('iced lemon tea', 1.99, 6, 'Disponible', 'iced_tea.png');


INSERT INTO Ingrediente (nombre, imagen) VALUES
('Sesame Bun', 'sesame_bun.png'),
('Beef Patty', 'beef_patty.png'),
('Lettuce', 'lettuce.png'),
('Tomato', 'tomato.png'),
('Cheddar Cheese', 'cheddar_cheese.png'),
('Avocado', 'avocado.png'),
('Onion', 'onion.png'),
('Double Beef Patty', 'double_beef_patty.png'),
('Onions', 'onions.png'),
('BBQ Sauce', 'bbq_sauce.png'),
('Whole Grain Bun', 'whole_grain_bun.png'),
('Veggie Patty', 'veggie_patty.png'),
('Vegan Cheese', 'vegan_cheese.png'),
('Vegan Mayo', 'vegan_mayo.png'),
('Onion Rings', 'onion_rings.png'),
('Brioche Bun', 'brioche_bun.png'),
('Crispy Chicken Breast', 'crispy_chicken_breast.png'),
('Mayo', 'mayo.png'),
('Double Chicken Patty', 'double_chicken_patty.png'),
('Spicy Mayo', 'spicy_mayo.png'),
('Gluten-Free Bun', 'gluten_free_bun.png'),
('Mustard', 'mustard.png');



INSERT INTO Producto_Ingrediente (nombre, imagen, id_producto) VALUES
('Sesame Bun', 'pan.png', 1),
('Beef Patty', 'carne.png', 1),
('Lettuce', 'lechuga.png', 1),
('Tomato', 'tomate.png', 1),
('Cheddar Cheese', 'queso.png', 1),
('Avocado', 'aguacate.png', 1),
('Onion', 'onion.png', 1),
('Sesame Bun', NULL, 2),
('Double Beef Patty', NULL, 2),
('Cheddar Cheese', NULL, 2),
('Lettuce', NULL, 2),
('Tomato', NULL, 2),
('Onions', NULL, 2),
('BBQ Sauce', NULL, 2),
('Whole Grain Bun', NULL, 3),
('Veggie Patty', NULL, 3),
('Lettuce', NULL, 3),
('Tomato', NULL, 3),
('Vegan Cheese', NULL, 3),
('Vegan Mayo', NULL, 3),
('Sesame Bun', NULL, 4),
('Beef Patty', NULL, 4),
('Onion Rings', NULL, 4),
('Cheddar Cheese', NULL, 4),
('BBQ Sauce', NULL, 4),
('Brioche Bun', NULL, 5),
('Crispy Chicken Breast', NULL, 5),
('Lettuce', NULL, 5),
('Mayo', NULL, 5),
('Brioche Bun', NULL, 6),
('Double Chicken Patty', NULL, 6),
('Cheddar Cheese', NULL, 6),
('Lettuce', NULL, 6),
('Spicy Mayo', NULL, 6),
('Gluten-Free Bun', NULL, 7),
('Beef Patty', NULL, 7),
('Lettuce', NULL, 7),
('Tomato', NULL, 7),
('Cheddar Cheese', NULL, 7),
('Mustard', NULL, 7);

INSERT INTO Restaurante (nombre, direccion) VALUES
('New York', '381 Malcolm X Blvd'),
('Los Angeles', '5601 Wilshire Blvd'),
('Chicago', '410 S Dearborn St'),
('Houston', '650 Main St'),
('Phoenix', '44 W Monroe St'),
('Miami', '198 SW 11th St');


INSERT INTO Almacen (id_restaurante, ubicacion, capacidad_maxima) VALUES
(1, 'New York', 50000),
(2, 'Los Angeles', 50000),
(3, 'Chicago', 45000),
(4, 'Houston', 40000),
(5, 'Phoenix', 40000),
(6, 'Miami', 30000);

INSERT INTO Usuarios (nombre, telefono, correo, direccion, contrasena ) VALUES
('Juan', '8099882732', 'juan.perez@example.com', 'Calle Falsa 123'),
('María', '8292332312', 'maria.lopez@example.com', 'Avenida Siempre Viva 456'),
('Carlos', '8292932934', 'carlos.gomez@example.com', 'Calle Real 789');
INSERT INTO Pedido (id_usuario, fecha, estado_pedido) VALUES
(1, '2025-05-15', 'Entregada'),
(2, '2025-05-16', 'Pendiente'),
(3, '2025-05-17', 'En preparación');

INSERT INTO Detalle_Pedido (id_pedido, id_producto, cantidad, total) VALUES
(1, 1, 2, 17.98),
(1, 8, 1, 1.50),
(2, 3, 1, 7.99),
(2, 10, 1, 1.00),
(3, 2, 1, 8.99),
(3, 11, 1, 1.99);


INSERT INTO Metodo_Pago (tipo) VALUES
('Tarjeta'),
('Efectivo'),
('Paypal');

INSERT INTO Factura (id_metodo, fecha_emision, total, iva) VALUES
(1, '2025-05-15', 19.48, 1.50),
(2, '2025-05-16', 9.99, 0.99),
(3, '2025-05-17', 10.98, 1.00);


INSERT INTO Empleado (nombre,apellidp,DNI, telefono, correo, direccion, cargo) VALUES
('Carlos', '8099882732', 'carlos.gomez@restaurante.com', 'Calle Falsa 123', 'Cocinero'),
('María', '8292332312', 'maria.lopez@restaurante.com', 'Avenida Siempre Viva 456', 'Camarera');

INSERT INTO Proveedor (nombre_empresa, telefono, correo, direccion) VALUES
('CarneCo', '8091234567', 'contacto@carneco.com', 'Calle Proveedor 101'),
('QuesosDeluxe', '8297654321', 'ventas@quesosdeluxe.com', 'Avenida Queso 202');

INSERT INTO Proveedor_Almacen (id_proveedor, id_almacen, fecha_ultima_entrega) VALUES
(1, 1, '2025-05-14'),
(2, 2, '2025-05-12');
INSERT INTO Departamento (nombre) VALUES
('Cocina'),
('Atención al Cliente'),
('Logística'),
('Recursos Humanos'),
('Marketing'),
('Finanzas'),
('Limpieza'),
('Gerencia');


contraseña : biteburguerSql
usuario: admin