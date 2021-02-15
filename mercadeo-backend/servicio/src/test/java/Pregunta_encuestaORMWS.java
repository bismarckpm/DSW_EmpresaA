import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Response.ListaEncuestasE;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.Pregunta_encuesta;

import javax.ws.rs.core.Response;
import java.util.List;

public class Pregunta_encuestaORMWS {
/*
    *//**
     * Este test prueba el registro de una pregunta
     *
     */
    @Test
    public void addPregunta_encuestaTest() throws Exception
    {
        ucab.dsw.servicio.Pregunta_encuestaORMWS servicio = new ucab.dsw.servicio.Pregunta_encuestaORMWS();
        Pregunta_encuestaDto pregunta_encuestaDto = new Pregunta_encuestaDto();
        pregunta_encuestaDto.setDescripcion( "DescpEj" );
        pregunta_encuestaDto.setTipoPregunta( "Seleccion simple" );
        pregunta_encuestaDto.setEstado( "A" );
        UsuarioDto usuario = new UsuarioDto( 2);
        pregunta_encuestaDto.setUsuarioDto( usuario );
        SubcategoriaDto subcategoria = new SubcategoriaDto( 1);
        pregunta_encuestaDto.setSubcategoriaDto( subcategoria );
        Response resultado = servicio.addPregunta_encuesta( pregunta_encuestaDto );
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Pregunta_encuesta pregunta_encuesta = (Pregunta_encuesta) responseDto.getObjeto();
        Assert.assertNotEquals( pregunta_encuesta.get_id(), 0  );
    }

    /**
     * Este test prueba la obtención de todas las preguntas registradas
     *
     */
    @Test
    public void showPregunta_encuestasTest() throws Exception{
        ucab.dsw.servicio.Pregunta_encuestaORMWS servicio = new ucab.dsw.servicio.Pregunta_encuestaORMWS();
        Response resultado = servicio.showPregunta_encuestas();
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Pregunta_encuesta> pregunta_encuestas = (List<Pregunta_encuesta>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",pregunta_encuestas.isEmpty());
    }

    /**
     * Este test prueba la actualización de una pregunta
     *
     */
    @Test
    public void updatePregunta_encuestaTest() throws Exception
    {
        ucab.dsw.servicio.Pregunta_encuestaORMWS servicio = new ucab.dsw.servicio.Pregunta_encuestaORMWS();
        Pregunta_encuestaDto pregunta_encuestaDto = new Pregunta_encuestaDto();
        pregunta_encuestaDto.setDescripcion( "DescpEjModfic" );
        pregunta_encuestaDto.setTipoPregunta( "AbiertaModfic" );
        pregunta_encuestaDto.setEstado( "A" );
        UsuarioDto usuario = new UsuarioDto( 2);
        pregunta_encuestaDto.setUsuarioDto( usuario );
        SubcategoriaDto subcategoria = new SubcategoriaDto( 1);
        pregunta_encuestaDto.setSubcategoriaDto( subcategoria );
        Response resultado = servicio.updatePregunta_encuesta( 1, pregunta_encuestaDto );
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Pregunta_encuesta pregunta_encuesta = (Pregunta_encuesta) responseDto.getObjeto();
        System.out.println(pregunta_encuesta.get_id());
        Assert.assertNotEquals( pregunta_encuesta.get_id(), 0);
    }

    /**
     * Este test prueba la obtención de una pregunta_encuesta especifica
     *
     */
    @Test
    public void consultarPregunta_encuestaTest() throws Exception{
        ucab.dsw.servicio.Pregunta_encuestaORMWS servicio = new ucab.dsw.servicio.Pregunta_encuestaORMWS();
        Response resultado = servicio.consultarPregunta_encuesta(1);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Pregunta_encuesta pregunta_encuesta = (Pregunta_encuesta) responseDto.getObjeto();
        System.out.println(pregunta_encuesta.get_id());
        Assert.assertNotEquals(pregunta_encuesta, null);
    }

    /**
     * Este test prueba la inactivación de una pregunta
     *
     */
    @Test
    public void inactivarPregunta_encuestaTest() throws Exception {
        ucab.dsw.servicio.Pregunta_encuestaORMWS servicio = new ucab.dsw.servicio.Pregunta_encuestaORMWS();
        Pregunta_encuestaDto pregunta = new Pregunta_encuestaDto();
        pregunta.setId(1);
        pregunta.setEstado("I");
        Response resultado = servicio.incativarPregunta_encuesta(1, pregunta);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Pregunta_encuesta pregunta_encuesta = (Pregunta_encuesta) responseDto.getObjeto();
        System.out.println(pregunta_encuesta.get_id());
        Assert.assertNotEquals(pregunta_encuesta, null);

    }

    /**
     * Este test prueba la consulta de las preguntas de tipo selección simple y selección múltiple
     *
     */
    @Test
    public void showPregunta_encuestas_con_opcionesTest() throws Exception{
        ucab.dsw.servicio.Pregunta_encuestaORMWS servicio = new ucab.dsw.servicio.Pregunta_encuestaORMWS();
        Response resultado = servicio.showPregunta_encuestas_con_opciones();
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Pregunta_encuesta> pregunta_encuestas = (List<Pregunta_encuesta>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",pregunta_encuestas.isEmpty());
    }
}
