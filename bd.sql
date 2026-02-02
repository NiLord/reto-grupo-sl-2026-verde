CREATE TABLE producto (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    cantidad_disponible INTEGER NOT NULL DEFAULT 0,
);


CREATE TABLE denominacion (
    id BIGSERIAL PRIMARY KEY,
    valor DECIMAL(10,2) NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    cantidad_disponible INTEGER NOT NULL DEFAULT 0
);


insert into producto(nombre, precio, cantidad_disponible) values ('Agua 100 ml', 1.15, 0)
insert into producto(nombre, precio, cantidad_disponible) values ('Soda 100 ml', 1.15, 0)
insert into producto(nombre, precio, cantidad_disponible) values ('Granola 100 ml', 1.15, 0)


INSERT INTO denominacion (valor, tipo, cantidad_disponible) VALUES
(0.01, 'MONEDA', 0),
(0.05, 'MONEDA', 0),
(0.10, 'MONEDA', 0),
(0.25, 'MONEDA', 0),
(0.50, 'MONEDA', 0),
(1.00, 'BILLETE', 0),
(2.00, 'BILLETE', 0),
(5.00, 'BILLETE', 0),
(10.00, 'BILLETE', 0),
(20.00, 'BILLETE', 0),
(50.00, 'BILLETE', 0),
(100.00, 'BILLETE', 0);

INSERT INTO producto (nombre, precio, cantidad_disponible) VALUES 
('Café Americano', 2.50, 0),
('Barra de Proteína', 3.75, 0),
('Jugo de Naranja', 2.25, 0),
('Sándwich de Jamón', 4.50, 0),
('Papas Fritas', 1.50, 0),
('Galletas de Avena', 1.80, 0);

UPDATE producto SET cantidad_disponible = 10;

UPDATE denominacion SET cantidad_disponible = 25;


