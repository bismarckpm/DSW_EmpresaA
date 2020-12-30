import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ucab.dsw.Response.UsuarioRespondieronEstudioResponse;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.Pregunta_estudio;
import ucab.dsw.entidades.Respuesta;
import ucab.dsw.entidades.Respuesta_pregunta;

import java.util.ArrayList;
import java.util.List;

public class RespuestaORMWS_Test {

    @Test
    public void addRespuestaTest() throws Exception
    {
        ucab.dsw.servicio.RespuestaORMWS servicio = new ucab.dsw.servicio.RespuestaORMWS();

        Pregunta_estudioDto pregunta_estudioDto = new Pregunta_estudioDto(1);
        UsuarioDto usuario = new UsuarioDto(1);

        RespuestaDto respuesta1 = new RespuestaDto();
        respuesta1.setPregunta("Pregunta1");
        respuesta1.setEstado("A");
        respuesta1.setRespuestaSimple("respuestaSimple");
        respuesta1.setPreguntaEstudioDto(pregunta_estudioDto);
        respuesta1.setUsuarioDto(usuario);

        RespuestaDto respuesta2 = new RespuestaDto();
        respuesta2.setPregunta("Pregunta2");
        respuesta2.setEstado("A");
        respuesta2.setRespuertaAbierta("respuestaAbierta");
        respuesta2.setPreguntaEstudioDto(pregunta_estudioDto);
        respuesta2.setUsuarioDto(usuario);

        RespuestaDto respuesta3 = new RespuestaDto();
        respuesta3.setPregunta("Pregunta3");
        respuesta3.setEstado("A");
        respuesta3.setEscala("5");
        respuesta3.setPreguntaEstudioDto(pregunta_estudioDto);
        respuesta3.setUsuarioDto(usuario);

        List<RespuestaDto> respuestas = new ArrayList<RespuestaDto>();

        respuestas.add(respuesta1);
        respuestas.add(respuesta2);
        respuestas.add(respuesta3);

        RespuestaDto resultado = servicio.addRespuesta(respuestas);
        Assert.assertNotEquals(resultado.getId(), 0);
    }

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

    @Test
    public void getRespuestasEncuestador()throws Exception{
        ucab.dsw.servicio.RespuestaORMWS servicio = new ucab.dsw.servicio.RespuestaORMWS();
        List<UsuarioRespondieronEstudioResponse> resultado = servicio.getAllByUserReponse(7);
        Assert.assertNotEquals(resultado.size(), 0);
    }
}

