import ucab.dsw.dtos.RolDto;
import ucab.dsw.entidades.Rol;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class RolORMWS_Test {

    @Test
    public void addRolTest() throws Exception {
        ucab.dsw.servicio.RolORMWS servicio = new ucab.dsw.servicio.RolORMWS();
        RolDto rolDto = new RolDto();
        rolDto.setNombre( "Rol1" );
        rolDto.setEstado( "A" );
        rolDto.setDescripcion( "Descripcion de ejemplo" );
        RolDto resultado = servicio.addRol( rolDto);
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void showRolTest() throws Exception
    {
        ucab.dsw.servicio.RolORMWS servicio = new ucab.dsw.servicio.RolORMWS();
        List<Rol> rols = servicio.showRol();
        Assert.assertFalse("Consulta Realizada con Exito",rols.isEmpty());
    }

    @Test
    public void updateRolTest() throws Exception{

        ucab.dsw.servicio.RolORMWS servicio = new ucab.dsw.servicio.RolORMWS();
        RolDto rolDto = new RolDto(1);
        rolDto.setNombre( "Rol2" );
        rolDto.setEstado( "I" );
        rolDto.setDescripcion( "Descripcion modificada" );
        RolDto resultado = servicio.editRol (rolDto);
        Assert.assertNotEquals( resultado.getId(), 0);
    }

    /**
     * Este test prueba la obtención de un rol especifico
     *
     */
    @Test
    public void consultarRolTest() throws Exception{
        ucab.dsw.servicio.RolORMWS servicio = new ucab.dsw.servicio.RolORMWS();
        Rol resultado = servicio.consultarRol(1);
        Assert.assertNotEquals(resultado, null);
    }
}
