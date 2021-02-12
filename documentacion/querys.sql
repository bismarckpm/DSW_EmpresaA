select pe.codigo as codigo, pe.descripcion as descripcion , pe.tipoPregunta as tipoPregunta, rp.nombre as Nombre
from Pregunta_encuesta as pe, Pregunta_estudio as pt, Respuesta_pregunta as rp where  
pe.codigo = pt.fk_preguntaEncuesta and pe.codigo = rp.fk_preguntaEncuesta and
pt.codigo not in ( ( select r.fk_preguntaEstudio from Respuesta as r where  
r.fk_preguntaEstudio = pt.codigo and pt.fk_estudio = 1 and r.fk_usuario = 1) )
order by pe.codigo;

select pe.codigo as codigo, pe.descripcion as descripcion , pe.tipoPregunta as tipoPregunta, rp.nombre as Nombre
from Pregunta_encuesta as pe, Pregunta_estudio as pt, Respuesta_pregunta as rp where  
pe.codigo = pt.fk_preguntaEncuesta and pe.codigo = rp.fk_preguntaEncuesta
order by pe.codigo;

select e.codigo as id, e.nombre as nombre, e.estatus as Estatus, e.fechaInicio as fechaInicio
from estudio as e, solicitud_estudio as se
where e.fk_solicitudEstudio = se.codigo and se.conCuantasPersonasVive = 1
and se.disponibilidadEnLinea = "Si" and 15>=se.edadMinimaPoblacion  and 15<=se.edadMaximaPoblacion
and se.fk_nivelEconomico = 3 and se.fk_ocupacion = 7 and se.generoPoblacional = "Masculino" and se.estatus ="En Proceso"
and 14 IN (Select re.fk_lugar from region_estudio as re Where re.fk_solicitudEstudio = se.codigo);