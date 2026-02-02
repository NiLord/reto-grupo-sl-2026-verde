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