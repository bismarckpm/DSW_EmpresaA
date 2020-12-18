import org.junit.Test;
import ucab.dsw.dtos.UsuarioDto;

import java.util.List;

public class Mailer_Test {

    @Test
    public void sendMailTest() throws Exception{
        ucab.dsw.servicio.SendMailSSL servicio = new ucab.dsw.servicio.SendMailSSL();
        servicio.enviar("jteixeira1102@gmail.com", "5874dfr");
    }

    @Test
    public void sendCorreoConCodigoTest() throws Exception{
        ucab.dsw.servicio.Mailer servicio = new ucab.dsw.servicio.Mailer();
        servicio.generarCodigoRecuperacion("jteixeira1102@gmail.com");
    }

    @Test
    public void validarCodigoTest() throws Exception{
        ucab.dsw.servicio.Mailer servicio = new ucab.dsw.servicio.Mailer();
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setCorreo("jteixeira1102@gmail.com");
        usuarioDto.setCodigoRecuperacion("66874");
        servicio.validarCodigo(usuarioDto);
    }

    @Test
    public void cambiarPasswordCodigoTest() throws Exception{
        ucab.dsw.servicio.Mailer servicio = new ucab.dsw.servicio.Mailer();
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(3);
        usuarioDto.setPassword("PasswordCambiadaConCodigo");
        servicio.cambiarPassWordCodigo(usuarioDto);
    }
}