package ucab.dsw.mappers;

import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.Pregunta_encuestaDto;
import ucab.dsw.dtos.Respuesta_preguntaDto;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.CustomException;

import java.util.List;

public class RespuestaPreguntaMapper {

    public static Respuesta_pregunta mapDtoToEntityInsert(Respuesta_preguntaDto respuesta_preguntaDto )throws CustomException
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
        respuesta_pregunta.set_preguntaEncuesta( pregunta_encuesta);

        return respuesta_pregunta;
    }

    public static Respuesta_pregunta mapDtoToEntityUpdate(long _id,Respuesta_preguntaDto respuesta_preguntaDto )throws CustomException
    {
        DaoRespuesta_pregunta daoRespuesta_pregunta=new DaoRespuesta_pregunta();
        if (respuesta_preguntaDto.getNombre() == null || respuesta_preguntaDto.getNombre().equals(""))
            throw new CustomException("001", "El nombre de la respuesta_pregunta no puede ser nulo ni vacío");
        if(respuesta_preguntaDto.getNombre().length() > 255)
            throw new CustomException("002", "El nombre de la respuesta_pregunta excede el máximo permitido");
        Respuesta_pregunta respuesta_pregunta = daoRespuesta_pregunta.find(_id,Respuesta_pregunta.class);

        DaoPregunta_encuesta daoPregunta_encuesta = new DaoPregunta_encuesta();

        respuesta_pregunta.set_nombre( respuesta_preguntaDto.getNombre() );
        respuesta_pregunta.set_estado( "A" );
        Pregunta_encuesta pregunta_encuesta = daoPregunta_encuesta.find(respuesta_preguntaDto.getPreguntaEncuestaDto().getId(), Pregunta_encuesta.class);
        respuesta_pregunta.set_preguntaEncuesta( pregunta_encuesta);

        return respuesta_pregunta;
    }

    public static Respuesta_preguntaDto mapEntityToDto(  Respuesta_pregunta respuesta_pregunta ) throws CustomException {
        Respuesta_preguntaDto respuesta_preguntaDto = new Respuesta_preguntaDto();
        if (respuesta_pregunta == null)
            throw new CustomException("004", "La respuesta_pregunta recibido es nula");
        if (respuesta_pregunta.get_id() == 0 || respuesta_pregunta.get_nombre()=="" ){
            throw new CustomException("001", "Existen atributos inválidos en la respuesta_pregunta");
        }
        DaoPregunta_encuesta daoPregunta_encuesta = new DaoPregunta_encuesta();

        respuesta_preguntaDto.setId(respuesta_pregunta.get_id());
        respuesta_preguntaDto.setNombre( respuesta_pregunta.get_nombre() );
        respuesta_preguntaDto.setEstado( "A" );
        respuesta_preguntaDto.setPreguntaEncuestaDto( PreguntaEncuestaMapper.mapEntityToDto(daoPregunta_encuesta.find(respuesta_pregunta.get_preguntaEncuesta().get_id(), Pregunta_encuesta.class)));
        return respuesta_preguntaDto;
    }

    public static List<Respuesta_pregunta> mapDtoToEntityInsertList(List<Respuesta_preguntaDto> lista, long id )throws CustomException
    {
        DaoPregunta_encuesta daoPregunta_encuesta = new DaoPregunta_encuesta();
        Pregunta_encuesta pregunta_encuesta = daoPregunta_encuesta.find(id, Pregunta_encuesta.class);
        List<Respuesta_pregunta> respuestas = null;
        for (Respuesta_preguntaDto respuesta_preguntaAux : lista) {
            Respuesta_pregunta respuesta_pregunta = new Respuesta_pregunta();
            if (respuesta_preguntaAux.getNombre() == null || respuesta_preguntaAux.getNombre().equals(""))
                throw new CustomException("001", "El nombre de la respuesta_pregunta no puede ser nulo ni vacío");
            if(respuesta_preguntaAux.getNombre().length() > 255)
                throw new CustomException("002", "El nombre de la respuesta_pregunta excede el máximo permitido");
            respuesta_pregunta.set_nombre( respuesta_preguntaAux.getNombre() );
            respuesta_pregunta.set_estado( "A" );
            respuesta_pregunta.set_preguntaEncuesta( pregunta_encuesta);
            respuestas.add(respuesta_pregunta);
        }

        return respuestas;
    }

}
