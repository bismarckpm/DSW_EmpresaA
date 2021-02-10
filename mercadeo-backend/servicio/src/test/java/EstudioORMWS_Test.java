import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.entidades.Response.ListaEncuestasE;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.PreguntaAux;
import ucab.dsw.entidades.Usuario;


import java.text.SimpleDateFormat;
import java.util.List;

public class EstudioORMWS_Test {

    /**
     * Este test prueba el registro de un nuevo estudio
     *
     */
    /*@Test
    public void addEstudioTest() throws Exception
    {
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        EstudioDto estudioDto = new EstudioDto();
        estudioDto.setNombre( "nombEj" );
        estudioDto.setFechaInicio( new SimpleDateFormat("dd/MM/yyyy").parse("21/11/2020") );
        estudioDto.setFechaFin( new SimpleDateFormat("dd/MM/yyyy").parse("21/11/2020") );
        estudioDto.setEstatus( "En Proceso" );
        estudioDto.setEstado( "A" );
        Solicitud_estudioDto solicitud_estudio = new Solicitud_estudioDto( 2);
        estudioDto.setSolicitudEstudioDto( solicitud_estudio );
        UsuarioDto usuario = new UsuarioDto( 1);
        estudioDto.setUsuarioDto( usuario );
        EstudioDto resultado = servicio.addEstudio( estudioDto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    /**
     * Este test prueba la obtención de todos los estudios registrados
     *
     */
    /*@Test
    public void showEstudiosTest() throws Exception{
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        List<Estudio> resultado = servicio.showEstudios();
        Assert.assertNotEquals(resultado, null);
    }

    /**
     * Este test prueba la actualización de un estudio específico
     *
     */
    /*@Test
    public void updateEstudioTest() throws Exception
    {
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        EstudioDto estudioDto = new EstudioDto(1);
        estudioDto.setNombre( "PruebaUnit" );
        estudioDto.setFechaInicio( new SimpleDateFormat("dd/MM/yyyy").parse("30/11/2020") );
        estudioDto.setFechaFin( new SimpleDateFormat("dd/MM/yyyy").parse("30/12/2020") );
        estudioDto.setEstado( "I" );
        estudioDto.setEstatus( "Finalizado" );
        Solicitud_estudioDto solicitud_estudio = new Solicitud_estudioDto( 2);
        estudioDto.setSolicitudEstudioDto( solicitud_estudio );
        UsuarioDto usuario = new UsuarioDto( 1);
        estudioDto.setUsuarioDto( usuario );
        EstudioDto resultado = servicio.updateEstudio( 1, estudioDto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    /**
     * Este test prueba la obtención de los resultados de un estudio específico
     *
     */
    /*@Test
    public void resultadosEstudioTest() throws Exception{
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        List<PreguntaAux> resultado = servicio.resultadosEstudio(4);
        System.out.println("Enunciados de preguntas:");
        for (PreguntaAux pAux : resultado) {
            System.out.print(pAux.get_enunciado());
            System.out.print(", ");
        }
        Assert.assertNotEquals(resultado, null);
    }

    /**
     * Este test prueba la obtención de la lista de estudios recomendados para una solicitud de estudio
     *
     */
    /*@Test
    public void obtenerRecomendacionesTest() throws Exception{
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        List<Estudio> resultado = servicio.obtenerRecomendaciones(1);
        Assert.assertNotEquals(resultado, null);
        //Ale
    }

    /**
     * Este test prueba la obtención de la lista de poblacion de un estudio
     *
     */
    /*@Test
    public void obtenerPoblacionEstudioTest() throws Exception{
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        List<Usuario> resultado = servicio.obtenerPoblacionEstudio(1);
        Assert.assertNotEquals(resultado, null);
    }

    /**
     * Este test prueba la obtención de la lista de estudios asignados a un analista
     *
     */
    /*@Test
    public void getEstudiosUsuarioTest() throws Exception{
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        List<Estudio> resultado = servicio.getEstudiosUsuario(1);
        Assert.assertNotEquals(resultado, null);
    }

    /**
     * Este test prueba la obtención de la lista de estudios pertenecientes a un cliente
     *
     */
    /*@Test
    public void getEstudiosClienteTest() throws Exception{
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        List<Estudio> resultado = servicio.getEstudiosCliente(2);
        Assert.assertNotEquals(resultado, null);
    }

    /**
     * Este test prueba el registro de un nuevo estudio basado en la recomendación de otro
     *
     */
    /*@Test
    public void addEstudioPorRecomendacionTest() throws Exception
    {
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        EstudioDto estudioDto = new EstudioDto();
        estudioDto.setId(2);
        UsuarioDto usuarioDto = new UsuarioDto(1);
        estudioDto.setUsuarioDto(usuarioDto);
        EstudioDto resultado = servicio.addEstudioPorRecomendacion( 2, estudioDto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }


    /**
     * Este test prueba el conteo de encuestados que han participado en un estudio específico
     *
     */
    /*@Test
    public void contarParticipantesTest() throws Exception
    {
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        Long resultado = servicio.contarParticipantes( 10);
        Assert.assertNotEquals( resultado, null  );
    }

    /**
     * Este test prueba la consulta de un estudio específico
     *
     */
    /*@Test
    public void consultarEstudioTest() throws Exception{
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        Estudio resultado = servicio.consultarEstudio(4);
        Assert.assertNotEquals(resultado, null);
    }

    /**
     * Este test prueba la obtención de todos los estudios a los que ha respondido un encuestado
     *
     */
    /*@Test
    public void getEstudiosRespondidosEncuestadoTest() throws Exception{
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        List<Estudio> resultado = servicio.getEstudiosRespondidosEncuestado(1);
        Assert.assertNotEquals(resultado, null);
    }

    /**
     * Este test prueba la obtención de las respuestas de un encuestado para un estudio específico
     *
     */
    /*@Test
    public void resultadosEncuestadoTest() throws Exception{
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        List<PreguntaAux> resultado = servicio.resultadosEncuestado(4, 1);
        System.out.println("Enunciados de preguntas:");
        for (PreguntaAux pAux : resultado) {
            System.out.print(pAux.get_enunciado());
            System.out.print(", ");
        }
        Assert.assertNotEquals(resultado, null);
    }

    /**
     * Este test prueba la obtención de la lista de estudios recomendados para una solicitud de estudio
     *
     */
   /* @Test
    public void obtenerEstudiosRecomendadosTest() throws Exception{
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        List<ListaEncuestasE> resultado = servicio.obtenerEstudiosRecomendados(1);
        Assert.assertNotEquals(resultado, null);
    }

    /**
     * Este test prueba la validación de la participación de un encuestado en un estudio
     *
     */
   /* @Test
    public void validarParticipacionTest() throws Exception{
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        Boolean resultado = servicio.validarParticipacion(10, 1);
        Assert.assertNotEquals(resultado, null);
    }

    /**
     * Este test prueba que algún encuestado haya participado en un estudio
     *
     */
    @Test
    public void validarContestadoTest() throws Exception{
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        Boolean resultado = servicio.validarContestado( 1);
        Assert.assertNotEquals(resultado, null);
    }*/
}
