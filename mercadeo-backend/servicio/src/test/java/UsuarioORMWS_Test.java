import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.Response.ListaEncuestasE;
import ucab.dsw.accesodatos.DaoDato_usuario;
import ucab.dsw.accesodatos.DaoRol;
import ucab.dsw.dtos.Dato_usuarioDto;
import ucab.dsw.dtos.LoginDto;
import ucab.dsw.dtos.RolDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Dato_usuario;
import ucab.dsw.entidades.Rol;
import ucab.dsw.servicio.UsuarioORMWS;

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
        loginDto.setEmail("auxi@gmail.com");

    }

    @Test
    public void create30UsuariosTest() throws Exception {

        int cont = 1;
        long val =0;
        long du = 1;
        DaoRol daoRol = new DaoRol();
        DaoDato_usuario daoDu = new DaoDato_usuario();
        for (cont = 100; cont <= 130; cont++){
            UsuarioDto usuario = new UsuarioDto();
            val++;
            Rol rol = daoRol.find(val, Rol.class);
            RolDto rolDto = new RolDto(rol.get_id());
            if (val == 4){
                Dato_usuario datoUsuario = daoDu.find(du, Dato_usuario.class);
                Dato_usuarioDto duDto = new Dato_usuarioDto(datoUsuario.get_id());
                usuario.setDatoUsuarioDto(duDto);
                du++;
                val=0;
            }
            else{
                Dato_usuarioDto datoUsuario = null;
                usuario.setDatoUsuarioDto(datoUsuario);
            }
            usuario.setRolDto(rolDto);
            usuario.setPassword("1234");
            usuario.setCorreo("prueba" + cont + "@gmail.com");
            usuario.setNombreUsuario("Usuario" + cont);
            Assert.assertEquals(usuario.getCorreo(), servicio.create(usuario).get_correo());
        }

    }


    @Test
    public void createTest() throws Exception {

        Assert.assertEquals(usuario.getCorreo(), servicio.create(usuario).get_correo());

    }

    @Test
    public void authenticateTest() throws Exception {

       Assert.assertNotNull(servicio.authenticate(loginDto));

    }

    /**
     * Este test prueba el cambio de contraseña de un usuario específico
     *
     */
    @Test
    public void cambiarPasswordTest() throws Exception {

        Assert.assertNotNull(servicio.cambiarPassword(3, "123"));

    }

    /**
     * Este test prueba la obtención de la lista de encuestas para un encuestado
     *
     */
    @Test
    public void dashboardEncuestadoTest() throws Exception{
        ucab.dsw.servicio.UsuarioORMWS servicio = new ucab.dsw.servicio.UsuarioORMWS();
        List<Estudio> resultado = servicio.dashboardEncuestado(30);
        Assert.assertNotEquals(resultado, null);
    }

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
        Assert.assertEquals(usuario.getCorreo(), servicio.create(usuario).get_correo());
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
            Assert.assertEquals(usuario.getCorreo(), servicio.create(usuario).get_correo());
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
            Assert.assertEquals(usuario.getCorreo(), servicio.create(usuario).get_correo());
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
            Assert.assertEquals(usuario.getCorreo(), servicio.create(usuario).get_correo());
            du++;
        }
    }


}
