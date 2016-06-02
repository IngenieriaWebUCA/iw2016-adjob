CREATE DATABASE  IF NOT EXISTS `adjob` /*!40100 DEFAULT CHARACTER SET latin1 */;
GRANT ALL PRIVILEGES ON `adjob`.* TO 'adjob'@'localhost';
USE `adjob`;
-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 02-06-2016 a las 18:52:21
-- Versión del servidor: 5.5.49-0ubuntu0.14.04.1
-- Versión de PHP: 5.5.9-1ubuntu4.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `adjob`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cursos`
--

CREATE TABLE IF NOT EXISTS `cursos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(30) DEFAULT NULL,
  `fecha_fin` datetime NOT NULL,
  `fecha_inicio` datetime NOT NULL,
  `nombre` varchar(30) DEFAULT NULL,
  `nombre_academia` varchar(30) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `usuario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_8k48hc1oxcalroi9dw7ufdn7k` (`usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cv`
--

CREATE TABLE IF NOT EXISTS `cv` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `foto` varchar(30) DEFAULT NULL,
  `trayectoria` varchar(300) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `usuario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m8k5e3mrdmk82n8ufamvrbvsv` (`usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cv_cursos`
--

CREATE TABLE IF NOT EXISTS `cv_cursos` (
  `cv` bigint(20) NOT NULL,
  `cursos` bigint(20) NOT NULL,
  PRIMARY KEY (`cv`,`cursos`),
  KEY `FK_ag4ools65wlbdvih2napncgmx` (`cursos`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cv_experiencia`
--

CREATE TABLE IF NOT EXISTS `cv_experiencia` (
  `cv` bigint(20) NOT NULL,
  `experiencia` bigint(20) NOT NULL,
  PRIMARY KEY (`cv`,`experiencia`),
  KEY `FK_eruku8icifkajprt2hke51lmj` (`experiencia`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cv_idiomas`
--

CREATE TABLE IF NOT EXISTS `cv_idiomas` (
  `cv` bigint(20) NOT NULL,
  `idiomas` bigint(20) NOT NULL,
  PRIMARY KEY (`cv`,`idiomas`),
  KEY `FK_h5xt9qxw5o801hwakt760majg` (`idiomas`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cv_puestos_posibles`
--

CREATE TABLE IF NOT EXISTS `cv_puestos_posibles` (
  `cv` bigint(20) NOT NULL,
  `puestos_posibles` bigint(20) NOT NULL,
  PRIMARY KEY (`cv`,`puestos_posibles`),
  KEY `FK_i8ck5398knehp5loy9smaojdh` (`puestos_posibles`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cv_titulos`
--

CREATE TABLE IF NOT EXISTS `cv_titulos` (
  `cv` bigint(20) NOT NULL,
  `titulos` bigint(20) NOT NULL,
  PRIMARY KEY (`cv`,`titulos`),
  KEY `FK_ius7naqxmq5c1aykbf4kbrpo4` (`titulos`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresa`
--

CREATE TABLE IF NOT EXISTS `empresa` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `actividad_profesional` varchar(200) DEFAULT NULL,
  `cif` varchar(9) DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `nombre` varchar(30) DEFAULT NULL,
  `numero_empleados` int(11) DEFAULT NULL,
  `telefono` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `web` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `experiencia`
--

CREATE TABLE IF NOT EXISTS `experiencia` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha_fin` datetime NOT NULL,
  `fecha_inicio` datetime NOT NULL,
  `nombre_empresa` varchar(30) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `puesto` bigint(20) DEFAULT NULL,
  `usuario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_hy1mg5bxn2ea3rtgu89ssefuu` (`puesto`),
  KEY `FK_847tfmixfvoxg8nib9893247r` (`usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `idiomas`
--

CREATE TABLE IF NOT EXISTS `idiomas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(50) DEFAULT NULL,
  `entidad_emisora` varchar(30) DEFAULT NULL,
  `nivel` varchar(10) DEFAULT NULL,
  `nombre_titulo` varchar(30) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `usuario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m719gqh4qie25v2c1q412fpst` (`usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `oferta`
--

CREATE TABLE IF NOT EXISTS `oferta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `estado_oferta` int(11) DEFAULT NULL,
  `fecha_fin` datetime NOT NULL,
  `fecha_inicio` datetime NOT NULL,
  `informacion` varchar(200) DEFAULT NULL,
  `nombre` varchar(30) DEFAULT NULL,
  `numero_vacantes` int(11) DEFAULT NULL,
  `perfil` varchar(200) DEFAULT NULL,
  `sueldo` float DEFAULT NULL,
  `tipo_contrato` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `empresa` bigint(20) DEFAULT NULL,
  `puesto_buscado` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5p5cq9yp4r32f9gpmfrp6x9ca` (`empresa`),
  KEY `FK_m38lfxqk8k89trxsalwdileoy` (`puesto_buscado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `peticion_oferta`
--

CREATE TABLE IF NOT EXISTS `peticion_oferta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `estado` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `curriculum` bigint(20) DEFAULT NULL,
  `oferta` bigint(20) DEFAULT NULL,
  `usuario_demandante` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_20ocnphxcrctn2xol3sn6mgjw` (`curriculum`),
  KEY `FK_kroh7kq5mj2y2h9xtu7jrk831` (`oferta`),
  KEY `FK_klf70mhjcpf615u7r1k2sni2v` (`usuario_demandante`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `puesto_trabajo`
--

CREATE TABLE IF NOT EXISTS `puesto_trabajo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(100) DEFAULT NULL,
  `nombre` varchar(30) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `titulos`
--

CREATE TABLE IF NOT EXISTS `titulos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(30) DEFAULT NULL,
  `entidad_emisora` varchar(30) DEFAULT NULL,
  `fecha_obtencion` datetime NOT NULL,
  `nombre` varchar(30) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `usuario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_myvbco4x62arm33m1efemxavg` (`usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `apellidos` varchar(50) DEFAULT NULL,
  `contrasena` varchar(255) NOT NULL,
  `direccion` varchar(30) DEFAULT NULL,
  `dni` varchar(9) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `fecha_nacimiento` datetime NOT NULL,
  `nombre` varchar(30) DEFAULT NULL,
  `sexo` int(11) DEFAULT NULL,
  `telefono` int(11) DEFAULT NULL,
  `tipo` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_empresas_gestionadas`
--

CREATE TABLE IF NOT EXISTS `usuario_empresas_gestionadas` (
  `usuario` bigint(20) NOT NULL,
  `empresas_gestionadas` bigint(20) NOT NULL,
  PRIMARY KEY (`usuario`,`empresas_gestionadas`),
  KEY `FK_j9g0dh69mon3tif219tbat1xd` (`empresas_gestionadas`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cursos`
--
ALTER TABLE `cursos`
  ADD CONSTRAINT `FK_8k48hc1oxcalroi9dw7ufdn7k` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `cv`
--
ALTER TABLE `cv`
  ADD CONSTRAINT `FK_m8k5e3mrdmk82n8ufamvrbvsv` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `cv_cursos`
--
ALTER TABLE `cv_cursos`
  ADD CONSTRAINT `FK_l6dil576fl3gd87c6kawyday3` FOREIGN KEY (`cv`) REFERENCES `cv` (`id`),
  ADD CONSTRAINT `FK_ag4ools65wlbdvih2napncgmx` FOREIGN KEY (`cursos`) REFERENCES `cursos` (`id`);

--
-- Filtros para la tabla `cv_experiencia`
--
ALTER TABLE `cv_experiencia`
  ADD CONSTRAINT `FK_pk7vcbemto3qtjcoa3fw21w0r` FOREIGN KEY (`cv`) REFERENCES `cv` (`id`),
  ADD CONSTRAINT `FK_eruku8icifkajprt2hke51lmj` FOREIGN KEY (`experiencia`) REFERENCES `experiencia` (`id`);

--
-- Filtros para la tabla `cv_idiomas`
--
ALTER TABLE `cv_idiomas`
  ADD CONSTRAINT `FK_qlnqhupghvsxdn1a9q878dk67` FOREIGN KEY (`cv`) REFERENCES `cv` (`id`),
  ADD CONSTRAINT `FK_h5xt9qxw5o801hwakt760majg` FOREIGN KEY (`idiomas`) REFERENCES `idiomas` (`id`);

--
-- Filtros para la tabla `cv_puestos_posibles`
--
ALTER TABLE `cv_puestos_posibles`
  ADD CONSTRAINT `FK_4c2k0kd6jhsr2ke3w72qmbb60` FOREIGN KEY (`cv`) REFERENCES `cv` (`id`),
  ADD CONSTRAINT `FK_i8ck5398knehp5loy9smaojdh` FOREIGN KEY (`puestos_posibles`) REFERENCES `puesto_trabajo` (`id`);

--
-- Filtros para la tabla `cv_titulos`
--
ALTER TABLE `cv_titulos`
  ADD CONSTRAINT `FK_hi4qhwex423sui1qubaxqrn0v` FOREIGN KEY (`cv`) REFERENCES `cv` (`id`),
  ADD CONSTRAINT `FK_ius7naqxmq5c1aykbf4kbrpo4` FOREIGN KEY (`titulos`) REFERENCES `titulos` (`id`);

--
-- Filtros para la tabla `experiencia`
--
ALTER TABLE `experiencia`
  ADD CONSTRAINT `FK_847tfmixfvoxg8nib9893247r` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `FK_hy1mg5bxn2ea3rtgu89ssefuu` FOREIGN KEY (`puesto`) REFERENCES `puesto_trabajo` (`id`);

--
-- Filtros para la tabla `idiomas`
--
ALTER TABLE `idiomas`
  ADD CONSTRAINT `FK_m719gqh4qie25v2c1q412fpst` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `oferta`
--
ALTER TABLE `oferta`
  ADD CONSTRAINT `FK_m38lfxqk8k89trxsalwdileoy` FOREIGN KEY (`puesto_buscado`) REFERENCES `puesto_trabajo` (`id`),
  ADD CONSTRAINT `FK_5p5cq9yp4r32f9gpmfrp6x9ca` FOREIGN KEY (`empresa`) REFERENCES `empresa` (`id`);

--
-- Filtros para la tabla `peticion_oferta`
--
ALTER TABLE `peticion_oferta`
  ADD CONSTRAINT `FK_klf70mhjcpf615u7r1k2sni2v` FOREIGN KEY (`usuario_demandante`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `FK_20ocnphxcrctn2xol3sn6mgjw` FOREIGN KEY (`curriculum`) REFERENCES `cv` (`id`),
  ADD CONSTRAINT `FK_kroh7kq5mj2y2h9xtu7jrk831` FOREIGN KEY (`oferta`) REFERENCES `oferta` (`id`);

--
-- Filtros para la tabla `titulos`
--
ALTER TABLE `titulos`
  ADD CONSTRAINT `FK_myvbco4x62arm33m1efemxavg` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `usuario_empresas_gestionadas`
--
ALTER TABLE `usuario_empresas_gestionadas`
  ADD CONSTRAINT `FK_em9rj73fsodsfunnat3sruq1s` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `FK_j9g0dh69mon3tif219tbat1xd` FOREIGN KEY (`empresas_gestionadas`) REFERENCES `empresa` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
