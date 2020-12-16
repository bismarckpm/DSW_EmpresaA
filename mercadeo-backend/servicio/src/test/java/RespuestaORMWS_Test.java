import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.Respuesta;
import ucab.dsw.entidades.Respuesta_pregunta;

import java.util.List;

public class RespuestaORMWS_Test {

    @Test
    public void contarRespuestasSimplesTest() throws Exception{
        ucab.dsw.servicio.RespuestaORMWS servicio = new ucab.dsw.servicio.RespuestaORMWS();
        List<Long> resultado = servicio.contarRespuestasSimples(4);
        Assert.assertNotEquals(resultado, null);
    }

    @Test
    public void getRespuestasPreguntaSimpleTest() throws Exception{
        ucab.dsw.servicio.RespuestaORMWS servicio = new ucab.dsw.servicio.RespuestaORMWS();
        List<Respuesta> resultado = servicio.showRespuestasAPreguntaSimple(1);
        Assert.assertNotEquals(resultado, null);
    }

    @Test
    public void contarRespuestasMultiplesTest() throws Exception{
        ucab.dsw.servicio.RespuestaORMWS servicio = new ucab.dsw.servicio.RespuestaORMWS();
        List<Long> resultado = servicio.contarRespuestasMultiples(4);
        Assert.assertNotEquals(resultado, null);
    }

    @Test
    public void getRespuestasPreguntaMultipleTest() throws Exception{
        ucab.dsw.servicio.RespuestaORMWS servicio = new ucab.dsw.servicio.RespuestaORMWS();
        List<Respuesta> resultado = servicio.showRespuestasAPreguntaMultiple(1);
        Assert.assertNotEquals(resultado, null);
    }

    @Test
    public void contarRespuestasVFTest() throws Exception{
        ucab.dsw.servicio.RespuestaORMWS servicio = new ucab.dsw.servicio.RespuestaORMWS();
        List<Long> resultado = servicio.contarRespuestasVF(9);
        Assert.assertNotEquals(resultado, null);
    }

    @Test
    public void getRespuestasPreguntaVFTest() throws Exception{
        ucab.dsw.servicio.RespuestaORMWS servicio = new ucab.dsw.servicio.RespuestaORMWS();
        List<Respuesta> resultado = servicio.showRespuestasAPreguntaVF(1);
        Assert.assertNotEquals(resultado, null);
    }

    @Test
    public void getRespuestasPreguntaAbiertaTest() throws Exception{
        ucab.dsw.servicio.RespuestaORMWS servicio = new ucab.dsw.servicio.RespuestaORMWS();
        List<Respuesta> resultado = servicio.showRespuestasAPreguntaAbierta(1);
        Assert.assertNotEquals(resultado, null);
    }
}
