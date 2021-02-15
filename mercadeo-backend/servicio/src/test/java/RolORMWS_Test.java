import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.dtos.RolDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Rol;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.List;

public class RolORMWS_Test {

    /**
     * Este test prueba el registro de un rol
     *
     */
    @Test
    public void addRolTest() throws Exception {
        ucab.dsw.servicio.RolORMWS servicio = new ucab.dsw.servicio.RolORMWS();
        RolDto rolDto = new RolDto();
        rolDto.setNombre( "Rol1" );
        rolDto.setEstado( "A" );
        rolDto.setDescripcion( "Descripcion de ejemplo" );
        Response resultado = servicio.addRol( rolDto);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Rol rol = (Rol) responseDto.getObjeto();
        Assert.assertNotEquals( rol.get_id(), 0  );
    }

    /**
     * Este test prueba la consulta de todos los roles
     *
     */
    @Test
    public void showRolTest() throws Exception
    {
        ucab.dsw.servicio.RolORMWS servicio = new ucab.dsw.servicio.RolORMWS();
        Response resultado = servicio.showRol();
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Rol> rols = (List<Rol>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",rols.isEmpty());
    }

    /**
     * Este test prueba la actualización de un rol
     *
     */
    @Test
    public void updateRolTest() throws Exception{

        ucab.dsw.servicio.RolORMWS servicio = new ucab.dsw.servicio.RolORMWS();
        RolDto rolDto = new RolDto(1);
        rolDto.setNombre( "Rol2" );
        rolDto.setEstado( "I" );
        rolDto.setDescripcion( "Descripcion modificada" );
        Response resultado = servicio.editRol (rolDto);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Rol rol = (Rol) responseDto.getObjeto();
        System.out.println(rol.get_id());
        Assert.assertNotEquals( rol.get_id(), 0);
    }

    /**
     * Este test prueba la obtención de un rol especifico
     *
     */
    @Test
    public void consultarRolTest() throws Exception{
        ucab.dsw.servicio.RolORMWS servicio = new ucab.dsw.servicio.RolORMWS();
        Response resultado = servicio.consultarRol(1);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Rol rol = (Rol) responseDto.getObjeto();
        System.out.println(rol.get_id());
        Assert.assertNotEquals(rol, null);
    }
}
