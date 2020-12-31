
INSERT into mercadeoucab.lugar (codigo, nombre, tipo, categoriaSocioEconomica, estado, fk_lugar) values
  -- Estados
  (1,'Amazonas','Estado', 'Alta', 'A', null),
  (2,'Anzoátegui','Estado','Alta', 'A', null),
    (3,'Apure','Estado','Alta', 'A', null),
  (4,'Aragua','Estado','Alta', 'A', null),
  (5,'Barinas','Estado','Alta', 'A', null),
  (6,'Bolívar','Estado','Alta', 'A', null),
  (7,'Carabobo','Estado','Alta', 'A', null),
  (8,'Cojedes','Estado','Alta', 'A', null),
  (9,'Delta Amacuro','Estado','Alta', 'A', null),
  (10,'Falcón','Estado','Alta', 'A', null),
  (11,'Guárico','Estado','Alta', 'A', null),
  (12,'Lara','Estado','Alta', 'A', null),
  (13,'Mérida','Estado','Alta', 'A', null),
  (14,'Miranda','Estado','Alta', 'A', null),
  (15,'Monagas','Estado','Alta', 'A', null),
  (16,'Nueva Esparta','Estado','Alta', 'A', null),
  (17,'Portuguesa','Estado','Alta', 'A', null),
  (18,'Sucre','Estado','Alta', 'A', null),
  (19,'Táchira','Estado','Alta', 'A', null),
  (20,'Trujillo','Estado','Alta', 'A', null),
  (21,'Vargas','Estado','Alta', 'A', null),
  (22,'Yaracuy','Estado','Alta', 'A', null),
  (23,'Zulia','Estado','Alta', 'A', null),
  (24,'Distrito Capital','Estado','Alta', 'A', null);
	

INSERT into mercadeoucab.rol (codigo, nombre, descripcion, estado)  values
  -- Roles
  (1, 'Administrador', 'prueba', 'A'),
  (2, 'Cliente', 'prueba', 'A'),
  (3, 'Analista', 'prueba', 'A'),
  (4, 'Encuestado', 'prueba', 'A');

 INSERT into mercadeoucab.ocupacion(codigo, nombre, estado)  values
  -- Ocupaciones
  (1, 'Arquitecto', 'A'),
  (2, 'Médico', 'A'),
  (3, 'Profesor', 'A'),
  (4, 'Abogado', 'A'),
  (5, 'Ingeniero', 'A'),
  (6, 'Enfermero', 'A'),
  (7, 'Contador publico', 'A'),
  (8, 'Administrador', 'A');

 INSERT into mercadeoucab.nivel_academico (codigo, nivel, estado)  values
  (1, 'Analfabeta', 'A'),
  (2, 'Sin estudios', 'A'),
  (3, 'Educación básica o primaria', 'A'),
  (4, 'Educación media o secundaria', 'A'),
  (5, 'Grado universitario', 'A'),
  (6, 'Postgrado', 'A');
  
  INSERT into mercadeoucab.nivel_economico (codigo, nivel, estado) values
  -- NivelesEconomicos
  (1, 'Muy bajo', 'A'),
  (2, 'Clase baja', 'A'),
  (3, 'Clase media', 'A'),
  (4, 'Clase alta', 'A'),
  (5, 'Muy alto', 'A');
  
INSERT INTO mercadeoucab.dato_usuario (`codigo`, `cedula`, `primerNombre`, `segundoNombre`, `primerApellido`, `segundoApellido`, 
  `sexo`, `fechaNacimiento`, `estadoCivil`, `disponibilidadEnLinea`, `conCuantasPersonasVive`, `estado`, 
  `fk_nivelEconomico`, `fk_lugar`, `fk_ocupacion`, `fk_nivelAcademico`) 
VALUES 
('1', '23678678', 'Pepe', 'Luis', 'Gonzalez', 'Pino', 'M', '1993-10-15', 'S', 'SI', '1', 'A', '1', '2', '1', '2'),
('2', '14325424', 'Jose', 'Carlos', 'Martinez', 'Lopez', 'M', '1999-03-20', 'S', 'SI', '4', 'A', '1', '2', '1', '2'),
('3', '26324424', 'Maria', 'Antonieta', 'Gomez', 'Cisnero', 'M', '2001-04-20', 'S', 'SI', '1', 'A', '1', '1', '1', '1'),
('4', '28762733', 'Julieta', 'Ana', 'Venegas', 'Frailer', 'M', '1970-02-12', 'D', 'NO', '1', 'A', '1', '1', '1', '1'),
('5', '21232434', 'Rosa', 'Maria', 'Beleno', 'Perez', 'M', '1994-05-19', 'C', 'NO', '1', 'A', '1', '1', '1', '1'),
('6', '25786687', 'Carlos', 'Alberto', 'Sanoja', 'Torres', 'M', '1992-05-20', 'C', 'SI', '1', 'A', '1', '1', '1', '1');

INSERT INTO `mercadeoucab`.`hijo` (`codigo`, `fechaNacimiento`, `genero`, `estado`, `fk_datoUsuario`) VALUES
 ('1', '1993-10-31', 'M', 'A', '1'),
 ('2', '2005-05-20', 'F', 'A', '1'),
 ('3', '2007-06-12', 'F', 'A', '2'),
 ('4', '1995-05-12', 'M', 'A', '2'),
 ('5', '2001-05-12', 'M', 'A', '3');


INSERT INTO `mercadeoucab`.`usuario` 
('1', 'Gino', 'gmlm60832@gmail.com', '1234', '1234', 'A', '2', '1'),
('2', 'Pepe', 'pepe@gmail.com', '1234', '1234', 'A', '1', '2'),
,'3', 'Jose', 'jose@gmail.com', '1234', '1234', 'A', '4', '3'),
('4', 'Maria', 'maria@gmail.com', '1234', '1234', 'A', '4', '4'),
('5', 'Julieta', 'julieta@gmail.com', '1234', '1234', 'A', '4', '5'),
('6', 'Rosa', 'rosa@gmail.com', '1234', '1234', 'A', '4', '6'),
('7', 'Carlos', 'carlos@gmail.com', '1234', '1234', 'A', '3');

INSERT INTO `mercadeoucab`.`marca`(codigo, nombre, estado) values
('1', 'Pepsi', 'A'),
('2', 'Coca', 'A');


INSERT INTO `mercadeoucab`.`categoria`(codigo, nombre, estado) values
 ('1', 'Categoria pepsi', 'A'),
 ('2', 'Categoria coca', 'A');

INSERT INTO mercadeoucab.subcategoria(codigo, nombre, descripcion, estado, fk_categoria) values  
('1', 'Botella 1 litro', 'Botella de litro ', 'A', '1'),
('2', 'Botella de medio litro', 'Botella de medio litro', 'A', '2');

INSERT INTO `mercadeoucab`.`producto` (codigo, nombre, descripcion, estado, fk_marca, fk_subcategoria, fk_usuario) values
('1', 'Producto botella', 'retornable', 'A', '1', '1', '2'),
('2', 'Producto botella', 'No retornable', 'A', '2', '2', '2');


INSERT INTO `mercadeoucab`.`usuario` (codigo, nombreUsuario, correo, password, codigoRecuperacion, estado, fk_rol, fk_datoUsuario) values
('1', 'Gino', 'gmlm60832@gmail.com', '1234', '1234', 'A', '2', null),
('2', 'Pepe', 'pepe@gmail.com', '1234', '1234', 'A', '1', null),
('3', 'Jose', 'jose@gmail.com', '1234', '1234', 'A', '4', '3'),
('4', 'Maria', 'maria@gmail.com', '1234', '1234', 'A', '4', '4'),
('5', 'Julieta', 'julieta@gmail.com', '1234', '1234', 'A', '4', '5'),
('6', 'Rosa', 'rosa@gmail.com', '1234', '1234', 'A', '4', '6'),
('7', 'Carlos', 'carlos@gmail.com', '1234', '1234', 'A', '3', null);


INSERT INTO `mercadeoucab`.`solicitud_estudio` (`codigo`, `descripcionSolicitud`, `generoPoblacional`, `fechaPeticion`, `edadMinimaPoblacion`, `edadMaximaPoblacion`, `estado`, `estatus`,`conCuantasPersonasVive`, `disponibilidadEnLinea`, `fk_nivelEconomico`, `fk_ocupacion`, `fk_usuario`, `fk_producto`) VALUES 
('1', 'Encuesta para coca cola', 'M', '2020-12-23', '17', '27', 'A', 'EN', '1', 'SI', '1', '1', '1', '2'),
('2', 'Encuesta para pepsi', 'F', '2020-12-23', '15', '17', 'A', 'S','2', 'SI', '2', '1', '1', '1');

INSERT INTO `mercadeoucab`.`estudio` (`codigo`, `nombre`, `fechaInicio`, `fechaFin`, `estatus`, `estado`, `fk_solicitudEstudio`, `fk_usuario`) VALUES ('1', 'Encuesta pepsi', '2020-12-23', '2020-12-24', 'EP', 'A', '2', '7');
INSERT INTO `mercadeoucab`.`estudio` (`codigo`, `nombre`, `fechaInicio`, `fechaFin`, `estatus`, `estado`, `fk_solicitudEstudio`, `fk_usuario`) VALUES ('2', 'Encuesta coca',  '2020-12-24', '2020-12-28', 'S', 'A', '1', '7');

INSERT INTO `mercadeoucab`.`region_estudio` (`codigo`, `estado`, `fk_lugar`, `fk_solicitudEstudio`) VALUES ('1', 'A', '1', '1');
INSERT INTO `mercadeoucab`.`region_estudio` (`codigo`, `estado`, `fk_lugar`, `fk_solicitudEstudio`) VALUES ('2', 'A', '2', '2');



  
  
