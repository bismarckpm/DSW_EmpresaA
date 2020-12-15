import ucab.dsw.dtos.Producto_presentacion_tipoDto;
import ucab.dsw.dtos.RolDto;
import ucab.dsw.dtos.Rol_privilegioDto;
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

    @Test
    public void deleteRolTest() throws Exception{

        ucab.dsw.servicio.RolORMWS servicio = new ucab.dsw.servicio.RolORMWS();
        RolDto rolDto = new RolDto(1);
        RolDto resultado = servicio.deleteRol(rolDto);
        Assert.assertNotEquals( resultado.getId(), 0 );

    }
}
