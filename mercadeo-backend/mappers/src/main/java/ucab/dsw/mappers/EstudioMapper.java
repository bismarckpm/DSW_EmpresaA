package ucab.dsw.mappers;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoSolicitud_estudio;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.Solicitud_estudio;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.PruebaExcepcion;

import java.util.Date;

public class EstudioMapper {

    public static Estudio mapDtoToEntityInsert(EstudioDto estudioDto )
    {
        Estudio estudio = new Estudio();

        DaoSolicitud_estudio daoSolicitud_estudio = new DaoSolicitud_estudio();
        DaoUsuario daoUsuario = new DaoUsuario();

        estudio.set_nombre( estudioDto.getNombre() );
        estudio.set_fechaInicio( estudioDto.getFechaInicio() );
        estudio.set_fechaFin( estudioDto.getFechaFin() );
        estudio.set_estatus( estudioDto.getEstatus() );
        estudio.set_estado( estudioDto.getEstado() );

        Solicitud_estudio solicitud_estudio = daoSolicitud_estudio.find(estudioDto.getSolicitudEstudioDto().getId(), Solicitud_estudio.class);
        Usuario usuario = daoUsuario.find(estudioDto.getUsuarioDto().getId(), Usuario.class);

        estudio.set_solicitudEstudio( solicitud_estudio);
        estudio.set_usuario( usuario);

        return estudio;
    }

    public static Estudio mapDtoToEntityUpdate(long _id,EstudioDto estudioDto )
    {
        DaoEstudio daoEstudio =new DaoEstudio();

        Estudio estudio = daoEstudio.find(_id,Estudio.class);

        DaoSolicitud_estudio daoSolicitud_estudio = new DaoSolicitud_estudio();
        DaoUsuario daoUsuario = new DaoUsuario();

        estudio.set_nombre( estudioDto.getNombre() );
        estudio.set_fechaInicio( estudioDto.getFechaInicio() );
        estudio.set_fechaFin( estudioDto.getFechaFin() );
        estudio.set_estatus( estudioDto.getEstatus() );
        estudio.set_estado( estudioDto.getEstado() );

        Solicitud_estudio solicitud_estudio = daoSolicitud_estudio.find(estudioDto.getSolicitudEstudioDto().getId(), Solicitud_estudio.class);
        estudio.set_solicitudEstudio( solicitud_estudio);
        Usuario usuario = daoUsuario.find(estudioDto.getUsuarioDto().getId(), Usuario.class);
        estudio.set_usuario( usuario);
        return estudio;
    }

    public static EstudioDto mapEntityToDto(  Estudio estudio ) throws PruebaExcepcion {
        EstudioDto estudioDto = new EstudioDto();

        DaoSolicitud_estudio daoSolicitud_estudio = new DaoSolicitud_estudio();
        DaoUsuario daousuario =new DaoUsuario();

        estudioDto.setId(estudio.get_id());
        estudioDto.setNombre( estudio.get_nombre() );
        estudioDto.setFechaInicio( estudio.get_fechaInicio() );
        estudioDto.setFechaFin( estudio.get_fechaFin() );
        estudioDto.setEstatus( estudio.get_estatus() );
        estudioDto.setEstado( estudio.get_estado() );

        estudioDto.setSolicitudEstudioDto( SolicitudEstudioMapper.mapEntityToDto(daoSolicitud_estudio.find(estudio.get_solicitudEstudio().get_id(), Solicitud_estudio.class)));
        estudioDto.setUsuarioDto( UsuarioMapper.mapEntityToDto(daousuario.find(estudio.get_usuario().get_id(),Usuario.class)));
        return estudioDto;
    }

    public static Estudio mapDtoToEntityInsertRecomendado(EstudioDto estudioDto, long idSolicitud )
    {
        Estudio estudio = new Estudio();

        Date date = new Date();
        DaoSolicitud_estudio daoSolicitud_estudio = new DaoSolicitud_estudio();
        DaoUsuario daoUsuario = new DaoUsuario();

        estudio.set_nombre( estudioDto.getNombre() );
        estudio.set_fechaInicio( date );
        estudio.set_fechaFin( null );
        estudio.set_estatus( "En Espera" );
        estudio.set_estado( "A" );

        Solicitud_estudio solicitud_estudio = daoSolicitud_estudio.find(idSolicitud, Solicitud_estudio.class);
        Usuario usuario = daoUsuario.find(estudioDto.getUsuarioDto().getId(), Usuario.class);

        estudio.set_solicitudEstudio( solicitud_estudio);
        estudio.set_usuario( usuario);

        return estudio;
    }
    
}
