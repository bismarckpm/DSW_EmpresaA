import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.Dato_usuarioDto;
import ucab.dsw.dtos.TelefonoDto;
import ucab.dsw.dtos.TelefonoDto;
import ucab.dsw.entidades.Telefono;
import ucab.dsw.servicio.TelefonoORMWS;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TelefonoORMWS_Test {

    @Test
    public void addTelefonoTest() throws Exception
    {
        ucab.dsw.servicio.TelefonoORMWS servicio = new ucab.dsw.servicio.TelefonoORMWS();

        Dato_usuarioDto dato_usuario = new Dato_usuarioDto( 1);

        TelefonoDto telefono1 = new TelefonoDto();
        telefono1.setNumero( "04241136422" );
        telefono1.setEstado( "A" );
        telefono1.setDatoUsuarioDto( dato_usuario );

        TelefonoDto telefono2 = new TelefonoDto();
        telefono1.setNumero( "04241133574" );
        telefono2.setEstado( "A" );
        telefono2.setDatoUsuarioDto( dato_usuario );

        TelefonoDto telefono3 = new TelefonoDto();
        telefono1.setNumero( "04242636422" );
        telefono3.setEstado( "A" );
        telefono3.setDatoUsuarioDto( dato_usuario );

        List<TelefonoDto> telefonos = new ArrayList<TelefonoDto>();

        telefonos.add(telefono1);
        telefonos.add(telefono2);
        telefonos.add(telefono3);

        TelefonoDto resultado = servicio.addTelefono( telefonos );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void deleteTelefonoTest() throws Exception{
        ucab.dsw.servicio.TelefonoORMWS servicio = new ucab.dsw.servicio.TelefonoORMWS();
        TelefonoDto resultado = servicio.deleteTelefono(1);
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void showTelefonosTest() throws Exception{
        ucab.dsw.servicio.TelefonoORMWS servicio = new ucab.dsw.servicio.TelefonoORMWS();
        List<Telefono> resultado = servicio.showTelefonos();
        Assert.assertNotEquals(resultado, null);
    }

    @Test
    public void updateTelefonoTest() throws Exception
    {
        ucab.dsw.servicio.TelefonoORMWS servicio = new ucab.dsw.servicio.TelefonoORMWS();
        Dato_usuarioDto dato_usuario = new Dato_usuarioDto( 1);

        TelefonoDto telefono1 = new TelefonoDto(1);
        telefono1.setNumero( "04241136422" );
        telefono1.setEstado( "A" );
        telefono1.setDatoUsuarioDto( dato_usuario );

        TelefonoDto telefono2 = new TelefonoDto(2);
        telefono1.setNumero( "04241133574" );
        telefono2.setEstado( "A" );
        telefono2.setDatoUsuarioDto( dato_usuario );

        TelefonoDto telefono3 = new TelefonoDto(3);
        telefono1.setNumero( "04242636422" );
        telefono3.setEstado( "A" );
        telefono3.setDatoUsuarioDto( dato_usuario );

        List<TelefonoDto> telefonos = new ArrayList<TelefonoDto>();

        telefonos.add(telefono1);
        telefonos.add(telefono2);
        telefonos.add(telefono3);

        TelefonoDto resultado = servicio.updateTelefono( 1, telefonos );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }
    
}
