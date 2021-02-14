import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Response.PreguntasResponse;
import ucab.dsw.dtos.MarcaDto;
import ucab.dsw.entidades.Marca;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.List;

public class MarcaORMWS_Test {
/*
    *//**
     * Este test prueba el registro de una nueva marca
     *
     */
    @Test
    public void addMarcaTest() throws Exception {
        ucab.dsw.servicio.MarcaORMWS servicio = new ucab.dsw.servicio.MarcaORMWS();
        MarcaDto marcaDto = new MarcaDto();
        marcaDto.setNombre( "Marca1" );
        marcaDto.setEstado( "A" );
        Response resultado = servicio.addMarca( marcaDto );
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Marca marca = (Marca) responseDto.getObjeto();
        Assert.assertNotEquals( marca.get_id(), 0  );
    }

    /**
     * Este test prueba la obtención de todas las marcas registradas
     *
     */
    @Test
    public void showMarcaTest() throws Exception
    {
        ucab.dsw.servicio.MarcaORMWS servicio = new ucab.dsw.servicio.MarcaORMWS();
        Response resultado = servicio.showMarca();
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Marca> marcas = (List<Marca>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",marcas.isEmpty());
    }

    /**
     * Este test prueba la actualización de una marca
     *
     */
    @Test
    public void updateMarcaTest() throws Exception{

        ucab.dsw.servicio.MarcaORMWS servicio = new ucab.dsw.servicio.MarcaORMWS();
        MarcaDto marcaDto = new MarcaDto(1);
        marcaDto.setNombre( "Marca2" );
        marcaDto.setEstado( "I" );
        Response resultado = servicio.editMarca (marcaDto);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Marca marca = (Marca) responseDto.getObjeto();
        System.out.println(marca.get_id());
        Assert.assertNotEquals( marca.get_id(), 0);
    }

    /**
     * Este test prueba la obtención de una marca especifica
     *
     */
    @Test
    public void consultarMarcaTest() throws Exception{
        ucab.dsw.servicio.MarcaORMWS servicio = new ucab.dsw.servicio.MarcaORMWS();
        Response resultado = servicio.consultarMarca(1);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Marca marca = (Marca) responseDto.getObjeto();
        System.out.println(marca.get_id());
        Assert.assertNotEquals(marca, null);
    }
}
