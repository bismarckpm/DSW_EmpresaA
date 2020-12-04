import ucab.dsw.dtos.PrivilegioDto;
import ucab.dsw.entidades.Privilegio;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PrivilegioORMWS_Test {

    @Test
    public void addPrivilegioTest() throws Exception {
        ucab.dsw.servicio.PrivilegioORMWS servicio = new ucab.dsw.servicio.PrivilegioORMWS();
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
        ucab.dsw.servicio.PrivilegioORMWS servicio = new ucab.dsw.servicio.PrivilegioORMWS();
        List<Privilegio> privilegios = servicio.showPrivilegio();
        Assert.assertFalse("Consulta Realizada con Exito",privilegios.isEmpty());
    }

    @Test
    public void updatePrivilegioTest() throws Exception{

        ucab.dsw.servicio.PrivilegioORMWS servicio = new ucab.dsw.servicio.PrivilegioORMWS();
        PrivilegioDto privilegioDto = new PrivilegioDto(1);
        privilegioDto.setTipoAccion( "Privilegio2" );
        privilegioDto.setEstado( "I" );
        privilegioDto.setDescripcion( "Descripcion modificada" );
        PrivilegioDto resultado = servicio.editPrivilegio (privilegioDto);
        Assert.assertNotEquals( resultado.getId(), 0);
    }

    @Test
    public void deletePrivilegioTest() throws Exception{

        ucab.dsw.servicio.PrivilegioORMWS servicio = new ucab.dsw.servicio.PrivilegioORMWS();
        PrivilegioDto privilegioDto = new PrivilegioDto(1);
        PrivilegioDto resultado = servicio.deletePrivilegio(privilegioDto);
        Assert.assertNotEquals( resultado.getId(), 0 );

    }
}
