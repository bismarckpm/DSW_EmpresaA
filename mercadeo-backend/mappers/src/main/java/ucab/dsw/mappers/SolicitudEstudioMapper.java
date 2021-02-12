package ucab.dsw.mappers;

import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.Solicitud_estudioDto;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.PruebaExcepcion;

import java.util.Date;

public class SolicitudEstudioMapper {

    public static Solicitud_estudio mapDtoToEntityInsert(Solicitud_estudioDto solicitud_estudioDto )
    {
        Solicitud_estudio solicitud_estudio = new Solicitud_estudio();

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
        Ocupacion ocupacion = daoOcu.find(solicitud_estudioDto.getOcupacionDto().getId(), Ocupacion.class);
        solicitud_estudio.set_ocupacion( ocupacion);
        Producto producto = daoProd.find(solicitud_estudioDto.getProductoDto().getId(), Producto.class);
        solicitud_estudio.set_producto( producto);

        return solicitud_estudio;
    }

    public static Solicitud_estudio mapDtoToEntityUpdate(long _id,Solicitud_estudioDto solicitud_estudioDto )
    {
        DaoSolicitud_estudio daoSolicitud_estudio=new DaoSolicitud_estudio();

        Solicitud_estudio solicitud_estudio = daoSolicitud_estudio.find(_id,Solicitud_estudio.class);

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
        Ocupacion ocupacion = daoOcu.find(solicitud_estudioDto.getOcupacionDto().getId(), Ocupacion.class);
        solicitud_estudio.set_ocupacion( ocupacion);
        Producto producto = daoProd.find(solicitud_estudioDto.getProductoDto().getId(), Producto.class);
        solicitud_estudio.set_producto( producto);

        return solicitud_estudio;
    }

    public static Solicitud_estudioDto mapEntityToDto(  Solicitud_estudio solicitud_estudio ) throws PruebaExcepcion {
        Solicitud_estudioDto solicitud_estudioDto = new Solicitud_estudioDto();

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
        solicitud_estudioDto.setOcupacionDto( OcupacionMapper.mapEntityToDto(daoOcu.find(solicitud_estudio.get_ocupacion().get_id(), Ocupacion.class)));
        solicitud_estudioDto.setProductoDto( ProductoMapper.mapEntityToDto(daoProd.find(solicitud_estudio.get_producto().get_id(), Producto.class)));

        return solicitud_estudioDto;
    }
    
}
