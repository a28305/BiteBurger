USE example_db;

INSERT INTO Productos (nombre, precio, id_categoria, disponibilidad, imagenes)
VALUES
('Cheese Burger', 8.99, 1, 'Disponible', 'imagenes/classic_burger.png'),
('BBQ Bacon Burger', 10.99, 2, 'Disponible', 'imagenes/bacon_burger.png'),
('Veggie Burger', 7.99, 3, 'Disponible', 'imagenes/veggie_burger.jpg'),
('Spicy Chicken Burger', 9.99, 4, 'Agotado', 'imagenes/Double_Chees.png');
('Chicken Burger', 9.99, 4, 'Agotado', 'imagenes/double_chicken.png');

INSERT INTO Categorias (id_categoria, tipo, descripcion)
VALUES
(1, 'Clásicas', 'Hamburguesas con ingredientes tradicionales'),
(2, 'Especiales', 'Recetas únicas con ingredientes premium'),
(3, 'Vegetarianas', 'Opciones sin carne para vegetarianos'),
(4, 'Picantes', 'Hamburguesas con un toque de picante');
