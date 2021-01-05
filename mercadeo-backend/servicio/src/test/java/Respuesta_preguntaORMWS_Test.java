import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.Region_estudio;
import ucab.dsw.entidades.Respuesta_pregunta;

import java.util.ArrayList;
import java.util.List;

public class Respuesta_preguntaORMWS_Test {

    /**
     * Este test prueba el registro de una opción de respuesta a una pregunta
     *
     */
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

    /**
     * Este test prueba la eliminación de una opción de respuesta de una pregunta
     *
     */
    @Test
    public void deleteRespuesta_preguntaTest() throws Exception{
        ucab.dsw.servicio.Respuesta_preguntaORMWS servicio = new ucab.dsw.servicio.Respuesta_preguntaORMWS();
        Respuesta_preguntaDto respuesta_preguntaDto = new Respuesta_preguntaDto(1);
        Respuesta_preguntaDto resultado = servicio.deleteRespuesta_pregunta(respuesta_preguntaDto);
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    /**
     * Este test prueba la obtención de todas las opciones de respuesta de las preguntas registradas
     *
     */
    @Test
    public void showRespuesta_preguntasTest() throws Exception{
        ucab.dsw.servicio.Respuesta_preguntaORMWS servicio = new ucab.dsw.servicio.Respuesta_preguntaORMWS();
        List<Respuesta_pregunta> resultado = servicio.showRespuesta_preguntas();
        Assert.assertNotEquals(resultado, null);
    }

    /**
     * Este test prueba la obtención de las opciones de respuesta de una pregunta específica
     *
     */
    @Test
    public void showRespuesta_preguntas_respuestasTest() throws Exception{
        ucab.dsw.servicio.Respuesta_preguntaORMWS servicio = new ucab.dsw.servicio.Respuesta_preguntaORMWS();
        List<Respuesta_pregunta> resultado = servicio.showRespuesta_preguntas_respuestas(1);
        Assert.assertNotEquals(resultado, null);
    }

    /**
     * Este test prueba la actualización de una opción de respuesta de una pregunta
     *
     */
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

    /**
     * Este test prueba el registro de una lista de opciones de respuesta para una pregunta específica
     *
     */
    @Test
    public void addLista_respuestasTest() throws Exception
    {
        ucab.dsw.servicio.Respuesta_preguntaORMWS servicio = new ucab.dsw.servicio.Respuesta_preguntaORMWS();
        List<Respuesta_preguntaDto> listaRespuestas = new ArrayList<>();
        Respuesta_preguntaDto respuesta_preguntaDto = new Respuesta_preguntaDto();
        respuesta_preguntaDto.setNombre( "Primera respuesta" );
        respuesta_preguntaDto.setEstado( "A" );
        Pregunta_encuestaDto pregunta_encuestaDto = new Pregunta_encuestaDto(1);
        respuesta_preguntaDto.setPreguntaEncuestaDto( pregunta_encuestaDto );
        listaRespuestas.add(respuesta_preguntaDto);
        Respuesta_preguntaDto respuesta_preguntaDto2 = new Respuesta_preguntaDto();
        respuesta_preguntaDto2.setNombre( "Segunda respuesta" );
        respuesta_preguntaDto2.setEstado( "A" );
        respuesta_preguntaDto2.setPreguntaEncuestaDto( pregunta_encuestaDto );
        listaRespuestas.add(respuesta_preguntaDto2);
        Respuesta_preguntaDto respuesta_preguntaDto3 = new Respuesta_preguntaDto();
        respuesta_preguntaDto3.setNombre( "Tercera respuesta" );
        respuesta_preguntaDto3.setEstado( "A" );
        respuesta_preguntaDto3.setPreguntaEncuestaDto( pregunta_encuestaDto );
        listaRespuestas.add(respuesta_preguntaDto3);
        Respuesta_preguntaDto respuesta_preguntaDto4 = new Respuesta_preguntaDto();
        respuesta_preguntaDto4.setNombre( "Cuarta respuesta" );
        respuesta_preguntaDto4.setEstado( "A" );
        respuesta_preguntaDto4.setPreguntaEncuestaDto( pregunta_encuestaDto );
        listaRespuestas.add(respuesta_preguntaDto4);
        Pregunta_encuestaDto resultado = servicio.addLista_respuestas( 1, listaRespuestas );
        Assert.assertNotNull( resultado);
    }

    /**
     * Este test prueba la obtención de una respuesta_preugunta especifica
     *
     */
    @Test
    public void consultarRespuesta_preguntaTest() throws Exception{
        ucab.dsw.servicio.Respuesta_preguntaORMWS servicio = new ucab.dsw.servicio.Respuesta_preguntaORMWS();
        Respuesta_pregunta resultado = servicio.consultarRespuesta_pregunta(1);
        Assert.assertNotEquals(resultado, null);
    }
}

