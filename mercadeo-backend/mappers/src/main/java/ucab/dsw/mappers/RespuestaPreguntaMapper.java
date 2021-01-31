package ucab.dsw.mappers;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoPregunta_encuesta;
import ucab.dsw.accesodatos.DaoRespuesta_pregunta;
import ucab.dsw.dtos.Respuesta_preguntaDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Pregunta_encuesta;
import ucab.dsw.entidades.Respuesta_pregunta;
import ucab.dsw.excepciones.PruebaExcepcion;

public class RespuestaPreguntaMapper {

    public static Respuesta_pregunta mapDtoToEntityInsert(Respuesta_preguntaDto respuesta_preguntaDto )
    {
        Respuesta_pregunta respuesta_pregunta = new Respuesta_pregunta();

        DaoPregunta_encuesta daoPregunta_encuesta = new DaoPregunta_encuesta();

        respuesta_pregunta.set_nombre( respuesta_preguntaDto.getNombre() );
        respuesta_pregunta.set_estado( "A" );
        Pregunta_encuesta pregunta_encuesta = daoPregunta_encuesta.find(respuesta_preguntaDto.getPreguntaEncuestaDto().getId(), Pregunta_encuesta.class);
        respuesta_pregunta.set_preguntaEncuesta( pregunta_encuesta);

        return respuesta_pregunta;
    }

    public static Respuesta_pregunta mapDtoToEntityUpdate(long _id,Respuesta_preguntaDto respuesta_preguntaDto )
    {
        DaoRespuesta_pregunta daoRespuesta_pregunta=new DaoRespuesta_pregunta();

        Respuesta_pregunta respuesta_pregunta = daoRespuesta_pregunta.find(_id,Respuesta_pregunta.class);

        DaoPregunta_encuesta daoPregunta_encuesta = new DaoPregunta_encuesta();

        respuesta_pregunta.set_nombre( respuesta_preguntaDto.getNombre() );
        respuesta_pregunta.set_estado( "A" );
        Pregunta_encuesta pregunta_encuesta = daoPregunta_encuesta.find(respuesta_preguntaDto.getPreguntaEncuestaDto().getId(), Pregunta_encuesta.class);
        respuesta_pregunta.set_preguntaEncuesta( pregunta_encuesta);

        return respuesta_pregunta;
    }

    public static Respuesta_preguntaDto mapEntityToDto(  Respuesta_pregunta respuesta_pregunta ) throws PruebaExcepcion {
        Respuesta_preguntaDto respuesta_preguntaDto = new Respuesta_preguntaDto();

        DaoPregunta_encuesta daoPregunta_encuesta = new DaoPregunta_encuesta();

        respuesta_preguntaDto.setId(respuesta_pregunta.get_id());
        respuesta_preguntaDto.setNombre( respuesta_pregunta.get_nombre() );
        respuesta_preguntaDto.setEstado( "A" );
        respuesta_preguntaDto.setPreguntaEncuestaDto( PreguntaEncuestaMapper.mapEntityToDto(daoPregunta_encuesta.find(respuesta_pregunta.get_preguntaEncuesta().get_id(), Pregunta_encuesta.class)));
        return respuesta_preguntaDto;
    }

}
