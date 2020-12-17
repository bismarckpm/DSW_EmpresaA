import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Usuario;

import java.util.List;

public class Mailer_Test {

    @Test
    public void sendMailTest() throws Exception{
        ucab.dsw.servicio.SendMailSSL servicio = new ucab.dsw.servicio.SendMailSSL();
        servicio.enviar("alejandroandrade1981@gmail.com", "203232");
    }

    @Test
    public void sendCorreoConCodigoTest() throws Exception{
        ucab.dsw.servicio.Mailer servicio = new ucab.dsw.servicio.Mailer();
        servicio.generarCodigoRecuperacion("alejandroandrade1981@gmail.com");
    }

    @Test
    public void validarCodigoTest() throws Exception{
        ucab.dsw.servicio.Mailer servicio = new ucab.dsw.servicio.Mailer();
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setCorreo("alejandroandrade1981@gmail.com");
        usuarioDto.setCodigoRecuperacion("67438");
        servicio.validarCodigo(usuarioDto);
    }
}
