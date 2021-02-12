import ucab.dsw.entidades.Response.PreguntasResponse;
import ucab.dsw.dtos.MarcaDto;
import ucab.dsw.entidades.Marca;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MarcaORMWS_Test {

    /**
     * Este test prueba el registro de una nueva marca
     *
     */
    @Test
    public void addMarcaTest() throws Exception {
        ucab.dsw.servicio.MarcaORMWS servicio = new ucab.dsw.servicio.MarcaORMWS();
        MarcaDto marcaDto = new MarcaDto();
        marcaDto.setNombre( "Marca1" );
        marcaDto.setEstado( "A" );
        MarcaDto resultado = servicio.addMarca( marcaDto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    /**
     * Este test prueba la obtención de todas las marcas registradas
     *
     */
    @Test
    public void showMarcaTest() throws Exception
    {
        ucab.dsw.servicio.MarcaORMWS servicio = new ucab.dsw.servicio.MarcaORMWS();
        List<Marca> marcas = servicio.showMarca();
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
        MarcaDto resultado = servicio.editMarca (marcaDto);
        Assert.assertNotEquals( resultado.getId(), 0);
    }

    /**
     * Este test prueba la obtención de una marca especifica
     *
     */
    @Test
    public void consultarMarcaTest() throws Exception{
        ucab.dsw.servicio.MarcaORMWS servicio = new ucab.dsw.servicio.MarcaORMWS();
        Marca resultado = servicio.consultarMarca(1);
        Assert.assertNotEquals(resultado, null);
    }
}
