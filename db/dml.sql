INSERT INTO concesionario.cliente (nif, nombre, direccion, ciudad, telefono, categoria, nVentas,estadoAlta)
VALUES
  ('12345678A', 'Juan Lopez', 'Calle Gran Vía, 123', 'Madrid', '623456789', 'Plata',1,1),
  ('98765432B', 'Pedro Gonzalez', 'Calle Arganda, 20', 'Madrid', '987654321', 'Plata',1,0);
  
INSERT INTO concesionario.empleado (ID, NOMBRE, DIRECCION, EMAIL, TELEFONO, SUELDO, COMISION) VALUES 
  (1, 'Javier Fernandez', 'CALLE GRAN VÍA, 51', 'javier@gmail.com', '916543876', 18000, 0.5),  (2, 'Maria Sanchez', 'CALLE ATOCHA, 13', 'maria@gmail.com', '643397543', 19000, 0.2);

INSERT INTO concesionario.coche (matricula, marca, modelo, color, precio, estado, anyo)
VALUES
  ('ABC1234', 'Toyota', 'Camry', 'Rojo', 25000.00, 1, 2022),
  ('XYZ5678', 'Honda', 'Civic', 'Azul', 22000.00, 1, 2021),
  ('DEF9876', 'Ford', 'Mondeo', 'Negro', 20000.00, 0, 2020);
  
INSERT INTO concesionario.venta (fecha,idCliente,idEmpleado,idCoche,monto) VALUES 
('2023-01-23',2,1,'ABC1234',30000.00),
('2023-12-14',1,2,'XYZ5678',25000.00);