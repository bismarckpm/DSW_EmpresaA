import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.PreguntaAux;


import java.text.SimpleDateFormat;
import java.util.List;

public class EstudioORMWS_Test {

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
        EstudioDto resultado = servicio.addEstudio( estudioDto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void deleteEstudioTest() throws Exception{
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        EstudioDto resultado = servicio.deleteEstudio(1);
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void showEstudiosTest() throws Exception{
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        List<Estudio> resultado = servicio.showEstudios();
        Assert.assertNotEquals(resultado, null);
    }

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
        EstudioDto resultado = servicio.updateEstudio( 1, estudioDto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
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

    @Test
    public void obtenerRecomendacionesTest() throws Exception{
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        List<Estudio> resultado = servicio.obtenerRecomendaciones(1);
        Assert.assertNotEquals(resultado, null);
    }

    @Test
    public void getEstudiosUsuarioTest() throws Exception{
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        List<Estudio> resultado = servicio.getEstudiosUsuario(1);
        Assert.assertNotEquals(resultado, null);
    }

    @Test
    public void getEstudiosClienteTest() throws Exception{
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        List<Estudio> resultado = servicio.getEstudiosCliente(2);
        Assert.assertNotEquals(resultado, null);
    }

    @Test
    public void addEstudioPorRecomendacionTest() throws Exception
    {
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        EstudioDto estudioDto = new EstudioDto();
        estudioDto.setId(2);
        UsuarioDto usuarioDto = new UsuarioDto(1);
        estudioDto.setUsuarioDto(usuarioDto);
        EstudioDto resultado = servicio.addEstudioPorRecomendacion( 4, estudioDto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }


    @Test
    public void contarParticipantesTest() throws Exception
    {
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        Long resultado = servicio.contarParticipantes( 10);
        Assert.assertNotEquals( resultado, null  );
    }

    @Test
    public void consultarEstudioTest() throws Exception{
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        Estudio resultado = servicio.consultarEstudio(4);
        Assert.assertNotEquals(resultado, null);
    }

    @Test
    public void getEstudiosRespondidosEncuestadoTest() throws Exception{
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        List<Estudio> resultado = servicio.getEstudiosRespondidosEncuestado(1);
        Assert.assertNotEquals(resultado, null);
    }
}
