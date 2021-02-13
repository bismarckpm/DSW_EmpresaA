package ucab.dsw.mappers;

import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.Solicitud_estudioDto;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.excepciones.PruebaExcepcion;

import java.util.Date;

public class SolicitudEstudioMapper {

    public static Solicitud_estudio mapDtoToEntityInsert(Solicitud_estudioDto solicitud_estudioDto )throws CustomException
    {
        Solicitud_estudio solicitud_estudio = new Solicitud_estudio();
        if (solicitud_estudioDto.getDescripcionSolicitud() == null || solicitud_estudioDto.getDescripcionSolicitud().equals(""))
            throw new CustomException("001", "La descripción de la solicitud_estudio no puede ser nulo ni vacío");
        if(solicitud_estudioDto.getDescripcionSolicitud().length() > 300)
            throw new CustomException("002", "La descripción de la solicitud_estudio excede el máximo permitido");
        if (solicitud_estudioDto.getEstatus() == null || solicitud_estudioDto.getEstatus().equals(""))
            throw new CustomException("001", "El estatus de la solicitud_estudio no puede ser nulo ni vacío");
        if(solicitud_estudioDto.getEstatus().length() > 45)
            throw new CustomException("002", "El estadus de la solicitud_estudio excede el máximo permitido");
        DaoNivel_economico daoNivel = new DaoNivel_economico();
        DaoOcupacion daoOcu = new DaoOcupacion();
        DaoUsuario daoUser = new DaoUsuario();
        DaoProducto daoProd = new DaoProducto();
        solicitud_estudio.set_descripcionSolicitud( solicitud_estudioDto.getDescripcionSolicitud() );
        solicitud_estudio.set_generoPoblacional( solicitud_estudioDto.getGeneroPoblacional() );
        solicitud_estudio.set_estatus("Solicitado");
        Date date = new Date();
        solicitud_estudio.set_fechaPeticion( date);
        solicitud_estudio.set_edadMinimaPoblacion( solicitud_estudioDto.getEdadMinimaPoblacion() );
        solicitud_estudio.set_edadMaximaPoblacion( solicitud_estudioDto.getEdadMaximaPoblacion() );
        solicitud_estudio.set_estado( "A" );
        solicitud_estudio.set_conCuantasPersonasVive( solicitud_estudioDto.getConCuantasPersonasVive() );
        solicitud_estudio.set_disponibilidadEnLinea( solicitud_estudioDto.getDisponibilidadEnLinea() );
        Usuario usuario = daoUser.find (solicitud_estudioDto.getUsuarioDto().getId(), Usuario.class);
        solicitud_estudio.set_usuario( usuario);
        Nivel_economico nivel_economico = daoNivel.find(solicitud_estudioDto.getNivelEconomicoDto().getId(), Nivel_economico.class);
        solicitud_estudio.set_nivelEconomico( nivel_economico);

        if (solicitud_estudioDto.getOcupacionDto() != null && solicitud_estudioDto.getOcupacionDto().getId()!=0) {
            Ocupacion ocupacion = daoOcu.find(solicitud_estudioDto.getOcupacionDto().getId(), Ocupacion.class);
            solicitud_estudio.set_ocupacion(ocupacion);
        }

        Producto producto = daoProd.find(solicitud_estudioDto.getProductoDto().getId(), Producto.class);
        solicitud_estudio.set_producto( producto);

        return solicitud_estudio;
    }

    public static Solicitud_estudio mapDtoToEntityUpdate(long _id,Solicitud_estudioDto solicitud_estudioDto )throws CustomException
    {
        DaoSolicitud_estudio daoSolicitud_estudio=new DaoSolicitud_estudio();

        Solicitud_estudio solicitud_estudio = daoSolicitud_estudio.find(_id,Solicitud_estudio.class);
        if (solicitud_estudioDto.getDescripcionSolicitud() == null || solicitud_estudioDto.getDescripcionSolicitud().equals(""))
            throw new CustomException("001", "La descripción de la solicitud_estudio no puede ser nulo ni vacío");
        if(solicitud_estudioDto.getDescripcionSolicitud().length() > 300)
            throw new CustomException("002", "La descripción de la solicitud_estudio excede el máximo permitido");
        if (solicitud_estudioDto.getEstatus() == null || solicitud_estudioDto.getEstatus().equals(""))
            throw new CustomException("001", "El estatus de la solicitud_estudio no puede ser nulo ni vacío");
        if(solicitud_estudioDto.getEstatus().length() > 45)
            throw new CustomException("002", "El estadus de la solicitud_estudio excede el máximo permitido");
        DaoNivel_economico daoNivel = new DaoNivel_economico();
        DaoOcupacion daoOcu = new DaoOcupacion();
        DaoUsuario daoUser = new DaoUsuario();
        DaoProducto daoProd = new DaoProducto();
        solicitud_estudio.set_descripcionSolicitud( solicitud_estudioDto.getDescripcionSolicitud() );
        solicitud_estudio.set_generoPoblacional( solicitud_estudioDto.getGeneroPoblacional() );
        solicitud_estudio.set_estatus("Solicitado");
        Date date = new Date();
        solicitud_estudio.set_fechaPeticion( date);
        solicitud_estudio.set_edadMinimaPoblacion( solicitud_estudioDto.getEdadMinimaPoblacion() );
        solicitud_estudio.set_edadMaximaPoblacion( solicitud_estudioDto.getEdadMaximaPoblacion() );
        solicitud_estudio.set_estado( "A" );
        solicitud_estudio.set_conCuantasPersonasVive( solicitud_estudioDto.getConCuantasPersonasVive() );
        solicitud_estudio.set_disponibilidadEnLinea( solicitud_estudioDto.getDisponibilidadEnLinea() );
        Usuario usuario = daoUser.find (solicitud_estudioDto.getUsuarioDto().getId(), Usuario.class);
        solicitud_estudio.set_usuario( usuario);
        Nivel_economico nivel_economico = daoNivel.find(solicitud_estudioDto.getNivelEconomicoDto().getId(), Nivel_economico.class);
        solicitud_estudio.set_nivelEconomico( nivel_economico);

        if (solicitud_estudioDto.getOcupacionDto() != null) {
            Ocupacion ocupacion = daoOcu.find(solicitud_estudioDto.getOcupacionDto().getId(), Ocupacion.class);
            solicitud_estudio.set_ocupacion(ocupacion);
        }

        Producto producto = daoProd.find(solicitud_estudioDto.getProductoDto().getId(), Producto.class);
        solicitud_estudio.set_producto( producto);

        return solicitud_estudio;
    }

    public static Solicitud_estudioDto mapEntityToDto(  Solicitud_estudio solicitud_estudio ) throws CustomException {
        Solicitud_estudioDto solicitud_estudioDto = new Solicitud_estudioDto();
        if (solicitud_estudio == null)
            throw new CustomException("004", "La solicitud_estudio recibida es nula");
        if (solicitud_estudio.get_id() == 0 || solicitud_estudio.get_descripcionSolicitud()=="" ||
                solicitud_estudio.get_generoPoblacional()==""){
            throw new CustomException("001", "Existen atributos inválidos en la solicitud_estudio");
        }
        DaoNivel_economico daoNivel = new DaoNivel_economico();
        DaoOcupacion daoOcu = new DaoOcupacion();
        DaoUsuario daoUser = new DaoUsuario();
        DaoProducto daoProd = new DaoProducto();

        solicitud_estudioDto.setId(solicitud_estudio.get_id());
        solicitud_estudioDto.setDescripcionSolicitud( solicitud_estudio.get_descripcionSolicitud() );
        solicitud_estudioDto.setGeneroPoblacional( solicitud_estudio.get_generoPoblacional() );
        solicitud_estudioDto.setEstatus("Solicitado");
        Date date = new Date();
        solicitud_estudioDto.setFechaPeticion( date);
        solicitud_estudioDto.setEdadMinimaPoblacion( solicitud_estudio.get_edadMinimaPoblacion() );
        solicitud_estudioDto.setEdadMaximaPoblacion( solicitud_estudio.get_edadMaximaPoblacion() );
        solicitud_estudioDto.setEstado( "A" );
        solicitud_estudioDto.setConCuantasPersonasVive( solicitud_estudio.get_conCuantasPersonasVive() );
        solicitud_estudioDto.setDisponibilidadEnLinea( solicitud_estudio.get_disponibilidadEnLinea() );

        solicitud_estudioDto.setUsuarioDto( UsuarioMapper.mapEntityToDto(daoUser.find (solicitud_estudio.get_usuario().get_id(), Usuario.class)));
        solicitud_estudioDto.setNivelEconomicoDto( NivelEconomicoMapper.mapEntityToDto(daoNivel.find(solicitud_estudio.get_nivelEconomico().get_id(), Nivel_economico.class)));

        if (solicitud_estudio.get_ocupacion() != null) {
            solicitud_estudioDto.setOcupacionDto( OcupacionMapper.mapEntityToDto(daoOcu.find(solicitud_estudio.get_ocupacion().get_id(), Ocupacion.class)));
        }
        solicitud_estudioDto.setProductoDto( ProductoMapper.mapEntityToDto(daoProd.find(solicitud_estudio.get_producto().get_id(), Producto.class)));

        return solicitud_estudioDto;
    }
    
}
