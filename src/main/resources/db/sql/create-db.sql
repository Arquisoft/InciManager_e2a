--DROP TABLE agent IF EXISTS;
--DROP TABLE incidencia IF EXISTS;
--DROP TABLE campo IF EXISTS;
--DROP TABLE etiqueta IF EXISTS;
--DROP TABLE location IF EXISTS;

CREATE TABLE agent (
  id BIGINT PRIMARY KEY,
  contrasena VARCHAR(30),
  nombreUsuario  VARCHAR(50),
  kind VARCHAR(50),
  kindCode BIGINT,
  dni VARCHAR(50),
  nombre VARCHAR(50),
  apellidos VARCHAR(50),
  email VARCHAR(50)
);

CREATE TABLE incidencia(
    id BIGINT PRIMARY KEY,
    id_user BIGINT NOT NULL,
    foreign key (id_user) REFERENCES agent(id),
    nombre VARCHAR(50),
    descripcion VARCHAR(50),
    estado VARCHAR(50),
    fecha DATE, 
    entidadAsignada VARCHAR(50),
    comentarioOperario VARCHAR(50)
);

CREATE TABLE location (
    id BIGINT PRIMARY KEY,
    id_inc BIGINT NOT NULL UNIQUE,
    foreign key (id_inc) REFERENCES incidencia(id),
    latitud DOUBLE,
    longitud DOUBLE
);

CREATE TABLE campo (
    id BIGINT PRIMARY KEY,
    id_inc BIGINT NOT NULL,
    foreign key (id_inc) REFERENCES incidencia(id),
    clave VARCHAR(50),
    valor VARCHAR(50),
    CONSTRAINT C_campo UNIQUE (id_inc,clave)
);

CREATE TABLE etiqueta (
    id BIGINT PRIMARY KEY,
    id_inc BIGINT NOT NULL,
    foreign key (id_inc) REFERENCES incidencia(id),
    valor VARCHAR(50),
    CONSTRAINT C_etiqueta UNIQUE (id_inc,valor)
);