import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.PresentacionDto;
import ucab.dsw.dtos.PrivilegioDto;
import ucab.dsw.dtos.Rol_privilegioDto;
import ucab.dsw.dtos.TipoDto;
import ucab.dsw.entidades.Producto;
import ucab.dsw.entidades.Rol;
import ucab.dsw.entidades.Rol_privilegio;

import java.util.List;

public class Rol_privilegioORMWS_Test {

    @Test
    public void addRol_privilegioTest() throws Exception {
        ucab.dsw.servicio.Rol_privilegioORMWS servicio = new ucab.dsw.servicio.Rol_privilegioORMWS();
        Rol_privilegioDto rol_privilegioDto = new Rol_privilegioDto();
        PrivilegioDto privilegio = new PrivilegioDto( 1);
        rol_privilegioDto.setPrivilegioDto( privilegio );
        Rol producto = new Rol( 1);
        rol_privilegioDto.setEstado( "A" );
        Rol_privilegioDto resultado = servicio.addRol_privilegio( rol_privilegioDto, producto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void showRol_privilegioTest() throws Exception
    {
        ucab.dsw.servicio.Rol_privilegioORMWS servicio = new ucab.dsw.servicio.Rol_privilegioORMWS();
        List<Rol_privilegio> rol_privilegios = servicio.showRol_privilegio();
        Assert.assertFalse("Consulta Realizada con Exito",rol_privilegios.isEmpty());
    }

    @Test
    public void updateRol_privilegioTest() throws Exception{

        ucab.dsw.servicio.Rol_privilegioORMWS servicio = new ucab.dsw.servicio.Rol_privilegioORMWS();
        Rol_privilegioDto rol_privilegioDto = new Rol_privilegioDto(1);
        PrivilegioDto privilegio = new PrivilegioDto( 1);
        rol_privilegioDto.setPrivilegioDto( privilegio );
        Rol producto = new Rol( 1);
        rol_privilegioDto.setEstado( "I" );
        Rol_privilegioDto resultado = servicio.editRol_privilegio (rol_privilegioDto, producto);
        Assert.assertNotEquals( resultado.getId(), 0);
    }

    @Test
    public void deleteRol_privilegioTest() throws Exception{

        ucab.dsw.servicio.Rol_privilegioORMWS servicio = new ucab.dsw.servicio.Rol_privilegioORMWS();
        Rol_privilegioDto rol_privilegioDto = new Rol_privilegioDto(1);
        Rol_privilegioDto resultado = servicio.deleteRol_privilegio(rol_privilegioDto);
        Assert.assertNotEquals( resultado.getId(), 0 );

    }
}
