package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import dtos.OcupacionDto;
import entidades.Ocupacion;
import java.util.List;

public class OcupacionORMWS_Test {

    @Test
    public void addOcupacionTest() throws Exception {
        servicios.OcupacionORMWS servicio = new servicios.OcupacionORMWS();
        OcupacionDto ocupacionDto = new OcupacionDto();
        ocupacionDto.setNombre( "Ocupacion1" );
        ocupacionDto.setEstado( "A" );
        OcupacionDto resultado = servicio.addOcupacion( ocupacionDto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void showOcupacionTest() throws Exception
    {
        servicios.OcupacionORMWS servicio = new servicios.OcupacionORMWS();
        List<Ocupacion> ocupacions = servicio.showOcupacion();
        Assert.assertFalse("Consulta Realizada con Exito",ocupacions.isEmpty());
    }

    @Test
    public void updateOcupacionTest() throws Exception{

        servicios.OcupacionORMWS servicio = new servicios.OcupacionORMWS();
        OcupacionDto ocupacionDto = new OcupacionDto(1);
        ocupacionDto.setNombre( "Ocupacion2" );
        ocupacionDto.setEstado( "I" );
        OcupacionDto resultado = servicio.editOcupacion (ocupacionDto);
        Assert.assertNotEquals( resultado.getId(), 0);
    }

    @Test
    public void deleteOcupacionTest() throws Exception{

        servicios.OcupacionORMWS servicio = new servicios.OcupacionORMWS();
        OcupacionDto ocupacionDto = new OcupacionDto(1);
        OcupacionDto resultado = servicio.deleteOcupacion(ocupacionDto);
        Assert.assertNotEquals( resultado.getId(), 0 );

    }
}
