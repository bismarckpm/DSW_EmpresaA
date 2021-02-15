import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Region_estudio;
import ucab.dsw.entidades.Respuesta_pregunta;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class Respuesta_preguntaORMWS_Test {
/*
    *//**
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
        Response resultado = servicio.addRespuesta_pregunta( respuesta_preguntaDto );
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Respuesta_pregunta respuesta_pregunta = (Respuesta_pregunta) responseDto.getObjeto();
        Assert.assertNotEquals( respuesta_pregunta.get_id(), 0  );
    }

    /**
     * Este test prueba la obtención de todas las opciones de respuesta de las preguntas registradas
     *
     */
    @Test
    public void showRespuesta_preguntasTest() throws Exception{
        ucab.dsw.servicio.Respuesta_preguntaORMWS servicio = new ucab.dsw.servicio.Respuesta_preguntaORMWS();
        Response resultado = servicio.showRespuesta_preguntas();
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Respuesta_pregunta> respuesta_preguntas = (List<Respuesta_pregunta>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",respuesta_preguntas.isEmpty());
    }

    /**
     * Este test prueba la obtención de las opciones de respuesta de una pregunta específica
     *
     */
    @Test
    public void showRespuesta_preguntas_respuestasTest() throws Exception{
        ucab.dsw.servicio.Respuesta_preguntaORMWS servicio = new ucab.dsw.servicio.Respuesta_preguntaORMWS();
        Response resultado = servicio.showRespuesta_preguntas_respuestas(1);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Respuesta_pregunta> respuesta_preguntas = (List<Respuesta_pregunta>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",respuesta_preguntas.isEmpty());
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
        Response resultado = servicio.updateRespuesta_pregunta( 1, respuesta_preguntaDto );
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Respuesta_pregunta respuesta_pregunta = (Respuesta_pregunta) responseDto.getObjeto();
        System.out.println(respuesta_pregunta.get_id());
        Assert.assertNotEquals( respuesta_pregunta.get_id(), 0);
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
        Response resultado = servicio.addLista_respuestas( 1, listaRespuestas );
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Respuesta_pregunta> respuesta_preguntas = (List<Respuesta_pregunta>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",respuesta_preguntas.isEmpty());
    }

    /**
     * Este test prueba la obtención de una respuesta_preugunta especifica
     *
     */
    @Test
    public void consultarRespuesta_preguntaTest() throws Exception{
        ucab.dsw.servicio.Respuesta_preguntaORMWS servicio = new ucab.dsw.servicio.Respuesta_preguntaORMWS();
        Response resultado = servicio.consultarRespuesta_pregunta(1);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Respuesta_pregunta respuesta_pregunta = (Respuesta_pregunta) responseDto.getObjeto();
        System.out.println(respuesta_pregunta.get_id());
        Assert.assertNotEquals(respuesta_pregunta, null);
    }

    /**
     * Este test prueba la incativación de una respuesta_pregunta
     *
     */
    @Test
    public void inactivarRespuesta_preguntaTest() throws Exception{
        ucab.dsw.servicio.Respuesta_preguntaORMWS servicio = new ucab.dsw.servicio.Respuesta_preguntaORMWS();
        Respuesta_preguntaDto respuesta = new Respuesta_preguntaDto();
        respuesta.setId(1);
        respuesta.setEstado("I");
        Response resultado = servicio.incativarRespuesta_pregunta(1, respuesta);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Respuesta_pregunta respuesta_pregunta = (Respuesta_pregunta) responseDto.getObjeto();
        System.out.println(respuesta_pregunta.get_id());
        Assert.assertNotEquals(respuesta_pregunta, null);
    }
}

