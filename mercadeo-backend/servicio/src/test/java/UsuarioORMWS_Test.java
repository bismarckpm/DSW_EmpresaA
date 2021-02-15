import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.*;
import ucab.dsw.entidades.Response.ListaEncuestasE;
import ucab.dsw.accesodatos.DaoDato_usuario;
import ucab.dsw.accesodatos.DaoRol;
import ucab.dsw.entidades.Response.UsuarioResponse;
import ucab.dsw.servicio.UsuarioORMWS;

import javax.ws.rs.core.Response;
import java.util.List;

public class UsuarioORMWS_Test {


    UsuarioORMWS servicio = new UsuarioORMWS();

    UsuarioDto usuario = new UsuarioDto();

    UsuarioDto usuarioUpdate = new UsuarioDto();

    LoginDto loginDto = new LoginDto();

    @Before
    public void setup() throws Exception {

        RolDto rol = new RolDto(3);
        //Dato_usuarioDto datoUsuario = new Dato_usuarioDto();

        usuario.setRolDto(rol);
       // usuario.setDatoUsuarioDto(datoUsuario);
        usuario.setPassword("1234");
        usuario.setCorreo("aux@gmail.com");
        usuario.setNombreUsuario("aux");

        usuarioUpdate.setId(40);
        usuarioUpdate.setNombreUsuario("auxilio");


        loginDto.setPassword("1234");
        loginDto.setEmail("aux@gmail.com");

    }

    /**
     * Este test prueba el registro de un usuario
     *
     */
    @Test
    public void createTest() throws Exception {
        Response resultado = servicio.create( usuario);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Usuario usuario = (Usuario) responseDto.getObjeto();
        Assert.assertNotEquals( usuario.get_id(), 0  );

    }

    /**
     * Este test prueba la autenticación de un usuario
     *
     */
    @Test
    public void authenticateTest() throws Exception {

        Response resultado = servicio.authenticate( loginDto);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        UsuarioResponse usuario = (UsuarioResponse) responseDto.getObjeto();
        Assert.assertNotEquals( usuario.getId(), 0  );

    }

    
    /**
     * Este test prueba el cambio de contraseña de un usuario específico
     *
     */

    @Test
    public void cambiarPasswordTest() throws Exception {
        Response resultado = servicio.cambiarPassword(3, "123");
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Usuario usuario = (Usuario) responseDto.getObjeto();
        Assert.assertNotEquals( usuario.get_id(), 0  );

    }


    /**
     * Este test prueba la obtención de la lista de encuestas para un encuestado
     *
     */

    @Test
    public void dashboardEncuestadoTest() throws Exception{
        ucab.dsw.servicio.UsuarioORMWS servicio = new ucab.dsw.servicio.UsuarioORMWS();
        Response resultado = servicio.dashboardEncuestado(30);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Estudio> estudios = (List<Estudio>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",estudios.isEmpty());
    }

    /**
     * TEST DE PRUEBA PARA POPULAR BASE DE DATOS Y LDAP CON 40 USUARIOS
     *
     */
    @Test
    public void popularConUsuariosTest() throws Exception {

        int cont = 1;
        DaoRol daoRol = new DaoRol();
        DaoDato_usuario daoDu = new DaoDato_usuario();
        UsuarioDto usuario = new UsuarioDto();
        long id_rol = 1;
        Rol rol = daoRol.find(id_rol, Rol.class);
        RolDto rolDto = new RolDto(rol.get_id());
        Dato_usuarioDto datoUsuario = null;
        usuario.setDatoUsuarioDto(datoUsuario);
        usuario.setRolDto(rolDto);
        usuario.setPassword("1234");
        usuario.setCorreo("prueba" + cont + "@gmail.com");
        usuario.setNombreUsuario("Usuario" + cont);
        servicio.create(usuario);
        cont++;
        for (cont = 2; cont <= 5; cont++){
            id_rol = 3;
            rol = daoRol.find(id_rol, Rol.class);
            rolDto = new RolDto(rol.get_id());
            datoUsuario = null;
            usuario.setDatoUsuarioDto(datoUsuario);
            usuario.setRolDto(rolDto);
            usuario.setPassword("1234");
            usuario.setCorreo("prueba" + cont + "@gmail.com");
            usuario.setNombreUsuario("Usuario" + cont);
            servicio.create(usuario);
        }
        for (cont = 6; cont <= 8; cont++){
            id_rol = 2;
            rol = daoRol.find(id_rol, Rol.class);
            rolDto = new RolDto(rol.get_id());
            datoUsuario = null;
            usuario.setDatoUsuarioDto(datoUsuario);
            usuario.setRolDto(rolDto);
            usuario.setPassword("1234");
            usuario.setCorreo("prueba" + cont + "@gmail.com");
            usuario.setNombreUsuario("Usuario" + cont);
            servicio.create(usuario);
        }
        long du =1;
        for (cont = 9; cont <= 40; cont++){
            id_rol = 4;
            rol = daoRol.find(id_rol, Rol.class);
            rolDto = new RolDto(rol.get_id());
            Dato_usuario datoUsuarioE = daoDu.find(du, Dato_usuario.class);
            Dato_usuarioDto duDto = new Dato_usuarioDto(datoUsuarioE.get_id());
            usuario.setDatoUsuarioDto(duDto);
            usuario.setRolDto(rolDto);
            usuario.setPassword("1234");
            usuario.setCorreo("prueba" + cont + "@gmail.com");
            usuario.setNombreUsuario("Usuario" + cont);
            servicio.create(usuario);
            du++;
        }
    }

    /**
     * Este test prueba la actualización de un Usuario
     *
     */

    @Test
    public void editUserTest() throws Exception {
        usuario.setId(3);
        Response resultado = servicio.updateUsuario(3, usuario);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Long resultadop = (Long) responseDto.getObjeto();
        Long prueba = 0L;
        Assert.assertNotEquals( resultadop, prueba  );

    }


}
