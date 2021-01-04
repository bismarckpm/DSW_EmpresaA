import ucab.dsw.dtos.OcupacionDto;
import ucab.dsw.entidades.Ocupacion;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class OcupacionORMWS_Test {

    /**
     * Este test prueba el registro de una nueva ocupación
     *
     */
    @Test
    public void addOcupacionTest() throws Exception {
        ucab.dsw.servicio.OcupacionORMWS servicio = new ucab.dsw.servicio.OcupacionORMWS();
        OcupacionDto ocupacionDto = new OcupacionDto();
        ocupacionDto.setNombre( "Ocupacion1" );
        ocupacionDto.setEstado( "A" );
        OcupacionDto resultado = servicio.addOcupacion( ocupacionDto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    /**
     * Este test prueba la obtención de todas las ocupaciones registradas
     *
     */
    @Test
    public void showOcupacionTest() throws Exception
    {
        ucab.dsw.servicio.OcupacionORMWS servicio = new ucab.dsw.servicio.OcupacionORMWS();
        List<Ocupacion> ocupacions = servicio.showOcupacion();
        Assert.assertFalse("Consulta Realizada con Exito",ocupacions.isEmpty());
    }

    /**
     * Este test prueba la actualización de una ocupación
     *
     */
    @Test
    public void updateOcupacionTest() throws Exception{

        ucab.dsw.servicio.OcupacionORMWS servicio = new ucab.dsw.servicio.OcupacionORMWS();
        OcupacionDto ocupacionDto = new OcupacionDto(1);
        ocupacionDto.setNombre( "Ocupacion2" );
        ocupacionDto.setEstado( "I" );
        OcupacionDto resultado = servicio.editOcupacion (ocupacionDto);
        Assert.assertNotEquals( resultado.getId(), 0);
    }

    /**
     * Este test prueba la eliminación de una ocupación
     *
     */
    @Test
    public void deleteOcupacionTest() throws Exception{

        ucab.dsw.servicio.OcupacionORMWS servicio = new ucab.dsw.servicio.OcupacionORMWS();
        OcupacionDto ocupacionDto = new OcupacionDto(1);
        OcupacionDto resultado = servicio.deleteOcupacion(ocupacionDto);
        Assert.assertNotEquals( resultado.getId(), 0 );

    }
}
