import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.Region_estudio;
import ucab.dsw.entidades.Solicitud_estudio;

import java.util.List;

public class Region_estudioORMWS_Test {
    
    @Test
    public void addRegion_estudioTest() throws Exception {
        ucab.dsw.servicio.Region_estudioORMWS servicio = new ucab.dsw.servicio.Region_estudioORMWS();
        Region_estudioDto region_estudioDto = new Region_estudioDto();

        LugarDto lugar = new LugarDto( 1);
        region_estudioDto.setLugarDto( lugar );

        Solicitud_estudioDto solicitud_estudio = new Solicitud_estudioDto( 1);
        region_estudioDto.setSolicitudEstudioDto(solicitud_estudio);

        region_estudioDto.setEstado( "A" );
        Region_estudioDto resultado = servicio.addRegion_estudio( region_estudioDto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void showRegion_estudioTest() throws Exception
    {
        ucab.dsw.servicio.Region_estudioORMWS servicio = new ucab.dsw.servicio.Region_estudioORMWS();
        List<Region_estudio> region_estudios = servicio.showRegion_estudio();
        Assert.assertFalse("Consulta Realizada con Exito",region_estudios.isEmpty());
    }

    @Test
    public void updateRegion_estudioTest() throws Exception{

        ucab.dsw.servicio.Region_estudioORMWS servicio = new ucab.dsw.servicio.Region_estudioORMWS();
        Region_estudioDto region_estudioDto = new Region_estudioDto(1);
        LugarDto lugar = new LugarDto( 1);
        region_estudioDto.setLugarDto( lugar );

        Solicitud_estudioDto solicitud_estudio = new Solicitud_estudioDto( 1);
        region_estudioDto.setSolicitudEstudioDto(solicitud_estudio);
        region_estudioDto.setEstado( "I" );
        Region_estudioDto resultado = servicio.editRegion_estudio (region_estudioDto);
        Assert.assertNotEquals( resultado.getId(), 0);
    }

    @Test
    public void deleteRegion_estudioTest() throws Exception{

        ucab.dsw.servicio.Region_estudioORMWS servicio = new ucab.dsw.servicio.Region_estudioORMWS();
        Region_estudioDto region_estudioDto = new Region_estudioDto(1);
        Region_estudioDto resultado = servicio.deleteRegion_estudio(region_estudioDto);
        Assert.assertNotEquals( resultado.getId(), 0 );

    }
}
