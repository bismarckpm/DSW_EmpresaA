package ucab.dsw.mappers;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoPregunta_encuesta;
import ucab.dsw.accesodatos.DaoRespuesta_pregunta;
import ucab.dsw.dtos.Respuesta_preguntaDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Pregunta_encuesta;
import ucab.dsw.entidades.Respuesta_pregunta;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.excepciones.PruebaExcepcion;

public class RespuestaPreguntaMapper {

    public static Respuesta_pregunta mapDtoToEntityInsert(Respuesta_preguntaDto respuesta_preguntaDto ) throws CustomException
    {
        Respuesta_pregunta respuesta_pregunta = new Respuesta_pregunta();

        DaoPregunta_encuesta daoPregunta_encuesta = new DaoPregunta_encuesta();
        if (respuesta_preguntaDto.getNombre() == null || respuesta_preguntaDto.getNombre().equals(""))
            throw new CustomException("001", "El nombre de la respuesta_pregunta no puede ser nulo ni vacío");
        if(respuesta_preguntaDto.getNombre().length() > 255)
            throw new CustomException("002", "El nombre de la respuesta_pregunta excede el máximo permitido");
        respuesta_pregunta.set_nombre( respuesta_preguntaDto.getNombre() );
        respuesta_pregunta.set_estado( "A" );
        Pregunta_encuesta pregunta_encuesta = daoPregunta_encuesta.find(respuesta_preguntaDto.getPreguntaEncuestaDto().getId(), Pregunta_encuesta.class);
        if (pregunta_encuesta == null)
            throw new CustomException("003","La pregunta_encuesta no existe");
        respuesta_pregunta.set_preguntaEncuesta( pregunta_encuesta);

        return respuesta_pregunta;
    }

    public static Respuesta_pregunta mapDtoToEntityUpdate(long _id,Respuesta_preguntaDto respuesta_preguntaDto ) throws CustomException
    {
        DaoRespuesta_pregunta daoRespuesta_pregunta=new DaoRespuesta_pregunta();

        Respuesta_pregunta respuesta_pregunta = daoRespuesta_pregunta.find(_id,Respuesta_pregunta.class);
        if (respuesta_pregunta == null)
            throw new CustomException("004", "La respuesta_pregunta recibida no existe");
        DaoPregunta_encuesta daoPregunta_encuesta = new DaoPregunta_encuesta();
        if(respuesta_preguntaDto.getNombre().length() > 255)
            throw new CustomException("002", "El nombre de la respuesta_pregunta excede el máximo permitido");
        respuesta_pregunta.set_nombre( respuesta_preguntaDto.getNombre() );
        respuesta_pregunta.set_estado( "A" );
        Pregunta_encuesta pregunta_encuesta = daoPregunta_encuesta.find(respuesta_preguntaDto.getPreguntaEncuestaDto().getId(), Pregunta_encuesta.class);
        if (pregunta_encuesta == null)
            throw new CustomException("003","La pregunta_encuesta no existe");
        respuesta_pregunta.set_preguntaEncuesta( pregunta_encuesta);

        return respuesta_pregunta;
    }

    public static Respuesta_preguntaDto mapEntityToDto(  Respuesta_pregunta respuesta_pregunta ) throws CustomException {
        Respuesta_preguntaDto respuesta_preguntaDto = new Respuesta_preguntaDto();

        DaoPregunta_encuesta daoPregunta_encuesta = new DaoPregunta_encuesta();
        if (respuesta_pregunta == null)
            throw new CustomException("004", "La respuesta_pregunta recibido es nula");
        if (respuesta_pregunta.get_id() == 0 || respuesta_pregunta.get_nombre()=="" ){
            throw new CustomException("001", "Existen atributos inválidos en la respuesta_pregunta");
        }
        respuesta_preguntaDto.setId(respuesta_pregunta.get_id());
        respuesta_preguntaDto.setNombre( respuesta_pregunta.get_nombre() );
        respuesta_preguntaDto.setEstado( "A" );
        respuesta_preguntaDto.setPreguntaEncuestaDto( PreguntaEncuestaMapper.mapEntityToDto(daoPregunta_encuesta.find(respuesta_pregunta.get_preguntaEncuesta().get_id(), Pregunta_encuesta.class)));
        return respuesta_preguntaDto;
    }

}
