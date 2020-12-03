import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.Medio_comunicacion;

import java.util.List;

public class Medio_comunicacionORMWS_Test {

    @Test
    public void addMedio_comunicacionTest() throws Exception
    {
        ucab.dsw.servicio.Medio_comunicacionORMWS servicio = new ucab.dsw.servicio.Medio_comunicacionORMWS();
        Medio_comunicacionDto medio_comunicacionDto = new Medio_comunicacionDto();
        medio_comunicacionDto.setNombre( "NombreEj" );
        medio_comunicacionDto.setEstado( "A" );
        Dato_usuarioDto dato_usuario = new Dato_usuarioDto( 1);
        medio_comunicacionDto.setDatoUsuarioDto( dato_usuario );
        Solicitud_estudioDto solicitud_estudio = new Solicitud_estudioDto( 1);
        medio_comunicacionDto.setSolicitudEstudioDto( solicitud_estudio );
        Medio_comunicacionDto resultado = servicio.addMedio_comunicacion( medio_comunicacionDto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void deleteMedio_comunicacionTest() throws Exception{
        ucab.dsw.servicio.Medio_comunicacionORMWS servicio = new ucab.dsw.servicio.Medio_comunicacionORMWS();
        Medio_comunicacionDto resultado = servicio.deleteMedio_comunicacion(1);
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void showMedio_comunicacionsTest() throws Exception{
        ucab.dsw.servicio.Medio_comunicacionORMWS servicio = new ucab.dsw.servicio.Medio_comunicacionORMWS();
        List<Medio_comunicacion> resultado = servicio.showMedio_comunicacions();
        Assert.assertNotEquals(resultado, null);
    }

    @Test
    public void updateMedio_comunicacionTest() throws Exception
    {
        ucab.dsw.servicio.Medio_comunicacionORMWS servicio = new ucab.dsw.servicio.Medio_comunicacionORMWS();
        Medio_comunicacionDto medio_comunicacionDto = new Medio_comunicacionDto();
        medio_comunicacionDto.setNombre( "NombreEjmODIF" );
        medio_comunicacionDto.setEstado( "I" );
        Dato_usuarioDto dato_usuario = new Dato_usuarioDto( 1);
        medio_comunicacionDto.setDatoUsuarioDto( dato_usuario );
        Solicitud_estudioDto solicitud_estudio = new Solicitud_estudioDto( 1);
        medio_comunicacionDto.setSolicitudEstudioDto( solicitud_estudio );
        Medio_comunicacionDto resultado = servicio.updateMedio_comunicacion( 1, medio_comunicacionDto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }
}
