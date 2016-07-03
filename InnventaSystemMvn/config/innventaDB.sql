-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 25-06-2016 a las 16:04:17
-- Versión del servidor: 10.1.10-MariaDB
-- Versión de PHP: 5.6.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `innventadb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `acl_acciones`
--

CREATE TABLE `acl_acciones` (
  `id` varchar(255) NOT NULL,
  `eliminado` smallint(6) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `acl_roles`
--

CREATE TABLE `acl_roles` (
  `ID` varchar(255) NOT NULL,
  `creado_por` varchar(255) DEFAULT NULL,
  `DESCRIPCION` varchar(255) DEFAULT NULL,
  `ELIMINADO` smallint(6) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `modificado_por` varchar(255) DEFAULT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `acl_roles_accion`
--

CREATE TABLE `acl_roles_accion` (
  `ID` int(11) NOT NULL,
  `ACCION` varchar(255) DEFAULT NULL,
  `ELIMINADO` smallint(6) DEFAULT NULL,
  `ROL` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `acl_roles_usuarios`
--

CREATE TABLE `acl_roles_usuarios` (
  `ID` int(11) NOT NULL,
  `ELIMINADO` smallint(6) DEFAULT NULL,
  `USUARIO` varchar(255) DEFAULT NULL,
  `rol` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `atributos`
--

CREATE TABLE `atributos` (
  `ID` varchar(255) NOT NULL,
  `ELIMINADO` smallint(6) DEFAULT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `ID` varchar(255) NOT NULL,
  `ELIMINADO` smallint(6) DEFAULT NULL,
  `IMAGEN` longblob,
  `MODULO` varchar(255) DEFAULT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL,
  `idpadre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`ID`, `ELIMINADO`, `IMAGEN`, `MODULO`, `NOMBRE`, `idpadre`) VALUES
('633c5eb3-1155-44a2-9785-4924747dd06a', 0, NULL, 'PRODUCTOS', 'METALICO', '92701bd1-0759-43e3-836c-e6cb6f13f3b7'),
('67ecf673-bfc3-4dc6-b576-8c6883142be9', 0, NULL, 'PRODUCTOS', 'GONDOLA', NULL),
('8e465b94-1350-4193-bdd3-922eb37f1303', 0, NULL, 'PRODUCTOS', 'MADERA', '92701bd1-0759-43e3-836c-e6cb6f13f3b7'),
('92701bd1-0759-43e3-836c-e6cb6f13f3b7', 0, NULL, 'PRODUCTOS', 'METALICO', NULL),
('93bce9e2-5038-4687-af8c-0305dd8e5655', 0, NULL, 'PRODUCTOS', 'LAMINA 2 X 3', '92701bd1-0759-43e3-836c-e6cb6f13f3b7'),
('a7efdb57-3122-4b6a-97f4-7553b49c3f19', 0, NULL, 'PRODUCTOS', 'PRODUCTOS DE VENTA', NULL),
('a8fc8306-a10e-42d6-a522-9b87cae0e630', 0, NULL, 'PRODUCTOS', 'INSUMO   XXX', '92701bd1-0759-43e3-836c-e6cb6f13f3b7'),
('c7afcdff-cdb6-4760-8d9b-fd432897ebd5', 1, NULL, 'PRODUCTOS', 'POS', 'a7efdb57-3122-4b6a-97f4-7553b49c3f19'),
('d14d5e03-aa5e-4508-b1e5-c2326b01aff5', 0, NULL, 'PRODUCTOS', 'GONDOLA ESPECIAL', '67ecf673-bfc3-4dc6-b576-8c6883142be9'),
('e3328939-fc0a-4a1c-b397-670874e60f48', 1, NULL, 'PRODUCTOS', 'insumo xy', NULL),
('f902bf18-b91d-46c3-8300-8e96f51df3ec', 0, NULL, 'PRODUCTOS', 'madera BALSO', '8e465b94-1350-4193-bdd3-922eb37f1303');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias_impuestos`
--

CREATE TABLE `categorias_impuestos` (
  `ID` varchar(255) NOT NULL,
  `ELIMINADO` smallint(6) DEFAULT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuentas`
--

CREATE TABLE `cuentas` (
  `id` varchar(255) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `tipo_cliente` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `lista_precios` varchar(255) DEFAULT NULL,
  `creado_por` varchar(255) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `modificado_por` varchar(255) DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `eliminado` smallint(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cuentas`
--

INSERT INTO `cuentas` (`id`, `nombre`, `tipo_cliente`, `estado`, `lista_precios`, `creado_por`, `fecha_creacion`, `modificado_por`, `fecha_modificacion`, `eliminado`) VALUES
('165169e8-83b8-450a-9531-4be0a458c473', '23432', NULL, 'INACTIVA', NULL, '14136571', '2016-06-19 08:25:26', '14136571', '2016-06-19 08:25:26', 1),
('234324', 'razon sdaoyodsf', NULL, 'ACTIVA', NULL, '14136571', '2016-06-19 08:34:53', '14136571', '2016-06-19 08:34:53', 0),
('47987564', 'kjlkjok', NULL, 'ACTIVA', NULL, '14136571', '2016-06-20 22:03:54', '14136571', '2016-06-20 22:03:54', 0),
('6e492f7e-58b7-4c22-9ac7-7286c22c9e8c', '213321', NULL, 'ACTIVA', NULL, '14136571', '2016-06-19 08:17:40', '14136571', '2016-06-19 08:17:40', 1),
('7984564561', 'empresa yyu', NULL, 'ACTIVA', NULL, '14136571', '2016-06-21 18:03:47', '14136571', '2016-06-21 18:03:47', 0),
('7987', 'dasd ', NULL, 'ACTIVA', NULL, '14136571', '2016-06-20 21:55:38', '14136571', '2016-06-20 21:55:38', 0),
('798798321798', 'Empresa xxx', NULL, 'ACTIVA', NULL, '14136571', '2016-06-19 08:39:48', '14136571', '2016-06-19 08:39:48', 0),
('878789', 'oioi', NULL, 'ACTIVA', NULL, '14136571', '2016-06-20 22:13:34', '14136571', '2016-06-20 22:13:34', 0),
('9874656', 'empresa yyyy', NULL, 'ACTIVA', NULL, '14136571', '2016-06-19 09:22:14', '14136571', '2016-06-19 23:49:00', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuentas_contactos`
--

CREATE TABLE `cuentas_contactos` (
  `id_persona` varchar(255) DEFAULT NULL,
  `id_cuenta` varchar(255) DEFAULT NULL,
  `CARGO` varchar(255) DEFAULT NULL,
  `ELIMINADO` smallint(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cuentas_contactos`
--

INSERT INTO `cuentas_contactos` (`id_persona`, `id_cuenta`, `CARGO`, `ELIMINADO`) VALUES
('987454564', '9874656', 'cargooo', 0),
('798456456', '7987', 'AP_DIR', 0),
('79879856456', '47987564', 'AP_SEC', 0),
('87987986', '878789', 'AP_SEC', 0),
('79845641', '7984564561', 'AP_COM', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuentas_direccion`
--

CREATE TABLE `cuentas_direccion` (
  `ID` varchar(255) NOT NULL,
  `DIRECCION` varchar(255) DEFAULT NULL,
  `ELIMINADO` smallint(6) DEFAULT NULL,
  `id_cliente` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estados`
--

CREATE TABLE `estados` (
  `ID` varchar(255) NOT NULL,
  `ELIMINADO` smallint(6) DEFAULT NULL,
  `ESTADO` varchar(255) DEFAULT NULL,
  `MODULO` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `estados`
--

INSERT INTO `estados` (`ID`, `ELIMINADO`, `ESTADO`, `MODULO`) VALUES
('06afb827-2cb4-4e08-abe4-757ba0dc69b5', 0, 'TERMINADA', 'ORDERS'),
('13625479-9841-4047-adad-9741eed735d3', 0, 'SOLICITADA', 'ORDERS'),
('1dcd7183-2c8d-4379-9b50-225a647283a0', 0, 'PAGADA', 'ORDERS'),
('226fd06d-05eb-40d5-8c04-ee0f0a2e277a', 0, 'ACEPTADA', 'ORDERS'),
('6228d8d2-7456-43ce-b7ba-47fabc2a142f', 0, 'FINALIZADA', 'ORDERS'),
('8d88996b-464d-46db-900e-e24bae8508fa', 0, 'ENTREGADA', 'ORDERS'),
('a400bb03-1310-4b12-89fd-0e62bdc792ea', 0, 'ENTREGA PARCIAL', 'ORDERS'),
('a861b528-b9fc-4876-90d8-9e7a6dd33251', 0, 'EN PROCESO', 'ORDERS'),
('ACTIVA', 0, 'ACTIVA', 'ACCOUNTS'),
('bdb2e05f-ec7d-492b-9c36-43722c40a9e7', 1, 'SOLICITADA', 'ORDERS'),
('INACTIVA', 0, 'INACTIVA', 'ACCOUNTS');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `impuestos`
--

CREATE TABLE `impuestos` (
  `ID` varchar(255) NOT NULL,
  `CATEGORIA` varchar(255) DEFAULT NULL,
  `creado_por` varchar(255) DEFAULT NULL,
  `ELIMINADO` smallint(6) DEFAULT NULL,
  `modificado_por` varchar(255) DEFAULT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL,
  `TARIFA` double DEFAULT NULL,
  `valido_desde` datetime DEFAULT NULL,
  `impuesto_padre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lineas_impuestos`
--

CREATE TABLE `lineas_impuestos` (
  `ID` varchar(255) NOT NULL,
  `BASE` double DEFAULT NULL,
  `ELIMINADO` smallint(6) DEFAULT NULL,
  `IMPUESTO` varchar(255) DEFAULT NULL,
  `MONTO` double DEFAULT NULL,
  `RECIBO` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lista_precios`
--

CREATE TABLE `lista_precios` (
  `ID` varchar(255) NOT NULL,
  `ELIMINADO` smallint(6) DEFAULT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL,
  `PORCENTAJE` tinyint(1) DEFAULT '0',
  `VALOR_PORCENTAJE` smallint(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pagos`
--

CREATE TABLE `pagos` (
  `ID` varchar(255) NOT NULL,
  `ELIMINADO` smallint(6) DEFAULT NULL,
  `id_trans` varchar(255) DEFAULT NULL,
  `PAGO` varchar(255) DEFAULT NULL,
  `RECIBO` varchar(255) DEFAULT NULL,
  `TOTAL` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `parametros`
--

CREATE TABLE `parametros` (
  `ID` varchar(255) NOT NULL,
  `CLAVE1` varchar(255) DEFAULT NULL,
  `CLAVE2` varchar(255) DEFAULT NULL,
  `ELIMINADO` smallint(6) DEFAULT NULL,
  `PARAMETRO` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `parametros`
--

INSERT INTO `parametros` (`ID`, `CLAVE1`, `CLAVE2`, `ELIMINADO`, `PARAMETRO`) VALUES
('09f23661-2537-4ebb-b397-b6d01cc38c68', 'RM', '1', 0, 'CONF_REMISSIONS'),
('1', 'PRODUCTO DE VENTA', NULL, 0, 'PRODUCT_TYPE'),
('2', 'INSUMO', NULL, 0, 'PRODUCT_TYPE'),
('86b0b849-4f2a-410a-8540-618608983eaf', 'OC', '11', 0, 'CONF_ORDERS'),
('AP_ASI', 'ASISTENTE', NULL, 0, 'ACCOUNTS_POSITION'),
('AP_COM', 'COMERCIAL', NULL, 0, 'ACCOUNTS_POSITION'),
('AP_DIR', 'DIRECTOR', NULL, 0, 'ACCOUNTS_POSITION'),
('AP_GER', 'GERENTE', NULL, 0, 'ACCOUNTS_POSITION'),
('AP_OPE', 'OPERARIO', NULL, 0, 'ACCOUNTS_POSITION'),
('AP_SEC', 'SECRETEARIA', NULL, 0, 'ACCOUNTS_POSITION'),
('AP_TEC', 'TECNICO', NULL, 0, 'ACCOUNTS_POSITION'),
('ID_C', 'CEDULA', NULL, 0, 'IDENTIFICATION'),
('ID_NIT', 'NIT', NULL, 0, 'IDENTIFICATION');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos`
--

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
  `id_cliente` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `pedidos`
--

INSERT INTO `pedidos` (`id`, `cantidad_pendientes`, `cantidad_total`, `creado_por`, `descripcion`, `eliminado`, `fecha_creacion`, `fecha_entrega`, `fecha_modificacion`, `fecha_pedido`, `modificado_por`, `observaciones`, `referencia`, `estado`, `id_cliente`) VALUES
('2a2e5f96-8c8c-4aab-9213-4fb86cf2e497', 0, 15, '14136571', '', 0, '2016-06-21 18:49:54', '2016-06-01', '2016-06-21 18:49:54', '2016-06-02', '14136571', '', 'OC00005', '13625479-9841-4047-adad-9741eed735d3', '7984564561'),
('2f6589dc-cc67-45a3-821f-5b158ecffb7a', 0, 0, '14136571', '', 0, '2016-06-21 22:52:18', NULL, '2016-06-21 22:52:18', '2016-06-09', '14136571', '', 'OC00008', '06afb827-2cb4-4e08-abe4-757ba0dc69b5', '234324'),
('5c019884-9f93-4186-a01a-3725e0c73dfa', 0, 2, '14136571', '', 0, '2016-06-21 22:24:17', '2016-06-21', '2016-06-21 22:24:17', '2016-06-15', '14136571', '', 'OC00006', '13625479-9841-4047-adad-9741eed735d3', '9874656'),
('66f9ec68-0e5a-4664-a4f9-5325200f58ec', 0, 0, '14136571', '', 0, '2016-06-21 23:01:21', NULL, '2016-06-21 23:01:21', '2016-06-15', '14136571', '', 'OC00009', '13625479-9841-4047-adad-9741eed735d3', '47987564'),
('911267d2-faf3-44d8-bba7-d9501d86f7a2', 0, 51, '14136571', '', 0, '2016-06-23 23:01:35', '2016-06-23', '2016-06-23 23:01:35', '2016-06-23', '14136571', '', 'OC00011', '13625479-9841-4047-adad-9741eed735d3', '798798321798'),
('cdacb2c5-9ace-4a23-bdb8-9cd59ece9c3a', 0, 0, '14136571', 'asdk{ñkdsa', 0, '2016-06-20 22:16:45', '2016-06-15', '2016-06-20 22:16:45', '2016-06-13', '14136571', 'observaciones', 'OC00003', '13625479-9841-4047-adad-9741eed735d3', '9874656'),
('dba7629f-2819-4e3d-acec-89b7cadc5086', 0, 0, '14136571', 'adssad', 0, '2016-06-21 18:06:48', '2016-09-30', '2016-06-21 18:06:48', '2016-06-21', '14136571', '', 'OC00004', '13625479-9841-4047-adad-9741eed735d3', '9874656'),
('edfbec23-7593-460c-b533-c20324c4b167', 0, 0, '14136571', '', 0, '2016-06-21 23:04:19', NULL, '2016-06-21 23:04:19', '2016-06-08', '14136571', '', 'OC00010', '13625479-9841-4047-adad-9741eed735d3', '7987'),
('fe7f0706-1335-4b83-a1ce-111783688b8c', 0, 0, '14136571', '', 0, '2016-06-21 22:45:25', NULL, '2016-06-21 22:45:25', '2016-06-21', '14136571', '', 'OC00007', '13625479-9841-4047-adad-9741eed735d3', '234324');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos_producto`
--

CREATE TABLE `pedidos_producto` (
  `ID` varchar(255) NOT NULL,
  `CANTIDAD` int(11) DEFAULT NULL,
  `cantidad_entregada` int(11) DEFAULT '0',
  `ELIMINADO` smallint(6) DEFAULT NULL,
  `valor_total` double DEFAULT NULL,
  `valor_unitario` double DEFAULT NULL,
  `id_pedido` varchar(255) DEFAULT NULL,
  `id_producto` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `pedidos_producto`
--

INSERT INTO `pedidos_producto` (`ID`, `CANTIDAD`, `cantidad_entregada`, `ELIMINADO`, `valor_total`, `valor_unitario`, `id_pedido`, `id_producto`) VALUES
('02ca4aeb-e233-4709-8f01-fd37ed6f2c35', 0, 0, 0, 0, 1000, 'fe7f0706-1335-4b83-a1ce-111783688b8c', '43d75b3d-e777-4596-b48f-6a925b764f04'),
('20d8aca3-124c-4a09-88a5-0ce02daac896', 36, 0, 0, 54000000, 1500000, '911267d2-faf3-44d8-bba7-d9501d86f7a2', 'a3207d52-ac35-4686-a22b-5f20e60e7a34'),
('4cb51c22-ef09-44d9-a8b2-673e66c5d149', 5, 0, 0, 50000, 10000, '911267d2-faf3-44d8-bba7-d9501d86f7a2', '4efddfa5-0153-403e-8461-3a2bc3aa74ee'),
('7ba9d496-e437-43e7-9615-146e5f818dce', 0, 0, 0, 0, 1000, '2f6589dc-cc67-45a3-821f-5b158ecffb7a', '43d75b3d-e777-4596-b48f-6a925b764f04'),
('8761a859-dfa6-4617-816a-716b7de95d2e', 15, 0, 0, 15000, 1000, '2a2e5f96-8c8c-4aab-9213-4fb86cf2e497', '43d75b3d-e777-4596-b48f-6a925b764f04'),
('9432a2ac-89a3-495c-8baa-eee9d9aff2a6', 100, 0, 0, 20000000, 200000, 'dba7629f-2819-4e3d-acec-89b7cadc5086', 'a82b7b59-2fc2-4485-b10c-171fcab90049'),
('da3b02ec-8500-42ff-a8b5-47dd188c38b8', 0, 0, 0, 0, 1000, 'cdacb2c5-9ace-4a23-bdb8-9cd59ece9c3a', '43d75b3d-e777-4596-b48f-6a925b764f04'),
('e101ebe2-5133-4f2b-a88c-10c3ed987308', 10, 0, 0, 10000, 1000, '911267d2-faf3-44d8-bba7-d9501d86f7a2', '43d75b3d-e777-4596-b48f-6a925b764f04'),
('fdc76964-b61c-46e4-882f-34b0dc131346', 2, 0, 0, 500000, 250000, '5c019884-9f93-4186-a01a-3725e0c73dfa', '61d5a8d1-0b60-4dab-a705-390769ffaecc');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personas`
--

CREATE TABLE `personas` (
  `id` varchar(255) NOT NULL,
  `apellido1` varchar(255) DEFAULT NULL,
  `apellido2` varchar(255) DEFAULT NULL,
  `eliminado` smallint(6) DEFAULT NULL,
  `fecha_nacim` date DEFAULT NULL,
  `nombre1` varchar(255) DEFAULT NULL,
  `nombre2` varchar(255) DEFAULT NULL,
  `sexo` varchar(255) DEFAULT NULL,
  `tipo_documento` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `personas`
--

INSERT INTO `personas` (`id`, `apellido1`, `apellido2`, `eliminado`, `fecha_nacim`, `nombre1`, `nombre2`, `sexo`, `tipo_documento`) VALUES
('123', '123', '123', 0, NULL, '123', '123', 'M', '123'),
('12315464', 'jkjdok', '', 0, NULL, 'jasok', '', 'M', 'cedula'),
('14136571', 'sanchez', NULL, NULL, NULL, 'hector', NULL, NULL, 'cedula'),
('234243', '234', '324', 0, NULL, '234', '234', 'M', '324'),
('43243432', 'wer', '', 0, NULL, 'wer', '', 'M', 'wer'),
('79845641', 'qweqew', 'qwewq', 0, '2016-06-22', 'sdds', 'wqewq', 'F', 'ID_C'),
('798456456', 'jljl', 'jljl', 0, '2016-06-07', 'jkjlj', 'lj', 'M', 'ipoip'),
('79879856456', 'LKJLKJ', 'LKJLKJ', 0, '2016-06-01', 'nlmlknlJLKJLK', 'JLKJLKJ', 'M', 'ID_C'),
('87987986', '45564', '', 0, '2016-06-20', '56456456', '', 'M', 'ID_C'),
('987454564', 'asd', '', 0, NULL, 'asd', 'asd', 'M', 'sad');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personas_tels`
--

CREATE TABLE `personas_tels` (
  `id` varchar(255) NOT NULL,
  `eliminado` smallint(6) DEFAULT NULL,
  `extension` varchar(255) DEFAULT NULL,
  `id_persona` varchar(255) DEFAULT NULL,
  `principal` tinyint(1) DEFAULT '0',
  `telefono` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona_dirs`
--

CREATE TABLE `persona_dirs` (
  `ID` varchar(255) NOT NULL,
  `DIRECCION` varchar(255) DEFAULT NULL,
  `ELIMINADO` smallint(6) DEFAULT NULL,
  `id_persona` int(11) DEFAULT NULL,
  `OBSERVACION` varchar(255) DEFAULT NULL,
  `PRINCIPAL` tinyint(1) DEFAULT '0',
  `TIPO` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona_mails`
--

CREATE TABLE `persona_mails` (
  `ID` varchar(255) NOT NULL,
  `ELIMINADO` datetime DEFAULT NULL,
  `id_persona` varchar(255) DEFAULT NULL,
  `MAIL` varchar(255) DEFAULT NULL,
  `PRINCIPAL` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

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
  `unidad_medida` varchar(36) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id`, `atributo`, `categoria_impuesto`, `codigo`, `creado_por`, `eliminado`, `fecha_creacion`, `fecha_modificacion`, `modificado_por`, `nombre`, `observaciones`, `precio_compra`, `precio_venta`, `referencia`, `stock`, `tipo_codigo`, `categoria`, `unidad_medida`) VALUES
('43d75b3d-e777-4596-b48f-6a925b764f04', NULL, NULL, NULL, '14136571', 0, '2016-06-18 17:31:54', '2016-06-23 23:05:29', '14136571', 'Gondola de 3 metros x 5 metros', '', 100, 1000, 'dondola xxx', 300, '1', '633c5eb3-1155-44a2-9785-4924747dd06a', '7d8c25bb-358f-11e6-a718-507b9d9f0aad'),
('4efddfa5-0153-403e-8461-3a2bc3aa74ee', NULL, NULL, NULL, '14136571', 0, '2016-06-18 16:46:31', '2016-06-18 16:46:31', '14136571', 'mesas', '', 5000, 10000, 'wertyui', 100, '1', '633c5eb3-1155-44a2-9785-4924747dd06a', '7d8c25bb-358f-11e6-a718-507b9d9f0aad'),
('50c64a5d-291b-4e0e-a2e4-5a5eda5a9c5f', NULL, NULL, NULL, '14136571', 0, '2016-06-18 18:14:43', '2016-06-18 18:14:43', '14136571', 'usoijcapj', '', 0, 36000, 'nuevo producto', 0, '2', '67ecf673-bfc3-4dc6-b576-8c6883142be9', '7d8c25bb-358f-11e6-a718-507b9d9f0aad'),
('61d5a8d1-0b60-4dab-a705-390769ffaecc', NULL, NULL, NULL, '14136571', 0, '2016-06-18 17:01:05', '2016-06-18 17:01:05', '14136571', 'Gondolas de 2 X 5', '', 0, 250000, '', 100, '1', '633c5eb3-1155-44a2-9785-4924747dd06a', '7d8c25bb-358f-11e6-a718-507b9d9f0aad'),
('7649d3a2-faea-470b-86d6-5f76f74879a9', NULL, NULL, NULL, '14136571', 1, '2016-06-18 17:41:39', '2016-06-18 17:41:39', '14136571', 'qwe', '', 0, 234324, 'qwe', 0, '2', '633c5eb3-1155-44a2-9785-4924747dd06a', '7d8c25bb-358f-11e6-a718-507b9d9f0aad'),
('a3207d52-ac35-4686-a22b-5f20e60e7a34', NULL, NULL, NULL, '14136571', 0, '2016-06-18 18:17:04', '2016-06-21 12:55:29', '14136571', 'Producto CC', 'asd', 0, 1500000, 'Ref 12346', 0, '1', '93bce9e2-5038-4687-af8c-0305dd8e5655', '7d8c25bb-358f-11e6-a718-507b9d9f0aad'),
('a82b7b59-2fc2-4485-b10c-171fcab90049', NULL, NULL, NULL, '14136571', 0, '2016-06-21 18:02:18', '2016-06-21 18:02:18', '14136571', 'Gondola de  50 *20', '', 0, 200000, 'GON5', 100, '1', '633c5eb3-1155-44a2-9785-4924747dd06a', '7d8c25bb-358f-11e6-a718-507b9d9f0aad'),
('d4931e85-2f04-4d42-b95c-7b268159e52a', NULL, NULL, NULL, '14136571', 0, '2016-06-24 16:17:08', '2016-06-24 16:17:08', '14136571', 'gondola xxx', 'asdasd', 0, 350000, 'ref 4252', 0, '1', '633c5eb3-1155-44a2-9785-4924747dd06a', '7d8c25bb-358f-11e6-a718-507b9d9f0aad'),
('f1c036a1-ee3d-4bef-8214-071c1be5e3b5', NULL, NULL, NULL, '14136571', 0, '2016-06-18 16:28:06', '2016-06-18 16:28:06', '14136571', 'igiuiu', 'sad', 2500, 350000, 'rtiy', 0, '1', '67ecf673-bfc3-4dc6-b576-8c6883142be9', '7d8c25bb-358f-11e6-a718-507b9d9f0aad');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `recibos`
--

CREATE TABLE `recibos` (
  `id` varchar(255) NOT NULL,
  `atributos` int(11) DEFAULT NULL,
  `eliminado` smallint(6) DEFAULT NULL,
  `fecha_fin` datetime DEFAULT NULL,
  `fecha_inicio` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `remisiones`
--

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
  `id_pedido` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `remisiones`
--

INSERT INTO `remisiones` (`id`, `referencia`, `creado_por`, `observaciones`, `eliminado`, `entregado_a`, `ESTADO`, `fecha_creacion`, `fecha_modificacion`, `fecha_remision`, `modificado_por`, `total_productos`, `id_pedido`) VALUES
('061ebd20-0045-4345-bc14-c54e2c1e73ee', 'RM00002', '14136571', 'listo tiene 3 items', 0, '', 'a400bb03-1310-4b12-89fd-0e62bdc792ea', '2016-06-25 00:56:00', '2016-06-25 00:56:00', '2016-06-24', '14136571', 0, '911267d2-faf3-44d8-bba7-d9501d86f7a2'),
('2f5557f4-e1e1-4b51-81a8-0b246a3c67c4', NULL, '14136571', '', 0, '', 'sfdsf', '2016-06-21 18:07:32', '2016-06-21 18:07:32', '2016-06-15', '14136571', 200, 'dba7629f-2819-4e3d-acec-89b7cadc5086'),
('3c9706a2-62d1-4b47-bb6b-df761fafe6aa', NULL, '14136571', 'extra', 0, '', '13625479-9841-4047-adad-9741eed735d3', '2016-06-24 19:10:20', '2016-06-24 19:10:20', '2016-06-25', '14136571', 0, '911267d2-faf3-44d8-bba7-d9501d86f7a2'),
('a6dcbe54-97be-4280-87ca-ee3e70f2ffff', 'RM00002', '14136571', '', 0, '', '8d88996b-464d-46db-900e-e24bae8508fa', '2016-06-25 00:57:24', '2016-06-25 00:57:24', '2016-06-23', '14136571', 0, 'fe7f0706-1335-4b83-a1ce-111783688b8c'),
('efe73f2d-9a46-4d69-b64e-63f9c85fc2da', NULL, '14136571', 'sdasd', 0, '', '13625479-9841-4047-adad-9741eed735d3', '2016-06-24 16:53:00', '2016-06-24 16:53:00', '2016-06-21', '14136571', 0, '911267d2-faf3-44d8-bba7-d9501d86f7a2'),
('f0431492-06be-403f-af22-8c790b940e0e', NULL, '14136571', 'esto son detalles de la remision ', 0, '', '13625479-9841-4047-adad-9741eed735d3', '2016-06-24 15:36:41', '2016-06-24 15:36:41', '2016-06-23', '14136571', 0, '911267d2-faf3-44d8-bba7-d9501d86f7a2');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `remisiones_producto`
--

CREATE TABLE `remisiones_producto` (
  `id` varchar(255) NOT NULL,
  `cantidad` smallint(6) DEFAULT NULL,
  `eliminado` smallint(6) DEFAULT NULL,
  `id_producto` varchar(255) DEFAULT NULL,
  `id_remision` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `remisiones_producto`
--

INSERT INTO `remisiones_producto` (`id`, `cantidad`, `eliminado`, `id_producto`, `id_remision`) VALUES
('13586dec-df3c-484f-8f2c-951e9c6d9613', 5, 0, '43d75b3d-e777-4596-b48f-6a925b764f04', 'f0431492-06be-403f-af22-8c790b940e0e'),
('2abad6af-fd72-4750-b181-67475d7bac1f', 36, 0, 'a3207d52-ac35-4686-a22b-5f20e60e7a34', '061ebd20-0045-4345-bc14-c54e2c1e73ee'),
('5809a34c-1b8b-4e29-a719-6430d252a903', 10, 0, '43d75b3d-e777-4596-b48f-6a925b764f04', '061ebd20-0045-4345-bc14-c54e2c1e73ee'),
('725a82b0-cb26-4d51-9341-a7adfc581aca', 5, 0, '4efddfa5-0153-403e-8461-3a2bc3aa74ee', '061ebd20-0045-4345-bc14-c54e2c1e73ee'),
('808249e7-54c4-4939-9762-6f0759208840', 36, 0, 'a3207d52-ac35-4686-a22b-5f20e60e7a34', '3c9706a2-62d1-4b47-bb6b-df761fafe6aa'),
('c48ab710-4e22-45d4-8cd4-29abf4386261', 23, 0, '43d75b3d-e777-4596-b48f-6a925b764f04', 'a6dcbe54-97be-4280-87ca-ee3e70f2ffff'),
('e82db713-f625-4821-b5a9-5ee60e608c21', 12, 0, 'a3207d52-ac35-4686-a22b-5f20e60e7a34', 'efe73f2d-9a46-4d69-b64e-63f9c85fc2da');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `terceros`
--

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
  `telefono` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos_cliente`
--

CREATE TABLE `tipos_cliente` (
  `id` varchar(255) NOT NULL,
  `eliminado` smallint(6) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos_dir`
--

CREATE TABLE `tipos_dir` (
  `id` varchar(255) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos_tel`
--

CREATE TABLE `tipos_tel` (
  `id` varchar(255) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `unidades_medida`
--

CREATE TABLE `unidades_medida` (
  `id` varchar(36) NOT NULL,
  `nombre` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `unidades_medida`
--

INSERT INTO `unidades_medida` (`id`, `nombre`) VALUES
('7d8c25bb-358f-11e6-a718-507b9d9f0aad', 'UNIDADES'),
('7d8c332c-358f-11e6-a718-507b9d9f0aad', 'GRAMOS'),
('aca83d9f-358f-11e6-a718-507b9d9f0aad', 'KILOS'),
('aca84b06-358f-11e6-a718-507b9d9f0aad', 'PULGADAS'),
('c3e2c2ea-358f-11e6-a718-507b9d9f0aad', 'METROS'),
('c3e2cf81-358f-11e6-a718-507b9d9f0aad', 'CENTIMETROS');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `usuario` varchar(255) NOT NULL,
  `creado_por` varchar(255) DEFAULT NULL,
  `eliminado` smallint(6) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `modificado_por` varchar(255) DEFAULT NULL,
  `passw` varchar(255) DEFAULT NULL,
  `persona` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`usuario`, `creado_por`, `eliminado`, `fecha_creacion`, `fecha_modificacion`, `modificado_por`, `passw`, `persona`) VALUES
('admin', NULL, NULL, NULL, NULL, NULL, 'admin', '14136571');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `acl_acciones`
--
ALTER TABLE `acl_acciones`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `acl_roles`
--
ALTER TABLE `acl_roles`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `acl_roles_accion`
--
ALTER TABLE `acl_roles_accion`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `acl_roles_usuarios`
--
ALTER TABLE `acl_roles_usuarios`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_acl_roles_usuarios_rol` (`rol`);

--
-- Indices de la tabla `atributos`
--
ALTER TABLE `atributos`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_categorias_idpadre` (`idpadre`);

--
-- Indices de la tabla `categorias_impuestos`
--
ALTER TABLE `categorias_impuestos`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `cuentas`
--
ALTER TABLE `cuentas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_cuentas_estado` (`estado`),
  ADD KEY `FK_cuentas_lista_precios` (`lista_precios`);

--
-- Indices de la tabla `cuentas_contactos`
--
ALTER TABLE `cuentas_contactos`
  ADD KEY `FK_cuentas_contactos_id_cliente` (`id_cuenta`),
  ADD KEY `FK_cuentas_contactos_id_persona` (`id_persona`);

--
-- Indices de la tabla `cuentas_direccion`
--
ALTER TABLE `cuentas_direccion`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_cuentas_direccion_id_cliente` (`id_cliente`);

--
-- Indices de la tabla `estados`
--
ALTER TABLE `estados`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `impuestos`
--
ALTER TABLE `impuestos`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_impuestos_impuesto_padre` (`impuesto_padre`);

--
-- Indices de la tabla `lineas_impuestos`
--
ALTER TABLE `lineas_impuestos`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `lista_precios`
--
ALTER TABLE `lista_precios`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `pagos`
--
ALTER TABLE `pagos`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `parametros`
--
ALTER TABLE `parametros`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_pedidos_id_cliente` (`id_cliente`),
  ADD KEY `FK_pedidos_estado` (`estado`);

--
-- Indices de la tabla `pedidos_producto`
--
ALTER TABLE `pedidos_producto`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_pedidos_producto_id_producto` (`id_producto`),
  ADD KEY `FK_pedidos_producto_id_pedido` (`id_pedido`);

--
-- Indices de la tabla `personas`
--
ALTER TABLE `personas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `personas_tels`
--
ALTER TABLE `personas_tels`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `persona_dirs`
--
ALTER TABLE `persona_dirs`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `persona_mails`
--
ALTER TABLE `persona_mails`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_productos_categoria` (`categoria`),
  ADD KEY `FK_unidad_medida` (`unidad_medida`);

--
-- Indices de la tabla `recibos`
--
ALTER TABLE `recibos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `remisiones`
--
ALTER TABLE `remisiones`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_remisiones_id_pedido` (`id_pedido`),
  ADD KEY `ESTADO` (`ESTADO`);

--
-- Indices de la tabla `remisiones_producto`
--
ALTER TABLE `remisiones_producto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_remisiones_producto_id_remision` (`id_remision`),
  ADD KEY `FK_producto_id` (`id_producto`) USING BTREE;

--
-- Indices de la tabla `terceros`
--
ALTER TABLE `terceros`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tipos_cliente`
--
ALTER TABLE `tipos_cliente`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tipos_dir`
--
ALTER TABLE `tipos_dir`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tipos_tel`
--
ALTER TABLE `tipos_tel`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `unidades_medida`
--
ALTER TABLE `unidades_medida`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`usuario`),
  ADD KEY `FK_usuarios_persona` (`persona`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `acl_roles_accion`
--
ALTER TABLE `acl_roles_accion`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `acl_roles_usuarios`
--
ALTER TABLE `acl_roles_usuarios`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `acl_roles_usuarios`
--
ALTER TABLE `acl_roles_usuarios`
  ADD CONSTRAINT `FK_acl_roles_usuarios_rol` FOREIGN KEY (`rol`) REFERENCES `acl_roles` (`ID`);

--
-- Filtros para la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD CONSTRAINT `FK_categorias_idpadre` FOREIGN KEY (`idpadre`) REFERENCES `categorias` (`ID`);

--
-- Filtros para la tabla `cuentas_contactos`
--
ALTER TABLE `cuentas_contactos`
  ADD CONSTRAINT `FK_cuentas_contactos_id_cuenta` FOREIGN KEY (`id_cuenta`) REFERENCES `cuentas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_cuentas_contactos_id_persona` FOREIGN KEY (`id_persona`) REFERENCES `personas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `cuentas_direccion`
--
ALTER TABLE `cuentas_direccion`
  ADD CONSTRAINT `FK_cuentas_direccion_id_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `cuentas` (`id`);

--
-- Filtros para la tabla `impuestos`
--
ALTER TABLE `impuestos`
  ADD CONSTRAINT `FK_impuestos_impuesto_padre` FOREIGN KEY (`impuesto_padre`) REFERENCES `impuestos` (`ID`);

--
-- Filtros para la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD CONSTRAINT `FK_pedidos_estado` FOREIGN KEY (`estado`) REFERENCES `estados` (`ID`),
  ADD CONSTRAINT `FK_pedidos_id_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `cuentas` (`id`);

--
-- Filtros para la tabla `pedidos_producto`
--
ALTER TABLE `pedidos_producto`
  ADD CONSTRAINT `FK_pedidos_producto_id_pedido` FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id`),
  ADD CONSTRAINT `FK_pedidos_producto_id_producto` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id`);

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `FK_productos_categoria` FOREIGN KEY (`categoria`) REFERENCES `categorias` (`ID`),
  ADD CONSTRAINT `FK_unidades_medida` FOREIGN KEY (`unidad_medida`) REFERENCES `unidades_medida` (`id`);

--
-- Filtros para la tabla `remisiones`
--
ALTER TABLE `remisiones`
  ADD CONSTRAINT `FK_remisiones_id_pedido` FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id`);

--
-- Filtros para la tabla `remisiones_producto`
--
ALTER TABLE `remisiones_producto`
  ADD CONSTRAINT `FK_id_producto` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id`),
  ADD CONSTRAINT `FK_remisiones_producto_id_remision` FOREIGN KEY (`id_remision`) REFERENCES `remisiones` (`id`);

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `FK_usuarios_persona` FOREIGN KEY (`persona`) REFERENCES `personas` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
