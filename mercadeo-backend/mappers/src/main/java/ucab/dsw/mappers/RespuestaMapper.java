package ucab.dsw.mappers;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoPregunta_estudio;
import ucab.dsw.accesodatos.DaoRespuesta;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.RespuestaDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Pregunta_estudio;
import ucab.dsw.entidades.Respuesta;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.CustomException;

import java.util.ArrayList;
import java.util.List;

public class RespuestaMapper {

    public static Respuesta mapDtoToEntityInsert(RespuestaDto respuestaDto )throws CustomException
    {
        DaoPregunta_estudio daoPregunta_estudio = new DaoPregunta_estudio();
        DaoUsuario daoUsuario = new DaoUsuario();
        if (respuestaDto.getPregunta() == null || respuestaDto.getPregunta().equals(""))
            throw new CustomException("001", "La pregunta de la respuesta no puede ser nulo ni vacío");
        if(respuestaDto.getPregunta().length() > 45)
            throw new CustomException("002", "La pregunta de la respuesta excede el máximo permitido");
        Respuesta respuesta = new Respuesta();
        respuesta.set_pregunta(respuestaDto.getPregunta());
        respuesta.set_estado(respuestaDto.getEstado());

        respuesta.set_escala(respuestaDto.getEscala());
        respuesta.set_respuestaAbierta(respuestaDto.getRespuertaAbierta());
        respuesta.set_respuestaMultiple(respuestaDto.getRespuestaMultiple());
        respuesta.set_respuestaSimple(respuestaDto.getRespuestaSimple());
        respuesta.set_verdaderoFalso(respuestaDto.getVerdaderoFalso());

        Pregunta_estudio pregunta_estudio = daoPregunta_estudio.find(respuestaDto.getPreguntaEstudioDto().getId(), Pregunta_estudio.class);
        Usuario usuario = daoUsuario.find(respuestaDto.getUsuarioDto().getId(), Usuario.class);

        respuesta.set_usuario(usuario);
        respuesta.set_preguntaEstudio(pregunta_estudio);

        return respuesta;
    }

    public static List<Respuesta> mapDtoToEntityUpdate(List<RespuestaDto> respuestas )throws CustomException
    {
        DaoRespuesta daoRespuesta=new DaoRespuesta();

        List<Respuesta> respuestas1 = new ArrayList<Respuesta>();
        DaoPregunta_estudio daoPregunta_estudio = new DaoPregunta_estudio();
        DaoUsuario daoUsuario = new DaoUsuario();

        for (RespuestaDto respuestaDto : respuestas) {
            if (respuestaDto.getPregunta() == null || respuestaDto.getPregunta().equals(""))
                throw new CustomException("001", "La pregunta de la respuesta no puede ser nulo ni vacío");
            if(respuestaDto.getPregunta().length() > 45)
                throw new CustomException("002", "La pregunta de la respuesta excede el máximo permitido");
            Respuesta respuesta = daoRespuesta.find(respuestaDto.getId(),Respuesta.class);
            respuesta.set_pregunta(respuestaDto.getPregunta());
            respuesta.set_estado(respuestaDto.getEstado());

            respuesta.set_escala(respuestaDto.getEscala());
            respuesta.set_respuestaAbierta(respuestaDto.getRespuertaAbierta());
            respuesta.set_respuestaMultiple(respuestaDto.getRespuestaMultiple());
            respuesta.set_respuestaSimple(respuestaDto.getRespuestaSimple());
            respuesta.set_verdaderoFalso(respuestaDto.getVerdaderoFalso());

            Pregunta_estudio pregunta_estudio = daoPregunta_estudio.find(respuestaDto.getPreguntaEstudioDto().getId(), Pregunta_estudio.class);
            Usuario usuario = daoUsuario.find(respuestaDto.getUsuarioDto().getId(), Usuario.class);

            respuesta.set_usuario(usuario);
            respuesta.set_preguntaEstudio(pregunta_estudio);

            respuestas1.add(respuesta);
        }

        return respuestas1;
    }

    public static List<RespuestaDto> mapEntityToDto(  List<Respuesta> respuestas ) throws CustomException {

        List<RespuestaDto> respuestas1 = new ArrayList<RespuestaDto>();
        DaoPregunta_estudio daoPregunta_estudio = new DaoPregunta_estudio();
        DaoUsuario daoUsuario = new DaoUsuario();

        for (Respuesta respuesta : respuestas) {
            if (respuesta == null)
                throw new CustomException("004", "La respuesta recibida es nula");
            if (respuesta.get_id() == 0 || respuesta.get_pregunta()=="" ){
                throw new CustomException("001", "Existen atributos inválidos en la respuesta");
            }
            RespuestaDto respuestaDto = new RespuestaDto();
            respuestaDto.setId(respuesta.get_id());
            respuestaDto.setPregunta(respuesta.get_pregunta());
            respuestaDto.setEstado(respuesta.get_estado());

            respuestaDto.setEscala(respuesta.get_escala());
            respuestaDto.setRespuertaAbierta(respuesta.get_respuestaAbierta());
            respuestaDto.setRespuestaMultiple(respuesta.get_respuestaMultiple());
            respuestaDto.setRespuestaSimple(respuesta.get_respuestaSimple());
            respuestaDto.setVerdaderoFalso(respuesta.get_verdaderoFalso());

            respuestaDto.setUsuarioDto(UsuarioMapper.mapEntityToDto(daoUsuario.find(respuesta.get_usuario().get_id(), Usuario.class)));
            respuestaDto.setPreguntaEstudioDto(PreguntaEstudioMapper.mapEntityToDto(daoPregunta_estudio.find(respuesta.get_preguntaEstudio().get_id(), Pregunta_estudio.class)));

            respuestas1.add(respuestaDto);
        }

        return respuestas1;
    }

    public static RespuestaDto mapEntityToDtoSingle(  Respuesta respuesta ) throws CustomException {

        DaoPregunta_estudio daoPregunta_estudio = new DaoPregunta_estudio();
        DaoUsuario daoUsuario = new DaoUsuario();
        if (respuesta == null)
            throw new CustomException("004", "La respuesta recibida es nula");
        if (respuesta.get_id() == 0 || respuesta.get_pregunta()=="" ){
            throw new CustomException("001", "Existen atributos inválidos en la respuesta");
        }
        RespuestaDto respuestaDto = new RespuestaDto();
        respuestaDto.setId(respuesta.get_id());
        respuestaDto.setPregunta(respuesta.get_pregunta());
        respuestaDto.setEstado(respuesta.get_estado());

        respuestaDto.setEscala(respuesta.get_escala());
        respuestaDto.setRespuertaAbierta(respuesta.get_respuestaAbierta());
        respuestaDto.setRespuestaMultiple(respuesta.get_respuestaMultiple());
        respuestaDto.setRespuestaSimple(respuesta.get_respuestaSimple());
        respuestaDto.setVerdaderoFalso(respuesta.get_verdaderoFalso());

        respuestaDto.setUsuarioDto(UsuarioMapper.mapEntityToDto(daoUsuario.find(respuesta.get_usuario().get_id(), Usuario.class)));
        respuestaDto.setPreguntaEstudioDto(PreguntaEstudioMapper.mapEntityToDto(daoPregunta_estudio.find(respuesta.get_preguntaEstudio().get_id(), Pregunta_estudio.class)));

        return respuestaDto;
    }

}
