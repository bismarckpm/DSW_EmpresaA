import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.entidades.Response.PreguntasResponse;
import ucab.dsw.accesodatos.DaoPregunta_encuesta;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.Pregunta_encuesta;
import ucab.dsw.entidades.Pregunta_estudio;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class Pregunta_estudioORMWS_Test {
/*
    *//**
     * Este test prueba el registro y asignación de una pregunta a un estudio
     *
     *//*
    @Test
    public void addPregunta_estudioTest() throws Exception
    {
        ucab.dsw.servicio.Pregunta_estudioORMWS servicio = new ucab.dsw.servicio.Pregunta_estudioORMWS();
        Pregunta_estudioDto pregunta_estudioDto = new Pregunta_estudioDto();
        pregunta_estudioDto.setEstado( "A" );
        pregunta_estudioDto.setPregunta("pregunta1");
        EstudioDto estudio = new EstudioDto( 1);
        pregunta_estudioDto.setEstudioDto( estudio );
        Pregunta_encuestaDto pregunta_encuesta = new Pregunta_encuestaDto( 1);
        pregunta_estudioDto.setPreguntaEncuestaDto( pregunta_encuesta );
        Pregunta_estudioDto resultado = servicio.addPregunta_estudio( pregunta_estudioDto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    *//**
     * Este test prueba la obtención de todas las preguntas que se encuentran asignadas a estudios
     *
     *//*
    @Test
    public void showPregunta_estudiosTest() throws Exception{
        ucab.dsw.servicio.Pregunta_estudioORMWS servicio = new ucab.dsw.servicio.Pregunta_estudioORMWS();
        List<Pregunta_estudio> resultado = servicio.showPregunta_estudios();
        Assert.assertNotEquals(resultado, null);
    }

    *//**
     * Este test prueba la actualización de una pregunta asignada a un estudio
     *
     *//*
    @Test
    public void updatePregunta_estudioTest() throws Exception
    {
        ucab.dsw.servicio.Pregunta_estudioORMWS servicio = new ucab.dsw.servicio.Pregunta_estudioORMWS();
        Pregunta_estudioDto pregunta_estudioDto = new Pregunta_estudioDto();
        pregunta_estudioDto.setEstado( "I" );
        pregunta_estudioDto.setPregunta("pregunta1");
        EstudioDto estudio = new EstudioDto( 2);
        pregunta_estudioDto.setEstudioDto( estudio );
        Pregunta_encuestaDto pregunta_encuesta = new Pregunta_encuestaDto( 2);
        pregunta_estudioDto.setPreguntaEncuestaDto( pregunta_encuesta );
        Pregunta_estudioDto resultado = servicio.updatePregunta_estudio( 1, pregunta_estudioDto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    *//**
     * Este test prueba el registro de una lista de preguntas y su asignación a un estudio específico
     *
     *//*
    @Test
    public void addListaPreguntasEstudioTest() throws Exception
    {
        ucab.dsw.servicio.Pregunta_estudioORMWS servicio = new ucab.dsw.servicio.Pregunta_estudioORMWS();
        List<Pregunta_encuestaDto> listaPreguntas = new ArrayList<>();
        DaoPregunta_encuesta dao = new DaoPregunta_encuesta();

        Pregunta_encuestaDto pregunta_encuestaDto1 = new Pregunta_encuestaDto();
        Pregunta_encuesta pregunta_encuesta1 = new Pregunta_encuesta();
        pregunta_encuesta1 = dao.find((long) 1, Pregunta_encuesta.class);
        pregunta_encuestaDto1.setDescripcion(pregunta_encuesta1.get_descripcion());
        pregunta_encuestaDto1.setId(pregunta_encuesta1.get_id());
        listaPreguntas.add(pregunta_encuestaDto1);

        Pregunta_encuestaDto pregunta_encuestaDto2 = new Pregunta_encuestaDto();
        Pregunta_encuesta pregunta_encuesta2 = new Pregunta_encuesta();
        pregunta_encuesta2 = dao.find((long) 2, Pregunta_encuesta.class);
        pregunta_encuestaDto2.setDescripcion(pregunta_encuesta2.get_descripcion());
        pregunta_encuestaDto2.setId(pregunta_encuesta2.get_id());
        listaPreguntas.add(pregunta_encuestaDto2);

        Pregunta_encuestaDto pregunta_encuestaDto3 = new Pregunta_encuestaDto();
        Pregunta_encuesta pregunta_encuesta3 = new Pregunta_encuesta();
        pregunta_encuesta3 = dao.find((long) 3, Pregunta_encuesta.class);
        pregunta_encuestaDto3.setDescripcion(pregunta_encuesta3.get_descripcion());
        pregunta_encuestaDto3.setId(pregunta_encuesta3.get_id());
        listaPreguntas.add(pregunta_encuestaDto3);

        EstudioDto resultado = servicio.addListaPreguntasEstudio( 1, listaPreguntas );
        Assert.assertNotNull( resultado);
    }

    *//**
     * Este test prueba la obtención de la lista de preguntas recomendados a asignar para un estudio
     *
     *//*
    @Test
    public void obtenerPreguntasRecomendadasTest() throws Exception{
        ucab.dsw.servicio.Pregunta_estudioORMWS servicio = new ucab.dsw.servicio.Pregunta_estudioORMWS();
        List<PreguntasResponse> resultado = servicio.obtenerPreguntasRecomendadas(1);
        Assert.assertNotEquals(resultado, null);
    }

    *//**
     * Este test prueba la obtención de la lista de preguntas a asignar para un estudio
     *
     *//*
    @Test
    public void obtenerPreguntasGeneralesTest() throws Exception{
        ucab.dsw.servicio.Pregunta_estudioORMWS servicio = new ucab.dsw.servicio.Pregunta_estudioORMWS();
        Response resultado = servicio.obtenerPreguntasGenerales(1);
        Assert.assertNotEquals(resultado, null);
    }

    *//**
     * Este test prueba la obtención de la lista de preguntas del estudio
     *
     *//*
    @Test
    public void obtenerPreguntasDeEstudioTest() throws Exception{
        ucab.dsw.servicio.Pregunta_estudioORMWS servicio = new ucab.dsw.servicio.Pregunta_estudioORMWS();
        List<PreguntasResponse> resultado = servicio.obtenerPreguntasDeEstudio(1);
        Assert.assertNotEquals(resultado, null);
    }

    *//**
     * Este test prueba la obtención del enunciado de una pregunta de estudio
     *
     *//*
    @Test
    public void getEnunciadoPreguntaTest() throws Exception{
        ucab.dsw.servicio.Pregunta_estudioORMWS servicio = new ucab.dsw.servicio.Pregunta_estudioORMWS();
        List<Pregunta_encuesta> resultado = servicio.getEnunciadoPregunta(1);
        Assert.assertNotEquals(resultado, null);
    }*/
}
