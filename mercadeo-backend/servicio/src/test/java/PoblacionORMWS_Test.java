import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.dtos.OcupacionDto;
import ucab.dsw.dtos.PoblacionDto;
import ucab.dsw.dtos.UsuarioDto;

public class PoblacionORMWS_Test {

    /**
     * Este test prueba el registro de una nueva ocupación
     *
     */
    @Test
    public void addPoblacionTest() throws Exception {
        ucab.dsw.servicio.PoblacionORMWS servicio = new ucab.dsw.servicio.PoblacionORMWS();
        UsuarioDto usuarioDto = new UsuarioDto(8);
        EstudioDto estudioDto = new EstudioDto(1);

        PoblacionDto poblacionDto = new PoblacionDto();
        poblacionDto.setEstado( "A" );
        poblacionDto.setEstudio(estudioDto);
        poblacionDto.setUsuario(usuarioDto);
        PoblacionDto resultado = servicio.addPoblacion( poblacionDto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    /**
     * Este test prueba la actualización de una ocupación
     *
     */
    @Test
    public void editPoblacionTest() throws Exception{

        ucab.dsw.servicio.PoblacionORMWS servicio = new ucab.dsw.servicio.PoblacionORMWS();
        PoblacionDto poblacionDto = new PoblacionDto(3);

        UsuarioDto usuarioDto = new UsuarioDto(8);
        EstudioDto estudioDto = new EstudioDto(2);

        poblacionDto.setEstado( "I" );
        poblacionDto.setEstudio(estudioDto);
        poblacionDto.setUsuario(usuarioDto);

        PoblacionDto resultado = servicio.editPoblacion (poblacionDto);
        Assert.assertNotEquals( resultado.getId(), 0);
    }

}
