import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Estudio;
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
     */
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
        Response resultado = servicio.addPregunta_estudio( pregunta_estudioDto );
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Pregunta_estudio pregunta_estudio = (Pregunta_estudio) responseDto.getObjeto();
        Assert.assertNotEquals( pregunta_estudio.get_id(), 0  );
    }

    /**
     * Este test prueba la obtención de todas las preguntas que se encuentran asignadas a estudios
     *
     */
    @Test
    public void showPregunta_estudiosTest() throws Exception{
        ucab.dsw.servicio.Pregunta_estudioORMWS servicio = new ucab.dsw.servicio.Pregunta_estudioORMWS();
        Response resultado = servicio.showPregunta_estudios();
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Pregunta_estudio> pregunta_estudios = (List<Pregunta_estudio>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",pregunta_estudios.isEmpty());
    }

    /**
     * Este test prueba la actualización de una pregunta asignada a un estudio
     *
     */
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
        Response resultado = servicio.updatePregunta_estudio( 1, pregunta_estudioDto );
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Pregunta_estudio pregunta_estudio = (Pregunta_estudio) responseDto.getObjeto();
        System.out.println(pregunta_estudio.get_id());
        Assert.assertNotEquals( pregunta_estudio.get_id(), 0);
    }

    /**
     * Este test prueba el registro de una lista de preguntas y su asignación a un estudio específico
     *
     */
    @Test
    public void addListaPreguntasEstudioTest() throws Exception
    {
        ucab.dsw.servicio.Pregunta_estudioORMWS servicio = new ucab.dsw.servicio.Pregunta_estudioORMWS();
        List<Pregunta_encuestaDto> listaPreguntas = new ArrayList<>();
        DaoPregunta_encuesta dao = new DaoPregunta_encuesta();
        UsuarioDto usuarioDto= new UsuarioDto(2);
        SubcategoriaDto subcategoriaDto = new SubcategoriaDto(1);

        Pregunta_encuestaDto pregunta_encuestaDto1 = new Pregunta_encuestaDto(1);
        pregunta_encuestaDto1.setDescripcion("Desc1");
        pregunta_encuestaDto1.setId(1);
        pregunta_encuestaDto1.setTipoPregunta("Abierta");
        pregunta_encuestaDto1.setUsuarioDto(usuarioDto);
        pregunta_encuestaDto1.setSubcategoriaDto(subcategoriaDto);
        listaPreguntas.add(pregunta_encuestaDto1);

        Pregunta_encuestaDto pregunta_encuestaDto2 = new Pregunta_encuestaDto(2);
        pregunta_encuestaDto2.setDescripcion("Desc2");
        pregunta_encuestaDto2.setId(2);
        pregunta_encuestaDto2.setTipoPregunta("Abierta");
        pregunta_encuestaDto2.setUsuarioDto(usuarioDto);
        pregunta_encuestaDto2.setSubcategoriaDto(subcategoriaDto);
        listaPreguntas.add(pregunta_encuestaDto2);

        Pregunta_encuestaDto pregunta_encuestaDto3 = new Pregunta_encuestaDto(3);
        pregunta_encuestaDto3.setDescripcion("Desc3");
        pregunta_encuestaDto3.setId(3);
        pregunta_encuestaDto3.setTipoPregunta("Abierta");
        pregunta_encuestaDto3.setUsuarioDto(usuarioDto);
        pregunta_encuestaDto3.setSubcategoriaDto(subcategoriaDto);
        listaPreguntas.add(pregunta_encuestaDto3);

        Response resultado = servicio.addListaPreguntasEstudio( 1, listaPreguntas );
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Estudio estudio = (Estudio) responseDto.getObjeto();
        Assert.assertNotEquals( estudio.get_id(), 0  );
    }

    /**
     * Este test prueba la obtención del enunciado de una pregunta de estudio
     *
     */
    @Test
    public void getEnunciadoPreguntaTest() {
        ucab.dsw.servicio.Pregunta_estudioORMWS servicio = new ucab.dsw.servicio.Pregunta_estudioORMWS();
        Response resultado = servicio.getEnunciadoPregunta(1);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Pregunta_encuesta> pregunta_encuestas = (List<Pregunta_encuesta>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",pregunta_encuestas.isEmpty());
    }
}
