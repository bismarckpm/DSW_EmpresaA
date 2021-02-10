package ucab.dsw.mappers;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoPregunta_encuesta;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.Pregunta_encuestaDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Pregunta_encuesta;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.excepciones.PruebaExcepcion;

public class PreguntaEncuestaMapper {

    public static Pregunta_encuesta mapDtoToEntityInsert(Pregunta_encuestaDto pregunta_encuestaDto ) throws CustomException
    {
        Pregunta_encuesta pregunta_encuesta = new Pregunta_encuesta();

        DaoUsuario daoUser = new DaoUsuario();
        DaoSubcategoria daoSub = new DaoSubcategoria();
        pregunta_encuesta.set_descripcion( pregunta_encuestaDto.getDescripcion() );
        pregunta_encuesta.set_tipoPregunta( pregunta_encuestaDto.getTipoPregunta() );
        pregunta_encuesta.set_estado( "A" );
        Usuario usuario = daoUser.find (pregunta_encuestaDto.getUsuarioDto().getId(), Usuario.class);
        pregunta_encuesta.set_usuario( usuario);
        Subcategoria subcategoria = daoSub.find(pregunta_encuestaDto.getSubcategoriaDto().getId(), Subcategoria.class);
        pregunta_encuesta.set_subcategoria( subcategoria);

        return pregunta_encuesta;
    }

    public static Pregunta_encuesta mapDtoToEntityUpdate(long _id,Pregunta_encuestaDto pregunta_encuestaDto ) throws CustomException
    {
        DaoPregunta_encuesta daoPregunta_encuesta=new DaoPregunta_encuesta();

        Pregunta_encuesta pregunta_encuesta = daoPregunta_encuesta.find(_id,Pregunta_encuesta.class);

        DaoUsuario daoUser = new DaoUsuario();
        DaoSubcategoria daoSub = new DaoSubcategoria();
        pregunta_encuesta.set_descripcion( pregunta_encuestaDto.getDescripcion() );
        pregunta_encuesta.set_tipoPregunta( pregunta_encuestaDto.getTipoPregunta() );
        pregunta_encuesta.set_estado( "A" );
        Usuario usuario = daoUser.find (pregunta_encuestaDto.getUsuarioDto().getId(), Usuario.class);
        pregunta_encuesta.set_usuario( usuario);
        Subcategoria subcategoria = daoSub.find(pregunta_encuestaDto.getSubcategoriaDto().getId(), Subcategoria.class);
        pregunta_encuesta.set_subcategoria( subcategoria);

        return pregunta_encuesta;
    }

    public static Pregunta_encuestaDto mapEntityToDto(  Pregunta_encuesta pregunta_encuesta ) throws CustomException {
        Pregunta_encuestaDto pregunta_encuestaDto = new Pregunta_encuestaDto();

        DaoCategoria dao = new DaoCategoria();
        DaoUsuario daoUser = new DaoUsuario();
        DaoSubcategoria daoSub = new DaoSubcategoria();

        pregunta_encuestaDto.setId(pregunta_encuesta.get_id());
        pregunta_encuestaDto.setDescripcion( pregunta_encuesta.get_descripcion() );
        pregunta_encuestaDto.setTipoPregunta( pregunta_encuesta.get_tipoPregunta() );
        pregunta_encuestaDto.setEstado( "A" );
        pregunta_encuestaDto.setUsuarioDto( UsuarioMapper.mapEntityToDto(daoUser.find (pregunta_encuesta.get_usuario().get_id(), Usuario.class)));
        pregunta_encuestaDto.setSubcategoriaDto( SubcategoriaMapper.mapEntityToDto(daoSub.find(pregunta_encuesta.get_subcategoria().get_id(), Subcategoria.class)));

        return pregunta_encuestaDto;
    }
}
