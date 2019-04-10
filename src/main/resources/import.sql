/*** DATOS DE TIPO FACTURA ***/

INSERT INTO tipo_facturas (id, nombre, activo) values (1, 'Tipo 1', true);
INSERT INTO tipo_facturas (id, nombre, activo) values (2, 'Tipo 2', true);

/*** DATOS DE FACTURA ***/

INSERT INTO facturas (id, descripcion, costo, fecha, tipo_id) values (1, 'Factura1', 250.55, '2019-04-20', 1);
INSERT INTO facturas (id, descripcion, costo, fecha, tipo_id) values (2, 'Factura2', 532.28, '2019-04-20', 2);
INSERT INTO facturas (id, descripcion, costo, fecha, tipo_id) values (3, 'Factura3', 150.02, '2019-04-20', 1);
INSERT INTO facturas (id, descripcion, costo, fecha, tipo_id) values (4, 'Factura4', 90.65, '2019-04-20', 2);
INSERT INTO facturas (id, descripcion, costo, fecha, tipo_id) values (5, 'Factura5', 1564.36, '2019-04-20', 1);
INSERT INTO facturas (id, descripcion, costo, fecha, tipo_id) values (6, 'Factura6', 250.55, '2019-04-20', 2);
INSERT INTO facturas (id, descripcion, costo, fecha, tipo_id) values (7, 'Factura7', 532.28, '2019-03-20', 1);
INSERT INTO facturas (id, descripcion, costo, fecha, tipo_id) values (8, 'Factura8', 150.02, '2019-02-20', 2);
INSERT INTO facturas (id, descripcion, costo, fecha, tipo_id) values (9, 'Factura9', 90.65, '2019-04-20', 1);
INSERT INTO facturas (id, descripcion, costo, fecha, tipo_id) values (10, 'Factura10', 1564.36, '2019-03-20', 2);
INSERT INTO facturas (id, descripcion, costo, fecha, tipo_id) values (11, 'Factura11', 250.55, '2019-02-20', 1);
INSERT INTO facturas (id, descripcion, costo, fecha, tipo_id) values (12, 'Factura12', 532.28, '2019-04-20', 2);
INSERT INTO facturas (id, descripcion, costo, fecha, tipo_id) values (13, 'Factura13', 150.02, '2019-03-20', 1);
INSERT INTO facturas (id, descripcion, costo, fecha, tipo_id) values (14, 'Factura14', 90.65, '2019-02-20', 2);
INSERT INTO facturas (id, descripcion, costo, fecha, tipo_id) values (15, 'Factura15', 1564.36, '2019-04-20', 1);
