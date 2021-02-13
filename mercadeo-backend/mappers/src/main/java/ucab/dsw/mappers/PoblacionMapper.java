package ucab.dsw.mappers;

import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoPoblacion;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.PoblacionDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.Poblacion;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.excepciones.PruebaExcepcion;

public class PoblacionMapper {

    public static Poblacion mapDtoToEntityInsert(PoblacionDto poblacionDto )throws CustomException
    {
        Poblacion poblacion = new Poblacion();

        DaoUsuario daoUsuario = new DaoUsuario();
        DaoEstudio daoEstudio = new DaoEstudio();

        Usuario usuario = daoUsuario.find(poblacionDto.getUsuario().getId(), Usuario.class);
        Estudio estudio = daoEstudio.find(poblacionDto.getEstudio().getId(), Estudio.class);

        poblacion.set_estado( "A" );
        poblacion.set_usuario( usuario);
        poblacion.set_estudio(estudio);

        return poblacion;
    }

    public static Poblacion mapDtoToEntityUpdate(long _id,PoblacionDto poblacionDto )throws CustomException
    {
        DaoPoblacion daoPoblacion=new DaoPoblacion();

        Poblacion poblacion = daoPoblacion.find(_id,Poblacion.class);

        DaoUsuario daoUsuario = new DaoUsuario();
        DaoEstudio daoEstudio = new DaoEstudio();

        Usuario usuario = daoUsuario.find(poblacionDto.getUsuario().getId(), Usuario.class);
        Estudio estudio = daoEstudio.find(poblacionDto.getEstudio().getId(), Estudio.class);

        poblacion.set_estado( poblacionDto.getEstado() );
        poblacion.set_usuario( usuario);
        poblacion.set_estudio(estudio);

        return poblacion;
    }

    public static PoblacionDto mapEntityToDto(  Poblacion poblacion ) throws CustomException {
        PoblacionDto poblacionDto = new PoblacionDto();
        if (poblacion == null)
            throw new CustomException("004", "La población recibida es nula");
        poblacionDto.setId(poblacion.get_id());
        poblacionDto.setEstado( "A" );

        DaoUsuario daoUsuario = new DaoUsuario();
        DaoEstudio daoEstudio = new DaoEstudio();

        poblacionDto.setUsuario( UsuarioMapper.mapEntityToDto(daoUsuario.find(poblacion.get_usuario().get_id(), Usuario.class)));
        poblacionDto.setEstudio( EstudioMapper.mapEntityToDto(daoEstudio.find(poblacion.get_estudio().get_id(), Estudio.class)));

        return poblacionDto;
    }

    public static Poblacion mapEntityInsert(Estudio estudio, Usuario usuario )throws CustomException
    {
        Poblacion poblacion = new Poblacion();
        if (usuario == null)
            throw new CustomException("004", "El usuario recibido es nulo");
        if (estudio == null)
            throw new CustomException("004", "El estudio recibido es nulo");
        poblacion.set_estado( "A" );
        poblacion.set_usuario( usuario);
        poblacion.set_estudio(estudio);

        return poblacion;
    }

    
}
