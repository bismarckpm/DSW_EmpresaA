package ucab.dsw.mappers;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoPregunta_encuesta;
import ucab.dsw.accesodatos.DaoPregunta_estudio;
import ucab.dsw.dtos.Pregunta_estudioDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.Pregunta_encuesta;
import ucab.dsw.entidades.Pregunta_estudio;
import ucab.dsw.excepciones.CustomException;

public class PreguntaEstudioMapper {

    public static Pregunta_estudio mapDtoToEntityInsert(Pregunta_estudioDto pregunta_estudioDto )throws CustomException
    {
        Pregunta_estudio pregunta_estudio = new Pregunta_estudio();
        if (pregunta_estudioDto.getPregunta() == null || pregunta_estudioDto.getPregunta().equals(""))
            throw new CustomException("001", "La descripción de la pregunta_estudio no puede ser nulo ni vacío");
        if(pregunta_estudioDto.getPregunta().length() > 255)
            throw new CustomException("002", "La descripción de la pregunta_estudio excede el máximo permitido");
        DaoEstudio daoEstudio = new DaoEstudio();
        DaoPregunta_encuesta daoPregunta_encuesta = new DaoPregunta_encuesta();

        pregunta_estudio.set_estado( pregunta_estudioDto.getEstado() );
        pregunta_estudio.set_pregunta(pregunta_estudioDto.getPregunta());
        Estudio estudio = daoEstudio.find(pregunta_estudioDto.getEstudioDto().getId(), Estudio.class);
        pregunta_estudio.set_estudio( estudio);
        Pregunta_encuesta pregunta_encuesta = daoPregunta_encuesta.find(pregunta_estudioDto.getPreguntaEncuestaDto().getId(), Pregunta_encuesta.class);
        pregunta_estudio.set_preguntaEncuesta( pregunta_encuesta);

        return pregunta_estudio;
    }

    public static Pregunta_estudio mapDtoToEntityUpdate(long _id,Pregunta_estudioDto pregunta_estudioDto )throws CustomException
    {
        DaoPregunta_estudio daoPregunta_estudio=new DaoPregunta_estudio();
        if (pregunta_estudioDto.getPregunta() == null || pregunta_estudioDto.getPregunta().equals(""))
            throw new CustomException("001", "La descripción de la pregunta_estudio no puede ser nulo ni vacío");
        if(pregunta_estudioDto.getPregunta().length() > 255)
            throw new CustomException("002", "La descripción de la pregunta_estudio excede el máximo permitido");
        Pregunta_estudio pregunta_estudio = daoPregunta_estudio.find(_id,Pregunta_estudio.class);

        DaoEstudio daoEstudio = new DaoEstudio();
        DaoPregunta_encuesta daoPregunta_encuesta = new DaoPregunta_encuesta();

        pregunta_estudio.set_estado( pregunta_estudioDto.getEstado() );
        pregunta_estudio.set_pregunta(pregunta_estudioDto.getPregunta());
        Estudio estudio = daoEstudio.find(pregunta_estudioDto.getEstudioDto().getId(), Estudio.class);
        pregunta_estudio.set_estudio( estudio);
        Pregunta_encuesta pregunta_encuesta = daoPregunta_encuesta.find(pregunta_estudioDto.getPreguntaEncuestaDto().getId(), Pregunta_encuesta.class);
        pregunta_estudio.set_preguntaEncuesta( pregunta_encuesta);

        return pregunta_estudio;
    }

    public static Pregunta_estudioDto mapEntityToDto(  Pregunta_estudio pregunta_estudio ) throws CustomException{
        Pregunta_estudioDto pregunta_estudioDto = new Pregunta_estudioDto();
        if (pregunta_estudio == null)
            throw new CustomException("004", "La pregunta_estudio recibida es nula");
        DaoEstudio daoEstudio = new DaoEstudio();
        DaoPregunta_encuesta daoPregunta_encuesta = new DaoPregunta_encuesta();
        if (pregunta_estudio.get_id() == 0 || pregunta_estudio.get_pregunta()=="" ){
            throw new CustomException("001", "Existen atributos inválidos en la pregunta_estudio");
        }
        pregunta_estudioDto.setId(pregunta_estudio.get_id());
        pregunta_estudioDto.setEstado( pregunta_estudio.get_estado() );
        pregunta_estudioDto.setPregunta(pregunta_estudio.get_pregunta());
        pregunta_estudioDto.setEstudioDto( EstudioMapper.mapEntityToDto(daoEstudio.find(pregunta_estudio.get_estudio().get_id(), Estudio.class)));
        pregunta_estudioDto.setPreguntaEncuestaDto( PreguntaEncuestaMapper.mapEntityToDto(daoPregunta_encuesta.find(pregunta_estudio.get_preguntaEncuesta().get_id(), Pregunta_encuesta.class)));

        return pregunta_estudioDto;
    }

    public static Pregunta_estudio mapDtoToEntityInsertRecomendado(Pregunta_estudio pregunta_estudioR, Estudio estudio )throws CustomException
    {
        Pregunta_estudio pregunta_estudio = new Pregunta_estudio();
        if (pregunta_estudioR == null)
            throw new CustomException("004", "La pregunta_estudio recibida es nula");
        if (estudio == null)
            throw new CustomException("004", "El estudio recibido es nulo");
        pregunta_estudio.set_estado( "A" );
        pregunta_estudio.set_pregunta(pregunta_estudioR.get_pregunta());
        pregunta_estudio.set_estudio( estudio);
        pregunta_estudio.set_preguntaEncuesta( pregunta_estudioR.get_preguntaEncuesta());

        return pregunta_estudio;
    }

    public static Pregunta_estudio mapDtoToEntityInsertLista(Pregunta_encuesta pregunta_encuestaAux, Estudio estudio )throws CustomException
    {
        Pregunta_estudio pregunta_estudio = new Pregunta_estudio();
        pregunta_estudio.set_pregunta( pregunta_encuestaAux.get_descripcion() );
        pregunta_estudio.set_estado( "A" );
        DaoPregunta_encuesta daoPregunta_encuesta = new DaoPregunta_encuesta();
        Pregunta_encuesta pregAux = daoPregunta_encuesta.find(pregunta_encuestaAux.get_id(), Pregunta_encuesta.class);
        pregunta_estudio.set_preguntaEncuesta( pregAux);
        pregunta_estudio.set_estudio(estudio);

        return pregunta_estudio;
    }

}
