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
INSERT INTO `categorias` VALUES ('633c5eb3-1155-44a2-9785-4924747dd06a',0,NULL,'PRODUCTOS','METALICO','92701bd1-0759-43e3-836c-e6cb6f13f3b7'),('67ecf673-bfc3-4dc6-b576-8c6883142be9',0,NULL,'PRODUCTOS','GONDOLA',NULL),('8e465b94-1350-4193-bdd3-922eb37f1303',0,NULL,'PRODUCTOS','MADERA','92701bd1-0759-43e3-836c-e6cb6f13f3b7'),('92701bd1-0759-43e3-836c-e6cb6f13f3b7',0,NULL,'PRODUCTOS','METALICO',NULL),('93bce9e2-5038-4687-af8c-0305dd8e5655',0,NULL,'PRODUCTOS','LAMINA 2 X 3','92701bd1-0759-43e3-836c-e6cb6f13f3b7'),('a7efdb57-3122-4b6a-97f4-7553b49c3f19',0,NULL,'PRODUCTOS','PRODUCTOS DE VENTA',NULL),('a8fc8306-a10e-42d6-a522-9b87cae0e630',0,NULL,'PRODUCTOS','INSUMO   XXX','92701bd1-0759-43e3-836c-e6cb6f13f3b7'),('c7afcdff-cdb6-4760-8d9b-fd432897ebd5',1,NULL,'PRODUCTOS','POS','a7efdb57-3122-4b6a-97f4-7553b49c3f19'),('d14d5e03-aa5e-4508-b1e5-c2326b01aff5',0,NULL,'PRODUCTOS','GONDOLA ESPECIAL','67ecf673-bfc3-4dc6-b576-8c6883142be9'),('e3328939-fc0a-4a1c-b397-670874e60f48',1,NULL,'PRODUCTOS','insumo xy',NULL),('f902bf18-b91d-46c3-8300-8e96f51df3ec',0,NULL,'PRODUCTOS','madera BALSO','8e465b94-1350-4193-bdd3-922eb37f1303');
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
INSERT INTO `cuentas` VALUES ('165169e8-83b8-450a-9531-4be0a458c473','23432',NULL,NULL,NULL,NULL,'INACTIVA',NULL,'14136571','2016-06-19 08:25:26','14136571','2016-06-19 08:25:26',1),('234324','razon sdaoyodsf',NULL,NULL,NULL,NULL,'ACTIVA',NULL,'14136571','2016-06-19 08:34:53','14136571','2016-06-19 08:34:53',0),('47987564','kjlkjok',NULL,NULL,NULL,NULL,'ACTIVA',NULL,'14136571','2016-06-20 22:03:54','14136571','2016-06-20 22:03:54',0),('6e492f7e-58b7-4c22-9ac7-7286c22c9e8c','213321',NULL,NULL,NULL,NULL,'ACTIVA',NULL,'14136571','2016-06-19 08:17:40','14136571','2016-06-19 08:17:40',1),('7874561230','kvhjgklkjlkjj',NULL,'ñljpojpoij','4645156','1231564','ACTIVA',NULL,'14136571','2016-07-04 12:10:12','14136571','2016-07-04 12:10:12',0),('794545621','COMÑIA Y ASOCIDOS',NULL,'CRA 65 # 28-52','1234564','','ACTIVA',NULL,'14136571','2016-07-04 12:39:30','14136571','2016-07-04 12:39:30',0),('7984564561','empresa yyu',NULL,NULL,NULL,NULL,'ACTIVA',NULL,'14136571','2016-06-21 18:03:47','14136571','2016-06-21 18:03:47',0),('7987','dasd ',NULL,NULL,NULL,NULL,'ACTIVA',NULL,'14136571','2016-06-20 21:55:38','14136571','2016-06-20 21:55:38',0),('798798321798','Empresa xxx',NULL,NULL,NULL,NULL,'ACTIVA',NULL,'14136571','2016-06-19 08:39:48','14136571','2016-06-19 08:39:48',0),('878789','oioi',NULL,NULL,NULL,NULL,'ACTIVA',NULL,'14136571','2016-06-20 22:13:34','14136571','2016-06-20 22:13:34',0),('9874656','empresa yyyy',NULL,NULL,NULL,NULL,'ACTIVA',NULL,'14136571','2016-06-19 09:22:14','14136571','2016-06-19 23:49:00',0);
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
INSERT INTO `cuentas_contactos` VALUES ('987454564','9874656','cargooo',0),('798456456','7987','AP_DIR',0),('79879856456','47987564','AP_SEC',0),('87987986','878789','AP_SEC',0),('79845641','7984564561','AP_COM',0),('1564564','7874561230','AP_COM',0),('454564','794545621','AP_ASI',0);
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
INSERT INTO `parametros` VALUES ('09f23661-2537-4ebb-b397-b6d01cc38c68','RM','11',0,'CONF_REMISSIONS'),('86b0b849-4f2a-410a-8540-618608983eaf','OC','21',0,'CONF_ORDERS'),('AP_ASI','ASISTENTE',NULL,0,'ACCOUNTS_POSITION'),('AP_COM','COMERCIAL',NULL,0,'ACCOUNTS_POSITION'),('AP_DIR','DIRECTOR',NULL,0,'ACCOUNTS_POSITION'),('AP_GER','GERENTE',NULL,0,'ACCOUNTS_POSITION'),('AP_OPE','OPERARIO',NULL,0,'ACCOUNTS_POSITION'),('AP_SEC','SECRETEARIA',NULL,0,'ACCOUNTS_POSITION'),('AP_TEC','TECNICO',NULL,0,'ACCOUNTS_POSITION'),('ID_C','CEDULA',NULL,0,'IDENTIFICATION'),('ID_NIT','NIT',NULL,0,'IDENTIFICATION'),('PRODUCT_TYPE1','PRODUCTO DE VENTA',NULL,0,'PRODUCT_TYPE'),('PRODUCT_TYPE2','INSUMO',NULL,0,'PRODUCT_TYPE');
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
INSERT INTO `pedidos` VALUES ('1eb5be14-6460-4742-88c3-9cf1440397ae',0,15,'14136571','',0,'2016-07-04 08:20:33',NULL,'2016-07-04 08:20:33','2016-07-19','14136571','esta es una remision','OC00018','8d88996b-464d-46db-900e-e24bae8508fa','798798321798'),('1f7e100a-4d0d-4989-bbb8-6911508dc2ef',0,1,'14136571','',0,'2016-07-03 07:50:54',NULL,'2016-07-03 07:50:54','2016-07-21','14136571','oc 15','OC00015','8d88996b-464d-46db-900e-e24bae8508fa','798798321798'),('2a2e5f96-8c8c-4aab-9213-4fb86cf2e497',0,15,'14136571','',0,'2016-06-21 18:49:54','2016-06-01','2016-06-21 18:49:54','2016-06-02','14136571','','OC00005','13625479-9841-4047-adad-9741eed735d3','7984564561'),('2e25c21f-150a-4501-ad9a-78f9ff334e65',0,2,'14136571','',0,'2016-07-03 07:39:57',NULL,'2016-07-03 07:39:57','2016-07-05','14136571','orden 14','OC00014','8d88996b-464d-46db-900e-e24bae8508fa','7984564561'),('2f6589dc-cc67-45a3-821f-5b158ecffb7a',0,0,'14136571','',0,'2016-06-21 22:52:18',NULL,'2016-06-21 22:52:18','2016-06-09','14136571','','OC00008','06afb827-2cb4-4e08-abe4-757ba0dc69b5','234324'),('467cf2d3-b3e4-477b-8a6f-82938c7fba61',0,15,'14136571','',0,'2016-07-04 08:35:27',NULL,'2016-07-04 08:35:27','2016-07-06','14136571','remision xxxksj','OC00020','13625479-9841-4047-adad-9741eed735d3','234324'),('52ba8dc7-caee-40c7-aae0-6b0302e60d56',0,61,'14136571','',0,'2016-07-03 08:07:15',NULL,'2016-07-03 08:07:15','2016-07-05','14136571','orden','OC00017','226fd06d-05eb-40d5-8c04-ee0f0a2e277a','798798321798'),('5c019884-9f93-4186-a01a-3725e0c73dfa',0,2,'14136571','',0,'2016-06-21 22:24:17','2016-06-21','2016-06-21 22:24:17','2016-06-15','14136571','','OC00006','13625479-9841-4047-adad-9741eed735d3','9874656'),('66f9ec68-0e5a-4664-a4f9-5325200f58ec',0,0,'14136571','',0,'2016-06-21 23:01:21',NULL,'2016-06-21 23:01:21','2016-06-15','14136571','','OC00009','13625479-9841-4047-adad-9741eed735d3','47987564'),('6ab953fb-eb5b-4ecc-aaae-8f01b62fa9f7',0,15,'14136571','',0,'2016-07-03 07:54:17',NULL,'2016-07-03 07:54:17','2016-07-28','14136571','or c 1555','OC00015','8d88996b-464d-46db-900e-e24bae8508fa','234324'),('86c73509-e3b7-4a5a-ba75-1ab50f73b228',0,0,'14136571','',0,'2016-07-02 07:17:38',NULL,'2016-07-02 07:17:38','2016-07-13','14136571','','OC00012','13625479-9841-4047-adad-9741eed735d3','7984564561'),('8d42ce72-720f-45d5-8ca5-c874c48135f2',0,54,'14136571','',0,'2016-07-04 17:10:59',NULL,'2016-07-04 17:10:59','2016-07-13','14136571','','OC00021','13625479-9841-4047-adad-9741eed735d3','794545621'),('911267d2-faf3-44d8-bba7-d9501d86f7a2',0,51,'14136571','',0,'2016-06-23 23:01:35','2016-06-23','2016-06-23 23:01:35','2016-06-23','14136571','','OC00011','13625479-9841-4047-adad-9741eed735d3','798798321798'),('929f4f36-37b7-4dbf-80c5-bf41189b5aae',0,15,'14136571','',0,'2016-07-02 08:12:42',NULL,'2016-07-02 08:12:42','2016-07-14','14136571','','OC00013','13625479-9841-4047-adad-9741eed735d3','7984564561'),('a00bbc7d-1800-473e-880c-c53c09665d3c',0,0,'14136571','',0,'2016-07-01 22:00:58',NULL,'2016-07-01 22:00:58','2016-07-14','14136571','','OC00012','226fd06d-05eb-40d5-8c04-ee0f0a2e277a','7984564561'),('b480ac48-27a3-47dc-a113-cf759a05f728',0,15,'14136571','',0,'2016-07-04 08:30:04',NULL,'2016-07-04 08:30:04','2016-07-21','14136571','sapdudsai','OC00019','1dcd7183-2c8d-4379-9b50-225a647283a0','7984564561'),('cdacb2c5-9ace-4a23-bdb8-9cd59ece9c3a',0,0,'14136571','asdk{ñkdsa',0,'2016-06-20 22:16:45','2016-06-15','2016-06-20 22:16:45','2016-06-13','14136571','observaciones','OC00003','13625479-9841-4047-adad-9741eed735d3','9874656'),('d0adc8aa-f764-4ea7-962c-fe057f494350',0,13,'14136571','',0,'2016-07-02 06:27:41',NULL,'2016-07-02 06:27:41','2016-07-06','14136571','','OC00012','13625479-9841-4047-adad-9741eed735d3','7984564561'),('dba7629f-2819-4e3d-acec-89b7cadc5086',0,0,'14136571','adssad',0,'2016-06-21 18:06:48','2016-09-30','2016-06-21 18:06:48','2016-06-21','14136571','','OC00004','13625479-9841-4047-adad-9741eed735d3','9874656'),('edfbec23-7593-460c-b533-c20324c4b167',0,0,'14136571','',0,'2016-06-21 23:04:19',NULL,'2016-06-21 23:04:19','2016-06-08','14136571','','OC00010','13625479-9841-4047-adad-9741eed735d3','7987'),('f8577cf5-1555-4ab2-b8dc-977e2b0392b7',0,15,'14136571','',0,'2016-07-03 08:06:22',NULL,'2016-07-03 08:06:22','2016-07-13','14136571','producto sin remision','OC00016','1dcd7183-2c8d-4379-9b50-225a647283a0','9874656'),('fe7f0706-1335-4b83-a1ce-111783688b8c',0,0,'14136571','',0,'2016-06-21 22:45:25',NULL,'2016-06-21 22:45:25','2016-06-21','14136571','','OC00007','13625479-9841-4047-adad-9741eed735d3','234324');
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
INSERT INTO `pedidos_producto` VALUES ('02ca4aeb-e233-4709-8f01-fd37ed6f2c35',0,0,0,0,1000,'fe7f0706-1335-4b83-a1ce-111783688b8c','43d75b3d-e777-4596-b48f-6a925b764f04'),('02f4923c-b7ea-47ec-9209-37bfe18cd70c',12,12,0,4200000,350000,'8d42ce72-720f-45d5-8ca5-c874c48135f2','f1c036a1-ee3d-4bef-8214-071c1be5e3b5'),('0b5a463a-f3a6-4fc5-86b5-3cb3e46d9aa7',15,15,0,3000000,200000,'1eb5be14-6460-4742-88c3-9cf1440397ae','a82b7b59-2fc2-4485-b10c-171fcab90049'),('0b991286-a030-4cb7-bbec-09e99ac7a485',10,10,0,1234560,123456,'8d42ce72-720f-45d5-8ca5-c874c48135f2','783f0a67-34c0-4d0d-971c-f85e05f189b2'),('20d8aca3-124c-4a09-88a5-0ce02daac896',36,0,0,54000000,1500000,'911267d2-faf3-44d8-bba7-d9501d86f7a2','a3207d52-ac35-4686-a22b-5f20e60e7a34'),('350f8c6b-0e28-49fd-9725-31d512275f68',15,15,0,15000,1000,'6ab953fb-eb5b-4ecc-aaae-8f01b62fa9f7','43d75b3d-e777-4596-b48f-6a925b764f04'),('413d74a8-b99d-46b2-b775-26d09ea7e487',2,2,0,2000,1000,'2e25c21f-150a-4501-ad9a-78f9ff334e65','43d75b3d-e777-4596-b48f-6a925b764f04'),('4cb51c22-ef09-44d9-a8b2-673e66c5d149',5,0,0,50000,10000,'911267d2-faf3-44d8-bba7-d9501d86f7a2','4efddfa5-0153-403e-8461-3a2bc3aa74ee'),('54d9310e-7697-44d4-bc8b-152b12b48e1c',36,36,0,54000000,1500000,'52ba8dc7-caee-40c7-aae0-6b0302e60d56','a3207d52-ac35-4686-a22b-5f20e60e7a34'),('5aade432-f89a-436d-a42c-251fe8d5f951',1,0,0,350000,350000,'a00bbc7d-1800-473e-880c-c53c09665d3c','d4931e85-2f04-4d42-b95c-7b268159e52a'),('5d8148a0-7bfe-4080-969e-9badf3b4f5dd',15,0,0,15000,1000,'929f4f36-37b7-4dbf-80c5-bf41189b5aae','43d75b3d-e777-4596-b48f-6a925b764f04'),('66907793-d531-4953-85f6-3ea31589d406',14,0,0,14000,1000,'a00bbc7d-1800-473e-880c-c53c09665d3c','43d75b3d-e777-4596-b48f-6a925b764f04'),('7ba9d496-e437-43e7-9615-146e5f818dce',0,0,0,0,1000,'2f6589dc-cc67-45a3-821f-5b158ecffb7a','43d75b3d-e777-4596-b48f-6a925b764f04'),('7fcabc34-3ccc-45e7-8892-b9dddd5a5da4',11,0,0,396000,36000,'86c73509-e3b7-4a5a-ba75-1ab50f73b228','50c64a5d-291b-4e0e-a2e4-5a5eda5a9c5f'),('8761a859-dfa6-4617-816a-716b7de95d2e',15,0,0,15000,1000,'2a2e5f96-8c8c-4aab-9213-4fb86cf2e497','43d75b3d-e777-4596-b48f-6a925b764f04'),('91246219-fd45-46ab-87b5-e2c06ed82c6c',7,7,0,1750000,250000,'8d42ce72-720f-45d5-8ca5-c874c48135f2','61d5a8d1-0b60-4dab-a705-390769ffaecc'),('93afc9a9-354f-4a4a-81eb-0e7c9dc478b3',10,0,0,360000,36000,'a00bbc7d-1800-473e-880c-c53c09665d3c','50c64a5d-291b-4e0e-a2e4-5a5eda5a9c5f'),('9432a2ac-89a3-495c-8baa-eee9d9aff2a6',100,0,0,20000000,200000,'dba7629f-2819-4e3d-acec-89b7cadc5086','a82b7b59-2fc2-4485-b10c-171fcab90049'),('979a13c4-9992-4fc3-b1d0-e19410283e1d',15,15,0,15000,1000,'467cf2d3-b3e4-477b-8a6f-82938c7fba61','43d75b3d-e777-4596-b48f-6a925b764f04'),('998cd1ef-c879-463c-82be-f53f91363cef',10,10,0,10000,1000,'8d42ce72-720f-45d5-8ca5-c874c48135f2','43d75b3d-e777-4596-b48f-6a925b764f04'),('99e0c023-8592-4ac6-8df9-461e5f070f7d',15,0,0,15000,1000,'f8577cf5-1555-4ab2-b8dc-977e2b0392b7','43d75b3d-e777-4596-b48f-6a925b764f04'),('a1b1d9ca-d90d-414d-aadf-0ae141f3dc55',15,15,0,15000,1000,'b480ac48-27a3-47dc-a113-cf759a05f728','43d75b3d-e777-4596-b48f-6a925b764f04'),('acec6c1f-d33e-491d-b3f6-fe5227d6b241',15,15,0,3000000,200000,'8d42ce72-720f-45d5-8ca5-c874c48135f2','a82b7b59-2fc2-4485-b10c-171fcab90049'),('bf54d0da-25fc-43e4-9be3-26759c524beb',12,0,0,0,250000,'d0adc8aa-f764-4ea7-962c-fe057f494350','61d5a8d1-0b60-4dab-a705-390769ffaecc'),('c1d1986f-f50b-4664-a919-8a954aeea000',25,25,0,25000,1000,'52ba8dc7-caee-40c7-aae0-6b0302e60d56','43d75b3d-e777-4596-b48f-6a925b764f04'),('c753bd1b-28fc-4789-91ba-3afb54c0b3de',1,1,0,1000,1000,'1f7e100a-4d0d-4989-bbb8-6911508dc2ef','43d75b3d-e777-4596-b48f-6a925b764f04'),('d58ca33d-5394-49ba-8967-2d233b07a433',12,0,0,12000,1000,'86c73509-e3b7-4a5a-ba75-1ab50f73b228','43d75b3d-e777-4596-b48f-6a925b764f04'),('d8cef308-bda8-4752-b67b-02b76804c2b3',1,0,0,0,1000,'d0adc8aa-f764-4ea7-962c-fe057f494350','43d75b3d-e777-4596-b48f-6a925b764f04'),('da3b02ec-8500-42ff-a8b5-47dd188c38b8',0,0,0,0,1000,'cdacb2c5-9ace-4a23-bdb8-9cd59ece9c3a','43d75b3d-e777-4596-b48f-6a925b764f04'),('e101ebe2-5133-4f2b-a88c-10c3ed987308',10,0,0,10000,1000,'911267d2-faf3-44d8-bba7-d9501d86f7a2','43d75b3d-e777-4596-b48f-6a925b764f04'),('fdc76964-b61c-46e4-882f-34b0dc131346',2,0,0,500000,250000,'5c019884-9f93-4186-a01a-3725e0c73dfa','61d5a8d1-0b60-4dab-a705-390769ffaecc');
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
INSERT INTO `personas` VALUES ('',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('123','123','123',0,NULL,'123','123','M','123'),('12315464','jkjdok','',0,NULL,'jasok','','M','cedula'),('14136571','sanchez',NULL,NULL,NULL,'hector',NULL,NULL,'cedula'),('1564564','oijipoi','',0,NULL,'asjdpoijda','oo','M','ID_C'),('234243','234','324',0,NULL,'234','234','M','324'),('43243432','wer','',0,NULL,'wer','','M','wer'),('454564','jsajpo','',0,NULL,'persona',' ','F','ID_C'),('79845641','qweqew','qwewq',0,'2016-06-22','sdds','wqewq','F','ID_C'),('798456456','jljl','jljl',0,'2016-06-07','jkjlj','lj','M','ipoip'),('79879856456','LKJLKJ','LKJLKJ',0,'2016-06-01','nlmlknlJLKJLK','JLKJLKJ','M','ID_C'),('87987986','45564','',0,'2016-06-20','56456456','','M','ID_C'),('987454564','asd','',0,NULL,'asd','asd','M','sad');
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
INSERT INTO `productos` VALUES ('43d75b3d-e777-4596-b48f-6a925b764f04',NULL,NULL,NULL,'14136571',0,'2016-06-18 17:31:54','2016-06-23 23:05:29','14136571','Gondola de 3 metros x 5 metros','',100,1000,'dondola xxx',300,'PRODUCT_TYPE1','633c5eb3-1155-44a2-9785-4924747dd06a','7d8c25bb-358f-11e6-a718-507b9d9f0aad'),('4efddfa5-0153-403e-8461-3a2bc3aa74ee',NULL,NULL,NULL,'14136571',0,'2016-06-18 16:46:31','2016-07-03 09:09:31','14136571','mesas','',5000,10000,'wertyui',100,'PRODUCT_TYPE2','633c5eb3-1155-44a2-9785-4924747dd06a','7d8c25bb-358f-11e6-a718-507b9d9f0aad'),('50c64a5d-291b-4e0e-a2e4-5a5eda5a9c5f',NULL,NULL,NULL,'14136571',0,'2016-06-18 18:14:43','2016-06-18 18:14:43','14136571','usoijcapj','',0,36000,'nuevo producto',0,'PRODUCT_TYPE2','67ecf673-bfc3-4dc6-b576-8c6883142be9','7d8c25bb-358f-11e6-a718-507b9d9f0aad'),('61d5a8d1-0b60-4dab-a705-390769ffaecc',NULL,NULL,NULL,'14136571',0,'2016-06-18 17:01:05','2016-06-18 17:01:05','14136571','Gondolas de 2 X 5','',0,250000,'',100,'PRODUCT_TYPE1','633c5eb3-1155-44a2-9785-4924747dd06a','7d8c25bb-358f-11e6-a718-507b9d9f0aad'),('7649d3a2-faea-470b-86d6-5f76f74879a9',NULL,NULL,NULL,'14136571',1,'2016-06-18 17:41:39','2016-06-18 17:41:39','14136571','qwe','',0,234324,'qwe',0,'PRODUCT_TYPE1','633c5eb3-1155-44a2-9785-4924747dd06a','7d8c25bb-358f-11e6-a718-507b9d9f0aad'),('783f0a67-34c0-4d0d-971c-f85e05f189b2',NULL,NULL,NULL,'14136571',0,'2016-07-04 12:00:13','2016-07-04 12:02:06','14136571','ref3','ewrfewr',0,123456,'erf3',0,'PRODUCT_TYPE1','8e465b94-1350-4193-bdd3-922eb37f1303','aca83d9f-358f-11e6-a718-507b9d9f0aad'),('96c23cf9-a279-4cd3-a38a-9de37cb84344',NULL,NULL,NULL,'14136571',0,'2016-07-04 11:59:36','2016-07-04 11:59:36','14136571','ref222222222','sin converter',0,1234,'ref2',0,'PRODUCT_TYPE2','67ecf673-bfc3-4dc6-b576-8c6883142be9','7d8c332c-358f-11e6-a718-507b9d9f0aad'),('a3207d52-ac35-4686-a22b-5f20e60e7a34',NULL,NULL,NULL,'14136571',0,'2016-06-18 18:17:04','2016-06-21 12:55:29','14136571','Producto CC','asd',0,1500000,'Ref 12346',0,'PRODUCT_TYPE1','93bce9e2-5038-4687-af8c-0305dd8e5655','7d8c25bb-358f-11e6-a718-507b9d9f0aad'),('a82b7b59-2fc2-4485-b10c-171fcab90049',NULL,NULL,NULL,'14136571',0,'2016-06-21 18:02:18','2016-06-21 18:02:18','14136571','Gondola de  50 *20','',0,200000,'GON5',100,'PRODUCT_TYPE1','633c5eb3-1155-44a2-9785-4924747dd06a','7d8c25bb-358f-11e6-a718-507b9d9f0aad'),('cf36a656-5b4c-4748-876c-e6d4aae865c7',NULL,NULL,NULL,'14136571',0,'2016-07-04 10:21:45','2016-07-04 10:21:45','14136571','asd','asd',0,120,'asd',0,'com.co.hsg.innventa.beans.Parametros[ id=PRODUCT_TYPE1 ]','8e465b94-1350-4193-bdd3-922eb37f1303','7d8c25bb-358f-11e6-a718-507b9d9f0aad'),('d4931e85-2f04-4d42-b95c-7b268159e52a',NULL,NULL,NULL,'14136571',0,'2016-06-24 16:17:08','2016-06-24 16:17:08','14136571','gondola xxx','asdasd',0,350000,'ref 4252',0,'PRODUCT_TYPE1','633c5eb3-1155-44a2-9785-4924747dd06a','7d8c25bb-358f-11e6-a718-507b9d9f0aad'),('f1c036a1-ee3d-4bef-8214-071c1be5e3b5',NULL,NULL,NULL,'14136571',0,'2016-06-18 16:28:06','2016-06-18 16:28:06','14136571','igiuiu','sad',2500,350000,'rtiy',0,'PRODUCT_TYPE1','67ecf673-bfc3-4dc6-b576-8c6883142be9','7d8c25bb-358f-11e6-a718-507b9d9f0aad');
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
  CONSTRAINT `FK_remisiones_creador` FOREIGN KEY (`creado_por`) REFERENCES `personas` (`id`),
  CONSTRAINT `FK_remisiones_id_pedido` FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `remisiones`
--

LOCK TABLES `remisiones` WRITE;
/*!40000 ALTER TABLE `remisiones` DISABLE KEYS */;
INSERT INTO `remisiones` VALUES ('2646fb4c-2157-4f0a-8ce9-efa64feefa4b','RM00009','14136571','sapdudsai',0,NULL,'1dcd7183-2c8d-4379-9b50-225a647283a0','2016-07-04 08:30:07','2016-07-04 08:30:07','2016-07-21','14136571',0,'b480ac48-27a3-47dc-a113-cf759a05f728'),('544d77c3-017c-4422-843a-a85c1aa1047b','RM00011','14136571','',0,NULL,'13625479-9841-4047-adad-9741eed735d3','2016-07-04 17:11:01','2016-07-04 17:11:01','2016-07-13','14136571',54,'8d42ce72-720f-45d5-8ca5-c874c48135f2'),('730d3b7d-f700-4612-923e-7d4cc6d99764','RM00008','14136571','',0,'','06afb827-2cb4-4e08-abe4-757ba0dc69b5','2016-07-04 08:27:02','2016-07-04 08:27:02','2016-07-12','14136571',10,'1eb5be14-6460-4742-88c3-9cf1440397ae'),('ed9c1c2b-c260-4636-87b2-f98a16e4e9bb','RM00010','14136571','remision xxxksj',0,NULL,'13625479-9841-4047-adad-9741eed735d3','2016-07-04 08:35:48','2016-07-04 08:35:48','2016-07-06','14136571',0,'467cf2d3-b3e4-477b-8a6f-82938c7fba61');
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
INSERT INTO `remisiones_producto` VALUES ('37da5ee3-2de0-4512-ba89-3e27143bd6f6',12,0,'f1c036a1-ee3d-4bef-8214-071c1be5e3b5','544d77c3-017c-4422-843a-a85c1aa1047b'),('5c1f9bc1-9a8c-4a9f-a861-2ac62d8524a7',10,0,'43d75b3d-e777-4596-b48f-6a925b764f04','544d77c3-017c-4422-843a-a85c1aa1047b'),('803698cb-4c7c-4f48-9711-5c76237d7369',15,0,'a82b7b59-2fc2-4485-b10c-171fcab90049','730d3b7d-f700-4612-923e-7d4cc6d99764'),('9b7634d8-4605-4b88-b246-6724d82deeac',15,0,'43d75b3d-e777-4596-b48f-6a925b764f04','ed9c1c2b-c260-4636-87b2-f98a16e4e9bb'),('cf5d73c6-5560-427e-b73f-532462b1587a',10,0,'783f0a67-34c0-4d0d-971c-f85e05f189b2','544d77c3-017c-4422-843a-a85c1aa1047b'),('f4c12926-484f-481e-9700-8a2e33c64bb3',15,0,'a82b7b59-2fc2-4485-b10c-171fcab90049','544d77c3-017c-4422-843a-a85c1aa1047b'),('f7becfd4-f1bf-42fd-a34a-f7e616131bab',7,0,'61d5a8d1-0b60-4dab-a705-390769ffaecc','544d77c3-017c-4422-843a-a85c1aa1047b');
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `terceros`
--

LOCK TABLES `terceros` WRITE;
/*!40000 ALTER TABLE `terceros` DISABLE KEYS */;
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
INSERT INTO `usuarios` VALUES ('',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('admin',NULL,NULL,NULL,NULL,NULL,'admin','14136571');
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

-- Dump completed on 2016-07-12 22:35:38
