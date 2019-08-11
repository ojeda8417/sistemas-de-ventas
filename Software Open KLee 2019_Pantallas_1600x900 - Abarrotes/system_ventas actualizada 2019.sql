-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 08-05-2019 a las 14:19:22
-- Versión del servidor: 5.7.19
-- Versión de PHP: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `system_ventas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuentas_cobrar`
--

DROP TABLE IF EXISTS `cuentas_cobrar`;
CREATE TABLE IF NOT EXISTS `cuentas_cobrar` (
  `num_fact` varchar(8) COLLATE utf8_swedish_ci NOT NULL,
  `cliente` varchar(100) COLLATE utf8_swedish_ci DEFAULT NULL,
  `total_credito` double(7,2) DEFAULT NULL,
  `tipo_pago` varchar(50) COLLATE utf8_swedish_ci DEFAULT NULL,
  `abonos` double(7,2) DEFAULT NULL,
  `saldos` double(7,2) DEFAULT NULL,
  `fecha_pago` date DEFAULT NULL,
  `fecha_cancelar` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Volcado de datos para la tabla `cuentas_cobrar`
--

INSERT INTO `cuentas_cobrar` (`num_fact`, `cliente`, `total_credito`, `tipo_pago`, `abonos`, `saldos`, `fecha_pago`, `fecha_cancelar`) VALUES
('00000005', 'Jose Romero Calderón', 62.47, 'Abono', 17.47, 0.00, '2017-03-10', '2017-02-11'),
('00000005', 'Jose Romero Calderón', 62.47, 'Abono', 17.47, 0.00, '2017-03-10', '2017-02-11'),
('00000008', 'Público en General', 41.86, 'Efectivo', 0.58, 0.00, '2017-02-10', '2016-11-30'),
('00000011', 'Público en General', 23.37, 'Efectivo', 23.37, 0.00, '2017-02-10', '2016-12-04'),
('00000004', 'Juana Páez Romero', 50.49, 'Abono', 2.00, 0.00, '2017-02-17', '2017-02-17'),
('00000005', 'Jose Romero Calderón', 62.47, 'Abono', 17.47, 0.00, '2017-03-10', '2017-02-11');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuentas_pagar`
--

DROP TABLE IF EXISTS `cuentas_pagar`;
CREATE TABLE IF NOT EXISTS `cuentas_pagar` (
  `num_fact` varchar(8) COLLATE utf8_swedish_ci DEFAULT NULL,
  `proveedor` varchar(100) COLLATE utf8_swedish_ci DEFAULT NULL,
  `total_credito` double(7,2) DEFAULT NULL,
  `tipo_pago` varchar(20) COLLATE utf8_swedish_ci DEFAULT NULL,
  `abonos` double(7,2) DEFAULT NULL,
  `saldos` double(7,2) DEFAULT NULL,
  `fecha_pago` date DEFAULT NULL,
  `fecha_cancelar` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Volcado de datos para la tabla `cuentas_pagar`
--

INSERT INTO `cuentas_pagar` (`num_fact`, `proveedor`, `total_credito`, `tipo_pago`, `abonos`, `saldos`, `fecha_pago`, `fecha_cancelar`) VALUES
('00000002', 'Juan Perez Gonzales', 25.62, 'Efectivo', 25.62, 0.00, '2016-11-27', '2016-11-29'),
('00000003', 'Leonardo Guaman Cortéz', 60.08, 'Efectivo', 20.08, 0.00, '2016-11-28', '2016-11-28');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallecompras`
--

DROP TABLE IF EXISTS `detallecompras`;
CREATE TABLE IF NOT EXISTS `detallecompras` (
  `num_comp` varchar(8) COLLATE utf8_swedish_ci NOT NULL,
  `cod_pro` varchar(15) COLLATE utf8_swedish_ci DEFAULT NULL,
  `des_pro` varchar(100) COLLATE utf8_swedish_ci DEFAULT NULL,
  `cant_pro` double(5,2) DEFAULT NULL,
  `pre_unit` double(7,0) DEFAULT NULL,
  `desct` double(7,2) DEFAULT NULL,
  `total` double(7,0) DEFAULT NULL,
  `tipo_comp` varchar(100) COLLATE utf8_swedish_ci DEFAULT NULL,
  `proveedor` varchar(100) COLLATE utf8_swedish_ci DEFAULT NULL,
  `empleado` varchar(100) COLLATE utf8_swedish_ci DEFAULT NULL,
  `hora` varchar(15) COLLATE utf8_swedish_ci DEFAULT NULL,
  `fecha` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Volcado de datos para la tabla `detallecompras`
--

INSERT INTO `detallecompras` (`num_comp`, `cod_pro`, `des_pro`, `cant_pro`, `pre_unit`, `desct`, `total`, `tipo_comp`, `proveedor`, `empleado`, `hora`, `fecha`) VALUES
('00000001', '5121154810', 'Agua vivant botella', 1.00, 0., 0.00, 0., 'Orden de compra', 'Juan Perez Gonzales', 'Netfex Programmer - Systems', '10:32:45 PM', '2016-11-19'),
('00000001', '2147483647', 'Cafe Clásico', 1.00, 4, 0.00, 4, 'Orden de compra', 'Juan Perez Gonzales', 'Netfex Programmer - Systems', '10:32:45 PM', '2016-11-19'),
('00000001', '1320221450', 'Detergente OMO', 1.00, 8, 0.04, 8, 'Orden de compra', 'Juan Perez Gonzales', 'Netfex Programmer - Systems', '10:32:45 PM', '2016-11-19'),
('00000001', '2147644217', 'Aceite La Favorita', 1.00, 1, 0.00, 1, 'Orden de compra', 'Juan Perez Gonzales', 'Netfex Programmer - Systems', '10:32:45 PM', '2016-11-19'),
('00000001', '2147783647', 'Achiote', 1.00, 1, 0.00, 1, 'Orden de compra', 'Juan Perez Gonzales', 'Netfex Programmer - Systems', '10:32:45 PM', '2016-11-19'),
('00000001', '2147850000', 'Clorox', 1.00, 1, 0.00, 1, 'Orden de compra', 'Juan Perez Gonzales', 'Netfex Programmer - Systems', '10:32:45 PM', '2016-11-19'),
('00000001', '2147928847', 'Café Pres 2', 1.00, 1, 0.00, 1, 'Orden de compra', 'Juan Perez Gonzales', 'Netfex Programmer - Systems', '10:32:45 PM', '2016-11-19'),
('00000001', '2148083450', 'Aliño pequeño 470ml', 1.00, 2, 0.00, 2, 'Orden de compra', 'Juan Perez Gonzales', 'Netfex Programmer - Systems', '10:32:45 PM', '2016-11-19'),
('00000001', '2148183647', 'PinoKlin + Suavitel (Combo)', 1.00, 4, 0.08, 3, 'Orden de compra', 'Juan Perez Gonzales', 'Netfex Programmer - Systems', '10:32:45 PM', '2016-11-19'),
('00000001', '4336528442', 'Cepillos ZigZag 2x1', 1.00, 1, 0.05, 1, 'Orden de compra', 'Juan Perez Gonzales', 'Netfex Programmer - Systems', '10:32:45 PM', '2016-11-19'),
('00000001', '2148383647', 'Aliño pequeño 470ml', 1.00, 2, 0.00, 2, 'Orden de compra', 'Juan Perez Gonzales', 'Netfex Programmer - Systems', '10:32:45 PM', '2016-11-19'),
('00000001', '2148483647', 'Cifrut CitrusPunch', 1.00, 1, 0.00, 1, 'Orden de compra', 'Juan Perez Gonzales', 'Netfex Programmer - Systems', '10:32:45 PM', '2016-11-19'),
('00000001', '1603302854', 'Shampoo Sedal Flores', 1.00, 2, 0.08, 2, 'Orden de compra', 'Juan Perez Gonzales', 'Netfex Programmer - Systems', '10:32:45 PM', '2016-11-19'),
('00000002', '5121154810', 'Agua vivant botella', 1.00, 0., 0.00, 0., 'Orden de compra', 'Juan Perez Gonzales', 'Netfex Programmer - Systems', '09:08:28 AM', '2016-11-27'),
('00000002', '2147483647', 'Cafe Clásico', 1.00, 4, 0.00, 4, 'Orden de compra', 'Juan Perez Gonzales', 'Netfex Programmer - Systems', '09:08:29 AM', '2016-11-27'),
('00000002', '1320221450', 'Detergente OMO', 1.00, 8, 0.04, 8, 'Orden de compra', 'Juan Perez Gonzales', 'Netfex Programmer - Systems', '09:08:29 AM', '2016-11-27'),
('00000002', '2147644217', 'Aceite La Favorita', 1.00, 1, 0.00, 1, 'Orden de compra', 'Juan Perez Gonzales', 'Netfex Programmer - Systems', '09:08:29 AM', '2016-11-27'),
('00000002', '2147783647', 'Achiote', 1.00, 1, 0.00, 1, 'Orden de compra', 'Juan Perez Gonzales', 'Netfex Programmer - Systems', '09:08:29 AM', '2016-11-27'),
('00000002', '2147850000', 'Clorox', 1.00, 1, 0.00, 1, 'Orden de compra', 'Juan Perez Gonzales', 'Netfex Programmer - Systems', '09:08:29 AM', '2016-11-27'),
('00000002', '2148083450', 'Aliño pequeño 470ml', 1.00, 2, 0.00, 2, 'Orden de compra', 'Juan Perez Gonzales', 'Netfex Programmer - Systems', '09:08:29 AM', '2016-11-27'),
('00000002', '2148183647', 'PinoKlin + Suavitel (Combo)', 1.00, 4, 0.08, 3, 'Orden de compra', 'Juan Perez Gonzales', 'Netfex Programmer - Systems', '09:08:29 AM', '2016-11-27'),
('00000002', '4336528442', 'Cepillos ZigZag 2x1', 1.00, 1, 0.05, 1, 'Orden de compra', 'Juan Perez Gonzales', 'Netfex Programmer - Systems', '09:08:29 AM', '2016-11-27'),
('00000003', '7664503740', 'Agua mineral Botella', 20.00, 0., 0.00, 9, 'Orden de compra', 'Leonardo Guaman Cortéz', 'Netfex Programmer - Systems', '09:07:46 PM', '2016-11-28'),
('00000003', '9240003506703', 'Combo yoghurt x2', 20.00, 2, 0.05, 44, 'Orden de compra', 'Leonardo Guaman Cortéz', 'Netfex Programmer - Systems', '09:07:46 PM', '2016-11-28'),
('00000004', '2122483640', 'Tips ambiental', 50.00, 2, 0.20, 73, 'Orden de compra', 'Juan Perez Gonzales', 'Netfex Programmer - Systems', '07:00:16 PM', '2016-11-30'),
('00000005', '1234567890', 'Prueba - Fósforos', 10.00, 1, 0.00, 10, 'Orden de compra', 'Juan Perez Gonzales', 'Netfex Programmer - Systems', '01:37:00 PM', '2016-12-01'),
('00000006', '5121154810', 'Agua vivant botella', 1.00, 0., 0.00, 0., 'Factura', 'Juan Perez Gonzales', 'Fernando Margarito Gonzalez Rodriguez', '09:02:34 AM', '2017-02-10'),
('00000006', '2147483647', 'Cafe Clásico', 1.00, 4, 0.00, 4, 'Factura', 'Juan Perez Gonzales', 'Fernando Margarito Gonzalez Rodriguez', '09:02:34 AM', '2017-02-10'),
('00000006', '1320221450', 'Detergente OMO', 1.00, 8, 0.04, 8, 'Factura', 'Juan Perez Gonzales', 'Fernando Margarito Gonzalez Rodriguez', '09:02:34 AM', '2017-02-10'),
('00000006', '2147644217', 'Aceite La Favorita', 1.00, 1, 0.00, 1, 'Factura', 'Juan Perez Gonzales', 'Fernando Margarito Gonzalez Rodriguez', '09:02:34 AM', '2017-02-10'),
('00000006', '2147783647', 'Achiote', 1.00, 1, 0.00, 1, 'Factura', 'Juan Perez Gonzales', 'Fernando Margarito Gonzalez Rodriguez', '09:02:34 AM', '2017-02-10'),
('00000006', '2147850000', 'Clorox', 1.00, 1, 0.00, 1, 'Factura', 'Juan Perez Gonzales', 'Fernando Margarito Gonzalez Rodriguez', '09:02:34 AM', '2017-02-10'),
('00000006', '2147928847', 'Café Pres 2', 1.00, 1, 0.00, 1, 'Factura', 'Juan Perez Gonzales', 'Fernando Margarito Gonzalez Rodriguez', '09:02:34 AM', '2017-02-10'),
('00000006', '2148083450', 'Aliño pequeño 470ml', 1.00, 2, 0.00, 2, 'Factura', 'Juan Perez Gonzales', 'Fernando Margarito Gonzalez Rodriguez', '09:02:34 AM', '2017-02-10'),
('00000006', '2148183647', 'PinoKlin + Suavitel (Combo)', 1.00, 4, 0.08, 3, 'Factura', 'Juan Perez Gonzales', 'Fernando Margarito Gonzalez Rodriguez', '09:02:34 AM', '2017-02-10'),
('00000006', '4336528442', 'Cepillos ZigZag 2x1', 1.00, 1, 0.05, 1, 'Factura', 'Juan Perez Gonzales', 'Fernando Margarito Gonzalez Rodriguez', '09:02:34 AM', '2017-02-10'),
('00000006', '2148383647', 'Aliño pequeño 470ml', 1.00, 2, 0.00, 2, 'Factura', 'Juan Perez Gonzales', 'Fernando Margarito Gonzalez Rodriguez', '09:02:34 AM', '2017-02-10'),
('00000006', '2122483640', 'Tips ambiental', 1.00, 2, 0.00, 2, 'Factura', 'Juan Perez Gonzales', 'Fernando Margarito Gonzalez Rodriguez', '09:02:34 AM', '2017-02-10'),
('00000006', '2148563544', 'FresKlin Detergente', 1.00, 2, 0.05, 2, 'Factura', 'Juan Perez Gonzales', 'Fernando Margarito Gonzalez Rodriguez', '09:02:34 AM', '2017-02-10'),
('00000006', '1320221458', 'Jabon líquido', 1.00, 4, 0.00, 4, 'Factura', 'Juan Perez Gonzales', 'Fernando Margarito Gonzalez Rodriguez', '09:02:34 AM', '2017-02-10'),
('00000006', '2148783647', 'Sopa Instantanea Lonchys', 1.00, 3, 0.00, 3, 'Factura', 'Juan Perez Gonzales', 'Fernando Margarito Gonzalez Rodriguez', '09:02:34 AM', '2017-02-10'),
('00000006', '1234887890', 'Shampoo Shoulders', 1.00, 5, 0.00, 5, 'Factura', 'Juan Perez Gonzales', 'Fernando Margarito Gonzalez Rodriguez', '09:02:34 AM', '2017-02-10'),
('00000006', '2148563541', 'Cigarrillos Lider', 1.00, 2, 0.00, 2, 'Factura', 'Juan Perez Gonzales', 'Fernando Margarito Gonzalez Rodriguez', '09:02:34 AM', '2017-02-10'),
('00000006', '2144493647', 'Cigarrillos Malboto', 1.00, 2, 0.08, 2, 'Factura', 'Juan Perez Gonzales', 'Fernando Margarito Gonzalez Rodriguez', '09:02:34 AM', '2017-02-10'),
('00000006', '2144593647', 'Pony Malta', 1.00, 1, 0.00, 1, 'Factura', 'Juan Perez Gonzales', 'Fernando Margarito Gonzalez Rodriguez', '09:02:34 AM', '2017-02-10'),
('00000006', '2144693647', 'Tomate rojo', 1.00, 0., 0.00, 0., 'Factura', 'Juan Perez Gonzales', 'Fernando Margarito Gonzalez Rodriguez', '09:02:34 AM', '2017-02-10'),
('00000006', '2144793647', 'Clavos de olor en frasco', 1.00, 1, 0.00, 1, 'Factura', 'Juan Perez Gonzales', 'Fernando Margarito Gonzalez Rodriguez', '09:02:34 AM', '2017-09-08'),
('00000006', '5596403357092', 'Supan Bimbo', 1.00, 1, 0.00, 1, 'Factura', 'Juan Perez Gonzales', 'Fernando Margarito Gonzalez Rodriguez', '09:02:34 AM', '2017-09-08'),
('00000006', '9663450325009', 'Leche condensada', 1.00, 2, 0.00, 2, 'Factura', 'Juan Perez Gonzales', 'Fernando Margarito Gonzalez Rodriguez', '09:02:34 AM', '2017-09-08'),
('00000006', '7552034035001', 'Yoghurt Frutilla', 1.00, 1, 0.00, 1, 'Factura', 'Juan Perez Gonzales', 'Fernando Margarito Gonzalez Rodriguez', '09:02:34 AM', '2017-09-08'),
('00000006', '1234567890', 'Prueba - Fósforos', 5.00, 1, 0.00, 5, 'Factura', 'Juan Perez Gonzales', 'Fernando Margarito Gonzalez Rodriguez', '09:02:34 AM', '2017-09-08'),
('00000007', '5121154810', 'Agua vivant botella', 5.00, 1, 0.00, 3, 'Factura', 'Juan Perez Gonzales', 'Fernando Margarito Gonzalez Rodriguez', '09:49:58 PM', '2019-01-05'),
('00000008', '2144493647', 'Cigarrillos Malboto', 1.00, 2, 0.08, 2, 'Factura', 'Ronald Perez Castillo', 'Fernando Margarito Gonzalez Rodriguez', '21:01:517 PM', '2019-01-06'),
('00000008', '7642230173', 'Alcohol Weir 255ml', 1.00, 2, 0.00, 2, 'Factura', 'Ronald Perez Castillo', 'Fernando Margarito Gonzalez Rodriguez', '21:01:517 PM', '2019-01-06'),
('00000008', '7000620156', 'Jugo de soya Ades', 1.00, 4, 0.05, 4, 'Factura', 'Ronald Perez Castillo', 'Fernando Margarito Gonzalez Rodriguez', '21:01:517 PM', '2019-01-06'),
('00000008', '8334069907002', 'Detergente Liquido ACE', 1.00, 5, 0.00, 5, 'Factura', 'Ronald Perez Castillo', 'Fernando Margarito Gonzalez Rodriguez', '21:01:517 PM', '2019-01-06'),
('00000008', '8862034509706', 'Yohurt Svelty', 1.00, 1, 0.00, 1, 'Factura', 'Ronald Perez Castillo', 'Fernando Margarito Gonzalez Rodriguez', '21:01:517 PM', '2019-01-06'),
('00000008', '9240003506703', 'Combo yoghurt x2', 1.00, 2, 0.05, 2, 'Factura', 'Ronald Perez Castillo', 'Fernando Margarito Gonzalez Rodriguez', '21:01:517 PM', '2019-01-06'),
('00000008', '4204606670599', 'Yoghurt Toni Mix', 1.00, 1, 0.00, 1, 'Factura', 'Ronald Perez Castillo', 'Fernando Margarito Gonzalez Rodriguez', '21:01:517 PM', '2019-01-06'),
('00000009', '2147644217', 'Aceite La Favorita', 1.00, 1, 0.00, 1, 'Orden de compra', 'Juan Perez Gonzales', 'Fernando Margarito Gonzalez Rodriguez', '21:06:13 PM', '2019-01-24'),
('00000010', '2276553490227', 'Papas Fritas Naturales', 12.00, 1, 0.00, 12, 'Factura', 'Juan Perez Gonzales', 'Fernando Margarito Gonzalez Rodriguez', '22:08:19 PM', '2019-02-05'),
('00000011', '2147483647', 'Cafe Clásico', 12.00, 4, 0.00, 54, 'Factura', 'Juan Perez Gonzales', 'Fernando Margarito Gonzalez Rodriguez', '20:42:31 PM', '2019-02-09'),
('00000012', '2147483647', 'Cafe Clásico', 12.00, 4, 0.00, 54, 'Orden de compra', 'Juan Perez Gonzales', 'Fernando Margarito Gonzalez Rodriguez', '20:59:16 PM', '2019-02-09'),
('00000013', '2147850000', 'Clorox', 5.00, 1, 0.00, 4, 'Orden de compra', 'Juan Perez Gonzales', 'Fernando Margarito Gonzalez Rodriguez', '12:43:46 PM', '2019-02-10'),
('00000013', '9962740350', 'Mortadela grande ', 5.00, 8, 0.00, 41, 'Orden de compra', 'Juan Perez Gonzales', 'Fernando Margarito Gonzalez Rodriguez', '12:43:46 PM', '2019-02-10'),
('00000013', '7552033640257', 'Yoghurt Fat Free', 5.00, 1, 0.00, 4, 'Orden de compra', 'Juan Perez Gonzales', 'Fernando Margarito Gonzalez Rodriguez', '12:43:46 PM', '2019-02-10'),
('00000013', '1234567890', 'Fósforos', 5.00, 0., 0.00, 0., 'Orden de compra', 'Juan Perez Gonzales', 'Fernando Margarito Gonzalez Rodriguez', '12:43:46 PM', '2019-02-10'),
('00000014', '144239231726', 'Doritos Queso', 1.00, 0., 0.00, 0., 'Orden de compra', 'Romano Gonzales Duarte', 'nawar', '09:26:41 AM', '2019-04-05'),
('00000014', '2941173975006', 'agua minerales', 1.00, 2500, 0.00, 2500, 'Orden de compra', 'Romano Gonzales Duarte', 'nawar', '09:26:41 AM', '2019-04-05');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallectacobrar`
--

DROP TABLE IF EXISTS `detallectacobrar`;
CREATE TABLE IF NOT EXISTS `detallectacobrar` (
  `numfactura` varchar(10) COLLATE utf8_swedish_ci DEFAULT NULL,
  `cliente` varchar(100) COLLATE utf8_swedish_ci DEFAULT NULL,
  `credito` double(7,2) DEFAULT NULL,
  `ultimoabono` double(7,2) DEFAULT NULL,
  `saldo` double(7,2) DEFAULT NULL,
  `abonoactual` double(7,2) DEFAULT NULL,
  `pendiente` double(7,2) DEFAULT NULL,
  `fechapago` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Volcado de datos para la tabla `detallectacobrar`
--

INSERT INTO `detallectacobrar` (`numfactura`, `cliente`, `credito`, `ultimoabono`, `saldo`, `abonoactual`, `pendiente`, `fechapago`) VALUES
('00000005', 'Público en General', 35.73, 25.00, 10.73, 10.73, 0.00, '2016-11-26'),
('00000008', 'Público en General', 41.86, 0.00, 41.86, 26.28, 15.58, '2016-11-29'),
('00000008', 'Público en General', 41.86, 26.28, 15.58, 15.00, 0.58, '2016-11-28'),
('00000011', 'Público en General', 23.37, 0.00, 23.37, 23.37, 0.00, '2017-02-10'),
('00000008', 'Público en General', 41.86, 15.00, 0.58, 0.58, 0.00, '2017-02-10'),
('00000004', 'Juana Páez Romero', 50.49, 25.00, 25.49, 10.00, 15.49, '2017-02-11'),
('00000004', 'Juana Páez Romero', 50.49, 10.00, 15.49, 8.74, 6.75, '2017-02-12'),
('00000004', 'Juana Páez Romero', 50.49, 8.74, 6.75, 1.00, 5.75, '2017-02-13'),
('00000004', 'Juana Páez Romero', 50.49, 1.00, 5.75, 2.00, 3.75, '2017-02-14'),
('00000004', 'Juana Páez Romero', 50.49, 2.00, 3.75, 1.50, 2.25, '2017-02-15'),
('00000004', 'Juana Páez Romero', 50.49, 1.50, 2.25, 0.25, 2.00, '2017-02-16'),
('00000004', 'Juana Páez Romero', 50.49, 0.25, 2.00, 2.00, 0.00, '2017-02-17'),
('00000005', 'Jose Romero Calderón', 62.47, 30.00, 32.47, 15.00, 17.47, '2017-02-10'),
('00000005', 'Jose Romero Calderón', 62.47, 15.00, 17.47, 17.47, 0.00, '2017-03-10');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallectapagar`
--

DROP TABLE IF EXISTS `detallectapagar`;
CREATE TABLE IF NOT EXISTS `detallectapagar` (
  `numfactura` varchar(10) COLLATE utf8_swedish_ci DEFAULT NULL,
  `proveedor` varchar(100) COLLATE utf8_swedish_ci DEFAULT NULL,
  `credito` double(7,2) DEFAULT NULL,
  `ultimoabono` double(7,2) DEFAULT NULL,
  `saldo` double(7,2) DEFAULT NULL,
  `abonoactual` double(7,2) DEFAULT NULL,
  `pendiente` double(7,2) DEFAULT NULL,
  `fechapago` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Volcado de datos para la tabla `detallectapagar`
--

INSERT INTO `detallectapagar` (`numfactura`, `proveedor`, `credito`, `ultimoabono`, `saldo`, `abonoactual`, `pendiente`, `fechapago`) VALUES
('00000002', 'Juan Perez Gonzales', 25.62, 0.00, 25.62, 25.62, 0.00, '2016-11-27'),
('00000003', 'Leonardo Guaman Cortéz', 60.08, 0.00, 60.08, 40.00, 20.08, '2016-11-29'),
('00000003', 'Leonardo Guaman Cortéz', 60.08, 40.00, 20.08, 20.08, 0.00, '2016-11-28');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalleventas`
--

DROP TABLE IF EXISTS `detalleventas`;
CREATE TABLE IF NOT EXISTS `detalleventas` (
  `num_fact` varchar(8) COLLATE utf8_swedish_ci NOT NULL,
  `cod_pro` varchar(15) COLLATE utf8_swedish_ci NOT NULL,
  `des_comp` varchar(100) COLLATE utf8_swedish_ci DEFAULT NULL,
  `cant_comp` double(5,2) DEFAULT NULL,
  `pre_comp` double(7,0) DEFAULT NULL,
  `descuento` double(7,2) DEFAULT NULL,
  `pre_tot` double(7,0) DEFAULT NULL,
  `formapago` varchar(50) COLLATE utf8_swedish_ci DEFAULT NULL,
  `referencia` varchar(20) COLLATE utf8_swedish_ci DEFAULT NULL,
  `numreferencia` int(13) DEFAULT NULL,
  `usuario` varchar(100) COLLATE utf8_swedish_ci DEFAULT NULL,
  `hora` varchar(15) COLLATE utf8_swedish_ci NOT NULL,
  `fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Volcado de datos para la tabla `detalleventas`
--

INSERT INTO `detalleventas` (`num_fact`, `cod_pro`, `des_comp`, `cant_comp`, `pre_comp`, `descuento`, `pre_tot`, `formapago`, `referencia`, `numreferencia`, `usuario`, `hora`, `fecha`) VALUES
('00000002', '2147483647', 'Nescafe Clàsico mediano', 1.00, 1, 0.00, 1, 'Contado', ' ', 0, 'Fernando Margarito Gonzalez Rodriguez', '23:23:26 PM', '2019-02-09'),
('00000002', '2147644217', 'Aceite La Favorita', 1.00, 1, 0.00, 1, 'Contado', ' ', 0, 'Fernando Margarito Gonzalez Rodriguez', '23:23:26 PM', '2019-02-09'),
('00000003', '5121154810', 'Agua vivant botella', 4.00, 1, 0.00, 2, 'Contado', ' ', 0, 'Fernando Margarito Gonzalez Rodriguez', '12:38:55 PM', '2019-02-10'),
('00000004', '2147644217', 'Aceite La Favorita', 1.00, 1, 0.00, 1, 'Crédito', ' ', 0, 'Fernando Margarito Gonzalez Rodriguez', '13:02:01 PM', '2019-02-10'),
('00000004', '2147783647', 'Achiote', 1.00, 1, 0.00, 1, 'Crédito', ' ', 0, 'Fernando Margarito Gonzalez Rodriguez', '13:02:01 PM', '2019-02-10'),
('00000004', '2147718364', 'Atun Isabel Pack 3', 1.00, 2, 0.00, 2, 'Crédito', ' ', 0, 'Fernando Margarito Gonzalez Rodriguez', '13:02:02 PM', '2019-02-10'),
('00000004', '2276553490227', 'Papas Fritas Naturales', 1.00, 1, 0.00, 1, 'Crédito', ' ', 0, 'Fernando Margarito Gonzalez Rodriguez', '13:02:02 PM', '2019-02-10'),
('00000004', '2148183647', 'PinoKlin + Suavitel (Combo)', 1.00, 4, 0.08, 3, 'Crédito', ' ', 0, 'Fernando Margarito Gonzalez Rodriguez', '13:02:02 PM', '2019-02-10'),
('00000004', '2144893647', 'Mayonesa Maggi 200 g', 1.00, 2, 0.00, 2, 'Crédito', ' ', 0, 'Fernando Margarito Gonzalez Rodriguez', '13:02:02 PM', '2019-02-10'),
('00000004', '9962740350', 'Mortadela grande ', 1.00, 8, 0.00, 8, 'Crédito', ' ', 0, 'Fernando Margarito Gonzalez Rodriguez', '13:02:02 PM', '2019-02-10'),
('00000005', '7610788307', 'Salsa de tomate Los Andes', 1.00, 2, 0.00, 2, 'Contado', ' ', 0, 'nawar', '12:21:02 PM', '2019-04-01'),
('00000006', '379042964570', 'Galletas Ricas 57gr', 3.00, 1, 0.00, 1, 'Contado', ' ', 0, 'nawar', '09:22:58 AM', '2019-04-05'),
('00000007', '6468435289551', 'Salsa De Tomate Facundo 375 g ', 3.00, 1, 0.00, 5, 'Contado', ' ', 0, 'nawar', '09:31:18 AM', '2019-04-06'),
('00000008', '5121154810', 'Agua vivant botella', 1.00, 1, 0.00, 1, 'Contado', ' ', 0, 'nawar', '17:01:15 PM', '2019-04-08'),
('00000008', '5121154810', 'Agua vivant botella', 1.00, 1, 0.00, 1, 'Contado', ' ', 0, 'nawar', '17:01:15 PM', '2019-04-08'),
('00000008', '5121154810', 'Agua vivant botella', 1.00, 1, 0.00, 1, 'Contado', ' ', 0, 'nawar', '17:01:15 PM', '2019-04-08'),
('00000008', '5121154810', 'Agua vivant botella', 1.00, 18000, 0.00, 18000, 'Contado', ' ', 0, 'nawar', '17:01:15 PM', '2019-04-08'),
('00000008', '5121154810', 'Agua vivant botella', 1.00, 1, 0.00, 1, 'Contado', ' ', 0, 'nawar', '17:01:25 PM', '2019-04-08'),
('00000008', '5121154810', 'Agua vivant botella', 1.00, 1, 0.00, 1, 'Contado', ' ', 0, 'nawar', '17:01:25 PM', '2019-04-08'),
('00000008', '5121154810', 'Agua vivant botella', 1.00, 1, 0.00, 1, 'Contado', ' ', 0, 'nawar', '17:01:25 PM', '2019-04-08'),
('00000008', '5121154810', 'Agua vivant botella', 1.00, 18000, 0.00, 18000, 'Contado', ' ', 0, 'nawar', '17:01:25 PM', '2019-04-08'),
('00000008', '5121154810', 'Agua vivant botella', 1.00, 1, 0.00, 1, 'Contado', ' ', 0, 'nawar', '17:01:34 PM', '2019-04-08'),
('00000008', '5121154810', 'Agua vivant botella', 1.00, 1, 0.00, 1, 'Contado', ' ', 0, 'nawar', '17:01:34 PM', '2019-04-08'),
('00000008', '5121154810', 'Agua vivant botella', 1.00, 1, 0.00, 1, 'Contado', ' ', 0, 'nawar', '17:01:34 PM', '2019-04-08'),
('00000008', '5121154810', 'Agua vivant botella', 1.00, 18000, 0.00, 18000, 'Contado', ' ', 0, 'nawar', '17:01:34 PM', '2019-04-08'),
('00000009', '1320221450', 'Detergente OMO', 2.00, 13000, 0.04, 24960, 'Contado', ' ', 0, 'Fernando Margarito Gonzalez Rodriguez', '22:30:11 PM', '2019-04-09'),
('00000010', '1320221450', 'Detergente OMO', 1.00, 13000, 0.00, 13000, 'Contado', ' ', 0, 'admin', '00:07:38 AM', '2019-04-10');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tabla_aperturacaja`
--

DROP TABLE IF EXISTS `tabla_aperturacaja`;
CREATE TABLE IF NOT EXISTS `tabla_aperturacaja` (
  `codcaja` int(6) NOT NULL AUTO_INCREMENT,
  `caja` varchar(50) COLLATE utf8_swedish_ci NOT NULL,
  `apertura` double(7,2) NOT NULL,
  `empleado` varchar(150) COLLATE utf8_swedish_ci DEFAULT NULL,
  `hora` varchar(15) COLLATE utf8_swedish_ci NOT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`codcaja`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Volcado de datos para la tabla `tabla_aperturacaja`
--

INSERT INTO `tabla_aperturacaja` (`codcaja`, `caja`, `apertura`, `empleado`, `hora`, `fecha`) VALUES
(1, 'CAJA PRINCIPAL', 250.00, 'Netfex Programmer - Systems', '10:42:42 PM', '2016-11-19'),
(2, 'CAJA PRINCIPAL', 162.00, 'Netfex Programmer - Systems', '11:37:03 PM', '2016-11-22'),
(3, 'CAJA PRINCIPAL', 200.00, 'Netfex Programmer - Systems', '06:38:43 PM', '2016-11-30'),
(4, 'CAJA PRINCIPAL', 200.00, 'Netfex Programmer - Systems', '10:06:59 AM', '2016-12-02'),
(5, 'CAJA PRINCIPAL', 150.00, 'Fernando Margarito Gonzalez Rodriguez', '12:34:04 AM', '2016-12-15'),
(6, 'CAJA PRINCIPAL', 100.00, 'Fernando Margarito Gonzalez Rodriguez', '09:10:10 AM', '2017-02-10'),
(7, 'CAJA PRINCIPAL', 200.00, 'Fernando Margarito Gonzalez Rodriguez', '08:33:50 PM', '2017-03-10'),
(8, 'CAJA PRINCIPAL', 100.00, 'Fernando Margarito Gonzalez Rodriguez', '09:39:09 PM', '2019-01-05'),
(9, 'CAJA PRINCIPAL', 150.00, 'Fernando Margarito Gonzalez Rodriguez', '21:01:733 PM', '2019-01-06'),
(10, 'CAJA 1', 120.00, 'Fernando Margarito Gonzalez Rodriguez', '10:01:510 AM', '2019-01-07'),
(11, 'CAJA 1', 123.00, 'Fernando Margarito Gonzalez Rodriguez', '22:01:779 PM', '2019-01-09'),
(12, 'CAJA 1', 150.00, 'Fernando Margarito Gonzalez Rodriguez', '06:53:30 AM', '2019-01-24'),
(13, 'CAJA 1', 200.00, 'EMP0002', '21:59:10 PM', '2019-02-05'),
(14, 'CAJA 1', 250.00, 'EMP0002', '23:06:05 PM', '2019-02-07'),
(15, 'CAJA 1', 100.00, 'EMP0002', '21:24:07 PM', '2019-02-09'),
(16, 'CAJA 1', 200.00, 'Fernando Margarito Gonzalez Rodriguez', '12:24:54 PM', '2019-02-10'),
(17, 'CAJA 1', 100.00, 'nawar', '14:31:02 PM', '2019-03-29'),
(18, 'CAJA 1', 45000.00, 'nawar', '22:41:04 PM', '2019-03-29'),
(19, 'CAJA 2', 20000.00, 'nawar', '09:21:01 AM', '2019-04-05'),
(20, 'CAJA 2', 30000.00, 'nawar', '18:29:54 PM', '2019-04-05'),
(21, 'CAJA 2', 40000.00, 'nawar', '09:31:45 AM', '2019-04-06'),
(22, 'CAJA 2', 10000.00, 'nawar', '16:58:53 PM', '2019-04-08'),
(23, '---Seleccionar---', 1000.00, '---Usuario---', '23:29:35 PM', '2019-04-09'),
(24, '---Seleccionar---', 500.00, '---Usuario---', '23:15:11 PM', '2019-04-10'),
(25, '---Seleccionar---', 2000.00, '---Usuario---', '19:19:36 PM', '2019-04-12'),
(26, '---Seleccionar---', 200.00, '---Usuario---', '10:21:51 AM', '2019-04-15'),
(27, '---Seleccionar---', 200.00, '---Usuario---', '10:25:33 AM', '2019-04-15'),
(28, '---Seleccionar---', 10.00, '---Usuario---', '10:48:03 AM', '2019-04-15');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tabla_categorias`
--

DROP TABLE IF EXISTS `tabla_categorias`;
CREATE TABLE IF NOT EXISTS `tabla_categorias` (
  `codcategoria` int(8) NOT NULL AUTO_INCREMENT,
  `categoria` varchar(80) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`codcategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Volcado de datos para la tabla `tabla_categorias`
--

INSERT INTO `tabla_categorias` (`codcategoria`, `categoria`) VALUES
(1, 'Bebidas, zumos y gaseosas'),
(2, 'Snack y consumos'),
(3, 'Utileria y cosméticos'),
(4, 'Embutidos y verduras'),
(5, 'Medicinas'),
(6, 'Computadores'),
(7, 'Conservas y Comida Preparada'),
(8, 'Panadería y Dulces'),
(9, 'Yoghurts'),
(10, 'Frutas y Verduras'),
(11, 'Carnes y Embutidos'),
(12, 'Cosmética y Cuidado Personal'),
(13, 'Hogar y Limpieza'),
(14, 'Bolos'),
(15, 'Hortalizas'),
(16, 'Pantalonetas'),
(17, 'Leches y quesos'),
(18, 'Jugos y licores');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tabla_cierre`
--

DROP TABLE IF EXISTS `tabla_cierre`;
CREATE TABLE IF NOT EXISTS `tabla_cierre` (
  `codcierre` int(6) NOT NULL AUTO_INCREMENT,
  `caja` varchar(50) COLLATE utf8_swedish_ci DEFAULT NULL,
  `aperturado` double(7,2) DEFAULT NULL,
  `tot_compras` double(7,2) DEFAULT NULL,
  `tot_ventas` double(7,2) DEFAULT NULL,
  `tot_ingresos` double(7,2) DEFAULT NULL,
  `tot_gastos` double(7,2) DEFAULT NULL,
  `tot_ctacobrar` double(7,2) DEFAULT NULL,
  `tot_ctapagar` double(7,2) DEFAULT NULL,
  `tot_devoluciones` double(7,2) DEFAULT NULL,
  `tot_caja` double(7,2) DEFAULT NULL,
  `empleado` varchar(150) COLLATE utf8_swedish_ci DEFAULT NULL,
  `hora` varchar(15) COLLATE utf8_swedish_ci DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`codcierre`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Volcado de datos para la tabla `tabla_cierre`
--

INSERT INTO `tabla_cierre` (`codcierre`, `caja`, `aperturado`, `tot_compras`, `tot_ventas`, `tot_ingresos`, `tot_gastos`, `tot_ctacobrar`, `tot_ctapagar`, `tot_devoluciones`, `tot_caja`, `empleado`, `hora`, `fecha`) VALUES
(5, 'CAJA PRINCIPAL', 150.00, 17.96, 60.34, 0.00, 0.00, 0.00, 0.00, 6.65, 185.73, 'Fernando Margarito Gonzalez Rodriguez', '21:01:625 PM', '2019-01-06'),
(6, 'CAJA 1', 200.00, 49.70, 23.76, 450.00, 27.30, 0.00, 0.00, 0.00, 596.76, 'Fernando Margarito Gonzalez Rodriguez', '13:11:13 PM', '2019-02-10'),
(7, 'CAJA 2', 20000.00, 2800.56, 0.75, 0.00, 0.00, 0.00, 0.00, 0.00, 17200.19, 'nawar', '09:27:34 AM', '2019-04-05');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tabla_clientes`
--

DROP TABLE IF EXISTS `tabla_clientes`;
CREATE TABLE IF NOT EXISTS `tabla_clientes` (
  `codcliente` varchar(8) COLLATE utf8_swedish_ci NOT NULL,
  `identidad` int(10) DEFAULT NULL,
  `nombres` varchar(100) COLLATE utf8_swedish_ci DEFAULT NULL,
  `direccion` varchar(100) COLLATE utf8_swedish_ci DEFAULT NULL,
  `telefono` int(10) DEFAULT NULL,
  `celular` int(10) DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`codcliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Volcado de datos para la tabla `tabla_clientes`
--

INSERT INTO `tabla_clientes` (`codcliente`, `identidad`, `nombres`, `direccion`, `telefono`, `celular`, `email`) VALUES
('CL0001', 706630200, 'Juan Hernandez Peña', 'Cdla. Las Mercedes', 2909664, 995103508, 'juan13006@hotmail.com'),
('CL0002', 113082064, 'Mariana Lorena Peña Páez', 'Cdla. Loma Quito', 2909506, 99854106, ''),
('CL0003', 780344067, 'Mario Gonzalez Pineda', 'Av. Juan Tarquí y Sucre', 2098510, 998836057, ''),
('CL0004', 700746052, 'Alberto Jumbo Calderón', 'Lotización Colinas de Santa Fé', 2908406, 964035206, ''),
('CL0005', 706270158, 'Mario Efrain Rodríguez', 'Cdla. Las Mercedes', 2909680, 988834506, 'netfexprogrammer@gmail.com'),
('CL0006', 155475601, 'Juana Páez Romero', 'CDLA LAS MERCEDES', 2065214, 66548451, 'juana_lor@gmail.com'),
('CL0007', 706005426, 'Gerar Madrid Paredes', 'Cdl. Las Brisas II', 2909181, 996340608, 'gerar.par@hotmail.com'),
('CL0008', 795210350, 'Jose Romero Calderón', 'Cdla. Las Mercedes Norte', 2033642, 963203521, 'joserom_147@yahoo.com'),
('CL0009', 706242111, 'Carlos Miguel Guamán Mejia', 'Cdla. Las Brisas', 209962, 965230014, 'carl_gunor@hotmail.com'),
('CL0010', 709663152, 'Guillermo Peña Lenz', 'Cdla. 9 de Octubre', 29098664, 0, 'elguille_13@hotmail.es');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tabla_compras`
--

DROP TABLE IF EXISTS `tabla_compras`;
CREATE TABLE IF NOT EXISTS `tabla_compras` (
  `num_comp` varchar(8) NOT NULL,
  `proveedor` varchar(100) NOT NULL,
  `documento` varchar(50) DEFAULT NULL,
  `subtotal` double(7,2) DEFAULT NULL,
  `iva` double(7,2) DEFAULT NULL,
  `pre_tot` double(7,2) NOT NULL,
  `hora` varchar(15) DEFAULT NULL,
  `fecha` date NOT NULL,
  KEY `num_comp` (`num_comp`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tabla_compras`
--

INSERT INTO `tabla_compras` (`num_comp`, `proveedor`, `documento`, `subtotal`, `iva`, `pre_tot`, `hora`, `fecha`) VALUES
('00000006', 'Juan Perez Gonzales', 'Factura', 56.74, 7.94, 64.68, '09:02:34 AM', '2017-02-10'),
('00000012', 'Juan Perez Gonzales', 'Orden de compra', 54.00, 0.05, 54.05, '20:59:16 PM', '2019-02-09'),
('00000013', 'Juan Perez Gonzales', 'Orden de compra', 49.65, 0.05, 49.70, '12:43:46 PM', '2019-02-10'),
('00000014', 'Romano Gonzales Duarte', 'Orden de compra', 2500.50, 300.06, 2800.56, '09:26:41 AM', '2019-04-05');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tabla_conceptos`
--

DROP TABLE IF EXISTS `tabla_conceptos`;
CREATE TABLE IF NOT EXISTS `tabla_conceptos` (
  `codconceptos` int(8) NOT NULL AUTO_INCREMENT,
  `conceptos` varchar(100) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`codconceptos`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Volcado de datos para la tabla `tabla_conceptos`
--

INSERT INTO `tabla_conceptos` (`codconceptos`, `conceptos`) VALUES
(1, 'Depósito de terceros'),
(2, 'Pago de planillas'),
(3, 'Pago de matriculas'),
(4, 'Pago y consumo de bebidas'),
(5, 'Ingreso de dinero extra'),
(6, 'Pago de cuentas adicionales'),
(7, 'Cobro de pensiones'),
(8, 'Pagos varios'),
(9, 'Otros');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tabla_devoluciones`
--

DROP TABLE IF EXISTS `tabla_devoluciones`;
CREATE TABLE IF NOT EXISTS `tabla_devoluciones` (
  `cod_detallefact` varchar(13) COLLATE utf8_swedish_ci DEFAULT NULL,
  `cod_prod` varchar(13) COLLATE utf8_swedish_ci DEFAULT NULL,
  `producto` varchar(100) COLLATE utf8_swedish_ci DEFAULT NULL,
  `motivo` varchar(300) COLLATE utf8_swedish_ci DEFAULT NULL,
  `tipdevolucion` varchar(30) COLLATE utf8_swedish_ci DEFAULT NULL,
  `cantidad` double(5,2) DEFAULT NULL,
  `precio` double(7,2) DEFAULT NULL,
  `total` double(7,2) DEFAULT NULL,
  `usuario` varchar(50) COLLATE utf8_swedish_ci DEFAULT NULL,
  `fecha` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Volcado de datos para la tabla `tabla_devoluciones`
--

INSERT INTO `tabla_devoluciones` (`cod_detallefact`, `cod_prod`, `producto`, `motivo`, `tipdevolucion`, `cantidad`, `precio`, `total`, `usuario`, `fecha`) VALUES
('00000001', '2147483647', 'Cafe Clásico', 'Ingrese un motivo...\n', 'Devolución', 1.00, 3.00, 3.00, 'Fernando Margarito Gonzalez Rodriguez', '2019-02-09');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tabla_empresas`
--

DROP TABLE IF EXISTS `tabla_empresas`;
CREATE TABLE IF NOT EXISTS `tabla_empresas` (
  `codigo` int(8) NOT NULL AUTO_INCREMENT,
  `empresa` varchar(100) COLLATE utf8_swedish_ci DEFAULT NULL,
  `propietarios` varchar(150) COLLATE utf8_swedish_ci DEFAULT NULL,
  `ruc` varchar(15) COLLATE utf8_swedish_ci DEFAULT NULL,
  `zonal` varchar(100) COLLATE utf8_swedish_ci DEFAULT NULL,
  `direccion` varchar(150) COLLATE utf8_swedish_ci DEFAULT NULL,
  `referencia` varchar(150) COLLATE utf8_swedish_ci DEFAULT NULL,
  `telf1` varchar(10) COLLATE utf8_swedish_ci DEFAULT NULL,
  `telf2` varchar(10) COLLATE utf8_swedish_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_swedish_ci DEFAULT NULL,
  `web` varchar(50) COLLATE utf8_swedish_ci DEFAULT NULL,
  `fecha` varchar(15) COLLATE utf8_swedish_ci DEFAULT NULL,
  `foto` varchar(100) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Volcado de datos para la tabla `tabla_empresas`
--

INSERT INTO `tabla_empresas` (`codigo`, `empresa`, `propietarios`, `ruc`, `zonal`, `direccion`, `referencia`, `telf1`, `telf2`, `email`, `web`, `fecha`, `foto`) VALUES
(3, 'EMPRESA DE PRUEBA', 'NOMBRES Y APELLIDOS', '2222222222222', 'DISTRITO', 'AVEN. CALLES', 'DONDE VIVE', '2222222222', '2222222222', 'corre@gmail.com', 'corre@gmail.com', '26/01/2019', 'C://Users//ADMIN//Downloads//logo-largo-17.png'),
(4, 'mafakafer', 'mafakafer', '1234567', '', 'calle 21 esquina', '', '1234567', '12345678', 'ertyu@.com', 'sjdbdk.com', '', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tabla_gastos`
--

DROP TABLE IF EXISTS `tabla_gastos`;
CREATE TABLE IF NOT EXISTS `tabla_gastos` (
  `codgastos` varchar(8) COLLATE utf8_swedish_ci NOT NULL,
  `descripcion` varchar(100) COLLATE utf8_swedish_ci DEFAULT NULL,
  `mes` varchar(50) COLLATE utf8_swedish_ci DEFAULT NULL,
  `periodo` int(5) DEFAULT NULL,
  `total` double(7,2) DEFAULT NULL,
  `fecha` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Volcado de datos para la tabla `tabla_gastos`
--

INSERT INTO `tabla_gastos` (`codgastos`, `descripcion`, `mes`, `periodo`, `total`, `fecha`) VALUES
('00000001', 'Pago de luz', 'Noviembre', 2016, 20.00, '2016-11-30'),
('00000002', 'PAGO A EMPLEADOS', 'Febrero', 2019, 80.00, '2019-02-05'),
('00000003', 'PAGO DE ALMUERZO', 'Febrero', 2019, 2.50, '2019-02-10'),
('00000004', 'PAGO DE RESMAS DE PAPEL BOND', 'Febrero', 2019, 24.80, '2019-02-10');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tabla_ingresos`
--

DROP TABLE IF EXISTS `tabla_ingresos`;
CREATE TABLE IF NOT EXISTS `tabla_ingresos` (
  `codingresos` int(8) NOT NULL AUTO_INCREMENT,
  `conceptos` varchar(150) COLLATE utf8_swedish_ci DEFAULT NULL,
  `mpagos` varchar(40) COLLATE utf8_swedish_ci DEFAULT NULL,
  `referencia` varchar(20) COLLATE utf8_swedish_ci DEFAULT NULL,
  `monto` double(7,2) DEFAULT NULL,
  `documento` varchar(20) COLLATE utf8_swedish_ci DEFAULT NULL,
  `numdocumento` varchar(13) COLLATE utf8_swedish_ci DEFAULT NULL,
  `descripcion` varchar(100) COLLATE utf8_swedish_ci DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`codingresos`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Volcado de datos para la tabla `tabla_ingresos`
--

INSERT INTO `tabla_ingresos` (`codingresos`, `conceptos`, `mpagos`, `referencia`, `monto`, `documento`, `numdocumento`, `descripcion`, `fecha`) VALUES
(1, 'Depósito de terceros', 'Efectivo', '0230148101', 250.00, 'Factura', '000-03201', 'Pagos', '2016-06-28'),
(2, 'Pago de cuentas adicionales', 'Efectivo', '0215442102', 140.60, 'Factura', '000-03201', 'Pagos', '2016-06-28'),
(3, 'Depósitos de terceros', 'Efectivo', '0215442102', 160.00, 'Factura', '000-03201', 'Pagos', '2016-06-28'),
(4, 'Pago de cuentas adicionales', 'Efectivo', '0215442102', 300.00, 'Factura', '000-03201', 'Pagos', '2016-06-28'),
(5, 'Pago de cuentas adicionales', 'Efectivo', '0215442102', 240.00, 'Factura', '000-03201', 'Pagos', '2016-06-29'),
(6, 'Ingreso de dinero extra', 'Efectivo', '0215442102', 300.00, 'Factura', '000-03201', 'Pagos', '2016-07-07'),
(7, 'Pago de matriculas', 'Efectivo', '0215442102', 200.00, 'Factura', '000-03201', 'Pagos', '2016-07-19'),
(8, 'Pago y consumo de bebidas', 'Efectivo', '0215442102', 150.00, 'Factura', '000-03201', 'Pagos', '2016-07-19'),
(9, 'Pago de cuentas adicionales', 'Efectivo', '0215442102', 1200.00, 'Factura', '000-03201', 'Pagos', '2016-07-19'),
(10, 'Ingreso de dinero extra', 'Efectivo', '0215442102', 2750.00, 'Factura', '000-03201', 'Pagos', '2016-08-09'),
(11, 'Pago de cuentas adicionales', 'Efectivo', '0215442102', 170.00, 'Factura', '000-03201', 'Pagos', '2016-08-23'),
(12, 'Depósito de terceros', 'Efectivo', '0215442102', 250.00, 'Factura', '000-03201', 'Pagos', '2016-08-23'),
(13, 'Ingreso de dinero extra', 'Efectivo', '0215442102', 150.00, 'Factura', '000-03201', 'Pagos', '2016-09-03'),
(14, 'Pago y consumo de bebidas', 'Efectivo', '0215442102', 56.00, 'Factura', '000-03201', 'Pagos', '2016-10-14'),
(15, 'Pago de cuentas adicionales', 'Efectivo', '0215442102', 30.00, 'Factura', '000-03201', 'Pagos', '2016-10-14'),
(16, 'Depósito de terceros', 'Efectivo', '1234567890', 200.00, 'Factura', '0001-00000001', 'Pagos', '2016-11-02'),
(17, 'Pago de planillas', 'Crédito', '0215442102', 120.00, 'Nota de débito', '000-03201', 'Pagos', '2016-11-02'),
(18, 'Pago de cuentas adicionales', 'Efectivo', '71002451883', 120.00, 'Factura', '00001-0105540', 'Pagos', '2016-11-03'),
(19, 'Cobro de pensiones', 'Efectivo', '0001', 150.00, 'Otros', '0001-000001', 'Pagos', '2016-11-05'),
(20, 'Pago de cuentas adicionales', 'Efectivo', '0002-003100', 35.00, 'Factura', '0001-021003', '', '2016-11-07'),
(21, 'Depósito de terceros', 'Efectivo', '032215', 60.00, 'Factura', '00021-100354', '', '2016-11-11'),
(22, 'Pago de cuentas adicionales', 'Efectivo', '132012', 20.00, 'Factura', '012-321540', '', '2016-11-11'),
(23, 'Pago de cuentas adicionales', 'Efectivo', '86531', 15.00, 'Factura', '002-000031', '', '2016-11-11'),
(24, 'Pago de cuentas adicionales', 'Efectivo', '8', 20.00, 'Orden de compra', '0212-33001', '', '2016-11-11'),
(25, 'Otros', 'Efectivo', 'SIN DESIGNAR', 30.00, 'Otros', '0232-3321', 'SIN DESIGNAR', '2016-11-13'),
(26, 'Otros', 'Efectivo', '000000124', 30.00, 'Otros', '000002', '', '2016-11-19'),
(27, 'Otros', 'Efectivo', 'PAGO DE CUENTAS', 50.00, 'Factura', '01-00001', '', '2016-11-28'),
(28, 'Pagos varios', 'Efectivo', '4210ASQ12', 120.00, 'Factura', '0000000010', 'Pago de intereses', '2016-11-30'),
(29, 'Depósito de terceros', 'Cheque', '08554002141', 450.00, 'Otros', '00210141', 'PAGO DE DEDUDAS', '2019-02-10');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tabla_kardex`
--

DROP TABLE IF EXISTS `tabla_kardex`;
CREATE TABLE IF NOT EXISTS `tabla_kardex` (
  `num_fact` varchar(8) COLLATE utf8_swedish_ci NOT NULL,
  `cod_prod` varchar(15) COLLATE utf8_swedish_ci NOT NULL,
  `producto` varchar(100) COLLATE utf8_swedish_ci NOT NULL,
  `motivo` varchar(50) COLLATE utf8_swedish_ci NOT NULL,
  `documento` varchar(80) COLLATE utf8_swedish_ci NOT NULL,
  `proveedor` varchar(100) COLLATE utf8_swedish_ci NOT NULL,
  `pre_unit` double(7,2) NOT NULL,
  `entrada` float(7,2) NOT NULL,
  `salida` float(7,2) NOT NULL,
  `total` double(7,2) NOT NULL,
  `fecha` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Volcado de datos para la tabla `tabla_kardex`
--

INSERT INTO `tabla_kardex` (`num_fact`, `cod_prod`, `producto`, `motivo`, `documento`, `proveedor`, `pre_unit`, `entrada`, `salida`, `total`, `fecha`) VALUES
('00000002', '2147483647', 'Nescafe Clàsico mediano', 'Ventas', 'Ticket', 'Fernando Margarito Gonzalez Rodriguez', 1.10, 0.00, 1.00, 1.10, '2019-02-09'),
('00000002', '2147644217', 'Aceite La Favorita', 'Ventas', 'Ticket', 'Fernando Margarito Gonzalez Rodriguez', 1.20, 0.00, 1.00, 1.20, '2019-02-09'),
('00000003', '5121154810', 'Agua vivant botella', 'Ventas', 'Ticket', 'Fernando Margarito Gonzalez Rodriguez', 0.53, 0.00, 4.00, 2.12, '2019-02-10'),
('00000013', '2147850000', 'Clorox', 'Orden de compra', 'Orden de compra', 'Juan Perez Gonzales', 0.90, 5.00, 0.00, 49.70, '2019-02-10'),
('00000013', '9962740350', 'Mortadela grande ', 'Orden de compra', 'Orden de compra', 'Juan Perez Gonzales', 8.11, 5.00, 0.00, 49.70, '2019-02-10'),
('00000013', '7552033640257', 'Yoghurt Fat Free', 'Orden de compra', 'Orden de compra', 'Juan Perez Gonzales', 0.85, 5.00, 0.00, 49.70, '2019-02-10'),
('00000013', '1234567890', 'Fósforos', 'Orden de compra', 'Orden de compra', 'Juan Perez Gonzales', 0.07, 5.00, 0.00, 49.70, '2019-02-10'),
('00000004', '2147644217', 'Aceite La Favorita', 'Ventas', 'Ticket', 'Fernando Margarito Gonzalez Rodriguez', 1.20, 0.00, 1.00, 1.20, '2019-02-10'),
('00000004', '2147783647', 'Achiote', 'Ventas', 'Ticket', 'Fernando Margarito Gonzalez Rodriguez', 1.00, 0.00, 1.00, 1.00, '2019-02-10'),
('00000004', '2147718364', 'Atun Isabel Pack 3', 'Ventas', 'Ticket', 'Fernando Margarito Gonzalez Rodriguez', 2.25, 0.00, 1.00, 2.25, '2019-02-10'),
('00000004', '2276553490227', 'Papas Fritas Naturales', 'Ventas', 'Ticket', 'Fernando Margarito Gonzalez Rodriguez', 1.10, 0.00, 1.00, 1.10, '2019-02-10'),
('00000004', '2148183647', 'PinoKlin + Suavitel (Combo)', 'Ventas', 'Ticket', 'Fernando Margarito Gonzalez Rodriguez', 3.75, 0.00, 1.00, 3.45, '2019-02-10'),
('00000004', '2144893647', 'Mayonesa Maggi 200 g', 'Ventas', 'Ticket', 'Fernando Margarito Gonzalez Rodriguez', 1.90, 0.00, 1.00, 1.90, '2019-02-10'),
('00000004', '9962740350', 'Mortadela grande ', 'Ventas', 'Ticket', 'Fernando Margarito Gonzalez Rodriguez', 8.20, 0.00, 1.00, 8.20, '2019-02-10'),
('00000005', '7610788307', 'Salsa de tomate Los Andes', 'Ventas', 'Ticket', 'nawar', 1.60, 0.00, 1.00, 1.60, '2019-04-01'),
('00000006', '379042964570', 'Galletas Ricas 57gr', 'Ventas', 'Ticket', 'nawar', 0.67, 0.00, 3.00, 0.67, '2019-04-05'),
('00000014', '144239231726', 'Doritos Queso', 'Orden de compra', 'Orden de compra', 'Romano Gonzales Duarte', 0.50, 1.00, 0.00, 0.50, '2019-04-05'),
('00000014', '2941173975006', 'agua minerales', 'Orden de compra', 'Orden de compra', 'Romano Gonzales Duarte', 2500.00, 1.00, 0.00, 2500.00, '2019-04-05'),
('00000007', '6468435289551', 'Salsa De Tomate Facundo 375 g ', 'Ventas', 'Ticket', 'nawar', 0.88, 0.00, 3.00, 5.28, '2019-04-06'),
('00000009', '1320221450', 'Detergente OMO', 'Ventas', 'Ticket', 'Fernando Margarito Gonzalez Rodriguez', 13000.00, 0.00, 2.00, 24960.00, '2019-04-09'),
('00000010', '1320221450', 'Detergente OMO', 'Ventas', 'Ticket', 'admin', 13000.00, 0.00, 1.00, 13000.00, '2019-04-10');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tabla_marcas`
--

DROP TABLE IF EXISTS `tabla_marcas`;
CREATE TABLE IF NOT EXISTS `tabla_marcas` (
  `codmarca` int(8) NOT NULL AUTO_INCREMENT,
  `marca` varchar(80) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`codmarca`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Volcado de datos para la tabla `tabla_marcas`
--

INSERT INTO `tabla_marcas` (`codmarca`, `marca`) VALUES
(1, 'Coca Cola'),
(2, 'Pepsi'),
(3, 'Sprite'),
(4, 'Firavanti'),
(5, 'Inka Cola'),
(6, 'Fanta'),
(7, 'K´chitos'),
(8, 'Nutrileche'),
(9, 'Intel'),
(10, 'Dual Core'),
(11, 'Genius'),
(12, 'Geforce'),
(13, 'Ace'),
(14, 'Lenutri'),
(15, 'Nestle'),
(16, 'Colun'),
(17, 'Toni'),
(18, 'Ruffles'),
(19, 'Grupo Pharma S.A'),
(20, 'Rexona'),
(21, 'Axe'),
(22, 'Nohemi'),
(23, 'Agogó'),
(24, 'Caribas'),
(25, 'Frito Lay'),
(26, 'Real'),
(27, 'Facundo'),
(28, 'Vital'),
(29, 'Cifrut'),
(30, 'Don Victorio'),
(31, 'Gustadina'),
(32, 'Doña Petrona'),
(33, 'Amancay'),
(34, 'Nescafé'),
(35, 'Marinela'),
(36, 'La Universal'),
(37, 'Colgate');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tabla_productos`
--

DROP TABLE IF EXISTS `tabla_productos`;
CREATE TABLE IF NOT EXISTS `tabla_productos` (
  `codprod` int(8) NOT NULL AUTO_INCREMENT,
  `codbarras` varchar(18) COLLATE utf8_swedish_ci DEFAULT NULL,
  `producto` varchar(100) COLLATE utf8_swedish_ci DEFAULT NULL,
  `marca` varchar(50) COLLATE utf8_swedish_ci DEFAULT NULL,
  `unidad` varchar(40) COLLATE utf8_swedish_ci DEFAULT NULL,
  `categoria` varchar(50) COLLATE utf8_swedish_ci DEFAULT NULL,
  `stock` double(5,2) DEFAULT NULL,
  `iva` double(5,2) DEFAULT NULL,
  `descuento` varchar(5) COLLATE utf8_swedish_ci DEFAULT NULL,
  `proveedor` varchar(50) COLLATE utf8_swedish_ci DEFAULT NULL,
  `pre_compra` double(7,0) DEFAULT NULL,
  `pre_venta` double(7,0) DEFAULT NULL,
  `fechacaducidad` date DEFAULT NULL,
  `detalles` varchar(200) COLLATE utf8_swedish_ci DEFAULT NULL,
  `imagen` varchar(100) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`codprod`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Volcado de datos para la tabla `tabla_productos`
--

INSERT INTO `tabla_productos` (`codprod`, `codbarras`, `producto`, `marca`, `unidad`, `categoria`, `stock`, `iva`, `descuento`, `proveedor`, `pre_compra`, `pre_venta`, `fechacaducidad`, `detalles`, `imagen`) VALUES
(1, '5121154810', 'Agua vivant botella', 'Pepsi', 'UN', 'Bebidas, zumos y gaseosas', 99.00, 0.18, '0.0', 'Leonardo Guaman Cortéz', 5000, 7000, '2016-10-18', '', 'D://FotosVentas//AGUA-VIVANT.jpg'),
(2, '2147483647', 'Nescafe Clásico mediano', 'Inka Cola', 'UN', 'Snack y consumos', 29.00, 0.00, '0.0', 'Ediberto Gonzalez Vaca', 3000, 5000, '2016-06-16', '', 'D://FotosVentas//CAFECLASICO.jpg'),
(3, '1320221450', 'Detergente OMO', 'Inka Cola', 'UN', 'Hogar y Limpieza', 85.00, 0.12, '0.04', 'Juan Perez Gonzales', 10000, 13000, '2016-05-21', 'Taldro Percutor Skil 6398AD F.015.639.8AD', 'D://FotosVentas//DETERGENTE-OMO.png');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tabla_proveedores`
--

DROP TABLE IF EXISTS `tabla_proveedores`;
CREATE TABLE IF NOT EXISTS `tabla_proveedores` (
  `codprov` varchar(6) COLLATE utf8_swedish_ci NOT NULL,
  `tipo` varchar(20) COLLATE utf8_swedish_ci DEFAULT NULL,
  `documento` varchar(20) COLLATE utf8_swedish_ci DEFAULT NULL,
  `nom_comercial` varchar(100) COLLATE utf8_swedish_ci DEFAULT NULL,
  `nombres` varchar(100) COLLATE utf8_swedish_ci DEFAULT NULL,
  `telefono` varchar(10) COLLATE utf8_swedish_ci DEFAULT NULL,
  `direccion` varchar(100) COLLATE utf8_swedish_ci DEFAULT NULL,
  `ciudad` varchar(100) COLLATE utf8_swedish_ci DEFAULT NULL,
  `email` varchar(100) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`codprov`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Volcado de datos para la tabla `tabla_proveedores`
--

INSERT INTO `tabla_proveedores` (`codprov`, `tipo`, `documento`, `nom_comercial`, `nombres`, `telefono`, `direccion`, `ciudad`, `email`) VALUES
('00001', 'Pasaporte', '0703520647', 'Juan Pi', 'Juan Perez Gonzales', '2909898', 'Cdla. Las Americas', 'Machala', 'juanpe@gmail.com'),
('00002', 'RUC', '1100056240215', 'The Company S.A', 'Leonardo Guaman Cortéz', '2960-301', 'Cdla. La Libertad', 'Machala', 'thecompany.sa@hotmail.com'),
('00003', 'RUC', '1150300157414', 'Hernes Rocafuerte', 'Ediberto Gonzalez Vaca', '2946-706', 'Av. Simon Roque Calla Simón Cayambe', 'Ribamba', 'her.roca16@yahoo.es'),
('00004', 'RUC', '0001301404864', 'S.A Steel', 'Juan Anangono Paredes', '2964-529', 'Cdla. 24 de Mayo y la A', 'Pasaje', 'steelfierro@hotmail.es'),
('00005', 'RUC', '1170054302582', 'FERRETERIA COMMER S.A', 'Gabriel Rodolfo Fernandez Ramirez', '2909407', 'Av. Tarqui y Guayas', 'Machala', 'ferreteria.commer@yahoo.es'),
('00006', 'RUC', '01202101', 'PP S.A FERRER', 'Romano Gonzales Duarte', '2908441', 'Cdla. Las Mercelinas', 'Machala', 'ppsa.mach@gmail.com'),
('00007', 'RUC', '0700603509', 'Servidor CTK', 'Ronald Perez Castillo', '29094255', 'Cdla. Las Mercedes Norte', 'Machala', 'servidor.ctk@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tabla_sesiones`
--

DROP TABLE IF EXISTS `tabla_sesiones`;
CREATE TABLE IF NOT EXISTS `tabla_sesiones` (
  `cod_sesion` int(8) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(60) COLLATE utf8_swedish_ci DEFAULT NULL,
  `tipo` varchar(30) COLLATE utf8_swedish_ci DEFAULT NULL,
  `hora` varchar(20) COLLATE utf8_swedish_ci DEFAULT NULL,
  `fecha` varchar(15) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`cod_sesion`)
) ENGINE=InnoDB AUTO_INCREMENT=190 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Volcado de datos para la tabla `tabla_sesiones`
--

INSERT INTO `tabla_sesiones` (`cod_sesion`, `usuario`, `tipo`, `hora`, `fecha`) VALUES
(1, 'Netfex Programmer - Systems', 'Administrador', '10:03:50 PM', '2016/11/17'),
(2, 'Netfex Programmer - Systems', 'Administrador', '06:35:55 PM', '2016/11/18'),
(3, 'Netfex Programmer - Systems', 'Administrador', '12:58:01 PM', '2016/11/19'),
(4, 'Netfex Programmer - Systems', 'Administrador', '09:52:18 PM', '2016/11/19'),
(5, 'Netfex Programmer - Systems', 'Administrador', '10:56:36 PM', '2016/11/19'),
(6, 'NETFEX PROGRAMMER - SYSTEMS', 'Administrador', '', ''),
(7, 'Netfex Programmer - Systems', 'Administrador', '11:01:20 PM', '2016/11/19'),
(8, 'Netfex Programmer - Systems', 'Administrador', '09:56:57 PM', '2016/11/20'),
(9, 'Netfex Programmer - Systems', 'Administrador', '09:57:05 PM', '2016/11/20'),
(10, 'NETFEX PROGRAMMER - SYSTEMS', 'Administrador', '', ''),
(11, 'Netfex Programmer - Systems', 'Administrador', '08:17:25 PM', '2016/11/25'),
(12, 'Netfex Programmer - Systems', 'Administrador', '08:17:35 PM', '2016/11/25'),
(13, 'Netfex Programmer - Systems', 'Administrador', '08:41:47 PM', '2016/11/25'),
(14, 'Netfex Programmer - Systems', 'Administrador', '01:45:45 PM', '2016/11/26'),
(15, 'Netfex Programmer - Systems', 'Administrador', '09:29:36 AM', '2016/11/27'),
(16, 'Netfex Programmer - Systems', 'Administrador', '09:37:26 AM', '2016/11/27'),
(17, 'Netfex Programmer - Systems', 'Administrador', '11:12:36 AM', '2016/11/27'),
(18, 'Netfex Programmer - Systems', 'Administrador', '08:32:14 PM', '2016/11/28'),
(19, 'Netfex Programmer - Systems', 'Administrador', '10:31:39 AM', '2016/11/29'),
(20, 'Netfex Programmer - Systems', 'Administrador', '11:03:28 PM', '2016/11/29'),
(21, 'Netfex Programmer - Systems', 'Administrador', '06:38:37 PM', '2016/11/30'),
(22, 'Netfex Programmer - Systems', 'Administrador', '06:41:53 PM', '2016/11/30'),
(23, 'Netfex Programmer - Systems', 'Administrador', '08:20:50 AM', '2016/12/02'),
(24, 'Netfex Programmer - Systems', 'Administrador', '08:25:18 AM', '2016/12/02'),
(25, 'Netfex Programmer - Systems', 'Administrador', '10:06:54 AM', '2016/12/02'),
(26, 'Netfex Programmer - Systems', 'Administrador', '10:43:45 AM', '2016/12/13'),
(27, 'Netfex Programmer - Systems', 'Administrador', '10:43:47 AM', '2016/12/13'),
(28, 'Netfex Programmer - Systems', 'Administrador', '10:43:52 AM', '2016/12/13'),
(29, 'Netfex Programmer - Systems', 'Administrador', '10:44:00 AM', '2016/12/13'),
(30, 'Netfex Programmer - Systems', 'Administrador', '10:44:06 AM', '2016/12/13'),
(31, 'Netfex Programmer - Systems', 'Administrador', '10:50:32 AM', '2016/12/13'),
(32, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '10:51:56 AM', '2016/12/13'),
(33, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '12:33:59 AM', '2016/12/15'),
(34, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '12:35:36 AM', '2016/12/15'),
(35, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '09:00:32 AM', '2017/02/10'),
(36, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '09:00:44 AM', '2017/02/10'),
(37, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '08:33:32 PM', '2017/03/10'),
(38, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '08:33:43 PM', '2017/03/10'),
(39, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '08:25:25 AM', '2017/03/20'),
(40, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '08:25:37 AM', '2017/03/20'),
(41, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '08:05:07 AM', '2017/04/10'),
(42, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '10:26:15 AM', '2017/09/08'),
(43, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '11:02:11 AM', '2017/09/08'),
(44, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '12:10:24 PM', '2017/09/08'),
(45, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '09:21:18 PM', '2019/01/04'),
(46, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '09:39:03 PM', '2019/01/05'),
(47, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '09:07:05 PM', '2019/01/06'),
(48, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '21:01:573 PM', '2019/01/06'),
(49, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '21:01:696 PM', '2019/01/06'),
(50, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '22:01:380 PM', '2019/01/06'),
(51, 'Fernando Margarito Gonzalez Rodriguez', 'Empleado', '10:01:679 AM', '2019/01/07'),
(52, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '10:01:684 AM', '2019/01/07'),
(53, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '11:01:903 AM', '2019/01/07'),
(54, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '11:01:901 AM', '2019/01/07'),
(55, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '11:01:698 AM', '2019/01/07'),
(56, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '11:01:722 AM', '2019/01/07'),
(57, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '11:01:643 AM', '2019/01/07'),
(58, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '06:01:471 AM', '2019/01/08'),
(59, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '06:01:475 AM', '2019/01/08'),
(60, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '07:01:355 AM', '2019/01/08'),
(61, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '22:01:226 PM', '2019/01/09'),
(62, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '07:01:653 AM', '2019/01/11'),
(63, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '07:01:739 AM', '2019/01/11'),
(64, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '07:01:924 AM', '2019/01/11'),
(65, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '09:01:533 AM', '2019/01/11'),
(66, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '07:54:26 AM', '2019/01/22'),
(67, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '06:29:29 AM', '2019/01/23'),
(68, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '06:32:11 AM', '2019/01/23'),
(69, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '08:11:52 AM', '2019/01/23'),
(70, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '06:53:22 AM', '2019/01/24'),
(71, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '07:03:21 AM', '2019/01/24'),
(72, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '07:29:31 AM', '2019/01/24'),
(73, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '07:33:44 AM', '2019/01/24'),
(74, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '07:37:56 AM', '2019/01/24'),
(75, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '20:44:05 PM', '2019/01/24'),
(76, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '07:20:28 AM', '2019/01/25'),
(77, 'Fernando Margarito Gonzalez Rodriguez', '---Seleccionar---', '23:26:22 PM', '2019/01/25'),
(78, 'Fernando Margarito Gonzalez Rodriguez', '---Seleccionar---', '23:26:23 PM', '2019/01/25'),
(79, 'Fernando Margarito Gonzalez Rodriguez', '---Seleccionar---', '23:26:24 PM', '2019/01/25'),
(80, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '23:26:32 PM', '2019/01/25'),
(81, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '07:11:54 AM', '2019/01/26'),
(82, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '07:45:40 AM', '2019/01/26'),
(83, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '20:36:07 PM', '2019/01/26'),
(84, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '22:26:02 PM', '2019/02/03'),
(85, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '22:27:28 PM', '2019/02/03'),
(86, 'EMP0002', 'Administrador', '21:51:01 PM', '2019/02/05'),
(87, 'EMP0002', 'Empleado', '21:51:10 PM', '2019/02/05'),
(88, 'EMP0003', 'Empleado', '21:53:11 PM', '2019/02/05'),
(89, 'EMP0002', 'Empleado', '22:02:06 PM', '2019/02/05'),
(90, 'EMP0003', 'Empleado', '00:17:43 AM', '2019/02/07'),
(91, 'EMP0002', 'Empleado', '23:05:56 PM', '2019/02/07'),
(92, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '23:09:51 PM', '2019/02/07'),
(93, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '09:15:18 AM', '2019/02/08'),
(94, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '09:22:09 AM', '2019/02/08'),
(95, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '11:37:42 AM', '2019/02/08'),
(96, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '06:30:03 AM', '2019/02/09'),
(97, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '20:11:09 PM', '2019/02/09'),
(98, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '20:13:11 PM', '2019/02/09'),
(99, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '12:24:49 PM', '2019/02/10'),
(100, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '13:20:25 PM', '2019/02/10'),
(101, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '13:20:54 PM', '2019/02/10'),
(102, 'Rene Ordoñez Barba', 'Administrador', '15:08:35 PM', '2019/02/10'),
(103, 'Fernando Margarito Gonzalez Rodriguez', 'Administrador', '13:33:37 PM', '2019/03/28'),
(104, 'nawar', 'Administrador', '13:44:12 PM', '2019/03/28'),
(105, 'nawar', 'Administrador', '17:29:58 PM', '2019/03/28'),
(106, 'nawar', 'Administrador', '14:08:47 PM', '2019/03/29'),
(107, 'nawar', 'Administrador', '14:30:12 PM', '2019/03/29'),
(108, 'nawar', 'Administrador', '22:16:08 PM', '2019/03/29'),
(109, 'nawar', 'Administrador', '22:27:48 PM', '2019/03/29'),
(110, 'nawar', 'Administrador', '10:59:40 AM', '2019/04/01'),
(111, 'nawar', 'Administrador', '11:53:24 AM', '2019/04/01'),
(112, 'nawar', 'Administrador', '08:52:12 AM', '2019/04/05'),
(113, 'nawar', 'Administrador', '09:19:32 AM', '2019/04/05'),
(114, 'nawar', 'Administrador', '09:20:48 AM', '2019/04/05'),
(115, 'nawar', 'Administrador', '09:27:03 AM', '2019/04/05'),
(116, 'nawar', 'Administrador', '09:45:23 AM', '2019/04/05'),
(117, 'nawar', 'Administrador', '09:54:03 AM', '2019/04/05'),
(118, 'nawar', 'Administrador', '18:27:31 PM', '2019/04/05'),
(119, 'nawar', 'Administrador', '09:26:10 AM', '2019/04/06'),
(120, 'nawar', 'Administrador', '16:33:18 PM', '2019/04/08'),
(121, 'nawar8417', 'Administrador', '16:59:17 PM', '2019/04/08'),
(122, 'nawar', 'Administrador', '16:59:28 PM', '2019/04/08'),
(123, 'nawar', 'Administrador', '22:39:27 PM', '2019/04/09'),
(124, 'admin', 'Administrador', '22:54:58 PM', '2019/04/09'),
(125, 'admin', 'Administrador', '23:07:54 PM', '2019/04/09'),
(126, 'admin', 'Administrador', '23:29:29 PM', '2019/04/09'),
(127, 'admin', 'Administrador', '23:48:15 PM', '2019/04/09'),
(128, 'admin', 'Administrador', '23:59:49 PM', '2019/04/09'),
(129, 'admin', 'Administrador', '00:07:31 AM', '2019/04/10'),
(130, 'admin', '---Seleccionar---', '21:31:18 PM', '2019/04/10'),
(131, 'admin', 'Administrador', '21:32:03 PM', '2019/04/10'),
(132, 'admin', 'Administrador', '23:15:01 PM', '2019/04/10'),
(133, 'admin', 'Administrador', '19:37:46 PM', '2019/04/12'),
(134, 'admin', 'Administrador', '19:42:42 PM', '2019/04/14'),
(135, 'admin', 'Administrador', '19:52:05 PM', '2019/04/14'),
(136, 'admin', 'Administrador', '20:04:27 PM', '2019/04/14'),
(137, 'admin', 'Administrador', '20:13:45 PM', '2019/04/14'),
(138, 'admin', 'Administrador', '20:17:13 PM', '2019/04/14'),
(139, 'admin', 'Administrador', '09:44:45 AM', '2019/04/15'),
(140, 'admin', 'Administrador', '09:46:35 AM', '2019/04/15'),
(141, 'admin', 'Administrador', '09:48:17 AM', '2019/04/15'),
(142, 'admin', 'Administrador', '09:51:49 AM', '2019/04/15'),
(143, 'admin', 'Administrador', '09:56:47 AM', '2019/04/15'),
(144, 'admin', 'Administrador', '10:03:33 AM', '2019/04/15'),
(145, 'admin', 'Administrador', '10:06:12 AM', '2019/04/15'),
(146, 'admin', 'Administrador', '10:09:25 AM', '2019/04/15'),
(147, 'admin', 'Administrador', '10:17:09 AM', '2019/04/15'),
(148, 'admin', 'Administrador', '10:21:46 AM', '2019/04/15'),
(149, 'admin', 'Administrador', '10:25:30 AM', '2019/04/15'),
(150, 'admin', 'Administrador', '10:31:17 AM', '2019/04/15'),
(151, 'admin', 'Administrador', '10:33:19 AM', '2019/04/15'),
(152, 'admin', 'Administrador', '10:34:14 AM', '2019/04/15'),
(153, 'admin', 'Administrador', '10:36:01 AM', '2019/04/15'),
(154, 'admin', 'Administrador', '10:38:32 AM', '2019/04/15'),
(155, 'admin', 'Administrador', '10:45:55 AM', '2019/04/15'),
(156, 'admin', 'Administrador', '10:47:57 AM', '2019/04/15'),
(157, 'admin', 'Administrador', '10:55:38 AM', '2019/04/15'),
(158, 'admin', 'Administrador', '10:58:26 AM', '2019/04/15'),
(159, 'admin', 'Administrador', '11:00:37 AM', '2019/04/15'),
(160, 'admin', 'Administrador', '17:17:44 PM', '2019/04/15'),
(161, 'admin', 'Administrador', '19:10:43 PM', '2019/04/15'),
(162, 'admin', 'Administrador', '19:14:25 PM', '2019/04/15'),
(163, 'admin', 'Administrador', '19:49:21 PM', '2019/04/15'),
(164, 'admin', 'Administrador', '19:51:39 PM', '2019/04/15'),
(165, 'admin', 'Administrador', '19:58:49 PM', '2019/04/15'),
(166, 'admin', 'Administrador', '20:06:34 PM', '2019/04/15'),
(167, 'admin', 'Administrador', '20:10:42 PM', '2019/04/15'),
(168, 'admin', 'Administrador', '20:24:20 PM', '2019/04/15'),
(169, 'admin', 'Administrador', '20:25:24 PM', '2019/04/15'),
(170, 'admin', 'Administrador', '20:34:01 PM', '2019/04/15'),
(171, 'admin', 'Administrador', '20:40:05 PM', '2019/04/15'),
(172, 'admin', 'Administrador', '20:43:37 PM', '2019/04/15'),
(173, 'admin', 'Administrador', '20:47:06 PM', '2019/04/15'),
(174, 'admin', 'Administrador', '20:54:16 PM', '2019/04/15'),
(175, 'admin', 'Administrador', '20:57:27 PM', '2019/04/15'),
(176, 'admin', 'Administrador', '20:58:51 PM', '2019/04/15'),
(177, 'admin', 'Administrador', '21:01:31 PM', '2019/04/15'),
(178, 'admin', 'Administrador', '21:04:04 PM', '2019/04/15'),
(179, 'admin', 'Administrador', '21:06:52 PM', '2019/04/15'),
(180, 'admin', 'Administrador', '09:50:46 AM', '2019/04/16'),
(181, 'admin', 'Administrador', '09:54:20 AM', '2019/04/16'),
(182, 'admin', 'Administrador', '09:57:25 AM', '2019/04/16'),
(183, 'admin', 'Administrador', '10:02:52 AM', '2019/04/16'),
(184, 'admin', 'Administrador', '10:10:32 AM', '2019/04/16'),
(185, 'admin', 'Administrador', '10:16:18 AM', '2019/04/16'),
(186, 'admin', 'Administrador', '10:18:00 AM', '2019/04/16'),
(187, 'admin', 'Administrador', '10:21:03 AM', '2019/04/16'),
(188, 'admin', 'Administrador', '09:26:03 AM', '2019/04/17'),
(189, 'admin', 'Administrador', '09:36:11 AM', '2019/04/17');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tabla_tickets`
--

DROP TABLE IF EXISTS `tabla_tickets`;
CREATE TABLE IF NOT EXISTS `tabla_tickets` (
  `cod` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE utf8_swedish_ci DEFAULT NULL,
  `encabezado` varchar(100) COLLATE utf8_swedish_ci DEFAULT NULL,
  `direccion` varchar(100) COLLATE utf8_swedish_ci DEFAULT NULL,
  `telefono` varchar(13) COLLATE utf8_swedish_ci DEFAULT NULL,
  `ruc` varchar(13) COLLATE utf8_swedish_ci DEFAULT NULL,
  `pie` varchar(100) COLLATE utf8_swedish_ci DEFAULT NULL,
  `logo` varchar(100) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`cod`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Volcado de datos para la tabla `tabla_tickets`
--

INSERT INTO `tabla_tickets` (`cod`, `nombre`, `encabezado`, `direccion`, `telefono`, `ruc`, `pie`, `logo`) VALUES
(1, 'EMPRESA DE PRUEBA', 'EMPRESA DE PRUEBA', 'EMPRESA DE PRUEBA', '0722222222', '1722102101001', '****Gracias por su visitarnos****', 'C://Users//NAWAR//Pictures//45608354_335220907273097_6537145652797767680_n (1).jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tabla_unidades`
--

DROP TABLE IF EXISTS `tabla_unidades`;
CREATE TABLE IF NOT EXISTS `tabla_unidades` (
  `codunidad` int(8) NOT NULL AUTO_INCREMENT,
  `unidad` varchar(80) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`codunidad`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Volcado de datos para la tabla `tabla_unidades`
--

INSERT INTO `tabla_unidades` (`codunidad`, `unidad`) VALUES
(1, 'LT'),
(2, 'UN'),
(3, 'PAQ'),
(4, 'EMP'),
(5, 'GLN'),
(7, 'GRANEL');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tabla_usuarios`
--

DROP TABLE IF EXISTS `tabla_usuarios`;
CREATE TABLE IF NOT EXISTS `tabla_usuarios` (
  `idusuario` int(11) NOT NULL,
  `nick` varchar(100) COLLATE utf8_swedish_ci DEFAULT NULL,
  `password` varchar(20) COLLATE utf8_swedish_ci DEFAULT NULL,
  `tipousuario` varchar(20) COLLATE utf8_swedish_ci DEFAULT NULL,
  `pregunta` varchar(100) COLLATE utf8_swedish_ci DEFAULT NULL,
  `respuesta` varchar(100) COLLATE utf8_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`idusuario`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Volcado de datos para la tabla `tabla_usuarios`
--

INSERT INTO `tabla_usuarios` (`idusuario`, `nick`, `password`, `tipousuario`, `pregunta`, `respuesta`) VALUES
(1, 'Fernando Margarito Gonzalez Rodriguez', '1111', 'Administrador', 'Estado?', 'admin'),
(2, 'Rene Ordoñez Barba', '1111', 'Administrador', 'quien', 'yo'),
(3, 'EMP0003', '1111', 'Empleado', 'quien', 'yo'),
(4, 'nawar', 'Nawar8417', 'Administrador', 'sobrenombre', 'nawar'),
(5, 'admin', 'admin', 'Administrador', 'nombre', 'admin');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tabla_ventas`
--

DROP TABLE IF EXISTS `tabla_ventas`;
CREATE TABLE IF NOT EXISTS `tabla_ventas` (
  `num_fact` varchar(12) COLLATE utf8_swedish_ci NOT NULL,
  `cliente` varchar(100) COLLATE utf8_swedish_ci NOT NULL,
  `subtotal` double(7,0) DEFAULT NULL,
  `iva` double(7,2) DEFAULT NULL,
  `total` double(7,0) DEFAULT NULL,
  `formapago` varchar(50) COLLATE utf8_swedish_ci DEFAULT NULL,
  `hora` varchar(15) COLLATE utf8_swedish_ci NOT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`num_fact`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Volcado de datos para la tabla `tabla_ventas`
--

INSERT INTO `tabla_ventas` (`num_fact`, `cliente`, `subtotal`, `iva`, `total`, `formapago`, `hora`, `fecha`) VALUES
('00000001', 'Público en General', 3, 0.31, 3, 'Contado', '23:22:17 PM', '2019-02-09'),
('00000002', 'Público en General', 2, 0.28, 3, 'Contado', '23:23:26 PM', '2019-02-09'),
('00000003', 'Mario Efrain Rodríguez', 2, 0.25, 2, 'Contado', '12:38:55 PM', '2019-02-10'),
('00000004', 'Público en General', 19, 2.29, 21, 'Crédito', '13:02:01 PM', '2019-02-10'),
('00000005', 'Consumidor final', 2, 0.19, 2, 'Contado', '12:21:02 PM', '2019-04-01'),
('00000006', 'Consumidor final', 1, 0.08, 1, 'Contado', '09:22:58 AM', '2019-04-05'),
('00000007', 'Consumidor final', 5, 0.63, 6, 'Contado', '09:31:18 AM', '2019-04-06'),
('00000008', 'Consumidor final', 18002, 2160.19, 20162, 'Contado', '17:01:15 PM', '2019-04-08'),
('00000009', 'Consumidor final', 24960, 27955.00, 27955, 'Contado', '22:30:11 PM', '2019-04-09'),
('00000010', 'Consumidor final', 13000, 14560.00, 14560, 'Contado', '00:07:37 AM', '2019-04-10');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
