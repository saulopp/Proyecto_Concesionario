DROP DATABASE IF EXISTS concesionario;

CREATE DATABASE concesionario; 

USE concesionario;

CREATE TABLE `coche` (
  `matricula` varchar(7) NOT NULL,
  `marca` varchar(50) NOT NULL,
  `modelo` varchar(50) NOT NULL,
  `color` varchar(50) DEFAULT NULL,
  `precio` double NOT NULL,
  `estado` tinyint NOT NULL,
  `anyo` int DEFAULT NULL,
  PRIMARY KEY (`matricula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `empleado` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `direccion` varchar(150) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `telefono` varchar(9) DEFAULT NULL,
  `sueldo` double DEFAULT NULL,
  `comision` decimal(2,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `cliente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nif` varchar(9) DEFAULT NULL,
  `nombre` varchar(50) NOT NULL,
  `direccion` varchar(150) DEFAULT NULL,
  `ciudad` varchar(50) DEFAULT NULL,
  `telefono` varchar(9) DEFAULT NULL,
  `categoria` varchar(10) DEFAULT NULL,
  `nVentas` INT  DEFAULT NULL,
  `estadoAlta` TINYINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `venta` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `idCliente` int DEFAULT NULL,
  `idEmpleado` int DEFAULT NULL,
  `idCoche` varchar(7) DEFAULT NULL,
  `monto` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ventaClienteId_idx` (`idCliente`),
  KEY `ventaEmpleadoId_idx` (`idEmpleado`),
  KEY `ventaCocheId_idx` (`idCoche`),
  CONSTRAINT `ventaClienteId` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`id`),
  CONSTRAINT `ventaCocheId` FOREIGN KEY (`idCoche`) REFERENCES `coche` (`matricula`),
  CONSTRAINT `ventaEmpleadoId` FOREIGN KEY (`idEmpleado`) REFERENCES `empleado` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;