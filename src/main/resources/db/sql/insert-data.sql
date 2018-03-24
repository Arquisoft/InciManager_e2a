INSERT INTO agent VALUES (1, '1234', 'juan', 'Person', 1, '526395L', 'Juan', 'Perez', 'juan@gmail.com');
INSERT INTO agent VALUES (2, '1234', 'pedro', 'Entity', 2, '554395K', 'Pedro', 'Garcia', 'pedro@gmail.com');
INSERT INTO agent VALUES (3, '1234', 'raul', 'Sensor', 3, '525695K', 'Raul', 'Ruiz', 'raul@gmail.com');

INSERT INTO incidencia VALUES (1,1,'Fuego', 'Fuego en Parque San Francisco', 'abierta', '2018-03-24', null, null);
INSERT INTO incidencia VALUES (2,1,'Inundacion', 'Inundación en la Universidad de Oviedo', 'abierta', '2018-03-22', null, null);
INSERT INTO incidencia VALUES (3,1,'Atasco', 'Atasco en la autopista', 'abierta', '2018-03-20', null, null);
INSERT INTO incidencia VALUES (4,2,'Fuego', 'Fuego en Parque San Francisco', 'abierta', '2018-03-24', null, null);
INSERT INTO incidencia VALUES (5,2,'Inundacion', 'Inundación en la Universidad de Oviedo', 'abierta', '2018-03-22', null, null);
INSERT INTO incidencia VALUES (6,2,'Atasco', 'Atasco en la autopista', 'abierta', '2018-03-20', null, null);
INSERT INTO incidencia VALUES (7,3,'Fuego', 'Fuego en Parque San Francisco', 'abierta', '2018-03-24', null, null);
INSERT INTO incidencia VALUES (8,3,'Inundacion', 'Inundación en la Universidad de Oviedo', 'abierta', '2018-03-22', null, null);
INSERT INTO incidencia VALUES (9,3,'Atasco', 'Atasco en la autopista', 'abierta', '2018-03-20', null, null);

INSERT INTO location VALUES (1,1,65.959,-48.42213);
INSERT INTO location VALUES (2,2,25.561,38.42213);
INSERT INTO location VALUES (3,3,35.767,-28.42213);
INSERT INTO location VALUES (4,4,45.712,38.42213);
INSERT INTO location VALUES (5,5,95.111,-38.42213);
INSERT INTO location VALUES (6,6,75.222,28.42213);
INSERT INTO location VALUES (7,7,61.444,-38.42213);
INSERT INTO location VALUES (8,8,63.945,48.42213);
INSERT INTO location VALUES (9,9,62.333,-38.42213);

INSERT INTO etiqueta VALUES(1,1,'Fuego');
INSERT INTO etiqueta VALUES(2,1,'San Francisco');
INSERT INTO etiqueta VALUES(3,2,'Inundacion');
INSERT INTO etiqueta VALUES(4,3,'Atasco');
INSERT INTO etiqueta VALUES(5,4,'Fuego');
INSERT INTO etiqueta VALUES(6,5,'Inundacion');
INSERT INTO etiqueta VALUES(7,6,'Atasco');
INSERT INTO etiqueta VALUES(8,7,'Fuego');
INSERT INTO etiqueta VALUES(9,8,'Inundacion');
INSERT INTO etiqueta VALUES(10,9,'Atasco');

INSERT INTO campo VALUES(1,1,'Fuego','Extremo');
INSERT INTO campo VALUES(2,1,'Parque','En llamas');
INSERT INTO campo VALUES(3,2,'Inundacion','Extrema');
INSERT INTO campo VALUES(4,3,'Atasco','pequeño');
INSERT INTO campo VALUES(5,4,'Fuego','Extremo');
INSERT INTO campo VALUES(6,5,'Inundacion','Extrema');
INSERT INTO campo VALUES(7,6,'Atasco','pequeño');
INSERT INTO campo VALUES(8,7,'Fuego','Extremo');
INSERT INTO campo VALUES(9,8,'Inundacion','Extrema');
INSERT INTO campo VALUES(10,9,'Atasco','pequeño');