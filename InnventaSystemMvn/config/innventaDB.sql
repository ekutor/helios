-- MySQL dump 10.13  Distrib 5.5.47, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: innventaDB
-- ------------------------------------------------------
-- Server version	5.5.47-0ubuntu0.14.04.1
create database innventaDB;
use innventaDB;
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
INSERT INTO `categorias` VALUES ('2958b9ea-2a87-4328-a446-64d9cc96c4ac',0,NULL,'PRODUCTOS','PRODUCTO DE VENTA',NULL),('698664ea-3be5-43c7-a2ed-b80dea3f6135',0,NULL,'PRODUCTOS','pinturas xxx',NULL),('741624b9-36a9-4328-8a1e-b2cbed874c1a',0,NULL,'PRODUCTOS','produti ysd',NULL),('8648684f-b42c-4888-b853-12e6c21c7baa',0,NULL,'PRODUCTOS','INSUMO',NULL),('ae7d8422-6759-40ef-8c40-4c66ad8ddb4f',0,NULL,'PRODUCTOS','dfwfwdsf',NULL),('b3f284f4-8554-47c7-a83b-ead703d694e2',0,NULL,'PRODUCTOS','PINTURAS',NULL),('f5392028-1728-4d7c-ad34-9d3af05238d0',0,NULL,'PRODUCTOS','tstrsdyr',NULL);
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
  `creado_por` varchar(255) DEFAULT NULL,
  `eliminado` smallint(6) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `modificado_por` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `tipo_cliente` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `lista_precios` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cuentas_estado` (`estado`),
  KEY `FK_cuentas_lista_precios` (`lista_precios`),
  CONSTRAINT `FK_cuentas_estado` FOREIGN KEY (`estado`) REFERENCES `estados` (`ID`),
  CONSTRAINT `FK_cuentas_lista_precios` FOREIGN KEY (`lista_precios`) REFERENCES `lista_precios` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuentas`
--

LOCK TABLES `cuentas` WRITE;
/*!40000 ALTER TABLE `cuentas` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuentas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuentas_contactos`
--

DROP TABLE IF EXISTS `cuentas_contactos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuentas_contactos` (
  `ID` varchar(255) NOT NULL,
  `CARGO` varchar(255) DEFAULT NULL,
  `ELIMINADO` smallint(6) DEFAULT NULL,
  `id_cliente` varchar(255) DEFAULT NULL,
  `id_persona` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_cuentas_contactos_id_cliente` (`id_cliente`),
  KEY `FK_cuentas_contactos_id_persona` (`id_persona`),
  CONSTRAINT `FK_cuentas_contactos_id_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `cuentas` (`id`),
  CONSTRAINT `FK_cuentas_contactos_id_persona` FOREIGN KEY (`id_persona`) REFERENCES `personas` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuentas_contactos`
--

LOCK TABLES `cuentas_contactos` WRITE;
/*!40000 ALTER TABLE `cuentas_contactos` DISABLE KEYS */;
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
INSERT INTO `estados` VALUES ('9b0184ab-ce97-47c3-bacb-9e9bcba08eed',0,'ENTREGADO','PEDIDOS');
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
INSERT INTO `parametros` VALUES ('bb79851c-14fd-4a29-afa8-decc6c97c6da','CO','1',0,NULL);
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
  `referncia` varchar(255) DEFAULT NULL,
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
INSERT INTO `personas` VALUES ('14136571','sanchez',NULL,NULL,NULL,'hector',NULL,NULL,'cedula');
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
  PRIMARY KEY (`id`),
  KEY `FK_productos_categoria` (`categoria`),
  CONSTRAINT `FK_productos_categoria` FOREIGN KEY (`categoria`) REFERENCES `categorias` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
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
  `creado_por` varchar(255) DEFAULT NULL,
  `detalles` varchar(255) DEFAULT NULL,
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
  CONSTRAINT `FK_remisiones_id_pedido` FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `remisiones`
--

LOCK TABLES `remisiones` WRITE;
/*!40000 ALTER TABLE `remisiones` DISABLE KEYS */;
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
  CONSTRAINT `FK_remisiones_producto_id_remision` FOREIGN KEY (`id_remision`) REFERENCES `remisiones` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `remisiones_producto`
--

LOCK TABLES `remisiones_producto` WRITE;
/*!40000 ALTER TABLE `remisiones_producto` DISABLE KEYS */;
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
INSERT INTO `usuarios` VALUES ('administrador',NULL,NULL,NULL,NULL,NULL,'administrador','14136571');
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

-- Dump completed on 2016-05-02  6:36:12
