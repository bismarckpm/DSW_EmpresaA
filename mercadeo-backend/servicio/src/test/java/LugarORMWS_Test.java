
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ucab.dsw.dtos.LugarDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Lugar;
import java.util.List;
import org.junit.Test;
import ucab.dsw.entidades.Solicitud_estudio;
import ucab.dsw.servicio.LugarORMWS;

import javax.ws.rs.core.Response;

public class LugarORMWS_Test {
/*
    *//**
     * Este test prueba el registro de un nuevo lugar
     *
     */
    @Test
    public void addLugarTest() throws Exception
    {
        ucab.dsw.servicio.LugarORMWS servicio = new ucab.dsw.servicio.LugarORMWS();
        LugarDto lugarDto = new LugarDto();
        lugarDto.setNombre( "Ejemplo nombre4" );
        lugarDto.setEstado( "A" );
        lugarDto.setCategoriaSocioEconomica("CatSocEc44");
        lugarDto.setTipo("Municipio");
        LugarDto lugar = new LugarDto( 1);
        lugarDto.setLugarDto( lugar );
        Response resultado = servicio.addLugar( lugarDto );
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Lugar lugarp = (Lugar) responseDto.getObjeto();
        Assert.assertNotEquals( lugarp.get_id(), 0  );
    }

    /**
     * Este test prueba la actualización de un lugar específico
     *
     */
    @Test
    public void updateLugarTest() throws Exception
    {
        ucab.dsw.servicio.LugarORMWS servicio = new ucab.dsw.servicio.LugarORMWS();
        LugarDto lugarDto = new LugarDto();
        lugarDto.setNombre( "Nuevo nombre" );
        lugarDto.setEstado( "I" );
        lugarDto.setCategoriaSocioEconomica( "Nueva categ" );
        lugarDto.setTipo( "Pais" );
        LugarDto lugar = new LugarDto( 2);
        lugarDto.setLugarDto( lugar );
        Response resultado = servicio.updateLugar( 4, lugarDto );
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Lugar lugarp = (Lugar) responseDto.getObjeto();
        System.out.println(lugarp.get_id());
        Assert.assertNotEquals( lugarp.get_id(), 0);
    }

    /**
     * Este test prueba la obtención de todos los lugares de tipo estado
     *
     */
    @Test
    public void getEstadosTest() throws Exception{
        ucab.dsw.servicio.LugarORMWS servicio = new ucab.dsw.servicio.LugarORMWS();
        Response resultado = servicio.getEstados();
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Lugar> lugars = (List<Lugar>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",lugars.isEmpty());
    }

    /**
     * Este test prueba la obtención de todos los lugares de tipo municipio
     *
     */
    @Test
    public void getMunicipiosTest() throws Exception{
        ucab.dsw.servicio.LugarORMWS servicio = new ucab.dsw.servicio.LugarORMWS();
        Response resultado = servicio.getMunicipios();
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Lugar> lugars = (List<Lugar>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",lugars.isEmpty());
    }
}
