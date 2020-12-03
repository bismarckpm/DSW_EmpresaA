package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import dtos.Nivel_economicoDto;
import entidades.Nivel_economico;
import java.util.List;

public class Nivel_economicoORMWS_Test {

    @Test
    public void addNivel_economicoTest() throws Exception {
        servicios.Nivel_economicoORMWS servicio = new servicios.Nivel_economicoORMWS();
        Nivel_economicoDto nivel_economicoDto = new Nivel_economicoDto();
        nivel_economicoDto.setNivel( "Nivel_economico1" );
        nivel_economicoDto.setEstado( "A" );
        Nivel_economicoDto resultado = servicio.addNivel_economico( nivel_economicoDto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void showNivel_economicoTest() throws Exception
    {
        servicios.Nivel_economicoORMWS servicio = new servicios.Nivel_economicoORMWS();
        List<Nivel_economico> nivel_economicos = servicio.showNivel_economico();
        Assert.assertFalse("Consulta Realizada con Exito",nivel_economicos.isEmpty());
    }

    @Test
    public void updateNivel_economicoTest() throws Exception{

        servicios.Nivel_economicoORMWS servicio = new servicios.Nivel_economicoORMWS();
        Nivel_economicoDto nivel_economicoDto = new Nivel_economicoDto(1);
        nivel_economicoDto.setNivel( "Nivel_economico2" );
        nivel_economicoDto.setEstado( "I" );
        Nivel_economicoDto resultado = servicio.editNivel_economico (nivel_economicoDto);
        Assert.assertNotEquals( resultado.getId(), 0);
    }

    @Test
    public void deleteNivel_economicoTest() throws Exception{

        servicios.Nivel_economicoORMWS servicio = new servicios.Nivel_economicoORMWS();
        Nivel_economicoDto nivel_economicoDto = new Nivel_economicoDto(1);
        Nivel_economicoDto resultado = servicio.deleteNivel_economico(nivel_economicoDto);
        Assert.assertNotEquals( resultado.getId(), 0 );

    }
}
