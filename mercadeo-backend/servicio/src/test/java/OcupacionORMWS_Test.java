import ucab.dsw.dtos.OcupacionDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Ocupacion;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.List;

public class OcupacionORMWS_Test {
/*
    *//**
     * Este test prueba el registro de una nueva ocupación
     *
     */
    @Test
    public void addOcupacionTest() throws Exception {
        ucab.dsw.servicio.OcupacionORMWS servicio = new ucab.dsw.servicio.OcupacionORMWS();
        OcupacionDto ocupacionDto = new OcupacionDto();
        ocupacionDto.setNombre( "Ocupacion1" );
        ocupacionDto.setEstado( "A" );
        Response resultado = servicio.addOcupacion( ocupacionDto );
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Ocupacion ocupacion = (Ocupacion) responseDto.getObjeto();
        Assert.assertNotEquals( ocupacion.get_id(), 0  );
    }

    /**
     * Este test prueba la obtención de todas las ocupaciones registradas
     *
     */
    @Test
    public void showOcupacionTest() throws Exception
    {
        ucab.dsw.servicio.OcupacionORMWS servicio = new ucab.dsw.servicio.OcupacionORMWS();
        Response resultado = servicio.showOcupacion();
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Ocupacion> ocupacions = (List<Ocupacion>) responseDto.getObjeto();
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
        Response resultado = servicio.editOcupacion (ocupacionDto);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Ocupacion ocupacion = (Ocupacion) responseDto.getObjeto();
        System.out.println(ocupacion.get_id());
        Assert.assertNotEquals( ocupacion.get_id(), 0);
    }
}
