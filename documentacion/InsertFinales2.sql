
-- SegundoNombre está como NOT NULL quizás habría que cambiarlo

-- ////////////////////////////////////// AQUI CORRER EL TEST QUE POPULA CON USUARIOS LA BASE DE DATOS /////////////////////////////////////////
-- En cuanto a los usuarios, hay un servicio que los insertará, serán:
-- El de id 1 administrador
-- Los analistas de id 2 a 5
-- Los clientes de id 6 a 8
-- Los encuestados de id 9 a 40
-- ///////////////////////////////////// AQUI CORRER EL TEST QUE POPULA CON USUARIOS LA BASE DE DATOS /////////////////////////////////////////

ALTER TABLE producto MODIFY descripcion VARCHAR(300);

insert into producto values(1, "VideoPro Editing Software", "Programa para edición de videos", "A", 6, 1, 6);
insert into producto values(2, "iGPU Graphic Card", "Tarjeta gráfica de última generación", "A", 8, 2, 6);
insert into producto values(3, "Confy Shoes", "Los zapatos más comodos del mercado", "A", 1, 21, 6);
insert into producto values(4, "Fancy Shirt", "Camisetas elegantes para todos los días", "A", 1, 23, 6);
insert into producto values(5, "Workout sweater", "Sweater especializado para hacer ejercicio", "A", 2, 23, 7);
insert into producto values(6, "Ultra boots", "Botas hechas con los mejores materiales", "A", 2, 21, 7);
insert into producto values(7, "Abrigo BlackElegant", "El abrigo más elegante nunca diseñado", "A", 1, 23, 7);
insert into producto values(8, "Procesador Sams3000", "Procesador de gama media para PCs", "A", 9, 2, 7);
insert into producto values(9, "iProtect Antivirus", "El antivirus más seguro", "A", 7, 1, 8);
insert into producto values(10, "DataAnalysis tool", "Herramienta de software para análisis del mercado", "A", 5, 1, 8);
insert into producto values(11, "GigaPower RAM", "La memoria RAM de mayor velocidad", "A", 5, 2, 8);
insert into producto values(12, "XiaMemory SSD", "Descripción del 12mo producto", "A", 7, 2, 8);

ALTER TABLE solicitud_estudio MODIFY descripcionSolicitud VARCHAR(300);

insert into Solicitud_estudio values
(1, "Solicitud para iGPU Graphic Card", "Masculino", '2020-12-04', '15', '90', 'Finalizado', 'A','1', 'Si', 1, 5, 6, 2),
(2, "Solicitud para producto Workout Sweater", "Masculino", '2020-12-14', '19', '70', 'Finalizado', 'A', '1', 'No', 2, 5, 7, 5),
(3, "Solicitud para DataAnalysis Tool", "Femenino", '2020-12-20', '14', '85', 'En Proceso', 'A', '3', 'Si', 1, 6, 8, 10),
(4, "Solicitud de estudio para Ultra Boots", "Femenino", '2020-12-21', '20', '88', 'En Proceso', 'A', '2', 'Si', 3, 4, 7, 6),
(5, "Solicitud para VideoPro Editing Software", "Femenino", '2020-12-25', '18', '80', 'En Proceso', 'A', '2', 'Si', 3, 4, 6, 1),
(6, "Solicitud de estudio para producto Confy Shoes", "Masculino", '2020-12-28', '18', '65', 'En Proceso', 'A', '1', 'No', 2, 6, 6, 3),
(7, "Solicitud para GigaPower RAM, memoria RAM", "Masculino", '2021-01-28', '15', '90', 'Solicitado', 'A', '1', 'Si', 1, 5, 8, 11),
(8, "Solicitud de estudio para antivirus iProtect Antivirus", "Masculino", '2021-01-31', '22', '78', 'Solicitado', 'A', '3', 'No', 1, 6, 8, 9);

Insert into Estudio values
(1, "Estudio de iGPU Graphic Card", '2020-12-06', '2020-12-30', 'Finalizado', 'A', "Los resultados fueron satisfactorios", 1, 2),
(2, "Estudio de Workout Sweater", '2020-12-16', '2021-01-05', 'Finalizado', 'A', "Las opiniones del público son muy diversas", 2, 3),
(3, "Estudio de DataAnalysis Tool", '2020-12-22', null, 'En Proceso', 'A', null, 3, 4),
(4, "Estudio de Ultra Boots", '2020-12-23', null, 'En Proceso', 'A', null, 4, 5),
(5, "Estudio de VideoPro Editing Software", '2020-12-28', null, 'En Proceso', 'A', null, 5, 2),
(6, "Estudio de Confy Shoes", '2020-12-30', null, 'En Proceso', 'A', null, 6, 3);

insert into hijo values 
(1, '2010-10-12', 'Masculino', 'A', 2),
(2, '2011-11-01', 'Masculino', 'A', 4),
(3, '2016-05-15', 'Femenino', 'A', 4),
(4, '2000-11-11', 'Masculino', 'A', 5),
(5, '2008-04-10', 'Femenino', 'A', 7),
(6, '2012-10-31', 'Femenino', 'A', 7),
(7, '2009-10-21', 'Masculino', 'A', 8),
(8, '2011-01-13', 'Femenino', 'A', 10),
(9, '2015-10-08', 'Femenino', 'A', 13),
(10, '2004-06-07', 'Masculino', 'A', 13),
(11, '2003-11-18', 'Masculino', 'A', 14),
(12, '2010-12-19', 'Femenino', 'A', 16),
(13, '2001-03-04', 'Masculino', 'A', 17),
(14, '2019-01-14', 'Masculino', 'A', 18),
(15, '2000-08-06', 'Masculino', 'A', 19),
(16, '2012-04-12', 'Femenino', 'A', 22),
(17, '2009-08-05', 'Masculino', 'A', 29),
(18, '2007-09-21', 'Femenino', 'A', 29),
(19, '2008-01-30', 'Femenino', 'A', 30),
(20, '2013-01-22', 'Masculino', 'A', 29),
(21, '2000-07-26', 'Masculino', 'A', 25),
(22, '2008-09-30', 'Femenino', 'A', 25),
(23, '2012-02-16', 'Masculino', 'A', 27);

insert into telefono values 
(1, '04142723355', 'A', 1),
(2, '04147784839', 'A', 2),
(3, '04168990909', 'A', 3),
(4, '04241123213', 'A', 4),
(5, '04128776232', 'A', 5),
(6, '04264475869', 'A', 6),
(7, '04167334409', 'A', 7),
(8, '04141125643', 'A', 8),
(9, '04129908776', 'A', 9),
(10, '04123312121', 'A', 10),
(11, '04268821314', 'A', 11),
(12, '04165554677', 'A', 12),
(13, '04149883421', 'A', 13),
(14, '04129453059', 'A', 14),
(15, '04161992837', 'A', 15),
(16, '04129918274', 'A', 16),
(17, '04169364777', 'A', 17),
(18, '04141126641', 'A', 18),
(19, '04149880078', 'A', 19),
(20, '04121132312', 'A', 20),
(21, '04265774848', 'A', 21),
(22, '04129882910', 'A', 22),
(23, '04169198827', 'A', 23),
(24, '04124214132', 'A', 24),
(25, '04129870757', 'A', 25),
(26, '04148554738', 'A', 26),
(27, '04168114242', 'A', 27),
(28, '04169342211', 'A', 28),
(29, '04121131415', 'A', 29),
(30, '04165224252', 'A', 30),
(31, '04161117879', 'A', 31),
(32, '04126669099', 'A', 32);

insert into region_estudio values 
(1, 'A', 174, 1),
(2, 'A', 175, 1),
(3, 'A', 176, 1),
(4, 'A', 190, 2),
(5, 'A', 191, 2),
(6, 'A', 192, 2),
(7, 'A', 193, 2),
(8, 'A', 200, 3),
(9, 'A', 201, 3),
(10, 'A', 202, 3),
(11, 'A', 203, 3),
(12, 'A', 182, 4),
(13, 'A', 183, 4),
(14, 'A', 184, 4),
(15, 'A', 185, 4),
(16, 'A', 300, 5),
(17, 'A', 301, 5),
(18, 'A', 302, 5),
(19, 'A', 303, 5),
(20, 'A', 225, 6),
(21, 'A', 226, 6),
(22, 'A', 227, 6),
(23, 'A', 228, 6),
(24, 'A', 174, 7),
(25, 'A', 175, 7),
(26, 'A', 176, 7),
(27, 'A', 290, 8),
(28, 'A', 291, 8),
(29, 'A', 292, 8),
(30, 'A', 293, 8);

insert into pregunta_encuesta values
(1, "Si un software tiene versión de pago y gratuita, ¿Suele escoger la de pago?", "Verdadero o Falso", 'A', 1, 3),
(2, "¿Ha cambiado de procesador en los últimos 5 años?", "Verdadero o Falso", 'A', 2, 3),
(3, "¿Posee calzados deportivos?", "Verdadero o Falso", 'A', 21, 3),
(4, "¿Posee ropa de invieron en su armario?", "Verdadero o Falso", 'A', 23, 3),
(5, "¿Tiene activadas las actualizaciones automáticas de su sistema operativo?", "Verdadero o Falso", 'A', 1, 3),
(6, "¿Prefiere periféricos inalámbricos?", "Verdadero o Falso", 'A', 2, 3),
(7, "Describa su calzado ideal", "Abierta", 'A', 21, 3),
(8, "¿Cuál ha sido el mejor regalo que le han hecho relacionado con ropa?", "Abierta", 'A', 23, 3),
(9, "Describa el proceso que suele seguir para encontrar un software que necesita", "Abierta", 'A', 1, 3),
(10, "Liste los componentes que posee su computador deseado", "Abierta", 'A', 2, 3),
(11, "Explique qué le gusta más, el calzado formal o el casual, y por qué", "Abierta", 'A', 21, 3),
(12, "¿Suele ir en busca de rebajas a la hora de comprar ropa? Explique por qué lo hace o no lo hace", "Abierta", 'A', 23, 3),
(13, "¿Cuál de estas marcas le gusta más?", "Seleccion Simple", 'A', 1, 3),
(14, "¿De que marca es el procesador de su equipo personal?", "Seleccion Simple", 'A', 2, 3),
(15, "¿Cuántos pares de zapatos posee?", "Seleccion Simple", 'A', 21, 3),
(16, "¿Qué material de ropa le parece más cómodo?", "Seleccion Simple", 'A', 23, 3),
(17, "¿Cuánto está dispuesto a gastar en una licencia para un programa que necesita?", "Seleccion Simple", 'A', 1, 3),
(18, "¿Cómo suele armar sus computadoras a la hora de comprarlas?", "Seleccion Simple", 'A', 2, 3),
(19, "¿Cuáles colores le gustan más para un calzado deportivo?", "Seleccion Multiple", 'A', 21, 3),
(20, "¿Cuáles de los siguientes tipos de prenda suele usar?", "Seleccion Multiple", 'A', 23, 3),
(21, "¿Cuáles de los siguientes navegadores tiene instalados en su equipo personal?", "Seleccion Multiple", 'A', 1, 3),
(22, "¿Cuáles de las siguientes características le parecen más importantes de un procesador?", "Seleccion Multiple", 'A', 2, 3),
(23, "¿Cuáles características le parecen más importantes a la hora de comprar un calzado?", "Seleccion Multiple", 'A', 21, 3),
(24, "¿Cuáles de las siguientes características le parecen más importantes a la hora de escoger una prenda de vestir?", "Seleccion Multiple", 'A', 23, 3),
(25, "¿Qué tan seguro se siente con su antivirus actual?", "Escala", 'A', 1, 3),
(26, "¿Cómo calificaría la potencia de su computador personal?", "Escala", 'A', 2, 3),
(27, "¿Qué tan cómodo le parece su par de zapatos favorito?", "Escala", 'A', 21, 3),
(28, "¿Qué tanto le gustan las prendas de cuero?", "Escala", 'A', 23, 3),
(29, "¿Qué tan importante es para usted que sus softwares reciban actualizaciones constantemente?", "Escala", 'A', 1, 3),
(30, "¿Cuánta importancia considera que le da a la velocidad de funcionamiento de sus equipos?", "Escala", 'A', 2, 3);


insert into pregunta_estudio values
(1, "Si un software tiene versión de pago y gratuita, ¿Suele escoger la de pago?",'A', 3, 1),
(2, "¿Ha cambiado de procesador en los últimos 5 años?", 'A', 1, 2),
(3, "¿Posee calzados deportivos?",'A', 4, 3),
(4, "¿Posee ropa de invieron en su armario?",'A', 2, 4),
(5, "¿Tiene activadas las actualizaciones automáticas de su sistema operativo?",'A', 3, 5),
(6, "¿Prefiere periféricos inalámbricos?", 'A', 1, 6),
(7, "Describa su calzado ideal", 'A', 4, 7),
(8, "¿Cuál ha sido el mejor regalo que le han hecho relacionado con ropa?",'A', 2, 8),
(9, "Describa el proceso que suele seguir para encontrar un software que necesita",'A', 3, 9),
(10, "Liste los componentes que posee su computador deseado", 'A', 1, 10),
(11, "Explique qué le gusta más, el calzado formal o el casual, y por qué", 'A', 4, 11),
(12, "¿Suele ir en busca de rebajas a la hora de comprar ropa? Explique por qué lo hace o no lo hace", 'A', 2, 12),
(13, "¿Cuál de estas marcas le gusta más?",'A', 3, 13),
(14, "¿De que marca es el procesador de su equipo personal?",'A', 1, 14),
(15, "¿Cuántos pares de zapatos posee?", 'A', 4, 15),
(16, "¿Qué material de ropa le parece más cómodo?", 'A', 2, 16),
(17, "¿Cuánto está dispuesto a gastar en una licencia para un programa que necesita?", 'A', 3, 17),
(18, "¿Cómo suele armar sus computadoras a la hora de comprarlas?", 'A', 1, 18),
(19, "¿Cuáles colores le gustan más para un calzado deportivo?", 'A', 4, 19),
(20, "¿Cuáles de los siguientes tipos de prenda suele usar?", 'A', 2, 20),
(21, "¿Cuáles de los siguientes navegadores tiene instalados en su equipo personal?",'A', 5, 21),
(22, "¿Cuáles de las siguientes características le parecen más importantes de un procesador?",'A', 1, 22),
(23, "¿Cuáles características le parecen más importantes a la hora de comprar un calzado?",'A', 6, 23),
(24, "¿Cuáles de las siguientes características le parecen más importantes a la hora de escoger una prenda de vestir?",'A', 2, 24),
(25, "¿Qué tan seguro se siente con su antivirus actual?",'A', 5, 25),
(26, "¿Cómo calificaría la potencia de su computador personal?",'A', 1, 26),
(27, "¿Qué tan cómodo le parece su par de zapatos favorito?", 'A', 6, 27),
(28, "¿Qué tanto le gustan las prendas de cuero?",'A', 2, 28),
(29, "¿Qué tan importante es para usted que sus softwares reciban actualizaciones constantemente?", 'A', 5, 29),
(30, "¿Cuánta importancia considera que le da a la velocidad de funcionamiento de sus equipos?",'A', 1, 30),
(31, "Si un software tiene versión de pago y gratuita, ¿Suele escoger la de pago?",'A', 5, 1),
(32, "¿Tiene activadas las actualizaciones automáticas de su sistema operativo?",'A', 5, 5),
(33, "¿Posee calzados deportivos?",'A', 6, 3),
(34, "Describa su calzado ideal", 'A', 6, 7),
(35, "Explique qué le gusta más, el calzado formal o el casual, y por qué", 'A', 6, 11);

insert into respuesta_pregunta values
(1, "Microsoft", 'A', 13),
(2, "Google", 'A', 13),
(3, "Apple", 'A', 13),
(4, "Oracle", 'A', 13),
(5, "AMD", 'A', 14),
(6, "Intel", 'A', 14),
(7, "Qualcomm", 'A', 14),
(8, "Apple", 'A', 14),
(9, "0-1", 'A', 15),
(10, "2-4", 'A', 15),
(11, "5-8", 'A', 15),
(12, "Más de 8", 'A', 15),
(13, "Cuero", 'A', 16),
(14, "Algodón", 'A', 16),
(15, "Jean", 'A', 16),
(16, "Seda", 'A', 16),
(17, "Menos de 10$", 'A', 17),
(18, "10$ - 40$", 'A', 17),
(19, "40$ - 100$", 'A', 17),
(20, "Más de 100$", 'A', 17),
(21, "Por cuenta propia", 'A', 18),
(22, "Pagándole a un experto", 'A', 18),
(23, "Pidiéndoselo a un conocido", 'A', 18),
(24, "Las compro ya armadas", 'A', 18),
(25, "Blanco", 'A', 19),
(26, "Negro", 'A', 19),
(27, "Rojo", 'A', 19),
(28, "Azul", 'A', 19),
(29, "Chaqueta", 'A', 20),
(30, "Franela", 'A', 20),
(31, "Camisa", 'A', 20),
(32, "Blusa", 'A', 20),
(33, "Google Chrome", 'A', 21),
(34, "Mozilla Firefox", 'A', 21),
(35, "Microsoft Edge", 'A', 21),
(36, "Opera", 'A', 21),
(37, "Cantidad de núcleos", 'A', 22),
(38, "Frecuencia máxima", 'A', 22),
(39, "Que se pueda overclockear", 'A', 22),
(40, "Caché", 'A', 22),
(41, "Precio", 'A', 23),
(42, "Comodidad", 'A', 23),
(43, "Aspecto", 'A', 23),
(44, "Materiales", 'A', 23),
(45, "Material de confección", 'A', 24),
(46, "Colores", 'A', 24),
(47, "Comodidad", 'A', 24),
(48, "Precio", 'A', 14);

ALTER TABLE Respuesta MODIFY respuestaAbierta VARCHAR(200);

INSERT into respuesta values  
  (1, "Si un software tiene versión de pago y gratuita, ¿Suele escoger la de pago?", 'A', null, null, null, null, "Verdadero", 11, 1),
  (2, "Si un software tiene versión de pago y gratuita, ¿Suele escoger la de pago?", 'A', null, null, null, null, "Verdadero", 12, 1),
  (3, "¿Ha cambiado de procesador en los últimos 5 años?", 'A', null, null, null, null, "Falso", 9, 2),
  (4, "¿Ha cambiado de procesador en los últimos 5 años?", 'A', null, null, null, null, "Verdadero", 10, 2),
  (5, "¿Ha cambiado de procesador en los últimos 5 años?", 'A', null, null, null, null, "Falso", 13, 2),
  (6, "¿Ha cambiado de procesador en los últimos 5 años?", 'A', null, null, null, null, "Verdadero", 14, 2),
  (7, "¿Posee calzados deportivos?", 'A', null, null, null, null, "Verdadero", 19, 3),
  (8, "¿Posee calzados deportivos?", 'A', null, null, null, null, "Falso", 20, 3),
  (9, "¿Posee calzados deportivos?", 'A', null, null, null, null, "Verdadero", 25, 3),
  (10, "¿Posee ropa de invieron en su armario?", 'A', null, null, null, null, "Falso", 17, 4),
  (11, "¿Posee ropa de invieron en su armario?", 'A', null, null, null, null, "Falso", 18, 4),
  (12, "¿Posee ropa de invieron en su armario?", 'A', null, null, null, null, "Verdadero", 21, 4),
  (13, "¿Posee ropa de invieron en su armario?", 'A', null, null, null, null, "Falso", 22, 4),
  (14, "¿Tiene activadas las actualizaciones automáticas de su sistema operativo?", 'A', null, null, null, null, "Falso", 11, 5),
  (15, "¿Tiene activadas las actualizaciones automáticas de su sistema operativo?", 'A', null, null, null, null, "Falso", 12, 5),
  (16, "¿Prefiere periféricos inalámbricos?", 'A', null, null, null, null, "Verdadero", 9, 6),
  (17, "¿Prefiere periféricos inalámbricos?", 'A', null, null, null, null, "Falso", 10, 6),
  (18, "¿Prefiere periféricos inalámbricos?", 'A', null, null, null, null, "Falso", 13, 6),
  (19, "¿Prefiere periféricos inalámbricos?", 'A', null, null, null, null, "Verdadero", 14, 6),
  (20, "Describa su calzado ideal", 'A', null, null, 'Unos zapatos deportivos cómodos y resistentes al deporte extremo', null, null, 19, 7),
  (21, "Describa su calzado ideal", 'A', null, null, 'Unos zapatos formales de color negro que combinen con un traje y corbata', null, null, 20, 7),
  (22, "Describa su calzado ideal", 'A', null, null, 'Me gustan los zapatos con aspecto un poco desgastado y retro', null, null, 25, 7),
  (23, "¿Cuál ha sido el mejor regalo que le han hecho relacionado con ropa?", 'A', null, null, 'Cuando tenía 15 años y me regalaron una bufanda en navidad', null, null, 17, 8),
  (24, "¿Cuál ha sido el mejor regalo que le han hecho relacionado con ropa?", 'A', null, null, 'Una camisa que era de mi abuelo', null, null, 18, 8),
  (25, "¿Cuál ha sido el mejor regalo que le han hecho relacionado con ropa?", 'A', null, null, 'Una chaqueta muy elegante que me regaló mi pareja', null, null, 21, 8),
  (26, "¿Cuál ha sido el mejor regalo que le han hecho relacionado con ropa?", 'A', null, null, 'Una blusa idéntica a una de cuando era niño', null, null, 22, 8),
  (27, "Describa el proceso que suele seguir para encontrar un software que necesita", 'A', null, null, 'Lo descargo del primer resultado que encuentro en Google', null, null, 11, 9),
  (28, "Describa el proceso que suele seguir para encontrar un software que necesita", 'A', null, null, 'Me aseguro de descargarlo de una página segura y lo dejo descargando por la noche', null, null, 12, 9),
  (29, "Liste los componentes que posee su computador deseado", 'A', null, null, 'No me interesan los componentes de mis computadoras', null, null, 9, 10),
  (30, "Liste los componentes que posee su computador deseado", 'A', null, null, 'Mucha memoria RAM y gran espacio de almacenamiento', null, null, 10, 10),
  (31, "Liste los componentes que posee su computador deseado", 'A', null, null, 'Procesador con al menos 4 núcleos y una tarjeta gráfica potente', null, null, 13, 10),
  (32, "Liste los componentes que posee su computador deseado", 'A', null, null, 'Un equipo con procesador intel de 9na generacion y más de 32 GB de RAM', null, null, 14, 10),
  (33, "Explique qué le gusta más, el calzado formal o el casual, y por qué", 'A', null, null, 'El casual porque siempre es más cómodo', null, null, 19, 11),
  (34, "Explique qué le gusta más, el calzado formal o el casual, y por qué", 'A', null, null, 'El formal para trabajar, el casual para pasear', null, null, 20, 11),
  (35, "Explique qué le gusta más, el calzado formal o el casual, y por qué", 'A', null, null, 'Prefiero zapatos deportivos antes que cualquier otro', null, null, 25, 11),
  (36, "¿Suele ir en busca de rebajas a la hora de comprar ropa? Explique por qué lo hace o no lo hace", 'A', null, null, 'No suelo buscar rebajas porque no tengo tiempo', null, null, 17, 12),
  (37, "¿Suele ir en busca de rebajas a la hora de comprar ropa? Explique por qué lo hace o no lo hace", 'A', null, null, 'Todos los fines de semana me dedico a buscar ropa en oferta para ahorrarme lo que pueda', null, null, 18, 12),
  (38, "¿Suele ir en busca de rebajas a la hora de comprar ropa? Explique por qué lo hace o no lo hace", 'A', null, null, 'Suelo comprar en oferta únicamente ropa de temporada si está muy rebajada de precio', null, null, 21, 12),
  (39, "¿Suele ir en busca de rebajas a la hora de comprar ropa? Explique por qué lo hace o no lo hace", 'A', null, null, 'No me interesa mucho comprar ropa, así que no suelo buscar ofertas intensamente', null, null, 22, 12),
  (40, "¿Cuál de estas marcas le gusta más?", 'A', 'Google', null, null, null, null, 11, 13),
  (41, "¿Cuál de estas marcas le gusta más?", 'A', 'Microsoft', null, null, null, null, 12, 13),
  (42, "¿De que marca es el procesador de su equipo personal?", 'A', 'AMD', null, null, null, null, 9, 14),
  (43, "¿De que marca es el procesador de su equipo personal?", 'A', 'Intel', null, null, null, null, 10, 14),
  (44, "¿De que marca es el procesador de su equipo personal?", 'A', 'Intel', null, null, null, null, 13, 14),
  (45, "¿De que marca es el procesador de su equipo personal?", 'A', 'Apple', null, null, null, null, 14, 14),
  (46, "¿Cuántos pares de zapatos posee?", 'A', '0-1', null, null, null, null, 19, 15),
  (47, "¿Cuántos pares de zapatos posee?", 'A', '2-4', null, null, null, null, 20, 15),
  (48, "¿Cuántos pares de zapatos posee?", 'A', '2-4', null, null, null, null, 25, 15),
  (49, "¿Qué material de ropa le parece más cómodo?", 'A', 'Jean', null, null, null, null, 17, 16),
  (50, "¿Qué material de ropa le parece más cómodo?", 'A', 'Algodón', null, null, null, null, 18, 16),
  (51, "¿Qué material de ropa le parece más cómodo?", 'A', 'Seda', null, null, null, null, 21, 16),
  (52, "¿Qué material de ropa le parece más cómodo?", 'A', 'Jean', null, null, null, null, 22, 16),
  (53, "¿Cuánto está dispuesto a gastar en una licencia para un programa que necesita?", 'A', '10$ - 40$', null, null, null, null, 11, 17),
  (54, "¿Cuánto está dispuesto a gastar en una licencia para un programa que necesita?", 'A', '40$ - 100$', null, null, null, null, 12, 17),
  (55, "¿Cómo suele armar sus computadoras a la hora de comprarlas?", 'A', 'Por cuenta propia', null, null, null, null, 9, 18),
  (56, "¿Cómo suele armar sus computadoras a la hora de comprarlas?", 'A', 'Por cuenta propia', null, null, null, null, 10, 18),
  (57, "¿Cómo suele armar sus computadoras a la hora de comprarlas?", 'A', 'Pagándole a un experto', null, null, null, null, 13, 18),
  (58, "¿Cómo suele armar sus computadoras a la hora de comprarlas?", 'A', 'Las compro ya armadas', null, null, null, null, 14, 18),
  (59, "¿Cuáles colores le gustan más para un calzado deportivo?", 'A', null, 'Blanco', null, null, null, 19, 19),
  (60, "¿Cuáles colores le gustan más para un calzado deportivo?", 'A', null, 'Negro', null, null, null, 19, 19),
  (61, "¿Cuáles colores le gustan más para un calzado deportivo?", 'A', null, 'Negro', null, null, null, 20, 19),
  (62, "¿Cuáles colores le gustan más para un calzado deportivo?", 'A', null, 'Azul', null, null, null, 20, 19),
  (63, "¿Cuáles colores le gustan más para un calzado deportivo?", 'A', null, 'Rojo', null, null, null, 20, 19),
  (64, "¿Cuáles colores le gustan más para un calzado deportivo?", 'A', null, 'Blanco', null, null, null, 25, 19),
  (65, "¿Cuáles colores le gustan más para un calzado deportivo?", 'A', null, 'Rojo', null, null, null, 25, 19),
  (66, "¿Cuáles de los siguientes tipos de prenda suele usar?", 'A', null, 'Franela', null, null, null, 17, 20),
  (67, "¿Cuáles de los siguientes tipos de prenda suele usar?", 'A', null, 'Chaqueta', null, null, null, 17, 20),
  (68, "¿Cuáles de los siguientes tipos de prenda suele usar?", 'A', null, 'Camisa', null, null, null, 18, 20),
  (69, "¿Cuáles de los siguientes tipos de prenda suele usar?", 'A', null, 'Chaqueta', null, null, null, 18, 20),
  (70, "¿Cuáles de los siguientes tipos de prenda suele usar?", 'A', null, 'Camisa', null, null, null, 21, 20),
  (71, "¿Cuáles de los siguientes tipos de prenda suele usar?", 'A', null, 'Franela', null, null, null, 21, 20),
  (72, "¿Cuáles de los siguientes tipos de prenda suele usar?", 'A', null, 'Chaqueta', null, null, null, 21, 20),
  (73, "¿Cuáles de los siguientes tipos de prenda suele usar?", 'A', null, 'Blusa', null, null, null, 22, 20),
  (74, "¿Cuáles de los siguientes navegadores tiene instalados en su equipo personal?", 'A', null, 'Google Chrome', null, null, null, 33, 21),
  (75, "¿Cuáles de los siguientes navegadores tiene instalados en su equipo personal?", 'A', null, 'Mozilla Firefox', null, null, null, 33, 21),
  (76, "¿Cuáles de los siguientes navegadores tiene instalados en su equipo personal?", 'A', null, 'Microsoft Edge', null, null, null, 33, 21),
  (77, "¿Cuáles de los siguientes navegadores tiene instalados en su equipo personal?", 'A', null, 'Opera', null, null, null, 36, 21),
  (78, "¿Cuáles de los siguientes navegadores tiene instalados en su equipo personal?", 'A', null, 'Mozilla Firefox', null, null, null, 36, 21),
  (79, "¿Cuáles de las siguientes características le parecen más importantes de un procesador?", 'A', null, 'Cantidad de núcleos', null, null, null, 9, 22),
  (80, "¿Cuáles de las siguientes características le parecen más importantes de un procesador?", 'A', null, 'Frecuencia máxima', null, null, null, 9, 22),
  (81, "¿Cuáles de las siguientes características le parecen más importantes de un procesador?", 'A', null, 'Frecuencia máxima', null, null, null, 10, 22),
  (82, "¿Cuáles de las siguientes características le parecen más importantes de un procesador?", 'A', null, 'Cantidad de núcleos', null, null, null, 10, 22),
  (83, "¿Cuáles de las siguientes características le parecen más importantes de un procesador?", 'A', null, 'Caché', null, null, null, 10, 22),
  (84, "¿Cuáles de las siguientes características le parecen más importantes de un procesador?", 'A', null, 'Cantidad de núcleos', null, null, null, 13, 22),
  (85, "¿Cuáles de las siguientes características le parecen más importantes de un procesador?", 'A', null, 'Caché', null, null, null, 13, 22),
  (86, "¿Cuáles de las siguientes características le parecen más importantes de un procesador?", 'A', null, 'Que se pueda overclockear', null, null, null, 13, 22),
  (87, "¿Cuáles de las siguientes características le parecen más importantes de un procesador?", 'A', null, 'Cantidad de núcleos', null, null, null, 14, 22),
  (88, "¿Cuáles características le parecen más importantes a la hora de comprar un calzado?", 'A', null, 'Precio', null, null, null, 23, 23),
  (89, "¿Cuáles características le parecen más importantes a la hora de comprar un calzado?", 'A', null, 'Comodidad', null, null, null, 23, 23),
  (90, "¿Cuáles características le parecen más importantes a la hora de comprar un calzado?", 'A', null, 'Aspecto', null, null, null, 23, 23),
  (91, "¿Cuáles de las siguientes características le parecen más importantes a la hora de escoger una prenda de vestir?", 'A', null, 'Material de confección', null, null, null, 17, 24),
  (92, "¿Cuáles de las siguientes características le parecen más importantes a la hora de escoger una prenda de vestir?", 'A', null, 'Colores', null, null, null, 18, 24),
  (93, "¿Cuáles de las siguientes características le parecen más importantes a la hora de escoger una prenda de vestir?", 'A', null, 'Material de confección', null, null, null, 18, 24),
  (94, "¿Cuáles de las siguientes características le parecen más importantes a la hora de escoger una prenda de vestir?", 'A', null, 'Comodidad', null, null, null, 21, 24),
  (95, "¿Cuáles de las siguientes características le parecen más importantes a la hora de escoger una prenda de vestir?", 'A', null, 'Precio', null, null, null, 21, 24),
  (96, "¿Cuáles de las siguientes características le parecen más importantes a la hora de escoger una prenda de vestir?", 'A', null, 'Comodidad', null, null, null, 22, 24),
  (97, "¿Cuáles de las siguientes características le parecen más importantes a la hora de escoger una prenda de vestir?", 'A', null, 'Material de confección', null, null, null, 22, 24),
  (98, "¿Qué tan seguro se siente con su antivirus actual?", 'A', null, null, null, '2', null, 33, 25),
  (99, "¿Qué tan seguro se siente con su antivirus actual?", 'A', null, null, null, '4', null, 36, 25),
  (100, "¿Cómo calificaría la potencia de su computador personal?", 'A', null, null, null, '4', null, 9, 26),
  (101, "¿Cómo calificaría la potencia de su computador personal?", 'A', null, null, null, '3', null, 10, 26),
  (102, "¿Cómo calificaría la potencia de su computador personal?", 'A', null, null, null, '2', null, 13, 26),
  (103, "¿Cómo calificaría la potencia de su computador personal?", 'A', null, null, null, '2', null, 14, 26),
  (104, "¿Qué tan cómodo le parece su par de zapatos favorito?", 'A', null, null, null, '5', null, 23, 27),
  (105, "¿Qué tanto le gustan las prendas de cuero?", 'A', null, null, null, '5', null, 17, 28),
  (106, "¿Qué tanto le gustan las prendas de cuero?", 'A', null, null, null, '2', null, 18, 28),
  (107, "¿Qué tanto le gustan las prendas de cuero?", 'A', null, null, null, '5', null, 21, 28),
  (108, "¿Qué tanto le gustan las prendas de cuero?", 'A', null, null, null, '3', null, 22, 28),
  (109, "¿Qué tan importante es para usted que sus softwares reciban actualizaciones constantemente?", 'A', null, null, null, '1', null, 33, 29),
  (110, "¿Qué tan importante es para usted que sus softwares reciban actualizaciones constantemente?", 'A', null, null, null, '4', null, 36, 29),
  (111, "¿Cuánta importancia considera que le da a la velocidad de funcionamiento de sus equipos?", 'A', null, null, null, '1', null, 9, 30),
  (112, "¿Cuánta importancia considera que le da a la velocidad de funcionamiento de sus equipos?", 'A', null, null, null, '3', null, 10, 30),
  (113, "¿Cuánta importancia considera que le da a la velocidad de funcionamiento de sus equipos?", 'A', null, null, null, '5', null, 13, 30),
  (114, "¿Cuánta importancia considera que le da a la velocidad de funcionamiento de sus equipos?", 'A', null, null, null, '5', null, 14, 30),
  (115, "Si un software tiene versión de pago y gratuita, ¿Suele escoger la de pago?", 'A', null, null, null, null, "Verdadero", 9, 31),
  (116, "Si un software tiene versión de pago y gratuita, ¿Suele escoger la de pago?", 'A', null, null, null, null, "Falso", 10, 31),
  (117, "Si un software tiene versión de pago y gratuita, ¿Suele escoger la de pago?", 'A', null, null, null, null, "Falso", 13, 31),
  (118, "Si un software tiene versión de pago y gratuita, ¿Suele escoger la de pago?", 'A', null, null, null, null, "Falso", 14, 31),
  (119, "¿Tiene activadas las actualizaciones automáticas de su sistema operativo?", 'A', null, null, null, null, "Falso", 33, 32),
  (120, "¿Tiene activadas las actualizaciones automáticas de su sistema operativo?", 'A', null, null, null, null, "Verdadero", 36, 32),
  (121, "¿Posee calzados deportivos?", 'A', null, null, null, null, "Verdadero", 23, 33),
  (122, "Describa su calzado ideal", 'A', null, null, 'Unos zapatos de múltiples colores y cordones muy extensos', null, null, 23, 34),
  (123, "Explique qué le gusta más, el calzado formal o el casual, y por qué", 'A', null, null, 'El calzado casual, ya que el formal me parece que casi siempre resulta más incómodo', null, null, 23, 35);


INSERT into Presentacion values
  (1, "Ninguna", "Ninguna", 'A'), 
  (2, "100 gramos", "100 gramos", 'A'),
  (3, "200 gramos", "200 gramos", 'A'),
  (4, "300 gramos", "300 gramos", 'A'),
  (5, "400 gramos", "400 gramos", 'A'),
  (6, "500 gramos", "500 gramos", 'A'),
  (7, "600 gramos", "600 gramos", 'A'),
  (8, "700 gramos", "700 gramos", 'A'),
  (9, "800 gramos", "800 gramos", 'A'),
  (10, "900 gramos", "900 gramos", 'A'),
  (11, "1 kilo", "1 kilo", 'A'),
  (12, "2 kilos", "2 kilos", 'A'),
  (13, "5 kilos", "5 kilos", 'A'),
  (14, "10 kilos", "10 kilos", 'A'),
  (15, "25 kilos", "25 kilos", 'A'),
  (16, "100 ml", "100 ml", 'A'),
  (17, "200 ml", "200 ml", 'A'),
  (18, "300 ml", "300 ml", 'A'),
  (19, "400 ml", "400 ml", 'A'),
  (20, "500 ml", "500 ml", 'A'),
  (21, "600 ml", "600 ml", 'A'),
  (22, "700 ml", "700 ml", 'A'),
  (23, "800 ml", "800 ml", 'A'),
  (24, "900 ml", "900 ml", 'A'),
  (25, "1 litro", "1 litro", 'A'),
  (26, "2 litros", "2 litros", 'A'),
  (27, "5 litros", "5 litros", 'A'),
  (28, "10 litros", "10 litros", 'A');

INSERT into Tipo values
  (1, "Ninguno", "Ninguno", 'A'), 
  (2, "Caja de cartón", "Caja de cartón", 'A'),
  (3, "Caja plástica", "Caja plástica", 'A'),
  (4, "Barra", "Barra", 'A'),
  (5, "Aerosol", "Aerosol", 'A'),
  (6, "Bolsa de papel", "Bolsa de papel", 'A'),
  (7, "Envase plástico", "Envase plástico", 'A'),
  (8, "Envase de aluminio", "Envase de aluminio", 'A'),
  (9, "Envase de vidrio", "Envase de vidrio", 'A'),
  (10, "Envase de madera", "Envase de madera", 'A'),
  (11, "Caja de madera", "Caja de madera", 'A'),
  (12, "Botella plástica", "Botella plástica", 'A'),
  (13, "Botella de vidrio", "Botella de vidrio", 'A');

insert into producto_presentacion_tipo values
  (1, 'A', 1, 1, 1),
  (2, 'A', 2, 3, 2),
  (3, 'A', 3, 7, 2),
  (4, 'A', 4, 2, 6),
  (5, 'A', 5, 6, 6),
  (6, 'A', 6, 3, 9),
  (7, 'A', 7, 6, 8),
  (8, 'A', 8, 5, 3),
  (9, 'A', 9, 1, 1),
  (10, 'A', 10, 1, 1),
  (11, 'A', 11, 8, 2),
  (12, 'A', 12, 4, 7);
  
  insert into poblacion values (1, 'A', 9, 1);
insert into poblacion values (2, 'A', 10, 1);
insert into poblacion values (3, 'A', 13, 1);
insert into poblacion values (4, 'A', 14, 1);
insert into poblacion values (5, 'A', 17, 2);
insert into poblacion values (6, 'A', 18, 2);
insert into poblacion values (7, 'A', 21, 2);
insert into poblacion values (8, 'A', 22, 2);
insert into poblacion values (9, 'A', 11, 3);
insert into poblacion values (10, 'A', 12, 3);
insert into poblacion values (11, 'A', 15, 3);
insert into poblacion values (12, 'A', 16, 3);
insert into poblacion values (13, 'A', 19, 4);
insert into poblacion values (14, 'A', 20, 4);
insert into poblacion values (15, 'A', 25, 4);
insert into poblacion values (16, 'A', 28, 4);
insert into poblacion values (17, 'A', 33, 5);
insert into poblacion values (18, 'A', 36, 5);
insert into poblacion values (19, 'A', 38, 5);
insert into poblacion values (20, 'A', 39, 5);
insert into poblacion values (21, 'A', 40, 5);
insert into poblacion values (22, 'A', 23, 6);
insert into poblacion values (23, 'A', 24, 6);
insert into poblacion values (24, 'A', 26, 6);
insert into poblacion values (25, 'A', 27, 6);
insert into poblacion values (26, 'A', 29, 6);
insert into poblacion values (27, 'A', 18, 3);
insert into poblacion values (28, 'A', 30, 4);
insert into poblacion values (29, 'A', 10, 5);
insert into poblacion values (30, 'A', 16, 6);