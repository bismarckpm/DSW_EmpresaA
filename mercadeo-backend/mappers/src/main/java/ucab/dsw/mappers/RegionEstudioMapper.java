package ucab.dsw.mappers;

import ucab.dsw.accesodatos.DaoLugar;
import ucab.dsw.accesodatos.DaoRegion_estudio;
import ucab.dsw.accesodatos.DaoSolicitud_estudio;
import ucab.dsw.dtos.Region_estudioDto;
import ucab.dsw.entidades.Lugar;
import ucab.dsw.entidades.Region_estudio;
import ucab.dsw.entidades.Solicitud_estudio;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.excepciones.PruebaExcepcion;

public class RegionEstudioMapper {

    public static Region_estudio mapDtoToEntityInsert(Region_estudioDto region_estudioDto ) throws CustomException
    {
        Region_estudio region_estudio = new Region_estudio();

        DaoLugar daoLugar = new DaoLugar();
        DaoSolicitud_estudio daoSolicitud_estudio = new DaoSolicitud_estudio();

        Lugar lugar = daoLugar.find(region_estudioDto.getLugarDto().getId(), Lugar.class);
        Solicitud_estudio solicitud_estudio = daoSolicitud_estudio.find(region_estudioDto.getSolicitudEstudioDto().getId(), Solicitud_estudio.class);

        region_estudio.set_estado( "A" );

        region_estudio.set_lugar( lugar);
        region_estudio.set_solicitudEstudio( solicitud_estudio);

        return region_estudio;
    }

    public static Region_estudio mapDtoToEntityUpdate(long _id,Region_estudioDto region_estudioDto ) throws CustomException
    {
        DaoRegion_estudio daoRegion_estudio=new DaoRegion_estudio();

        Region_estudio region_estudio = daoRegion_estudio.find(_id,Region_estudio.class);

        DaoLugar daoLugar = new DaoLugar();
        DaoSolicitud_estudio daoSolicitud_estudio = new DaoSolicitud_estudio();

        Lugar lugar = daoLugar.find(region_estudioDto.getLugarDto().getId(), Lugar.class);
        Solicitud_estudio solicitud_estudio = daoSolicitud_estudio.find(region_estudioDto.getSolicitudEstudioDto().getId(), Solicitud_estudio.class);

        region_estudio.set_estado( "A" );

        region_estudio.set_lugar( lugar);
        region_estudio.set_solicitudEstudio( solicitud_estudio);

        return region_estudio;
    }

    public static Region_estudioDto mapEntityToDto(  Region_estudio region_estudio ) throws CustomException {
        Region_estudioDto region_estudioDto = new Region_estudioDto();
        if (region_estudio == null)
            throw new CustomException("004", "La región de estudio recibida es nula");
        DaoLugar daoLugar = new DaoLugar();
        DaoSolicitud_estudio daoSolicitud_estudio = new DaoSolicitud_estudio();

        region_estudioDto.setId(region_estudio.get_id());
        region_estudioDto.setEstado( "A" );

        region_estudioDto.setLugarDto( LugarMapper.mapEntityToDto(daoLugar.find(region_estudio.get_lugar().get_id(), Lugar.class)));
        region_estudioDto.setSolicitudEstudioDto( SolicitudEstudioMapper.mapEntityToDto(daoSolicitud_estudio.find(region_estudio.get_solicitudEstudio().get_id(), Solicitud_estudio.class)));

        return region_estudioDto;
    }

}
