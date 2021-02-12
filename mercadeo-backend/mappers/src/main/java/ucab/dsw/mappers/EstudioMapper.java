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
import ucab.dsw.excepciones.CustomException;

public class EstudioMapper {

    public static Estudio mapDtoToEntityInsert(EstudioDto estudioDto ) throws CustomException
    {
        Estudio estudio = new Estudio();


        DaoSolicitud_estudio daoSolicitud_estudio = new DaoSolicitud_estudio();
        if (estudioDto.getNombre() == null || estudioDto.getNombre().equals("") )
            throw new CustomException("001", "El nombre del estudio no puede ser nulo ni vacío");
        if (estudioDto.getNombre().length()>45)
            throw new CustomException("002", "El nombre del estudio excede el límite permitido");
        if (estudioDto.getEstatus() == null || estudioDto.getEstatus().equals("") )
            throw new CustomException("001", "El estatus del estudio no puede ser nulo ni vacío");
        if (estudioDto.getEstatus().length()>20)
            throw new CustomException("002", "El estatus del estudio excede el límite permitido");
        estudio.set_nombre( estudioDto.getNombre() );
        estudio.set_fechaInicio( estudioDto.getFechaInicio() );
        estudio.set_fechaFin( estudioDto.getFechaFin() );
        estudio.set_estatus( estudioDto.getEstatus() );
        estudio.set_estado( estudioDto.getEstado() );
        Solicitud_estudio solicitud_estudio = daoSolicitud_estudio.find(estudioDto.getSolicitudEstudioDto().getId(), Solicitud_estudio.class);
        estudio.set_solicitudEstudio( solicitud_estudio);
        Usuario usuario = new Usuario(estudioDto.getUsuarioDto().getId());
        estudio.set_usuario( usuario);

        return estudio;
    }

    public static Estudio mapDtoToEntityUpdate(long _id,EstudioDto estudioDto ) throws CustomException
    {
        DaoEstudio daoEstudio =new DaoEstudio();

        Estudio estudio = daoEstudio.find(_id,Estudio.class);

        DaoSolicitud_estudio daoSolicitud_estudio = new DaoSolicitud_estudio();
        DaoUsuario daoUsuario = new DaoUsuario();
        if (estudioDto.getNombre() == null || estudioDto.getNombre().equals("") )
            throw new CustomException("001", "El nombre del estudio no puede ser nulo ni vacío");
        if (estudioDto.getNombre().length()>45)
            throw new CustomException("002", "El nombre del estudio excede el límite permitido");
        if (estudioDto.getEstatus() == null || estudioDto.getEstatus().equals("") )
            throw new CustomException("001", "El estatus del estudio no puede ser nulo ni vacío");
        if (estudioDto.getEstatus().length()>20)
            throw new CustomException("002", "El estatus del estudio excede el límite permitido");
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

    public static EstudioDto mapEntityToDto(  Estudio estudio ) throws CustomException {
        EstudioDto estudioDto = new EstudioDto();
        if (estudio == null)
            throw new CustomException("004", "El estudio recibido es nulo");
        DaoSolicitud_estudio daoSolicitud_estudio = new DaoSolicitud_estudio();
        DaoUsuario daousuario =new DaoUsuario();
        if (estudioDto.getNombre() == "" || estudioDto.getEstatus()=="" )
            throw new CustomException("001", "Existen datos inválidos en el estudio");
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
    
}
