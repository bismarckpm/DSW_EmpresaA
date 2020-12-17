import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.Pregunta_encuesta;
import ucab.dsw.entidades.Pregunta_estudio;

import java.util.List;

public class Pregunta_estudioORMWS_Test {

    @Test
    public void addPregunta_estudioTest() throws Exception
    {
        ucab.dsw.servicio.Pregunta_estudioORMWS servicio = new ucab.dsw.servicio.Pregunta_estudioORMWS();
        Pregunta_estudioDto pregunta_estudioDto = new Pregunta_estudioDto();
        pregunta_estudioDto.setEstado( "A" );
        EstudioDto estudio = new EstudioDto( 1);
        pregunta_estudioDto.setEstudioDto( estudio );
        Pregunta_encuestaDto pregunta_encuesta = new Pregunta_encuestaDto( 1);
        pregunta_estudioDto.setPreguntaEncuestaDto( pregunta_encuesta );
        Pregunta_estudioDto resultado = servicio.addPregunta_estudio( pregunta_estudioDto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void deletePregunta_estudioTest() throws Exception{
        ucab.dsw.servicio.Pregunta_estudioORMWS servicio = new ucab.dsw.servicio.Pregunta_estudioORMWS();
        Pregunta_estudioDto resultado = servicio.deletePregunta_estudio(1);
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void showPregunta_estudiosTest() throws Exception{
        ucab.dsw.servicio.Pregunta_estudioORMWS servicio = new ucab.dsw.servicio.Pregunta_estudioORMWS();
        List<Pregunta_estudio> resultado = servicio.showPregunta_estudios();
        Assert.assertNotEquals(resultado, null);
    }

    @Test
    public void updatePregunta_estudioTest() throws Exception
    {
        ucab.dsw.servicio.Pregunta_estudioORMWS servicio = new ucab.dsw.servicio.Pregunta_estudioORMWS();
        Pregunta_estudioDto pregunta_estudioDto = new Pregunta_estudioDto();
        pregunta_estudioDto.setEstado( "I" );
        EstudioDto estudio = new EstudioDto( 2);
        pregunta_estudioDto.setEstudioDto( estudio );
        Pregunta_encuestaDto pregunta_encuesta = new Pregunta_encuestaDto( 2);
        pregunta_estudioDto.setPreguntaEncuestaDto( pregunta_encuesta );
        Pregunta_estudioDto resultado = servicio.updatePregunta_estudio( 1, pregunta_estudioDto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void enunciadoPreguntaTest() throws Exception{
        ucab.dsw.servicio.Pregunta_estudioORMWS servicio = new ucab.dsw.servicio.Pregunta_estudioORMWS();
        List<Pregunta_encuesta> resultado = servicio.getEnunciadoPregunta(1);
        Assert.assertNotEquals(resultado, null);
    }
}
