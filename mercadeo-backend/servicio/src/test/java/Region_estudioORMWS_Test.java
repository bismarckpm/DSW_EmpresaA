import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.*;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class Region_estudioORMWS_Test {
/*
    *//**
     * Este test prueba el registro de una región de estudio para una solicitud de estudio
     *
     */
    @Test
    public void addListaRegion_estudioTest() throws Exception {
        ucab.dsw.servicio.Region_estudioORMWS servicio = new ucab.dsw.servicio.Region_estudioORMWS();
        List<Region_estudioDto> region_estudioList = new ArrayList<>();
        Region_estudioDto region_estudioDto = new Region_estudioDto();
        LugarDto lugar = new LugarDto( 1);
        region_estudioDto.setLugarDto( lugar );

        Solicitud_estudioDto solicitud_estudio = new Solicitud_estudioDto( 1);
        region_estudioDto.setSolicitudEstudioDto(solicitud_estudio);

        region_estudioDto.setEstado( "A" );
        region_estudioList.add(region_estudioDto);
        Response resultado = servicio.addLista_regiones( 1, region_estudioList );
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Region_estudio> region_estudios = (List<Region_estudio>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",region_estudios.isEmpty());
    }

    /**
     * Este test prueba la obtención de todas las regiones de estudio
     *
     */
    @Test
    public void showRegion_estudioTest() throws Exception
    {
        ucab.dsw.servicio.Region_estudioORMWS servicio = new ucab.dsw.servicio.Region_estudioORMWS();
        Response resultado = servicio.showRegion_estudio();
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Region_estudio> region_estudios = (List<Region_estudio>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",region_estudios.isEmpty());
    }

    /**
     * Este test prueba la actualización de una región de estudio de una solicitud de estudio
     *
     */
    @Test
    public void updateRegion_estudioTest() throws Exception{

        ucab.dsw.servicio.Region_estudioORMWS servicio = new ucab.dsw.servicio.Region_estudioORMWS();
        List<Region_estudioDto> region_estudioList = new ArrayList<>();
        Region_estudioDto region_estudioDto = new Region_estudioDto(1);
        LugarDto lugar = new LugarDto( 150);
        region_estudioDto.setLugarDto( lugar );

        Solicitud_estudioDto solicitud_estudio = new Solicitud_estudioDto( 1);
        region_estudioDto.setSolicitudEstudioDto(solicitud_estudio);
        region_estudioDto.setEstado( "I" );
        region_estudioList.add(region_estudioDto);
        Response resultado = servicio.updateLista_regiones (1,region_estudioList);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Region_estudio> region_estudios = (List<Region_estudio>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",region_estudios.isEmpty());
    }

    /**
     * Este test prueba la obtención de todas las regiones de estudio de una solicitud de estudio específica
     *
     */
    @Test
    public void getRegionesDeSolicitudTest() throws Exception{
        ucab.dsw.servicio.Region_estudioORMWS servicio = new ucab.dsw.servicio.Region_estudioORMWS();
        Response resultado = servicio.getRegionesDeSolicitud(1);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Lugar> lugars = (List<Lugar>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",lugars.isEmpty());
    }

    /**
     * Este test prueba la obtención de una region_estudio especifica
     *
     */
    @Test
    public void consultarRegion_estudioTest() throws Exception{
        ucab.dsw.servicio.Region_estudioORMWS servicio = new ucab.dsw.servicio.Region_estudioORMWS();
        Response resultado = servicio.consultarRegion_estudio(1);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Region_estudio region_estudio = (Region_estudio) responseDto.getObjeto();
        System.out.println(region_estudio.get_id());
        Assert.assertNotEquals(region_estudio, null);
    }
}
