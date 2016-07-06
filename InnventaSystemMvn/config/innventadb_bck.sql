-- MySQL dump 10.13  Distrib 5.6.31, for Linux (x86_64)
--
-- Host: localhost    Database: innventadb
-- ------------------------------------------------------
-- Server version	5.6.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `acl_acciones`
--

DROP TABLE IF EXISTS `acl_acciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acl_acciones` (
  `id` varchar(255) NOT NULL,
  `eliminado` smallint(6) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acl_acciones`
--

LOCK TABLES `acl_acciones` WRITE;
/*!40000 ALTER TABLE `acl_acciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `acl_acciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acl_roles`
--

DROP TABLE IF EXISTS `acl_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acl_roles` (
  `ID` varchar(255) NOT NULL,
  `creado_por` varchar(255) DEFAULT NULL,
  `DESCRIPCION` varchar(255) DEFAULT NULL,
  `ELIMINADO` smallint(6) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `modificado_por` varchar(255) DEFAULT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acl_roles`
--

LOCK TABLES `acl_roles` WRITE;
/*!40000 ALTER TABLE `acl_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `acl_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acl_roles_accion`
--

DROP TABLE IF EXISTS `acl_roles_accion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acl_roles_accion` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ACCION` varchar(255) DEFAULT NULL,
  `ELIMINADO` smallint(6) DEFAULT NULL,
  `ROL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acl_roles_accion`
--

LOCK TABLES `acl_roles_accion` WRITE;
/*!40000 ALTER TABLE `acl_roles_accion` DISABLE KEYS */;
/*!40000 ALTER TABLE `acl_roles_accion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acl_roles_usuarios`
--

DROP TABLE IF EXISTS `acl_roles_usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acl_roles_usuarios` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ELIMINADO` smallint(6) DEFAULT NULL,
  `USUARIO` varchar(255) DEFAULT NULL,
  `rol` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_acl_roles_usuarios_rol` (`rol`),
  CONSTRAINT `FK_acl_roles_usuarios_rol` FOREIGN KEY (`rol`) REFERENCES `acl_roles` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acl_roles_usuarios`
--

LOCK TABLES `acl_roles_usuarios` WRITE;
/*!40000 ALTER TABLE `acl_roles_usuarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `acl_roles_usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `atributos`
--

DROP TABLE IF EXISTS `atributos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `atributos` (
  `ID` varchar(255) NOT NULL,
  `ELIMINADO` smallint(6) DEFAULT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `atributos`
--

LOCK TABLES `atributos` WRITE;
/*!40000 ALTER TABLE `atributos` DISABLE KEYS */;
/*!40000 ALTER TABLE `atributos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categorias` (
  `ID` varchar(255) NOT NULL,
  `ELIMINADO` smallint(6) DEFAULT NULL,
  `IMAGEN` longblob,
  `MODULO` varchar(255) DEFAULT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL,
  `idpadre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_categorias_idpadre` (`idpadre`),
  CONSTRAINT `FK_categorias_idpadre` FOREIGN KEY (`idpadre`) REFERENCES `categorias` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES ('23cdafe1-c190-48e6-944a-fc4766f63c50',0,NULL,'PRODUCTOS','MADERA',NULL),('3645a47f-095c-44c4-9bb9-17daf3f15a71',0,NULL,'PRODUCTOS','ENTREPAÑO','392f989c-8534-4b44-9e3e-1c2b754c9139'),('36e5cd7d-1d87-49fa-9f94-7813973efc78',0,NULL,'PRODUCTOS','PUESTOS DE PAGO','5f6a78bf-b826-4bbd-a11f-ad7f82917866'),('392f989c-8534-4b44-9e3e-1c2b754c9139',0,NULL,'PRODUCTOS','GONDOLAS','5f6a78bf-b826-4bbd-a11f-ad7f82917866'),('5f6a78bf-b826-4bbd-a11f-ad7f82917866',0,NULL,'PRODUCTOS','SUPERMERCADOS','6b4c4e60-1d01-4660-a0a8-92eba3933dd6'),('633c5eb3-1155-44a2-9785-4924747dd06a',1,NULL,'PRODUCTOS','METALICO','92701bd1-0759-43e3-836c-e6cb6f13f3b7'),('67ecf673-bfc3-4dc6-b576-8c6883142be9',1,NULL,'PRODUCTOS','GONDOLA',NULL),('6b4c4e60-1d01-4660-a0a8-92eba3933dd6',0,NULL,'PRODUCTOS','METAL',NULL),('8e465b94-1350-4193-bdd3-922eb37f1303',1,NULL,'PRODUCTOS','MADERA','92701bd1-0759-43e3-836c-e6cb6f13f3b7'),('92701bd1-0759-43e3-836c-e6cb6f13f3b7',1,NULL,'PRODUCTOS','METALICO',NULL),('93bce9e2-5038-4687-af8c-0305dd8e5655',1,NULL,'PRODUCTOS','LAMINA 2 X 3','92701bd1-0759-43e3-836c-e6cb6f13f3b7'),('a7efdb57-3122-4b6a-97f4-7553b49c3f19',1,NULL,'PRODUCTOS','PRODUCTOS DE VENTA',NULL),('a8dc0815-a30a-4dc1-a51f-9925c6e8727b',0,NULL,'PRODUCTOS','BOTADEROS','5f6a78bf-b826-4bbd-a11f-ad7f82917866'),('a8fc8306-a10e-42d6-a522-9b87cae0e630',1,NULL,'PRODUCTOS','INSUMO   XXX','92701bd1-0759-43e3-836c-e6cb6f13f3b7'),('c7afcdff-cdb6-4760-8d9b-fd432897ebd5',1,NULL,'PRODUCTOS','POS','a7efdb57-3122-4b6a-97f4-7553b49c3f19'),('d14d5e03-aa5e-4508-b1e5-c2326b01aff5',1,NULL,'PRODUCTOS','GONDOLA ESPECIAL','67ecf673-bfc3-4dc6-b576-8c6883142be9'),('e3328939-fc0a-4a1c-b397-670874e60f48',1,NULL,'PRODUCTOS','insumo xy',NULL),('f902bf18-b91d-46c3-8300-8e96f51df3ec',1,NULL,'PRODUCTOS','madera BALSO','8e465b94-1350-4193-bdd3-922eb37f1303'),('fc8c280a-0597-41f3-9079-f631ad74f9bc',1,NULL,'PRODUCTOS','BOTADERO','6b4c4e60-1d01-4660-a0a8-92eba3933dd6');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categorias_impuestos`
--

DROP TABLE IF EXISTS `categorias_impuestos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categorias_impuestos` (
  `ID` varchar(255) NOT NULL,
  `ELIMINADO` smallint(6) DEFAULT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias_impuestos`
--

LOCK TABLES `categorias_impuestos` WRITE;
/*!40000 ALTER TABLE `categorias_impuestos` DISABLE KEYS */;
/*!40000 ALTER TABLE `categorias_impuestos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuentas`
--

DROP TABLE IF EXISTS `cuentas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuentas` (
  `id` varchar(255) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `tipo_cliente` varchar(255) DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `telefono` varchar(10) DEFAULT NULL,
  `telefono_sec` varchar(10) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `lista_precios` varchar(255) DEFAULT NULL,
  `creado_por` varchar(255) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `modificado_por` varchar(255) DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `eliminado` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cuentas_estado` (`estado`),
  KEY `FK_cuentas_lista_precios` (`lista_precios`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuentas`
--

LOCK TABLES `cuentas` WRITE;
/*!40000 ALTER TABLE `cuentas` DISABLE KEYS */;
INSERT INTO `cuentas` VALUES ('900615645.5','EXHIBICIONES Y DISEÑOS S.A.S.',NULL,'CL 34 66 A 58','2653706','','ACTIVA',NULL,'1020413761','2016-07-05 10:35:31','1020413761','2016-07-05 10:59:44',0);
/*!40000 ALTER TABLE `cuentas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuentas_contactos`
--

DROP TABLE IF EXISTS `cuentas_contactos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuentas_contactos` (
  `id_persona` varchar(255) DEFAULT NULL,
  `id_cuenta` varchar(255) DEFAULT NULL,
  `CARGO` varchar(255) DEFAULT NULL,
  `ELIMINADO` smallint(6) DEFAULT NULL,
  KEY `FK_cuentas_contactos_id_cliente` (`id_cuenta`),
  KEY `FK_cuentas_contactos_id_persona` (`id_persona`),
  CONSTRAINT `FK_cuentas_contactos_id_cuenta` FOREIGN KEY (`id_cuenta`) REFERENCES `cuentas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_cuentas_contactos_id_persona` FOREIGN KEY (`id_persona`) REFERENCES `personas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuentas_contactos`
--

LOCK TABLES `cuentas_contactos` WRITE;
/*!40000 ALTER TABLE `cuentas_contactos` DISABLE KEYS */;
INSERT INTO `cuentas_contactos` VALUES ('70569547','900615645.5','AP_GER',0);
/*!40000 ALTER TABLE `cuentas_contactos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuentas_direccion`
--

DROP TABLE IF EXISTS `cuentas_direccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuentas_direccion` (
  `ID` varchar(255) NOT NULL,
  `DIRECCION` varchar(255) DEFAULT NULL,
  `ELIMINADO` smallint(6) DEFAULT NULL,
  `id_cliente` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_cuentas_direccion_id_cliente` (`id_cliente`),
  CONSTRAINT `FK_cuentas_direccion_id_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `cuentas` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuentas_direccion`
--

LOCK TABLES `cuentas_direccion` WRITE;
/*!40000 ALTER TABLE `cuentas_direccion` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuentas_direccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estados`
--

DROP TABLE IF EXISTS `estados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estados` (
  `ID` varchar(255) NOT NULL,
  `ELIMINADO` smallint(6) DEFAULT NULL,
  `ESTADO` varchar(255) DEFAULT NULL,
  `MODULO` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estados`
--

LOCK TABLES `estados` WRITE;
/*!40000 ALTER TABLE `estados` DISABLE KEYS */;
INSERT INTO `estados` VALUES ('06afb827-2cb4-4e08-abe4-757ba0dc69b5',0,'TERMINADA','ORDERS'),('13625479-9841-4047-adad-9741eed735d3',0,'SOLICITADA','ORDERS'),('1dcd7183-2c8d-4379-9b50-225a647283a0',0,'PAGADA','ORDERS'),('226fd06d-05eb-40d5-8c04-ee0f0a2e277a',0,'ACEPTADA','ORDERS'),('6228d8d2-7456-43ce-b7ba-47fabc2a142f',0,'FINALIZADA','ORDERS'),('8d88996b-464d-46db-900e-e24bae8508fa',0,'ENTREGADA','ORDERS'),('a400bb03-1310-4b12-89fd-0e62bdc792ea',0,'ENTREGA PARCIAL','ORDERS'),('a861b528-b9fc-4876-90d8-9e7a6dd33251',0,'EN PROCESO','ORDERS'),('ACTIVA',0,'ACTIVA','ACCOUNTS'),('bdb2e05f-ec7d-492b-9c36-43722c40a9e7',1,'SOLICITADA','ORDERS'),('INACTIVA',0,'INACTIVA','ACCOUNTS');
/*!40000 ALTER TABLE `estados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `impuestos`
--

DROP TABLE IF EXISTS `impuestos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `impuestos` (
  `ID` varchar(255) NOT NULL,
  `CATEGORIA` varchar(255) DEFAULT NULL,
  `creado_por` varchar(255) DEFAULT NULL,
  `ELIMINADO` smallint(6) DEFAULT NULL,
  `modificado_por` varchar(255) DEFAULT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL,
  `TARIFA` double DEFAULT NULL,
  `valido_desde` datetime DEFAULT NULL,
  `impuesto_padre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_impuestos_impuesto_padre` (`impuesto_padre`),
  CONSTRAINT `FK_impuestos_impuesto_padre` FOREIGN KEY (`impuesto_padre`) REFERENCES `impuestos` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `impuestos`
--

LOCK TABLES `impuestos` WRITE;
/*!40000 ALTER TABLE `impuestos` DISABLE KEYS */;
/*!40000 ALTER TABLE `impuestos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lineas_impuestos`
--

DROP TABLE IF EXISTS `lineas_impuestos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lineas_impuestos` (
  `ID` varchar(255) NOT NULL,
  `BASE` double DEFAULT NULL,
  `ELIMINADO` smallint(6) DEFAULT NULL,
  `IMPUESTO` varchar(255) DEFAULT NULL,
  `MONTO` double DEFAULT NULL,
  `RECIBO` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lineas_impuestos`
--

LOCK TABLES `lineas_impuestos` WRITE;
/*!40000 ALTER TABLE `lineas_impuestos` DISABLE KEYS */;
/*!40000 ALTER TABLE `lineas_impuestos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lista_precios`
--

DROP TABLE IF EXISTS `lista_precios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lista_precios` (
  `ID` varchar(255) NOT NULL,
  `ELIMINADO` smallint(6) DEFAULT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL,
  `PORCENTAJE` tinyint(1) DEFAULT '0',
  `VALOR_PORCENTAJE` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lista_precios`
--

LOCK TABLES `lista_precios` WRITE;
/*!40000 ALTER TABLE `lista_precios` DISABLE KEYS */;
/*!40000 ALTER TABLE `lista_precios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagos`
--

DROP TABLE IF EXISTS `pagos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pagos` (
  `ID` varchar(255) NOT NULL,
  `ELIMINADO` smallint(6) DEFAULT NULL,
  `id_trans` varchar(255) DEFAULT NULL,
  `PAGO` varchar(255) DEFAULT NULL,
  `RECIBO` varchar(255) DEFAULT NULL,
  `TOTAL` double DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagos`
--

LOCK TABLES `pagos` WRITE;
/*!40000 ALTER TABLE `pagos` DISABLE KEYS */;
/*!40000 ALTER TABLE `pagos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parametros`
--

DROP TABLE IF EXISTS `parametros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parametros` (
  `ID` varchar(255) NOT NULL,
  `CLAVE1` varchar(255) DEFAULT NULL,
  `CLAVE2` varchar(255) DEFAULT NULL,
  `ELIMINADO` smallint(6) DEFAULT NULL,
  `PARAMETRO` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parametros`
--

LOCK TABLES `parametros` WRITE;
/*!40000 ALTER TABLE `parametros` DISABLE KEYS */;
INSERT INTO `parametros` VALUES ('09f23661-2537-4ebb-b397-b6d01cc38c68','RM','424',0,'CONF_REMISSIONS'),('86b0b849-4f2a-410a-8540-618608983eaf','OC','156',0,'CONF_ORDERS'),('AP_ASI','ASISTENTE',NULL,0,'ACCOUNTS_POSITION'),('AP_COM','COMERCIAL',NULL,0,'ACCOUNTS_POSITION'),('AP_DIR','DIRECTOR',NULL,0,'ACCOUNTS_POSITION'),('AP_GER','GERENTE',NULL,0,'ACCOUNTS_POSITION'),('AP_OPE','OPERARIO',NULL,0,'ACCOUNTS_POSITION'),('AP_SEC','SECRETEARIA',NULL,0,'ACCOUNTS_POSITION'),('AP_TEC','TECNICO',NULL,0,'ACCOUNTS_POSITION'),('ID_C','CEDULA',NULL,0,'IDENTIFICATION'),('ID_NIT','NIT',NULL,0,'IDENTIFICATION'),('PRODUCT_TYPE1','PRODUCTO DE VENTA',NULL,0,'PRODUCT_TYPE'),('PRODUCT_TYPE2','INSUMO',NULL,0,'PRODUCT_TYPE');
/*!40000 ALTER TABLE `parametros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedidos` (
  `id` varchar(255) NOT NULL,
  `cantidad_pendientes` int(11) DEFAULT NULL,
  `cantidad_total` int(11) DEFAULT NULL,
  `creado_por` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `eliminado` smallint(6) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `fecha_entrega` date DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `fecha_pedido` date DEFAULT NULL,
  `modificado_por` varchar(255) DEFAULT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  `referencia` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `id_cliente` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_pedidos_id_cliente` (`id_cliente`),
  KEY `FK_pedidos_estado` (`estado`),
  CONSTRAINT `FK_pedidos_estado` FOREIGN KEY (`estado`) REFERENCES `estados` (`ID`),
  CONSTRAINT `FK_pedidos_id_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `cuentas` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
INSERT INTO `pedidos` VALUES ('04baa1b4-88a4-4565-8b55-aa4acd3e0290',0,9,'1020413761','',0,'2016-07-06 06:14:13','2016-01-25','2016-07-06 06:14:13','2016-01-25','1020413761','','OC00015','8d88996b-464d-46db-900e-e24bae8508fa','900615645.5'),('11ae1769-38b8-42d3-8b13-8277cfd2d93a',0,308,'1020413761','',0,'2016-07-05 22:41:21',NULL,'2016-07-05 22:41:21','2016-01-04','1020413761','','OC00001','6228d8d2-7456-43ce-b7ba-47fabc2a142f','900615645.5'),('3b506903-c873-4432-820c-fc6a50388588',0,24,'1020413761','',0,'2016-07-06 06:00:43','2016-01-12','2016-07-06 06:00:43','2016-01-12','1020413761','','OC00005','8d88996b-464d-46db-900e-e24bae8508fa','900615645.5'),('48064538-d4e9-47c9-a3de-edac081bef9a',0,210,'1020413761','',0,'2016-07-06 12:03:07','2016-07-06','2016-07-06 12:03:07','2016-07-06','1020413761','Se entrega de la mercancia en buen estado, Despacho Bodega Britalia.','OC00156','8d88996b-464d-46db-900e-e24bae8508fa','900615645.5'),('48bb2179-a1bf-4687-b3f6-3d30636ff531',0,6,'1020413761','',0,'2016-07-06 06:01:49','2016-01-13','2016-07-06 06:01:49','2016-01-13','1020413761','','OC00006','8d88996b-464d-46db-900e-e24bae8508fa','900615645.5'),('5c5fb5b2-eebc-4ab0-bb32-96733b4a8d0b',0,2,'1020413761','',0,'2016-07-06 06:08:10','2016-01-20','2016-07-06 06:08:10','2016-01-20','1020413761','','OC00010','8d88996b-464d-46db-900e-e24bae8508fa','900615645.5'),('8b2c7f28-364b-4647-8fb7-90d005ec2622',0,10,'1020413761','',0,'2016-07-06 06:11:35','2016-01-21','2016-07-06 06:11:35','2016-01-21','1020413761','','OC00013','8d88996b-464d-46db-900e-e24bae8508fa','900615645.5'),('8cd8eeb0-fba0-44ef-8289-1d263eb4da05',0,2,'1020413761','',0,'2016-07-06 06:07:36','2016-01-19','2016-07-06 06:07:36','2016-01-19','1020413761','','OC00009','8d88996b-464d-46db-900e-e24bae8508fa','900615645.5'),('90d8ef64-7262-4078-ad68-807d188985d7',0,110,'1020413761','',0,'2016-07-06 06:05:36','2016-01-16','2016-07-06 06:05:36','2016-01-16','1020413761','Esta se Remite con otra orden que tiene reprocesos.','OC00007','8d88996b-464d-46db-900e-e24bae8508fa','900615645.5'),('9734086e-bfc9-4909-8f3f-8cace3e4a54d',0,6,'1020413761','',0,'2016-07-06 06:06:58','2016-01-19','2016-07-06 06:06:58','2016-01-19','1020413761','','OC00008','8d88996b-464d-46db-900e-e24bae8508fa','900615645.5'),('b7b162db-afef-455b-9aaa-012d3335e7c3',0,38,'1020413761','',0,'2016-07-05 23:49:22','2016-01-07','2016-07-05 23:49:22','2016-01-07','1020413761','','OC00002','06afb827-2cb4-4e08-abe4-757ba0dc69b5','900615645.5'),('cd780ff2-7251-43b4-8c12-abf694102552',0,30,'1020413761','',0,'2016-07-05 10:53:02','2016-07-01','2016-07-05 10:53:02','2016-07-01','1020413761','Se hace entrega de la mercancía en buen estado.','OC00155','8d88996b-464d-46db-900e-e24bae8508fa','900615645.5'),('d9e9e076-afee-4b1d-88ab-e211a6b169a1',0,24,'1020413761','',0,'2016-07-06 06:10:43','2016-01-21','2016-07-06 06:10:43','2016-01-21','1020413761','','OC00012','8d88996b-464d-46db-900e-e24bae8508fa','900615645.5'),('dd5f1bd8-c6fb-4395-9f57-7162fd67eaa7',0,15,'1020413761','',0,'2016-07-06 05:54:39',NULL,'2016-07-06 05:54:39','2016-01-08','1020413761','','OC00003','8d88996b-464d-46db-900e-e24bae8508fa','900615645.5'),('e56995cb-9f24-4bbe-b099-7c22239732bc',0,23,'1020413761','',0,'2016-07-06 06:12:50','2016-01-21','2016-07-06 06:12:50','2016-01-21','1020413761','','OC00014','8d88996b-464d-46db-900e-e24bae8508fa','900615645.5'),('ee46d986-e333-4c83-8826-ef106a858504',0,30,'1020413761','',0,'2016-07-06 06:09:38','2016-01-21','2016-07-06 06:09:38','2016-01-21','1020413761','','OC00011','8d88996b-464d-46db-900e-e24bae8508fa','900615645.5'),('f466365f-e96d-4a5b-b19d-6c7703cbce5a',0,132,'1020413761','',0,'2016-07-06 05:59:23','2016-01-12','2016-07-06 05:59:23','2016-01-12','1020413761','','OC00004','8d88996b-464d-46db-900e-e24bae8508fa','900615645.5');
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidos_producto`
--

DROP TABLE IF EXISTS `pedidos_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedidos_producto` (
  `ID` varchar(255) NOT NULL,
  `CANTIDAD` int(11) DEFAULT NULL,
  `cantidad_entregada` int(11) DEFAULT '0',
  `ELIMINADO` smallint(6) DEFAULT NULL,
  `valor_total` double DEFAULT NULL,
  `valor_unitario` double DEFAULT NULL,
  `id_pedido` varchar(255) DEFAULT NULL,
  `id_producto` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_pedidos_producto_id_producto` (`id_producto`),
  KEY `FK_pedidos_producto_id_pedido` (`id_pedido`),
  CONSTRAINT `FK_pedidos_producto_id_pedido` FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id`),
  CONSTRAINT `FK_pedidos_producto_id_producto` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos_producto`
--

LOCK TABLES `pedidos_producto` WRITE;
/*!40000 ALTER TABLE `pedidos_producto` DISABLE KEYS */;
INSERT INTO `pedidos_producto` VALUES ('0415cb5d-0920-4fff-b437-042d26f2bc54',2,2,0,200,100,'8cd8eeb0-fba0-44ef-8289-1d263eb4da05','5664c1d8-223c-4436-9e4c-ea082069e842'),('04de9bbe-b0b4-4a01-aa94-f29135505d67',2,2,0,200,100,'9734086e-bfc9-4909-8f3f-8cace3e4a54d','a2411366-d135-4114-bcc5-369a737c1c85'),('0e7a93a3-8503-4983-8aa1-ef36f347908a',4,0,0,200,100,'11ae1769-38b8-42d3-8b13-8277cfd2d93a','280edafb-0d8a-4e84-ab37-e8092d2703e1'),('1123dc55-3771-4730-b114-9fe5fcd53631',10,10,0,1000,100,'cd780ff2-7251-43b4-8c12-abf694102552','5664c1d8-223c-4436-9e4c-ea082069e842'),('1d83c509-529a-4797-9c72-60c50225c095',44,44,0,4400,100,'f466365f-e96d-4a5b-b19d-6c7703cbce5a','5664c1d8-223c-4436-9e4c-ea082069e842'),('1dde978a-8253-4163-9289-25d2b848609e',10,10,0,1000,100,'b7b162db-afef-455b-9aaa-012d3335e7c3','5664c1d8-223c-4436-9e4c-ea082069e842'),('1e2988e5-7e51-4373-8d57-6862e2c2a734',8,8,0,800,100,'b7b162db-afef-455b-9aaa-012d3335e7c3','8edcbd34-99e3-4bf0-8da7-24816d97e8f2'),('27d74796-f4a1-4b29-8c86-2d0c926f9b39',5,5,0,500,100,'dd5f1bd8-c6fb-4395-9f57-7162fd67eaa7','5664c1d8-223c-4436-9e4c-ea082069e842'),('2df7f8a8-7118-4e19-87ac-9d84c958f01e',2,2,0,200,100,'5c5fb5b2-eebc-4ab0-bb32-96733b4a8d0b','280edafb-0d8a-4e84-ab37-e8092d2703e1'),('31fe33b2-6e8d-4509-92e8-0b0f15467211',50,0,0,5000,100,'11ae1769-38b8-42d3-8b13-8277cfd2d93a','a2b044be-b95e-4d6f-9ced-b2370001c891'),('33f4b7fc-ebcd-42aa-a92a-2e18d6dc2aac',86,86,0,8600,100,'90d8ef64-7262-4078-ad68-807d188985d7','79b1052d-bcb1-469f-8748-5f4bc69ae97a'),('3c9db0e4-894f-4a72-acc4-e8a6df7a9218',10,10,0,1000,100,'ee46d986-e333-4c83-8826-ef106a858504','a2411366-d135-4114-bcc5-369a737c1c85'),('4dfd2d70-c4f2-4cd6-9cf6-3fbf268884f7',33,0,0,3300,100,'11ae1769-38b8-42d3-8b13-8277cfd2d93a','a2411366-d135-4114-bcc5-369a737c1c85'),('4ecf1442-9eac-432e-89ab-a85971cc3560',16,16,0,1600,100,'3b506903-c873-4432-820c-fc6a50388588','5664c1d8-223c-4436-9e4c-ea082069e842'),('6091ac52-a152-4347-9149-6d2f078d5a8e',20,20,0,2000,100,'e56995cb-9f24-4bbe-b099-7c22239732bc','280edafb-0d8a-4e84-ab37-e8092d2703e1'),('63c80ef2-1c56-4b4b-987a-90092f892f93',17,0,0,1700,100,'11ae1769-38b8-42d3-8b13-8277cfd2d93a','06cb9820-68c7-4cbd-a2f2-fd195e789100'),('64c1e99f-45aa-4529-a2ea-86ac5df38df3',20,20,0,2000,100,'48064538-d4e9-47c9-a3de-edac081bef9a','a2b044be-b95e-4d6f-9ced-b2370001c891'),('6ed06e75-3cd9-40c4-8341-657f5134b344',10,10,0,1000,100,'cd780ff2-7251-43b4-8c12-abf694102552','a2b044be-b95e-4d6f-9ced-b2370001c891'),('7060ea59-2751-4f59-9045-38032ecda8fe',67,0,0,5800,100,'11ae1769-38b8-42d3-8b13-8277cfd2d93a','79b1052d-bcb1-469f-8748-5f4bc69ae97a'),('7388fe6b-9a3d-42cc-8d8a-976a9752bfb1',4,4,0,400,100,'9734086e-bfc9-4909-8f3f-8cace3e4a54d','5664c1d8-223c-4436-9e4c-ea082069e842'),('76f24f01-c074-4ef4-8912-e19b3702f1b0',10,10,0,1000,100,'cd780ff2-7251-43b4-8c12-abf694102552','79b1052d-bcb1-469f-8748-5f4bc69ae97a'),('7afb85cd-5330-4907-8902-f92a950c07f5',50,50,0,5000,100,'48064538-d4e9-47c9-a3de-edac081bef9a','a2411366-d135-4114-bcc5-369a737c1c85'),('7bd5d95f-0209-4385-affb-2234f31740df',3,3,0,300,100,'e56995cb-9f24-4bbe-b099-7c22239732bc','c4a68db1-dfb0-402f-8fb6-8a6c013f5b9e'),('7c12e9c1-6388-4423-866e-e1817692133c',2,2,0,200,100,'b7b162db-afef-455b-9aaa-012d3335e7c3','e72d8a91-3f7d-40e2-8609-be2c41077919'),('8517fc44-9af6-4b5b-9760-6cfdfea4e10f',24,24,0,2400,100,'90d8ef64-7262-4078-ad68-807d188985d7','a2411366-d135-4114-bcc5-369a737c1c85'),('92b016d5-dec9-4d98-b78d-e563b2ef110f',10,10,0,1000,100,'8b2c7f28-364b-4647-8fb7-90d005ec2622','5664c1d8-223c-4436-9e4c-ea082069e842'),('a2f6a022-538d-4c23-bf39-c51304dd18f7',20,20,0,2000,100,'48064538-d4e9-47c9-a3de-edac081bef9a','79b1052d-bcb1-469f-8748-5f4bc69ae97a'),('a66e9652-edae-4d9c-89b6-5acedb5fa5ca',1,1,0,100,100,'dd5f1bd8-c6fb-4395-9f57-7162fd67eaa7','79b1052d-bcb1-469f-8748-5f4bc69ae97a'),('a9c17c2d-f971-4972-bd73-19f61a67c4b1',9,9,0,900,100,'04baa1b4-88a4-4565-8b55-aa4acd3e0290','c4a68db1-dfb0-402f-8fb6-8a6c013f5b9e'),('adc3b874-ae2f-4766-b7e2-ae43d41f9eef',120,120,0,12000,100,'48064538-d4e9-47c9-a3de-edac081bef9a','5664c1d8-223c-4436-9e4c-ea082069e842'),('b1c7eb10-a129-4749-a169-25bc3efb5db0',8,8,0,800,100,'d9e9e076-afee-4b1d-88ab-e211a6b169a1','a2411366-d135-4114-bcc5-369a737c1c85'),('b59ec0b7-9c86-47d9-90fd-36084c89d331',44,44,0,4400,100,'f466365f-e96d-4a5b-b19d-6c7703cbce5a','a2b044be-b95e-4d6f-9ced-b2370001c891'),('b613e989-ee42-43cc-8a38-2c8eae1bace2',6,6,0,600,100,'48bb2179-a1bf-4687-b3f6-3d30636ff531','280edafb-0d8a-4e84-ab37-e8092d2703e1'),('b6268e5b-7411-487f-8703-9b638195b6a2',18,18,0,1800,100,'b7b162db-afef-455b-9aaa-012d3335e7c3','82289b0a-9159-4a10-93fe-4c0559ade658'),('c20c24f7-3d2d-4407-b899-34dfc3b019a5',20,20,0,2000,100,'ee46d986-e333-4c83-8826-ef106a858504','5664c1d8-223c-4436-9e4c-ea082069e842'),('d7a42b83-ccf5-4881-8bd5-756eb8ce38a7',4,4,0,400,100,'dd5f1bd8-c6fb-4395-9f57-7162fd67eaa7','0ee4a9e7-cb2c-4b14-bdbe-00622eeebf1d'),('d7e16751-ef96-497d-ba43-df16c5eb981a',137,0,0,13700,100,'11ae1769-38b8-42d3-8b13-8277cfd2d93a','5664c1d8-223c-4436-9e4c-ea082069e842'),('e34cd948-31ba-4060-8a4c-467d42bb7c82',5,5,0,500,100,'dd5f1bd8-c6fb-4395-9f57-7162fd67eaa7','a2b044be-b95e-4d6f-9ced-b2370001c891'),('e53ee8d4-49e2-4c77-b8c8-6d03f7c65bf5',44,44,0,4400,100,'f466365f-e96d-4a5b-b19d-6c7703cbce5a','79b1052d-bcb1-469f-8748-5f4bc69ae97a'),('f1b0fb57-dc61-4f97-be7f-e7dee0b31bb7',8,8,0,800,100,'3b506903-c873-4432-820c-fc6a50388588','a2411366-d135-4114-bcc5-369a737c1c85'),('f2225c62-7917-41ea-b130-7b2fdf8130cc',16,16,0,1600,100,'d9e9e076-afee-4b1d-88ab-e211a6b169a1','5664c1d8-223c-4436-9e4c-ea082069e842');
/*!40000 ALTER TABLE `pedidos_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona_dirs`
--

DROP TABLE IF EXISTS `persona_dirs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persona_dirs` (
  `ID` varchar(255) NOT NULL,
  `DIRECCION` varchar(255) DEFAULT NULL,
  `ELIMINADO` smallint(6) DEFAULT NULL,
  `id_persona` int(11) DEFAULT NULL,
  `OBSERVACION` varchar(255) DEFAULT NULL,
  `PRINCIPAL` tinyint(1) DEFAULT '0',
  `TIPO` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona_dirs`
--

LOCK TABLES `persona_dirs` WRITE;
/*!40000 ALTER TABLE `persona_dirs` DISABLE KEYS */;
/*!40000 ALTER TABLE `persona_dirs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona_mails`
--

DROP TABLE IF EXISTS `persona_mails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persona_mails` (
  `ID` varchar(255) NOT NULL,
  `ELIMINADO` datetime DEFAULT NULL,
  `id_persona` varchar(255) DEFAULT NULL,
  `MAIL` varchar(255) DEFAULT NULL,
  `PRINCIPAL` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona_mails`
--

LOCK TABLES `persona_mails` WRITE;
/*!40000 ALTER TABLE `persona_mails` DISABLE KEYS */;
/*!40000 ALTER TABLE `persona_mails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personas`
--

DROP TABLE IF EXISTS `personas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personas` (
  `id` varchar(255) NOT NULL,
  `apellido1` varchar(255) DEFAULT NULL,
  `apellido2` varchar(255) DEFAULT NULL,
  `eliminado` smallint(6) DEFAULT NULL,
  `fecha_nacim` date DEFAULT NULL,
  `nombre1` varchar(255) DEFAULT NULL,
  `nombre2` varchar(255) DEFAULT NULL,
  `sexo` varchar(255) DEFAULT NULL,
  `tipo_documento` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personas`
--

LOCK TABLES `personas` WRITE;
/*!40000 ALTER TABLE `personas` DISABLE KEYS */;
INSERT INTO `personas` VALUES ('10204','Higuita','Delgado',0,NULL,'Jeferson','','M','ID_C'),('1020413761','Higuita','Delgado',0,'1988-02-11','Juliana','','F','ID_C'),('12346','Dairo','',0,'2016-06-01','Dairo','','M','ID_C'),('13','adskñkñasd','',0,NULL,'hcetor','','M','ID_C'),('70569547','LONDOÑO','ARISTISABAL',0,NULL,'DAIRO','ALFONSO','M','ID_C'),('asd','asd','sad',0,'2016-07-19','asd','asd','M','ID_C');
/*!40000 ALTER TABLE `personas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personas_tels`
--

DROP TABLE IF EXISTS `personas_tels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personas_tels` (
  `id` varchar(255) NOT NULL,
  `eliminado` smallint(6) DEFAULT NULL,
  `extension` varchar(255) DEFAULT NULL,
  `id_persona` varchar(255) DEFAULT NULL,
  `principal` tinyint(1) DEFAULT '0',
  `telefono` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personas_tels`
--

LOCK TABLES `personas_tels` WRITE;
/*!40000 ALTER TABLE `personas_tels` DISABLE KEYS */;
/*!40000 ALTER TABLE `personas_tels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productos` (
  `id` varchar(255) NOT NULL,
  `atributo` varchar(255) DEFAULT NULL,
  `categoria_impuesto` varchar(255) DEFAULT NULL,
  `codigo` varchar(255) DEFAULT NULL,
  `creado_por` varchar(255) DEFAULT NULL,
  `eliminado` smallint(6) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `modificado_por` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  `precio_compra` double DEFAULT NULL,
  `precio_venta` double DEFAULT NULL,
  `referencia` varchar(255) DEFAULT NULL,
  `stock` double DEFAULT NULL,
  `tipo_codigo` varchar(255) DEFAULT NULL,
  `categoria` varchar(255) DEFAULT NULL,
  `unidad_medida` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_productos_categoria` (`categoria`),
  KEY `FK_unidad_medida` (`unidad_medida`),
  CONSTRAINT `FK_productos_categoria` FOREIGN KEY (`categoria`) REFERENCES `categorias` (`ID`),
  CONSTRAINT `FK_unidades_medida` FOREIGN KEY (`unidad_medida`) REFERENCES `unidades_medida` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES ('06559bfb-083e-4546-aac7-d29861241361',NULL,NULL,NULL,'1020413761',0,'2016-07-04 21:51:48','2016-07-04 21:51:48','1020413761','Gondola Especial de 135 x 120 cm pata alta','',0,100,'GON13512',0,'PRODUCT_TYPE1','392f989c-8534-4b44-9e3e-1c2b754c9139','7d8c25bb-358f-11e6-a718-507b9d9f0aad'),('06cb9820-68c7-4cbd-a2f2-fd195e789100',NULL,NULL,NULL,'1020413761',0,'2016-07-05 22:28:57','2016-07-05 22:28:57','1020413761','Gondola de 110 x 120 cm sin espaldar','',0,100,'GON110120',0,'PRODUCT_TYPE1','392f989c-8534-4b44-9e3e-1c2b754c9139','7d8c25bb-358f-11e6-a718-507b9d9f0aad'),('0ee4a9e7-cb2c-4b14-bdbe-00622eeebf1d',NULL,NULL,NULL,'1020413761',0,'2016-07-06 05:51:58','2016-07-06 05:51:58','1020413761','Entrepaño central de 135 x 45 cm','',0,100,'ENT13545',0,'PRODUCT_TYPE1','3645a47f-095c-44c4-9bb9-17daf3f15a71','7d8c25bb-358f-11e6-a718-507b9d9f0aad'),('11fc17d7-98d4-46d0-9cb0-4714b9c4248d',NULL,NULL,NULL,'1020413761',1,'2016-07-04 21:52:21','2016-07-05 10:44:34','1020413761','Entrepaño central de 120 cm','',0,100,'ENT120',0,'PRODUCT_TYPE1','3645a47f-095c-44c4-9bb9-17daf3f15a71','7d8c25bb-358f-11e6-a718-507b9d9f0aad'),('280edafb-0d8a-4e84-ab37-e8092d2703e1',NULL,NULL,NULL,'1020413761',0,'2016-07-05 22:28:00','2016-07-05 22:28:00','1020413761','PUESTO DE PAGO','',0,100,'PPAG',0,'PRODUCT_TYPE1','36e5cd7d-1d87-49fa-9f94-7813973efc78','7d8c25bb-358f-11e6-a718-507b9d9f0aad'),('4e5041ce-8ec1-480a-8511-59dec4981296',NULL,NULL,NULL,'1020413761',0,'2016-07-04 20:18:06','2016-07-04 20:18:06','1020413761','Gondola Especial de 135 x 110 cm','',0,100,'GON135',0,'PRODUCT_TYPE1','392f989c-8534-4b44-9e3e-1c2b754c9139','7d8c25bb-358f-11e6-a718-507b9d9f0aad'),('5664c1d8-223c-4436-9e4c-ea082069e842',NULL,NULL,NULL,'1020413761',0,'2016-07-05 10:45:14','2016-07-05 10:45:14','1020413761','Entrepaño Central de 120 x 45 cm','',0,100,'ENT12045',0,'PRODUCT_TYPE1','3645a47f-095c-44c4-9bb9-17daf3f15a71','7d8c25bb-358f-11e6-a718-507b9d9f0aad'),('61e5de09-af29-46dd-8639-f8ab72f78b70',NULL,NULL,NULL,'1020413761',0,'2016-07-04 21:54:58','2016-07-04 21:54:58','1020413761','Gondola de 135 x 90 cm','',0,100,'GON13590',0,'PRODUCT_TYPE1','392f989c-8534-4b44-9e3e-1c2b754c9139','7d8c25bb-358f-11e6-a718-507b9d9f0aad'),('702d13eb-abda-4b08-bf57-46178958e391',NULL,NULL,NULL,'1020413761',0,'2016-07-04 21:53:26','2016-07-04 21:53:26','1020413761','Entrepaño Central de 90 x 45 cm','',0,100,'ENT9045',0,'PRODUCT_TYPE1','3645a47f-095c-44c4-9bb9-17daf3f15a71','7d8c25bb-358f-11e6-a718-507b9d9f0aad'),('79b1052d-bcb1-469f-8748-5f4bc69ae97a',NULL,NULL,NULL,'1020413761',0,'2016-07-05 10:45:55','2016-07-05 10:45:55','1020413761','Entrepaño de Piso de 120 x 55 cm','',0,100,'ENTP12055',0,'PRODUCT_TYPE1','3645a47f-095c-44c4-9bb9-17daf3f15a71','7d8c25bb-358f-11e6-a718-507b9d9f0aad'),('82289b0a-9159-4a10-93fe-4c0559ade658',NULL,NULL,NULL,'1020413761',0,'2016-07-05 23:47:48','2016-07-05 23:47:48','1020413761','Cremallera pared 2 M','',0,100,'CRM2',0,'PRODUCT_TYPE1','392f989c-8534-4b44-9e3e-1c2b754c9139','7d8c25bb-358f-11e6-a718-507b9d9f0aad'),('8edcbd34-99e3-4bf0-8da7-24816d97e8f2',NULL,NULL,NULL,'1020413761',0,'2016-07-05 22:33:17','2016-07-05 22:33:17','1020413761','Entrepaño central de 100 x 45 cm','',0,100,'ENT10045',0,'PRODUCT_TYPE1','3645a47f-095c-44c4-9bb9-17daf3f15a71','7d8c25bb-358f-11e6-a718-507b9d9f0aad'),('a2411366-d135-4114-bcc5-369a737c1c85',NULL,NULL,NULL,'1020413761',0,'2016-07-05 22:27:10','2016-07-05 22:27:10','1020413761','Gondola de 135 x 120 cm','',0,100,'GON135120',0,'PRODUCT_TYPE1','392f989c-8534-4b44-9e3e-1c2b754c9139','7d8c25bb-358f-11e6-a718-507b9d9f0aad'),('a2b044be-b95e-4d6f-9ced-b2370001c891',NULL,NULL,NULL,'1020413761',0,'2016-07-05 10:40:20','2016-07-05 22:30:20','1020413761','Góndola de 110 x 120 cm','',100,100,'GON110',0,'PRODUCT_TYPE1','392f989c-8534-4b44-9e3e-1c2b754c9139','7d8c25bb-358f-11e6-a718-507b9d9f0aad'),('b2ac18b6-f15c-4ca8-969f-bb4c98ae5a00',NULL,NULL,NULL,'1020413761',0,'2016-07-04 21:54:25','2016-07-04 21:54:25','1020413761','Entrepaño Central de 70 x 45 cm','',0,100,'ENT7045',0,'PRODUCT_TYPE1','3645a47f-095c-44c4-9bb9-17daf3f15a71','7d8c25bb-358f-11e6-a718-507b9d9f0aad'),('c09ee133-d645-4ae5-b415-a685bd40b384',NULL,NULL,NULL,'1020413761',0,'2016-07-05 23:53:17','2016-07-05 23:53:17','1020413761','Gondola especial doble cara de 135 x 120 cm','',0,100,'GONDCAR',0,'PRODUCT_TYPE1','392f989c-8534-4b44-9e3e-1c2b754c9139','7d8c25bb-358f-11e6-a718-507b9d9f0aad'),('c4a68db1-dfb0-402f-8fb6-8a6c013f5b9e',NULL,NULL,NULL,'1020413761',0,'2016-07-04 21:53:49','2016-07-04 21:53:49','1020413761','Botaderos','',0,100,'BOT01',0,'PRODUCT_TYPE1','a8dc0815-a30a-4dc1-a51f-9925c6e8727b','7d8c25bb-358f-11e6-a718-507b9d9f0aad'),('db5b7a57-cda0-4808-ae09-8ae5130693d5',NULL,NULL,NULL,'1020413761',0,'2016-07-04 21:52:59','2016-07-04 21:52:59','1020413761','Entrepaño Central 110 x 45 cm','',0,100,'ENT11045',0,'PRODUCT_TYPE1','3645a47f-095c-44c4-9bb9-17daf3f15a71','7d8c25bb-358f-11e6-a718-507b9d9f0aad'),('e72d8a91-3f7d-40e2-8609-be2c41077919',NULL,NULL,NULL,'1020413761',0,'2016-07-05 22:32:28','2016-07-05 22:32:28','1020413761','Entrepaño Central de 80 x 45 cm','',0,100,'ENT8045',0,'PRODUCT_TYPE1','3645a47f-095c-44c4-9bb9-17daf3f15a71','7d8c25bb-358f-11e6-a718-507b9d9f0aad'),('e8aada71-ab3d-4781-991b-cd4127cd3f80',NULL,NULL,NULL,'1020413761',0,'2016-07-04 20:18:46','2016-07-04 20:18:46','1020413761','Gondola Especial de 135 x 70 cm','',0,100,'GON13570',0,'PRODUCT_TYPE1','392f989c-8534-4b44-9e3e-1c2b754c9139','7d8c25bb-358f-11e6-a718-507b9d9f0aad');
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recibos`
--

DROP TABLE IF EXISTS `recibos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recibos` (
  `id` varchar(255) NOT NULL,
  `atributos` int(11) DEFAULT NULL,
  `eliminado` smallint(6) DEFAULT NULL,
  `fecha_fin` datetime DEFAULT NULL,
  `fecha_inicio` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recibos`
--

LOCK TABLES `recibos` WRITE;
/*!40000 ALTER TABLE `recibos` DISABLE KEYS */;
/*!40000 ALTER TABLE `recibos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `remisiones`
--

DROP TABLE IF EXISTS `remisiones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `remisiones` (
  `id` varchar(255) NOT NULL,
  `referencia` varchar(20) DEFAULT NULL,
  `creado_por` varchar(255) DEFAULT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  `eliminado` smallint(6) DEFAULT NULL,
  `entregado_a` varchar(255) DEFAULT NULL,
  `ESTADO` varchar(255) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `fecha_remision` date DEFAULT NULL,
  `modificado_por` varchar(255) DEFAULT NULL,
  `total_productos` int(11) DEFAULT NULL,
  `id_pedido` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_remisiones_id_pedido` (`id_pedido`),
  KEY `ESTADO` (`ESTADO`),
  KEY `FK_UserCreator` (`creado_por`) USING BTREE,
  KEY `FK_rem_terceros` (`entregado_a`),
  CONSTRAINT `FK_remisiones_creador` FOREIGN KEY (`creado_por`) REFERENCES `personas` (`id`),
  CONSTRAINT `FK_remisiones_id_pedido` FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `remisiones`
--

LOCK TABLES `remisiones` WRITE;
/*!40000 ALTER TABLE `remisiones` DISABLE KEYS */;
INSERT INTO `remisiones` VALUES ('00c2866f-0e3a-4b58-a7e5-fe93abc4ae64','RM00267','1020413761','Se hace entrega de la mercancia en buen estado',0,'','8d88996b-464d-46db-900e-e24bae8508fa','2016-07-05 23:24:16','2016-07-05 23:24:16','2016-01-04','1020413761',101,'11ae1769-38b8-42d3-8b13-8277cfd2d93a'),('02f3b7d7-ef61-4d39-bef8-ea7f99c59713','RM00268','1020413761','Se hace entrega de la mercancía en buen estado',0,'','8d88996b-464d-46db-900e-e24bae8508fa','2016-07-05 23:35:15','2016-07-05 23:35:15','2016-01-05','1020413761',178,'11ae1769-38b8-42d3-8b13-8277cfd2d93a'),('11c3513e-8bfc-482b-a5e8-54d171ba6eac','RM00274','1020413761','',0,NULL,'8d88996b-464d-46db-900e-e24bae8508fa','2016-07-06 06:01:49','2016-07-06 06:01:49','2016-01-13','1020413761',6,'48bb2179-a1bf-4687-b3f6-3d30636ff531'),('12fe62c8-c95d-4b32-beb5-6fad5dbe3537','RM00282','1020413761','',0,NULL,'8d88996b-464d-46db-900e-e24bae8508fa','2016-07-06 06:12:50','2016-07-06 06:12:50','2016-01-21','1020413761',23,'e56995cb-9f24-4bbe-b099-7c22239732bc'),('36ad98f1-5f4c-4f19-a6e6-15ea824c99df','RM00269','1020413761','',0,'','8d88996b-464d-46db-900e-e24bae8508fa','2016-07-05 23:39:46','2016-07-05 23:39:46','2016-01-05','1020413761',29,'11ae1769-38b8-42d3-8b13-8277cfd2d93a'),('3d152bd9-3f24-4017-916a-e98f6c75e08b','RM00275','1020413761','Esta se Remite con otra orden que tiene reprocesos.',0,NULL,'8d88996b-464d-46db-900e-e24bae8508fa','2016-07-06 06:05:37','2016-07-06 06:05:37','2016-01-16','1020413761',110,'90d8ef64-7262-4078-ad68-807d188985d7'),('4c23c45b-2996-4ad8-bb9e-78440a16c676','RM00283','1020413761','',0,NULL,'8d88996b-464d-46db-900e-e24bae8508fa','2016-07-06 06:14:13','2016-07-06 06:14:13','2016-01-25','1020413761',9,'04baa1b4-88a4-4565-8b55-aa4acd3e0290'),('66911382-5240-4f22-88e6-deb812fef318','RM00276','1020413761','',0,NULL,'8d88996b-464d-46db-900e-e24bae8508fa','2016-07-06 06:06:58','2016-07-06 06:06:58','2016-01-19','1020413761',6,'9734086e-bfc9-4909-8f3f-8cace3e4a54d'),('79cc0d60-28c8-4a3d-98d6-abdef94ed571','RM00279','1020413761','',0,NULL,'8d88996b-464d-46db-900e-e24bae8508fa','2016-07-06 06:09:39','2016-07-06 06:09:39','2016-01-21','1020413761',30,'ee46d986-e333-4c83-8826-ef106a858504'),('7fa28b0c-58cf-4715-9214-fe889da5695a','RM00424','1020413761','Se hace entrega de la mercancia en buen estado, Despacho Bodega Britalia.',0,NULL,'8d88996b-464d-46db-900e-e24bae8508fa','2016-07-06 12:03:07','2016-07-06 12:03:07','2016-07-06','1020413761',210,'48064538-d4e9-47c9-a3de-edac081bef9a'),('884b43d4-8a4c-4fd2-8d9b-507ef70b9f7e','RM00281','1020413761','',0,NULL,'8d88996b-464d-46db-900e-e24bae8508fa','2016-07-06 06:11:35','2016-07-06 06:11:35','2016-01-21','1020413761',10,'8b2c7f28-364b-4647-8fb7-90d005ec2622'),('89ba9a04-c766-4124-ba82-fa723d091cd3','RM00278','1020413761','',0,NULL,'8d88996b-464d-46db-900e-e24bae8508fa','2016-07-06 06:08:11','2016-07-06 06:08:11','2016-01-20','1020413761',2,'5c5fb5b2-eebc-4ab0-bb32-96733b4a8d0b'),('9c316564-ae2f-409e-aacb-ab341eec7483','RM00271','1020413761','',0,NULL,'8d88996b-464d-46db-900e-e24bae8508fa','2016-07-06 05:54:39','2016-07-06 05:54:39','2016-01-08','1020413761',15,'dd5f1bd8-c6fb-4395-9f57-7162fd67eaa7'),('bfee5e01-b5a9-4e48-af1c-7d11d57d5b47','RM00280','1020413761','',0,NULL,'8d88996b-464d-46db-900e-e24bae8508fa','2016-07-06 06:10:43','2016-07-06 06:10:43','2016-01-21','1020413761',24,'d9e9e076-afee-4b1d-88ab-e211a6b169a1'),('c653404e-a6e7-482b-b2ac-4dc6b3ab57ed','RM00270','1020413761','',0,NULL,'06afb827-2cb4-4e08-abe4-757ba0dc69b5','2016-07-05 23:49:22','2016-07-05 23:49:22','2016-01-07','1020413761',38,'b7b162db-afef-455b-9aaa-012d3335e7c3'),('cb04fa96-58b8-4674-8fc6-2ea672030bb7','RM00272','1020413761','',0,NULL,'8d88996b-464d-46db-900e-e24bae8508fa','2016-07-06 05:59:24','2016-07-06 05:59:24','2016-01-12','1020413761',132,'f466365f-e96d-4a5b-b19d-6c7703cbce5a'),('cb0eb298-0f35-45c1-bdea-4e6e83eb0ea2','RM00423','1020413761','Se hace entrega de la mercancía en buen estado.',0,NULL,'8d88996b-464d-46db-900e-e24bae8508fa','2016-07-05 10:53:02','2016-07-05 10:53:02','2016-07-01','1020413761',30,'cd780ff2-7251-43b4-8c12-abf694102552'),('d7bd8518-3b37-4fe3-86c5-6d9d45eb3674','RM00273','1020413761','',0,NULL,'8d88996b-464d-46db-900e-e24bae8508fa','2016-07-06 06:00:43','2016-07-06 06:00:43','2016-01-12','1020413761',24,'3b506903-c873-4432-820c-fc6a50388588'),('eddaa3e0-4e52-432c-9fd6-4027d03854ac','RM00277','1020413761','',0,NULL,'8d88996b-464d-46db-900e-e24bae8508fa','2016-07-06 06:07:36','2016-07-06 06:07:36','2016-01-19','1020413761',2,'8cd8eeb0-fba0-44ef-8289-1d263eb4da05');
/*!40000 ALTER TABLE `remisiones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `remisiones_producto`
--

DROP TABLE IF EXISTS `remisiones_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `remisiones_producto` (
  `id` varchar(255) NOT NULL,
  `cantidad` smallint(6) DEFAULT NULL,
  `eliminado` smallint(6) DEFAULT NULL,
  `id_producto` varchar(255) DEFAULT NULL,
  `id_remision` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_remisiones_producto_id_remision` (`id_remision`),
  KEY `FK_producto_id` (`id_producto`) USING BTREE,
  CONSTRAINT `FK_id_producto` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id`),
  CONSTRAINT `FK_remisiones_producto_id_remision` FOREIGN KEY (`id_remision`) REFERENCES `remisiones` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `remisiones_producto`
--

LOCK TABLES `remisiones_producto` WRITE;
/*!40000 ALTER TABLE `remisiones_producto` DISABLE KEYS */;
INSERT INTO `remisiones_producto` VALUES ('021ed92b-f449-4ff7-b785-f0d5e093bbe8',4,0,'5664c1d8-223c-4436-9e4c-ea082069e842','66911382-5240-4f22-88e6-deb812fef318'),('02b7aff9-cccf-4d61-8719-daec4a028dc5',9,0,'79b1052d-bcb1-469f-8748-5f4bc69ae97a','36ad98f1-5f4c-4f19-a6e6-15ea824c99df'),('030a3c9f-b37d-48c0-8262-39bfe8bedb81',16,0,'5664c1d8-223c-4436-9e4c-ea082069e842','bfee5e01-b5a9-4e48-af1c-7d11d57d5b47'),('0387ca92-763c-4783-baf6-107e5c9f5e6c',44,0,'5664c1d8-223c-4436-9e4c-ea082069e842','cb04fa96-58b8-4674-8fc6-2ea672030bb7'),('04fac491-7c1a-4088-9c83-a90c6747d565',2,0,'e72d8a91-3f7d-40e2-8609-be2c41077919','c653404e-a6e7-482b-b2ac-4dc6b3ab57ed'),('093cf6a6-e2f5-4c44-95fa-74c01d86f5c6',17,0,'06cb9820-68c7-4cbd-a2f2-fd195e789100','02f3b7d7-ef61-4d39-bef8-ea7f99c59713'),('1ab44134-c37d-4c58-bbb1-9dd233b76051',86,0,'79b1052d-bcb1-469f-8748-5f4bc69ae97a','3d152bd9-3f24-4017-916a-e98f6c75e08b'),('1b7ddad2-e9a2-4f16-9df3-b417af0a8e8d',18,0,'82289b0a-9159-4a10-93fe-4c0559ade658','c653404e-a6e7-482b-b2ac-4dc6b3ab57ed'),('2b85b7e4-14a1-406e-8837-ff90d14c4932',120,0,'5664c1d8-223c-4436-9e4c-ea082069e842','7fa28b0c-58cf-4715-9214-fe889da5695a'),('2c7c745b-31ba-487f-a1c8-dcb5f11e40ba',10,0,'5664c1d8-223c-4436-9e4c-ea082069e842','c653404e-a6e7-482b-b2ac-4dc6b3ab57ed'),('2f66dff1-ab82-4630-9715-ca0d6536241c',44,0,'79b1052d-bcb1-469f-8748-5f4bc69ae97a','cb04fa96-58b8-4674-8fc6-2ea672030bb7'),('3ab3172a-f17a-4eed-a3ac-318983f5c1d4',10,0,'a2b044be-b95e-4d6f-9ced-b2370001c891','cb0eb298-0f35-45c1-bdea-4e6e83eb0ea2'),('40bc6bee-1af4-4722-8ab8-cd26353eaf53',9,0,'5664c1d8-223c-4436-9e4c-ea082069e842','36ad98f1-5f4c-4f19-a6e6-15ea824c99df'),('42037cbd-196f-488e-bda4-e5f392b8cffa',6,0,'280edafb-0d8a-4e84-ab37-e8092d2703e1','11c3513e-8bfc-482b-a5e8-54d171ba6eac'),('445f7641-d16c-4aa4-9006-3fec4851df80',16,0,'5664c1d8-223c-4436-9e4c-ea082069e842','d7bd8518-3b37-4fe3-86c5-6d9d45eb3674'),('4470e152-7869-4dba-8c71-83c712562671',8,0,'a2411366-d135-4114-bcc5-369a737c1c85','bfee5e01-b5a9-4e48-af1c-7d11d57d5b47'),('4a9b4864-5eae-4e96-8b6a-c42aa5d27742',8,0,'a2411366-d135-4114-bcc5-369a737c1c85','d7bd8518-3b37-4fe3-86c5-6d9d45eb3674'),('53c6de96-5cf3-4269-8814-c703d9f2d124',24,0,'a2411366-d135-4114-bcc5-369a737c1c85','3d152bd9-3f24-4017-916a-e98f6c75e08b'),('583f752b-c202-4432-a7e9-b1c6582354fd',9,0,'c4a68db1-dfb0-402f-8fb6-8a6c013f5b9e','4c23c45b-2996-4ad8-bb9e-78440a16c676'),('5c164906-a47a-4450-9841-256c39187a6c',8,0,'8edcbd34-99e3-4bf0-8da7-24816d97e8f2','c653404e-a6e7-482b-b2ac-4dc6b3ab57ed'),('5c3fed6f-5825-43a9-9c20-5207fd906587',2,0,'280edafb-0d8a-4e84-ab37-e8092d2703e1','89ba9a04-c766-4124-ba82-fa723d091cd3'),('5d618fd0-eba0-4b03-96d7-a4c6ca024025',62,0,'5664c1d8-223c-4436-9e4c-ea082069e842','02f3b7d7-ef61-4d39-bef8-ea7f99c59713'),('608322f9-a275-43bc-a452-0a24e0f49ad7',20,0,'280edafb-0d8a-4e84-ab37-e8092d2703e1','12fe62c8-c95d-4b32-beb5-6fad5dbe3537'),('60ef935c-4d48-4dec-8118-424dad244015',50,0,'a2411366-d135-4114-bcc5-369a737c1c85','7fa28b0c-58cf-4715-9214-fe889da5695a'),('619e6500-4ad9-4952-946a-10418f7c2d25',10,0,'5664c1d8-223c-4436-9e4c-ea082069e842','cb0eb298-0f35-45c1-bdea-4e6e83eb0ea2'),('649921dc-a7c4-499e-aed0-0f715605b50c',3,0,'c4a68db1-dfb0-402f-8fb6-8a6c013f5b9e','12fe62c8-c95d-4b32-beb5-6fad5dbe3537'),('6f32d0cb-9e95-46a3-a08f-9d0969ffb572',2,0,'280edafb-0d8a-4e84-ab37-e8092d2703e1','00c2866f-0e3a-4b58-a7e5-fe93abc4ae64'),('7739420f-bcc1-404a-8ece-37befed586c0',10,0,'a2411366-d135-4114-bcc5-369a737c1c85','79cc0d60-28c8-4a3d-98d6-abdef94ed571'),('776ed60b-115b-4bde-97a3-1339d808e111',5,0,'a2b044be-b95e-4d6f-9ced-b2370001c891','9c316564-ae2f-409e-aacb-ab341eec7483'),('871f1b4f-edc5-493d-9b16-35c0a83026c0',33,0,'a2411366-d135-4114-bcc5-369a737c1c85','00c2866f-0e3a-4b58-a7e5-fe93abc4ae64'),('91728e1c-6d68-441d-9ad2-7c3343f3da63',20,0,'5664c1d8-223c-4436-9e4c-ea082069e842','79cc0d60-28c8-4a3d-98d6-abdef94ed571'),('979b798e-3ea7-4226-b61c-859c3cbe0731',5,0,'5664c1d8-223c-4436-9e4c-ea082069e842','9c316564-ae2f-409e-aacb-ab341eec7483'),('a05154d5-69bb-4710-8867-89e4505a0da9',58,0,'79b1052d-bcb1-469f-8748-5f4bc69ae97a','02f3b7d7-ef61-4d39-bef8-ea7f99c59713'),('a1ad76f5-8fe3-4ebc-97ba-94e6b2ab02ff',2,0,'5664c1d8-223c-4436-9e4c-ea082069e842','eddaa3e0-4e52-432c-9fd6-4027d03854ac'),('a4155117-5709-46ba-ac75-e91119204398',20,0,'79b1052d-bcb1-469f-8748-5f4bc69ae97a','7fa28b0c-58cf-4715-9214-fe889da5695a'),('a5933fb5-67ff-4690-9da5-0bf0fae7981a',10,0,'79b1052d-bcb1-469f-8748-5f4bc69ae97a','cb0eb298-0f35-45c1-bdea-4e6e83eb0ea2'),('a71ceb9d-e949-40f2-91d2-4c05a7be902a',9,0,'a2b044be-b95e-4d6f-9ced-b2370001c891','36ad98f1-5f4c-4f19-a6e6-15ea824c99df'),('ac56a656-0e74-4059-b45f-3e690caddacc',4,0,'0ee4a9e7-cb2c-4b14-bdbe-00622eeebf1d','9c316564-ae2f-409e-aacb-ab341eec7483'),('df0941f5-e553-467e-8eba-229b1007c05d',41,0,'a2b044be-b95e-4d6f-9ced-b2370001c891','02f3b7d7-ef61-4d39-bef8-ea7f99c59713'),('e07da2ff-704d-4dee-8713-b199d79f5971',1,0,'79b1052d-bcb1-469f-8748-5f4bc69ae97a','9c316564-ae2f-409e-aacb-ab341eec7483'),('e9b746cc-7c7d-4c1e-847c-4a31d16083b7',44,0,'a2b044be-b95e-4d6f-9ced-b2370001c891','cb04fa96-58b8-4674-8fc6-2ea672030bb7'),('ea58f1b1-ae1c-40e2-9fa4-af4098926898',2,0,'a2411366-d135-4114-bcc5-369a737c1c85','66911382-5240-4f22-88e6-deb812fef318'),('ee8f1cae-688f-4e20-9bb4-8d8b2fcd2866',10,0,'5664c1d8-223c-4436-9e4c-ea082069e842','884b43d4-8a4c-4fd2-8d9b-507ef70b9f7e'),('f05a229e-a111-4221-9135-4fc92f446d98',20,0,'a2b044be-b95e-4d6f-9ced-b2370001c891','7fa28b0c-58cf-4715-9214-fe889da5695a'),('f4970248-7dc0-48d0-9df7-996648dcfca0',66,0,'5664c1d8-223c-4436-9e4c-ea082069e842','00c2866f-0e3a-4b58-a7e5-fe93abc4ae64'),('f4ee4e75-851b-4f1e-b889-f2aceea57b9a',2,0,'280edafb-0d8a-4e84-ab37-e8092d2703e1','36ad98f1-5f4c-4f19-a6e6-15ea824c99df');
/*!40000 ALTER TABLE `remisiones_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `terceros`
--

DROP TABLE IF EXISTS `terceros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `terceros` (
  `id` varchar(255) NOT NULL,
  `contacto` varchar(255) DEFAULT NULL,
  `creado_por` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `eliminado` smallint(6) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `modificado_por` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_tercero_contacto` (`contacto`),
  CONSTRAINT `FK_tercero_contacto` FOREIGN KEY (`contacto`) REFERENCES `personas` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `terceros`
--

LOCK TABLES `terceros` WRITE;
/*!40000 ALTER TABLE `terceros` DISABLE KEYS */;
INSERT INTO `terceros` VALUES ('ae95acfa-6224-4f79-b995-489dcc3a5304','asd','1020413761','ASD',1,'2016-07-05 05:50:18','2016-07-05 05:50:18','1020413761','SAD','asd','asd'),('c65d9d41-1ebe-4f48-a057-f4d9921eb737','13','1020413761','WRE',1,'2016-07-05 05:51:01','2016-07-05 05:51:01','1020413761','REWR','3465','23443');
/*!40000 ALTER TABLE `terceros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipos_cliente`
--

DROP TABLE IF EXISTS `tipos_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipos_cliente` (
  `id` varchar(255) NOT NULL,
  `eliminado` smallint(6) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos_cliente`
--

LOCK TABLES `tipos_cliente` WRITE;
/*!40000 ALTER TABLE `tipos_cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipos_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipos_dir`
--

DROP TABLE IF EXISTS `tipos_dir`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipos_dir` (
  `id` varchar(255) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos_dir`
--

LOCK TABLES `tipos_dir` WRITE;
/*!40000 ALTER TABLE `tipos_dir` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipos_dir` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipos_tel`
--

DROP TABLE IF EXISTS `tipos_tel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipos_tel` (
  `id` varchar(255) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos_tel`
--

LOCK TABLES `tipos_tel` WRITE;
/*!40000 ALTER TABLE `tipos_tel` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipos_tel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unidades_medida`
--

DROP TABLE IF EXISTS `unidades_medida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unidades_medida` (
  `id` varchar(36) NOT NULL,
  `nombre` varchar(80) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unidades_medida`
--

LOCK TABLES `unidades_medida` WRITE;
/*!40000 ALTER TABLE `unidades_medida` DISABLE KEYS */;
INSERT INTO `unidades_medida` VALUES ('7d8c25bb-358f-11e6-a718-507b9d9f0aad','UNIDADES'),('7d8c332c-358f-11e6-a718-507b9d9f0aad','GRAMOS'),('aca83d9f-358f-11e6-a718-507b9d9f0aad','KILOS'),('aca84b06-358f-11e6-a718-507b9d9f0aad','PULGADAS'),('c3e2c2ea-358f-11e6-a718-507b9d9f0aad','METROS'),('c3e2cf81-358f-11e6-a718-507b9d9f0aad','CENTIMETROS');
/*!40000 ALTER TABLE `unidades_medida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `usuario` varchar(255) NOT NULL,
  `creado_por` varchar(255) DEFAULT NULL,
  `eliminado` smallint(6) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `modificado_por` varchar(255) DEFAULT NULL,
  `passw` varchar(255) DEFAULT NULL,
  `persona` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`usuario`),
  KEY `FK_usuarios_persona` (`persona`),
  CONSTRAINT `FK_usuarios_persona` FOREIGN KEY (`persona`) REFERENCES `personas` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES ('julyhiguita','admin',0,'2016-06-25 12:47:49','2016-06-25 12:47:49',NULL,'julyhiguita','1020413761');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-06 12:28:44
