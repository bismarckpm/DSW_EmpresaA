package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import dtos.MarcaDto;
import entidades.Marca;
import java.util.List;

public class MarcaORMWS_Test {

    @Test
    public void addMarcaTest() throws Exception {
        servicios.MarcaORMWS servicio = new servicios.MarcaORMWS();
        MarcaDto marcaDto = new MarcaDto();
        marcaDto.setNombre( "Marca1" );
        marcaDto.setEstado( "A" );
        MarcaDto resultado = servicio.addMarca( marcaDto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void showMarcaTest() throws Exception
    {
        servicios.MarcaORMWS servicio = new servicios.MarcaORMWS();
        List<Marca> marcas = servicio.showMarca();
        Assert.assertFalse("Consulta Realizada con Exito",marcas.isEmpty());
    }

    @Test
    public void updateMarcaTest() throws Exception{

        servicios.MarcaORMWS servicio = new servicios.MarcaORMWS();
        MarcaDto marcaDto = new MarcaDto(1);
        marcaDto.setNombre( "Marca2" );
        marcaDto.setEstado( "I" );
        MarcaDto resultado = servicio.editMarca (marcaDto);
        Assert.assertNotEquals( resultado.getId(), 0);
    }

    @Test
    public void deleteMarcaTest() throws Exception{

        servicios.MarcaORMWS servicio = new servicios.MarcaORMWS();
        MarcaDto marcaDto = new MarcaDto(1);
        MarcaDto resultado = servicio.deleteMarca(marcaDto);
        Assert.assertNotEquals( resultado.getId(), 0 );

    }
}
