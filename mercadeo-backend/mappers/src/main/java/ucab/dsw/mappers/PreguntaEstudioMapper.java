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
import ucab.dsw.excepciones.PruebaExcepcion;

public class PreguntaEstudioMapper {

    public static Pregunta_estudio mapDtoToEntityInsert(Pregunta_estudioDto pregunta_estudioDto )
    {
        Pregunta_estudio pregunta_estudio = new Pregunta_estudio();

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

    public static Pregunta_estudio mapDtoToEntityUpdate(long _id,Pregunta_estudioDto pregunta_estudioDto )
    {
        DaoPregunta_estudio daoPregunta_estudio=new DaoPregunta_estudio();

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

    public static Pregunta_estudioDto mapEntityToDto(  Pregunta_estudio pregunta_estudio ) throws PruebaExcepcion {
        Pregunta_estudioDto pregunta_estudioDto = new Pregunta_estudioDto();

        DaoEstudio daoEstudio = new DaoEstudio();
        DaoPregunta_encuesta daoPregunta_encuesta = new DaoPregunta_encuesta();

        pregunta_estudioDto.setId(pregunta_estudio.get_id());
        pregunta_estudioDto.setEstado( pregunta_estudio.get_estado() );
        pregunta_estudioDto.setPregunta(pregunta_estudio.get_pregunta());
        pregunta_estudioDto.setEstudioDto( EstudioMapper.mapEntityToDto(daoEstudio.find(pregunta_estudio.get_estudio().get_id(), Estudio.class)));
        pregunta_estudioDto.setPreguntaEncuestaDto( PreguntaEncuestaMapper.mapEntityToDto(daoPregunta_encuesta.find(pregunta_estudio.get_preguntaEncuesta().get_id(), Pregunta_encuesta.class)));

        return pregunta_estudioDto;
    }

}
