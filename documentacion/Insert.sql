INSERT into LUGAR (lu_codigo, lu_nombre, lu_tipo) values
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
	

INSERT into ROL  values
  -- Roles
  (1, 'Administrador', 'prueba', 'A'),
  (2, 'Cliente', 'prueba', 'A'),
  (3, 'Analista', 'prueba', 'A'),
  (4, 'Encuestado', 'prueba', 'A');

INSERT into OCUPACION  values
  -- Ocupaciones
  (1, 'Arquitecto', 'A'),
  (2, 'Médico', 'A'),
  (3, 'Profesor', 'A'),
  (4, 'Abogado', 'A'),
  (5, 'Ingeniero', 'A'),
  (6, 'Enfermero', 'A'),
  (7, 'Contador publico', 'A'),
  (8, 'Administrador', 'A');

INSERT into NIVEL_ACADEMICO  values
  -- NivelesAcademicos
  (1, 'Analfabeta', 'A'),
  (2, 'Sin estudios', 'A'),
  (3, 'Educación básica o primaria', 'A'),
  (4, 'Educación media o secundaria', 'A'),
  (5, 'Grado universitario', 'A'),
  (6, 'Postgrado', 'A');

INSERT into NIVEL_ECONOMICO  values
  -- NivelesEconomicos
  (1, 'Muy bajo', 'A'),
  (2, 'Clase baja', 'A'),
  (3, 'Clase media', 'A'),
  (4, 'Clase alta', 'A'),
  (5, 'Muy alto', 'A');