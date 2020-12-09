import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.Hijo;

import java.util.List;

public class HijoORMWS_Test {

    @Test
    public void addHijoTest() throws Exception
    {
        ucab.dsw.servicio.HijoORMWS servicio = new ucab.dsw.servicio.HijoORMWS();
        HijoDto hijoDto = new HijoDto();
        java.util.Date fecha = new java.util.Date("21/11/2020");
        hijoDto.setFechaNacimiento( fecha );
        hijoDto.setGenero( "M" );
        hijoDto.setEstado( "A" );
        Dato_usuarioDto dato_usuario = new Dato_usuarioDto( 1);
        hijoDto.setDatoUsuarioDto( dato_usuario );
        HijoDto resultado = servicio.addHijo( hijoDto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void deleteHijoTest() throws Exception{
        ucab.dsw.servicio.HijoORMWS servicio = new ucab.dsw.servicio.HijoORMWS();
        HijoDto resultado = servicio.deleteHijo(1);
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void showHijosTest() throws Exception{
        ucab.dsw.servicio.HijoORMWS servicio = new ucab.dsw.servicio.HijoORMWS();
        List<Hijo> resultado = servicio.showHijos();
        Assert.assertNotEquals(resultado, null);
    }

    @Test
    public void updateHijoTest() throws Exception
    {
        ucab.dsw.servicio.HijoORMWS servicio = new ucab.dsw.servicio.HijoORMWS();
        HijoDto hijoDto = new HijoDto();
        java.util.Date fecha = new java.util.Date("21/12/2020");
        hijoDto.setFechaNacimiento( fecha );
        hijoDto.setGenero( "F" );
        hijoDto.setEstado( "I" );
        Dato_usuarioDto dato_usuario = new Dato_usuarioDto( 2);
        hijoDto.setDatoUsuarioDto( dato_usuario );
        HijoDto resultado = servicio.updateHijo( 1, hijoDto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }
}
