import org.junit.Assert;
import org.junit.Test;

import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.dtos.TipoDto;
import ucab.dsw.dtos.Dato_usuarioDto;
import ucab.dsw.dtos.TipoDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Producto;
import ucab.dsw.entidades.Tipo;
import ucab.dsw.servicio.TipoORMWS;

import javax.ws.rs.core.Response;
import java.util.List;

public class TipoORMWS_Test {

    @Test
    public void addTipoTest() throws Exception {
        ucab.dsw.servicio.TipoORMWS servicio = new ucab.dsw.servicio.TipoORMWS();
        TipoDto tipoDto = new TipoDto();
        tipoDto.setNombre( "Tipo1" );
        tipoDto.setEstado( "A" );
        tipoDto.setDescripcion("DescEj");
        Response resultado = servicio.addTipo( tipoDto );
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Tipo tipo = (Tipo) responseDto.getObjeto();
        Assert.assertNotEquals( tipo.get_id(), 0  );
    }

    @Test
    public void showTipoTest() throws Exception
    {
        ucab.dsw.servicio.TipoORMWS servicio = new ucab.dsw.servicio.TipoORMWS();
        Response resultado = servicio.showTipo();
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Tipo> tipos = (List<Tipo>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",tipos.isEmpty());
    }

    @Test
    public void updateTipoTest() throws Exception{

        ucab.dsw.servicio.TipoORMWS servicio = new ucab.dsw.servicio.TipoORMWS();
        TipoDto tipoDto = new TipoDto(1);
        tipoDto.setNombre( "Tipo2" );
        tipoDto.setEstado( "I" );
        tipoDto.setDescripcion("DescEj");
        Response resultado = servicio.editTipo (tipoDto);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Tipo tipo = (Tipo) responseDto.getObjeto();
        System.out.println(tipo.get_id());
        Assert.assertNotEquals( tipo.get_id(), 0);
    }

    /**
     * Este test prueba la obtención de un tipo especifico
     *
     */
    @Test
    public void consultarTipoTest() throws Exception{
        ucab.dsw.servicio.TipoORMWS servicio = new ucab.dsw.servicio.TipoORMWS();
        Response resultado = servicio.consultarTipo(1);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Tipo tipo = (Tipo) responseDto.getObjeto();
        System.out.println(tipo.get_id());
        Assert.assertNotEquals(tipo, null);
    }
}
