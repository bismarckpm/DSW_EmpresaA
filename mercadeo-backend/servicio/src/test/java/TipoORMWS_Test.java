import org.junit.Assert;
import org.junit.Test;

import ucab.dsw.dtos.TipoDto;
import ucab.dsw.dtos.Dato_usuarioDto;
import ucab.dsw.dtos.TipoDto;
import ucab.dsw.entidades.Tipo;
import ucab.dsw.servicio.TipoORMWS;

import java.util.List;

public class TipoORMWS_Test {

    @Test
    public void addTipoTest() throws Exception {
        ucab.dsw.servicio.TipoORMWS servicio = new ucab.dsw.servicio.TipoORMWS();
        TipoDto tipoDto = new TipoDto();
        tipoDto.setNombre( "Tipo1" );
        tipoDto.setEstado( "A" );
        TipoDto resultado = servicio.addTipo( tipoDto );
        System.out.println(resultado.getId());
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void showTipoTest() throws Exception
    {
        ucab.dsw.servicio.TipoORMWS servicio = new ucab.dsw.servicio.TipoORMWS();
        List<Tipo> tipos = servicio.showTipo();
        Assert.assertFalse("Consulta Realizada con Exito",tipos.isEmpty());
    }

    @Test
    public void updateTipoTest() throws Exception{

        ucab.dsw.servicio.TipoORMWS servicio = new ucab.dsw.servicio.TipoORMWS();
        TipoDto tipoDto = new TipoDto(1);
        tipoDto.setNombre( "Tipo2" );
        tipoDto.setEstado( "I" );
        TipoDto resultado = servicio.editTipo (tipoDto);
        Assert.assertNotEquals( resultado.getId(), 0);
    }

    @Test
    public void deleteTipoTest() throws Exception{

        ucab.dsw.servicio.TipoORMWS servicio = new ucab.dsw.servicio.TipoORMWS();
        TipoDto tipoDto = new TipoDto(1);
        TipoDto resultado = servicio.deleteTipo(tipoDto);
        Assert.assertNotEquals( resultado.getId(), 0 );

    }
}
