import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.Lugar;
import ucab.dsw.entidades.Region_estudio;
import ucab.dsw.entidades.Solicitud_estudio;

import java.util.ArrayList;
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

    @Test
    public void addLista_regionesTest() throws Exception
    {
        ucab.dsw.servicio.Region_estudioORMWS servicio = new ucab.dsw.servicio.Region_estudioORMWS();
        List<Region_estudioDto> listaLugares = new ArrayList<>();
        Region_estudioDto region_estudioDto = new Region_estudioDto();
        LugarDto lugarDto = new LugarDto(1);
        region_estudioDto.setLugarDto(lugarDto);
        listaLugares.add(region_estudioDto);

        Region_estudioDto region_estudioDto2 = new Region_estudioDto();
        LugarDto lugarDto2 = new LugarDto(2);
        region_estudioDto2.setLugarDto(lugarDto2);
        listaLugares.add(region_estudioDto2);

        Region_estudioDto region_estudioDto3 = new Region_estudioDto();
        LugarDto lugarDto3 = new LugarDto(3);
        region_estudioDto3.setLugarDto(lugarDto3);
        listaLugares.add(region_estudioDto3);

        Solicitud_estudioDto resultado = servicio.addLista_regiones( 1, listaLugares );
        Assert.assertNotNull( resultado);
    }

    @Test
    public void getRegionesDeSolicitudTest() throws Exception{
        ucab.dsw.servicio.Region_estudioORMWS servicio = new ucab.dsw.servicio.Region_estudioORMWS();
        List<Lugar> resultado = servicio.getRegionesDeSolicitud(1);
        Assert.assertNotEquals(resultado, null);
    }

    @Test
    public void updateLista_regionesTest() throws Exception
    {
        ucab.dsw.servicio.Region_estudioORMWS servicio = new ucab.dsw.servicio.Region_estudioORMWS();
        List<Region_estudioDto> listaLugares = new ArrayList<>();
        Region_estudioDto region_estudioDto = new Region_estudioDto();
        LugarDto lugarDto = new LugarDto(4);
        region_estudioDto.setLugarDto(lugarDto);
        listaLugares.add(region_estudioDto);

        Region_estudioDto region_estudioDto2 = new Region_estudioDto();
        LugarDto lugarDto2 = new LugarDto(5);
        region_estudioDto2.setLugarDto(lugarDto2);
        listaLugares.add(region_estudioDto2);

        Region_estudioDto region_estudioDto3 = new Region_estudioDto();
        LugarDto lugarDto3 = new LugarDto(6);
        region_estudioDto3.setLugarDto(lugarDto3);
        listaLugares.add(region_estudioDto3);

        Solicitud_estudioDto resultado = servicio.updateLista_regiones( 1, listaLugares );
        Assert.assertNotNull( resultado);
    }
}
