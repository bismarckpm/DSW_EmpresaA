import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.dtos.PresentacionDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Presentacion;

import javax.ws.rs.core.Response;
import java.util.List;

public class PresentacionORMWS_Test {

    /**
     * Este test prueba el registro de una nueva categoría
     *
     */
    @Test
    public void addPresentacionTest() {
        ucab.dsw.servicio.PresentacionORMWS servicio = new ucab.dsw.servicio.PresentacionORMWS();
        PresentacionDto presentacionDto = new PresentacionDto();
        presentacionDto.setTitulo( "Presentacion1" );
        presentacionDto.setCaracteristicas("Caracter1");
        presentacionDto.setEstado( "A" );
        Response resultado = servicio.addPresentacion( presentacionDto );
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Presentacion presentacion = (Presentacion) responseDto.getObjeto();
        Assert.assertNotEquals( presentacion.get_id(), 0  );
    }

    /**
     * Este test prueba la obtención de todas las categorías registradas
     *
     */
    @Test
    public void showPresentacionTest()
    {
        ucab.dsw.servicio.PresentacionORMWS servicio = new ucab.dsw.servicio.PresentacionORMWS();
        Response resultado = servicio.showPresentaciones();
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Presentacion> presentacions = (List<Presentacion>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",presentacions.isEmpty());
    }

    /**
     * Este test prueba la actualización de una categoría
     *
     */
    @Test
    public void updatePresentacionTest() throws Exception{

        ucab.dsw.servicio.PresentacionORMWS servicio = new ucab.dsw.servicio.PresentacionORMWS();
        PresentacionDto presentacionDto = new PresentacionDto(2);
        presentacionDto.setTitulo( "Presentacion2" );
        presentacionDto.setCaracteristicas("CarUpdate");
        presentacionDto.setEstado( "I" );
        Response resultado = servicio.updatePresentacion (2,presentacionDto);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Presentacion presentacion = (Presentacion) responseDto.getObjeto();
        System.out.println(presentacion.get_id());
        Assert.assertNotEquals( presentacion.get_id(), 0);
    }

    /**
     * Este test prueba la obtención de una presentacion especifica
     *
     */
    @Test
    public void consultarPresentacionTest() throws Exception{
        ucab.dsw.servicio.PresentacionORMWS servicio = new ucab.dsw.servicio.PresentacionORMWS();
        Response resultado = servicio.consultarPresentacion(1);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Presentacion presentacion = (Presentacion) responseDto.getObjeto();
        System.out.println(presentacion.get_id());
        Assert.assertNotEquals(presentacion, null);
    }
}
