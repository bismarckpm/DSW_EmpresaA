import org.junit.Test;
import ucab.dsw.dtos.UsuarioDto;

import java.util.List;

public class Mailer_Test {

    /**
     * Este test prueba el envío de un correo electrónico
     *
     */
    @Test
    public void sendMailTest() throws Exception{
        ucab.dsw.servicio.SendMailSSL servicio = new ucab.dsw.servicio.SendMailSSL();
        servicio.enviar("raikjars@gmail.com", "5874dfr");
    }

    /**
     * Este test prueba el envío de un correo electrónico con un código de recuperación
     *
     */
    @Test
    public void sendCorreoConCodigoTest() throws Exception{
        ucab.dsw.servicio.Mailer servicio = new ucab.dsw.servicio.Mailer();
        servicio.generarCodigoRecuperacion("jteixeira1102@gmail.com");
    }

    /**
     * Este test prueba la validación del código de recuperación de contraseña de un usuario
     *
     */
    @Test
    public void validarCodigoTest() throws Exception{
        ucab.dsw.servicio.Mailer servicio = new ucab.dsw.servicio.Mailer();
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setCorreo("jteixeira1102@gmail.com");
        usuarioDto.setCodigoRecuperacion("66874");
        servicio.validarCodigo(usuarioDto);
    }

    /**
     * Este test prueba el cambio de contraseña de un usuario cuando éste desea recuperarla
     *
     */
    @Test
    public void cambiarPasswordCodigoTest() throws Exception{
        ucab.dsw.servicio.Mailer servicio = new ucab.dsw.servicio.Mailer();
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(3);
        usuarioDto.setPassword("PasswordCambiadaConCodigo");
        servicio.cambiarPassWordCodigo(usuarioDto);
    }
}