package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import dtos.PrivilegioDto;
import entidades.Privilegio;
import java.util.List;

public class PrivilegioORMWS_Test {

    @Test
    public void addPrivilegioTest() throws Exception {
        servicios.PrivilegioORMWS servicio = new servicios.PrivilegioORMWS();
        PrivilegioDto privilegioDto = new PrivilegioDto();
        privilegioDto.setTipoAccion( "Privilegio1" );
        privilegioDto.setEstado( "A" );
        privilegioDto.setDescripcion( "Descripcion de ejemplo" );
        PrivilegioDto resultado = servicio.addPrivilegio( privilegioDto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void showPrivilegioTest() throws Exception
    {
        servicios.PrivilegioORMWS servicio = new servicios.PrivilegioORMWS();
        List<Privilegio> privilegios = servicio.showPrivilegio();
        Assert.assertFalse("Consulta Realizada con Exito",privilegios.isEmpty());
    }

    @Test
    public void updatePrivilegioTest() throws Exception{

        servicios.PrivilegioORMWS servicio = new servicios.PrivilegioORMWS();
        PrivilegioDto privilegioDto = new PrivilegioDto(1);
        privilegioDto.setTipoAccion( "Privilegio2" );
        privilegioDto.setEstado( "I" );
        privilegioDto.setDescripcion( "Descripcion modificada" );
        PrivilegioDto resultado = servicio.editPrivilegio (privilegioDto);
        Assert.assertNotEquals( resultado.getId(), 0);
    }

    @Test
    public void deletePrivilegioTest() throws Exception{

        servicios.PrivilegioORMWS servicio = new servicios.PrivilegioORMWS();
        PrivilegioDto privilegioDto = new PrivilegioDto(1);
        PrivilegioDto resultado = servicio.deletePrivilegio(privilegioDto);
        Assert.assertNotEquals( resultado.getId(), 0 );

    }
}
