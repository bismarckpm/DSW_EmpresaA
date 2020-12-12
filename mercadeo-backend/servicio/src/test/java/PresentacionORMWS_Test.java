import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.Presentacion;

import java.util.List;

public class PresentacionORMWS_Test {

    @Test
    public void addPresentacionTest() throws Exception
    {
        ucab.dsw.servicio.PresentacionORMWS servicio = new ucab.dsw.servicio.PresentacionORMWS();
        PresentacionDto presentacionDto = new PresentacionDto();
        presentacionDto.setTitulo( "TituloEj" );
        presentacionDto.setCaracteristicas( "CaracaEj" );
        presentacionDto.setEstado( "A" );
        ProductoDto producto = new ProductoDto( 1);
        PresentacionDto resultado = servicio.addPresentacion( presentacionDto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void deletePresentacionTest() throws Exception{
        ucab.dsw.servicio.PresentacionORMWS servicio = new ucab.dsw.servicio.PresentacionORMWS();
        PresentacionDto resultado = servicio.deletePresentacion(1);
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void showPresentacionesTest() throws Exception{
        ucab.dsw.servicio.PresentacionORMWS sssservicio = new ucab.dsw.servicio.PresentacionORMWS();
        List<Presentacion> resultado = sssservicio.showPresentaciones();
        Assert.assertNotEquals(resultado, null);
    }

    @Test
    public void updatePresentacionTest() throws Exception
    {
        ucab.dsw.servicio.PresentacionORMWS servicio = new ucab.dsw.servicio.PresentacionORMWS();
        PresentacionDto presentacionDto = new PresentacionDto();
        presentacionDto.setTitulo( "TituloModif" );
        presentacionDto.setCaracteristicas( "CaracaModif" );
        presentacionDto.setEstado( "I" );
<<<<<<< HEAD
=======
        ProductoDto producto = new ProductoDto( 2);
>>>>>>> 50c9f9f1154c01d68d1ab5f3532d72268b51fecc
        PresentacionDto resultado = servicio.updatePresentacion( 1, presentacionDto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }
}
