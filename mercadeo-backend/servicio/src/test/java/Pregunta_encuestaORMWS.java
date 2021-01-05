import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ucab.dsw.Response.ListaEncuestasE;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Pregunta_encuesta;

import java.util.List;

public class Pregunta_encuestaORMWS {

    /**
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
        UsuarioDto usuario = new UsuarioDto( 1);
        pregunta_encuestaDto.setUsuarioDto( usuario );
        SubcategoriaDto subcategoria = new SubcategoriaDto( 1);
        pregunta_encuestaDto.setSubcategoriaDto( subcategoria );
        Pregunta_encuestaDto resultado = servicio.addPregunta_encuesta( pregunta_encuestaDto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    /**
     * Este test prueba la eliminación de una pregunta
     *
     */
    @Test
    public void deletePregunta_encuestaTest() throws Exception{
        ucab.dsw.servicio.Pregunta_encuestaORMWS servicio = new ucab.dsw.servicio.Pregunta_encuestaORMWS();
        Pregunta_encuestaDto resultado = servicio.deletePregunta_encuesta(1);
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    /**
     * Este test prueba la obtención de todas las preguntas registradas
     *
     */
    @Test
    public void showPregunta_encuestasTest() throws Exception{
        ucab.dsw.servicio.Pregunta_encuestaORMWS servicio = new ucab.dsw.servicio.Pregunta_encuestaORMWS();
        List<Pregunta_encuesta> resultado = servicio.showPregunta_encuestas();
        Assert.assertNotEquals(resultado, null);
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
        UsuarioDto usuario = new UsuarioDto( 1);
        pregunta_encuestaDto.setUsuarioDto( usuario );
        SubcategoriaDto subcategoria = new SubcategoriaDto( 1);
        pregunta_encuestaDto.setSubcategoriaDto( subcategoria );
        Pregunta_encuestaDto resultado = servicio.updatePregunta_encuesta( 1, pregunta_encuestaDto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    /**
     * Este test prueba la obtención de una pregunta_encuesta especifica
     *
     */
    @Test
    public void consultarPregunta_encuestaTest() throws Exception{
        ucab.dsw.servicio.Pregunta_encuestaORMWS servicio = new ucab.dsw.servicio.Pregunta_encuestaORMWS();
        Pregunta_encuesta resultado = servicio.consultarPregunta_encuesta(1);
        Assert.assertNotEquals(resultado, null);
    }


    @Test
    public void showPregunta_encuestas_con_opcionesTest() throws Exception{
        ucab.dsw.servicio.Pregunta_encuestaORMWS servicio = new ucab.dsw.servicio.Pregunta_encuestaORMWS();
        List<Pregunta_encuesta> resultado = servicio.showPregunta_encuestas_con_opciones();
        Assert.assertNotEquals(resultado, null);
    }
}
