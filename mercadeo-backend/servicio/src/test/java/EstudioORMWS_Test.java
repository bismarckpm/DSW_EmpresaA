import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Response.ListaEncuestasE;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.PreguntaAux;
import ucab.dsw.entidades.Usuario;


import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.List;

public class EstudioORMWS_Test {
/*
    *//**
     * Este test prueba el registro de un nuevo estudio
     *
     */
    @Test
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
        Response resultado = servicio.addEstudio( estudioDto );
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Estudio estudio = (Estudio) responseDto.getObjeto();
        Assert.assertNotEquals( estudio.get_id(), 0  );
    }

    /**
     * Este test prueba la obtención de todos los estudios registrados
     *
     */
    @Test
    public void showEstudiosTest() throws Exception{
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        Response resultado = servicio.showEstudios();
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Estudio> estudios = (List<Estudio>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",estudios.isEmpty());
    }

    /**
     * Este test prueba la actualización de un estudio específico
     *
     */
    @Test
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
        Response resultado = servicio.updateEstudio( 1, estudioDto );
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Estudio estudio = (Estudio) responseDto.getObjeto();
        System.out.println(estudio.get_id());
        Assert.assertNotEquals( estudio.get_id(), 0);
    }

    /**
     * Este test prueba la obtención de los resultados de un estudio específico
     *
     */
    @Test
    public void resultadosEstudioTest() throws Exception{
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        Response resultado = servicio.resultadosEstudio(4);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<PreguntaAux> preguntas = (List<PreguntaAux>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",preguntas.isEmpty());
    }

    /**
     * Este test prueba la obtención de la lista de estudios recomendados para una solicitud de estudio
     *
     */
    @Test
    public void obtenerRecomendacionesTest() throws Exception{
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        Response resultado = servicio.obtenerRecomendaciones(1);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Estudio> estudios = (List<Estudio>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",estudios.isEmpty());
        //Ale
    }

    /**
     * Este test prueba la obtención de la lista de poblacion de un estudio
     *
     */
    @Test
    public void obtenerPoblacionEstudioTest() throws Exception{
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        Response resultado = servicio.obtenerPoblacionEstudio(1);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Usuario> usuarios = (List<Usuario>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",usuarios.isEmpty());
    }

    /**
     * Este test prueba la obtención de la lista de estudios asignados a un analista
     *
     */
    @Test
    public void getEstudiosUsuarioTest() throws Exception{
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        Response resultado = servicio.getEstudiosUsuario(1);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Estudio> estudios = (List<Estudio>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",estudios.isEmpty());
    }

    /**
     * Este test prueba la obtención de la lista de estudios pertenecientes a un cliente
     *
     */
    @Test
    public void getEstudiosClienteTest() throws Exception{
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        Response resultado = servicio.getEstudiosCliente(2);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Estudio> estudios = (List<Estudio>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",estudios.isEmpty());
    }

    /**
     * Este test prueba el registro de un nuevo estudio basado en la recomendación de otro
     *
     */
    @Test
    public void addEstudioPorRecomendacionTest() throws Exception
    {
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        EstudioDto estudioDto = new EstudioDto();
        estudioDto.setId(2);
        estudioDto.setNombre("PruebaUnitaria");
        UsuarioDto usuarioDto = new UsuarioDto(2);
        estudioDto.setUsuarioDto(usuarioDto);
        Response resultado = servicio.addEstudioPorRecomendacion( 2, estudioDto );
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Estudio estudio = (Estudio) responseDto.getObjeto();
        Assert.assertNotEquals( estudio.get_id(), 0  );
    }


    /**
     * Este test prueba el conteo de encuestados que han participado en un estudio específico
     *
     */
    @Test
    public void contarParticipantesTest() throws Exception
    {
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        Response resultado = servicio.contarParticipantes( 10);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Long cantidad = (Long) responseDto.getObjeto();
        Long prueba = 0L;
        Assert.assertNotEquals( cantidad, prueba  );
    }

    /**
     * Este test prueba la consulta de un estudio específico
     *
     */
    @Test
    public void consultarEstudioTest() throws Exception{
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        Response resultado = servicio.consultarEstudio(4);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Estudio estudio = (Estudio) responseDto.getObjeto();
        System.out.println(estudio.get_id());
        Assert.assertNotEquals(estudio, null);
    }

    /**
     * Este test prueba la obtención de todos los estudios a los que ha respondido un encuestado
     *
     */
    @Test
    public void getEstudiosRespondidosEncuestadoTest() throws Exception{
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        Response resultado = servicio.getEstudiosRespondidosEncuestado(1);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Estudio> estudios = (List<Estudio>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",estudios.isEmpty());
    }

    /**
     * Este test prueba la obtención de las respuestas de un encuestado para un estudio específico
     *
     */
    @Test
    public void resultadosEncuestadoTest() throws Exception{
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        Response resultado = servicio.resultadosEncuestado(4, 1);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<PreguntaAux> preguntas = (List<PreguntaAux>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",preguntas.isEmpty());
    }

    /**
     * Este test prueba la obtención de la lista de estudios recomendados para una solicitud de estudio
     *
     */
    @Test
    public void obtenerEstudiosRecomendadosTest() throws Exception{
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        Response resultado = servicio.obtenerEstudiosRecomendados(1);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<ListaEncuestasE> listaEncuestasEs = (List<ListaEncuestasE>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",listaEncuestasEs.isEmpty());
    }

    /**
     * Este test prueba la validación de la participación de un encuestado en un estudio
     *
     */
   @Test
    public void validarParticipacionTest() throws Exception{
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        Response resultado = servicio.validarParticipacion(10, 1);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Boolean valor = (Boolean) responseDto.getObjeto();
        Assert.assertNotEquals( valor, false  );
    }

    /**
     * Este test prueba que algún encuestado haya participado en un estudio
     *
     */
    @Test
    public void validarContestadoTest() throws Exception{
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        Response resultado = servicio.validarContestado( 1);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Boolean valor = (Boolean) responseDto.getObjeto();
        Assert.assertNotEquals( valor, false  );
    }
}
