import ucab.dsw.dtos.MarcaDto;
import ucab.dsw.entidades.Marca;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MarcaORMWS_Test {

    @Test
    public void addMarcaTest() throws Exception {
        ucab.dsw.servicio.MarcaORMWS servicio = new ucab.dsw.servicio.MarcaORMWS();
        MarcaDto marcaDto = new MarcaDto();
        marcaDto.setNombre( "Marca1" );
        marcaDto.setEstado( "A" );
        MarcaDto resultado = servicio.addMarca( marcaDto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void showMarcaTest() throws Exception
    {
        ucab.dsw.servicio.MarcaORMWS servicio = new ucab.dsw.servicio.MarcaORMWS();
        List<Marca> marcas = servicio.showMarca();
        Assert.assertFalse("Consulta Realizada con Exito",marcas.isEmpty());
    }

    @Test
    public void updateMarcaTest() throws Exception{

        ucab.dsw.servicio.MarcaORMWS servicio = new ucab.dsw.servicio.MarcaORMWS();
        MarcaDto marcaDto = new MarcaDto(1);
        marcaDto.setNombre( "Marca2" );
        marcaDto.setEstado( "I" );
        MarcaDto resultado = servicio.editMarca (marcaDto);
        Assert.assertNotEquals( resultado.getId(), 0);
    }

    @Test
    public void deleteMarcaTest() throws Exception{

        ucab.dsw.servicio.MarcaORMWS servicio = new ucab.dsw.servicio.MarcaORMWS();
        MarcaDto marcaDto = new MarcaDto(1);
        MarcaDto resultado = servicio.deleteMarca(marcaDto);
        Assert.assertNotEquals( resultado.getId(), 0 );

    }
}
