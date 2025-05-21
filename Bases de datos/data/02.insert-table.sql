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


INSERT INTO Producto (nombre, precio, id_categoria, descripcion, imagenes) VALUES
('classic burger', 5.99, 1, 'Juicy beef patty, creamy avocado, fresh tomato, and crisp lettuce, all stacked in a toasted bun-simple, classic, and mouthwateringly delicious.', 'meet_burger.png'),
('double-classic', 8.99, 1, 'Double juicy beef patties, creamy avocado, fresh tomato, and crisp lettuce on a toasted bun-bold, classic flavor in every irresistible bite.', 'doble_burger.png'),
('veggie', 7.49, 2, 'Two savory vegan patties, creamy avocado, crisp lettuce, and juicy tomato on a green veggie bun-fresh, vibrant, and packed with plant-powered flavor.', 'veggie_burger.png'),
('BBQ', 6.99, 1, 'Two juicy beef patties, smoky bacon, and rich BBQ sauce on a toasted bun-bold, messy, and bursting with sweet, savory, and smoky flavor in every bite.', 'BBQ_burger.png'),
('chicken', 5.99, 3, 'Crispy breaded chicken, fresh lettuce, and creamy mayo on a toasted bun-golden, crunchy, and deliciously smooth in every satisfying bite.', 'chicken_burger.jpg'),
('doble-chicken', 8.99, 3, 'Double crispy breaded chicken, fresh lettuce, and creamy mayo on a toasted bun-extra crunch, extra flavor, and double the golden, juicy satisfaction.', 'doble_chicken_burger.png'),
('gluten-free', 6.50, 4, 'Juicy beef patty, creamy avocado, fresh tomato, and crisp lettuce on a soft, gluten-free bun—pure, classic flavor with no sesame seeds, just goodness.', 'gluten_free_burger.png'),
('chips', 1.50, 5, 'Golden, crispy classic fries. Perfectly salted, fluffy inside, and irresistibly crunchy with every bite. Simple, timeless, and always satisfying.', 'normal_chips.png'),
('deluxe-chips', 1.99, 5, 'Thick-cut deluxe fries: Crispy on the outside, tender inside, seasoned to perfection for a bold, hearty bite every time. Rich, rustic, and deeply satisfying.', 'deluxe_chips.png'),
('water', 1.00, 6, 'Just mineral water.', 'water.png'),
('orange soda', 1.99, 6, 'Vibrant orange soda, fizzy and sweet with zesty citrus punch: refreshingly bright and bursting with lively, tangy flavor in every bubbly sip.', 'refresco_naranja.png'),
('lemon soda', 1.99, 6, 'Crisp lemon soda, sparkling and tart with a refreshing citrus zing: cool, lively, and perfectly balanced for a thirst-quenching blast.', 'refresco_limon.png'),
('cola soda', 1.99, 6, 'Classic cola soda: bold, fizzy, and sweet with deep caramel notes and a refreshing sparkle that wakes up your taste buds with every sip.', 'refresco_cola.png'),
('iced lemon tea', 1.99, 6, 'Refreshing iced tea soda: lightly sweetened with natural tea notes and a hint of citrus, sparkling and cool for a crisp, revitalizing sip every time.', 'iced_tea.png');


INSERT INTO Ingrediente (nombre, imagen) VALUES
('Sesame Bun', 'sesame_bun.png'),
('Beef Patty', 'beef_patty.png'),
('Lettuce', 'lettuce.png'),
('Tomato', 'tomato.png'),
('Cheddar Cheese', 'cheddar_cheese.png'),
('Avocado', 'avocado.png'),
('Onion', 'onion.png'),
('Double Beef Patty', 'beef_patty.png'),
('Onions', 'onions.png'),
('BBQ Sauce', 'bbq_sauce.png'),
('Whole Grain Bun', 'whole_grain_bun.png'),
('Veggie Patty', 'veggie_patty.png'),
('Vegan Cheese', 'vegan_cheese.png'),
('Vegan Mayo', 'vegan_mayo.png'),
('Onion Rings', 'onion.png'),
('Brioche Bun', 'brioche_bun.png'),
('Crispy Chicken Breast', 'crispy_chicken_breast.png'),
('Mayo', 'mayo.png'),
('Double Chicken Patty', 'crispy_chicken_breast.png'),
('Spicy Mayo', 'spicy_mayo.png'),
('Gluten-Free Bun', 'brioche_bun.png'),
('Mustard', 'mustard.png');



INSERT INTO Producto_Ingrediente (nombre, imagen, id_producto) VALUES
('Sesame Bun', 'sesame_bun.png', 1),
('Beef Patty', 'beef_patty.png', 1),
('Lettuce', 'lettuce.png', 1),
('Tomato', 'tomato.png', 1),
('Cheddar Cheese', 'cheddar_cheese.png', 1),
('Avocado', 'avocado.png', 1),
('Onion', 'onion.png', 1),
('Sesame Bun', 'sesame_bun.png', 2),
('Double Beef Patty', 'beef_patty.png', 2),
('Cheddar Cheese', 'cheddar_cheese.png', 2),
('Lettuce', 'lettuce.png', 2),
('Tomato', 'tomato.png', 2),
('Onions', 'onions.png', 2),
('BBQ Sauce', 'bbq_sauce.png', 2),
('Whole Grain Bun', 'whole_grain_bun.png', 3),
('Veggie Patty', 'veggie_patty.png', 3),
('Lettuce', 'lettuce.png', 3),
('Tomato', 'tomato.png', 3),
('Vegan Cheese', 'vegan_cheese.png', 3),
('Vegan Mayo', 'vegan_mayo.png', 3),
('Sesame Bun', 'sesame_bun.png', 4),
('Beef Patty', 'beef_patty.png', 4),
('Onion Rings', 'onion_rings.png', 4),
('Cheddar Cheese', 'cheddar_cheese.png', 4),
('BBQ Sauce', 'bbq_sauce.png', 4),
('Brioche Bun', 'brioche_bun.png', 5),
('Crispy Chicken Breast', 'crispy_chicken_breast.png', 5),
('Lettuce', 'lettuce.png', 5),
('Mayo', 'mayo.png', 5),
('Brioche Bun', 'brioche_bun.png', 6),
('Double Chicken Patty', 'double_chicken_patty.png', 6),
('Cheddar Cheese', 'cheddar_cheese.png', 6),
('Lettuce', 'lettuce.png', 6),
('Spicy Mayo', 'spicy_mayo.png', 6),
('Gluten-Free Bun', 'gluten_free_bun.png', 7),
('Beef Patty', 'beef_patty.png', 7),
('Lettuce', 'lettuce.png', 7),
('Tomato', 'tomato.png', 7),
('Cheddar Cheese', 'cheddar_cheese.png', 7),
('Mustard', 'mustard.png', 7);


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
