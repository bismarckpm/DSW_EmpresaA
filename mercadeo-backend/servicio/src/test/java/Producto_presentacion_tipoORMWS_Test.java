import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.*;

import javax.ws.rs.core.Response;
import java.util.List;

public class Producto_presentacion_tipoORMWS_Test {

    /**
     * Este test prueba el registro de una nueva producto_presentación_tipo
     *
     */
    @Test
    public void addProducto_presentacion_tipoTest() throws Exception {
        ucab.dsw.servicio.Producto_presentacion_tipoORMWS servicio = new ucab.dsw.servicio.Producto_presentacion_tipoORMWS();
        Producto_presentacion_tipoDto producto_presentacion_tipoDto = new Producto_presentacion_tipoDto();
        PresentacionDto presentacion = new PresentacionDto( 1);
        producto_presentacion_tipoDto.setPresentacionDto( presentacion );
        TipoDto tipo = new TipoDto( 1);
        producto_presentacion_tipoDto.setTipoDto( tipo );
        ProductoDto producto = new ProductoDto( 1);
        producto_presentacion_tipoDto.setProductoDto(producto);
        producto_presentacion_tipoDto.setEstado( "A" );
        Response resultado = servicio.addProducto_presentacion_tipo( producto_presentacion_tipoDto);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Producto_presentacion_tipo producto_presentacion_tipo = (Producto_presentacion_tipo) responseDto.getObjeto();
        Assert.assertNotEquals( producto_presentacion_tipo.get_id(), 0  );
    }

    /**
     * Este test prueba la consulta de todos los producto_presentación_tipo
     *
     */
    @Test
    public void showProducto_presentacion_tipoTest() throws Exception
    {
        ucab.dsw.servicio.Producto_presentacion_tipoORMWS servicio = new ucab.dsw.servicio.Producto_presentacion_tipoORMWS();
        Response resultado = servicio.showProducto_presentacion_tipo();
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Producto_presentacion_tipo> producto_presentacion_tipos = (List<Producto_presentacion_tipo>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",producto_presentacion_tipos.isEmpty());
    }


    /**
     * Este test prueba la actualización de un producto_presentación_tipo
     *
     */
    @Test
    public void updateProducto_presentacion_tipoTest() throws Exception{

        ucab.dsw.servicio.Producto_presentacion_tipoORMWS servicio = new ucab.dsw.servicio.Producto_presentacion_tipoORMWS();
        Producto_presentacion_tipoDto producto_presentacion_tipoDto = new Producto_presentacion_tipoDto(1);
        PresentacionDto presentacion = new PresentacionDto( 1);
        producto_presentacion_tipoDto.setPresentacionDto( presentacion );
        TipoDto tipo = new TipoDto( 1);
        producto_presentacion_tipoDto.setTipoDto( tipo );
        ProductoDto producto = new ProductoDto( 1);
        producto_presentacion_tipoDto.setProductoDto(producto);
        producto_presentacion_tipoDto.setEstado( "I" );
        Response resultado = servicio.editProducto_presentacion_tipo (producto_presentacion_tipoDto);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Producto_presentacion_tipo producto_presentacion_tipos = (Producto_presentacion_tipo) responseDto.getObjeto();
        Assert.assertNotEquals( producto_presentacion_tipos.get_id(), 0  );
    }

    /**
     * Este test prueba la obtención de un Producto_presentacion_tipo especifico
     *
     */
    @Test
    public void consultarProducto_presentacion_tipoTest() throws Exception{
        ucab.dsw.servicio.Producto_presentacion_tipoORMWS servicio = new ucab.dsw.servicio.Producto_presentacion_tipoORMWS();
        Response resultado = servicio.consultarProducto_presentacion_tipo(1);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Producto_presentacion_tipo producto_presentacion_tipo = (Producto_presentacion_tipo) responseDto.getObjeto();
        System.out.println(producto_presentacion_tipo.get_id());
        Assert.assertNotEquals(producto_presentacion_tipo, null);
    }
}
