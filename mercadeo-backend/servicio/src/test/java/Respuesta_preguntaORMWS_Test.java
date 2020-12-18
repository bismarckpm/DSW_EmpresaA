import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.Respuesta_pregunta;

import java.util.List;

public class Respuesta_preguntaORMWS_Test {

    @Test
    public void addRespuesta_preguntaTest() throws Exception
    {
        ucab.dsw.servicio.Respuesta_preguntaORMWS servicio = new ucab.dsw.servicio.Respuesta_preguntaORMWS();
        Respuesta_preguntaDto respuesta_preguntaDto = new Respuesta_preguntaDto();
        respuesta_preguntaDto.setNombre( "NombreEj" );
        respuesta_preguntaDto.setEstado( "A" );
        Pregunta_encuestaDto pregunta_encuestaDto = new Pregunta_encuestaDto(1);
        respuesta_preguntaDto.setPreguntaEncuestaDto( pregunta_encuestaDto );
        Respuesta_preguntaDto resultado = servicio.addRespuesta_pregunta( respuesta_preguntaDto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void deleteRespuesta_preguntaTest() throws Exception{
        ucab.dsw.servicio.Respuesta_preguntaORMWS servicio = new ucab.dsw.servicio.Respuesta_preguntaORMWS();
        Respuesta_preguntaDto respuesta_preguntaDto = new Respuesta_preguntaDto(1);
        Respuesta_preguntaDto resultado = servicio.deleteRespuesta_pregunta(respuesta_preguntaDto);
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void showRespuesta_preguntasTest() throws Exception{
        ucab.dsw.servicio.Respuesta_preguntaORMWS servicio = new ucab.dsw.servicio.Respuesta_preguntaORMWS();
        List<Respuesta_pregunta> resultado = servicio.showRespuesta_preguntas();
        Assert.assertNotEquals(resultado, null);
    }

    @Test
    public void showRespuesta_preguntas_respuestasTest() throws Exception{
        ucab.dsw.servicio.Respuesta_preguntaORMWS servicio = new ucab.dsw.servicio.Respuesta_preguntaORMWS();
        List<Respuesta_pregunta> resultado = servicio.showRespuesta_preguntas_respuestas(1);
        Assert.assertNotEquals(resultado, null);
    }

    @Test
    public void updateRespuesta_preguntaTest() throws Exception
    {
        ucab.dsw.servicio.Respuesta_preguntaORMWS servicio = new ucab.dsw.servicio.Respuesta_preguntaORMWS();
        Respuesta_preguntaDto respuesta_preguntaDto = new Respuesta_preguntaDto();
        respuesta_preguntaDto.setNombre( "NombreEjModfic" );
        respuesta_preguntaDto.setEstado( "I" );
        Pregunta_encuestaDto pregunta_encuestaDto = new Pregunta_encuestaDto(1);
        respuesta_preguntaDto.setPreguntaEncuestaDto( pregunta_encuestaDto );
        Respuesta_preguntaDto resultado = servicio.updateRespuesta_pregunta( 1, respuesta_preguntaDto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }
}

