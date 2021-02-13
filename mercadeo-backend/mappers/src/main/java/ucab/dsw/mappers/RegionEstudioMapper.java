package ucab.dsw.mappers;

import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.Region_estudioDto;
import ucab.dsw.dtos.TelefonoDto;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.PruebaExcepcion;

import java.util.ArrayList;
import java.util.List;

public class RegionEstudioMapper {

    public static Region_estudio mapDtoToEntityInsert(Region_estudioDto region_estudioDto )
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

    public static Region_estudio mapDtoToEntityUpdate(long _id,Region_estudioDto region_estudioDto )
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

    public static Region_estudioDto mapEntityToDto(  Region_estudio region_estudio ) throws PruebaExcepcion {
        Region_estudioDto region_estudioDto = new Region_estudioDto();

        DaoLugar daoLugar = new DaoLugar();
        DaoSolicitud_estudio daoSolicitud_estudio = new DaoSolicitud_estudio();

        region_estudioDto.setId(region_estudio.get_id());
        region_estudioDto.setEstado( "A" );

        region_estudioDto.setLugarDto( LugarMapper.mapEntityToDto(daoLugar.find(region_estudio.get_lugar().get_id(), Lugar.class)));
        region_estudioDto.setSolicitudEstudioDto( SolicitudEstudioMapper.mapEntityToDto(daoSolicitud_estudio.find(region_estudio.get_solicitudEstudio().get_id(), Solicitud_estudio.class)));

        return region_estudioDto;
    }

    public static List<Region_estudio> mapDtoToEntityInsertList(List<Region_estudioDto> lista, long id )
    {
        List<Region_estudio> result = new ArrayList<Region_estudio>();
        DaoLugar daoLugar = new DaoLugar();
        DaoSolicitud_estudio daoSolicitud_estudio = new DaoSolicitud_estudio();
        Solicitud_estudio solicitud_estudio = daoSolicitud_estudio.find(id, Solicitud_estudio.class);
        for (Region_estudioDto region_estudioDto : lista) {
            Region_estudio region_estudio = new Region_estudio();

            Lugar lugar = daoLugar.find(region_estudioDto.getLugarDto().getId(), Lugar.class);

            region_estudio.set_estado( "A" );
            region_estudio.set_lugar( lugar);
            region_estudio.set_solicitudEstudio( solicitud_estudio);
            result.add(region_estudio);
        }
        return result;
    }

    public static List<Region_estudio> mapDtoToEntityUpdateList(List<Region_estudioDto> lista, long id)
    {
        List<Region_estudio> result = new ArrayList<Region_estudio>();
        DaoLugar daoLugar = new DaoLugar();
        DaoSolicitud_estudio daoSolicitud_estudio = new DaoSolicitud_estudio();
        Solicitud_estudio solicitud_estudio = daoSolicitud_estudio.find(id, Solicitud_estudio.class);
        for (Region_estudioDto region_estudioDto : lista) {
            Region_estudio region_estudio = new Region_estudio();
            Lugar lugar = daoLugar.find(region_estudioDto.getLugarDto().getId(), Lugar.class);

            region_estudio.set_estado( "A" );
            region_estudio.set_lugar( lugar);
            region_estudio.set_solicitudEstudio( solicitud_estudio);
            result.add(region_estudio);
        }

        return result;
    }

}
