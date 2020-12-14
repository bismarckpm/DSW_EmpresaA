import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.Presentacion;
import ucab.dsw.entidades.Producto;
import ucab.dsw.entidades.Producto_presentacion_tipo;

import java.util.List;

public class Producto_presentacion_tipoORMWS_Test {

    @Test
    public void addProducto_presentacion_tipoTest() throws Exception {
        ucab.dsw.servicio.Producto_presentacion_tipoORMWS servicio = new ucab.dsw.servicio.Producto_presentacion_tipoORMWS();
        Producto_presentacion_tipoDto producto_presentacion_tipoDto = new Producto_presentacion_tipoDto();
        PresentacionDto presentacion = new PresentacionDto( 1);
        producto_presentacion_tipoDto.setPresentacionDto( presentacion );
        TipoDto tipo = new TipoDto( 1);
        producto_presentacion_tipoDto.setTipoDto( tipo );
        Producto producto = new Producto( 1);
        producto_presentacion_tipoDto.setEstado( "A" );
        Producto_presentacion_tipoDto resultado = servicio.addProducto_presentacion_tipo( producto_presentacion_tipoDto, producto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void showProducto_presentacion_tipoTest() throws Exception
    {
        ucab.dsw.servicio.Producto_presentacion_tipoORMWS servicio = new ucab.dsw.servicio.Producto_presentacion_tipoORMWS();
        List<Producto_presentacion_tipo> producto_presentacion_tipos = servicio.showProducto_presentacion_tipo();
        Assert.assertFalse("Consulta Realizada con Exito",producto_presentacion_tipos.isEmpty());
    }

    @Test
    public void updateProducto_presentacion_tipoTest() throws Exception{

        ucab.dsw.servicio.Producto_presentacion_tipoORMWS servicio = new ucab.dsw.servicio.Producto_presentacion_tipoORMWS();
        Producto_presentacion_tipoDto producto_presentacion_tipoDto = new Producto_presentacion_tipoDto(1);
        PresentacionDto presentacion = new PresentacionDto( 1);
        producto_presentacion_tipoDto.setPresentacionDto( presentacion );
        TipoDto tipo = new TipoDto( 1);
        producto_presentacion_tipoDto.setTipoDto( tipo );
        Producto producto = new Producto( 1);
        producto_presentacion_tipoDto.setEstado( "I" );
        Producto_presentacion_tipoDto resultado = servicio.editProducto_presentacion_tipo (producto_presentacion_tipoDto, producto);
        Assert.assertNotEquals( resultado.getId(), 0);
    }

    @Test
    public void deleteProducto_presentacion_tipoTest() throws Exception{

        ucab.dsw.servicio.Producto_presentacion_tipoORMWS servicio = new ucab.dsw.servicio.Producto_presentacion_tipoORMWS();
        Producto_presentacion_tipoDto producto_presentacion_tipoDto = new Producto_presentacion_tipoDto(1);
        Producto_presentacion_tipoDto resultado = servicio.deleteProducto_presentacion_tipo(producto_presentacion_tipoDto);
        Assert.assertNotEquals( resultado.getId(), 0 );

    }
}
