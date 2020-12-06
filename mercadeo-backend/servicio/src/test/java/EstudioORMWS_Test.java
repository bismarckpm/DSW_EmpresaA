import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.Estudio;

import java.util.List;

public class EstudioORMWS_Test {

    @Test
    public void addEstudioTest() throws Exception
    {
        ucab.dsw.servicio.EstudioORMWS servicio = new ucab.dsw.servicio.EstudioORMWS();
        EstudioDto estudioDto = new EstudioDto();
        java.util.Date fecha = new java.util.Date("21/11/2020");
        java.util.Date fecha2 = new java.util.Date("21/12/2020");
        estudioDto.setNombre( "nombEj" );
        estudioDto.setTipoDeInstrumento( "tIPOeJ" );
        estudioDto.setFechaInicio( fecha );
        estudioDto.setFechaFin( fecha2 );
        estudioDto.setEstatus( "A" );
        estudioDto.setEstado( "A" );
        Solicitud_estudioDto solicitud_estudio = new Solicitud_estudioDto( 1);
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
        EstudioDto estudioDto = new EstudioDto();
        java.util.Date fecha = new java.util.Date("30/11/2020");
        java.util.Date fecha2 = new java.util.Date("30/12/2020");
        estudioDto.setNombre( "nombEjModif" );
        estudioDto.setTipoDeInstrumento( "tIPOeJModif" );
        estudioDto.setFechaInicio( fecha );
        estudioDto.setFechaFin( fecha2 );
        estudioDto.setEstado( "I" );
        estudioDto.setEstatus( "I" );
        Solicitud_estudioDto solicitud_estudio = new Solicitud_estudioDto( 1);
        estudioDto.setSolicitudEstudioDto( solicitud_estudio );
        UsuarioDto usuario = new UsuarioDto( 1);
        estudioDto.setUsuarioDto( usuario );
        EstudioDto resultado = servicio.updateEstudio( 1, estudioDto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }
}
