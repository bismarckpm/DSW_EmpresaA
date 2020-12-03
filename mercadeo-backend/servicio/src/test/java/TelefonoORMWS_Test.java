import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.Response.TelefonoResponse;
import ucab.dsw.dtos.Dato_usuarioDto;
import ucab.dsw.dtos.TelefonoDto;
import ucab.dsw.servicio.TelefonoORMWS;

import java.util.List;

public class TelefonoORMWS_Test {

    TelefonoORMWS telefonoORMWS = new TelefonoORMWS();


    @Test
    public void  createTest() throws Exception {
        TelefonoDto telefonoDto = new TelefonoDto();
        Dato_usuarioDto dato_usuarioDto = new Dato_usuarioDto(18);

        telefonoDto.setNumero("04142386726");
        telefonoDto.setDatoUsuarioDto(dato_usuarioDto);

        TelefonoResponse result = telefonoORMWS.create(telefonoDto);

        Assert.assertEquals("04142386726", result.getNumero());

    }

    @Test
    public void  findAllByIdUserTest() throws Exception {
        List<TelefonoResponse> result = telefonoORMWS.getList(18);
        Assert.assertNotEquals(0, result.size());
    }

    @Test
    public void  updateTest() throws Exception {
        TelefonoDto telefonoDto = new TelefonoDto(1);

        telefonoDto.setNumero("0212121212");
        TelefonoResponse result = telefonoORMWS.update(telefonoDto);

        Assert.assertEquals("0212121212", result.getNumero());
    }

    @Test
    public void  deleteTest() throws Exception {
        TelefonoResponse result = telefonoORMWS.delete(1);
        Assert.assertEquals("I", result.getEstado());

    }
}
