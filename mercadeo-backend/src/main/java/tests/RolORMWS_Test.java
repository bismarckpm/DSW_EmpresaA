package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import dtos.RolDto;
import entidades.Rol;
import java.util.List;

public class RolORMWS_Test {

    @Test
    public void addRolTest() throws Exception {
        servicios.RolORMWS servicio = new servicios.RolORMWS();
        RolDto rolDto = new RolDto();
        rolDto.setNombre( "Rol1" );
        rolDto.setEstado( "A" );
        rolDto.setDescripcion( "Descripcion de ejemplo" );
        RolDto resultado = servicio.addRol( rolDto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void showRolTest() throws Exception
    {
        servicios.RolORMWS servicio = new servicios.RolORMWS();
        List<Rol> rols = servicio.showRol();
        Assert.assertFalse("Consulta Realizada con Exito",rols.isEmpty());
    }

    @Test
    public void updateRolTest() throws Exception{

        servicios.RolORMWS servicio = new servicios.RolORMWS();
        RolDto rolDto = new RolDto(1);
        rolDto.setNombre( "Rol2" );
        rolDto.setEstado( "I" );
        rolDto.setDescripcion( "Descripcion modificada" );
        RolDto resultado = servicio.editRol (rolDto);
        Assert.assertNotEquals( resultado.getId(), 0);
    }

    @Test
    public void deleteRolTest() throws Exception{

        servicios.RolORMWS servicio = new servicios.RolORMWS();
        RolDto rolDto = new RolDto(1);
        RolDto resultado = servicio.deleteRol(rolDto);
        Assert.assertNotEquals( resultado.getId(), 0 );

    }
}
