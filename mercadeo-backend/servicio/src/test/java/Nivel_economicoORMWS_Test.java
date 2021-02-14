import ucab.dsw.dtos.Nivel_economicoDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Nivel_economico;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.List;

public class Nivel_economicoORMWS_Test {
/*
    *//**
     * Este test prueba el registro de un nivel económico
     *
     */
    @Test
    public void addNivel_economicoTest() throws Exception {
        ucab.dsw.servicio.Nivel_economicoORMWS servicio = new ucab.dsw.servicio.Nivel_economicoORMWS();
        Nivel_economicoDto nivel_economicoDto = new Nivel_economicoDto();
        nivel_economicoDto.setNivel( "Nivel_economico1" );
        nivel_economicoDto.setEstado( "A" );
        Response resultado = servicio.addNivel_economico( nivel_economicoDto );
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Nivel_economico nivel_economico = (Nivel_economico) responseDto.getObjeto();
        Assert.assertNotEquals( nivel_economico.get_id(), 0  );
    }

    /**
     * Este test prueba la obtención de todos los niveles económicos registrados
     *
     */
    @Test
    public void showNivel_economicoTest() throws Exception
    {
        ucab.dsw.servicio.Nivel_economicoORMWS servicio = new ucab.dsw.servicio.Nivel_economicoORMWS();
        Response resultado = servicio.showNivel_economico();
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Nivel_economico> nivel_economicos = (List<Nivel_economico>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",nivel_economicos.isEmpty());
    }

    /**
     * Este test prueba la actualización de un nivel económico
     *
     */
    @Test
    public void updateNivel_economicoTest() throws Exception{

        ucab.dsw.servicio.Nivel_economicoORMWS servicio = new ucab.dsw.servicio.Nivel_economicoORMWS();
        Nivel_economicoDto nivel_economicoDto = new Nivel_economicoDto(1);
        nivel_economicoDto.setNivel( "Nivel_economico2" );
        nivel_economicoDto.setEstado( "I" );
        Response resultado = servicio.editNivel_economico (nivel_economicoDto);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Nivel_economico nivel_economico = (Nivel_economico) responseDto.getObjeto();
        System.out.println(nivel_economico.get_id());
        Assert.assertNotEquals( nivel_economico.get_id(), 0);
    }
}
